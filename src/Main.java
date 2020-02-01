import Model.Utilisateur;
import Utils.DataStorage;
import Utils.ViewLauncher;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import static Utils.Consts.APPLICATION_NAME;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewLauncher launcher = new ViewLauncher("Accueil", APPLICATION_NAME);
        launcher.launch();
    }



    public static void main(String[] args) {
        DataStorage.deserializeData();

        launch(args);
        DataStorage.serializeData();
    }
}
