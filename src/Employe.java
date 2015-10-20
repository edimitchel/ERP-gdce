/**
 * Created by Corentin on 20/10/2015.
 */
public class Employe {

    public enum Role{
        Chef_de_Projet,
        Developpeur
    }

    private String nom;
    private String prenom;
    private Role role;
    private int efficience;

    public Employe() {
    }

    public Employe(String nom, String prenom, Role role, int efficience) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.efficience = efficience;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getEfficience() {
        return efficience;
    }

    public void setEfficience(int efficience) {
        this.efficience = efficience;
    }
}
