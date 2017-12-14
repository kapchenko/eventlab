package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {
    private static final String url_db="jdbc:postgresql://localhost:5432/eventlaba3";
    private static final String user ="postgres";
    private static final String password="";

    private static Connection connection =null;

    public static  Connection connect(){
        System.out.println("Try connect to Database");

        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        System.out.println("Successfully connected");

        try {
            connection = DriverManager.getConnection(url_db,user,password);
        } catch (SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
        if (connection!=null){
            System.out.println("Connected");
        }
        else {
            System.out.println("Trouble");
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try{
            connection.close();
            System.out.println("Connection close");
        }catch (SQLException e){
            System.out.println("Problem with closing connection");
        }
    }



}
