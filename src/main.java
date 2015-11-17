import java.text.DateFormat;
import java.util.*;

/**
 * Created by Corentin on 20/10/2015.
 */
public class main {

    public static void main(String[] args) {
        ArrayList<Projet> listeProjets = new ArrayList<>();
        listeProjets.add(new Projet(initDate(2017, 06, 01), 200, 40, "CREDIT MUTX"));
        listeProjets.add(new Projet(initDate(2017, 03, 01), 75, 25, "PEUGEIT"));
        listeProjets.add(new Projet(initDate(2017, 07, 01), 150, 45, "RENULT"));

        ArrayList<Employe> listeEmployes = new ArrayList<>();
        listeEmployes.add(new Employe("Meiko","Hampele", Employe.Poste.Chef_de_Projet));
        listeEmployes.add(new Employe("Claude", "Maneisen", Employe.Poste.Resp_technique));   //resp tech
        listeEmployes.add(new Employe("Bertrand", "Canta", Employe.Poste.Developpeur));
        listeEmployes.add(new Employe("Pepsi", "Cola", Employe.Poste.Developpeur));
        listeEmployes.add(new Employe("Adibou", "Mitchell", Employe.Poste.Developpeur));

        testFaisabiliteGlobale(listeProjets, listeEmployes, 100);
        testFaisabiliteGlobale(listeProjets, listeEmployes, 80);
//        testFaisabiliteGlobale(listeProjets, listeEmployes, 50);
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

    private static boolean testFaisabiliteGlobale(ArrayList<Projet> listeProjets, ArrayList<Employe> listeEmployes, int efficience) {
        boolean faisable = true;
        Date currentDate_dev, currentDate_ges;

        Collections.sort(listeProjets);

        currentDate_dev = initDate(2017,1,1);
        currentDate_ges = initDate(2017, 1, 1);

        for(Projet projet : listeProjets){
            float coef = 100.f / efficience;
            float dureeProjet_dev = (projet.getNbJoursDev() * coef) / getEffectif(Employe.Role.Dev, listeEmployes);
            float dureeProjet_ges = (projet.getNbJoursGestion() * coef) / getEffectif(Employe.Role.Gestion, listeEmployes);
            
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
            if(currentDate_dev.after(projet.getDateFin()) || currentDate_ges.after(projet.getDateFin())){
                faisable = false;
                System.out.println("Avec une efficience de "+efficience+"%, il sera impossible de finir le projet "+projet.getNomProjet()+" dans les temps " + Utils.dateToSimpleString(endDate));
            }
            else{
                System.out.println("Avec une efficience de "+efficience+"%, le projet "+projet.getNomProjet()+" sera fini dans les temps " + Utils.dateToSimpleString(endDate));
            }
            currentDate_dev = endDate;
            currentDate_ges = endDate;
        }

        return false;
    }


    private static int getEffectif(Employe.Role role, ArrayList<Employe> listeEmployes){
        int effectif = 0;
        for(Employe employe : listeEmployes){
            if(role == Employe.Role.Dev){
                if(employe.getPoste().equals(Employe.Poste.Developpeur) || employe.getPoste().equals(Employe.Poste.Resp_technique)){
                    effectif++;
                }
            } else if(role ==Employe.Role.Gestion){
                if(employe.getPoste().equals(Employe.Poste.Chef_de_Projet)){
                    effectif++;
                }
            }
        }
        return effectif;
    }
}
