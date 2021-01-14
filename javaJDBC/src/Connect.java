import java.sql.*;

public class Connect {
    private static java.sql.Connection con = null;
    public static final String CONNECTION_URL = "jdbc:sqlserver://";
    public static final String CONNECTION_SERVER_URL = "10.62.73.87";
    public static final String CONNECTION_PORT = "1433";
    public static final String CONNECTION_DATABASE_NAME = "L3DG_21";
    public static final String CONNECTION_USER = "L3DG_21";
    public static final String CONNECTION_ACESS_PASSWORD = "L3DG_21";  //userpassword
    public static final String CONNECT_URL = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";

    private static Connection connection;

    // Constructor
    public Connect() {
    }

    public static void main (String[] args){
        getConnection();
    }

    private static String getConnectionUrl() {
        return CONNECTION_URL + CONNECTION_SERVER_URL + ":" + CONNECTION_PORT + ";databaseName=" + CONNECTION_DATABASE_NAME;

    }

    public static java.sql.Connection getConnection() {
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
