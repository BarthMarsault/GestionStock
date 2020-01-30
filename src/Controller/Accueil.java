package Controller;

import Utils.ViewLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import static Utils.Consts.APPLICATION_NAME;
import static Utils.Consts.USER_SESSION;

public class Accueil extends MenuBar{


    @FXML
    Label txtAvertissement;

    @FXML
    Button btnAdminUser;

    @FXML
    Button btnCreationArt;

    @FXML
    Button btnConsultationArt;

    public void initialize(){
        super.initialize();
        if(USER_SESSION == null){
            txtAvertissement.setVisible(true);
            btnCreationArt.setVisible(false);
            btnConsultationArt.setVisible(false);
            btnAdminUser.setVisible(false);

        }else if(USER_SESSION.getClass().getTypeName() == "Model.Administrateur"){
            txtAvertissement.setVisible(false);
            btnCreationArt.setVisible(true);
            btnConsultationArt.setVisible(true);
            btnAdminUser.setVisible(true);
        }else{
            txtAvertissement.setVisible(false);
            btnCreationArt.setVisible(true);
            btnConsultationArt.setVisible(true);
            btnAdminUser.setVisible(false);
        }

    }


    @FXML
    public void creationArticle(){
        ViewLauncher launcher = new ViewLauncher(bPane,"CreationArticle", APPLICATION_NAME);
        launcher.launch();
    }

    @FXML
    public void consultationArticles(){
        ViewLauncher launcher = new ViewLauncher(bPane,"ConsultationArticles", APPLICATION_NAME);
        launcher.launch();
    }


    @FXML
    public void gestionUtilisateurs(){
        ViewLauncher launcher = new ViewLauncher(bPane,"GestionUtilisateurs", APPLICATION_NAME);
        launcher.launch();
    }


}
