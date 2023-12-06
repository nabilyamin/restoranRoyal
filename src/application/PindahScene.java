package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PindahScene {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public void pindahscene (ActionEvent event, String fxmlnm ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlnm));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
