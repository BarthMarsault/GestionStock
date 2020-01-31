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
    @FXML Label txtAvertissementRayon;
    @FXML Label txtAvertissementNom;
    @FXML Label txtAvertissementPrenom;
    @FXML Label labelRayon;
    @FXML ComboBox<Rayon> cbRayon;

    ObservableList<Rayon> listeRayons = FXCollections.observableArrayList();



    public void initialize(){
        listeRayons = FXCollections.observableArrayList(magasin.getListeRayons());
        cbRayon.setItems(listeRayons);


    }




    public void creationUtilisateur(){
        Boolean result = true;
        Utilisateur user;
        if(ckbAdmin.isSelected()){
            user = new Administrateur();
        }else{
            user = new Utilisateur();
        }

        if(user.validationNomPrenom(txtNom.getText())){
            user.setNom(txtNom.getText());
            txtAvertissementNom.setText("");
        }else{
            result =false;
            txtAvertissementNom.setText("Nom non valide");
        }


        if(user.validationNomPrenom(txtPrenom.getText())){
            user.setPrenom(txtPrenom.getText());
            txtAvertissementPrenom.setText("");
        }else{
            result =false;
            txtAvertissementPrenom.setText("Prénom non valide");
        }



        if(!user.validationMdp(txtMdp.getText())){
            txtAvertissementMdp.setText("Mot de passe non valide");
            result = false;
        }else{
            user.setMdp(txtMdp.getText());
            txtAvertissementMdp.setText("");
        }
        if(cbRayon.getValue() == null && !ckbAdmin.isSelected()){
            txtAvertissementRayon.setText("Selectionner un Rayon");
            result = false;
        }else{
            user.setRayon(cbRayon.getValue());
            txtAvertissementRayon.setText("");
        }



        if(result){
            magasin.addUtilisateur(user);
            annuler();
        }

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
