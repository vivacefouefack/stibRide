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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     *
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
        } else if (false) {
            //***bouton mes favoris
            model.compute();
            mainControl.disable(true);
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
            Stage stage = (Stage) favoriteControl.getQuitter().getScene().getWindow();
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
        }

    }

    @Override
    public void update(Observable observable, Object arg) {
        try {

            if (false) {
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
