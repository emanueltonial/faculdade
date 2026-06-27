select trunc(dt_emis, 'MM') Emissao, sum(peso_brt) PesoBruto
from tnfs_saida_hist
where dt_emis between '30-NOV-02' and '30-NOV-04' 
and uf = 'RS' 
and sit_nf = 'I' 
and peso_liq > 100000
group by trunc(dt_emis, 'MM');

/* Criação do Index */
create index tnfs_saida_hist_ix1
on tnfs_saida_hist (dt_emis, uf, sit_nf, peso_liq);

/* SELECTs */

select uf, count(*)
from tnfs_saida_hist
group by uf order by 2;

select count(*)
from tnfs_saida_hist
where uf = 'RS';

select uf, cidade, sum(vlr_total)
from tnfs_saida_hist
where dt_emis between '01-MAR-04' and '31-MAR-04'
and sit_nf = 'C'
and vlr_total > 0
group by uf, cidade
having sum(vlr_total) > 1000000;

select dt_emis,
sum(vlr_total)VlrTotal,
sum(peso_liq)PesoLiq,
sum(peso_brt)PesoBrt
from tnfs_saida_hist
where dt_emis between '01-JAN-04' and '10-JAN-04'
group by dt_emis
order by 1;

select dt_emis,
sum(vlr_total)VlrTotal,
sum(peso_liq)PesoLiq
from tnfs_saida_hist
where dt_emis between '01-JAN-04' and '10-JAN-04'
group by dt_emis
order by 1;