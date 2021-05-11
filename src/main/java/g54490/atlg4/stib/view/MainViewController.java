package g54490.atlg4.stib.view;

import g54490.atlg4.stib.handler.Handler;
import g54490.atlg4.stib.presenter.Presenter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class MainViewController {//implements Initializable{

    @FXML
    private SearchableComboBox<String> destination;
    @FXML
    private SearchableComboBox<String> origine;
    @FXML
    private MenuItem itemMesfavoris;
    @FXML
    private ImageView image1;
    @FXML
    private AnchorPane parent;
    @FXML
    private Button search;
    @FXML
    private Label erreur;

//    @FXML
//    void launchFavoritePage(ActionEvent event) throws IOException, Exception {
//        FavoriteView view = new FavoriteView();
//        FavoriteViewController mainControl = view.getFxmlLoader().getController();
//        mainControl.disableButton(search, itemMesfavoris);
//        this.itemMesfavoris.setDisable(true);
//        this.search.setDisable(true);
//        view.start(new Stage());
//    }
    /**
     * launches the search algorithm and displays the result in a new window
     * when you click on the search button
     *
     * @param event ActionEvent;
     */
//    @FXML
//    private void search(ActionEvent event) {
//        this.originchoice=origine.getValue();
//        this.destinationChoice=destination.getValue();
//        
//        try {
//            this.erreur.setText("");
//            if (origine.getValue() == null || destination.getValue() == null) {
//                this.erreur.setText("? erreur veuillez selectionner l'origine et la destination");
//            } else {
//                
//                
////                ResultView result = new ResultView();
////                ResultViewController mainController = result.fxmlLoader.getController();
////                mainController.AddResultData(origine.getValue(), destination.getValue());
////                mainController.disableButton(search, itemMesfavoris);
////                result.start(new Stage());
////                mainController.disableButton(search, itemMesfavoris);
////                this.search.setDisable(true);
////                this.itemMesfavoris.setDisable(true);
//            }
//
//        } catch (Exception e) {
//            System.out.println("erreur" + e.getMessage());
//        }
//   }
//    public void MainViewController(Stage stage) throws IOException{
//        
//        Parent root =FXMLLoader.load(getClass().getResource("/fxml/stibInterface.fxml"));
//        Scene scene = new Scene(root);
//        
//        stage.getIcons().add(new Image("/icons/logo.png"));
//        stage.setTitle("Stib Ride");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//    }
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            StationRepository stations = new StationRepository();
//            this.origine.setItems(stations.getStationName());
//            this.destination.setItems(stations.getStationName());
//        } catch (IOException ex) {
//            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButton(Presenter presenter) {
        Handler handler = new Handler(presenter);
        search.setOnAction(handler);
        itemMesfavoris.setOnAction(handler);
    }

    /**
     * constructor of mainViewController.
     */
    public MainViewController() {
        this.destination = new SearchableComboBox<String>();
        this.origine = new SearchableComboBox<String>();
        this.erreur = new Label();
        this.itemMesfavoris = new MenuItem();
    }

    /**
     * allows you to initialize the list of stations in the search area.
     *
     * @param allNameStations list of all station name.
     */
    public void initialize(ObservableList<String> allNameStations) {
        this.origine.setItems(allNameStations);
        this.destination.setItems(allNameStations);
    }

    /**
     * allows to deactivate the buttons when the model performs the calculation
     * and to reactivate them at the end of the calculation according to the
     * boolean received
     *
     * @param answer allows you to know if you deactivate or not.
     */
    public void disable(boolean answer) {
        this.search.setDisable(answer);
        this.itemMesfavoris.setDisable(answer);
    }

    /**
     * getter.
     *
     * @return the choice of the user's destination station.
     */
    public SearchableComboBox<String> getDestination() {
        return destination;
    }

    /**
     * getter.
     *
     * @return the choice of the user's origin station
     */
    public SearchableComboBox<String> getOrigine() {
        return origine;
    }

    /**
     * getter.
     *
     * @return error label.
     */
    public Label getErreur() {
        return erreur;
    }

    /**
     * getter.
     *
     * @return itemMesfavoris menuItem.
     */
    public MenuItem getItemMesfavoris() {
        return itemMesfavoris;
    }

}
