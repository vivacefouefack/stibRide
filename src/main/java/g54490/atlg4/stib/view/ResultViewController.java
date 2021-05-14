package g54490.atlg4.stib.view;

import g54490.atlg4.stib.model.ResultData;
import g54490.atlg4.stib.presenter.Presenter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button Addfavorites;
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

    /**
     * constructor of mainViewResultControl.
     */
    public ResultViewController() {
        this.tStation = new TableColumn<>("");
        this.tLine = new TableColumn<>("");
        this.tableView = new TableView<>();
        this.favoryName = new TextField();
        this.nbStations = new Label();
        this.favoryName=new TextField();
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
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                presenter.closeResultView();
            }
        });
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonAddfavory(Presenter presenter) {
        Addfavorites.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    presenter.addFavorite();
                } catch (IOException ex) {
                    Logger.getLogger(ResultViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
