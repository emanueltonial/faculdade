import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database
{
    final static int PORTA = 3306;
    final static String NOME_DB = "empresa";

    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String PARAMS = "?allowPublicKeyRetrieval=true&useSSL=false";

    // root so e usado pra criar o banco, o resto usa o aluno
    final static String USUARIO_ROOT = "root";
    final static String SENHA_ROOT = "root123";
    final static String USUARIO = "aluno";
    final static String SENHA = "aluno123";

    public void geraBD()
    {
        final String URL = "jdbc:mysql://localhost:" + PORTA + "/" + PARAMS;

        try
        {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USUARIO_ROOT, SENHA_ROOT);

            Statement st = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS " + NOME_DB;
            st.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "BD criado com sucesso");

            st.close();
            conn.close();
        }
        catch (ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Driver JDBC nao encontrado!");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Problemas na conexao com a fonte de dados: " + ex.getMessage());
        }
    }

    public void criarTabela()
    {
        final String URL = "jdbc:mysql://localhost:" + PORTA + "/" + NOME_DB + PARAMS;

        try
        {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);

            Statement st = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS produto ("
                       + "id int primary key,"
                       + "descricao varchar(50),"
                       + "categoria varchar(30),"
                       + "marca varchar(30),"
                       + "preco_custo double,"
                       + "preco_venda double,"
                       + "fornecedor varchar(30),"
                       + "tipo varchar(20),"
                       + "quantidade_estoque int,"
                       + "estoque_minimo int,"
                       + "situacao varchar(10))";

            System.out.println("Executando:\n" + sql);
            st.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Tabela criada com sucesso");

            st.close();
            conn.close();
        }
        catch (ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Driver JDBC nao encontrado!");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Problemas na conexao com a fonte de dados: " + ex.getMessage());
        }
    }

    public void consultaBD()
    {
        final String URL = "jdbc:mysql://localhost:" + PORTA + "/" + NOME_DB + PARAMS;

        try
        {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);

            // consulta aq

            conn.close();
        }
        catch (ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Driver JDBC nao encontrado!");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Problemas na conexao com a fonte de dados: " + ex.getMessage());
        }
    }

}