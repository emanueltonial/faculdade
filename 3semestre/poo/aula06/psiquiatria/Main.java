package aula6.psiquiatria;

public class Main {
    public static void main(String[] args) {
        Humor h1 = new Humor();
        Feliz f1 = new Feliz();
        Triste t1 = new Triste();
        Psiquiatra p1 = new Psiquiatra();

        perguntaBoba();
        h1.printarHumor();
        
        perguntaBoba();
        f1.printarHumor();
        
        perguntaBoba();
        t1.printarHumor();
        
        p1.examinar(h1);
        p1.examinar(f1);
        p1.examinar(t1);

        p1.observar(f1);
        p1.observar(t1);
    }

    private static void perguntaBoba() {
        System.out.println("Como você se sente?");
    }
}
