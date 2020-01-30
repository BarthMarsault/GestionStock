package Controller;

import Model.Article;
import Model.Utilisateur;
import Utils.DataStorage;
import Utils.FiledFormater;
import Utils.ViewLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import static Utils.Consts.APPLICATION_NAME;

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

    public void initialize(){

        msgInformation.setVisible(false);
    }




    public void passArticle(Utilisateur utilisateur){
        if(utilisateur != null) {
            ancienUtilisateur = utilisateur;
            txtId.setText(Integer.toString(utilisateur.getId()));
            txtNom.setText(utilisateur.getNom());
            txtPrenom.setText(utilisateur.getPrenom());
            txtMdp.setText(utilisateur.getMdp());
        }
    }


    public void annuler(){
        ViewLauncher launcher = new ViewLauncher(bPane,"GestionUtilisateurs", APPLICATION_NAME);
        launcher.launch();
    }
}
