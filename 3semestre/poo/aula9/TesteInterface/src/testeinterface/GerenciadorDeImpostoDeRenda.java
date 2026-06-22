/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeinterface;

/**
 *
 * @author emanuel_tonial
 */
public class GerenciadorDeImpostoDeRenda {
    public double totalTributos = 0.0;
    
    
    public void adicionarTributos(Tributavel t) {
        totalTributos += t.calculaTributos();
    }
    
    
}