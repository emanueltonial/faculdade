import java.util.Random;

public class Tarefa implements Runnable {

    private String nome;
    private static Random gerador = new Random();

    public Tarefa(String nome) {
        this.nome = nome;
    }

    public void run() {
        for (int mensagens = 1; mensagens <= 25; mensagens++) {
            System.out.println(nome + " mensagem bacana tipo " + mensagens);
            try {
                Thread.sleep(gerador.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
