package Controller;

import Model.Article;
import Model.Rayon;
import Utils.DataStorage;
import Utils.FiledFormater;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CreationArticle {


    @FXML private TextField nomArticleTxtField;
    @FXML private TextField refArticleTxtField;
    @FXML private TextField qteArticleTxtField;
    @FXML private ComboBox<String> rayonArticleComboBox;
    @FXML private TextArea descArticleTxtArea;
    @FXML private Label msgInformation;

    public TextField getNomArticleTxtField() {
        return nomArticleTxtField;
    }

    public void setNomArticleTxtField(TextField nomArticleTxtField) {
        this.nomArticleTxtField = nomArticleTxtField;
    }

    public TextField getRefArticleTxtField() {
        return refArticleTxtField;
    }

    public void setRefArticleTxtField(TextField refArticleTxtField) {
        this.refArticleTxtField = refArticleTxtField;
    }

    public TextField getQteArticleTxtField() {
        return qteArticleTxtField;
    }

    public void setQteArticleTxtField(TextField qteArticleTxtField) {
        this.qteArticleTxtField = qteArticleTxtField;
    }

    public ComboBox<String> getRayonArticleComboBox() {
        return rayonArticleComboBox;
    }

    public void setRayonArticleComboBox(ComboBox<String> rayonArticleComboBox) {
        this.rayonArticleComboBox = rayonArticleComboBox;
    }

    public TextArea getDescArticleTxtArea() {
        return descArticleTxtArea;
    }

    public void setDescArticleTxtArea(TextArea descArticleTxtArea) {
        this.descArticleTxtArea = descArticleTxtArea;
    }

    public Label getMsgInformation() {
        return msgInformation;
    }

    public void setMsgInformation(Label msgInformation) {
        this.msgInformation = msgInformation;
    }

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
        FiledFormater.noSpecialCharacters(nomArticleTxtField);
        FiledFormater.noSpecialCharacters(refArticleTxtField);
        FiledFormater.onlyNumbers(qteArticleTxtField);
    }

    public void creationArticle(){
        if(!refArticleTxtField.getText().equals("") && !nomArticleTxtField.getText().equals("") && !qteArticleTxtField.getText().equals("") && !rayonArticleComboBox.getValue().equals("")) {
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
