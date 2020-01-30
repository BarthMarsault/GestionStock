package Controller;

import Model.Article;
import Model.Rayon;
import Utils.DataStorage;
import Utils.FiledFormater;
import Utils.ViewLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import static Utils.Consts.APPLICATION_NAME;

public class ModificationArticle {

    @FXML private TextField nomArticleTxtField;
    @FXML private TextField refArticleTxtField;
    @FXML private TextField qteArticleTxtField;
    @FXML private ComboBox<String> rayonArticleComboBox;
    @FXML private TextArea descArticleTxtArea;
    @FXML private Button validerBtn;
    @FXML private Label msgInformation;

    private Article ancienArticle;

    public ModificationArticle(){
        this.refArticleTxtField = null;
        this.nomArticleTxtField = null;
        this.qteArticleTxtField = null;
        this.rayonArticleComboBox = null;
        this.descArticleTxtArea = null;
        this.msgInformation = null;
        this.ancienArticle = null;
    }

    public ModificationArticle(TextField refArticleTxtField, TextField nomArticleTxtField, TextField qteArticleTxtField, ComboBox<String> rayonArticleComboBox, TextArea descArticleTxtArea, Label msgInformation, Article article){
        this.refArticleTxtField = refArticleTxtField;
        this.nomArticleTxtField = nomArticleTxtField;
        this.qteArticleTxtField = qteArticleTxtField;
        this.rayonArticleComboBox = rayonArticleComboBox;
        this.descArticleTxtArea = descArticleTxtArea;
        this.msgInformation = msgInformation;
        ancienArticle = article;
    }

    @FXML
    public void initialize(){

        ArrayList<Rayon> listeRayon = DataStorage.magasin.getListeRayons();
        ArrayList<String> listeNomRayon = new ArrayList<>();
        for(Rayon rayon : listeRayon){
            listeNomRayon.add(rayon.getNom());
        }
        rayonArticleComboBox.getItems().addAll(listeNomRayon);
        msgInformation.setVisible(false);

    }

    public void passArticle(Article article){
        if(article != null) {
            ancienArticle = article;
            nomArticleTxtField.setText(article.getNom());
            refArticleTxtField.setText(article.getReference());
            qteArticleTxtField.setText(Integer.toString(article.getQte()));
            descArticleTxtArea.setText(article.getDescription());
            rayonArticleComboBox.setValue(DataStorage.magasin.getRayonFromArticle(article).getNom());
            FiledFormater.noSpecialCharacters(nomArticleTxtField);
            FiledFormater.noSpecialCharacters(refArticleTxtField);
            FiledFormater.onlyNumbers(qteArticleTxtField);
        }
    }

    public void modificationArticle(){
        if(!nomArticleTxtField.getText().equals("") && !refArticleTxtField.getText().equals("") && !qteArticleTxtField.getText().equals("") && rayonArticleComboBox.getValue() != null){
            if (ancienArticle.getReference().equals(refArticleTxtField.getText()) || DataStorage.magasin.getArticleFromReference(refArticleTxtField.getText()) == null) {
                Rayon rayon = DataStorage.magasin.getRayonFromArticle(ancienArticle);
                if (rayon != null){
                    rayon.updateArticle(ancienArticle, new Article(refArticleTxtField.getText(), nomArticleTxtField.getText(), Integer.parseInt(qteArticleTxtField.getText()), descArticleTxtArea.getText()));
                    Stage stage = (Stage) validerBtn.getScene().getWindow();
                    stage.close();
                    ViewLauncher launcher = new ViewLauncher("ConsultationArticles",APPLICATION_NAME);
                    launcher.launch();
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
