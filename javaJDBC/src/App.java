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
    private static Statement stmt = null;

    private static ResultSet rs = null;

    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(URL);
            //Run();
            //addColono();
            //changeTeam();
            findAgeColono(3);

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
            con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO COLONO " +
                    "VALUES (32,'tp','2007-02-03','+351937862398',10,'13245467',24397623,2,2)");
            System.out.println();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addColono() {
        String questionBeginMasculino = "Introduza o seu ";
        String questionBeginFeminino = "Introduza a sua ";

        String questionEnd = ": ";

        try {
            con = DriverManager.getConnection(URL);

            System.out.println("Vamos adicionar um novo Colono à base de dados.");
            System.out.println(questionBeginMasculino + "Nome" + questionEnd);
            String name = input.nextLine();
            String firstLetter = name.charAt(0)+"";
            firstLetter = firstLetter.toUpperCase();
            name = firstLetter + name.substring(1);

            System.out.println(questionBeginFeminino + "data de nascimento" + questionEnd);
            System.out.println("dado do tipo 2010-03-05");
            String dtnascimento = input.nextLine();
            //TODO: implementar restrições para a leitura charAt(4)=='-' & charAt(7)=='-'
            Date date = Date.valueOf(dtnascimento);

            char verifyplus = 'n';
            String verifycountry = null;
            String contacto;
            do {
                System.out.println(questionBeginMasculino + "Contacto" + questionEnd);
                System.out.println("Contacto português ex +351937862398");
                contacto = input.nextLine();
                verifyplus = contacto.charAt(0);
                verifycountry =  contacto.substring(1,3);
                //TODO: implementar restrições para a leitura do 351

            }while(verifyplus != '+');

            int escolaridade;
            do{
                System.out.println(questionBeginFeminino + "escolaridade" + questionEnd);
                escolaridade = input.nextInt();
            }while(escolaridade<=0 & escolaridade>12);
            input.nextLine();  // Consume newline left-over

            System.out.println(questionBeginMasculino + "cartão de cidadao" + questionEnd);
            String ccidadao = input.nextLine();

            System.out.println(questionBeginMasculino + "cartão de utente" + questionEnd);
            float cutente = input.nextFloat();

            System.out.println(questionBeginMasculino + "Encarregado de Educação" + questionEnd);
            int eeducacao = input.nextInt();

            System.out.println(questionBeginFeminino + "equipa" + questionEnd);
            int equipa = input.nextInt();
            //TODO: implementar restrições para verificar idade para cada equipa

            pstmt = con.prepareStatement("INSERT INTO COLONO " +
                    "(numero, nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa)" +
                    " VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, getNextIdColono());
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
            con = DriverManager.getConnection(URL);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT max(numero) FROM COLONO");
            rs.next();
            myMaxId = rs.getInt(1);
        }
        catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return ++myMaxId;
    }

    private static void changeTeam() {
        int equipaFinal = -1;
        System.out.println("Vamos alterar a equipa de um Colono.");
        System.out.println("Colono que pretendemos alterar de equipa : ");
        int ref = input.nextInt();
        findCurrentTeam(ref);
        //verificar se há vaga na equipa
        //verificar se a equipa pertence ao mesmo age group
        findAgeColono(ref);
        //checkTeamAgeGroup();
    }



    private static int findCurrentTeam(int ref){
        int equipaAtual = -1;
        try {
            con = DriverManager.getConnection(URL);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT (equipa) FROM COLONO WHERE COLONO.numero = " + ref);
            rs.next();
            equipaAtual = rs.getInt(1);
            System.out.println(equipaAtual);
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return equipaAtual;
    }

    public static int findAgeColono(int ref) {
        int age = -1;
        try {
            con = DriverManager.getConnection(URL);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Datediff(YEAR,dtnascimento,GETDATE()) " +
                    "FROM COLONO WHERE COLONO.numero = " + ref);
            rs.next();
            age = rs.getInt(1);
            System.out.println(age);
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }return age;
    }

    //private static String checkTeamAgeGroup() {
        //iniciados;
        //juniores;
    //}

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
