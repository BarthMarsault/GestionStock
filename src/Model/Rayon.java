package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rayon implements Serializable {

    private String nom;
    private ArrayList<Article> listeArticles;

    public Rayon(){
        this.listeArticles = new ArrayList<>();
    }

    public Rayon(Rayon rayon){
        this.nom = rayon.getNom();
        this.listeArticles = rayon.getListeArticles();
    }

    public Rayon(String nom, ArrayList<Article> listeArticles) {
        this.nom = nom;
        this.listeArticles = listeArticles;
    }


    public void addArticle(Article article){
        if(!listeArticles.contains(article) && article != null){
            listeArticles.add(article);
        }
    }

    public void updateArticle(Article ancienArticle, Article nouvelArticle){
        if(!listeArticles.contains(nouvelArticle) && nouvelArticle != null && listeArticles.indexOf(ancienArticle) != -1) {
            listeArticles.set(listeArticles.indexOf(ancienArticle), nouvelArticle);
        }
    }

    public void deleteArticle(Article article){
        listeArticles.remove(article);
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
