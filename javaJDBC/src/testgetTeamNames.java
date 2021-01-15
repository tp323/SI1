import java.sql.*;

public class testgetTeamNames {

    public static final String url = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";
    public static Connection connect = null;
    private static PreparedStatement pstmt = null;


    public static void main(String[] args) throws SQLException {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            connect = DriverManager.getConnection(url);
            stmt = connect.createStatement();
            //stmt.executeUpdate("INSERT INTO ACTIVIDADE " +
             //       "VALUES (12, 'recreativa', 'hipismo', 90, 'opcional')");
            //rs = stmt.executeQuery("SELECT nome FROM COLONO");
            //while(rs.next()) System.out.println(rs.getString("nome"));
            rs = stmt.executeQuery("SELECT max(numero) FROM COLONO");
            rs.next();
            int myMaxId = rs.getInt(1);

            System.out.println(myMaxId);
            while(rs.next()) System.out.println(rs.getString("numero"));
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
    public static void addActividade() {
        try {
            connect = DriverManager.getConnection(url);

            System.out.println("Vamos adicionar uma nova Actividade ao sistema.");

            pstmt = connect.prepareStatement("INSERT INTO ACTIVIDADE (referencia, designacao, descricao, duracao, participacao) VALUES (?,?,?,?,?)");
            pstmt.setInt(1, 13);
            pstmt.setString(2, "recreativa");
            pstmt.setString(3, "coisas");
            pstmt.setInt(4, 45);
            pstmt.setString(5, "opcional");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}