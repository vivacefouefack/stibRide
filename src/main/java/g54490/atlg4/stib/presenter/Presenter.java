package g54490.atlg4.stib.presenter;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.model.Model;
import g54490.atlg4.stib.model.Observable;
import g54490.atlg4.stib.model.Observer;
import g54490.atlg4.stib.model.Search;
import g54490.atlg4.stib.repository.FavoriteRepository;
import g54490.atlg4.stib.view.FavoriteView;
import g54490.atlg4.stib.view.FavoriteViewController;
import g54490.atlg4.stib.view.MainView;
import g54490.atlg4.stib.view.MainViewController;
import g54490.atlg4.stib.view.ResultView;
import g54490.atlg4.stib.view.ResultViewController;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * 
     * @throws Exception 
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
     * 
     * @throws Exception 
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
     * 
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
     * 
     * @throws IOException 
     */
    public void addFavorite() throws IOException {
        initializeTextDispplay();
        if (resultControl.getFavoryName().getText().length() == 0) {
            resultControl.getSmsErreur().setText("Veuillez fournir le nom du favori");
        } else {
            model.computeAddFavory(new FavoritesDto(resultControl.getFavoryName().getText(), mainControl.getOrigine().getValue(), mainControl.getDestination().getValue()));
        }
    }

    /**
     * 
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
                favoriteControl.getSearchFavaris().setItems(model.getSearchFavoryData());
                model.computeDeletefavorite(favoriteControl.getSearchFavaris().getValue());
            } else {
                favoriteControl.getConfirmSuppressio().setText("Opération annulée");
            }
        }
    }

    /**
     * 
     */
    private void initializeTextDispplay() {
        mainControl.getErreur().setText("");
        favoriteControl.getErreurSelection().setText("");
        favoriteControl.getConfirmModification().setText("");
        resultControl.getSmsErreur().setText("");
        resultControl.getSmsConfirm().setText("");
        favoriteControl.getConfirmSuppressio().setText("");
    }

    /**
     * 
     * @param disable 
     */
    private void disableButton(Boolean disable) {
        favoriteControl.getNouvelleOri().setDisable(disable);
        favoriteControl.getNouvelleDes().setDisable(disable);
        favoriteControl.getOk().setDisable(disable);
    }

    /**
     * 
     */
    public void updateButton() {
        initializeTextDispplay();
        if (favoriteControl.getSearchFavaris().getValue() == null) {
            favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
        } else {
            disableButton(false) ;
        }
    }

    /**
     * 
     */
    public void updateFavorite() {

        if (favoriteControl.getNouvelleOri() == null || favoriteControl.getNouvelleDes() == null
                || favoriteControl.getNouvelleOri() == favoriteControl.getNouvelleDes()) {
            favoriteControl.getConfirmModification().setText("veuillez selectionner une origine et une destination");
        } else {
            model.updateFavorites(new FavoritesDto(favoriteControl.getSearchFavaris().getValue(),
                    favoriteControl.getNouvelleOri().getValue(), favoriteControl.getNouvelleDes().getValue()));
        }
    }

    /**
     * 
     */
    public void closeResultView() {
        mainControl.getSearch().setDisable(false);
        Stage stage = (Stage) resultControl.getQuitter().getScene().getWindow();
        stage.setScene(null);
        stage.close();
    }

    /**
     * 
     */
    public void closeFavoriteView() {
        mainControl.getItemMesfavoris().setDisable(false);
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
            favoriteControl.getConfirmModification().setText("le favori a été mise à jour ");
            disableButton(true) ;
        } else {
        }

    }

}
