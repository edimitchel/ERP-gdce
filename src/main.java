import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by Corentin on 20/10/2015.
 */
public class main {

    public static void main(String[] args) throws FileNotFoundException {

        PrintStream ps = new PrintStream("file.txt");
        PrintStream orig = System.out;
        System.setOut(ps);

        /// QUESTIONS A , B , C

        System.out.println("\nQUESTIONS A , B , C\n");

        ArrayList<Projet> listeProjets = new ArrayList<>();
        listeProjets.add(new Projet(initDate(2017, 6, 1), 200, 40, "CREDIT MUTX"));
        listeProjets.add(new Projet(initDate(2017, 3, 1), 75, 25, "PEUGEIT"));
        listeProjets.add(new Projet(initDate(2017, 7, 1), 150, 45, "RENULT"));

        ArrayList<Employe> listeEmployes = new ArrayList<>();
        listeEmployes.add(new Employe("Meiko","Hampele", Employe.Poste.Chef_de_Projet, initDate(2017,1,1)));
        listeEmployes.add(new Employe("Claude", "Maneisen", Employe.Poste.Resp_technique, initDate(2017, 1, 1)));   //resp tech
        listeEmployes.add(new Employe("Bertrand", "Canta", Employe.Poste.Developpeur, initDate(2017, 1, 1)));
        listeEmployes.add(new Employe("Pepsi", "Cola", Employe.Poste.Developpeur, initDate(2017, 1, 1)));
        listeEmployes.add(new Employe("Adibou", "Mitchell", Employe.Poste.Developpeur, initDate(2017, 1, 1)));

        evalationRecrutement(listeProjets, listeEmployes, 100);
        System.out.println("----------------------------------------");
        evalationRecrutement(listeProjets, listeEmployes, 80);


        System.out.println("\n############################################");

        /// QUESTION D , E

        System.out.println("\nQUESTION D , E\n");

        listeProjets.add(new Projet(initDate(2017, 9, 1), 400, 80, "AIRBOSS"));

        evalationRecrutement(listeProjets, listeEmployes, 100);
        System.out.println("----------------------------------------");
        evalationRecrutement(listeProjets, listeEmployes, 80);


        System.setOut(orig);
        ps.close();
    }

    public static void evalationRecrutement(ArrayList<Projet> listeProjets, ArrayList<Employe> listeEmployes, int efficience){
        ArrayList<Employe> listeEmployesCopie = new ArrayList<>();
        listeEmployesCopie.addAll(listeEmployes);
        int res = 0;
        int i = 0;
        int nbDEV = 0, nbGEST = 0;
        while((res = testFaisabiliteGlobale(listeProjets, listeEmployesCopie, efficience))!=0){
            switch (res){
                case 1 :
                    nbDEV++;
                    System.out.println("Ajout d'un nouveau developpeur");
                    System.out.println("Relance de la simulation");
                    System.out.println("- - - - - - - - - - - - - - - - - - - -");
                    listeEmployesCopie.add(new Employe("Baby", "LoupDev_"+(i++), Employe.Poste.Developpeur, initDate(2017, 5, 1)));
                    break;
                case 2 :
                    nbGEST++;
                    System.out.println("Ajout d'un nouvel assistant en gestion");
                    System.out.println("Relance de la simulation");
                    System.out.println("- - - - - - - - - - - - - - - - - - - -");
                    listeEmployesCopie.add(new Employe("Baby", "LoupGest_"+(i++), Employe.Poste.Assistant_Gestion, initDate(2017, 5, 1)));
                    break;
                case 3 :
                    nbGEST++;
                    nbDEV++;
                    System.out.println("Ajout d'un nouveau developpeur et d'un nouvel assistant en gestion");
                    System.out.println("Relance de la simulation");
                    System.out.println("- - - - - - - - - - - - - - - - - - - -");
                    listeEmployesCopie.add(new Employe("Baby", "LoupDev_"+(i++), Employe.Poste.Developpeur, initDate(2017, 5, 1)));
                    listeEmployesCopie.add(new Employe("Baby", "LoupGest_"+(i++), Employe.Poste.Assistant_Gestion, initDate(2017, 5, 1)));
                    break;
            }
        }
        if(nbDEV+nbGEST == 0)
            System.out.println("Aucune embauche requise");
        else
            System.out.println("Nous avons du embaucher "+nbDEV+" developpeurs et "+nbGEST+" en gestion.");
    }


