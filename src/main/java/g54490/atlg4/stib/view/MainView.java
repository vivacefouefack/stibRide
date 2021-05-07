package g54490.atlg4.stib.view;

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
public class MainView extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root=FXMLLoader.load(getClass().getResource("/fxml/stibInterface.fxml"));
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/icons/logo.png"));
        stage.setTitle("Stib Ride");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
