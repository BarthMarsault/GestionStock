package Controller;

import Model.Article;
import Model.Utilisateur;
import Utils.DataStorage;
import Utils.ViewLauncher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import static Utils.Consts.APPLICATION_NAME;
import static Utils.Consts.USER_SESSION;
import static Utils.DataStorage.magasin;


public class GestionUtilisateurs extends MenuBar{

    @FXML private TableView<Utilisateur> tblGestionUtilisateurs;
    @FXML private TableColumn<Utilisateur,String> colPrenomUtilisateur;
    @FXML private TableColumn<Utilisateur,String> colNomUtilisateur;
    @FXML private TableColumn<Utilisateur,Void> colModifUtilisateur;
    @FXML private TableColumn<Utilisateur,Void> colSupprUtilisateur;


    ObservableList<Utilisateur> data = FXCollections.observableArrayList();


    public void initialize(){
        super.initialize();
        data = FXCollections.observableArrayList(magasin.getListeUtilisateurs());
        colPrenomUtilisateur.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPrenom()));
        colNomUtilisateur.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNom()));



        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> modifCellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {
            @Override
            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {

                    private final Button btnModification = new Button("Modifier");
                    {
                        btnModification.setOnAction((ActionEvent event) -> {
                            Utilisateur utilisateur = getTableView().getItems().get(getIndex());
                            ViewLauncher launcher = new ViewLauncher(bPane,"ModificationUtilisateur", APPLICATION_NAME);
                            ModificationUtilisateur modificationUtilisateur = (ModificationUtilisateur) launcher.getAController();
                            modificationUtilisateur.passUtilisateur(utilisateur);
                            launcher.launch();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnModification);
                        }
                    }
                };
                return cell;
            }
        };
        colModifUtilisateur.setCellFactory(modifCellFactory);


        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> suppressionCellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {
            @Override
            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {

                    private final Button suppressionBtn = new Button("Supprimer");
                    {
                        suppressionBtn.setOnAction((ActionEvent event) -> {
                            Utilisateur utilisateur = getTableView().getItems().get(getIndex());
                            if(utilisateur.getId() != USER_SESSION.getId()){
                                DataStorage.magasin.deleteUtilisateur(utilisateur);
                            }
                            initialize();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(suppressionBtn);
                        }
                    }
                };
                return cell;
            }
        };
        colSupprUtilisateur.setCellFactory(suppressionCellFactory);


        tblGestionUtilisateurs.setItems(data);
    }


    public void creationUtilisateur(){
        ViewLauncher launcher = new ViewLauncher(bPane,"CreationUtilisateur", APPLICATION_NAME);
        launcher.launch();
    }


}
