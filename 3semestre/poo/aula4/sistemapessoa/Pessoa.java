package aula4.sistemapessoa;

public class Pessoa {
    protected String nomePessoa;
    protected String endereco;
    protected String telefone;

    public Pessoa(
        String nomePessoa, 
        String endereco,
        String telefone
    ) {
        this.nomePessoa = nomePessoa;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters
    public String getNomePessoa() {
        return nomePessoa;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    // Setters
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void printarInformacoes() {
        System.out.println("Nome: " + this.nomePessoa);
        System.out.println("Endereço: " + this.endereco);
        System.out.println("Telefone: " + this.telefone);
    }
}