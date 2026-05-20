/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeinterface;

/**
 *
 * @author emanuel_tonial
 */
public class ContaCorrente extends Conta implements Tributavel{
    
    public ContaCorrente() {
        super(saldo);
    }
    
    public double calculaTributos() {
        return this.saldo * 1.5/100;
    }
}
