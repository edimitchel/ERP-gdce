import java.util.Date;

/**
 * Created by Corentin on 20/10/2015.
 */
public class Employe {

    public enum Poste {
        Chef_de_Projet,     //gestion
        Assistant_Gestion,  //gestion
        Resp_technique,     //dev
        Developpeur         //dev
    }

    public enum Role {
        Dev,
        Gestion
    }

    private String nom;
    private String prenom;
    private Poste poste;
    private Date dateDispo;

    public Employe() {
    }

    public Employe(String nom, String prenom, Poste poste, Date dateDispo) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.dateDispo = dateDispo;
    }

    public Date getDateDispo() {
        return dateDispo;
    }

    public void setDateDispo(Date dateDispo) {
        this.dateDispo = dateDispo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
}
