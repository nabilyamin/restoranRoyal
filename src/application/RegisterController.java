package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class RegisterController implements Initializable{
	
	@FXML
	private TextField firstnametext;
	@FXML
	private TextField lastnametext;
	@FXML
	private TextField usernameregis;
	@FXML
	private PasswordField passregister;
	@FXML
	private TextField phoneNum;
	@FXML
	private TextField emailUser;
	@FXML
	private Button registerButton;
	@FXML
	private Label regislabel;
	@FXML
	private ChoiceBox<String> roleBox;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void registerButtonAction(ActionEvent event) {
		if(firstnametext.getText().isBlank()==false && 
			lastnametext.getText().isBlank()==false && 
			usernameregis.getText().isBlank()==false &&
			passregister.getText().isBlank()==false && 
			roleBox.getValue()!= null &&
			roleBox.getValue().isBlank()==false &&
			phoneNum.getText().isBlank()==false &&
			emailUser.getText().isBlank()==false) {
			regis();
		} else {
		 regislabel.setText("Mohon isi dengan lengkap.");;
		}
	}
	
	public void regis() {
		DatabaseConnection konekSekarang = new DatabaseConnection();
		Connection konekDatabase = konekSekarang.getConnection();
		
		String verifyRegis = "insert into useraccount (username, pass, first_name, last_name, peran, no_telp, email) values ('"+usernameregis.getText()+"', '"+passregister.getText()+"', '"+firstnametext.getText()+"', '"+lastnametext.getText()+"', '"+roleBox.getValue()+"', '"+phoneNum.getText()+"', '"+emailUser.getText()+"')";
		try {
			Statement stmt = konekDatabase.createStatement();
			int rowsAffected = stmt.executeUpdate(verifyRegis);
			
			if(rowsAffected > 0) {
				regislabel.setText("Berhasil registrasi!");
			}else {regislabel.setText("Gagal Registrasi");}
			
		} catch (Exception event){
			event.printStackTrace();
		}
	}
	
	
	public void keHalamanUtama(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
	
	public void keHalamanOwner(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Owner.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
	
	public void keHalamanFinance(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FinanceTab.fxml"));
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
	
	
	private String[] roles = {"Pelayan", "Koki", "Owner"};
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		roleBox.getItems().addAll(roles);
		
	}
	
}
