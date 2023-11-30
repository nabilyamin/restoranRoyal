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


public class ListOrderController implements Initializable {
    @FXML
    private TableColumn<Order, Integer> idOrder;

    @FXML
    private TableColumn<Order, String> pesanan;

    @FXML
    private TableView<Order> tabelOrder;

    @FXML
    private TableColumn<Order, Integer> totalHarga;
    
    ObservableList<Order> listOrder;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
	public static ObservableList<Order> getDataorder(){
		DatabaseConnection konekNow = new DatabaseConnection();
		Connection konekData = konekNow.getConnection();
	ObservableList<Order> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = konekData.prepareStatement("select * from orderlist");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(new Order(Integer.parseInt(rs.getString("order_id")), rs.getString("pesanan"),Integer.parseInt(rs.getString("total_price"))));
			}
			
		} catch (Exception e) {}
	
	return list;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idOrder.setCellValueFactory(new PropertyValueFactory<Order, Integer>("tableIdOrder"));
		pesanan.setCellValueFactory(new PropertyValueFactory<Order, String>("tablePesanan"));
		totalHarga.setCellValueFactory(new PropertyValueFactory<Order, Integer>("tableTotalHarga"));

		listOrder = ListOrderController.getDataorder();
		tabelOrder.setItems(listOrder);
	}

    PindahScene scenechange = new PindahScene();
    public void toMainScene(ActionEvent event) throws IOException {
        scenechange.pindahscene(event, "First.fxml");
    }
    
    public void toOrderFoodScene(ActionEvent event) throws IOException {
        scenechange.pindahscene(event, "tambahOrderFoodRevisi.fxml");
    }
}
