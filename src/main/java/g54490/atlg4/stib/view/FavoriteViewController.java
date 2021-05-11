package g54490.atlg4.stib.view;

import g54490.atlg4.stib.handler.Handler;
import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.presenter.Presenter;
import g54490.atlg4.stib.repository.FavoriteRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteViewController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Label nbStation;
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
    private TextField nouveauNom;
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
//    @FXML
//    void actionOk(ActionEvent event) {
//
//        if (nouveauNom == null || nouvelleOri == null || nouvelleDes == null) {
//            this.confirmModification.setText("veuillez introduire un nom,selectionner une origine et une destination");
//        } else {
//            myfavorites.update(new FavoritesDto(nouveauNom.getText(), nouvelleOri.getValue(), nouvelleDes.getValue()));
//            this.confirmModification.setText("le favori a été mise à jour ");
//            disableButtons(true);
//        }
//   }

//    @FXML
//    void actionConsulter(ActionEvent event) throws IOException {
//        this.erreurSelection.setText("");
//        this.confirmModification.setText("");
//        if (searchFavaris.getValue() == null) {
//            this.erreurSelection.setText("? erreur veuillez selectionner un favori");
//        } else {
//            FavoritesDto dto = myfavorites.get(searchFavaris.getValue());
//           // this.AddResultData(dto.getOrigin(), dto.getDestination());
//        }
//        disableButtons(true);
//    }
//    @FXML
//    void actionModifier(ActionEvent event) {
//        this.erreurSelection.setText("");
//        this.confirmModification.setText("");
//        if (searchFavaris.getValue() == null) {
//            this.erreurSelection.setText("? erreur veuillez selectionner un favori");
//        } else {
//            disableButtons(false);
//        }
//    }

//    @FXML
//    void actionQuitter(ActionEvent event) {
//        Stage stage = (Stage) quitter.getScene().getWindow();
//        stage.close();
//    }
//    @FXML
//    void actionSupprimer(ActionEvent event) {
//        this.erreurSelection.setText("");
//        this.confirmModification.setText("");
//        if (searchFavaris.getValue() == null) {
//            this.erreurSelection.setText("? erreur veuillez selectionner un favori");
//        } else {
//            Alert dialogC = new Alert(AlertType.CONFIRMATION);
//            dialogC.setTitle("confirmation suppression");
//            dialogC.setHeaderText(null);
//            dialogC.setContentText("etes-vous sur de vouloir supprimer ce favori ?");
//            Optional<ButtonType> answer = dialogC.showAndWait();
//            if (answer.get() == ButtonType.OK) {
//                this.myfavorites.remove(searchFavaris.getValue());
//                this.confirmSuppressio.setText("le favori " + this.searchFavaris.getValue()
//                        + " a été supprimé de la liste de vos favoris");
//            } else {
//                this.confirmSuppressio.setText("Opération annulée");
//            }
//        }
//    }

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
        this.nbStation = new Label();
        this.erreurSelection = new Label();
        this.myfavorites = new FavoriteRepository();
        this.ok = new Button();
        this.nouveauNom = new TextField();
        this.nouvelleOri = new SearchableComboBox<>();
        this.nouvelleDes = new SearchableComboBox<>();
        this.confirmModification = new Label();
    }

    /**
     * 
     * @param data 
     */
    public void AddResultData(List<ResultData> data) {
        if (this.tableView != null) {
            this.tableView.getItems().clear();
        }

        for (ResultData oneLine : data) {
            this.tableView.getItems().add(oneLine);
        }
        //this.nbStation.setText("Nomnbres de stations : " + r.getNbtation());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stations.setCellValueFactory(new PropertyValueFactory<ResultData, String>("nameStation"));
        this.lignes.setCellValueFactory(new PropertyValueFactory<ResultData, String>("lines"));
//        try {
//            StationRepository stations = new StationRepository();
//            this.searchFavaris.setItems(myfavorites.getFavoritesName());
//            this.nouvelleOri.setItems(stations.getStationName());
//            this.nouvelleDes.setItems(stations.getStationName());
//        } catch (IOException ex) {
//            Logger.getLogger(FavoriteViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        disableButtons(true);
    }

    /**
     * initializes the list of all favorites and the list of all station names.
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
    }

    /** 
     * allows you to add a button to the event manager.
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButton(Presenter presenter) {
        Handler handler = new Handler(presenter);
        quitter.setOnAction(handler);
        ok.setOnAction(handler);
        modifier.setOnAction(handler);
        supprimer.setOnAction(handler);
        consulter.setOnAction(handler);
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
     * 
     * @return 
     */
    public SearchableComboBox<String> getNouvelleOri() {
        return nouvelleOri;
    }

    /**
     * 
     * @return 
     */
    public SearchableComboBox<String> getNouvelleDes() {
        return nouvelleDes;
    }
    
    

}
