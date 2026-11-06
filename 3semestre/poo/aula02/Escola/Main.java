package aula2.Escola;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String continuar = "SIM";

        while (continuar.equalsIgnoreCase("SIM")) {

            Aluno aluno = new Aluno();

            String nome;
            int matricula;
            String curso;

            String[] disciplinas = new String[3];
            double[][] notas = new double[3][4];

            System.out.print("Digite o nome do aluno: ");
            nome = s.nextLine();
            aluno.setNome(nome);

            System.out.print("Digite a matrícula do aluno: ");
            matricula = s.nextInt();
            s.nextLine();
            aluno.setMatricula(matricula);

            System.out.print("Digite o curso do aluno: ");
            curso = s.nextLine();
            aluno.setCurso(curso);
            
            // Adiciona disciplinas do Aluno
            for (int i = 0; i < 3; i++) {
                System.out.print("Digite o nome da disciplina " + (i + 1) + ": ");
                disciplinas[i] = s.nextLine();
                
                // Adiciona notas de cada disciplina
                for (int j = 0; j < 4; j++) {
                    System.out.print("Digite a nota " + (j + 1) + " da disciplina " + disciplinas[i] + ": ");
                    notas[i][j] = s.nextDouble();
                }

                s.nextLine();
            }

            aluno.setDisciplinas(disciplinas);
            aluno.setNotas(notas);
            
            // Printa dados do aluno
            // e itera sobre as disciplinas e notas
            System.out.println();
            System.out.println("DADOS DO ALUNO");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println();
            
            // Itera sobre cada disciplina do aluno
            for (int i = 0; i < 3; i++) {
                System.out.println("Disciplina: " + aluno.getDisciplinas()[i]);
                
                // Itera sobre as notas de cada disciplina
                System.out.print("Notas: ");
                for (int j = 0; j < 4; j++) {
                    System.out.print(aluno.getNotas()[i][j] + " ");
                }
                System.out.println();
                // Calcula a media de uma disciplina
                double media = aluno.calcularMedia(i);
                System.out.println("Média: " + media);
                // Verifica aprovacao para uma disciplina
                if (aluno.verificarAprovacao(i)) {
                    System.out.println("Situação: Aprovado");
                } else {
                    System.out.println("Situação: Reprovado");
                }

                System.out.println();
            }
            
            System.out.print("Você quer inserir outro aluno? (SIM/NAO): ");
            continuar = s.nextLine();
            System.out.println();
        }

        s.close();
    }
}