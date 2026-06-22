/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeinterface;

/**
 *
 * @author emanuel_tonial
 */
public class Pessoa implements Tributavel {
    private double saldo;
    
    public Pessoa(double saldo) {
        this.saldo = saldo;
    }
    
    @Override
    public double calculaTributos() {
        return this.saldo * 11/100;
    }
}
 

