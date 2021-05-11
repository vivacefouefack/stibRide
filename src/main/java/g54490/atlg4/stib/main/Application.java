package g54490.atlg4.stib.main;

import g54490.atlg4.stib.model.Model;
import g54490.atlg4.stib.presenter.Presenter;
import g54490.atlg4.stib.view.FavoriteView;
import g54490.atlg4.stib.view.FavoriteViewController;
import g54490.atlg4.stib.view.MainView;
import g54490.atlg4.stib.view.MainViewController;
import g54490.atlg4.stib.view.ResultView;
import g54490.atlg4.stib.view.ResultViewController;
import javafx.stage.Stage;

/**
 * allows you to launch the application.
 *
 * @author 54490@etu.he2b.be
 */
public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainView Mainview = new MainView();
        ResultView resultView = new ResultView();
        FavoriteView favoriteView = new FavoriteView();

        MainViewController mainViewControl = Mainview.getFxmlLoader().getController();
        ResultViewController resultviewControl = resultView.getFxmlLoader().getController();
        FavoriteViewController favoriteviewControl = favoriteView.getFxmlLoader().getController();

        Model model = new Model();

        Presenter presenter = new Presenter(model, Mainview, resultView, favoriteView);
        presenter.initialize();
        model.addObserver(presenter);
        Mainview.start(stage);

        mainViewControl.addHandlerButton(presenter);
        favoriteviewControl.addHandlerButton(presenter);
        resultviewControl.addHandlerButton(presenter);

    }
}
