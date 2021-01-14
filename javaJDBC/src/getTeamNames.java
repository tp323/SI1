import java.sql.*;

public class getTeamNames {
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT designacao, monitor FROM EQUIPA");
            while(rs.next()) System.out.println(rs.getString("designacao"));
            System.out.println();
        } catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
        finally {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
    }
}