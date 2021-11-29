
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Sathwik
 */
public class Setting {

    /**
     * @param args the command line arguments
     */
    
    public static User user = new User();
    public static Connection c = null;
    static {
      try {        
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres","postgres", "postgres");
        
      }catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
    }
    
      public static boolean createtables(){
        String users = "CREATE TABLE IF NOT EXISTS users "
                + "(id INTEGER unsigned AUTO_INCREMENT, "
                + " username VARCHAR(20) NOT NULL UNIQUE,"
                + " password VARCHAR(70) NOT NULL, "
                + " )";
        
        try {
          Statement stmt = c.createStatement();
          stmt.executeUpdate(users);
          return true;
        } catch (SQLException ex) {
        }
        return false;
  }
    
}
