package Model;

import java.io.Serializable;

public class Administrateur extends Utilisateur implements Serializable {


    public Administrateur(int id, String nom, String prenom, String mdp) {
        super(id, nom, prenom, mdp, null);
    }

    public Administrateur(String nom, String prenom, String mdp) {
        super(nom, prenom, mdp, null);
    }

    public Administrateur() {
        super();
        this.setRayon(null);
    }
}
