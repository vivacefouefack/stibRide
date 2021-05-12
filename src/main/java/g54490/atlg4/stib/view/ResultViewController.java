package g54490.atlg4.stib.view;

import g54490.atlg4.stib.handler.Handler;
import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.presenter.Presenter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class ResultViewController implements Initializable {

    @FXML
    private TableView<ResultData> tableView;
    @FXML
    private TableColumn<ResultData, String> tStation;
    @FXML
    private TableColumn<ResultData, String> tLine;
    @FXML
    private Button Addfavory;
    @FXML
    private TextField favoryName;
    @FXML
    private Label nbStations;
    @FXML
    private Label smsErreur;
    @FXML
    private Label smsConfirm;
    @FXML
    private Button quitter;

//    @FXML void actionQuitter(ActionEvent event) {
//        Stage stage = (Stage) quitter.getScene().getWindow();
//        search.setDisable(false);
//        this.itemMesfavoris.setDisable(false);
//        stage.close();
//    }
//    @FXML void actionAddFavorites(ActionEvent event) throws IOException {
//        this.smsErreur.setText("");
//        this.smsConfirm.setText("");
//        System.out.println(this.favoryName.getText().length());
//        if (this.favoryName.getText().length()==0) {
//            this.smsErreur.setText("Veuillez fournir le nom du favori");
//        } else {
//            FavoriteRepository favorites=new FavoriteRepository();
//            this.smsConfirm.setText("le favori " + this.favoryName.getText() + " a été ajouté aux favoris");
//            favorites.add(new FavoritesDto(this.favoryName.getText(), origin, destination));
//        }
//    }
//  
//    public void AddResultData(String origin, String destination) {
//        if (this.tableView != null) {
//            this.tableView.getItems().clear();
//        }
//        this.origin = origin; 
//        this.destination = destination;
//        Search search = new Search(origin, destination);
//        List<ResultData> data = search.getResultData();
//        for (ResultData oneLine : data) {
//            this.tableView.getItems().add(oneLine);
//        }
//        this.nbStations.setText("Nomnbres de stations : " + search.getNbtation());
//    }
    private String origin;
    private String destination;
    private Button search;
    private MenuItem itemMesfavoris;

    /**
     * constructor of mainViewResultControl.
     */
    public ResultViewController() {
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

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonquitter(Presenter presenter) {
        Handler handler = new Handler(presenter);
        quitter.setOnAction(handler);
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonAddfavory(Presenter presenter) {
        Handler handler = new Handler(presenter);
        //Addfavory.setOnAction(handler);
    }

    /**
     * allows you to add data to the results table.
     *
     * @param datas data list to display.
     */
    public void AddResultData(List<ResultData> datas) {
        int count = 0;
        for (ResultData oneLine : datas) {
            this.tableView.getItems().add(oneLine);
            count++;
        }
        this.nbStations.setText("Nomnbres de stations : " + count);
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
     * @return Addfavory button.
     */
    public Button getAddfavory() {
        return Addfavory;
    }

    /**
     * getter.
     *
     * @return favoryName textfield.
     */
    public TextField getFavoryName() {
        return favoryName;
    }

    /**
     * getter.
     *
     * @return smsErreur label.
     */
    public Label getSmsErreur() {
        return smsErreur;
    }

    /**
     * getter.
     *
     * @return smsConfirm label.
     */
    public Label getSmsConfirm() {
        return smsConfirm;
    }

}
