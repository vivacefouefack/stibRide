package g54490.atlg4.stib.view;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.repository.favoritesRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class FavoriteViewController implements Initializable{

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

    @FXML void actionConsulter(ActionEvent event) {

    }

    @FXML void actionModifier(ActionEvent event) {

    }

    @FXML void actionQuitter(ActionEvent event) {
        Stage stage = (Stage) quitter.getScene().getWindow();
        //search.setDisable(false);
        stage.close();
    }

    @FXML void actionSupprimer(ActionEvent event) {

    }

    public FavoriteViewController() throws IOException {
        this.tableView=new TableView<>();
        this.stations=new TableColumn<>();
        this.lignes=new TableColumn<>();
        this.searchFavaris=new SearchableComboBox<>();
        this.confirmSuppressio=new Label();
        this.nbStation=new Label();
        this.erreurSelection=new Label();
        //AddData();
    }
    
    

    public void AddData() throws IOException{
        favoritesRepository favorites=new favoritesRepository();
        List<FavoritesDto> myfavori=favorites.getAll();
        ObservableList<String> searchList=FXCollections.observableArrayList();
        for (FavoritesDto onefavori : myfavori) {
            searchList.add( onefavori.getKey());
        }
        
        //this.searchFavaris.setItems(searchList);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stations.setCellValueFactory(new PropertyValueFactory<ResultData, String>("nameStation"));
        this.lignes.setCellValueFactory(new PropertyValueFactory<ResultData, String>("lines"));
        
    }
    
}
