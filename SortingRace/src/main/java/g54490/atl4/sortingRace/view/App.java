package g54490.atl4.sortingRace.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author g54490@etu.he2b.be
 */
public class App extends Application{

    @Override
    public void start(Stage stage) throws IOException {

        Parent root =FXMLLoader.load(getClass().getResource("/fxml/sort.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/icons/logo.png"));
        stage.setTitle("Sorting Race");
        stage.setScene(scene);
        stage.show();   
    }
    
}
 