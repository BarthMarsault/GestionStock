package Model;

import java.io.Serializable;
import java.util.ArrayList;

import static Utils.DataStorage.magasin;


public class Utilisateur implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String mdp;
    private Rayon rayon;


    public Utilisateur(int id, String nom, String prenom, String mdp, Rayon rayon) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.rayon = rayon;

    }

    public Utilisateur(String nom, String prenom, String mdp, Rayon rayon) {
        this.id = magasin.getLastIdUtilisateur();
        this.nom = nom;
        this.prenom = prenom;
        setMdp(mdp);
        this.rayon = rayon;
    }

    public Utilisateur() {
        this.id = magasin.getLastIdUtilisateur();
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
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
        if (validationMdp(mdp)) {
            this.mdp = mdp;
        }
    }

    public boolean validationMdp(String mdp){
        if(mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)([-+!*$@%_\\w]{6,20})$")){
            return  true;
        }
        return false;
    }

    public boolean validationNomPrenom(String str){
        if(str.matches("^[A-Za-z\\é\\è\\ê\\-]+$")){
            return true;
        }
        return false;
    }



    public Rayon getRayon(){
        return rayon;
    }

    public void setRayon(Rayon rayon){
      this.rayon = rayon;
    }

}
