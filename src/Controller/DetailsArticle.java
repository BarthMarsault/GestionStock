package Controller;

import Model.Article;
import Utils.DataStorage;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailsArticle{

    @FXML private TextField nomArticleTxtField;
    @FXML private TextField refArticleTxtField;
    @FXML private TextField qteArticleTxtField;
    @FXML private TextField rayonArticleTxtField;
    @FXML private TextArea descArticleTxtArea;

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
}
