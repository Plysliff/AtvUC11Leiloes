
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
    
    // "?useSSL=False" desabilita a necessidade de verificar a identidade do servidor (Usar apenas para projetos locais de estudo)
    private static final String URL = "jdbc:mysql://localhost:3306/leiloestdsat?useSSL=false"; //Nome da base de dados
    private static final String USER = "root"; //nome do usuário do MySQL
    private static final String PASSWORD = "Sonda#9318054"; //senha do MySQL 
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
