# Atividade EAD BD2 - Data Warehousing

## 1) Já que um Data Warehouse (sistema OLAP - Online Analytical Processing) é utilizado por bem menos pessoas do que um sistema OLTP (Online Transaction Processing - um ERP, por exemplo) poderíamos investir menos dinheiro no hardware que suportaria um Data Warehouse afinal de contas o sistema será menos concorrido – utilizado por menos pessoas, somente a turma da análise de dados. Você concorda com essa afirmação? Justifique sua resposta

Essa afirmação não esta correta e é problemática por diversas razões. Enquanto a organização OLTP foca principalmente na lógica do produto, como usar um repository no backend para armazenar dados no banco, o OLAP foca em manter os dados dos clientes e do negócio o mais claro possivel. No modelo OLAP é realizado ETLs ou ELTs para armazenamento dos dados do banco OLTP. Os sistemas OLAP são usados por analistas, engenheiros e cientistas de dados, e por mais que tenham menos transações e usuários alterando eles, a quantidade de dados e as queries são bem mais massivas, muitas vezes retornando milhões de registros. Sendo assim, um bom hardware para *Data Warehousing* se mostra muito importante.

---

## 2) Qual a necessidade afinal de possuírmos um Data Warehouse já que a origem dos dados é o sistema OLTP em produção? Poderíamos ter um sistema único – o ERP – e extrair informações diretamente dele. Está certo esse raciocínio?

O grande problema de nao usar um Data Warehouse, é que o sistema que usa OLTP, precisa de baixa latência e ter alta disponiblidade. Se as queries complexas realizadas em Data Warehouse fossem realizadas no banco de dados em produção, ocasionaria em maior tempo de resposta, concorrência e problemas eventuais com o banco de dados ficando fora depois de queries ou carregamentos complexos.

---

## 3) Extract, Transform, Load são ações ou atividades periodicamente atreladas à alimentação de um Data Warehouse. Por que cada uma delas é importante?

As etapas de *extração, transformação e carregamento* são extremamente relevantes na alimentação de um Data Warehouse. A extração é importante pois os dados de origem são do sistema em produção, não podendo altera-los ali. Seguindo a transformação é essencial, já que os dados que vieram de outras fontes não estão normalizados e seguindo as escolhas de modelagem do banco de dados. Por fim, o carregamento se mostra de grande importância e precisa ser feito de forma eficiente, com transações atomicas para não deixar o DW com estado incosistente.

---

## 4) Por que a figura geométrica de um CUBO foi associada às operações de análise de dados realizadas em sistemas de Data Warehouse?

A figura de um *CUBO* muitas vezes é associada as operações de análise de dados, já que representa muitos eixos/dimensões(tempo, produto, região) e cada ponto dentro dele é um valor métrico cruzado por todas as outras dimensões.

---

## 5) Quais os tipos de conhecimento que você identifica como sendo necessários para uma pessoa ou uma equipe de pessoas trabalhar/rem com um sistema de Data Warehouse? Imagine que você tenha que contratar alguém. Quais conhecimentos e habilidades você exigiria?

Nós dias atuais, o processamento de dados ocorre em sua maioria usando Python. Existem bibliotecas como Pandas e PySpark, que ajudam a analise e transformação de dados. Portanto, os requisitos que eu pediria para uma vaga de *Data Engineering* moderna seria:

- Python;
- SQL Avançado;
- Modelagem de dados(Snowflake Schema, Star Schema)
- PySpark
- Ferramentas de orquestração(Airflow, Dagster)
- DW em cloud(Snowflake, Redshift)
- Cloud Provider(AWS, Azure ou GCP)
- Docker
