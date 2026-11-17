import java.sql.*;

public class BD {

    // configuracao centralizada: Database.java tambem usa essas constantes
    final static int PORTA = 3306;
    final static String NOME_DB = "empresa";
    final static String USUARIO = "aluno";
    final static String SENHA = "aluno123";
    final static String PARAMS = "?allowPublicKeyRetrieval=true&useSSL=false";

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // URL do servidor, sem banco (usada para o CREATE DATABASE)
    public static final String URL_SERVIDOR = "jdbc:mysql://localhost:" + PORTA + "/" + PARAMS;
    // URL do banco da aplicacao
    public static String URL = "jdbc:mysql://localhost:" + PORTA + "/" + NOME_DB + PARAMS;

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;

    // statement do ultimo setResultSet com parametros (fechado no proximo)
    private static PreparedStatement preparedAtual = null;

    public static String getUrl() { return URL; }

    // abre uma conexao avulsa (quem chamar fecha)
    public static Connection abrir(String url) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(url, USUARIO, SENHA);
    }

    // conecta no banco; se ja estiver conectado, reaproveita a conexao
    // retorna true se houver sucesso, ou false em caso negativo
    public static boolean getConnection() {
        try {
            if (connection != null && !connection.isClosed())
                return true;

            connection = abrir(URL);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            System.out.println("Conectou");
            return true;
        } catch (ClassNotFoundException | SQLException erro) {
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
                connection = null;
                System.out.println("Desconectou");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    private static void closeStatement() {
        try {
            if (preparedAtual != null) { preparedAtual.close(); preparedAtual = null; }
            if (statement != null) { statement.close(); statement = null; }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeResultSet() {
        try {
            if (resultSet != null) { resultSet.close(); resultSet = null; }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // carrega o resultSet com o resultado do script SQL
    public static void setResultSet(String sql) {
        try {
            fecharPreparedAtual();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    // versao com parametros (?), segura contra SQL injection e apostrofos
    // ex: BD.setResultSet("SELECT * FROM produto WHERE id = ?", 10);
    public static void setResultSet(String sql, Object... params) {
        try {
            fecharPreparedAtual();
            preparedAtual = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preencher(preparedAtual, params);
            resultSet = preparedAtual.executeQuery();
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    // executa um script SQL de atualizacao
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

    // versao com parametros (?)
    // ex: BD.runSQL("DELETE FROM produto WHERE id = ?", 10);
    public static int runSQL(String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            preencher(ps, params);
            return ps.executeUpdate();
        } catch (SQLException erro) {
            erro.printStackTrace();
            return 0;
        }
    }

    private static void preencher(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++)
            ps.setObject(i + 1, params[i]);
    }

    private static void fecharPreparedAtual() throws SQLException {
        if (preparedAtual != null) {
            preparedAtual.close(); // fecha junto o resultSet dele
            preparedAtual = null;
        }
    }
}
