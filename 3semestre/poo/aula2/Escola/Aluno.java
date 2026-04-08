package aula2.Escola;

public class Aluno {

    private String nome;
    private int matricula;
    private String curso;
    private String[] disciplinas;
    private double[][] notas;

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(String[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public double[][] getNotas() {
        return notas;
    }

    public void setNotas(double[][] notas) {
        this.notas = notas;
    }

    public double calcularMedia(int indiceDisciplina) {
        double soma = 0.0;

        for (int i = 0; i < 4; i++) {
            soma = soma + notas[indiceDisciplina][i];
        }

        double media = soma / 4.0;
        return media;
    }

    public boolean verificarAprovacao(int indiceDisciplina) {
        double media = calcularMedia(indiceDisciplina);

        if (media >= 7.0) {
            return true;
        } else {
            return false;
        }
    }
}