package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserCreation extends MenuBar{

    @FXML
    TextField MdpTxtField;
    @FXML
    TextField MailTxtField;
    @FXML
    TextField PrenomTxtField;
    @FXML
    TextField NomTxtField;

    @FXML
    Label echecCreation;

    /**
     * Fonction : Création d'un utilisateur dans l'application
     */
    public void creation() {
        int succeed = 0;
        echecCreation.setText(" ");
        if (NomTxtField.getText().equals(""))
            System.out.println("Valeur : "+NomTxtField.getText());

        if (!NomTxtField.getText().equals("") && !PrenomTxtField.getText().equals("") && (!MailTxtField.getText().equals("") && MailTxtField.getText().indexOf("@") > 0  && MailTxtField.getText().indexOf(".") > 0 ) && !MdpTxtField.getText().equals(""))
        {
            //succeed = UtilisateurDAO.createUtilisateur(NomTxtField.getText(), PrenomTxtField.getText(), MailTxtField.getText(), MdpTxtField.getText());
            if(succeed != 1)
                echecCreation.setText("Impossible de créer le compte");
            else backHome();
        }
        else echecCreation.setText("Les champs sont incomplets");

    }



}
