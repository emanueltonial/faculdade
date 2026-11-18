public class ComSincronizacao {
    public static int contador = 0;
    public static final int numeroSomas = 1000;

    public static synchronized void incrementar() {
        contador++;
    }

    static class Somador extends Thread {

        public void run() {
            for (int i = 0; i < numeroSomas; i++)
                incrementar();
        }
    }

    public static void main(String[] args) {
        Somador thread1 = new Somador();
        Somador thread2 = new Somador();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException");
        }

        System.out.println("Valor do contador apos as somas: " + contador);
    }
}
