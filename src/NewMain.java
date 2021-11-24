
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Sathwik
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Connection c = null;
      String CreateSql = null;



      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5433/postgres",
            "postgres", "postgres");
        
//        stmt.executeUpdate("INSERT INTO test(id, name, address)" + "VALUES (1001, 'Simpson', 'Springfield')");
        PreparedStatement st = c.prepareStatement("SELECT COUNT(*) FROM public.users");
        ResultSet r1=st.executeQuery();
        r1.next();
        System.out.print(r1.getInt(1));
//        System.out.print(r1.next());
//          stmt.executeUpdate( "Create Table Users(id int primary key,username varchar, password varchar) ");
//        int id = 2;
//        String username = "sads",password = "dfs";
//        PreparedStatement stmt = c.prepareStatement("INSERT INTO users(id, username, password) VALUES(?, ?, ?)");
//        stmt.setInt(1, id);
//        stmt.setString(2, username);
//        stmt.setString(3, password);
//        stmt.executeUpdate();
          
        



      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        // TODO code application logic here
    }
    
}
