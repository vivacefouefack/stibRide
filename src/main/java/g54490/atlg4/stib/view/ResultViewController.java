package g54490.atlg4.stib.view;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.handler.Handler;
import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.model.Search;
import g54490.atlg4.stib.presenter.Presenter;
import g54490.atlg4.stib.repository.FavoriteRepository;
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
    
    public void addHandlerButton(Presenter presenter) { 
        Handler handler = new Handler(presenter);
        quitter.setOnAction(handler);
        //Addfavory.setOnAction(handler);
        
    }

    public void disableButton(Button button,MenuItem item) {
        this.search = button;
        this.itemMesfavoris=item;
    }
    
    public void AddResultData(List<ResultData> datas){
        int count=0;
        for (ResultData oneLine : datas) {
            this.tableView.getItems().add(oneLine);
            count++;
        }
        this.nbStations.setText("Nomnbres de stations : " +count);
    }

    public Button getQuitter() {
        return quitter;
    }

    public Button getAddfavory() {
        return Addfavory;
    }

    public TextField getFavoryName() {
        return favoryName;
    }

    public Label getSmsErreur() {
        return smsErreur;
    }

    public Label getSmsConfirm() {
        return smsConfirm;
    }

}
