package Controller;

import Model.Utilisateur;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import static Utils.Consts.APPLICATION_NAME;
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
        System.out.println(magasin.getListeUtilisateurs().size());
        data = FXCollections.observableArrayList(magasin.getListeUtilisateurs());
        colPrenomUtilisateur.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPrenom()));
        colNomUtilisateur.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNom()));



//        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> modifCellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
//            @Override
//            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
//                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {
//
//                    private final Button btnModification = new Button("Modifier");
//                    {
//                        btnModification.setOnAction((ActionEvent event) -> {
//                            Utilisateur utilisateur = getTableView().getItems().get(getIndex());
//                            ViewLauncher launcher = new ViewLauncher(bPane,"DetailsArticle", APPLICATION_NAME);
//                            ModificationUtilisateur modificationUtilisateur = (ModificationUtilisateur) launcher.getAController();
//                            modificationUtilisateur.passArticle(utilisateur);
//                            launcher.launch();
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(detailsBtn);
//                        }
//                    }
//                };
//                return cell;
//            }
//
//
//        };



        tblGestionUtilisateurs.setItems(data);
    }


}
