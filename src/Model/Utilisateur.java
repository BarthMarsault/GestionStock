package Model;

import java.io.Serializable;

import static Utils.DataStorage.magasin;

public class Utilisateur implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String mdp;



    public Utilisateur(int id, String nom, String prenom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;

    }

    public Utilisateur(String nom, String prenom, String mdp) {
        this.id = generationIdentifiant();
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
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
        this.mdp = mdp;
    }
}
