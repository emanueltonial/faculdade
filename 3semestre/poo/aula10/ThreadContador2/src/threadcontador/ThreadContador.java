/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threadcontador;

/**
 *
 * @author emanuel_tonial
 */
public class ThreadContador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Programa p1 = new Programa();
        p1.setId(1);
        
        Thread t1 = new Thread(p1);
        t1.start();
        
        Programa p2 = new Programa();
        p1.setId(2);
        
        Thread t2 = new Thread(p2);
        t2.start();
    }
    
}
