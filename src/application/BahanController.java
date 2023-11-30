package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BahanController implements Initializable {
	
	    @FXML
	    private TableColumn<Bahan, Integer> idBahan;

	    @FXML
	    private TableColumn<Bahan, String> jumlahBahan;

	    @FXML
	    private TableColumn<Bahan, String> namaBahan;

	    @FXML
	    private TableView<Bahan> tableBahan;
	    
	    ObservableList<Bahan> listBahan;
	    
	    private Stage stage;
	    private Scene scene;
	    private Parent root;
PindahScene scenechange = new PindahScene();
	    public void toUiKoki(ActionEvent event)throws IOException{
			scenechange.pindahscene(event, "Koki.fxml");
	    }
	    
		public void keHalamanUtama(ActionEvent event) throws IOException {
			scenechange.pindahscene(event, "First.fxml");
		}
		
		
	public static ObservableList<Bahan> getDatabahan(){
		DatabaseConnection konekNow = new DatabaseConnection();
		Connection konekData = konekNow.getConnection();
	ObservableList<Bahan> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = konekData.prepareStatement("select * from bahan");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(new Bahan(Integer.parseInt(rs.getString("id_bahan")), rs.getString("nama_bahan"), rs.getString("jumlah_bahan")));
			}
			
		} catch (Exception e) {}
	
	return list;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idBahan.setCellValueFactory(new PropertyValueFactory<Bahan, Integer>("kolomIdBahan"));
		namaBahan.setCellValueFactory(new PropertyValueFactory<Bahan, String>("kolomBahan"));
		jumlahBahan.setCellValueFactory(new PropertyValueFactory<Bahan, String>("kolomJumlah"));
		
		listBahan = BahanController.getDatabahan();
		tableBahan.setItems(listBahan);
	}

}
