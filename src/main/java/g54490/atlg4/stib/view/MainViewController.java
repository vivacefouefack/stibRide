package g54490.atlg4.stib.view;


import java.net.URL;
import java.util.ResourceBundle;
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
public class MainViewController implements Initializable{
    
    @FXML private SearchableComboBox<String> destination;
    @FXML private SearchableComboBox<String> origine;
    @FXML private ImageView image1;
    @FXML private AnchorPane parent;
    @FXML private Button search;
    @FXML private Label erreur;
    
    @FXML void launchFavoritePage(ActionEvent event)  {
       
    }

    @FXML private void search(ActionEvent event) {
        
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
        
    }
    
}
