
import java.sql.*;
import java.util.Scanner;

public class TP3_G21_SI1 {

    public static final String CONNECTION_URL = "jdbc:sqlserver://";
    public static final String CONNECTION_SERVER_NAME = "intra.vpn.net.ipl.pt";
    public static final String CONNECTION_PORT = "1433";
    public static final String CONNECTION_DATABASE_NAME = "L";
    public static final String CONNECTION_USER = "47206@alunos.isel.ipl.pt";
    public static final String CONNECTION_ACESS_PASSWORD = "";  //userpassword

    private static Connection connection;


    public static void main(String[] args){
        connection = null;
        openConnection();



        closeConnection();
    }

    private static String getConnectionUrl() {
        return CONNECTION_URL + CONNECTION_SERVER_NAME + ":" + CONNECTION_PORT + ";databaseName=" + CONNECTION_DATABASE_NAME;
    }

    private static Connection openConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = java.sql.DriverManager.getConnection(getConnectionUrl(), CONNECTION_USER, CONNECTION_ACESS_PASSWORD);
            if (connection != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return connection;
    }

    private static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}