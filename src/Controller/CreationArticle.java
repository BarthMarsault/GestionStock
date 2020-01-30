package Controller;

import Model.Article;
import Model.Rayon;
import Utils.Consts;
import Utils.DataStorage;
import Utils.FiledFormater;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CreationArticle extends MenuBar{


    @FXML private TextField nomArticleTxtField;
    @FXML private TextField refArticleTxtField;
    @FXML private TextField qteArticleTxtField;
    @FXML private ComboBox<String> rayonArticleComboBox;
    @FXML private TextArea descArticleTxtArea;
    @FXML private Label msgInformation;

    public CreationArticle(){
        this.refArticleTxtField = null;
        this.nomArticleTxtField = null;
        this.qteArticleTxtField = null;
        this.rayonArticleComboBox = null;
        this.descArticleTxtArea = null;
        this.msgInformation = null;
    }

    public CreationArticle(TextField refArticleTxtField, TextField nomArticleTxtField, TextField qteArticleTxtField, ComboBox<String> rayonArticleComboBox, TextArea descArticleTxtArea, Label msgInformation) {
        this.refArticleTxtField = refArticleTxtField;
        this.nomArticleTxtField = nomArticleTxtField;
        this.qteArticleTxtField = qteArticleTxtField;
        this.rayonArticleComboBox = rayonArticleComboBox;
        this.descArticleTxtArea = descArticleTxtArea;
        this.msgInformation = msgInformation;
    }

    @FXML
    public void initialize(){
        super.initialize();
        rayonArticleComboBox.getItems().clear();
        ArrayList<Rayon> listeRayon = DataStorage.magasin.getListeRayons();
        ArrayList<String> listeNomRayon = new ArrayList<>();
        for(Rayon rayon : listeRayon){
            if(Consts.USER_SESSION.getRayon().equals(rayon))
                listeNomRayon.add(rayon.getNom());
        }

        rayonArticleComboBox.getItems().addAll(listeNomRayon);
        msgInformation.setVisible(false);
        FiledFormater.noSpecialCharacters(nomArticleTxtField);
        FiledFormater.noSpecialCharacters(refArticleTxtField);
        FiledFormater.onlyNumbers(qteArticleTxtField);
    }

    public void creationArticle(){
        if(!refArticleTxtField.getText().equals("") && !nomArticleTxtField.getText().equals("") && !qteArticleTxtField.getText().equals("") && rayonArticleComboBox.getValue() != null) {
            if (DataStorage.magasin.getArticleFromReference(refArticleTxtField.getText()) == null){
                for (Rayon rayon : DataStorage.magasin.getListeRayons()) {
                    if (rayon.getNom().equals(rayonArticleComboBox.getValue())) {
                        rayon.addArticle(new Article(refArticleTxtField.getText(), nomArticleTxtField.getText(), Integer.parseInt(qteArticleTxtField.getText()), descArticleTxtArea.getText()));
                        msgInformation.setVisible(true);
                        msgInformation.setTextFill(Color.web("#63a32e"));
                        msgInformation.setText("Article créé");
                    }
                }
            }
            else{
                msgInformation.setVisible(true);
                msgInformation.setTextFill(Color.web("#ce0a0a"));
                msgInformation.setText("Référence déjà existante.");
            }
        }
        else{
            msgInformation.setVisible(true);
            msgInformation.setTextFill(Color.web("#ce0a0a"));
            msgInformation.setText("Veuillez remplir tous les champs.");
        }
    }
}
