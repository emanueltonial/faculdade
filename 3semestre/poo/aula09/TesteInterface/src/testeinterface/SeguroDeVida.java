/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeinterface;

/**
 *
 * @author emanuel_tonial
 */
public class SeguroDeVida implements Tributavel{
    private double imposto;
    
    public SeguroDeVida(double imposto) {
        this.imposto = imposto;
        
    }
    @Override
    public double calculaTributos() {
        return this.imposto;
    }
    
}
