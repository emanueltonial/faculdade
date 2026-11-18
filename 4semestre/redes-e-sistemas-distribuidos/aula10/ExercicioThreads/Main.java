import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        // quantidade minima de threads eh 2, max eh 5
        int quantidadeThreads = random.nextInt(4) + 2;
        System.out.println("Quantidade de threads sorteada: " + quantidadeThreads);

        for (int i = 1; i <= quantidadeThreads; i++) {
            Thread t = new Thread(new Tarefa("Thread " + i));
            t.start();
        }

        System.out.println("Criação de threads finalizada!");
    }
}
