# Relatório: Rolador de Dados

App em React (Vite) que rola dados configuráveis, destaca resultados críticos, compara com um valor alvo e guarda os últimos 10 lançamentos num carrossel.

> [!info] Stack
> React 19 + Vite, JavaScript puro (sem TypeScript). Mesma base do projeto `estado-botao`: componentes em `minúsculo.jsx`, prop `funcao`, `Botao` com objeto-mapa por `type`, estilos no `styles.css`.

## Como rodar

> [!tip] Passo a passo
> 1. `npm install`
> 2. `npm run dev`
> 3. Abre o endereço que o Vite mostrar (geralmente `http://localhost:5173`)

## Estrutura de arquivos

São 8 arquivos. Mantive componente só onde tem reuso real ou item de lista, o resto ficou no `App` pra não abstrair além do necessário.

| Arquivo | O que faz |
|---|---|
| `src/index.jsx` | Ponto de entrada, monta o `App` |
| `src/App.jsx` | Estados, validação, função de lançamento e o layout (config, resultado e histórico) |
| `src/styles.css` | Todo o estilo, com variáveis de cor no `:root` |
| `src/utils.js` | Funções puras: `sortearDado`, `criarLancamento`, `notacaoLancamento`, `validarNumero` |
| `src/components/botao.jsx` | Botão reutilizável (usado nos `+`/`−`, no lançar e nas setas) |
| `src/components/seletorNumero.jsx` | Campo numérico com input + botões, reusado 3x |
| `src/components/dadoResultado.jsx` | Um dado do resultado, com pips no D6 e destaque MIN/MAX |
| `src/components/cartaHistorico.jsx` | Uma carta do carrossel de histórico |

## Requisitos funcionais

| # | Requisito | Onde está |
|---|---|---|
| RF1 | Selecionar quantidade de dados | `SeletorNumero` (Quantidade) |
| RF2 | Selecionar tipo/faces | `select` + atalhos no `App`, opções em `TIPOS_DE_DADO` |
| RF3 | Modificador positivo ou negativo | `SeletorNumero` (Modificador) |
| RF4 | Selecionar valor alvo | `SeletorNumero` (Valor alvo) |
| RF5 | Executar o lançamento | botão "Lançar dados" chama `lancarDados()` |
| RF6 | Resultado de cada dado | `dados.map()` renderiza um `DadoResultado` por dado |
| RF7 | Destacar críticos (máx/mín) | `DadoResultado` marca `valor === 1` (MIN) e `valor === faces` (MAX) |
| RF8 | Resultado final (soma + modificador) | `criarLancamento` calcula `somaBase + modificador` |
| RF9 | Alvo atingido (final ≥ alvo) | `atingiuAlvo` vira o banner verde/vermelho |
| RF10 | Últimos 10 + navegar | `slice(0, 10)` no histórico + carrossel com setas |

## Requisitos não funcionais

| Requisito | Como foi atendido |
|---|---|
| Digitar ou usar botões | Campos numéricos têm input + `+`/`−`; o tipo tem `select` + botões de atalho |
| Clareza e legibilidade | Layout em cartões, cores com significado (verde = sucesso, vermelho = erro) |
| Reutilização de componentes | `Botao` e `SeletorNumero` reusados; funções puras isoladas no `utils.js` |
| Histórico tipo carrossel | Painel grande mostra o lançamento em foco; ao lançar, foco vai pro novo (`indiceAtivo = 0`) |
| Validação dos campos | `validarNumero` barra vazio, texto, decimal e negativo; campo inválido fica vermelho com mensagem e o botão desabilita |

## Conceitos da atividade

| Conceito | Onde aparece |
|---|---|
| Componentização | `Botao`, `SeletorNumero`, `DadoResultado`, `CartaHistorico` |
| Passagem de propriedades | ex: `<SeletorNumero titulo valor funcao invalido textoErro />` |
| Controle de estados | `useState` para quantidade, faces, modificador, alvo, histórico e índice |
| Conversão de tipos | campos guardados como texto e convertidos com `Number(...)` no lançamento |
| Estruturação de dados | objeto do lançamento montado em `criarLancamento` |
| Renderização de listas | `TIPOS_DE_DADO.map`, `dados.map`, `historico.map` (todos com `key`) |

## Decisões de projeto

> [!note] Campos guardados como texto
> `quantidade`, `modificador` e `alvo` ficam como string no estado. Isso deixa validar exatamente o que foi digitado (inclusive detectar decimal e texto) e só converter pra número na hora de lançar.

> [!note] Histórico com o mais recente primeiro
> Ao lançar, o novo entra no começo da lista e o foco vai pra ele (índice 0). Assim ele já aparece à esquerda, sem precisar de scroll automático nem de efeitos.

> [!success] Bug corrigido no destaque MIN
> O crítico de mínimo aplicava cor e fundo vermelhos na mesma regra. Em dados com pips (D6) funcionava, mas num D20 tirando 1 o número ficava vermelho sobre fundo vermelho, ou seja, invisível. Separei em duas regras: o número só troca a cor, a bolinha troca o fundo.

> [!tip] Nível de abstração
> Comecei com mais componentes e reduzi. Cabeçalho, seletor de tipo, painel de resultado e histórico eram usados uma vez só, então voltaram pro `App`. Ficaram como componente só os que têm reuso (`Botao`, `SeletorNumero`) ou são item de lista (`DadoResultado`, `CartaHistorico`).
