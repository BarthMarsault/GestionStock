package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rayon implements Serializable {

    private int id;
    private String nom;
    private ArrayList<Article> listeArticles;

    public Rayon(int id, String nom, ArrayList<Article> listeArticles) {
        this.id = id;
        this.nom = nom;
        this.listeArticles = listeArticles;
    }

    public Rayon(String nom, ArrayList<Article> listeArticles) {
        this.nom = nom;
        this.listeArticles = listeArticles;
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

    public ArrayList<Article> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(ArrayList<Article> listeArticles) {
        this.listeArticles = listeArticles;
    }
}
