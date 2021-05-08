package g54490.atlg4.stib.view;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.model.Search;
import g54490.atlg4.stib.repository.FavoritesRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteViewController implements Initializable {

    @FXML private AnchorPane parent;
    @FXML private Label nbStation;
    @FXML private TableView<ResultData> tableView;
    @FXML private TableColumn<ResultData, String> stations;
    @FXML private TableColumn<ResultData, String> lignes;
    @FXML private Button quitter;
    @FXML private SearchableComboBox<String> searchFavaris;
    @FXML private Button supprimer;
    @FXML private Button modifier;
    @FXML private Button consulter;
    @FXML private Label erreurSelection;
    @FXML private Label confirmSuppressio;

    @FXML void actionConsulter(ActionEvent event) throws IOException {
        this.erreurSelection.setText("");
        if (searchFavaris.getValue() == null) {
            this.erreurSelection.setText("? erreur veuillez selectionner un favori");
        } else {
            FavoritesDto dto=myfavorites.get(searchFavaris.getValue());
            this.AddResultData(dto.getOrigin(), dto.getDestination());
        }

    }

    @FXML void actionModifier(ActionEvent event) {
        this.erreurSelection.setText("");
        if (searchFavaris.getValue() == null) {
            this.erreurSelection.setText("? erreur veuillez selectionner un favori");
        } else {

        }
    }

    @FXML void actionQuitter(ActionEvent event) {
        Stage stage = (Stage) quitter.getScene().getWindow();
        this.search.setDisable(false);
        this.itemMesfavoris.setDisable(false);
        stage.close();
    }

    @FXML
    void actionSupprimer(ActionEvent event) {
        this.erreurSelection.setText("");
        if (searchFavaris.getValue() == null) {
            this.erreurSelection.setText("? erreur veuillez selectionner un favori");
        } else {

        }
    }

    private Button search;
    private MenuItem itemMesfavoris;
    private FavoritesRepository myfavorites;

    public FavoriteViewController() throws IOException {
        this.tableView = new TableView<>();
        this.stations = new TableColumn<>();
        this.lignes = new TableColumn<>();
        this.searchFavaris = new SearchableComboBox<>();
        this.confirmSuppressio = new Label();
        this.nbStation = new Label();
        this.erreurSelection = new Label();
        this.myfavorites = new FavoritesRepository();

    }

    public void AddResultData(String origin, String destination) {
        if (this.tableView != null) {
            this.tableView.getItems().clear();
        }
        Search r = new Search(origin, destination);
        List<ResultData> data = r.getResultData();
        for (ResultData oneLine : data) {
            this.tableView.getItems().add(oneLine);
        }
        this.nbStation.setText("Nomnbres de stations : " + r.getNbtation());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stations.setCellValueFactory(new PropertyValueFactory<ResultData, String>("nameStation"));
        this.lignes.setCellValueFactory(new PropertyValueFactory<ResultData, String>("lines"));
        try {
            this.searchFavaris.setItems(myfavorites.getFavoritesName());
        } catch (IOException ex) {
            Logger.getLogger(FavoriteViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void disableButton(Button button, MenuItem item) {
        this.search = button;
        this.itemMesfavoris = item;
    }

}
