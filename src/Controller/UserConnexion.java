package Controller;

import Model.Utilisateur;
import ModelDAO.UtilisateurDAO;
import Utils.Consts;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class UserConnexion extends MenuBar {

    @FXML
    TextField txtEmail;
    @FXML
    PasswordField txtMdp;
    @FXML
    Label echecConnexion;

    /**
     * Fonction : Connecte un utilisateur à l'application
     */
    public void connexion(){

        Utilisateur utilisateur = UtilisateurDAO.findUtilisateur(txtEmail.getText());
        if(utilisateur != null) {
            if (utilisateur.getMdp().equals(txtMdp.getText())) {
                Consts.USER_SESSION = utilisateur;
                backHome();
            } else {
                echecConnexion.setText("Identifiants incorrectes");
            }
        } else {
            echecConnexion.setText("Identifiants incorrectes");
        }

    }
}
