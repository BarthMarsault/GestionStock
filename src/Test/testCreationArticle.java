package Test;

import Controller.CreationArticle;
import Model.Article;
import Utils.DataStorage;
import Utils.FiledFormater;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.assertions.api.Assertions;

@ExtendWith(ApplicationExtension.class)
public class testCreationArticle {

    private CreationArticle creationArticle;
    private TextField nomArticleTxtField;
    private TextField refArticleTxtField;
    private TextField qteArticleTxtField;
    private ComboBox<String> rayonArticleComboBox;
    private TextArea descArticleTxtArea;
    private Label msgInformationLabel;

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
        FiledFormater.noSpecialCharacters(nomArticleTxtField);
        FiledFormater.noSpecialCharacters(refArticleTxtField);
        FiledFormater.onlyNumbers(qteArticleTxtField);
        creationArticle = new CreationArticle(refArticleTxtField, nomArticleTxtField, qteArticleTxtField, rayonArticleComboBox, descArticleTxtArea, msgInformationLabel);
    }

    @Test
    void when_all_fields_are_empty_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("");
        refArticleTxtField.setText("");
        qteArticleTxtField.setText("");
        rayonArticleComboBox.setValue("");
        creationArticle.creationArticle();
        //Then :
        //Un message d'avertissement apparaît
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été créé
        Assertions.assertThat(DataStorage.magasin.getArticleFromReference("")).isNull();
    }

    @Test
    void when_one_field_is_empty_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Chaussures");
        refArticleTxtField.setText("G463");
        qteArticleTxtField.setText("");
        rayonArticleComboBox.setValue("Sport");
        creationArticle.creationArticle();
        //Then :
        //Un message d'avertissement apparaît
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été créé
        Assertions.assertThat(DataStorage.magasin.getArticleFromReference("G463")).isNull();
    }

    @Test
    void when_trying_to_fill_quantitee_field__with_something_else_than_figures_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Chaussures");
        refArticleTxtField.setText("B685");
        qteArticleTxtField.setText("Erreur");
        rayonArticleComboBox.setValue("Sport");
        creationArticle.creationArticle();
        //Then :
        //Un message d'avertissement apparaît puisque il n'est possible de mettre que des chiffres
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été créé
        Assertions.assertThat(DataStorage.magasin.getArticleFromReference("B685")).isNull();
    }

    @Test
    void when_trying_to_fill_nom_or_reference_field_with_specials_characters_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("/+:(");
        refArticleTxtField.setText("R578");
        qteArticleTxtField.setText("21");
        rayonArticleComboBox.setValue("Sport");
        creationArticle.creationArticle();
        //Then :
        //Un message d'avertissement apparaît puisque il n'est pas possible de mettre que des caractères spéciaux
        Assertions.assertThat(msgInformationLabel).hasText("Veuillez remplir tous les champs.");
        //L'article n'a pas été créé
        Assertions.assertThat(DataStorage.magasin.getArticleFromReference("R578")).isNull();
    }

    @Test
    void when_all_fields_are_filled_article_is_created(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Chaussures");
        refArticleTxtField.setText("H876");
        qteArticleTxtField.setText("20");
        rayonArticleComboBox.setValue("Sport");
        descArticleTxtArea.setText("Chaussures de bonne qualité.");
        creationArticle.creationArticle();
        //Then :
        Article articleCreated = DataStorage.magasin.getArticleFromReference("H876");
        Assertions.assertThat(articleCreated).isNotNull();
        Assertions.assertThat(articleCreated.getNom()).isEqualTo("Chaussures");
        Assertions.assertThat(articleCreated.getReference()).isEqualTo("H876");
        Assertions.assertThat(articleCreated.getQte()).isEqualTo(20);
        Assertions.assertThat(DataStorage.magasin.getRayonFromArticle(articleCreated).getNom()).isEqualTo("Sport");
        Assertions.assertThat(articleCreated.getDescription()).isEqualTo("Chaussures de bonne qualité.");
    }

    @Test
    void when_trying_to_add_article_with_reference_already_exists_warning_appears(FxRobot robot){
        //When :
        nomArticleTxtField.setText("Mauvaises chaussures");
        refArticleTxtField.setText(DataStorage.magasin.getListeRayons().get(0).getListeArticles().get(0).getReference());
        qteArticleTxtField.setText("42");
        rayonArticleComboBox.setValue("Sport");
        creationArticle.creationArticle();
        //Then :
        //Un message d'avertissement apparaît
        Assertions.assertThat(msgInformationLabel).hasText("Référence déjà existante.");

    }

}
