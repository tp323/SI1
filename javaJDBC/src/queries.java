import java.sql.*;

public class queries {
    private static final String URL = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";

    private static Connection connection = null;
    private static PreparedStatement pstmt = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void connect(){
        try {
            connection = DriverManager.getConnection(URL);
        }catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void close(){
        try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(stmt != null) stmt.close();
            if(connection != null) connection.close();
        }catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //1
    public static void addColono(String name,Date date,String contacto,int escolaridade,String ccidadao,float cutente,int eeducacao,int equipa) {
        try {
            connect();
            pstmt = connection.prepareStatement("INSERT INTO COLONO " +
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
            pstmt.setInt(9, equipa); //does not check if team is full or same age group
            pstmt.executeUpdate();
            close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNextIdColono(){
        int myMaxId = -1;
        try {
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT max(numero) FROM COLONO");
            rs.next();
            myMaxId = rs.getInt(1);
            rs.close();
            stmt.close();
        }
        catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return ++myMaxId;
    }

    //2
    public static int findCurrentTeam(int ref){
        int equipaAtual = -1;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT equipa FROM COLONO WHERE numero = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            equipaAtual = rs.getInt(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return equipaAtual;
    }

    public static int findAgeColono(int ref){
        int age = -1;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT Datediff(YEAR,dtnascimento,GETDATE()) " +
                    "FROM COLONO WHERE numero = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            age = rs.getInt(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return age;
    }
    public static String getGroupFromEquipa(int equipa){
        String group = "";
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT grupo FROM EQUIPA WHERE numero = ?");
            pstmt.setInt(1, equipa);
            rs = pstmt.executeQuery();
            rs.next();
            group = rs.getString(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return group;
    }

    public static int getNumColonosInEquipa(int equipa){
        int numColonosInEquipa = -1;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT COUNT (equipa) FROM COLONO WHERE equipa = ?");
            pstmt.setInt(1, equipa);
            rs = pstmt.executeQuery();
            rs.next();
            numColonosInEquipa = rs.getInt(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return numColonosInEquipa;
    }

    public static void changeTeam(int colono,int equipa){
        try {
            connect();
            pstmt = connection.prepareStatement("UPDATE COLONO SET equipa = ? WHERE numero = ?");
            pstmt.setInt(1, equipa);
            pstmt.setInt(2, colono);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //3
    public static void deleteActividadeMonitor(int ref){
        try {
            connect();
            pstmt = connection.prepareStatement("DELETE FROM ACTIVIDADE_MONITOR WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteActividadeEquipa(int ref){
        try {
            connect();
            pstmt = connection.prepareStatement("DELETE FROM ACTIVIDADE_EQUIPA WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteActividadeDesportiva(int ref){
        try {
            connect();
            pstmt = connection.prepareStatement("DELETE FROM ACTIVIDADE_DESPORTIVA WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteActividade(int ref){
        try {
            connect();
            pstmt = connection.prepareStatement("DELETE FROM ACTIVIDADE WHERE referencia = ? ");
            pstmt.setInt(1, ref);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //4
    public static boolean checkIfMonitorIsOnEquipa(int num){
        boolean check = false;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT COUNT (monitor) FROM EQUIPA WHERE monitor = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            rs.next();
            check = rs.getBoolean(1);
            close();
        }catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return check;
    }

    public static boolean checkIfMonitorExists(int num){
        boolean check = false;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT COUNT (numero) FROM MONITOR WHERE numero = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            rs.next();
            check = rs.getBoolean(1);
            close();
        }catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return check;
    }

    public static void updateMonitorEquipa(int numOldMonitor, int numNewMonitor){
        try {
            connect();
            pstmt = connection.prepareStatement("UPDATE EQUIPA SET monitor = ? WHERE monitor = ?");
            pstmt.setInt(1, numNewMonitor);
            pstmt.setInt(2, numOldMonitor);
            pstmt.executeUpdate();
            close();
        }catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static int findComonitor(int num){
        int monitor = -1;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT numero FROM MONITOR WHERE comonitor = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            rs.next();
            monitor = rs.getInt(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return monitor;
    }

    public static void deleteComonitor(int num){
        try {
            connect();
            pstmt = connection.prepareStatement("UPDATE MONITOR SET comonitor = NULL WHERE numero = ?");
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static void deleteMonitor(int num){
        try {
            connect();
            pstmt = connection.prepareStatement("DELETE FROM MONITOR WHERE numero = ? ");
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //5
    public static void changeTeamMonitor(int monitor, int equipa){
        try {
            connect();
            pstmt = connection.prepareStatement("UPDATE EQUIPA SET monitor = ? WHERE numero = ?");
            pstmt.setInt(1, monitor);
            pstmt.setInt(2, equipa);
            pstmt.executeUpdate();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    public static int findCurrentMonitor(int ref){
        int numero = -1;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT monitor FROM EQUIPA WHERE numero = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            numero = rs.getInt(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return numero;
    }

    public static int findCurrentTeamFromMonitor(int ref){
        int equipaAtual = -1;
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT numero FROM EQUIPA WHERE monitor = ?");
            pstmt.setInt(1, ref);
            rs = pstmt.executeQuery();
            rs.next();
            equipaAtual = rs.getInt(1);
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return equipaAtual;
    }


    //6
    public static void quer2c(String participacao,int participantes){
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT descricao " +
                    "FROM (ACTIVIDADE JOIN ACTIVIDADE_DESPORTIVA ON ACTIVIDADE.referencia = ACTIVIDADE_DESPORTIVA.referencia) " +
                    "WHERE (participacao = ? AND participantes >= ?)");
            pstmt.setString(1, participacao);
            pstmt.setInt(2, participantes);
            rs = pstmt.executeQuery();
            while(rs.next()) System.out.println(rs.getString("descricao"));
            System.out.println();
            close();
        }
        catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //7
    public static void quer2f(String grupo){
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT descricao " +
                    "FROM ((ACTIVIDADE_EQUIPA JOIN EQUIPA ON ACTIVIDADE_EQUIPA.equipa = EQUIPA.numero) " +
                    "JOIN ACTIVIDADE ON ACTIVIDADE_EQUIPA.referencia = ACTIVIDADE.referencia) " +
                    "WHERE grupo = ?");
            pstmt.setString(1, grupo);
            rs = pstmt.executeQuery();
            while(rs.next()) System.out.println(rs.getString("descricao"));
            System.out.println();
            close();
        }
        catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //8
    public static void quer2g(int num){
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT nome " +
                    "    FROM(SELECT SCN.representante" +
                    "         FROM (SELECT FRS.colono AS representante, COUNT(equipa) mbrs" +
                    "                 FROM (SELECT R.colono, C.equipa " +
                    "                       FROM (REPRESENTANTE R JOIN COLONO C ON R.equipa = C.equipa)) AS FRS" +
                    "                 GROUP BY FRS.colono) AS SCN" +
                    "         WHERE mbrs > ?) AS TR JOIN COLONO ON TR.representante = numero;");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while (rs.next()) System.out.println(rs.getString("nome"));
            System.out.println();
            close();
        }catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //9
    public static void quer3c(){
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT nome, endereco FROM PESSOA P" +
                    "        WHERE (SELECT COUNT(*) FROM COLONO C WHERE P.numero = C.eeducacao) > 1;");
            rs = pstmt.executeQuery();
            App.doHeader();
            while (rs.next()) App.showValues(rs.getString("nome"), rs.getString("endereco"));
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //10
    public static void quer3e(String startTime, String endTime){
        try {
            connect();
            pstmt = connection.prepareStatement("SELECT designacao FROM ACTIVIDADE JOIN ACTIVIDADE_EQUIPA " +
                    "ON ACTIVIDADE.referencia = ACTIVIDADE_EQUIPA.referencia" +
                    "    WHERE (horainicial < ? AND horafinal < ?) " +
                    "OR (horainicial > ? AND horafinal > ?);");
            pstmt.setString(1, startTime);
            pstmt.setString(2, endTime);
            pstmt.setString(3, startTime);
            pstmt.setString(4, endTime);

            rs = pstmt.executeQuery();
            while (rs.next()) System.out.println(rs.getString("designacao"));
            System.out.println();
            close();
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
    }

    //11
    public static void changeDuration() {
        try {
            connect();
            pstmt = connection.prepareStatement("ALTER TABLE ACTIVIDADE_EQUIPA DROP CONSTRAINT IF EXISTS ck_duracao");
            pstmt.executeUpdate();
            pstmt.close();
            pstmt = connection.prepareStatement("ALTER TABLE ACTIVIDADE_EQUIPA ADD CONSTRAINT check_duracao " +
                    "CHECK (horafinal > horainicial AND horafinal <= DATEADD(MINUTE,120,horainicial))");
            pstmt.executeUpdate();
            close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}