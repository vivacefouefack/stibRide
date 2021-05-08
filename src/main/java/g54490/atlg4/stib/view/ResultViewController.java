package g54490.atlg4.stib.view;

import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.model.Search;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class ResultViewController implements Initializable{

    @FXML private TableView<ResultData> tableView;
    @FXML private TableColumn<ResultData, String> tStation;
    @FXML private TableColumn<ResultData, String> tLine;
    @FXML private Button Addfavory;
    @FXML private TextField favoryName;
    @FXML private Label nbStations;
    @FXML private Label smsErreur;
    @FXML private Label smsConfirm;
    @FXML private Button quitter;

    @FXML void actionQuitter(ActionEvent event) {
        Stage stage = (Stage) quitter.getScene().getWindow();
        search.setDisable(false);
        this.itemMesfavoris.setDisable(false);
        stage.close();
    }

    @FXML void actionAddFavorites(ActionEvent event) {
        this.smsErreur.setText("");
        this.smsConfirm.setText("");
        if (this.favoryName.getText() == "") {
            this.smsErreur.setText("Veuillez fournir le nom du favori");
        } else {
            this.smsConfirm.setText("le favori " + this.favoryName.getText() + " a été ajouté aux favoris");
        }
    }
    
    private String origin;
    private String destination;
    private Button search;
    private MenuItem itemMesfavoris;

    /**
     * constructor of mainViewResultControl.
     * @throws IOException 
     */
    public ResultViewController() throws IOException {
        this.tStation = new TableColumn<>("");
        this.tLine = new TableColumn<>("");
        this.tableView = new TableView<>();
        this.favoryName = new TextField();
        this.nbStations = new Label();
        this.origin = "";
        this.destination = "";
        this.smsErreur = new Label();
        this.smsConfirm = new Label();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tStation.setCellValueFactory(new PropertyValueFactory<ResultData, String>("nameStation"));
        tLine.setCellValueFactory(new PropertyValueFactory<ResultData, String>("lines"));

    }

    public void AddResultData(String origin, String destination) {
        if (this.tableView != null) {
            this.tableView.getItems().clear();
        }
        this.origin = origin;
        this.destination = destination;
        Search r = new Search(origin, destination);
        List<ResultData> data = r.getResultData();
        for (ResultData oneLine : data) {
            this.tableView.getItems().add(oneLine);
        }
        this.nbStations.setText("Nomnbres de stations : " + r.getNbtation());
    }

    public void disableButton(Button button,MenuItem item) {
        this.search = button;
        this.itemMesfavoris=item;
    }

}
