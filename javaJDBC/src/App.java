import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;


public class App {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        optionsMenu();


    }

    private static void optionsMenu() throws SQLException {
        optionsMenuDisplay();
        switch ( input.nextInt() ) {
            case 1:
                addColono();
                break;
            case 2:
                changeTeamColono();
                break;
            case 3:
                cancelActividade();
                break;
            case 4:
                removeMonitor();
                break;
            case 5:
                changeTeamMonitor();
                break;
            case 6:
                alinea2c();
                break;
            case 7:
                alinea2f();
                break;
            case 8:
                alinea2g();
                break;
            case 9:
                alinea3c();
                break;
            case 10:
                alinea3e();
                break;
            case 11:
                changeDuration();
                break;
            case 12:
                exit();//System.exit(0);
                break;
            default:
                System.err.println("Unrecognized option");
                break;
        }
    }

    private static void exit() throws SQLException {
        System.out.println("Confirm Exit");
        System.out.println("press Y to confirm or any other key to return to the menu");
        char confirmExit = input.next().charAt(0);
        if(confirmExit =='y' || confirmExit =='Y') System.exit(0);
        else optionsMenu();
    }

    private static void optionsMenuDisplay()
    {
        System.out.println("Course management");
        System.out.println();
        System.out.println("1. Add Colono");
        System.out.println("2. Change Team Colono");
        System.out.println("3. Cancel Activity");
        System.out.println("4. Remove Monitor");
        System.out.println("5. Change Team Monitor");
        System.out.println("6. 2.c");
        System.out.println("7. 2.f");
        System.out.println("8. 2.g");
        System.out.println("9. 3.c");
        System.out.println("10. 3.e");
        System.out.println("11. Change Duration Restriction");
        System.out.println("12. Exit");
        System.out.print(">");
    }


    //1
    public static void addColono(){
        input.nextLine();  // Consume newline left-over

        System.out.println("Vamos adicionar um novo Colono à base de dados.");
        System.out.println("Introduza o seu Nome: ");
        String name = input.nextLine();
        String firstLetter = name.charAt(0)+"";
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + name.substring(1);

        System.out.println("Introduza a sua data de nascimento: ");
        System.out.println("dado do tipo 2010-03-05");
        String dtnascimento = input.nextLine();
        //TODO: implementar restrições para a leitura charAt(4)=='-' & charAt(7)=='-'
        Date date = Date.valueOf(dtnascimento);

        char verifyplus;
        String verifycountry;
        String contacto;
        do {
            System.out.println("Introduza o seu Contacto: ");
            System.out.println("Contacto português ex +351937862398");
            contacto = input.nextLine();
            verifyplus = contacto.charAt(0);
            verifycountry =  contacto.substring(1,3);
            //TODO: implementar restrições para a leitura do 351

        }while(verifyplus != '+');

        int escolaridade;
        do{
            System.out.println("Introduza a sua escolaridade: ");
            escolaridade = input.nextInt();
        }while(escolaridade<=0 & escolaridade>12);
        input.nextLine();  // Consume newline left-over

        System.out.println("Introduza o seu cartão de cidadao: ");
        String ccidadao = input.nextLine();

        System.out.println("Introduza o seu cartão de utente: ");
        float cutente = input.nextFloat();

        System.out.println("Introduza o seu Encarregado de Educação: ");
        int eeducacao = input.nextInt();

        System.out.println("Introduza a sua equipa: ");
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
        while(!queries.getGroupFromEquipa(equipa).equals(group) ||
                (!checkIfTeamHasVacancie(queries.getNumColonosInEquipa(equipa),maxColonosEquipa) &&
                        queries.findCurrentTeam(ref)!=equipa)){
            if(!queries.getGroupFromEquipa(equipa).equals(group))
                System.out.println("Colono não tem idade adequada para se juntar a esta equipa");
            else System.out.println("Equipa não tem vagas disponiveis");
            equipa = input.nextInt();
        }
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
    private static void cancelActividade(){
        System.out.println("Vamos remover a seguinte Actividade da base de dados.");
        System.out.println("Actividade que pretendemos remover :  ");
        int ref = input.nextInt();
        queries.deleteActividadeEquipa(ref);
        queries.deleteActividadeMonitor(ref);
        queries.deleteActividadeDesportiva(ref);
        queries.deleteActividade(ref);
    }


    //4
    private static void removeMonitor(){
        System.out.println("Vamos remover o seguinte monitor da base de dados.");
        System.out.println("Introduza o número identificador do Monitor que deseja remover: ");
        int numOldMonitor = input.nextInt();
        int numNewMonitor = -1;
        if (queries.checkIfMonitorIsOnEquipa(numOldMonitor))
            System.out.println("O atual monitor encontra se em uma ou mais equipas");
        while(queries.checkIfMonitorIsOnEquipa(numOldMonitor)){
            System.out.println("Selecione outro monitor para o substituir na(s) mesma(s)");
            numNewMonitor = input.nextInt();
                //só funciona se houver apenas um comonitor para esse monitor, no entanto tal acontecer seria uma anomalia
            if(queries.checkIfMonitorExists(numNewMonitor)){
                queries.updateMonitorEquipa(numOldMonitor, numNewMonitor);
            }
        }queries.deleteComonitor(queries.findComonitor(numOldMonitor));
        queries.deleteMonitor(numOldMonitor);
    }


    //5
    private static void changeTeamMonitor()
    {
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

    //6
    private static void alinea2c() {
        System.out.println("Deseja apresentar as atividades com carácter:");
        String participacao = "";
        System.out.println("Participação (opcional ou obrigatório)");
        while (!participacao.equals("opcional") && !participacao.equals("obrigatório")) participacao = input.nextLine();
        System.out.println("Número minimo de participantes");
        int numMinParticipantes = input.nextInt();
        queries.quer2c(participacao,numMinParticipantes);
    }

    //7
    private static void alinea2f(){
        String grupo = "";
        System.out.println("Gostaria de apresentar a equipa dos: ");
        System.out.println("Grupo (iniciados, juniores, seniores)");
        while (!grupo.equals("iniciados") && !grupo.equals("juniores") && !grupo.equals("seniores")) grupo = input.nextLine();
        queries.quer2f(grupo);
    }

    //8
    private static void alinea2g(){
        System.out.println("Deseja mostrar os representantes responsávies por mais do que quantos colonos?");
        System.out.println("Numero colonos");
        int numColonos = input.nextInt();
        queries.quer2g(numColonos);
    }

    //9
    public static void alinea3c(){
        System.out.println("Deseja apresentar o nome e endereço dos EE com quantos educandos?");
        queries.quer3c();
    }

    public static void doHeader() {
        System.out.println("+------------------------------------------------" +
                "----------------------------------------------+");
        System.out.println("|            Encarregados de Educação           |" +
                "                   Endereços                  |");
        System.out.println("+-------------------------------------------------" +
                "---------------------------------------------+");
    }

    public static void showValues(String nome, String endereco) {
        String spaces = "";
        for(int n = 0; nome.length()+n*2 <= 45;n++) {
            spaces += (" ");
        }System.out.print("|" + spaces + nome + spaces + "|");

        spaces = "";
        for(int n = 0; endereco.length()+n*2 <= 45;n++) spaces += " ";
        System.out.println(spaces + endereco + spaces + "|");

        System.out.println("+------------------------------------------------" +
                "----------------------------------------------+");
    }

    //10
    private static void alinea3e(){
        System.out.println("Entre que horas deseja saber as atividades que não foram realizadas");
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

    //11
    private static void changeDuration()
    {
        System.out.println("Alterar a restrição da duração das Actividades para 120 minutos ");
        queries.changeDuration();
    }
}
