import java.sql.SQLException;

public class Ap3 {
    public static void main(String[] args) throws SQLException, Exception {
        //Source of the JDBC and the SQLServer, here:
        //	http://technet.microsoft.com/en-us/library/ms378988.aspx
        //Source of SQL Server:
        //	- the SQL Browser service must be running: net start SqlBrowser
        //	- The TCP-IP protocol must be connected in SQLServer
        //      - to use integrated security you must specify the following command
        // from the java virtual machine: -Djava.library.path=<directoria para sqljdbc_auth.dll>
        //String url = "jdbc:sqlserver://10.62.73.95:1433;user=jdbcuser;password=jdbcpasswd;databaseName=aula03"; //(1)
        String url = "jdbc:sqlserver://localhost:1433;user=jdbcuser;password=jdbcpasswd;databaseName=aula03";      //(2)

        App.getInstance().setConnectionString(url);
        App.getInstance().Run();
    }
}
