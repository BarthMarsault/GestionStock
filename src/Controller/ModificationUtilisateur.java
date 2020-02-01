package Controller;

import Model.Rayon;
import Model.Utilisateur;
import Utils.ViewLauncher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;


import static Utils.Consts.APPLICATION_NAME;
import static Utils.DataStorage.magasin;

public class ModificationUtilisateur {

    private Utilisateur ancienUtilisateur;

    @FXML
    BorderPane bPane;

    @FXML Label txtId;
    @FXML TextField txtNom;
    @FXML TextField txtPrenom;
    @FXML PasswordField txtMdp;
    @FXML Label msgInformation;
    @FXML Button validerBtn;
    @FXML Label txtAvertissementMdp;
    @FXML Label txtAvertissementRayon;
    @FXML Label txtAvertissementNom;
    @FXML Label txtAvertissementPrenom;
    @FXML Label labelRayon;
    @FXML ComboBox<Rayon> cbRayon;

    ObservableList<Rayon> listeRayons = FXCollections.observableArrayList();



    public void initialize(){
        msgInformation.setVisible(false);
        listeRayons = FXCollections.observableArrayList(magasin.getListeRayons());
        cbRayon.setItems(listeRayons);

    }


    public void isAdmin(){
        if(ancienUtilisateur.getClass().getTypeName().equals("Model.Administrateur")){
            cbRayon.setVisible(false);
            labelRayon.setVisible(false);
        }else{
            cbRayon.setVisible(true);
            labelRayon.setVisible(true);

            ObservableList<Rayon> listeRayons = FXCollections.observableArrayList(magasin.getListeRayons());
            cbRayon.setItems(listeRayons);
            cbRayon.getSelectionModel().select(ancienUtilisateur.getRayon());
        }
    }



    public void modificationUtilisateur(){
        Boolean result = true;

        if(ancienUtilisateur.validationNomPrenom(txtNom.getText())){

            txtAvertissementNom.setText("");
        }else{
            result =false;
            txtAvertissementNom.setText("Nom non valide");
        }


        if(ancienUtilisateur.validationNomPrenom(txtPrenom.getText())){

            txtAvertissementPrenom.setText("");
        }else{
            result =false;
            txtAvertissementPrenom.setText("Prénom non valide");
        }



        if(!ancienUtilisateur.validationMdp(txtMdp.getText())){
            txtAvertissementMdp.setText("Mot de passe non valide");
            result = false;
        }else{
            txtAvertissementMdp.setText("");
        }
        if(cbRayon.getValue() == null && ancienUtilisateur.getClass().equals("Model.Utilisateur")){
            txtAvertissementRayon.setText("Selectionner un Rayon");
            result = false;
        }else{
            txtAvertissementRayon.setText("");
        }




        if(result){
            ancienUtilisateur.setNom(txtNom.getText());
            ancienUtilisateur.setPrenom(txtPrenom.getText());
            ancienUtilisateur.setMdp(txtMdp.getText());
            ancienUtilisateur.setRayon(cbRayon.getValue());
            annuler();
        }

    }




    public void passUtilisateur(Utilisateur utilisateur){
        if(utilisateur != null) {
            ancienUtilisateur = utilisateur;
            isAdmin();
            txtId.setText(Integer.toString(utilisateur.getId()));
            txtNom.setText(utilisateur.getNom());
            txtPrenom.setText(utilisateur.getPrenom());
            txtMdp.setText(utilisateur.getMdp());
            cbRayon.getSelectionModel().select(utilisateur.getRayon());

        }
    }


    public void annuler(){
        ViewLauncher launcher = new ViewLauncher(bPane,"GestionUtilisateurs", APPLICATION_NAME);
        launcher.launch();
    }
}
