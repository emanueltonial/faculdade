package tratamentodeexcecao2;

import java.util.Scanner;

public class TratamentoDeExcecao2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            converterFrase(sc);
        } catch(NullPointerException e) {
            System.out.println("String nula:" + e);
        }
        
    }
    
    public static void converterFrase(Scanner sc) throws NullPointerException   {
        String frase = null;
        String novaFrase = null;
        
        novaFrase = frase.toUpperCase();
            
        frase = "Frase vazia";
        novaFrase = frase.toUpperCase();
        
        System.out.println("Frase antiga: " + frase);
        System.out.println("Frase nova: " + novaFrase);
    }
    
        /*
        public static void converterFrase(Scanner sc)   {
        System.out.println("Digite uma frase: ");
        
        String frase = sc.nextLine();
        String novaFrase = null;
        
        try
        {
            novaFrase = frase.toUpperCase();
        } catch(NullPointerException e) {
            System.out.println("Nao eh possivel converter uma string nula.");
        frase = "Frase vazia";
        novaFrase = frase.toUpperCase();
        }
        
        System.out.println("Frase antiga: " + frase);
        System.out.println("Frase nova: " + novaFrase);
    }
    */
} 
