package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLauncher {

    private Parent parent;
    private FXMLLoader fxmlLoader;
    private Stage stage;


    /**
     * Lance la vue passé en paramètre
     * @param parent Container parent
     * @param view nom de la vue voulu (dans le package View)
     * @param title Nom de l'application
     */
    public ViewLauncher(Parent parent, String view, String title) {
        this.parent = parent;
        fxmlLoader = new FXMLLoader();


        try{

            fxmlLoader.setLocation(getClass().getResource("/View/" + view + ".fxml"));
            fxmlLoader.load();


            Parent root = fxmlLoader.getRoot();
            Scene scene = new Scene(root);


            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);

            stage.setMinHeight(430);
            stage.setMinWidth(630);



            stage.setMaximized(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ViewLauncher(String view, String title) {
        this(null,view, title);
    }



    public void launch(){
        stage.show();

        if (parent != null) {
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.close();
        }
    }
}