    public static Date initDate(int a, int m, int d){
        Date date;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, d);
        calendar.set(Calendar.MONTH, m-1);
        calendar.set(Calendar.YEAR, a);
        date = calendar.getTime();

        return date;
    }

    private static int testFaisabiliteGlobale(ArrayList<Projet> listeProjets, ArrayList<Employe> listeEmployes, int efficience) {

        int result = 0;

        Date currentDate_dev, currentDate_ges;

        Collections.sort(listeProjets);

        currentDate_dev = initDate(2017, 1, 1);
        currentDate_ges = initDate(2017, 1, 1);

        System.out.println("Avec une efficience de "+efficience+"%");

        for(Projet projet : listeProjets){
            float coef = 100.f / efficience;
            float dureeProjet_dev = (projet.getNbJoursDev() * coef) / getEffectif(Employe.Role.Dev, listeEmployes,currentDate_ges);
            float dureeProjet_ges = (projet.getNbJoursGestion() * coef) / getEffectif(Employe.Role.Gestion, listeEmployes,currentDate_ges);

            while(dureeProjet_dev > 0){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate_dev);
                calendar.add(Calendar.DATE, 1);
                currentDate_dev = calendar.getTime();
                if(Utils.estJourTravaille(calendar.getTime())){
                    dureeProjet_dev--;
                }
            }
            while(dureeProjet_ges > 0){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate_ges);
                calendar.add(Calendar.DATE, 1);
                currentDate_ges = calendar.getTime();
                if(Utils.estJourTravaille(calendar.getTime())){
                    dureeProjet_ges--;
                }
            }
            Date endDate = currentDate_dev.after(currentDate_ges) ? currentDate_dev : currentDate_ges;

            if(currentDate_dev.after(projet.getDateFin())){
                result += 1;
            }

            if(currentDate_ges.after(projet.getDateFin())){
                result += 2;
            }

            if(result != 0){
                System.out.println("\tIl sera impossible de finir le projet "+projet.getNomProjet()+" dans les temps. Date de fin effective : "
                        + Utils.dateToSimpleString(endDate)+" - "+getEffectif(Employe.Role.Dev,listeEmployes,endDate)
                        +" dev, "+getEffectif(Employe.Role.Gestion,listeEmployes,endDate)+" gestion");
                return result;
            }
            else{
                System.out.println("\tLe projet "+projet.getNomProjet()+" sera fini dans les temps. Date de fin effective : " + Utils.dateToSimpleString(endDate)
                        +" - "+getEffectif(Employe.Role.Dev, listeEmployes, endDate)
                        +" dev, "+getEffectif(Employe.Role.Gestion, listeEmployes, endDate)+" gestion");
            }
            currentDate_dev = endDate;
            currentDate_ges = endDate;
        }
        return result;
    }


    private static int getEffectif(Employe.Role role, ArrayList<Employe> listeEmployes, Date currentDate){
        int effectif = 0;
        for(Employe employe : listeEmployes){
            if(currentDate == null || !employe.getDateDispo().after(currentDate)) {
                if (role == Employe.Role.Dev) {
                    if (employe.getPoste().equals(Employe.Poste.Developpeur) || employe.getPoste().equals(Employe.Poste.Resp_technique)) {
                        effectif++;
                    }
                } else if (role == Employe.Role.Gestion) {
                    if (employe.getPoste().equals(Employe.Poste.Chef_de_Projet) || employe.getPoste().equals(Employe.Poste.Assistant_Gestion)) {
                        effectif++;
                    }
                }
            }
        }
        return effectif;
    }
}
