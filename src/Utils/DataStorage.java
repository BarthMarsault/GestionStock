package Utils;

import Model.*;

import java.io.*;
import java.util.ArrayList;

import static Utils.Consts.*;
import static Utils.Log.logError;


/**
 * Store a Company object use to store all application datas
 * Contain serialisation and unserialisation methods
 */
public class DataStorage {

    public static Magasin magasin;




    /**
     * Serialisation method
     * Serialize the object company which stock all data use by the application in a file.
     */
    public static void serializeData() {
        try {
            FileOutputStream fos = new FileOutputStream(SERIALISATION_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Writing of the object company in the file.
            oos.writeObject(magasin);


            oos.close();
            fos.close();
        } catch (IOException e) {
            logError(e);
            e.printStackTrace();
        }



    }







    /**
     * Deserialisation method
     * Call at the start of the application to restore all data stocked in a file.
     */
    public static void deserializeData(){

        try {
            File file = new File(SERIALISATION_PATH);
            if(file.exists()) {
                FileInputStream fis = new FileInputStream(SERIALISATION_PATH);

                ObjectInputStream ois = new ObjectInputStream(fis);

                //Reading of the object stocked in the save file and casting in Company to restore data
                magasin = (Magasin) ois.readObject();

                ois.close();
                fis.close();
            }else{
                stub();
            }

        } catch (IOException e) {
            logError(e);
            System.out.println("Change on Model classes - Delete the data serialisation file");
        } catch (ClassNotFoundException e) {
            logError(e);
            e.printStackTrace();
        }


    }



    /**
     * Create data
     */
    public static void stub(){
        magasin = new Magasin();




        //Ajout de rayons
        ArrayList<Article> listeArticles = new ArrayList<>();
        listeArticles.add(new Article("A123", "Paire de chaussures", 34, "Paire de chaussures super confortable."));
        listeArticles.add(new Article("G578", "Bracelet éponge", 22, "Bracelet qui permet également de faire la vaisselle."));
        listeArticles.add(new Article("D13", "Semelle de chaussure", 34, "Moelleuse et légère, elle convient parfaitement à tout sportif."));
        Rayon rayon1 = new Rayon("Course",listeArticles);
        magasin.addRayon(rayon1);
        ArrayList<Article> listeArticles2 = new ArrayList<>();
        listeArticles2.add(new Article("U907", "Cuissard d'hiver", 34, "Cuissard de compétition."));
        listeArticles2.add(new Article("I508", "Casque bleu", 22, "Pour une protection en toute classe."));
        listeArticles2.add(new Article("F313", "Roue 20 pouces", 34, "Une roue qui se dégonfle en une utilisation."));
        Rayon rayon2 = new Rayon("Cyclotourisme",listeArticles2);
        magasin.addRayon(rayon2);

        //Ajout d'un administrateur
        magasin.addUtilisateur(new Administrateur(1,"Admin","Admin","123"));
        //Ajout d'utilisateurs
        magasin.addUtilisateur(new Utilisateur(2,"Dupont","Jean","123",rayon1));
        magasin.addUtilisateur(new Utilisateur(3,"Martin","Alexis","123", rayon2));
        magasin.addUtilisateur(new Utilisateur(4,"Morin","Clément","123", rayon1));




    }

}
