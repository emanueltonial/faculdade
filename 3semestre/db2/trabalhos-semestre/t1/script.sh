sqlplus system/123 << fim_script

create user prod identified by prod default tablespace users;
grant connect, resource to prod;

conn prod/prod

create table cliente (
  cod_cli number not null,
  razao_social varchar2(255) not null,
  nome_fantasia varchar2(30) not null,
  constraint pk_cliente primary key(cod_cli)
);

create table produto (
  cod_prod number not null,
  descricao varchar2(255) not null,
  valor number(10,2) not null,
  constraint pk_produto primary key(cod_prod)
);

create table pedido (
  cod_ped number not null,
  cod_cli number not null,
  filial number(1) not null,
  constraint pk_pedido primary key(cod_ped),
  constraint fk_cliente foreign key(cod_cli) references cliente(cod_cli)
);

create table item_pedido (
  cod_ped number not null,
  cod_prod number not null,
  num_item number not null,
  qtde number not null,
  constraint pk_item_pedido primary key(cod_ped, cod_prod, num_item),
  constraint fk_item_pedido_ped foreign key(cod_ped) references pedido(cod_ped),
  constraint fk_item_pedido_prod foreign key(cod_prod) references produto(cod_prod)
);

create table aux_hist_cliente (
  id_hist number not null,
  cod_cli number not null,
  campo_alterado varchar2(30) not null,
  valor_antigo varchar2(255),
  valor_novo varchar2(255),
  data_alteracao date not null,
  constraint pk_hist_cliente primary key (id_hist),
  constraint fk_hist_cliente foreign key (cod_cli) references cliente(cod_cli)
);

create sequence seq_hist_cliente start with 1 increment by 1 nocache;

create or replace trigger t_hist_cliente
after update of razao_social, nome_fantasia on cliente
for each row
begin
  if :old.razao_social != :new.razao_social then
    insert into aux_hist_cliente
      (id_hist, cod_cli, campo_alterado, valor_antigo, valor_novo, data_alteracao)
    values
      (seq_hist_cliente.nextval, :new.cod_cli, 'razao_social', :old.razao_social, :new.razao_social, sysdate);
  end if;
  if :old.nome_fantasia != :new.nome_fantasia then
    insert into aux_hist_cliente
      (id_hist, cod_cli, campo_alterado, valor_antigo, valor_novo, data_alteracao)
    values
      (seq_hist_cliente.nextval, :new.cod_cli, 'nome_fantasia', :old.nome_fantasia, :new.nome_fantasia, sysdate);
  end if;
end;
/

create or replace procedure p_busca_filiais_invalidas as
begin
  for r_pedido in (
    select cod_ped, cod_cli, filial
    from pedido
    where filial not in (1, 2, 3)
  ) loop
    dbms_output.put_line('cod_ped: ' || r_pedido.cod_ped);
    dbms_output.put_line('cod_cli: ' || r_pedido.cod_cli);
    dbms_output.put_line('filial : ' || r_pedido.filial);
    dbms_output.put_line('----------------------------------------------');
  end loop;
end;
/

insert into cliente values (1, 'Cliente Um', 'Lojas Um');
insert into cliente values (2, 'Cliente Dois', 'Magazine Dois');
insert into cliente values (3, 'Cliente Tres', 'Hipermercado Tres');
insert into cliente values (4, 'Cliente Quatro', 'Atacadao Quatro');
insert into cliente values (5, 'Cliente Cinco', 'Distribuidora Cinco');

insert into produto values (111, 'Silicone Incolor 300g', 12.00);
insert into produto values (112, 'Cimento Portland CP II 50 kg', 28.00);
insert into produto values (113, 'Tijolo ceramico 6 furos', 1.20);
insert into produto values (114, 'Telha ceramica tipo romana', 3.50);
insert into produto values (115, 'Tinta acrilica branca 18L', 230.00);
insert into produto values (116, 'Rejunte ceramico 1kg', 9.50);
insert into produto values (117, 'Argamassa ACI 20Kg', 22.00);
insert into produto values (118, 'Interruptor simples de embutir', 12.00);
insert into produto values (119, 'Tubo PVC soldavel 25 mm 6m', 32.00);
insert into produto values (120, 'Fechadura externa padrao', 95.00);

insert into pedido values (11, 2, 3);
insert into pedido values (12, 4, 1);
insert into pedido values (13, 5, 2);
insert into pedido values (14, 3, 2);
insert into pedido values (15, 4, 1);
insert into pedido values (16, 2, 3);
insert into pedido values (17, 1, 1);
insert into pedido values (18, 2, 3);
insert into pedido values (19, 4, 1);
insert into pedido values (20, 1, 1);
insert into pedido values (21, 5, 2);
insert into pedido values (22, 3, 5);
insert into pedido values (23, 5, 2);
insert into pedido values (24, 3, 4);

insert into item_pedido values (11, 112, 1, 9);
insert into item_pedido values (11, 118, 2, 5);
insert into item_pedido values (12, 120, 1, 3);
insert into item_pedido values (12, 119, 2, 1);
insert into item_pedido values (12, 111, 3, 2);
insert into item_pedido values (13, 113, 1, 6);
insert into item_pedido values (13, 115, 2, 7);
insert into item_pedido values (13, 120, 3, 3);
insert into item_pedido values (13, 119, 4, 9);
insert into item_pedido values (13, 111, 5, 8);
insert into item_pedido values (14, 120, 1, 6);
insert into item_pedido values (15, 117, 1, 7);
insert into item_pedido values (15, 116, 2, 5);
insert into item_pedido values (16, 115, 1, 6);
insert into item_pedido values (16, 114, 2, 6);
insert into item_pedido values (16, 113, 3, 2);
insert into item_pedido values (16, 111, 4, 3);
insert into item_pedido values (17, 117, 1, 1);
insert into item_pedido values (17, 112, 2, 4);
insert into item_pedido values (18, 112, 1, 5);
insert into item_pedido values (19, 120, 1, 3);
insert into item_pedido values (19, 115, 2, 4);
insert into item_pedido values (20, 111, 1, 5);
insert into item_pedido values (21, 111, 1, 4);
insert into item_pedido values (21, 112, 2, 7);
insert into item_pedido values (22, 119, 1, 2);
insert into item_pedido values (22, 118, 2, 3);
insert into item_pedido values (22, 117, 3, 1);
insert into item_pedido values (23, 115, 1, 2);
insert into item_pedido values (23, 111, 2, 4);
insert into item_pedido values (23, 112, 3, 3);
insert into item_pedido values (24, 113, 1, 2);

commit;
exit;
fim_script
