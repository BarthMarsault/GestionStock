package Controller;

import Model.Utilisateur;
import Utils.Consts;
import Utils.FiledFormater;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static Utils.DataStorage.magasin;


public class UserConnexion extends MenuBar {

    @FXML
    TextField txtId;
    @FXML
    PasswordField txtMdp;
    @FXML
    Label echecConnexion;


    public void initialize(){
        FiledFormater.onlyNumbers(txtId);
    }

    /**
     * Fonction : Connecte un utilisateur à l'application
     */
    public void connexion(){


        Utilisateur utilisateur = null;
        if(!txtId.getText().equals("")){
            utilisateur = magasin.getUtilisateurById(Integer.parseInt(txtId.getText()));
        }


        if(utilisateur != null) {
            if (utilisateur.getMdp().equals(txtMdp.getText())) {
                Consts.USER_SESSION = utilisateur;
                backHome();
            } else {
                echecConnexion.setText("Identifiants invalides");
            }
        } else {
            echecConnexion.setText("Identifiants invalides");
        }

    }
}
