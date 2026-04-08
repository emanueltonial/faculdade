package aula6.psiquiatria;

public class Triste extends Humor{

    @Override
    protected String getHumor() {
        return "triste";
    }

    public void chorar() {
        System.out.print("BUAAAAAAAAAA!");
    }

}
