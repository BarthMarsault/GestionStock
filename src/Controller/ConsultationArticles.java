package Controller;

import Model.Article;
import Model.Rayon;
import Utils.DataStorage;
import Utils.ViewLauncher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.util.ArrayList;

import static Utils.Consts.APPLICATION_NAME;
import static Utils.Consts.USER_SESSION;

public class ConsultationArticles extends MenuBar {


    @FXML private TableView<Article> articleTableView;
    @FXML private TableColumn<Article, String> refArticleColumn;
    @FXML private TableColumn<Article, String> nomArticleColumn;
    @FXML private TableColumn<Article, Void> qteArticleColumn;
    @FXML private TableColumn<Article, String> rayonArticleColumn;
    @FXML private TableColumn<Article, Void> detailsArticleColumn;
    @FXML private TableColumn<Article, Void> modificationArticleColumn;
    @FXML private TableColumn<Article, Void> suppressionArticleColumn;
    @FXML private TableColumn<Article, Void> modifQteArticleColumn;

    ObservableList<Article> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        super.initialize();
        ArrayList<Article> listeArticles = new ArrayList<>();
        for(Rayon rayon : DataStorage.magasin.getListeRayons()){
                listeArticles.addAll(rayon.getListeArticles());
        }
        data = FXCollections.observableList(listeArticles);
        refArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getReference()));
        nomArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNom()));
        //qteArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(Integer.toString(cellData.getValue().getQte())));
        rayonArticleColumn.setCellValueFactory(cellData->new SimpleStringProperty(DataStorage.magasin.getRayonFromArticle(cellData.getValue()).getNom()));


        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> qteCellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button moinsBtn = new Button("-");
                    private final Button plusBtn = new Button("+");

                    {
                        moinsBtn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            int qte = DataStorage.magasin.getArticleFromReference(article.getReference()).getQte();
                            if(qte > 0) {
                                DataStorage.magasin.getArticleFromReference(article.getReference()).setQte(qte - 1);
                                initialize();
                            }
                        });

                        plusBtn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            int qte = DataStorage.magasin.getArticleFromReference(article.getReference()).getQte();
                            DataStorage.magasin.getArticleFromReference(article.getReference()).setQte(qte + 1);
                                initialize();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Label qteLabel = new Label(" "+Integer.toString(getTableView().getItems().get(getIndex()).getQte())+ " ");
                            HBox hBox = new HBox(qteLabel);
                            if (DataStorage.magasin.getRayonFromArticle(getTableView().getItems().get(getIndex())).equals(USER_SESSION.getRayon()) || USER_SESSION.getClass().getTypeName().equals("Model.Administrateur")){
                                hBox.getChildren().clear();
                                hBox.getChildren().addAll(moinsBtn, qteLabel, plusBtn);
                            }
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
        qteArticleColumn.setCellFactory(qteCellFactory);

        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> detailsCellFactory = new Callback<>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<>() {

                    private final Button detailsBtn = new Button("Détails");
                    {
                        detailsBtn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            ViewLauncher launcher = new ViewLauncher(bPane,"DetailsArticle", APPLICATION_NAME);
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
                            ViewLauncher launcher = new ViewLauncher(bPane,"ModificationArticle", APPLICATION_NAME);
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
                            if(USER_SESSION.getClass().getTypeName().equals("Model.Administrateur"))
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
                            if(DataStorage.magasin.getRayonFromArticle(getTableView().getItems().get(getIndex())).equals(USER_SESSION.getRayon()) || USER_SESSION.getClass().getTypeName().equals("Model.Administrateur"))
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
