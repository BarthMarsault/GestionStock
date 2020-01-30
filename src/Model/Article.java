package Model;

import java.io.Serializable;

public class Article implements Serializable {

    private String reference;
    private String nom;
    private int qte;
    private String description;


    public Article(String reference, String nom, int qte, String description) {
        this.reference = reference;
        this.nom = nom;
        this.qte = qte;
        this.description = description;
    }

    public Article(String nom, int qte, String description) {
        this.nom = nom;
        this.qte = qte;
        this.description = description;
    }

    public Article(Article article){
        this(article.getReference(),article.getNom(), article.getQte(), article.getDescription());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
