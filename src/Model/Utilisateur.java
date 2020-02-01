package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

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

    /**
     * Valide le mot de passe s'il contient à minima une minuscule, une majuscule,
     * Un chiffre et qu'il fait entre 6 et 20 caractère.
     * @param mdp String
     * @return  Vrai si le mot de passe respecte les conditions
     *          Faux Sinon
     */
    public boolean validationMdp(String mdp){
        if(mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)([-+!*$@%_\\w]{6,20})$")){
            return  true;
        }
        return false;
    }

    /**
     * Verifie que la chaîne passé en paramètre ne contient que des caractères valides pour
     * un nom ou un prénom (Lettres et tirets)
     * @param str String
     * @return  Vrai si la chaîne respecte les conditions
     *          Faux Sinon
     */
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom);
    }
}
