
import java.sql.*;
import java.util.Scanner;

public class model {

    public static final String CONNECTION_URL = "jdbc:sqlserver://";
    public static final String CONNECTION_SERVER_NAME = "intra.vpn.net.ipl.pt";
    public static final String CONNECTION_PORT = "1433";
    public static final String CONNECTION_DATABASE_NAME = "L3DG_21";
    public static final String CONNECTION_USER = "L3DG_21";
    public static final String CONNECTION_ACESS_PASSWORD = "L3DF_21";

    private static Connection connection;


    public static void main(String[] args){
        connection = null;
        openConnection();
        //App.getinstance

        closeConnection();
    }

    private static String getConnectionUrl() {
        return CONNECTION_URL + CONNECTION_SERVER_NAME + ":" + CONNECTION_PORT + ";databaseName=" + CONNECTION_DATABASE_NAME;
    }

    private static Connection openConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = java.sql.DriverManager.getConnection(getConnectionUrl(), "user="+CONNECTION_USER, "PASSWORD=" + CONNECTION_ACESS_PASSWORD);
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