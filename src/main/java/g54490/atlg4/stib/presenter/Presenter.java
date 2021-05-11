package g54490.atlg4.stib.presenter;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.model.Model;
import g54490.atlg4.stib.model.Observable;
import g54490.atlg4.stib.model.Observer;
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
     * @param Mainview is a view of the presenter/
     * @param resultView is a view of the presenter.
     * @param favoriteView is a view of the presenter.
     */
    public Presenter(Model model, MainView Mainview, ResultView resultView, FavoriteView favoriteView) {
        this.Mainview = Mainview;
        this.resultView = resultView;
        this.favoriteView = favoriteView;
        this.model = model;
        this.mainControl = Mainview.getFxmlLoader().getController();
        this.resultControl = resultView.getFxmlLoader().getController();
        this.favoriteControl = favoriteView.getFxmlLoader().getController();
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

    public void doSomething1() {
        mainControl.getErreur().setText("");
        if (mainControl.getOrigine().getValue() == null || mainControl.getDestination().getValue() == null
                || mainControl.getOrigine().getValue().equals(mainControl.getDestination().getValue())) {
            mainControl.getErreur().setText("? erreur veuillez selectionner l'origine et la destination");
        } else {
            mainControl.disable(true);
            model.computePath(mainControl.getOrigine().getValue(), mainControl.getDestination().getValue());

        }
    }

    /**
     * asks the model to do a calculation when clicking on a button.
     */
    public void doSomething() {

        if (true) {
            //***bouton recherche
            mainControl.getErreur().setText("");
            if (mainControl.getOrigine().getValue() == null || mainControl.getDestination().getValue() == null
                    || mainControl.getOrigine().getValue().equals(mainControl.getDestination().getValue())) {
                mainControl.getErreur().setText("? erreur veuillez selectionner l'origine et la destination");
            } else {
                mainControl.disable(true);
                model.computePath(mainControl.getOrigine().getValue(), mainControl.getDestination().getValue());

            }
        } else if (true) {
            //***bouton mes favoris
            model.compute();
            //mainControl.disable(true);
        } else if (false) {
            //result/bouton quitter
            mainControl.disable(false);
            Stage stage = (Stage) resultControl.getQuitter().getScene().getWindow();
            stage.close();
            model.compute();
        } else if (false) {
            //resul/ajout favori
            resultControl.getSmsErreur().setText("");
            resultControl.getSmsConfirm().setText("");
            if (resultControl.getFavoryName().getText().length() == 0) {
                resultControl.getSmsErreur().setText("Veuillez fournir le nom du favori");
            } else {
                model.computeAddFavory(new FavoritesDto(resultControl.getFavoryName().getText(), mainControl.getOrigine().getValue(), mainControl.getDestination().getValue()));
                resultControl.getSmsConfirm().setText("le favori " + resultControl.getFavoryName().getText() + " a été ajouté aux favoris");
            }
        } else if (false) {
            //***bouton quitter favori
            mainControl.disable(false);
            Stage stage = (Stage) resultControl.getQuitter().getScene().getWindow();
            stage.close();
            model.compute();

        } else if (false) {
            //consulter
            //this.erreurSelection.setText("");
            //this.confirmModification.setText("");
            if (favoriteControl.getSearchFavaris().getValue() == null) {
                favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
            } else {
                model.computeConsultFavory(favoriteControl.getSearchFavaris().getValue());
                //this.AddResultData(dto.getOrigin(), dto.getDestination());
            }
        } else if (false) {
            //suppression dans favori
            favoriteControl.getErreurSelection().setText("");
            favoriteControl.getConfirmSuppressio().setText("");
            if (favoriteControl.getSearchFavaris().getValue() == null) {
                favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
            } else {
                Alert dialogC = new Alert(AlertType.CONFIRMATION);
                dialogC.setTitle("confirmation suppression");
                dialogC.setHeaderText(null);
                dialogC.setContentText("etes-vous sur de vouloir supprimer ce favori ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    model.getFavorites().remove(favoriteControl.getSearchFavaris().getValue());
                    favoriteControl.getSearchFavaris().getItems().remove(favoriteControl.getSearchFavaris().getValue());
                    favoriteControl.getConfirmSuppressio().setText("le favori " + favoriteControl.getSearchFavaris().getValue()
                            + " a été supprimé de la liste de vos favoris");
                } else {
                    favoriteControl.getConfirmSuppressio().setText("Opération annulée");
                }
                model.compute();
            }
        } else if (true) {
            //modifier
            favoriteControl.getErreurSelection().setText("");
            favoriteControl.getConfirmModification().setText("");
            if (favoriteControl.getSearchFavaris().getValue() == null) {
                favoriteControl.getErreurSelection().setText("? erreur veuillez selectionner un favori");
            } else {
                favoriteControl.getNouvelleOri().setDisable(false);
                favoriteControl.getNouvelleDes().setDisable(false);
                favoriteControl.getOk().setDisable(false);
            }
            model.compute();
        } else if (false) {
            //press ok

            if (favoriteControl.getNouvelleOri() == null || favoriteControl.getNouvelleDes() == null
                    || favoriteControl.getNouvelleOri() == favoriteControl.getNouvelleDes()) {
                favoriteControl.getConfirmModification().setText("veuillez selectionner une origine et une destination");
            } else {
                model.getFavorites().update(new FavoritesDto(favoriteControl.getSearchFavaris().getValue(),
                        favoriteControl.getNouvelleOri().getValue(), favoriteControl.getNouvelleDes().getValue()));

                favoriteControl.getConfirmModification().setText("le favori a été mise à jour ");
                favoriteControl.getNouvelleOri().setDisable(true);
                favoriteControl.getNouvelleDes().setDisable(true);
                favoriteControl.getOk().setDisable(true);
            }
        } else if (false) {
            //***bouton quitter favori
            mainControl.disable(false);
            Stage stage = (Stage) favoriteControl.getQuitter().getScene().getWindow();
            stage.close();
            model.compute();

        }

    }

    @Override
    public void update(Observable observable, Object arg) {
        try {

            if (true) {
                resultControl.AddResultData(((Model) observable).getDatas());
                resultView.start(new Stage());
                mainControl.disable(false);
            } else if (false) {
                favoriteView.start(new Stage());
            } else if (false) {
                //consult
                favoriteControl.AddResultData(model.getDataFavoris());
            }

        } catch (Exception ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
