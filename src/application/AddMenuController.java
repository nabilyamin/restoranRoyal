package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class AddMenuController implements Initializable{

    @FXML
    private TextField menunamefield, menupricefield, qtyField;
    @FXML
    private Button addMenuButton, addingredientsButton;
    @FXML
    private Label addMenuLabel, addedIngredientsLabel;
    @FXML
    private ComboBox<String> ingredientName, unitBox;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addMenuButtonAction(ActionEvent event) throws IOException {
        if(menunamefield.getText().isBlank()==false &&
           menupricefield.getText().isBlank()==false &&
           !Objects.equals(ingredientslabel, ""))
        {
            addMenu();
            addMenuLabel.setText("menambahkan menu berhasil");
            Parent root = FXMLLoader.load(getClass().getResource("AddMenuTab.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            addMenuLabel.setText("Mohon isi data dengan lengkap.");;
        }
    }

    public void addMenu() {     //not finished yet
        DatabaseConnection konekSekarang = new DatabaseConnection();
        Connection konekDatabase = konekSekarang.getConnection();

        String verifyaddMenu = "insert into databasename (menu_name, price) values ('"+menunamefield.getText()+"', '"+menupricefield.getText()+"')";
        try {
            Statement stmt = konekDatabase.createStatement();
            int rowsAffected = stmt.executeUpdate(verifyaddMenu);

            if(rowsAffected > 0) {
                addMenuLabel.setText("Berhasil menambahkan menu!");
            }else {addMenuLabel.setText("Gagal menambahkan menu");}

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
    //dont delete
    String ingredientslabel = "";
    public void ingredientsButtonAction(){
        System.out.println("ingredient added");
        String ingredientNameValue = ingredientName.getValue();
        String qtyFieldText = qtyField.getText();
        String unitBoxValue = unitBox.getValue();
        ingredientslabel = ingredientslabel + ingredientNameValue +" " + qtyFieldText + " " + unitBoxValue + "\n";
        addedIngredientsLabel.setText(ingredientslabel);
    }
    private final String[] Unit = {"gr","L", "kg", "mL", "dozen"}; //final mi ini iya, kecuali mau ditambahkan unit baru
    private final String[] ingredientsname = {"Nasi", "Bumbu", "Gula", "Mirsa"}; //mau diubah jadi database
    //    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ingredientName.getItems().addAll(ingredientsname);
        unitBox.getItems().addAll(Unit);
    }
    
	public void switchToRegister(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RegisterTab.fxml"));
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

}
