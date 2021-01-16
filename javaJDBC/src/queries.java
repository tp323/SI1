import java.sql.*;
import java.util.Scanner;

public class queries {
    public static final String URL = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";

    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static Statement stmt = null;

    private static ResultSet rs = null;

    public static Scanner input = new Scanner(System.in);


    public static void connect(){
        try {
            con = DriverManager.getConnection(URL);
        }catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //1
    public static void addColono(String name,Date date,String contacto,int escolaridade,String ccidadao,float cutente,int eeducacao,int equipa) {
        try {
            con = DriverManager.getConnection(URL);
            pstmt = con.prepareStatement("INSERT INTO COLONO " +
                    "(numero, nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa)" +
                    " VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, queries.getNextIdColono());
            pstmt.setString(2, name);
            pstmt.setDate(3, date);
            pstmt.setString(4, contacto);
            pstmt.setInt(5, escolaridade);
            pstmt.setString(6, ccidadao);
            pstmt.setFloat(7, cutente);
            pstmt.setInt(8, eeducacao);
            pstmt.setInt(9, equipa);
            pstmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNextIdColono() {
        int myMaxId = -1;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT max(numero) FROM COLONO");
            rs.next();
            myMaxId = rs.getInt(1);
        }
        catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return ++myMaxId;
    }

    //2
    public static int findCurrentTeam(int ref){
        int equipaAtual = -1;
        try {
            pstmt = con.prepareStatement("SELECT equipa FROM COLONO WHERE numero = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            equipaAtual = rs.getInt(1);
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return equipaAtual;
    }

    public static int findAgeColono(int ref) {
        int age = -1;
        try {
            pstmt = con.prepareStatement("SELECT Datediff(YEAR,dtnascimento,GETDATE()) " +
                    "FROM COLONO WHERE numero = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            age = rs.getInt(1);
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return age;
    }
    public static String getGroupFromEquipa(int equipa) {
        String group = "";
        try {
            pstmt = con.prepareStatement("SELECT grupo FROM EQUIPA WHERE numero = ?");
            pstmt.setInt(1, equipa);
            rs = pstmt.executeQuery();
            rs.next();
            group = rs.getString(1);
            pstmt.close();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return group;
    }

    public static int getNumColonosInEquipa(int equipa) {
        int numColonosInEquipa = -1;
        try {
            pstmt = con.prepareStatement("SELECT COUNT (equipa) FROM COLONO WHERE equipa = ?");
            pstmt.setInt(1, equipa);
            rs = pstmt.executeQuery();
            rs.next();
            numColonosInEquipa = rs.getInt(1);
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return numColonosInEquipa;
    }

    public static void changeTeam(int colono,int equipa) {
        try {
            pstmt = con.prepareStatement("UPDATE COLONO SET equipa = ? WHERE numero = ?");
            pstmt.setInt(1, equipa);
            pstmt.setInt(2, colono);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //3
    public static void deleteActividadeMonitor(int ref){
        try {
            pstmt = con.prepareStatement("DELETE FROM ACTIVIDADE_MONITOR WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteActividadeEquipa(int ref){
        try {
            pstmt = con.prepareStatement("DELETE FROM ACTIVIDADE_EQUIPA WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteActividadeDesportiva(int ref){
        try {
            pstmt = con.prepareStatement("DELETE FROM ACTIVIDADE_DESPORTIVA WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteActividade(int ref){
        try {
            pstmt = con.prepareStatement("DELETE FROM ACTIVIDADE WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }


    //4
    public static boolean checkIfMonitorIsOnEquipa(int num){
        boolean check = false;
        try {
            pstmt = con.prepareStatement("SELECT COUNT (monitor) FROM EQUIPA WHERE monitor = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            rs.next();
            check = rs.getBoolean(1);
        }catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return check;
    }

    public static boolean checkIfMonitorExists(int num){
        boolean check = false;
        try {
            pstmt = con.prepareStatement("SELECT COUNT (numero) FROM MONITOR WHERE numero = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            rs.next();
            check = rs.getBoolean(1);
        }catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return check;
    }

    public static void updateMonitorEquipa(int numOldMonitor, int numNewMonitor){
        try {
            pstmt = con.prepareStatement("UPDATE EQUIPA SET monitor = ? WHERE monitor = ?");
            pstmt.setInt(1, numNewMonitor);
            pstmt.setInt(2, numOldMonitor);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static int findComonitor(int num){
        int monitor = -1;
        try {
            pstmt = con.prepareStatement("SELECT numero FROM MONITOR WHERE comonitor = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            rs.next();
            monitor = rs.getInt(1);
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return monitor;
    }

    public static void deleteComonitor(int num){
        try {
            pstmt = con.prepareStatement("UPDATE MONITOR SET comonitor = NULL WHERE numero = ?");
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteMonitor(int num){
        try {
            pstmt = con.prepareStatement("DELETE FROM MONITOR WHERE numero = ? ");
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //5
    public static void changeTeamMonitor(int monitor, int equipa){
        try {
            pstmt = con.prepareStatement("UPDATE EQUIPA SET monitor = ? WHERE numero = ?");
            pstmt.setInt(1, monitor);
            pstmt.setInt(2, equipa);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static int findCurrentMonitor(int ref){
        int numero = -1;
        try {
            pstmt = con.prepareStatement("SELECT monitor FROM EQUIPA WHERE numero = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            numero = rs.getInt(1);
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return numero;
    }

    public static int findCurrentTeamFromMonitor(int ref){
        int equipaAtual = -1;
        try {
            pstmt = con.prepareStatement("SELECT numero FROM EQUIPA WHERE monitor = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            equipaAtual = rs.getInt(1);
            pstmt.close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return equipaAtual;
    }

}
