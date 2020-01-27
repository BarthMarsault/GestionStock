package Controller;

import Model.Article;
import Model.Rayon;
import Utils.DataStorage;
import Utils.ViewLauncher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import java.util.ArrayList;

import static Utils.Consts.APPLICATION_NAME;

public class ConsultationArticles {

    @FXML private AnchorPane consultationAnchor;
    @FXML private TableView<Article> articleTableView;
    @FXML private TableColumn<Article, String> refArticleColumn;
    @FXML private TableColumn<Article, String> nomArticleColumn;
    @FXML private TableColumn<Article, String> qteArticleColumn;
    @FXML private TableColumn<Article, String> rayonArticleColumn;
    @FXML private TableColumn<Article, Void> detailsArticleColumn;
    @FXML private TableColumn<Article, Void> modificationArticleColumn;
    @FXML private TableColumn<Article, Void> suppressionArticleColumn;

    ObservableList<Article> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        ArrayList<Article> listeArticles = new ArrayList<>();
        for(Rayon rayon : DataStorage.magasin.getListeRayons()){
            listeArticles.addAll(rayon.getListeArticles());
        }
        data = FXCollections.observableList(listeArticles);//Faire selon l'utilisateur (prendre que les articles du rayon auquel il est affecté)
        refArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getReference()));
        nomArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNom()));
        qteArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(Integer.toString(cellData.getValue().getQte())));
        rayonArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(DataStorage.magasin.getRayonFromArticle(cellData.getValue()).getNom()));

        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> detailsCellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button detailsBtn = new Button("Détails");
                    private final Button modificationBtn = new Button("Modifier");
                    private final Button suppressionBtn = new Button("Supprimer");
                    {
                        detailsBtn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            ViewLauncher launcher = new ViewLauncher(consultationAnchor,"DetailsArticle", APPLICATION_NAME);
                            DetailsArticle detailsArticle = (DetailsArticle) launcher.getAController();
                            detailsArticle.passArticle(article);
                            launcher.launch();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(detailsBtn);
                        }
                    }
                };
                return cell;
            }
        };

        detailsArticleColumn.setCellFactory(detailsCellFactory);

        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> modificationCellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button modificationBtn = new Button("Modifier");
                    {
                        modificationBtn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            ViewLauncher launcher = new ViewLauncher(consultationAnchor,"ModificationArticle", APPLICATION_NAME);
                            ModificationArticle modificationArticle = (ModificationArticle) launcher.getAController();
                            modificationArticle.passArticle(article);
                            launcher.launch();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(modificationBtn);
                        }
                    }
                };
                return cell;
            }
        };
        modificationArticleColumn.setCellFactory(modificationCellFactory);

        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> suppressionCellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button suppressionBtn = new Button("Supprimer");
                    {
                        suppressionBtn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            DataStorage.magasin.getRayonFromArticle(article).deleteArticle(article);
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
        suppressionArticleColumn.setCellFactory(suppressionCellFactory);

        articleTableView.setItems(data);

    }
}
