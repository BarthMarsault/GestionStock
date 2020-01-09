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


    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public ArrayList<Rayon> getListeRayons() {
        return listeRayons;
    }
}
