# Gabarito — Atividade de Fixação (Semana 01)

## Jogos Digitais I: o que é um jogo, evolução e fundamentos de game design

---

## Parte A — Múltipla Escolha

**Q1.** Resposta: **b) Regras, objetivo e participação voluntária do jogador**
Tanto o xadrez quanto o Fortnite têm regras que estruturam o que é possível, um objetivo claro (dar xeque-mate / ser o último de pé) e dependem da escolha voluntária do jogador em participar.

**Q2.** Resposta: **c) No digital, o computador executa as regras e mantém o estado do jogo**
Num jogo de tabuleiro os jogadores aplicam as regras "manualmente"; num jogo digital é o sistema que calcula, valida e guarda o estado a cada momento.

**Q3.** Resposta: **c) Mobile, jogos independentes e engines acessíveis permitiram que qualquer pessoa criasse e publicasse jogos**
A "democratização" se refere à queda das barreiras técnicas e financeiras para desenvolver e distribuir jogos.

**Q4.** Resposta: **b) O que o jogador FAZ dentro do jogo — o verbo da interação**
Mecânica é a ação concreta (pular, atirar, combinar peças), não a narrativa nem o hardware.

**Q5.** Resposta: **c) Mecânicas são as ações do jogador; regras definem o que pode e não pode acontecer no sistema**
Mecânica é o verbo; regra é a restrição/lei que governa esse verbo.

**Q6.** Resposta: **c) Perder sentindo que "quase conseguiu", motivando a nova tentativa**
Esse é o princípio central da calibragem de desafio: a derrota deve parecer superável, não injusta ou impossível.

**Q7.** Resposta: **b) Ansiedade**
No diagrama de Flow (Csikszentmihalyi): desafio alto + habilidade baixa = ansiedade; desafio baixo + habilidade alta = tédio; os dois equilibrados e altos = flow.

**Q8.** Resposta: **b) A satisfação que nasce da própria atividade: dominar, descobrir, superar**
Recompensa intrínseca vem de dentro da experiência; pontos, itens e prêmios são recompensas *extrínsecas*.

**Q9.** Resposta: **b) Ação → feedback → recompensa → nova meta → ação**
É o ciclo que mantém o jogador engajado, retroalimentando o interesse a cada volta.

**Q10.** Resposta: **c) O comportamento que EMERGE quando pessoas reais jogam**
No MDA: Mecânicas = o que é programado; Dinâmicas = o comportamento emergente do uso das mecânicas; Estéticas = a emoção que o jogador sente.

---

## Parte B — Verdadeiro ou Falso (Q11)

| Item | Resposta | Justificativa |

|---|---|---|
| a | **F** | Sem interação/participação do jogador não há jogo — apenas um sistema passivo (ex: uma simulação rodando sozinha). |
| b | **V** | São as regras que impõem restrições e, com isso, criam o desafio; sem elas há apenas atividade livre (brincadeira sem estrutura). |
| c | **F** | A curva ideal não é uma rampa constante — bons jogos alternam picos e vales (tensão e alívio), não um crescimento linear único. |
| d | **F** | Recompensas puramente extrínsecas tendem a gerar engajamento superficial e de curta duração; motivação duradoura depende também do intrínseco. |
| e | **V** | Dinâmicas não são codificadas diretamente — surgem como consequência do jogo real sendo jogado por pessoas. |

---

## Parte C — Associação de Colunas (Q12)

| Definição (Coluna 2) | Conceito (Coluna 1) |

|---|---|
| A resposta imediata do sistema a cada ação do jogador — visual, sonora ou tátil | **(3) Feedback** |
| O estado de imersão total, quando desafio e habilidade se equilibram | **(4) Flow** |
| A meta que dá direção às ações do jogador | **(1) Objetivo** |
| O comportamento que emerge quando pessoas reais jogam — sem estar programado | **(5) Dinâmica (MDA)** |
| O que o jogador FAZ dentro do jogo — o verbo da interação | **(2) Mecânica** |

---

## Parte D — Questões Discursivas (respostas-modelo)

> Estas são respostas de referência — vale adaptar com suas próprias palavras e exemplos antes de entregar, como pede o enunciado.

**Q13. Mecânicas x Regras (exemplo: Xadrez)**
Mecânicas são as ações que o jogador realiza — no xadrez: *mover uma peça* e *capturar uma peça adversária*. Regras são as restrições que definem o que é permitido dentro dessas ações — por exemplo, *o bispo só pode se mover na diagonal* e *o rei nunca pode ficar em xeque*. A mecânica é o verbo; a regra é o limite que dá forma a esse verbo.

**Q14. Ciclo de jogo (exemplo: pulo do Mario)**

- **Ação:** o jogador pressiona o botão de pulo.
- **Regra aplicada:** o sistema consulta as regras de física do jogo (gravidade, força do impulso, se Mario está no chão) para calcular a trajetória.
- **Estado alterado:** a posição de Mario no mundo muda; se ele colide com um inimigo ou bloco, o estado desse elemento também muda (inimigo derrotado, bloco quebrado, moeda liberada).
- **Feedback:** o jogo exibe a animação do salto, toca o som característico e mostra os efeitos da colisão, sinalizando ao jogador o resultado da ação.

**Q15. Análise MDA (exemplo: Duolingo)**

- **Mecânicas:** sistema de XP, sequência de dias consecutivos (streak), lições com exercícios de múltipla escolha e tradução, vidas/corações limitados.
- **Dinâmicas:** o usuário cria o hábito de abrir o app todo dia para não perder a streak, e passa a competir informalmente com amigos nas ligas de ranking.
- **Estéticas:** sensação de progresso a cada lição concluída, leve competição social nas ligas, e por vezes frustração quando a sequência é quebrada.
