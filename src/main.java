import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

/**
 * Created by Corentin on 20/10/2015.
 */
public class main {

    public static void main(String[] args) {

        ArrayList<Projet> listeProjets = new ArrayList<>();
        listeProjets.add(new Projet(new Date(2017, 06, 01), 200, 40, "CREDIT MUTX"));
        listeProjets.add(new Projet(new Date(2017, 03, 01), 75, 25, "PEUGEIT"));
        listeProjets.add(new Projet(new Date(2017, 07, 01), 150, 45, "RENULT"));

        ArrayList<Employe> listeEmployes = new ArrayList<>();
        listeEmployes.add(new Employe("Meiko","Hampele",Employe.Role.Chef_de_Projet,100));
        listeEmployes.add(new Employe("Claude","Maneisen",Employe.Role.Developpeur,100));
        listeEmployes.add(new Employe("Bertrand","Canta",Employe.Role.Developpeur,100));
        listeEmployes.add(new Employe("Pepsi","Cola",Employe.Role.Developpeur,100));
        listeEmployes.add(new Employe("Adibou","Mitchell",Employe.Role.Developpeur,100));

        testFaisabiliteGlobale(listeProjets, listeEmployes);
    }

    private static boolean testFaisabiliteGlobale(ArrayList<Projet> listeProjets, ArrayList<Employe> listeEmployes) {
        Collections.sort(listeProjets);

        return false;
    }
}
