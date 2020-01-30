package Test;

import Controller.ModificationArticle;
import Model.Article;
import Utils.DataStorage;
import Utils.FiledFormater;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class testModificationArticle {

    private ModificationArticle modificationArticle;
    private TextField nomArticleTxtField;
    private TextField refArticleTxtField;
    private TextField qteArticleTxtField;
    private ComboBox<String> rayonArticleComboBox;
    private TextArea descArticleTxtArea;
    private Label msgInformationLabel;
    private Button validerBtn;
    private Article avantModifArticle;
    private Article apresModifArticle;

    @Start
    public final void start(Stage stage){
        DataStorage.deserializeData();
        nomArticleTxtField = new TextField();
        refArticleTxtField = new TextField();
        qteArticleTxtField = new TextField();
        rayonArticleComboBox = new ComboBox<>();
        descArticleTxtArea = new TextArea();
        msgInformationLabel = new Label();
        msgInformationLabel.setId("msgInfoLabel");
        apresModifArticle = DataStorage.magasin.getListeRayons().get(0).getListeArticles().get(0);
        avantModifArticle = new Article(apresModifArticle);
        FiledFormater.noSpecialCharacters(nomArticleTxtField);
        FiledFormater.noSpecialCharacters(refArticleTxtField);
        FiledFormater.onlyNumbers(qteArticleTxtField);
        modificationArticle = new ModificationArticle(refArticleTxtField, nomArticleTxtField, qteArticleTxtField, rayonArticleComboBox, descArticleTxtArea, msgInformationLabel, avantModifArticle);
        validerBtn = new Button("Valider");
        validerBtn.setOnAction(actionEvent -> modificationArticle.modificationArticle());
        stage.setScene(new Scene(new StackPane(validerBtn), 100, 100));
        stage.show();
    }

    @Test
    void when_all_fields_are_empty_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("");
        refArticleTxtField.setText("");
        qteArticleTxtField.setText("");
        rayonArticleComboBox.setValue("");
        robot.clickOn(".button");
        //Then :
        //Un message d'avertissement apparaît
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été créé
        Assertions.assertThat(apresModifArticle.getNom()).isEqualTo(avantModifArticle.getNom());
    }

    @Test
    void when_one_field_is_empty_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Chaussures");
        refArticleTxtField.setText("G463");
        qteArticleTxtField.setText("");
        rayonArticleComboBox.setValue("Sport");
        robot.clickOn(".button");
        //Then :
        //Un message d'avertissement apparaît
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été créé
        Assertions.assertThat(apresModifArticle.getNom()).isEqualTo(avantModifArticle.getNom());
    }

    @Test
    void when_trying_to_fill_quantitee_field__with_something_else_than_figures_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Chaussures");
        refArticleTxtField.setText("B685");
        qteArticleTxtField.setText("Erreur");
        rayonArticleComboBox.setValue("Sport");

        robot.clickOn(".button");
        //Then :
        //Un message d'avertissement apparaît puisque il n'est possible de mettre que des chiffres
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été modifié
        Assertions.assertThat(apresModifArticle.getNom()).isEqualTo(avantModifArticle.getNom());
    }

    @Test
    void when_trying_to_fill_nom_or_reference_field_with_specials_characters_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("/+:(");
        refArticleTxtField.setText("R578");
        qteArticleTxtField.setText("21");
        rayonArticleComboBox.setValue("Sport");

        robot.clickOn(".button");
        //Then :
        //Un message d'avertissement apparaît puisque il n'est pas possible de mettre que des caractères spéciaux
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été modifié
        Assertions.assertThat(apresModifArticle.getNom()).isEqualTo(avantModifArticle.getNom());
    }

    @Test
    void when_all_fields_are_filled_article_is_modified(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Chaussures");
        refArticleTxtField.setText("H876");
        qteArticleTxtField.setText("20");
        rayonArticleComboBox.setValue("Sport");
        descArticleTxtArea.setText("Chaussures de bonne qualité.");
        robot.clickOn(".button");
        //Then :
        Assertions.assertThat(apresModifArticle.getNom()).isNotEqualTo(avantModifArticle.getNom());
        Assertions.assertThat(apresModifArticle.getNom()).isEqualTo("Chaussures");
        Assertions.assertThat(apresModifArticle.getReference()).isEqualTo("H876");
        Assertions.assertThat(apresModifArticle.getQte()).isEqualTo(20);
        Assertions.assertThat(DataStorage.magasin.getRayonFromArticle(apresModifArticle).getNom()).isEqualTo("Sport");
        Assertions.assertThat(apresModifArticle.getDescription()).isEqualTo("Chaussures de bonne qualité.");
    }

    @Test
    void when_trying_to_modify_article_with_reference_already_exists_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Mauvaises chaussures");
        refArticleTxtField.setText("D13");
        qteArticleTxtField.setText("42");
        rayonArticleComboBox.setValue("Sport");
        robot.clickOn(".button");
        //Then :
        //Un message d'avertissement apparaît
        Assertions.assertThat(msgInformationLabel).hasText("Référence déjà existante.");
        Assertions.assertThat(apresModifArticle.getNom()).isEqualTo(avantModifArticle.getNom());
    }

}

