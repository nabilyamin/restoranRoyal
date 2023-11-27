package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OwnerController implements Initializable {
	   @FXML
	    private TableColumn<User, String> email;

	    @FXML
	    private TableColumn<User, String> firstName;

	    @FXML
	    private TableColumn<User, Integer> id;

	    @FXML
	    private TableColumn<User, String> lastName;

	    @FXML
	    private TableColumn<User, String> phoneNumber;

	    @FXML
	    private TableColumn<User, String> roles;

	    @FXML
	    private TableView<User> tablePegawai;
	    
	    ObservableList<User> listM;
	    
	
	private Stage stage;
	private Scene scene;
	private Parent root;

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
	
	public static ObservableList<User> getDatausers(){
		DatabaseConnection konekNow = new DatabaseConnection();
		Connection konekData = konekNow.getConnection();
	ObservableList<User> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = konekData.prepareStatement("select * from useraccount");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(new User(Integer.parseInt(rs.getString("idpegawai")), rs.getString("first_name"), rs.getString("last_name"), rs.getString("peran"), rs.getString("no_telp"), rs.getString("email")));
			}
			
		} catch (Exception e) {}
	
	return list;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<User, Integer>("tableId"));
		firstName.setCellValueFactory(new PropertyValueFactory<User, String>("tableFirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<User, String>("tableLastName"));
		roles.setCellValueFactory(new PropertyValueFactory<User, String>("tableRoles"));
		phoneNumber.setCellValueFactory(new PropertyValueFactory<User, String>("tablePhone"));
		email.setCellValueFactory(new PropertyValueFactory<User, String>("tableEmail"));
		
		listM = OwnerController.getDatausers();
		tablePegawai.setItems(listM);
	}
	
	
}
