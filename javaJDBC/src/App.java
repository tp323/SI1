//package jdbc;

import java.awt.*;
import java.sql.*;
import java.util.Scanner;
import java.util.HashMap;

interface DbWorker
{
    void doWork() throws SQLException;
}
class App
{
    private enum Option
    {
        Unknown,
        Exit,
        addColono,
        changeTeam,
        cancelActivity,
        removeMonitor,
        changeMonitor,
        alíneaF,
        changeDuration
    }
    private static App __instance = null;
    private String __connectionString;
    private HashMap<Option,DbWorker> __dbMethods;
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Scanner input = new Scanner(System.in);


    private App()
    {
        __dbMethods = new HashMap<Option,DbWorker>();
        __dbMethods.put(Option.addColono, new DbWorker() {public void doWork() throws SQLException {App.this.addColono();}});
        __dbMethods.put(Option.changeTeam, new DbWorker() {public void doWork() {App.this.changeTeam();}});
        __dbMethods.put(Option.cancelActivity, new DbWorker() {public void doWork() {App.this.cancelActivity();}});
        __dbMethods.put(Option.removeMonitor, new DbWorker() {public void doWork() throws SQLException {App.this.removeMonitor();}});
        __dbMethods.put(Option.changeMonitor, new DbWorker() {public void doWork() {App.this.changeMonitor();}});
        __dbMethods.put(Option.alíneaF, new DbWorker() {public void doWork() {App.this.alíneaF();}});
        __dbMethods.put(Option.changeDuration, new DbWorker() {public void doWork() {App.this.changeDuration();}});

    }

    public static void main(String[] args) {
        Connect.getConnection();
        getInstance();
        
    }

    public static App getInstance()
    {
        if(__instance == null)
        {
            __instance = new App();
        }
        return __instance;
    }

    private Option DisplayMenu()
    {
        Option option=Option.Unknown;
        try
        {
            System.out.println("Course management");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. Add Colono");
            System.out.println("3. Change Team");
            System.out.println("4. Cancel Activity");
            System.out.println("5. Remove Monitor");
            System.out.println("6. Change Monitor");
            System.out.println("7. alíneaF");
            System.out.println("8. Change Duration");
            System.out.print(">");
            Scanner s = new Scanner(System.in);
            int result = s.nextInt();
            option = Option.values()[result];
        }
        catch(RuntimeException ex)
        {
            //nothing to do.
        }

        return option;

    }
    private final static void clearConsole() throws Exception
    {
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");

    }
    private void Login() throws java.sql.SQLException
    {

        Connection con = DriverManager.getConnection(getConnectionString());
        if(con != null)
            con.close();

    }
    public void Run() throws Exception
    {
        Login ();
        Option userInput = Option.Unknown;
        do
        {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try
            {
                __dbMethods.get(userInput).doWork();
                System.in.read();

            }
            catch(NullPointerException ex)
            {
                //Nothing to do. The option was not a valid one. Read another.
            }

        }while(userInput!=Option.Exit);
    }

    public String getConnectionString()
    {
        return __connectionString;
    }
    public void setConnectionString(String s)
    {
        __connectionString = s;
    }


    private static final int TAB_SIZE = 4;
    private void printResults(ResultSet dr) throws SQLException
    {
        //TODO
		/*Result must be similar like:
		ListStudent()
		id   name           dateBirth      sex            
		----------------------------------------
		1    John           1970-01-21     M              
		2    Joe            1971-07-12     M              
		3    Mary           1969-05-04     F              
		4    Bob            1970-12-12     M              
		5    Zoe            1978-12-12     F        */
    }


    private void addColono() throws SQLException {
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


            pstmt = con.prepareStatement(("INSERT INTO XXXXXXX (numero(verificar), nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa) VALUES (?,?,?,?,?,?,?,?,?)"));
            //String a = ''INSERT INTO XXXXXXX (numero(verificar), nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa) VALUES (?,?,?,?,?,?,?,?,?) '';
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




            Login();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Login();
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




            Login();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Login();
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


