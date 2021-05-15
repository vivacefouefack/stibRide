package g54490.atlg4.stib.view;

import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.presenter.Presenter;
import g54490.atlg4.stib.repository.FavoriteRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteViewController implements Initializable {

    @FXML
    private TableView<ResultData> tableView;
    @FXML
    private TableColumn<ResultData, String> stations;
    @FXML
    private TableColumn<ResultData, String> lignes;
    @FXML
    private Button quitter;
    @FXML
    private SearchableComboBox<String> searchFavaris;
    @FXML
    private Label erreurSelection;
    @FXML
    private Label confirmSuppressio;
    @FXML
    private Button Addfavorites;
    @FXML
    private SearchableComboBox<String> nouvelleOri;
    @FXML
    private SearchableComboBox<String> nouvelleDes;
    @FXML
    private Label confirmModification;
    @FXML
    private Button ok;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button consulter;
    @FXML
    private TextField newNam;

    private MenuItem itemMesfavoris;
    private FavoriteRepository myfavorites;

    /**
     * constructor of FavoriteViewController.
     *
     * @throws IOException if the myfavorites does not access the database
     */
    public FavoriteViewController() throws IOException {
        this.tableView = new TableView<>();
        this.stations = new TableColumn<>();
        this.lignes = new TableColumn<>();
        this.searchFavaris = new SearchableComboBox<>();
        this.confirmSuppressio = new Label();
        this.erreurSelection = new Label();
        this.myfavorites = new FavoriteRepository();
        this.ok = new Button();
        this.nouvelleOri = new SearchableComboBox<>();
        this.nouvelleDes = new SearchableComboBox<>();
        this.confirmModification = new Label();
        this.newNam=new TextField();
    }

    /**
     * allows to populate the table through a list of data received as a
     * parameter.
     *
     * @param data data list to add in the tableView.
     */
    public void AddResultData(List<ResultData> data) {
        if (this.tableView != null || data == null) {
            this.tableView.getItems().clear();
        }
        for (ResultData oneLine : data) {
            this.tableView.getItems().add(oneLine);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stations.setCellValueFactory(new PropertyValueFactory<ResultData, String>("nameStation"));
        this.lignes.setCellValueFactory(new PropertyValueFactory<ResultData, String>("lines"));
    }

    /**
     * initializes the list of all favorites and the list of all station names.
     *
     * @param itemsFavorite all name favorites list
     * @param items all name station list.
     */
    public void initialize(ObservableList<String> itemsFavorite, ObservableList<String> items) {
        this.searchFavaris.setItems(itemsFavorite);
        this.nouvelleOri.setItems(items);
        this.nouvelleDes.setItems(items);
        this.ok.setDisable(true);
        this.nouvelleDes.setDisable(true);
        this.nouvelleOri.setDisable(true);
        this.newNam.setDisable(true);
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonQuitter(Presenter presenter) {
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                presenter.closeFavoriteView();
            }
        });
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonOk(Presenter presenter) {
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                presenter.updateFavorite();
            }
        });
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonModifier(Presenter presenter) {
        modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                presenter.updateButton();
            }
        });
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonSupprimer(Presenter presenter) {
        supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                presenter.supprimer();
            }
        });
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonConsulter(Presenter presenter) {
        consulter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                presenter.Consulter();
            }
        });
    }

    /**
     * getter.
     *
     * @return quitter button.
     */
    public Button getQuitter() {
        return quitter;
    }

    /**
     * getter.
     *
     * @return erreurSelection label.
     */
    public Label getErreurSelection() {
        return erreurSelection;
    }

    /**
     * getter.
     *
     * @return confirmSuppressio label.
     */
    public Label getConfirmSuppressio() {
        return confirmSuppressio;
    }

    /**
     * getter.
     *
     * @return confirmModification label.
     */
    public Label getConfirmModification() {
        return confirmModification;
    }

    /**
     * getter.
     *
     * @return ok button.
     */
    public Button getOk() {
        return ok;
    }

    /**
     * getter.
     *
     * @return searchFavaris.
     */
    public SearchableComboBox<String> getSearchFavaris() {
        return searchFavaris;
    }

    /**
     * getter.
     *
     * @return nouvelleOri
     */
    public SearchableComboBox<String> getNouvelleOri() {
        return nouvelleOri;
    }

    /**
     * getter.
     *
     * @return nouvelleDes.
     */
    public SearchableComboBox<String> getNouvelleDes() {
        return nouvelleDes;
    }

    /**
     * getter.
     *
     * @return data value list of tableView.
     */
    public TableView<ResultData> getTableView() {
        return tableView;
    }

    /**
     * getter.
     * @return new namme.
     */
    public TextField getNewNam() {
        return newNam;
    }

}
