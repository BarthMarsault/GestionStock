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
        ViewLauncher launcher = new ViewLauncher("sample", APPLICATION_NAME);
        launcher.launch();
    }



    public static void main(String[] args) {
        DataStorage.deserializeData();

        ArrayList<Utilisateur> u = DataStorage.magasin.getListeUtilisateurs();
        for(Utilisateur ut : u){
            System.out.println(ut.toString());
        }

        launch(args);
        DataStorage.serializeData();
    }
}
