package aula6.psiquiatria;

public class Humor {
    // Polimorfismo para retornar o humor

    protected String getHumor() {
        return "Mal-humorado";
    }

    // printa como o objeto se sente
    public void printarHumor() {
        System.out.println("Eu me sinto " + getHumor() + " hoje! ");
    }
}
