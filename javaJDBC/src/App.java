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
            //changeTeamColono();
            //cancelActividade();
            //removeMonitor();
            //changeTeamMonitor();
            //alinea2c();
            //alinea2f();

            //alinea2g(); needs fix

            alinea3e();
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
    private static void changeTeamColono() {
        int equipaFinal = -1;
        System.out.println("Vamos alterar a equipa de um Colono.");
        System.out.println("Colono que pretendemos alterar de equipa : ");
        int ref = input.nextInt();
        int age = queries.findAgeColono(ref);
        String group = getGroupFromAge(age);
        int maxColonosEquipa = getMaxColonosInTeamByGroup(group);
        int equipa = -1;
        System.out.println("Equipa para onde pretendemos enviar o colono : ");
        equipa = input.nextInt();
        while(!queries.getGroupFromEquipa(equipa).equals(group) || !checkIfTeamHasVacancie(queries.getNumColonosInEquipa(equipa),maxColonosEquipa)){
            if(!queries.getGroupFromEquipa(equipa).equals(group))
                System.out.println("Colono não tem idade adequada para se juntar a esta equipa");
            else System.out.println("Equipa não tem vagas disponiveis");
            equipa = input.nextInt();
        }
        //TODO: se a equipa tiver cheia e o colono pertencer já à mesma não o conseguimos inserir na mesma
        // usar currentTeam para corrigir isso
        queries.changeTeam(ref,equipa);
    }

    public static String getGroupFromAge(int age) {
        String group = "";
        //iniciados 6 - 10      juniores 11 - 14        seniores 15 - 17
        if(age >= 6 & age <= 10)    group = "iniciados";
        else if(age >= 11 & age <= 14)  group = "juniores";
        else if(age >= 15 & age <= 17)  group = "seniores";
        return group;
    }

    public static int getMaxColonosInTeamByGroup(String group){
        //iniciados 6Colonos        juniores 8Colonos         seniores 10Colonos
        int maxColonosInTeam = -1;
        if(group.equals("iniciados"))   maxColonosInTeam = 6;
        else if(group.equals("juniores"))  maxColonosInTeam = 8;
        else if(group.equals("seniores"))  maxColonosInTeam = 10;
        return maxColonosInTeam;
    }

    public static boolean checkIfTeamHasVacancie(int numColonosInTeam, int maxColonosInTeam){
        return numColonosInTeam < maxColonosInTeam;
    }


    //3
    private static void cancelActividade()
    {
        System.out.println("Vamos remover a seguinte Actividade da base de dados.");
        System.out.println("Actividade que pretendemos remover :  ");
        int ref = input.nextInt();
        queries.deleteActividadeEquipa(ref);
        queries.deleteActividadeMonitor(ref);
        queries.deleteActividadeDesportiva(ref);
        queries.deleteActividade(ref);
    }


    //4
    private static void removeMonitor() throws SQLException {
        System.out.println("Vamos remover o seguinte monitor da base de dados.");
        System.out.println("Introduza o número identificador do Monitor que deseja remover: ");
        int numOldMonitor = input.nextInt();
        int numNewMonitor = -1;
        if (queries.checkIfMonitorIsOnEquipa(numOldMonitor))
            System.out.println("O atual monitor encontra se em uma ou mais equipas");
        while(queries.checkIfMonitorIsOnEquipa(numOldMonitor)){
            System.out.println("Selecione outro monitor para o substituir na(s) mesma(s)");
            numNewMonitor = input.nextInt();

            if(queries.checkIfMonitorExists(numNewMonitor)){
                //TODO: só funciona para o caso de o monitor estar em 1 ou menos equipas
                queries.updateMonitorEquipa(numOldMonitor, numNewMonitor);
                break;
            }
        }
        queries.deleteComonitor(queries.findComonitor(numOldMonitor));
        queries.deleteMonitor(numOldMonitor);
    }




    //5
    private static void changeTeamMonitor()
    {
        int equipaFinal = -1;
        System.out.println("Vamos alterar a equipa de um Monitor.");
        System.out.println("Monitor que pretendemos alterar de equipa : ");
        int selectedMonitor = input.nextInt();
        System.out.println("Equipa para onde pretendemos enviar o Monitor : ");
        int equipa = input.nextInt();

        if(queries.checkIfMonitorIsOnEquipa(selectedMonitor)){
            //efetuar troca de monitor
            int monitorAlreadyOnEquipa = queries.findCurrentMonitor(equipa);
            int currentEquipa = queries.findCurrentTeamFromMonitor(selectedMonitor);
            queries.changeTeamMonitor(monitorAlreadyOnEquipa, currentEquipa);
            queries.changeTeamMonitor(selectedMonitor, equipa);
        }else {
            queries.changeTeamMonitor(selectedMonitor, equipa);
        }
    }

    private static void alinea2c() {
        String participacao = "";
        System.out.println("Participação (opcional ou obrigatório)");
        while (!participacao.equals("opcional") && !participacao.equals("obrigatório")) participacao = input.nextLine();
        System.out.println("Número minimo de participantes");
        int numMinParticipantes = input.nextInt();
        queries.quer2c(participacao,numMinParticipantes);
    }

    private static void alinea2f(){
        String grupo = "";
        System.out.println("Grupo (iniciados, juniores, seniores)");
        while (!grupo.equals("iniciados") && !grupo.equals("juniores") && !grupo.equals("seniores")) grupo = input.nextLine();
        queries.quer2f(grupo);
    }

    private static void alinea2g(){
        //TODO: fix query
        System.out.println("Numero colonos");
        int numColonos = input.nextInt();
        queries.quer2g(numColonos);
    }

    private static void alinea3c(){

    }

    private static void alinea3e(){
        System.out.println("Staring time activity (HH:MM:SS)");
        String startTime = getTime();
        System.out.println("End time activity");
        String endTime = getTime();
        queries.quer3e(startTime,endTime);
    }

    private static String getTime(){
        String time = "";
        while(time.length()<8)time = input.nextLine();
        if(time.charAt(2)!=':' || time.charAt(5)!=':')getTime();
        return time;
    }

    private static void changeDuration()
    {
        //TODO: Implement
    }
}
