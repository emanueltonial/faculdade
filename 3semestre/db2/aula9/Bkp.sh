echo  Rotina de backup FRIO do servidor Oracle
echo  O banco Oracle sera tirado do ar no inicio deste procedimento! 

# tira Oracle do ar

sqlplus sys/123 as sysdba << FIM_SQL
spool shutdownORACLE.log
shutdown immediate
spool off
exit
FIM_SQL

lsnrctl stop >> shutdownORACLE.log


# Backup TAR

tar -cvf BkpORACLE.tar /usr/lib/oracle/xe/oradata 
gzip -f BkpORACLE.tar 
rm -rf BkpORACLE.tar


# coloca Oracle do ar

sqlplus sys/123 as sysdba << FIM_SQL
spool startupORACLE.log
startup
spool off
exit
FIM_SQL

lsnrctl start >> startupORACLE.log
