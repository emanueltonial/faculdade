# Trabalho BD2 - BDs Objeto-Relacionais

## 1) Pelo seu conhecimento atual, qual a vantagem que os diferentes tipos de bancos de dados levam sobre os seus concorrentes?

Tendo mais contato com bancos relacionais e objeto-relacionais no dia a dia, consigo enxergar vantagens claras dependendo do critério analisado:

**Performance**: bancos relacionais e objeto-relacionais se saem melhor em queries complexas com JOINs e agregações, por conta de décadas de otimização dos query planners. Bancos orientados a objetos tendem a performar melhor quando os dados já chegam como objetos complexos e não precisam ser mapeados, mas em queries analíticas gerais ficam atrás.

**Tamanho**: bancos relacionais costumam ser mais eficientes no armazenamento de dados estruturados por conta da normalização. Bancos orientados a objetos podem ter overhead maior por armazenar metadados dos objetos junto com os dados em si.

**Integração com dispositivos móveis, aplicações embarcadas e IoT**: aqui o SQLite domina, sendo um banco relacional leve que roda direto no dispositivo sem servidor. Bancos orientados a objetos como o ObjectBox têm ganhado espaço no mobile por mapear direto com as classes da aplicação, mas ainda são nicho.

**Custo**: bancos relacionais open source como PostgreSQL e MySQL não têm custo de licença, com uma comunidade enorme. A maioria dos bancos orientados a objetos maduros é comercial, o que eleva o custo de adoção.

**Manutenção**: bancos relacionais têm documentação abundante, profissionais no mercado e ferramentas consolidadas. Bancos orientados a objetos têm uma comunidade menor, o que torna mais difícil encontrar suporte e profissionais experientes.

**Desenvolvimento de uma aplicação**: com ORMs como SQLAlchemy, Django ORM e Hibernate, o gap entre o modelo relacional e o código orientado a objetos foi bastante reduzido. Bancos orientados a objetos eliminam esse mapeamento por completo, mas a curva de aprendizado e a falta de adoção em larga escala tornam a escolha arriscada para a maioria dos projetos.

---

## 2) Cite pelo menos 3 bancos de dados dentro de cada um dos tipos: Relacional, Objeto-Relacional e Orientado a Objetos

Relacional:

- MySQL
- MariaDB
- SQLite

Objeto-Relacional:

- PostgreSQL
- Oracle Database
- IBM Db2

Orientado a Objetos:

- ObjectDB
- db4o (extinto, descontinuado em 2014)
- Objectivity/DB

---

## 3) Os bancos de dados relacionais foram e continuam sendo amplamente utilizados. Se eles fizeram e fazem tanto sucesso, qual a necessidade que justifica o crescimento dos bancos de dados orientados a objeto?

O modelo relacional foi criado para dados tabulares e estruturados, e funciona muito bem nesse contexto. O problema é que linguagens de programação modernas trabalham com objetos, herança, polimorfismo e estruturas complexas como listas aninhadas e referências entre objetos. Toda vez que a aplicação precisa salvar ou recuperar um objeto do banco relacional, existe um trabalho de mapeamento entre o mundo dos objetos e o mundo das tabelas, o que é chamado de impedance mismatch. Em domínios onde os dados são intrinsecamente complexos, como sistemas CAD, simulações científicas e modelagem de redes de telecomunicações, esse mapeamento se torna custoso e pouco natural. Os bancos orientados a objetos surgem como resposta a isso, permitindo salvar e recuperar os objetos diretamente sem conversão.

---

## 4) Percentual de uso dos bancos relacionais/objeto-relacionais versus bancos orientados a objetos

De acordo com dados do relatório da Mordor Intelligence (2025), os bancos de dados relacionais lideraram o mercado global de bancos de dados com 57,30% de participação em 2025.

O DB-Engines Ranking de junho de 2026 reforça esse cenário: dos 10 sistemas mais populares no ranking completo, 7 são relacionais ou objeto-relacionais (Oracle, MySQL, Microsoft SQL Server, PostgreSQL, Snowflake, IBM Db2 e SQLite),  sem nenhum banco puramente orientado a objetos no topo da lista.

Já o mercado de bancos orientados a objetos, segundo a Verified Market Reports, foi avaliado em aproximadamente 5,8 bilhões de dólares em 2024, com projeção de alcançar 12,4 bilhões até 2033, registrando um CAGR de 9,2%.  Apesar do crescimento, o segmento ainda é consideravelmente menor comparado ao mercado relacional como um todo.

Fontes:
- Mordor Intelligence: [https://www.mordorintelligence.com/industry-reports/database-market](https://www.mordorintelligence.com/industry-reports/database-market)
- DB-Engines Ranking: [https://db-engines.com/en/ranking](https://db-engines.com/en/ranking)
- Verified Market Reports: [https://www.verifiedmarketreports.com/product/object-oriented-databases-software-market/](https://www.verifiedmarketreports.com/product/object-oriented-databases-software-market/)