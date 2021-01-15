import java.sql.*;

public class testgetTeamNames {
    public static void main(String[] args) throws SQLException {
        Connection connect = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";
            connect = DriverManager.getConnection(url);
            stmt = connect.createStatement();
            stmt.executeUpdate("INSERT INTO ACTIVIDADE " +
                    "VALUES (12, 'recreativa', 'hipismo', 90, 'opcional')");
            //rs = stmt.executeQuery("SELECT nome FROM COLONO");
            //while(rs.next()) System.out.println(rs.getString("nome"));
            System.out.println();
        }
        catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
        finally {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(connect != null) connect.close();
        }
    }
}