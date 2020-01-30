package Model;

import java.io.Serializable;

import static Utils.DataStorage.magasin;

public class Utilisateur implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String mdp;
    private Rayon rayon;



    public Utilisateur(int id, String nom, String prenom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;

    }



    public Utilisateur(String nom, String prenom, String mdp, Rayon rayon) {
        this.id = generationIdentifiant();
        this.nom = nom;
        this.prenom = prenom;
        setMdp(mdp);
        this.rayon = rayon;
    }



    @Override
    public String toString() {
        return prenom + " " + nom;
    }



    public int generationIdentifiant(){
        return  (int) (Math.random() * (999999 - 1000));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        if(validationMdp(mdp)){
            this.mdp = mdp;
        }
    }

    public boolean validationMdp(String mdp){
        if(mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)([-+!*$@%_\\w]{6,20})$")){
            return  true;
        }
        return false;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }
}
