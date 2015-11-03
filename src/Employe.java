/**
 * Created by Corentin on 20/10/2015.
 */
public class Employe {

    public enum Poste {
        Chef_de_Projet,     //gestion
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

    public Employe() {
    }

    public Employe(String nom, String prenom, Poste poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
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
