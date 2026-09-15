# Exercicio Segmentação de Redes

Um administrador de redes vai organizar a estrutura dos laboratórios e setores da instituição em 6 sub-redes diferentes. Irá utilizar o bloco privado 10.0.0.0/8 sub-dividido com uma máscara de sub-rede /20 (20 bits para a rede). As demais sub-redes (que são muito mais do que 6, não serão utilizadas no momento). Para as 6 primeiras sub-redes, demonstre e apresente:

- O número IP da rede
- O número IP de broadcast (difusão)
- A máscara de rede (em notação decimal pontuada)
- A faixa de endereços IP válidos (disponíveis para hosts)

Entrega: pode ser enviado um texto em PDF ou diretamente na tarefa.

obs.: Esse exercício terá valor de nota e valerá presenças para esta data

---

## Sub-redes /20 no bloco 10.0.0.0/8

### Informações

Bloco original: `10.0.0.0/8` (24 bits de host)
Nova máscara: `/20` (12 bits de host)
Bits emprestados para as subnets -> 20 - 8 (subnet - original)

### Calculos realizados

Hosts válidos por sub-rede: 2^{12} - 2 = 4094
Salto entre sub-redes no 3º octeto: 2^4 = 16 (porque sobram 4 bits do 3º octeto dentro dos 20 bits de rede)
Máscara /20 em decimal: `255.255.240.0`

## Tabela das 6 primeiras sub-redes

| # | IP de rede | IP de broadcast | Máscara | Faixa de hosts válidos |
| --- | --- | --- | --- | --- |
| 1 | 10.0.0.0 | 10.0.15.255 | 255.255.240.0 | 10.0.0.1 – 10.0.15.254 |
| 2 | 10.0.16.0 | 10.0.31.255 | 255.255.240.0 | 10.0.16.1 – 10.0.31.254 |
| 3 | 10.0.32.0 | 10.0.47.255 | 255.255.240.0 | 10.0.32.1 – 10.0.47.254 |
| 4 | 10.0.48.0 | 10.0.63.255 | 255.255.240.0 | 10.0.48.1 – 10.0.63.254 |
| 5 | 10.0.64.0 | 10.0.79.255 | 255.255.240.0 | 10.0.64.1 – 10.0.79.254 |
| 6 | 10.0.80.0 | 10.0.95.255 | 255.255.240.0 | 10.0.80.1 – 10.0.95.254 |
