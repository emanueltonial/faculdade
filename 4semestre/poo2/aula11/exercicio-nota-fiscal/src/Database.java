
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

// Cria o banco e as tabelas (DDL).
// A configuracao de conexao (porta, nome do banco, usuario, senha) fica so no BD.java.
public class Database
{
    public void geraBD()
    {
        try (Connection conn = BD.abrir(BD.URL_SERVIDOR);
             Statement st = conn.createStatement())
        {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + BD.NOME_DB);
            JOptionPane.showMessageDialog(null, "BD criado com sucesso");
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
        try (Connection conn = BD.abrir(BD.URL);
             Statement st = conn.createStatement())
        {
            // criacao da tabela de produtos
            String sql = "CREATE TABLE IF NOT EXISTS produto"
                       + "(id int primary key,"
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

            // criacao da tabela de nota fiscal
            // nomes iguais aos usados no INSERT do CadastroNotaFiscal
            String sql2 = "CREATE TABLE IF NOT EXISTS nota_fiscal"
                       + "(id_nota int primary key AUTO_INCREMENT,"
                       + "data_emissao date,"
                       + "id_cliente int,"
                       + "valor_total double)";

            System.out.println("Executando:\n" + sql2);
            st.executeUpdate(sql2);

            // criacao da tabela dos itens da nota
            // PK composta: o id_item recomeca em 1 a cada nota
            String sql3 = "CREATE TABLE IF NOT EXISTS item_nota"
                       + "(id_item int,"
                       + "id_nota int,"
                       + "descricao varchar(50),"
                       + "quantidade int,"
                       + "preco_unitario double,"
                       + "PRIMARY KEY (id_nota, id_item),"
                       + "FOREIGN KEY (id_nota) REFERENCES nota_fiscal (id_nota))";

            System.out.println("Executando:\n" + sql3);
            st.executeUpdate(sql3);

            JOptionPane.showMessageDialog(null, "Tabelas criadas com sucesso");
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
