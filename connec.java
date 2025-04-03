
package hotelmanagementsystem;


import java.sql.*;
public class connec {
    Connection c;
    Statement s;
    connec(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
      c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root","Sumit@123");
       
            s=c.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
