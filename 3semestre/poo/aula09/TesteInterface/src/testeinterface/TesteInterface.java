/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testeinterface;

/**
 *
 * @author emanuel_tonial
 */
public class TesteInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pessoa p = new Pessoa(4500);
        SeguroDeVida s = new SeguroDeVida(36.00);
        ContaCorrente c = new ContaCorrente(500.00);
        GerenciadorDeImpostoDeRenda g = new GerenciadorDeImpostoDeRenda();
        
        g.adicionarTributos(p);
        g.adicionarTributos(s);
        g.adicionarTributos(c);
        System.out.println("Total de tributos: " + g.adicionarTributos());
        System.out.println("Total de tributos: " + p.calculaTributos());
        System.out.println("Total de tributos: " + c.saldo());
        System.out.println("Total de tributos: " + s.calculaTributos());
        
    }
    
}
