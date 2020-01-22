package Controller;

import Model.Article;
import Utils.DataStorage;
import Utils.ViewLauncher;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static Utils.Consts.APPLICATION_NAME;

public class DetailsArticle{

    private Article article;

    @FXML
    private TextField nomArticleTxtField;
    @FXML
    private TextField refArticleTxtField;
    @FXML
    private TextField qteArticleTxtField;
    @FXML
    private TextField rayonArticleTxtField;
    @FXML
    private TextArea descArticleTxtArea;

    @FXML
    public void initialize(){
    }

    public void setArticle(Article article){
        System.out.println("Article passé :"+ article);
        if(article != null){
            nomArticleTxtField.setText(article.getNom());
            refArticleTxtField.setText(article.getReference());
            qteArticleTxtField.setText(Integer.toString(article.getQte()));
            rayonArticleTxtField.setText(DataStorage.magasin.getRayonFromArticle(article).getNom());
            descArticleTxtArea.setText(article.getDescription());
        }
        else
            System.out.println("Pas bon");
    }
}
