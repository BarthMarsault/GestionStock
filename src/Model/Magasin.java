package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Magasin implements Serializable {
    private ArrayList<Utilisateur> listeUtilisateurs;
    private ArrayList<Rayon> listeRayons;


    public Magasin(){
        listeRayons = new ArrayList<>();
        listeUtilisateurs = new ArrayList<>();
    }

    public Magasin(ArrayList<Utilisateur> listeUtilisateurs, ArrayList<Rayon> listeRayons) {
        this.listeUtilisateurs = listeUtilisateurs;
        this.listeRayons = listeRayons;
    }


    public void addUtilisateur(Utilisateur utilisateur){
        if(!listeUtilisateurs.contains(utilisateur) && utilisateur != null){
            listeUtilisateurs.add(utilisateur);
        }

    }

    public void addRayon(Rayon rayon){
        if(!listeRayons.contains(rayon) && rayon != null){
            listeRayons.add(rayon);
        }
    }

    public Rayon getRayonFromArticle(Article article){
        for(Rayon r : listeRayons){
            for(Article ar : r.getListeArticles())
                if(article.equals(ar))
                    return r;
        }
        return null;
    }

    public void affichageArticleParRayon(){
        for(Rayon rayon : listeRayons){
            System.out.println(">"+rayon.getNom());
            for(Article article : rayon.getListeArticles())
                System.out.println(article.getNom());
        }
    }

    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public ArrayList<Rayon> getListeRayons() {
        return listeRayons;
    }
}
