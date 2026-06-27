#!/bin/bash
#
# =============================================================================
# Trabalho 2 - Replicacao de Dados (Oracle 10g XE)
# -----------------------------------------------------------------------------
# Replica os pedidos por filial a partir da "matriz" (usuario PROD), seguindo
# o metodo da disciplina: DATABASE LINK + SNAPSHOT (REFRESH FAST) + SNAPSHOT LOG
# na origem + JOB de atualizacao a cada 3 minutos (dbms_job).
#
#   Filial 1 -> FILIALA      Filial 2 -> FILIALB
#
# Origem dos dados : PROD.PEDIDO e PROD.ITEM_PEDIDO (Trabalho 1)
# Pre-requisito    : ambiente Oracle configurado (tnsnames com o alias 'XE')
#                    e usuario PROD ja populado.
# =============================================================================

set -e

echo "=============================================="
echo " Trabalho 2 - iniciando replicacao"
echo "=============================================="

sqlplus system/123 << 'FIM_SCRIPT'

SET ECHO ON
SET SERVEROUTPUT ON

-- =========================================================================
-- PASSO 1 - CRIACAO DOS USUARIOS FILIALA E FILIALB
-- (DROP antes torna o script reexecutavel; o erro do 1o run e ignorado)
-- =========================================================================

DROP USER FILIALA CASCADE;
DROP USER FILIALB CASCADE;

CREATE USER FILIALA IDENTIFIED BY filiala DEFAULT TABLESPACE users;
GRANT CONNECT, RESOURCE TO FILIALA;
GRANT CREATE DATABASE LINK TO FILIALA;
GRANT CREATE MATERIALIZED VIEW TO FILIALA;

CREATE USER FILIALB IDENTIFIED BY filialb DEFAULT TABLESPACE users;
GRANT CONNECT, RESOURCE TO FILIALB;
GRANT CREATE DATABASE LINK TO FILIALB;
GRANT CREATE MATERIALIZED VIEW TO FILIALB;

-- =========================================================================
-- PASSO 2 - CRIACAO DOS SNAPSHOTS
-- =========================================================================

-- ----- 2.1) Snapshot log na ORIGEM (matriz = PROD) -----
-- Necessario para o REFRESH FAST do snapshot de PEDIDO (senao: ORA-23413).
-- DROP antes para reexecutar sem erro.
CONN PROD/prod

DROP SNAPSHOT LOG ON PEDIDO;
CREATE SNAPSHOT LOG ON PEDIDO;

-- ----- 2.2) FILIALA: database link + snapshots (somente FILIAL 1) -----
CONN FILIALA/filiala

CREATE DATABASE LINK DL_PROD
  CONNECT TO PROD IDENTIFIED BY prod
  USING 'XE';

-- PEDIDO: filtro de uma tabela -> permite REFRESH FAST
CREATE SNAPSHOT PEDIDO
  REFRESH FAST
  AS SELECT * FROM PEDIDO@DL_PROD
     WHERE FILIAL = 1;

-- ITEM_PEDIDO: nao tem coluna FILIAL; filtra pelos pedidos da filial via
-- subconsulta. Subconsulta nao e fast-refreshable -> REFRESH COMPLETE.
CREATE SNAPSHOT ITEM_PEDIDO
  REFRESH COMPLETE
  AS SELECT * FROM ITEM_PEDIDO@DL_PROD
     WHERE COD_PED IN (SELECT COD_PED FROM PEDIDO@DL_PROD WHERE FILIAL = 1);

-- ----- 2.3) FILIALB: database link + snapshots (somente FILIAL 2) -----
CONN FILIALB/filialb

CREATE DATABASE LINK DL_PROD
  CONNECT TO PROD IDENTIFIED BY prod
  USING 'XE';

CREATE SNAPSHOT PEDIDO
  REFRESH FAST
  AS SELECT * FROM PEDIDO@DL_PROD
     WHERE FILIAL = 2;

CREATE SNAPSHOT ITEM_PEDIDO
  REFRESH COMPLETE
  AS SELECT * FROM ITEM_PEDIDO@DL_PROD
     WHERE COD_PED IN (SELECT COD_PED FROM PEDIDO@DL_PROD WHERE FILIAL = 2);

-- =========================================================================
-- PASSO 3 - JOBS DE REPLICACAO AUTOMATICA (a cada 3 minutos)
-- Intervalo: SYSDATE + 3/1440. PEDIDO refresca 'F' (fast), ITEM 'C' (complete).
-- =========================================================================

-- ----- JOB da FILIALA -----
CONN FILIALA/filiala

VARIABLE X NUMBER;
BEGIN
  DBMS_JOB.SUBMIT(
    :X,
    'DBMS_SNAPSHOT.REFRESH(''PEDIDO'',''F''); DBMS_SNAPSHOT.REFRESH(''ITEM_PEDIDO'',''C'');',
    SYSDATE + 1/1440,
    'SYSDATE + 3/1440'
  );
END;
/
COMMIT;

-- ----- JOB da FILIALB -----
CONN FILIALB/filialb

VARIABLE X NUMBER;
BEGIN
  DBMS_JOB.SUBMIT(
    :X,
    'DBMS_SNAPSHOT.REFRESH(''PEDIDO'',''F''); DBMS_SNAPSHOT.REFRESH(''ITEM_PEDIDO'',''C'');',
    SYSDATE + 1/1440,
    'SYSDATE + 3/1440'
  );
END;
/
COMMIT;

-- =========================================================================
-- VERIFICACAO - dados replicados e jobs agendados em cada filial
-- =========================================================================

CONN FILIALA/filiala
PROMPT === FILIALA: PEDIDO ===
SELECT * FROM PEDIDO ORDER BY COD_PED;
PROMPT === FILIALA: ITEM_PEDIDO ===
SELECT * FROM ITEM_PEDIDO ORDER BY COD_PED, NUM_ITEM;
PROMPT === FILIALA: JOBS ===
SELECT JOB, WHAT, INTERVAL FROM USER_JOBS;

CONN FILIALB/filialb
PROMPT === FILIALB: PEDIDO ===
SELECT * FROM PEDIDO ORDER BY COD_PED;
PROMPT === FILIALB: ITEM_PEDIDO ===
SELECT * FROM ITEM_PEDIDO ORDER BY COD_PED, NUM_ITEM;
PROMPT === FILIALB: JOBS ===
SELECT JOB, WHAT, INTERVAL FROM USER_JOBS;

EXIT;
FIM_SCRIPT

echo ""
echo "=============================================="
echo " Replicacao criada com sucesso."
echo "=============================================="