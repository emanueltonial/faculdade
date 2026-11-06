
package tabelateste;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;

public class TabelaTeste {

    public static void main(String[] args) {
        geraDB();
    }
    
    public static void geraDB() {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/aula03";
        
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, "aluno", "aluno123");
            
            Statement st = connection.createStatement();
            
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");
            connection.close(); 
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e.getMessage());
        }
    }
    
   public static void criarTabela() {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/aula03";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, "aluno", "aluno123");

            Statement st = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS funcionario "
                    + "(id int primary key, "
                    + "nome varchar(50), "
                    + "endereco varchar(100))";
            st.executeUpdate(sql);

            String sql2 = "INSERT INTO funcionario VALUES (1, 'Pedro Silva', 'Rua das Dores, 85')";
            st.executeUpdate(sql2);

            JOptionPane.showMessageDialog(null, "Tabela criada e registro inserido com sucesso");
            connection.close();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e.getMessage());
        }
    }
    
}