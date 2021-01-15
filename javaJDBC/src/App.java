import java.sql.*;
import java.util.Scanner;


public class App {

    public enum Options {
        Unknown,
        addColono,
        changeTeam,
        cancelActivity,
        removeMonitor,
        changeMonitor,
        fandteste,
        changeDurationRestriction,
        Exit
    }

    public static final String URL = "jdbc:sqlserver://10.62.73.87:1433;user=L3DG_21;password=L3DG_21;databaseName=L3DG_21";

    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(URL);
            //Run();
            //addColonoTest();
            addActividade();

        }catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
        finally {
            //con.rollback();

            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();

        }
    }

    private static void Run() {
        Options userInput = Options.Unknown;
        do{
            userInput = OptionsMenu();

        }
        while(userInput != Options.Exit);
    }

    private static Options OptionsMenu()
    {
        Options option= Options.Unknown;
            System.out.println("Course management");
            System.out.println();
            System.out.println("1. Add Colono");
            System.out.println("2. Change Team");
            System.out.println("3. Cancel Activity");
            System.out.println("4. Remove Monitor");
            System.out.println("5. Change Monitor");
            System.out.println("6. f and test");
            System.out.println("7. Change Duration Restriction");
            System.out.println("8. Exit");
            System.out.print(">");
            int result = input.nextInt();
            option = Options.values()[result];
        return option;
    }

    public static void testing() {
        try {
            //con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO COLONO " +
                    "VALUES (32,'tp','2007-02-03','+351937862398',10,'13245467',24397623,2,2)");
            System.out.println();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addColonoTest() {
        try {
            System.out.println("Vamos adicionar um novo Colono ao sistema.");
            //int num = input.nextInt();

            //"(numero, nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa)"
            pstmt = con.prepareStatement(("INSERT INTO COLONO VALUES " +
                    " VALUES (" +
                    ",?,?,?,?,?,?,?,?)"));
            pstmt.setInt(1, 33);
            pstmt.setString(2, "tp");
            pstmt.setString(3, "2007-02-03");
            pstmt.setString(4, "+351937862398");
            pstmt.setInt(5, 10);
            pstmt.setString(6, "13245467");
            pstmt.setDouble(7, 24397623);
            pstmt.setInt(8, 2);
            pstmt.setInt(9, 2);
            pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addActividade() {
        try {
            System.out.println("Vamos adicionar uma nova Actividade ao sistema.");
            //int num = input.nextInt();

            //"(numero, nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa)"
            pstmt = con.prepareStatement(("INSERT INTO ACTIVIDADE VALUES " +
                    " VALUES (" +
                    "?,?,?,?,?)"));
            pstmt.setInt(1, 13);
            pstmt.setString(2, "recreativa");
            pstmt.setString(3, "coisas");
            pstmt.setInt(4, 45);
            pstmt.setString(5, "opcional");
            pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addColono() {
        try {
            System.out.println("Vamos adicionar um novo Colono ao sistema.");
            //TODO: Verificar qual é o próximo número de disponível para colonos
            int num = input.nextInt();
            System.out.println("Introduza o seu Nome: ");
            String name = input.nextLine();
            name = name.toUpperCase();

            String date = "";
            do {
                //TODO:
                System.out.println("Introduza a sua data de nascimento: ");
                date = input.nextLine();

            } while (false);

            System.out.println("Introduza o seu contacto: ");
            String contact = input.nextLine();

            int school = -1;
            do {
                System.out.println("Introduza a sua escolaridade: ");
                school = input.nextInt();

                if (school < 0 && school > 12) System.out.println("Input incorreto");
            } while (school < 0 && school > 12);

            System.out.println("Introduza o seu número de cc: ");
            String noCidadao = input.nextLine();

            System.out.println("Introduza o seu número de utente: ");
            String noUtente = input.nextLine();

            System.out.println("Introduza a pessoa responsável: ");
            String eEducacao = input.nextLine();

            System.out.println("Introduza o número da equipa: ");
            String team = input.nextLine();

            //"(numerO, nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa)"
            pstmt = con.prepareStatement(("INSERT INTO COLONO VALUES " +
                    " VALUES (" +
                    ",?,?,?,?,?,?,?,?)"));
            pstmt.setInt(1, num);
            pstmt.setString(2, name);
            pstmt.setString(3, date);
            pstmt.setString(4, contact);
            pstmt.setInt(5, school);
            pstmt.setString(6, noCidadao);
            pstmt.setString(7, noUtente);
            pstmt.setString(8, eEducacao);
            pstmt.setString(9, team);
            pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeTeam()
    {
        //TODO: Implement
        System.out.println("ListCourse()");
    }
    private void cancelActivity()
    {
        //TODO: Implement
        System.out.println("RegisterStudent()");
    }

    private void removeMonitor() throws SQLException {
        try {
            System.out.println("Vamos retirar o monitor que indicar em seguida.");
            boolean aux = true;
            int noID = -1;
            do {
                System.out.println("Introduza o número do ID que deseja retirar: ");
                noID = input.nextInt();

                pstmt = con.prepareStatement("SELECT xxxxxxx FROM xxxxxxxxxxx");
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int value = rs.getInt(1);
                    if (value == noID) {
                        aux = false;
                        break;
                    }
                }

                if (aux) System.out.println("O número que introduziu não está presente na relação");
            } while (aux);

            pstmt = con.prepareStatement("DELETE FROM XXXXXXXXXXXXXXXXX WHERE numero = ?");
            pstmt.setInt(1, noID);
            pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeMonitor()
    {
        //TODO: Implement
        System.out.println("EnrolStudent()");
    }

    private void alíneaF()
    {
        //TODO: Implement
        System.out.println("EnrolStudent()");
    }

    private void changeDuration()
    {
        //TODO: Implement
        System.out.println("EnrolStudent()");
    }
}
