import java.util.Date;

/**
 * Created by Corentin on 20/10/2015.
 */
public class Projet implements Comparable<Projet> {

    private Date dateFin;
    private int nbJoursDev;
    private int nbJoursGestion;
    private String nomProjet;

    public Projet() {
    }

    public Projet(Date dateFin, int nbJoursDev, int nbJoursGestion, String nomProjet) {
        this.dateFin = dateFin;
        this.nbJoursDev = nbJoursDev;
        this.nbJoursGestion = nbJoursGestion;
        this.nomProjet = nomProjet;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbJoursDev() {
        return nbJoursDev;
    }

    public void setNbJoursDev(int nbJoursDev) {
        this.nbJoursDev = nbJoursDev;
    }

    public int getNbJoursGestion() {
        return nbJoursGestion;
    }

    public void setNbJoursGestion(int nbJoursGestion) {
        this.nbJoursGestion = nbJoursGestion;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public int compareTo(Projet projet) {
        return this.getDateFin().compareTo(projet.getDateFin());
    }
}
