package Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;


public class FiledFormater {

    /**
     * Fonction : Formate un champ de text pour qu'il n'accepte que les chiffres
     */
    public static void onlyNumbers(TextField txtField) {
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    /**
     * Fonction : Formate un champ de text pour qu'il n'accepte que les lettres
     */
    public static void onlyLetters(TextField txtField){
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z0-9À-ÿ*")) {
                txtField.setText(newValue.replaceAll("[^\\sa-zA-Z0-9À-ÿ]", ""));
            }
        });
    }

    /**
     * Fonction : Formate un champ de text pour qu'il n'accepte aucun caratères spéciaux
     */
    public static void noSpecialCharacters(TextField txtField){
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\s-a-zA-Z0-9À-ÿ")) {
                txtField.setText(newValue.replaceAll("[^\\s-a-zA-Z0-9À-ÿ]", ""));
            }
        });
    }

}
