package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private Button buttonlogin;
	@FXML
	private TextField usernametextfield;
	@FXML
	private Label loginlabel;
	@FXML
	private PasswordField passtextfield;
	
	public void loginButtonAction(ActionEvent event) {
		if(usernametextfield.getText().isBlank()==false && passtextfield.getText().isBlank()==false) {
			validasi();
		} else {
			loginlabel.setText("Masukkan dengan lengkap");
		}
	}
	
	public void validasi() {
		DatabaseConnection konekSekarang = new DatabaseConnection();
		Connection konekDatabase = konekSekarang.getConnection();
		
		String verifylogin = "Select count(1), peran from useraccount where username = '" + usernametextfield.getText() + "' and pass = '" + passtextfield.getText() + "'";
		
		try {
			Statement statement = konekDatabase.createStatement();
			ResultSet queryResult = statement.executeQuery(verifylogin);
			
			while(queryResult.next()) {
				if(queryResult.getInt(1)==1 && "Pelayan".equals(queryResult.getString("peran"))) {
					switchToNewScenePelayan();
				} else if (queryResult.getInt(1)==1 && "Koki".equals(queryResult.getString("peran"))){
					switchToNewSceneKoki();
				} else if (queryResult.getInt(1)==1 && "Owner".equals(queryResult.getString("peran"))) {
					switchToNewSceneOwner();
				} else {
					loginlabel.setText("Username atau Password salah");
					
				}
			}
		} catch (Exception event) {
			event.printStackTrace();
		}
	}
	
	private void switchToNewScenePelayan() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pelayan.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            // Get the current stage if available
            Stage stage = (Stage) loginlabel.getScene().getWindow();

            stage.setScene(newScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void switchToNewSceneKoki() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Koki.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            // Get the current stage if available
            Stage stage = (Stage) loginlabel.getScene().getWindow();

            stage.setScene(newScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void switchToNewSceneOwner() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Owner.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            // Get the current stage if available
            Stage stage = (Stage) loginlabel.getScene().getWindow();

            stage.setScene(newScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
}
