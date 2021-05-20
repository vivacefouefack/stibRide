package g54490.atlg4.stib.view;

import g54490.atlg4.stib.presenter.Presenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class MainViewController {

    @FXML
    private SearchableComboBox<String> destination;
    @FXML
    private SearchableComboBox<String> origine;
    @FXML
    private MenuItem itemMesfavoris;
    @FXML
    private Button search;
    @FXML
    private Label erreur;
    
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
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonsearch(Presenter presenter) {
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    presenter.search();
                } catch (Exception ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * allows you to add a button to the event manager.
     *
     * @param presenter ask the model to do a calculation.
     */
    public void addHandlerButtonitemMesfavoris(Presenter presenter) {
        itemMesfavoris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    presenter.myfavorites();
                } catch (Exception ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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

    /**
     * getter.
     * @return search button.
     */
    public Button getSearch() {
        return search;
    }

}
