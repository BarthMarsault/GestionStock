package Controller;

import Model.Article;
import Utils.DataStorage;
import Utils.ViewLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import static Utils.Consts.APPLICATION_NAME;

public class DetailsArticle{

    @FXML private TextField nomArticleTxtField;
    @FXML private TextField refArticleTxtField;
    @FXML private TextField qteArticleTxtField;
    @FXML private TextField rayonArticleTxtField;
    @FXML private TextArea descArticleTxtArea;
    @FXML private AnchorPane aPane;

    @FXML
    public void initialize(){
    }

    public void passArticle(Article article){
        if(article != null){
            nomArticleTxtField.setText(article.getNom());
            refArticleTxtField.setText(article.getReference());
            qteArticleTxtField.setText(Integer.toString(article.getQte()));
            rayonArticleTxtField.setText(DataStorage.magasin.getRayonFromArticle(article).getNom());
            descArticleTxtArea.setText(article.getDescription());
        }
    }

    public void retour(){
        ViewLauncher launcher = new ViewLauncher(aPane,"ConsultationArticles",APPLICATION_NAME);
        launcher.launch();
    }
}
