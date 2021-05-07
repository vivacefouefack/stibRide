package g54490.atlg4.stib.view;

import g54490.atlg4.stib.repository.StationRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class MainViewController implements Initializable {

    @FXML
    private SearchableComboBox<String> destination;
    @FXML
    private SearchableComboBox<String> origine;
    @FXML
    private ImageView image1;
    @FXML
    private AnchorPane parent;
    @FXML
    private Button search;
    @FXML
    private Label erreur;

    @FXML
    void launchFavoritePage(ActionEvent event) {

    }

    /**
     * launches the search algorithm and displays the result in a new window
     * when you click on the search button
     *
     * @param event ActionEvent;
     */
    @FXML
    private void search(ActionEvent event) {
        this.erreur.setText("");
        if (origine.getValue() == null || destination.getValue() == null) {
            this.erreur.setText("? erreur veuillez selectionner l'origine et la destination");
        }
    }

    private AnchorPane parentHome;

    /**
     * constructor of mainViewController.
     */
    public MainViewController() {

        this.destination = new SearchableComboBox<String>();
        this.origine = new SearchableComboBox<String>();
        this.erreur = new Label();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            StationRepository stations = new StationRepository();
            this.origine.setItems(stations.getStationName());
            this.destination.setItems(stations.getStationName());
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
