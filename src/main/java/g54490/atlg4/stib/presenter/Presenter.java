package g54490.atlg4.stib.presenter;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.model.Model;
import g54490.atlg4.stib.obersers.Observable;
import g54490.atlg4.stib.obersers.Observer;
import g54490.atlg4.stib.model.Search;
import g54490.atlg4.stib.repository.FavoriteRepository;
import g54490.atlg4.stib.view.FavoriteView;
import g54490.atlg4.stib.view.FavoriteViewController;
import g54490.atlg4.stib.view.MainView;
import g54490.atlg4.stib.view.MainViewController;
import g54490.atlg4.stib.view.ResultView;
import g54490.atlg4.stib.view.ResultViewController;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Presenter implements Observer {

    private Model model;
    private MainViewController mainControl;
    private ResultViewController resultControl;
    private FavoriteViewController favoriteControl;
    private MainView Mainview;
    private ResultView resultView;
    private FavoriteView favoriteView;

    /**
     * constructor of presenter.
     *
     * @param model will do all the necessary calculations.
     * @param Mainview is a view of the presenter
     * @throws IOException if the fxml favorite file has not been loaded or if
     * the fxml result file has not been loaded.
     */
    public Presenter(Model model, MainView Mainview) throws IOException {
        this.model = model;
        this.Mainview = Mainview;
        this.mainControl = Mainview.getFxmlLoader().getController();
        this.resultView = new ResultView();
        this.favoriteView = new FavoriteView();
        this.resultControl = resultView.getFxmlLoader().getController();
        this.favoriteControl = favoriteView.getFxmlLoader().getController();
    }

    /**
     * allows you to build a new window when you click on my favorites.
     *
     * @throws Exception if the fxml favorite file has not been loaded.
     */
    public void myfavorites() throws Exception {
        favoriteView = new FavoriteView();
        favoriteControl = favoriteView.getFxmlLoader().getController();
        favoriteControl.addHandlerButtonConsulter(this);
        favoriteControl.addHandlerButtonModifier(this);
        favoriteControl.addHandlerButtonSupprimer(this);
        favoriteControl.addHandlerButtonOk(this);
        favoriteControl.addHandlerButtonQuitter(this);
        model.computeNewFavoriteStage();
        mainControl.getItemMesfavoris().setDisable(true);
    }

    /**
     * allows to ask the model to build the data of a new window result.
     *
     * @throws Exception if the fxml result file has not been loaded.
     */
    public void search() throws Exception {
        initializeTextDispplay();
        if (mainControl.getOrigine().getValue() == null || mainControl.getDestination().getValue() == null
                || mainControl.getOrigine().getValue().equals(mainControl.getDestination().getValue())) {
            mainControl.getErreur().setText("? erreur veuillez selectionner l'origine et la destination");
        } else {
            model.computePath(mainControl.getOrigine().getValue(), mainControl.getDestination().getValue());
            mainControl.getSearch().setDisable(true);
        }
    }

    /**
     * display the most current path of a favorite choose.
     */
    public void Consulter() {
        initializeTextDispplay();
        favoriteControl.getNouvelleOri().setDisable(true);
        favoriteControl.getNouvelleDes().setDisable(true);
        favoriteControl.getOk().setDisable(true);
        if (favoriteControl.getSearchFavaris().getValue() == null) {
            favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
        } else {
            model.computeConsultFavory(favoriteControl.getSearchFavaris().getValue());
        }
    }

    /**
     * allows you to add a favorite in the database.
     *
     * @throws IOException if the connection to the database fails.
     */
    public void addFavorite() throws IOException {
        initializeTextDispplay();
        if (resultControl.getFavoryName().getText().length() == 0) {
            resultControl.getSmsErreur().setText("Veuillez fournir le nom du favori");
        } else {
            if (model.getSearchFavoryData().contains(resultControl.getFavoryName().getText())) {
                resultControl.getNotInsert().setText("Attention! ce nom existe déjà");
            } else {
                model.computeAddFavory(new FavoritesDto(0, resultControl.getFavoryName().getText(),
                        mainControl.getOrigine().getValue(), mainControl.getDestination().getValue()));
            }
        }
    }

    /**
     * allows you to delete a favorite. before deleting, a confirmation action
     * is requested from the user f the action is OK, the favorite is deleted
     * and the view is updated. if not, the operation is canceled and the view
     * is also updated.
     */
    public void supprimer() {
        initializeTextDispplay();
        favoriteControl.getNouvelleOri().setDisable(true);
        favoriteControl.getNouvelleDes().setDisable(true);
        favoriteControl.getOk().setDisable(true);

        if (favoriteControl.getSearchFavaris().getValue() == null) {
            favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
        } else {
            Alert dialogC = new Alert(AlertType.CONFIRMATION);
            dialogC.setTitle("confirmation suppression");
            dialogC.setHeaderText(null);
            dialogC.setContentText("etes-vous sur de vouloir supprimer ce favori ?");
            Optional<ButtonType> answer = dialogC.showAndWait();
            if (answer.get() == ButtonType.OK) {
                ObservableList<String> liste;
                String name = favoriteControl.getSearchFavaris().getValue();
                List<String> changeListe = model.getallFavoriteName();
                changeListe.remove(name);
                liste = FXCollections.observableArrayList(changeListe);
                favoriteControl.getSearchFavaris().setItems(liste);
                model.computeDeletefavorite(name);
            } else {
                favoriteControl.getConfirmSuppressio().setText("Opération annulée");
            }
        }
    }

    /**
     * allows you to update the test areas on the different views.
     */
    private void initializeTextDispplay() {
        mainControl.getErreur().setText("");
        favoriteControl.getErreurSelection().setText("");
        favoriteControl.getConfirmModification().setText("");
        resultControl.getSmsErreur().setText("");
        resultControl.getSmsConfirm().setText("");
        resultControl.getNotInsert().setText("");
        favoriteControl.getConfirmSuppressio().setText("");
    }

    /**
     * allows you to activate the buttons when you want to modify a favorite or
     * deactivate when you do something else.
     *
     * @param disable deactivated if true and active otherwise.
     */
    private void disableButton(Boolean disable) {
        favoriteControl.getNouvelleOri().setDisable(disable);
        favoriteControl.getNouvelleDes().setDisable(disable);
        favoriteControl.getOk().setDisable(disable);
        favoriteControl.getNewNam().setDisable(disable);
    }

    /**
     * activates the station name search zones when you want to modify a
     * favorite.
     */
    public void updateButton() {
        initializeTextDispplay();
        if (favoriteControl.getSearchFavaris().getValue() == null) {
            favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
        } else {
            disableButton(false);
        }
    }

    /**
     * allows you to modify an existing favorite.
     */
    public void updateFavorite() {
        if (favoriteControl.getNewNam().getText().length() ==0|| favoriteControl.getNouvelleOri().getValue() == null || 
                favoriteControl.getNouvelleDes().getValue() == null|| favoriteControl.getNouvelleOri().getValue() ==
                favoriteControl.getNouvelleDes().getValue()) {
            favoriteControl.getConfirmModification().setText("veuillez introduire un nom,selectionner une origine et une destination");
        } else {
            String name = favoriteControl.getSearchFavaris().getValue();
            
            if (model.getSearchFavoryData().contains(favoriteControl.getNewNam().getText())) {
                favoriteControl.getErreurSelection().setText("Attention! ce nom existe déjà");
            } else {
                model.updateFavorites(new FavoritesDto(model.computeKey(name), favoriteControl.getNewNam().getText(),
                        favoriteControl.getNouvelleOri().getValue(), favoriteControl.getNouvelleDes().getValue()));
                //favoriteControl.getSearchFavaris().setItems(FXCollections.observableArrayList(model.getallFavoriteName()));
            }

        }
    }

    /**
     * allows to quit the window of a result, before quitting the window, the
     * search button is activated.
     */
    public void closeResultView() {
        mainControl.getSearch().setDisable(false);
        Stage stage = (Stage) resultControl.getQuitter().getScene().getWindow();
        stage.close();
    }

    /**
     * allows you to quit the favorite manager window, before quitting the
     * window, the my favorites tab is activated.
     */
    public void closeFavoriteView() {
        Stage stage = (Stage) favoriteControl.getQuitter().getScene().getWindow();
        mainControl.getItemMesfavoris().setDisable(false);
        stage.close();
    }

    /**
     * initializes the presenter.
     *
     * @throws IOException if the connection to the database fails.
     */
    public void initialize() throws IOException {
        model.initialize();
        mainControl.initialize(model.getSearchData());
        favoriteControl.initialize(model.getSearchFavoryData(), model.getSearchData());
    }

    @Override
    public void update(Observable observable, Object arg) {

        if (arg instanceof Search) {
            try {
                resultView = new ResultView();
                resultControl = resultView.getFxmlLoader().getController();
                resultControl.AddResultData(((Model) observable).getDatas());
                resultControl.addHandlerButtonAddfavory(this);
                resultControl.addHandlerButtonquitter(this);
                resultView.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (arg instanceof Model) {
            favoriteControl.AddResultData(model.getDataFavoris());
        } else if (arg instanceof FavoritesDto) {
            favoriteControl.getTableView().getItems().clear();
            favoriteControl.getConfirmSuppressio().setText("le favori a été supprimé de la liste de vos favoris");
        } else if (arg instanceof String) {
            resultControl.getSmsConfirm().setText("le favori " + resultControl.getFavoryName().getText() + " a été ajouté aux favoris");

        } else if (arg instanceof Integer) {
            favoriteControl.initialize(model.getSearchFavoryData(), model.getSearchData());
            try {
                favoriteView.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (arg instanceof FavoriteRepository) {
            initializeTextDispplay();
            favoriteControl.getSearchFavaris().setItems(FXCollections.observableArrayList(model.getallFavoriteName()));
            favoriteControl.getConfirmModification().setText("le favori a été mise à jour ");
            disableButton(true);
        } else {
        }

    }

}
