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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BillController implements Initializable{
	@FXML
	private Label pesananBill, totalBill, billOrder;
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	
    public void toOrderFoodScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tambahOrderFoodRevisi.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void toRequestScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tambahRequest.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void toMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DatabaseConnection konekSekarang = new DatabaseConnection();
		Connection konekDatabase = konekSekarang.getConnection();
		
		try {
			Statement statement = konekDatabase.createStatement();
			ResultSet queryResult = statement.executeQuery("select * from orderlist "
					+ "where order_id = (select max(order_id) from orderlist)");
			while(queryResult.next()) {
				pesananBill.setText(queryResult.getString(2));
				totalBill.setText(queryResult.getString(3));
				billOrder.setText(queryResult.getString(1));
			}
		} catch (Exception event) {
			event.printStackTrace();
		}
	}
}
