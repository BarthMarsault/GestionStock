package Utils;

import Model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


        magasin.addUtilisateur(new Utilisateur(1,"Dupont","Jean","123"));
        magasin.addUtilisateur(new Utilisateur(2,"Duper","Je suis","123"));

        ArrayList<Article> listeArticles = new ArrayList<>();
        listeArticles.add(new Article("A123", "Paire de chaussures", 34, "Paire de chaussures super confortable."));
        listeArticles.add(new Article("G578", "Canne à pêche", 22, "Canne à pêche de compétition."));
        listeArticles.add(new Article("D13", "Tapis de yoga", 34, "Moelleux et léger il convient parfaitement à tout sportif."));

        magasin.addRayon(new Rayon("Sport",listeArticles));
    }



}
