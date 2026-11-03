
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            // Instanciando e chamando a conexão
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            
            // Preparando comando
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            // Pxecutando o comando
            prep.executeUpdate();
            
            // Fechando a conexão
            prep.close();
            conn.close();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        
        String sql = "SELECT * FROM produtos"; // Comando enviado ao banco
        ArrayList<ProdutosDTO> listagem = new ArrayList<>(); // Lista para guardar os produtos encontrados no banco

        try {
            // Abre a conexão, prepara o SELECT, e executa a consulta
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            // Percorre cada linha da tabela...
            while (resultset.next()) {
                // Criando objetos preenchidos com os dados dos objetos banco
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                // Adiciona o objeto criado à lista
                listagem.add(produto);
            }
            
            // Finaliza a conexão
            resultset.close();
            prep.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listagem;
    }
    
    
    
        
}

