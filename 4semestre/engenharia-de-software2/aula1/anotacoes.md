# Anotações

## Arquitetura de Software

O objetivo da arquitetura não é facilitar a criação do software, e sim a manutenabilidade dele. Programar é so uma pequena parte da vida útil de um sistema. Após a criação, vem:

- correções;
- melhorias;
- adaptações;
- novas funcionalidades.

## UML - Unified Modeling Language

Ajuda a visualizar o sistema antes da implementação.
Permite representar:

- casos de uso;
- classes;
- objetos;
- sequência;
- atividades;
- estados.

## Por que construir diagramas?

Para diminuir os erros na hora de programar um sistema.

## Abstração

Mecanismo utilizado na análise de um domínio. Por meio dela, o indivíduo observa a realidade e dela abstrai entidades, ações, etc, consideradas esssenciais para uma apliacação.

## Operações de Abstração relevantes na Modelagem Conceitual

### Classificação (Instanciação)

- Categoriazação dos objetos e/ou classes, com base em um conteudo de características em comum.

### Generalização (Especialização)

- a partir de duas categorias abstrai-se uma categoria mais
genérica;
- sub-categorias satisfazem todas as propriedades das
categorias de que elas constituem especializações;
- deve existir pelo menos uma propriedade que distingue
duas categorias especializadas.

### Revisão & Prática

#### lápis

atributos:

- cor_da_pintura_externa: str;
- comprimento: float;
- circunferência: float;
- tipo_do_grafite: str;
- durabilidade: float;
- tipo_da_madeira: str;
- marca: str;
- alta_qualidade: bool;
- data_de_fabricacao: str;
- peso: float.

métodos:

- escrever();
- rabiscar();
- apontar();
- desenhar();
- sublinhar();
- quebrar();
- borrar();
- medir();
- decorar();
- guardar().

##### caneta

atributos:

- cor_da_pintura_externa: str;
- comprimento: float;
- circunferência: float;
- tipo_do_grafite: str;
- durabilidade: float;
- marca: str;
- alta_qualidade: bool;
- data_de_fabricacao: str;
- cor: str;
- peso: float.

métodos:

- escrever();
- rabiscar();
- trocar_a_tinta();
- desenhar();
- sublinhar();
- tampar();
- destampar();
- clicar();
- vazar();
- guardar().

#### tubarão

atributos:

- altura: float;
- largura: float;
- comprimento: float;
- peso: float;
- espécie: str;
- cor: str;
- habitat: str;
- velocidade_máxima: float;
- expectativa_de_vida: float;
- tipo_de_dentes: str.

métodos:

- movimentar();
- comer();
- atacar();
- caçar();
- detectar_presa();
- migrar();
- reproduzir();
- nadar();
- mergulhar();
- descansar().

#### caminhão

atributos:

- altura: float;
- largura: float;
- comprimento: float;
- peso: float;
- tipo_de_roda: str;
- diametro_pneu: float;
- material_pneu: str;
- altura_roda: float;
- capacidade_de_carga: float;
- tipo_de_combustível: str.

métodos:

- acelerar(intensidade: float);
- freiar(intensidade: float);
- trocar_marcha(marcha_destino: str);
- ligar();
- desligar();
- carregar(carga: float);
- descarregar();
- buzinar();
- ré();
- estacionar().

#### satélite

atributos:

- altura: float;
- largura: float;
- comprimento: float;
- peso: float;
- fabricante: str;
- material: str;
- tipo_de_órbita: str;
- velocidade_orbital: float;
- fonte_de_energia: str;
- vida_útil: float.

métodos:

- transmitir_dados();
- receber_comandos(comando: str);
- orbitar();
- captar_energia_solar();
- ativar();
- desativar();
- ajustar_trajetória();
- capturar_imagem();
- entrar_em_modo_seguro();
- reiniciar().

### Diagramas de caso de Uso

Diagrama que representa os requisitos do sistema.
