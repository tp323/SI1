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


        try {
            con = DriverManager.getConnection(URL);
            queries.connect();
            //addColono();
            //queries.getGroupFromEquipa(1);
        }catch(SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
        finally {
            //con.rollback();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
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


    //1
    public static void addColono() {
        String questionBeginMasculino = "Introduza o seu ";
        String questionBeginFeminino = "Introduza a sua ";
        String questionEnd = ": ";

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

        queries.addColono(name,date,contacto,escolaridade,ccidadao,cutente,eeducacao,equipa);
    }


    //2
    private static void changeTeam() {
        int equipaFinal = -1;
        System.out.println("Vamos alterar a equipa de um Colono.");
        System.out.println("Colono que pretendemos alterar de equipa : ");
        int ref = input.nextInt();
        int age = queries.findAgeColono(ref);
        String group = getGroupFromAge(age);
        int maxColonosEquipa = getMaxColonosInTeamByGroup(group);
        int equipa = -1;
        do{
            System.out.println("Equipa para onde pretendemos enviar o colono : ");
            equipa = input.nextInt();
        }while(queries.getGroupFromEquipa(equipa).equals(group) & checkIfTeamHasVacancie(queries.getNumColonosInEquipa(equipa),maxColonosEquipa));

    }

    public static String getGroupFromAge(int age) {
        int beginAge = -1;
        int endAge = -1;
        String group = "";
        //iniciados 6 - 10      juniores 11 - 14        seniores 15 - 17
        switch (age) {
            case 6:
                group = "iniciados";
                break;
            case 11:
                group = "juniores";
                break;
            case 15:
                group = "seniores";
                break;
            case 17:
                group = "seniores";
                break;
            default:
                break;
        }return group;
    }

    public static int getMaxColonosInTeamByGroup(String group){
        //iniciados 6Colonos        juniores 8Colonos         seniores 10Colonos
        int maxColonosInTeam = -1;
        switch (group) {
            case "iniciados":
                maxColonosInTeam = 6;
                break;
            case "juniores":
                maxColonosInTeam = 8;
                break;
            case "seniores":
                maxColonosInTeam = 10;
                break;
            default:
                break;
        }return maxColonosInTeam;
    }

    public static boolean checkIfTeamHasVacancie(int numColonosInTeam, int maxColonosInTeam){
        return numColonosInTeam < maxColonosInTeam;
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
