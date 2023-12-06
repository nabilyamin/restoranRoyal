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

public class KokiController implements Initializable {
 
    @FXML
    private TableColumn<Order, Integer> idOrderKoki;

    @FXML
    private TableColumn<Order, String> pesananKoki;

    @FXML
    private TableView<Order> tableOrderKoki;

    @FXML
    private TableColumn<Order, Integer> totalKoki;
    
    ObservableList<Order> listOrderKoki;
    
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//public static ObservableList<Order> getDataorderkoki(){
	//	DatabaseConnection konekNow = new DatabaseConnection();
	//	Connection konekData = konekNow.getConnection();
	//ObservableList<Order> list = FXCollections.observableArrayList();
	//	try {
	//		PreparedStatement ps = konekData.prepareStatement("select * from orderlist");
	//		ResultSet rs = ps.executeQuery();
	//		
	//		while (rs.next()) {
	//			list.add(new Order(Integer.parseInt(rs.getString("order_id")), rs.getString("pesanan"),Integer.parseInt(rs.getString("total_price"))));
	//		}
	//		
	//	} catch (Exception e) {}
	//
	//return list;
	//}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idOrderKoki.setCellValueFactory(new PropertyValueFactory<Order, Integer>("tableIdOrder"));
		pesananKoki.setCellValueFactory(new PropertyValueFactory<Order, String>("tablePesanan"));
		totalKoki.setCellValueFactory(new PropertyValueFactory<Order, Integer>("tableTotalHarga"));

		listOrderKoki = ListOrderController.getDataorder();
		tableOrderKoki.setItems(listOrderKoki);
	}
	
	PindahScene scenechange = new PindahScene();
    public void toListBahanScene(ActionEvent event)throws IOException{
        scenechange.pindahscene(event, "ListBahanUI.fxml");
    }
    
    public void toUiKoki(ActionEvent event)throws IOException{
        scenechange.pindahscene(event, "Koki.fxml");
    }
    
	public void keHalamanUtama(ActionEvent event) throws IOException {
        scenechange.pindahscene(event, "First.fxml");
	}
    

}
