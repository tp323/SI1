import java.sql.*;

public class Connect {
    private static java.sql.Connection con = null;
    public static final String CONNECTION_URL = "jdbc:sqlserver://";
    public static final String CONNECTION_SERVER_NAME = "intra.vpn.net.ipl.pt";
    public static final String CONNECTION_PORT = "1433";
    public static final String CONNECTION_DATABASE_NAME = "L3DG_21";
    public static final String CONNECTION_USER = "L3DG_21";
    public static final String CONNECTION_ACESS_PASSWORD = "L3DG_21";  //userpassword

    private static Connection connection;

    // Constructor
    public Connect() {
    }

    public static void main (String[] args){
        getConnection();
    }

    private static String getConnectionUrl() {
        return CONNECTION_URL + CONNECTION_SERVER_NAME + ":" + CONNECTION_PORT + ";databaseName=" + CONNECTION_DATABASE_NAME;
    }

    private static java.sql.Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = java.sql.DriverManager.getConnection(getConnectionUrl(), CONNECTION_USER, CONNECTION_ACESS_PASSWORD);
            if (con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return con;
    }


    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
