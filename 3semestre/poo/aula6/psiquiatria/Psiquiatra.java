package aula6.psiquiatria;

public class Psiquiatra {
    public void examinar(Humor h) {
        System.out.print("\nFale-me, objeto, como você se sente hoje?\n");
        h.printarHumor();
        System.out.println();
    }

    // Tipos de polimorfismo
    // 1. Override (Sobreescrita): Dinâmico em tempo de execução
    // 2. Overload (Sobreecarga): Estatico em tempo de compilação
    // tem o mesmo nome, mas assinaturas diferentes dentro da mesma classe.
    public void observar(Triste t) {
        t.chorar();

        System.out.println("\nHum.. Que bom que você está botando pra fora!");
    }

        public void observar(Feliz f) {
        f.rir();

        System.out.println("Vocẽ é estranho em!");
    }
}
