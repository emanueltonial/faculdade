#!/bin/bash
sqlplus system/123 << 'fim_script'

-- criacao dos users
create user filiala identified by filiala default tablespace users;

grant connect, resource to filiala;
grant create database link to filiala;
grant create materialized view to filiala;
create user filialb identified by filialb default tablespace users;

grant connect, resource to filialb;
grant create database link to filialb;
grant create materialized view to filialb;

-- criacao dos snapshots
conn prod/prod

-- criar as views com user prod
create materialized view log on pedido;
create materialized view log on item_pedido;

-- filiala
conn filiala/filiala

create database link dl_prod
    connect to prod identified by prod
    using 'xe';

create snapshot pedido
    refresh fast
    as select * from pedido@dl_prod
        where filial = 1;

create snapshot item_pedido
    refresh complete
    as select * from item_pedido@dl_prod
        where cod_ped in (
            select cod_ped from pedido@dl_prod where filial = 1
        );

-- filialb
conn filialb/filialb

create database link dl_prod
    connect to prod identified by prod
    using 'xe';

create snapshot pedido
    refresh fast
    as select * from pedido@dl_prod
        where filial = 2;

create snapshot item_pedido
    refresh complete
    as select * from item_pedido@dl_prod
        where cod_ped in (
            select cod_ped from pedido@dl_prod where filial = 2
        );

-- jobs de replicacao

--filiala
conn filiala/filiala

variable x number;
begin
    dbms_job.submit(
    :x,
    'dbms_snapshot.refresh(''pedido'', ''f''); 
    dbms_snapshot.refresh(''item_pedido'', ''c'');', 
    sysdate,
    'sysdate + 3/1440'
    );
end;
/
commit;

--filialb
conn filialb/filialb

variable x number;
begin
    dbms_job.submit(
    :x,
    'dbms_snapshot.refresh(''pedido'', ''f''); 
    dbms_snapshot.refresh(''item_pedido'', ''c'');', 
    sysdate,
    'sysdate + 3/1440'
    );
end;
/
commit;


-- verificacao dos dados replicados e jobs
conn filiala/filiala
prompt === filiala: pedido ===
select * from pedido order by cod_ped;
prompt === filiala: item_pedido ===
select * from item_pedido order by cod_ped, num_item;
prompt == filiala: jobs ===
select job, what, interval from user_jobs;

conn filialb/filialb
prompt === filialb: pedido ===
select * from pedido order by cod_ped;
prompt === filialb: item_pedido ===
select * from item_pedido order by cod_ped, num_item;
prompt == filialb: jobs ===
select job, what, interval from user_jobs;

exit
fim_script
