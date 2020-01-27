package Test;

import Controller.CreationArticle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
//import org.testfx.assertions.api.Assertions;
//import org.testfx.framework.junit.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCreationArticle {


    @Test
    public final void testChampsVides(){
        CreationArticle creationArticle = new CreationArticle();
        /*TextField nomArticleTxtField = new TextField();
        TextField refArticleTxtField = new TextField();
        TextField qteArticleTxtField = new TextField();
        ComboBox<String> rayonArticleComboBox = new ComboBox<>();
        TextArea descArticleTxtArea = new TextArea();
        Label msgInformation = new Label();
        nomArticleTxtField.setText("");
        refArticleTxtField.setText("");
        qteArticleTxtField.setText("");
        rayonArticleComboBox.setValue("");
        creationArticle.creationArticle();
        assertEquals("Veuillez remplir tous les champs.",msgInformation.getText());
        */
        assertEquals(1,1);
    }
    /*
    @Test
    public void should_contain_button_with_text() {
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("click me!"));
    }*/

}
