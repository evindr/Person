package utils;

import java.sql.*;

public class CommonMethodsForDatabase {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static void connectDatabase() throws ClassNotFoundException {



        String url="jdbc:h2:tcp://localhost:8083/mem:personDB";
        String username="testDBUsername";
        String password="testDBPassword";

        String urldb="jdbc:h2:tcp://localhost:8083/mem:personDB;"
                + "user=testDBUsername"
                + "password=testDBPassword;";



        try{
            connection= DriverManager.getConnection(url,username,password);

            System.out.println("Connected "+url+" successfully.");
        }catch (SQLException s){
            System.out.println(s.getMessage());
            System.out.println(s.getCause());
        }
    }

    public static ResultSet runQuery(String query){

        try{
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=statement.executeQuery(query);
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return resultSet;
    }
}
