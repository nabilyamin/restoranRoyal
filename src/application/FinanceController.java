package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FinanceController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private TableView<Pendapatan> tablePendapatan;

    @FXML
    private TableColumn<Pendapatan, Integer> totalPendapatan;
    
    @FXML
    private Label labelTotal;
    
    ObservableList<Pendapatan> listPendapatan;

	PindahScene scenechange = new PindahScene();
	public void keHalamanOwner(ActionEvent event) throws IOException {
		scenechange.pindahscene(event, "Owner.fxml");
		}
	
	public void switchToRegister(ActionEvent event) throws IOException {
		scenechange.pindahscene(event, "RegisterTab.fxml");
	}
	
	public void keHalamanUtama(ActionEvent event) throws IOException {
		scenechange.pindahscene(event, "First.fxml");
	}

	public void keHalamanAddMenu(ActionEvent event) throws IOException {
		scenechange.pindahscene(event, "AddMenuTab.fxml");
	}

	 
		public static ObservableList<Pendapatan> getDatapendapatan(){
			DatabaseConnection konekNow = new DatabaseConnection();
			Connection konekData = konekNow.getConnection();
		ObservableList<Pendapatan> list = FXCollections.observableArrayList();
			try {
				PreparedStatement ps = konekData.prepareStatement("select * from orderlist");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					list.add(new Pendapatan(Integer.parseInt(rs.getString("total_price"))));
				}
				
			} catch (Exception e) {}
		
		return list;
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			totalPendapatan.setCellValueFactory(new PropertyValueFactory<Pendapatan, Integer>("pendapatan"));

			listPendapatan = FinanceController.getDatapendapatan();
			tablePendapatan.setItems(listPendapatan);
			
			DatabaseConnection konekSekarang = new DatabaseConnection();
			Connection konekDatabase = konekSekarang.getConnection();
			
			try {
				Statement statement = konekDatabase.createStatement();
				ResultSet queryResult = statement.executeQuery("SELECT SUM(total_price) AS total_sum FROM orderlist");
				
				while(queryResult.next()) {
					labelTotal.setText(queryResult.getString(1));
				}
			} catch (Exception event) {
				event.printStackTrace();
			}
		}
		

}


