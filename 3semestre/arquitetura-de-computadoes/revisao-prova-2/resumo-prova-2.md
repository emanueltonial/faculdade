![Status](https://img.shields.io/badge/status-concluído-brightgreen)
![Tema](https://img.shields.io/badge/tema-arquiteturas%20paralelas-blue)

```
Título    : Arquiteturas Paralelas (resumo dos slides)
Data      : 2026-06-16
Tags      : #infra #computacao #paralelismo #arquitetura
Status    : ✅ Concluído
Fonte     : Slides ERAD-RS 2013, Prof. César A. F. De Rose e Prof. Alexandro M. S. Adário (PUCRS / URI)
```

> 📌 **Referência:** Plataformas para Processamento Paralelo e Distribuído, ERAD-RS 2013.

---

## 🎯 Ideia central do material

O texto conta a história de por que a computação migrou do modelo sequencial para o paralelo, mostra como o paralelismo aparece em vários níveis (do pipeline dentro de um processador até grades de máquinas espalhadas pelo mundo), e fecha com como classificar e construir máquinas paralelas. O fio condutor é sempre o mesmo: dividir o trabalho para ganhar desempenho, e os custos que vêm junto (comunicação, sincronização, coerência).

---

## 📚 SUMÁRIO

1. Motivação histórica
2. Níveis de paralelismo
3. Evolução do processador (pipeline, superescalar, multithreading, multicore)
4. Fatores de desempenho (Lei de Amdahl, speedup, eficiência)
5. Classificação de Flynn
6. Classificação por memória (UMA, NUMA, COMA, NORMA)
7. Redes de interconexão (estáticas e dinâmicas)
8. Chaveamento e roteamento
9. Plataformas tradicionais (PVP, SMP, MPP, NOW)
10. Tecnologias de interconexão
11. Tópicos atuais (Grid, MPSoC, NoC, TOP500)

---

## 1. 📜 Motivação histórica

A linha do tempo do material mostra a pressão crescente por desempenho:

| Época   | Situação                                                                 |
| ------- | ------------------------------------------------------------------------ |
| Anos 50 | Desempenho sequencial era suficiente e atendia à demanda das aplicações  |
| Anos 70 | Surge a necessidade de mais desempenho, processadores mais velozes, e a ideia de processamento paralelo |
| Hoje    | O futuro da computação é paralelo                                        |

As três razões para apostar no paralelismo no material são:

1. **Aumento de desempenho** pela estratégia de dividir e conquistar.
2. **Alternativa aos limites físicos** (não dá para aumentar o clock para sempre por causa de calor e consumo).
3. **Maior tolerância a falhas**, já que uma unidade defeituosa pode ser substituída por outra.

> 💡 **Dica:** sempre que ouvir "não dá mais para aumentar o clock", a resposta histórica foi colocar mais unidades trabalhando juntas. Esse é o pano de fundo de tudo que vem depois.

---

## 2. 🪜 Níveis de paralelismo

O paralelismo não acontece em um único lugar. Ele aparece em camadas, da aplicação até o processador.

### Fluxo: `camadas onde o paralelismo aparece`

```
[Aplicação]          → Threads, Métodos Remotos, Processos Remotos
[Sistema Operacional]→ Multiprocessamento, Multiprogramação
[Arquitetura]        → Vários Processadores, Hierarquia de Memória e Barramentos
[Processador]        → Múltiplas Unidades Funcionais, Pipeline de Instrução
```

**O que isso quer dizer na prática:** quando você cria threads no seu código FastAPI, está usando paralelismo no nível da aplicação. Quando o SO decide rodar vários processos "ao mesmo tempo", é multiprogramação. Quando o hardware tem vários cores, é o nível de arquitetura. E dentro de um core o pipeline já é uma forma de paralelismo. Tudo isso convive.

---

## 3. 🧬 Evolução do processador

Antes de falar de várias máquinas, o material mostra o paralelismo crescendo **dentro** de um processador.

### `arquitetura tradicional`

**O que é:** o modelo clássico de von Neumann, com Entrada, CPU, Saída e Memória ligados de forma direta.

**Fluxo:**

```
[E] → [CPU] → [S]
        ↕
     [Memória]
```

**Limitação:** a CPU fica esperando a memória, o famoso gargalo de von Neumann.

### `DMA (Direct Memory Access)`

**O que é:** um mecanismo que deixa dispositivos de E/S acessarem a memória sem passar tudo pela CPU.

**Por que importa:** libera a CPU de tarefas de transferência (delegação), o que possibilita a multiprogramação de verdade.

**Exemplo do dia a dia:** ler um arquivo grande do disco. Com DMA, a CPU dispara a transferência e vai fazer outra coisa enquanto os dados chegam à memória sozinhos.

### `hierarquia de memória`

**O que é:** vários níveis de memória organizados por velocidade, custo e tamanho.

**Metáfora:** é como uma mesa de trabalho. O que você usa agora fica na mão (registradores), o material do dia fica na mesa (cache), os livros ficam na estante (memória principal) e o arquivo morto fica no almoxarifado (disco).

```
Mais rápido / mais caro / menor
   Registradores
   Cache L1
   Cache L2
   Memória Principal
   Memória Virtual (Disco)
Mais lento / mais barato / maior
```

**Objetivo:** acelerar a alimentação da CPU e reduzir o abismo de velocidade entre CPU e memória, que só aumentou com o tempo.

### `pipeline`

**O que é:** dividir a execução de uma instrução em etapas (Busca, Decodificação, Execução, escrita do resultado) e sobrepor essas etapas de várias instruções.

**Metáfora:** linha de montagem. Enquanto um carro está sendo pintado, outro já está na fase de motor.

**Comparação:**

```
SEM pipeline (uma instrução por vez):
Instrução 1: B D E F
Instrução 2:         B D E F
Instrução 3:                 B D E F

COM pipeline (etapas sobrepostas):
Instrução 1: B D E F
Instrução 2:   B D E F
Instrução 3:     B D E F
Instrução 4:       B D E F
```

**Ganho:** mais instruções terminam por unidade de tempo, mesmo sem aumentar o clock.

### `superescalaridade`

**O que é:** replicar unidades funcionais dentro da mesma CPU para executar mais de uma instrução por ciclo.

**Diferença para o pipeline:** o pipeline sobrepõe etapas de instruções diferentes. A superescalaridade vai além e executa instruções de verdade em paralelo, lado a lado.

**Limites práticos citados:**

1. Máximo prático de cerca de 2 instruções por ciclo.
2. Programação sequencial limita o que dá para paralelizar.
3. Dependência de dados (uma instrução depende do resultado da outra).
4. Fluxo de controle (desvios e condicionais).

> ⚠️ **Atenção:** replicar unidades dentro de um core esbarra rápido em dependências. Por isso a indústria partiu para outras soluções, como multithreading e multicore.

### `multithreading`

**O que é:** executar mais de uma thread (fluxo de execução) por aplicação, para manter as unidades do processador ocupadas quando uma thread trava esperando dados.

**Os três tipos do material:**

| Tipo                              | Como funciona                                                                 |
| --------------------------------- | ----------------------------------------------------------------------------- |
| Interleaved (IMT)                 | Troca de thread a cada ciclo, intercalando instruções de threads diferentes   |
| Blocked (BMT)                     | Fica na mesma thread e só troca quando acontece um evento de alta latência    |
| Simultaneous (SMT)                | Várias threads despacham várias instruções no mesmo ciclo (superescalar com multithread) |

**Exemplo conhecido:** o Hyper-Threading da Intel é uma implementação de SMT. Um core físico aparece como dois lógicos.

### `multicore`

**O que é:** integrar múltiplos cores no mesmo chip, replicando os recursos de processamento.

**Vantagem central:** mais desempenho sem precisar aumentar o clock.

> ⚖️ **Trade-off: concorrência vs paralelismo**
>
> | Cenário           | O que acontece                                                  |
> | ----------------- | --------------------------------------------------------------- |
> | 1 CPU             | Concorrência, as tarefas se revezam e a CPU vira gargalo        |
> | 2 CPUs, 2 apps    | Paralelismo real, cada app em um core                           |
> | 2 CPUs, 1 app     | Paralelismo de threads, a mesma aplicação espalha threads nos cores |
>
> **Decisão de projeto:** ter cores não basta, a aplicação precisa ser escrita para usar threads, senão você só ganha concorrência.

**Exemplos do material:** Intel Dual Core e AMD Turion Dual Core.

---

## 4. 📊 Fatores de desempenho

Ter hardware paralelo não garante ganho. O material levanta três perguntas que definem o desempenho final de uma aplicação paralela: quantos processadores usar, como distribuir a carga entre eles, e quando comunicar ou sincronizar.

### `Lei de Amdahl`

**O que é:** o ganho que você obtém melhorando uma parte do sistema é limitado pela fração de tempo em que essa parte é realmente usada.

**Tradução direta:** se 10% do seu programa é sequencial e não dá para paralelizar, esses 10% viram o teto do seu ganho, não importa quantos cores você jogue no problema.

**Exemplo:** um pipeline de ETL onde a leitura da API é sequencial mas o processamento dos dados é paralelizável. Se a leitura toma metade do tempo, paralelizar o processamento nunca vai te dar mais que o dobro de velocidade no total.

> 💡 **Dica:** essa lei é o motivo de sempre medir antes de otimizar. Acelerar a parte errada quase não move o ponteiro.

### `speedup`

**O que é:** o fator de aceleração, ou seja, quanto a versão paralela ficou mais rápida que a versão sequencial.

**Fórmula em palavras:** tempo da versão sequencial dividido pelo tempo da versão paralela.

### `eficiência`

**O que é:** a taxa média de utilização das unidades ativas usadas.

**Exemplo:** se você usa 8 cores mas em média só 4 ficam ocupados, sua eficiência está em torno de 50%. Speedup alto com eficiência baixa significa desperdício de recurso.

---

## 5. 🗂️ Classificação de Flynn

**Origem:** Michael Flynn, 1970. Classifica máquinas cruzando fluxos de instruções com fluxos de dados.

| Fluxos                | 1 fluxo de dados | N fluxos de dados |
| --------------------- | ---------------- | ----------------- |
| 1 fluxo de instruções | SISD             | SIMD              |
| N fluxos de instruções| MISD             | MIMD              |

### `SISD` (Single Instruction, Single Data)

**O que é:** uma instrução agindo sobre um dado por vez.

**Exemplo:** arquiteturas tradicionais, máquinas de von Neumann, microcomputadores e estações de trabalho clássicas.

### `MISD` (Multiple Instruction, Single Data)

**O que é:** várias instruções sobre o mesmo dado.

**Status:** classe teórica, citada no material como ainda sem implementação prática real.

### `SIMD` (Single Instruction, Multiple Data)

**O que é:** uma mesma instrução aplicada a muitos dados ao mesmo tempo, em execução síncrona.

**Exemplo:** processadores gráficos (GPUs) e arquiteturas de array para matrizes, imagens e vetores. Quando uma GPU soma dois vetores enormes aplicando a mesma operação a cada posição, isso é SIMD.

### `MIMD` (Multiple Instruction, Multiple Data)

**O que é:** vários programas rodando sobre vários dados, de forma independente.

**Exemplo:** as arquiteturas paralelas modernas em geral. Um cluster onde cada nó roda seu próprio programa sobre seus próprios dados é MIMD.

---

## 6. 🧠 Classificação por memória

Aqui o material separa as máquinas MIMD por dois critérios: como a memória é compartilhada e como ela é organizada.

> ⚖️ **Trade-off: Multiprocessadores vs Multicomputadores**
>
> | Critério     | Multiprocessadores            | Multicomputadores              |
> | ------------ | ----------------------------- | ------------------------------ |
> | Memória      | Compartilhada                 | Não compartilhada              |
> | Comunicação  | Variáveis compartilhadas      | Troca de mensagens             |
>
> **Resumo:** no multiprocessador todos enxergam a mesma memória. No multicomputador cada nó tem a sua e eles conversam mandando mensagens.

Dentro disso, o material organiza quatro modelos:

```
MIMD
 ├─ Multiprocessadores (memória compartilhada)
 │    ├─ UMA  (centralizada)
 │    ├─ NUMA (distribuída)
 │    └─ COMA (distribuída)
 └─ Multicomputadores (memória não compartilhada)
      └─ NORMA
```

### `UMA` (Uniform Memory Access)

**O que é:** memória centralizada, onde todo acesso leva o mesmo tempo.

**Problemas:** precisa tratar coerência das caches e sofre com contenção, que é a disputa dos processadores pelo mesmo recurso.

**Exemplo:** os antigos servidores SMP com vários processadores ligados ao mesmo barramento de memória.

### `NUMA` (Non-Uniform Memory Access)

**O que é:** memória distribuída, onde acessos levam tempos diferentes dependendo de qual parte da memória você acessa.

**Vantagem:** permite acessos em paralelo e reduz contenção, com a ideia de variáveis globais e locais.

**Exemplo:** servidores modernos de múltiplos soquetes, onde cada processador tem sua memória local rápida e acessa a memória do vizinho de forma mais lenta.

### `COMA` (Cache-Only Memory Architecture)

**O que é:** o acesso fica restrito às memórias cache, que funcionam como a memória principal.

**Vantagem:** mesma ideia da NUMA, permite acessos em paralelo e reduz contenção. É um modelo mais raro e específico.

### `NORMA` (No-Remote Memory Access)

**O que é:** cada nó só acessa a sua memória local, sem acesso direto à memória dos outros.

**Como conversam:** por troca de mensagens. É o modelo típico dos multicomputadores e clusters.

**Exemplo:** um cluster onde cada máquina tem sua RAM e a comunicação acontece via rede usando algo como MPI.

---

## 7. 🔌 Redes de interconexão

Se há vários processadores ou nós, eles precisam estar ligados. O material divide as redes em estáticas e dinâmicas.

### Redes estáticas

**O que são:** ligações fixas e diretas, dedicadas. A topologia escolhida determina as características da rede, como custo, escalabilidade e tolerância a falhas.

**Topologias citadas:**

| Topologia      | Característica principal                                                    |
| -------------- | -------------------------------------------------------------------------- |
| Array linear   | Nós em sequência, ligação simples                                          |
| Estrela        | Todos ligados a um nó central                                              |
| Anel simples   | Baixa escalabilidade e baixa tolerância a falhas, pode ser unidirecional ou bidirecional |
| Anel cordado   | Anel com atalhos para melhorar caminhos                                    |
| Anel completo  | Todos conectados entre si dentro do anel                                   |
| Malha (mesh)   | Roteamento simplificado e boa escalabilidade                               |
| Torus          | Malha com as extremidades conectadas, roteamento muito simplificado e boa escalabilidade |

> 💡 **Dica:** a malha e o torus aparecem como as opções com melhor escalabilidade. Não é coincidência que supercomputadores grandes usem topologias desse tipo.

### Redes dinâmicas

**O que são:** não têm topologia fixa, se adaptam por demanda.

| Tipo                  | Característica                                                          |
| --------------------- | ---------------------------------------------------------------------- |
| Barramento            | Todos os processadores e memórias compartilham o mesmo meio            |
| Rede multinível       | Vários estágios de chaves para conectar entradas a saídas              |
| Matriz de chaveamento | Alto custo, baixa escalabilidade, sem contenção e não bloqueante       |

> ⚠️ **Atenção:** o barramento é barato e simples, mas vira gargalo rápido porque todo mundo disputa o mesmo meio. A matriz de chaveamento resolve a contenção mas custa caro e escala mal.

---

## 8. 📨 Chaveamento e roteamento

**Contexto:** como não existem ligações diretas de todos para todos, a mensagem passa por nós intermediários até chegar ao destino. O material separa dois temas: como o caminho é estabelecido (chaveamento) e como a mensagem avança pelos nós (roteamento).

### Tipos de chaveamento

### `chaveamento de circuito`

**O que é:** estabelece todo o caminho antes de enviar, e só depois manda a mensagem.

**Custo:** alto, porque reserva o caminho inteiro.

**Metáfora:** ligação telefônica antiga, onde a linha fica reservada do início ao fim da conversa.

### `chaveamento de pacote`

**O que é:** não há caminho nem custo pré-definidos. Os nós são adicionados ao caminho conforme a mensagem avança e nenhum canal é reservado.

**Metáfora:** é como o correio. Cada pacote acha seu caminho e nada fica reservado só para ele.

### Tipos de roteamento

### `store-and-forward`

**O que é:** cada nó armazena o pacote inteiro num buffer e só então repassa para o próximo.

**Custo:** usa buffer para o pacote completo, o que adiciona atraso em cada salto.

### `cut-through` (e Wormhole)

**O que é:** o nó armazena apenas uma parte da mensagem (um flit) e já vai repassando, no estilo pipeline. O cabeçalho vai abrindo caminho, o que dá o nome de wormhole.

**Vantagem:** latência muito menor que o store-and-forward, porque não precisa esperar a mensagem inteira chegar antes de seguir.

> ⚖️ **Trade-off: Store-and-Forward vs Cut-Through**
>
> | Critério     | Store-and-Forward         | Cut-Through (Wormhole)     |
> | ------------ | ------------------------- | -------------------------- |
> | Buffer       | Pacote inteiro            | Apenas um flit             |
> | Latência     | Maior                     | Menor                      |
> | Estilo       | Espera e repassa          | Pipeline, repassa cedo     |

---

## 9. 🏗️ Plataformas tradicionais

O material apresenta quatro plataformas clássicas para montar máquinas paralelas.

### `PVP` (Parallel Vector Processor)

**O que é:** processadores vetoriais com memória concorrente UMA e matriz de chaveamento.

**Características:** baixa escalabilidade, grandes registradores e sem caches.

**Exemplos:** Cray T90 e NEC SX6.

### `SMP` (Symmetric Multiprocessor)

**O que é:** multiprocessadores simétricos com memória UMA e interconexão por barramento.

**Características:** baixa escalabilidade. Evoluíram para DSM, que é memória compartilhada distribuída no modelo NUMA.

**Exemplos:** Intel Quad Xeon 7400 Server e HP Integrity rx8620-32 Server.

### `MPP` (Massively Parallel Processors)

**O que é:** multicomputadores maciçamente paralelos com múltiplas memórias locais e interconexão por rede proprietária.

**Característica:** boa escalabilidade.

**Exemplos:** Intel Paragon, IBM SP2 e Connection Machine CM-5.

### `NOW` (Network of Workstations)

**O que é:** redes de estações de trabalho com múltiplas memórias locais ligadas por rede convencional.

**Características:** baixa escalabilidade mas custo baixo. É o ancestral do cluster comum montado com máquinas de prateleira.

### ⚖️ Comparação das plataformas

| Critério                | PVP   | SMP    | MPP      | NOW    |
| ----------------------- | ----- | ------ | -------- | ------ |
| Número de EPs           | Baixo | Baixo  | Alto     | Médio  |
| Escalabilidade          | Baixa | Baixa  | Alta     | Média  |
| Latência de programação | Baixa | Média  | Baixa    | Alta   |
| Programação             | Média | Fácil  | Difícil  | Difícil|
| Custo                    | Alto  | Médio  | Alto     | Baixo  |

> 🔍 **Investigar:** o material levanta a pergunta de qual classe escolher quando a necessidade não se encaixa em nenhuma. A resposta caminha para máquinas que juntam poucos EPs, boa escalabilidade, baixa latência e baixo custo, que é a direção dos supercomputadores modernos.

**Exemplos de supercomputadores citados:** Jaguar (18688 nós hex-core e 7832 nós quad-core, conexão proprietária da Cray), Blue Gene e Earth Simulator.

---

## 10. 🛰️ Tecnologias de interconexão

Duas tecnologias aparecem em destaque para ligar nós com alto desempenho.

| Tecnologia | Modelo suportado            | Latência       | Vazão        | Interligação                         |
| ---------- | --------------------------- | -------------- | ------------ | ------------------------------------ |
| Myrinet    | Troca de mensagens (NORMA)  | cerca de 5 µs  | 1.28 Gbit/s  | Switch de alto desempenho            |
| SCI        | Mensagens e memória compartilhada (NORMA e NUMA) | cerca de 5 µs | 6.4 Gbit/s | Anel ou switch de alto desempenho |

**Detalhe do SCI:** é o Scalable Coherent Interface, padrão IEEE 1596-1992.

---

## 11. 🌐 Tópicos atuais

### `Grid Computing` (Computação em Grade)

**O que é:** uma infraestrutura que conecta recursos de forma dinâmica para executar aplicações distribuídas de larga escala com uso intenso de recursos. É uma plataforma virtual que integra redes, computação, comunicação e informação.

**Três palavras-chave:** distribuído, heterogêneo e dinâmico.

**Marco histórico:** o I-WAY (Information Wide-Area Year), primeiro grid, apresentado no Supercomputing 95. Foi um testbed com 17 sites conectados e 60 aplicações, que mostrou a diferença entre pesquisa em Sistemas Distribuídos (foco em separação geográfica) e Grid (foco em integração e gerência de software).

**Projetos que nasceram dali:** Globus e Legion (infraestrutura), Condor (exploração de ciclos ociosos), AppLeS e Prophet (escalonadores), Network Weather Service (monitoração e predição), SRB (transparência de heterogeneidade) e NetSolve e Ninf (computação remota cliente servidor).

**Exemplo real:** a grade francesa Grid5000, composta de clusters com mais de 7000 cores.

**Padronização:** o GGF (Global Grid Forum), criado no fim dos anos 90, com o objetivo de criar padrões, incluindo a OGSA que integrava Globus e Web services.

> 📌 **Referência:** comparando com hoje, o Grid é o avô conceitual da computação em nuvem. A ideia de juntar recursos distribuídos sob demanda virou o que você usa na AWS.

### `Multicores, considerações`

O material lembra que multicore não é só hardware. Ele lista pontos que afetam o desenvolvimento:

1. **Hardware:** chegou em laptops, desktops e servidores.
2. **Software:** exige atenção em compiladores, depuração e desempenho.
3. **Licenças:** modelos variam bastante, citando exemplos da época como Microsoft cobrando por processador, Oracle por core com fator de redução, e VMWare por processador com limite de cores.

### `MPSoC` (MultiProcessor System-on-Chip)

**O que é:** um sistema com múltiplos processadores integrados num único chip. Pode ser homogêneo, com unidades de processamento idênticas, ou heterogêneo, com unidades diferentes.

**Relação com multicore:** o material afirma que um multicore é um MPSoC homogêneo "pequeno".

**Exemplo:** o protótipo Intel de 80 cores, organizado em uma malha de 10 por 8.

### `Networks-on-Chip` (NoC)

**O que é:** uma forma de interconectar os núcleos dentro do chip usando ideias de rede, em vez de ligação ponto a ponto ou barramento.

> ⚖️ **Trade-off: como interligar núcleos dentro do chip**
>
> | Abordagem         | Vantagens                       | Desvantagens                          |
> | ----------------- | ------------------------------- | ------------------------------------- |
> | Ponto a ponto     | Alto paralelismo                | Baixa reusabilidade                   |
> | Barramento        | Alta reusabilidade              | Sem paralelismo e não escalável       |
> | Networks-on-Chip  | Alta reusabilidade, alto paralelismo e escalável | Maior complexidade de projeto |
>
> **Decisão:** a NoC aparece como a melhor combinação para muitos núcleos, porque junta reusabilidade, paralelismo e escalabilidade.

### `TOP500`

**O que é:** o ranking dos computadores mais rápidos do mundo, publicado duas vezes por ano (junho e novembro) desde 1993.

**Como mede:** usa o benchmark Linpack, que resolve um sistema de equações lineares.

**Onde ver:** <www.top500.org>.

---

## ✅ CHECKLIST de revisão do conteúdo

- [x] Entender por que a computação virou paralela
- [x] Diferenciar pipeline, superescalar, multithreading e multicore
- [x] Saber aplicar a Lei de Amdahl numa decisão de otimização
- [x] Decorar a tabela de Flynn (SISD, SIMD, MISD, MIMD)
- [x] Diferenciar UMA, NUMA, COMA e NORMA
- [x] Comparar topologias de rede e tipos de chaveamento
- [ ] Revisar a tabela comparativa PVP, SMP, MPP e NOW antes de prova

---

## 🔗 LINKS ÚTEIS

| Recurso                | URL                                | Tipo  |
| ---------------------- | ---------------------------------- | ----- |
| TOP500                 | <https://www.top500.org>             | Site  |
| Grid5000               | <https://www.grid5000.fr>            | Site  |

---

## 🧠 Resumo de uma frase por bloco

| Bloco                  | Em uma frase                                                                 |
| ---------------------- | ---------------------------------------------------------------------------- |
| Motivação              | Paralelismo nasceu da necessidade de desempenho e dos limites físicos do clock |
| Níveis de paralelismo  | O paralelismo existe da aplicação ao processador, em camadas                 |
| Evolução do processador| Pipeline, superescalar, multithreading e multicore foram passos para extrair mais paralelismo |
| Desempenho             | Amdahl, speedup e eficiência medem se o paralelismo valeu a pena             |
| Flynn                  | Classifica máquinas por fluxos de instruções e de dados                      |
| Memória                | UMA, NUMA, COMA e NORMA descrevem como a memória é compartilhada e organizada |
| Redes                  | Topologia e chaveamento definem custo, latência e escalabilidade             |
| Plataformas            | PVP, SMP, MPP e NOW são os arquétipos clássicos de máquina paralela          |
| Tópicos atuais         | Grid, MPSoC, NoC e TOP500 mostram para onde o paralelismo evoluiu            |
