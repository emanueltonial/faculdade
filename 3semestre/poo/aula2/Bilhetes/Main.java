package aula2.Bilhetes;
public class Main {
    public static void main(String[] args){
        MaquinaBilhetes maquina = new MaquinaBilhetes(12.50f);

        System.out.println("Preço do bilhete: R$ " + maquina.getPreco());

        maquina.inserirDinheiro(12.50f);
        maquina.inserirDinheiro(12.50f);

        
        System.out.println("Saldo após inserções: R$ " + maquina.getSaldo());
        System.out.println("Total arrecadado: R$ " + maquina.getTotal());

        maquina.imprimirBilhete();

        System.out.println("Saldo após bilhete: R$ " + maquina.getSaldo());
        System.out.println("Total arrecadado: R$ " + maquina.getTotal());

        maquina.imprimirBilhete();
        System.out.println("Saldo após segunda impressão: R$ " + maquina.getSaldo());

        MaquinaBilhetes maquina2 = new MaquinaBilhetes(5.00f);
        maquina2.inserirDinheiro(5.00f);
        maquina2.imprimirBilhete();
    }
}