package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinanceController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void keHalamanOwner(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Owner.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
	
	public void switchToRegister(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RegisterTab.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void keHalamanUtama(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}

	public void keHalamanAddMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AddMenuTab.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


}
