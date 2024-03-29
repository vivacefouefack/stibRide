package g54490.atlg4.stib.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class MainView extends Application {

    private Parent root;
    final FXMLLoader fxmlLoader;

    /**
     * constructor.
     *
     * @throws IOException if the resource is not found.
     */
    public MainView() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/stibInterface.fxml"));
        this.root = fxmlLoader.load();
    }

    /**
     * getter.
     *
     * @return FXMLLoader so that we can have access to the interface
     * controller.
     */
    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root);

        stage.getIcons().add(new Image("/icons/logo.png"));
        stage.setTitle("Stib Ride");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
