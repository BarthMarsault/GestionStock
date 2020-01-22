package Controller;

import Model.Article;
import Model.Rayon;
import Utils.DataStorage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CreationArticle {


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

    @FXML
    public void initialize(){

        rayonArticleComboBox.getItems().clear();

        ArrayList<Rayon> listeRayon = DataStorage.magasin.getListeRayons();
        ArrayList<String> listeNomRayon = new ArrayList<>();
        for(Rayon rayon : listeRayon){
            listeNomRayon.add(rayon.getNom());
        }
        rayonArticleComboBox.getItems().addAll(listeNomRayon);
        msgInformation.setVisible(false);
        //Ajouter regex
    }

    public void creationArticle(){
        if(!refArticleTxtField.getText().equals("") && !nomArticleTxtField.getText().equals("") && !qteArticleTxtField.getText().equals("") && !rayonArticleComboBox.getValue().equals("")){
            for (Rayon rayon : DataStorage.magasin.getListeRayons()) {
                if (rayon.getNom().equals(rayonArticleComboBox.getValue())) {
                    rayon.addArticle(new Article(refArticleTxtField.getText(),nomArticleTxtField.getText(), Integer.parseInt(qteArticleTxtField.getText()), descArticleTxtArea.getText()));
                    msgInformation.setVisible(true);
                    msgInformation.setTextFill(Color.web("#63a32e"));
                    msgInformation.setText("Article créé");
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
