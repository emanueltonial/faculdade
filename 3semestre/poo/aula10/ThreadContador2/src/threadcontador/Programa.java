/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadcontador;

/**
 *
 * @author emanuel_tonial
 */
public class Programa implements Runnable {
    private int id;
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("Programa" + id + "iteracao: " + i);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
