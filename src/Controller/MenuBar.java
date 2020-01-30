package Controller;

import Utils.ViewLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.awt.*;

import static Utils.Consts.APPLICATION_NAME;
import static Utils.Consts.USER_SESSION;


/**
 * Classe regrouppant les appels de fonction de la MenuBar de l'application
 */
public class MenuBar {

    @FXML
    protected BorderPane bPane;

    @FXML
    Button btnConnect;

    @FXML
    Label txtUser;





    public void initialize(){
        if(USER_SESSION == null){
            btnConnect.setText("Connexion");
            txtUser.setText("");
        }else{
            btnConnect.setText("Deconnexion");
            txtUser.setText(USER_SESSION.toString());
        }
    }



    /**
     * Fonction : Permet de retourner sur la page principale de l'application
     * rootLayout
     */
    public void backHome(){
        ViewLauncher launcher = new ViewLauncher(bPane,"Accueil",APPLICATION_NAME);
        launcher.launch();
    }

    public void connect(){
        if(USER_SESSION == null){
            ViewLauncher launcher = new ViewLauncher(bPane,"UserConnexion",APPLICATION_NAME);
            launcher.launch();
        }else{
            USER_SESSION = null;
            backHome();
        }
    }


}
