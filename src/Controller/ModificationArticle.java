package Controller;

import Model.Article;
import Model.Rayon;
import Utils.DataStorage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ModificationArticle {

    @FXML
    private TextField nomArticleTxtField;
    @FXML
    private TextField refArticleTxtField;
    @FXML
    private TextField qteArticleTxtField;
    @FXML
    private ComboBox<String> rayonArticleComboBox;
    @FXML
    private TextArea descArticleTxtArea;

    @FXML
    private Label msgInformation;

    private Article ancienArticle;

    @FXML
    public void initialize(){

        ArrayList<Rayon> listeRayon = DataStorage.magasin.getListeRayons();
        ArrayList<String> listeNomRayon = new ArrayList<>();
        for(Rayon rayon : listeRayon){
            listeNomRayon.add(rayon.getNom());
        }
        rayonArticleComboBox.getItems().addAll(listeNomRayon);
        msgInformation.setVisible(false);
        //Ajouter regex

    }

    public void setArticle(Article article){
        if(article != null){
            ancienArticle = article;
            nomArticleTxtField.setText(article.getNom());
            refArticleTxtField.setText(article.getReference());
            qteArticleTxtField.setText(Integer.toString(article.getQte()));
            descArticleTxtArea.setText(article.getDescription());

            rayonArticleComboBox.setValue(DataStorage.magasin.getRayonFromArticle(article).getNom());

        }
        else
            System.out.println("Pas bon");
    }

    public void modificationArticle(){

        System.out.println(rayonArticleComboBox.getValue());
        if(!nomArticleTxtField.getText().equals("") && !refArticleTxtField.getText().equals("") && !qteArticleTxtField.getText().equals("") && rayonArticleComboBox.getValue() != null){
            for (Rayon rayon : DataStorage.magasin.getListeRayons()) {
                if (rayon.getNom().equals(rayonArticleComboBox.getValue())) {
                    rayon.updateArticle(ancienArticle, new Article(refArticleTxtField.getText(),nomArticleTxtField.getText(), Integer.parseInt(qteArticleTxtField.getText()), descArticleTxtArea.getText()));
                    msgInformation.setVisible(true);
                    msgInformation.setTextFill(Color.web("#63a32e"));
                    msgInformation.setText("Article modifié");
                }
            }
        }
        else{
            msgInformation.setVisible(true);
            msgInformation.setTextFill(Color.web("#ce0a0a"));
            msgInformation.setText("Veuillez remplir tous les champs.");
        }
    }
}
