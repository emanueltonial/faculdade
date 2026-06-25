sqlplus sys/123 as sysdba << FIM_SQL

set lines 80 
set pages 100 
col name for a50

spool Total_Datafiles.log

select sum(bytes)/1024/1024/1024 "Soma Data Files Oracle"
from dba_data_files;

spool off

FIM_SQL

