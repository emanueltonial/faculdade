# Atividade EAD BD2 - Bancos de Dados NoSQL

## 1) Os bancos de dados NoSQL armazenam informações não-estruturadas, ao contrário dos bancos de dados relacionais onde as informações estão estruturadas. Mas o que significa “estruturar” as informações? o que seria uma “estrutura” ?

No contexto de banco de dados, estruturar informações é modelar os dados dentro de uma estrutura previsivel, como por exemplos colunas, relação entre tabelas, tipagem dos dados. Uma estrutura na prática, seria um banco de dados com colunas como (id_item, descricao_item, peso_item) cada um com seu tipo de dado esperado (int, varchar, number) e as informações adicionadas a essas colunas tem que seguir.

---

## 2) Qual o critério para você escolher implementar em uma empresa um banco de dados relacional ou um banco de dados NoSQL?

O critério principal para o uso de NoSQL e SQL dependente do tipo de aplicação que está sendo construida. Em casos que a base de dados tem muitas tabelas, precisa de colunas padronizados, relacionamentos complexos entre elas, e que sigam os principios ACID, por exemplo: sistemas de pagamento, sistemas de RH, contabilidade. Por outro lado, o caso de uso de NoSQL é quando a aplicação precisa de flexibilidade, escala horizontal e velocidade de write/read. Dentre os bancos de dados não relacionais, existem diferentes modelos:

- Document Database(MongoDB, Firestore): Dados semiestruturados, schemas variaveis, APIs REST.
- Chave-Valor(Redis, DynamoDB): Caches, sessões, filas, dados de acesso ultra-rápido.
- Colunar(Cassandra): Séries temporais, escritas massivas e em computação distribuida.
- Grafo (Neo4j): Relacionamentos complexos entre entidades.

De forma geral, o uso de SQL é preferível quando existem relacionamentos muito complexos e transações que usam dados de multiplas entidades. E o NoSQL quando o que mais importa é escala, schema flexivel e velocidade.

---

## 3) Cite alguns tipos de aplicação para as quais são mais indicados os bancos de dados NoSQL e alguns tipos de aplicação para as quais são mais indicados bancos de dados relacionais

Banco de Dados Relacionais:

- Sistemas de pagamento;
- Transferências Bancárias;
- ERPs;
- Sistemas de RH;
- Sistemas de Contabilidade.

Banco de Dados Não Relacionais:

- Redes Sociais(Schema Flexivel);
- Plataformas que usam sistema de recomendações(Grafos);
- Sistemas de IoT com séries temporais(colunar);
- Cache(Redis)
- Categorias de produtos com atributos diferentes por categorias(Document).
