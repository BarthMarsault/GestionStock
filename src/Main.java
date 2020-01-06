import Utils.ViewLauncher;
import javafx.application.Application;
import javafx.stage.Stage;

import static Utils.Consts.NOM_APPLICATION;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewLauncher launcher = new ViewLauncher("sample",NOM_APPLICATION);
        launcher.launch();
        //ligne inutile
    }



    public static void main(String[] args) {
        launch(args);
    }
}
