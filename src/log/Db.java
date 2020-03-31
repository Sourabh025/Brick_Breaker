package log;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.*;   //added package from mysql-connection
public class Db {
   public static void main(String[]args){
      Connection con = null;
      try {
    	  Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "aa44bd80sr");
         System.out.println("Connection is successful !!!!!");
      } catch(Exception e) {
         System.out.print(e);
      }
   }
}