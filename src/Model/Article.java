package Model;

import java.io.Serializable;

public class Article implements Serializable {

    private String reference;
    private String nom;
    private int qte;
    private Rayon rayon;
    private String description;


    public Article(String reference, String nom, int qte, Rayon rayon, String description) {
        this.reference = reference;
        this.nom = nom;
        this.qte = qte;
        this.rayon = rayon;
        this.description = description;
    }

    public Article(String nom, int qte, Rayon rayon, String description) {
        this.nom = nom;
        this.qte = qte;
        this.rayon = rayon;
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
