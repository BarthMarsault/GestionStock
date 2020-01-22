package Controller;

import Utils.ViewLauncher;
import javafx.fxml.FXML;

import static Utils.Consts.APPLICATION_NAME;

public class Accueil {


    @FXML
    public void creationArticle(){
        ViewLauncher launcher = new ViewLauncher("CreationArticle", APPLICATION_NAME);
        launcher.launch();
    }

    @FXML
    public void consultationArticles(){
        ViewLauncher launcher = new ViewLauncher("ConsultationArticles", APPLICATION_NAME);
        launcher.launch();
    }


}
