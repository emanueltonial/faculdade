package aula1;

public class CupomSupermercado {

  private static void imprimirLinhaItem(Item item) {
    System.out.printf("%-6s  %-15s  %3d  %7.2f%%  R$ %8.2f  R$ %8.2f  R$ %8.2f\n",
        item.getCodigo(),
        item.getDescricao(),
        item.getQuantidade(),
        item.getDesconto(),
        item.calcularValorDesconto(),
        item.getValorUnitario(),
        item.calcularValor());
  }

  private static void imprimirCupom(String titulo, Item[] itens) {
    System.out.println("------------------------------------------------------------------------------");
    System.out.printf("-------------------------------- %-12s --------------------------------\n", titulo);
    System.out.println("------------------------------------------------------------------------------");
    System.out.println("Código  Descrição         Qtd  Desconto V. Desconto  V. Unitário     V. Total");

    double valorTotal = 0;
    for (Item item : itens) {
      imprimirLinhaItem(item);
      valorTotal += item.calcularValor();
    }

    System.out.println("------------------------------------------------------------------------------");
    System.out.printf("%65s R$ %8.2f\n", "Valor Total:", valorTotal);
    System.out.println("------------------------------------------------------------------------------");
  }

  public static void main(String[] args) {

    Item item1 = new Item("001", "Arroz",    2, 0.5, 10.0);
    Item item2 = new Item("002", "Feijão",   1, 0.0,  8.0);
    Item item3 = new Item("003", "Macarrão", 3, 1.0,  5.0);

    Item[] itens = { item1, item2, item3 };

    imprimirCupom("CUPOM FISCAL", itens);

    item1.setQuantidade(5);
    item1.setDesconto(10.0);
    item2.setValorUnitario(9.5);

    imprimirCupom("CUPOM ATUALIZADO", itens);
  }

}