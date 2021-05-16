package g54490.atlg4.stib.main;

import g54490.atlg4.stib.model.Model;
import g54490.atlg4.stib.presenter.Presenter;
import g54490.atlg4.stib.view.MainView;
import g54490.atlg4.stib.view.MainViewController;
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
        MainViewController mainViewControl = Mainview.getFxmlLoader().getController();

        Model model = new Model();

        Presenter presenter = new Presenter(model, Mainview);
        presenter.initialize();
        model.addObserver(presenter);

        mainViewControl.addHandlerButtonsearch(presenter);
        mainViewControl.addHandlerButtonitemMesfavoris(presenter);
        presenter.initialize();
        Mainview.start(stage);
    }
}
