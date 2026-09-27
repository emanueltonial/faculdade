import java.sql.*;

public class BD {

    final static int PORTA = 3306;
    final static String NOME_DB = "empresa";
    final static String USUARIO = "aluno";
    final static String SENHA = "aluno123";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;
    public static String URL = "jdbc:mysql://localhost:" + PORTA + "/" + NOME_DB
            + "?allowPublicKeyRetrieval=true&useSSL=false";

    // método que faz conexão com o banco de dados
    // retorna true se houver sucesso, ou false em caso negativo

    public static boolean getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println("Conectou");
            return true;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return false;
        }
    }

    // fecha ResultSet, Statement e Connection

    public static void close() {
        closeResultSet();
        closeStatement();
        closeConnection();
    }

    private static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Desconectou");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    private static void closeStatement() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeResultSet() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // carrega o resultSet com o resultado do script SQL

    public static void setResultSet(String sql) {
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    // executa um script SQL de atualização
    // retorna um valor inteiro contendo a quantidade de linhas afetadas

    public static int runSQL(String sql) {
        int quant = 0;
        try {
            quant = statement.executeUpdate(sql);
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return quant;
    }

    // executa um script SQL (ex: DDL como CREATE TABLE)
    // retorna true se executou sem erro, false caso contrário
    // (runSQL retorna 0 tanto em sucesso de DDL quanto em erro, então não serve pra isso)

    public static boolean executar(String sql) {
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return false;
        }
    }
}