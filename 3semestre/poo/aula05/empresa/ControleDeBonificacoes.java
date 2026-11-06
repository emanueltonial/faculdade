// ControleDeBonificacoes.java
package aula5.empresa;

public class ControleDeBonificacoes {
    private double totalDeBonificacoes = 0;

    public void registra(Funcionario funcionario) {
        this.totalDeBonificacoes += funcionario.calcularBonificacao();
    }

    public double getTotalDeBonificacoes() {
        return this.totalDeBonificacoes;
    }

    public void setTotalDeBonificacoes(double totalDeBonificacoes) {
        this.totalDeBonificacoes = totalDeBonificacoes;
    }
}