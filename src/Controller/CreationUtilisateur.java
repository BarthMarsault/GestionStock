package Controller;

import Model.Administrateur;
import Model.Rayon;
import Model.Utilisateur;
import Utils.ViewLauncher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static Utils.Consts.APPLICATION_NAME;
import static Utils.DataStorage.magasin;

public class CreationUtilisateur extends MenuBar {

    @FXML TextField txtNom;
    @FXML TextField txtPrenom;
    @FXML PasswordField txtMdp;
    @FXML Label msgInformation;
    @FXML Button validerBtn;
    @FXML CheckBox ckbAdmin;
    @FXML Label txtAvertissementMdp;
    @FXML Label labelRayon;
    @FXML ComboBox<Rayon> cbRayon;

    ObservableList<Rayon> listeRayons = FXCollections.observableArrayList();



    public void initialize(){
        listeRayons = FXCollections.observableArrayList(magasin.getListeRayons());
        cbRayon.setItems(listeRayons);


    }




    public boolean creationUtilisateur(){
//        Utilisateur user;
//        if(ckbAdmin.isSelected()){
//            user = new Administrateur();
//        }else{
//            user = new Utilisateur();
//        }
//
//        user.setNom(txtNom.getText());
//        user.setPrenom(txtPrenom.getText());
//        if(user.validationMdp(txtMdp.getText())){
//            txtAvertissementMdp.setText("Mot de passe non valide");
//            return false;
//        }else{
//            txtAvertissementMdp.setText("");
//        }
//        if(cbRayon.getValue().equals(null) && !ckbAdmin.isSelected()){
//            return false;
//        }
//        user.setRayon(cbRayon.getValue());
//
//
//        magasin.addUtilisateur(user);

        return true;
    }


    public void checkAdmin(){
        if(ckbAdmin.isSelected()){
            labelRayon.setVisible(false);
            cbRayon.setItems(null);
            cbRayon.setVisible(false);

        }else{
            listeRayons = FXCollections.observableArrayList(magasin.getListeRayons());
            cbRayon.setItems(listeRayons);
            labelRayon.setVisible(true);
            cbRayon.setVisible(true);

        }
    }

    public void annuler(){
        ViewLauncher launcher = new ViewLauncher(bPane,"GestionUtilisateurs", APPLICATION_NAME);
        launcher.launch();
    }
}
