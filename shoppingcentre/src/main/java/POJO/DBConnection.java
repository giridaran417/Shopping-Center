package POJO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private static Connection connection;
    public  void setConnection(String dbname, String username, String pass){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname+"?autoReconnect=true&useSSL=false", username, pass);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    public void closeConnection(){
        try{
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static ResultSet selectQuery(String query) throws Exception{

        System.out.println(connection + " connecton");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;

    }

    public static void insertQuery(String query) throws Exception{

        Statement stmt = connection.createStatement();
        stmt.execute(query);

    }

    public static void updateQuery(String query) throws Exception{

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);

    }
}
