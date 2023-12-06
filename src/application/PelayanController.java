package application;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PelayanController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane foodOrderScene, DrinksScene, RequestScene;
    @FXML
    private CheckBox checkAyamGeprek, 
    				 checkAyamPenyet,
    				 checkAyamCrispy, 
    				 checkAyamLalapan, 
    				 checkAyamBBQ, 
    				 checkBakso, 
    				 checkNasGor, 
    				 checkEsTeh, 
    				 checkEsKelapa, 
    				 checkEsJeruk;
    @FXML
    private Label labelOrder;
    @FXML
    private TextField jumlahGeprek,
    				  jumlahPenyet, 
    				  jumlahCrispy, 
    				  jumlahLalapan, 
    				  jumlahBBQ, 
    				  jumlahBakso, 
    				  jumlahNasGor, 
    				  jumlahEsTeh, 
    				  jumlahEsKelapa, 
    				  jumlahEsJeruk;
    

    public void keHalamanUtama(ActionEvent event) {
        //ketika tombol logout ditekan maka akan kembali ke halaman utama/halaman login

    }
    
    
    int ayamgeprek, ayampenyet, ayamcrispy, ayamlalapan, ayambbq, sobakso, nasgorr, esteh, eskelapa, esjeruk;
    public void actionGeprek(ActionEvent event) {
    	if(checkAyamGeprek.isSelected()) {
    		ayamgeprek = 1;
    	}else {
    		ayamgeprek = 0;
    	}
    }
    
    public void actionPenyet(ActionEvent event) {
    	if(checkAyamPenyet.isSelected()) {
    		ayampenyet = 2;
    	}else {
    		ayampenyet = 0;
    	}
    }
    
    public void actionCrispy(ActionEvent event) {
    	if(checkAyamCrispy.isSelected()) {
    		ayamcrispy = 4;
    	}else {
    		ayamcrispy = 0;
    	}
    }
    
    public void actionLalapan(ActionEvent event) {
    	if(checkAyamLalapan.isSelected()) {
    		ayamlalapan = 3;
    	}else {
    		ayamlalapan = 0;
    	}
    }
    
    public void actionBbq(ActionEvent event) {
    	if(checkAyamBBQ.isSelected()) {
    		ayambbq = 5;
    	}else {
    		ayambbq = 0;
    	}
    }
    
    public void actionBakso(ActionEvent event) {
    	if(checkBakso.isSelected()) {
    		sobakso = 6;
    	}else {
    		sobakso = 0;
    	}
    }
    
    public void actionNasgor(ActionEvent event) {
    	if(checkNasGor.isSelected()) {
    		nasgorr = 7;
    	}else {
    		nasgorr = 0;
    	}
    }
    
    public void actionEsteh(ActionEvent event) {
    	if(checkEsTeh.isSelected()) {
    		esteh = 8;
    	}else {
    		esteh = 0;
    	}
    }
    
    public void actionEskelapa(ActionEvent event) {
    	if(checkEsKelapa.isSelected()) {
    		eskelapa = 9;
    	}else {
    		eskelapa = 0;
    	}
    }
    
    public void actionEsjeruk(ActionEvent event) {
    	if(checkEsJeruk.isSelected()) {
    		esjeruk = 10;
    	}else {
    		esjeruk = 0;
    	}
    }
    
    public void confirmOrder() {
    	DatabaseConnection connect = new DatabaseConnection();
    	Connection connectDB = connect.getConnection();
    	
    	String verifyOrder = "UPDATE bahan SET bahan.jumlah_bahan = bahan.jumlah_bahan - " +
    	        "(SELECT SUM(CASE " +
    	        "WHEN menubahan.id_menu = 1 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 2 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 3 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 4 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 5 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 6 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 7 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 8 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 9 THEN COALESCE(?, 0) * menubahan.quantity_required " +
    	        "WHEN menubahan.id_menu = 10 THEN COALESCE(?, 0) * menubahan.quantity_required END) " +
    	        "FROM menubahan WHERE bahan.id_bahan = menubahan.id_bahan) " +
    	        "WHERE bahan.id_bahan IN (SELECT id_bahan FROM menubahan WHERE id_menu IN ("+ayamgeprek+", "+ayampenyet+", "+ayamlalapan+", "+ayamcrispy+", "+ayambbq+", "+sobakso+", "+nasgorr+", "+esteh+", "+eskelapa+", "+esjeruk+"))";

    	String insertOrder = "INSERT INTO orderlist (pesanan, total_price) VALUES (" +
    	        "(SELECT CONCAT(" +
    	        "(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?), " +
    	        "CONCAT(?) " +
    	        ") FROM dual), " +
    	        "(SELECT (" +
    	        "COALESCE(?, 0) * 12000 + " +
    	        "COALESCE(?, 0) * 10000 + " +
    	        "COALESCE(?, 0) * 10000 + " +
    	        "COALESCE(?, 0) * 12000 + " +
    	        "COALESCE(?, 0) * 12000 + " +
    	        "COALESCE(?, 0) * 10000 + " +
    	        "COALESCE(?, 0) * 10000 + " +
    	        "COALESCE(?, 0) * 5000 + " +
    	        "COALESCE(?, 0) * 5000 + " +
    	        "COALESCE(?, 0) * 5000) " +
    	        "FROM dual))";
    	
    	try {
    		PreparedStatement preparedStatement = connectDB.prepareStatement(verifyOrder);
    		
    		
    		if (!jumlahGeprek.getText().isEmpty()) {
    			preparedStatement.setInt(1, Integer.parseInt(jumlahGeprek.getText()));
    		} else {
    		    preparedStatement.setInt(1, 0);
    		}
    		
    		if (!jumlahPenyet.getText().isEmpty()) {
    		    preparedStatement.setInt(2, Integer.parseInt(jumlahPenyet.getText()));
    		} else {
    		    preparedStatement.setInt(2, 0);
    		}
    		
    		if (!jumlahLalapan.getText().isEmpty()) {
    		    preparedStatement.setInt(3, Integer.parseInt(jumlahLalapan.getText()));
    		} else {
    		    preparedStatement.setInt(3, 0);
    		}
    		
    		if (!jumlahCrispy.getText().isEmpty()) {
    		    preparedStatement.setInt(4, Integer.parseInt(jumlahCrispy.getText()));
    		} else {
    		    preparedStatement.setInt(4, 0);
    		}
    		
    		if (!jumlahBBQ.getText().isEmpty()) {
    		    preparedStatement.setInt(5, Integer.parseInt(jumlahBBQ.getText()));
    		} else {
    		    preparedStatement.setInt(5, 0);
    		}
    		
    		if (!jumlahBakso.getText().isEmpty()) {
    		    preparedStatement.setInt(6, Integer.parseInt(jumlahBakso.getText()));
    		} else {
    		    preparedStatement.setInt(6, 0);
    		}
    		
    		if (!jumlahNasGor.getText().isEmpty()) {
    		    preparedStatement.setInt(7, Integer.parseInt(jumlahNasGor.getText()));
    		} else {
    		    preparedStatement.setInt(7, 0);
    		}
    		
    		if (!jumlahEsTeh.getText().isEmpty()) {
    		    preparedStatement.setInt(8, Integer.parseInt(jumlahEsTeh.getText()));
    		} else {
    		    preparedStatement.setInt(8, 0);
    		}
    		
    		if (!jumlahEsKelapa.getText().isEmpty()) {
    		    preparedStatement.setInt(9, Integer.parseInt(jumlahEsKelapa.getText()));
    		} else {
    		    preparedStatement.setInt(9, 0);
    		}
    		
    		if (!jumlahEsJeruk.getText().isEmpty()) {
    		    preparedStatement.setInt(10, Integer.parseInt(jumlahEsJeruk.getText()));
    		} else {
    		    preparedStatement.setInt(10, 0);
    		}
    		
    		int rowsAffect = preparedStatement.executeUpdate();
    		
    		if(rowsAffect > 0) {
    			PreparedStatement insertOrderStatement = connectDB.prepareStatement(insertOrder);
    			
                if (!jumlahGeprek.getText().isEmpty()) {
                	insertOrderStatement.setString(1, jumlahGeprek.getText()+" Geprek, ");
        		} else {
        			insertOrderStatement.setString(1, "");
        		}
        		
        		if (!jumlahPenyet.getText().isEmpty()) {
        			insertOrderStatement.setString(2, jumlahPenyet.getText()+" Penyet, ");
        		} else {
        			insertOrderStatement.setString(2, "");
        		}
        		
        		if (!jumlahLalapan.getText().isEmpty()) {
        			insertOrderStatement.setString(3, jumlahLalapan.getText()+" Lalapan, ");
        		} else {
        			insertOrderStatement.setString(3, "");
        		}
        		
        		if (!jumlahCrispy.getText().isEmpty()) {
        			insertOrderStatement.setString(4, jumlahCrispy.getText()+" Crispy, ");
        		} else {
        			insertOrderStatement.setString(4, "");
        		}
        		
        		if (!jumlahBBQ.getText().isEmpty()) {
        			insertOrderStatement.setString(5, jumlahBBQ.getText()+" BBQ, ");
        		} else {
        			insertOrderStatement.setString(5, "");
        		}
        		
        		if (!jumlahBakso.getText().isEmpty()) {
        			insertOrderStatement.setString(6, jumlahBakso.getText()+" Bakso, ");
        		} else {
        			insertOrderStatement.setString(6, "");
        		}
        		
        		if (!jumlahNasGor.getText().isEmpty()) {
        			insertOrderStatement.setString(7, jumlahNasGor.getText()+" NasGor, ");
        		} else {
        			insertOrderStatement.setString(7, "");
        		}
        		
        		if (!jumlahEsTeh.getText().isEmpty()) {
        			insertOrderStatement.setString(8, jumlahEsTeh.getText()+" Es Teh, ");
        		} else {
        			insertOrderStatement.setString(8, "");
        		}
        		
        		if (!jumlahEsKelapa.getText().isEmpty()) {
        			insertOrderStatement.setString(9, jumlahEsKelapa.getText()+" Es Kelapa, ");
        		} else {
        			insertOrderStatement.setString(9, "");
        		}
        		
        		if (!jumlahEsJeruk.getText().isEmpty()) {
        			insertOrderStatement.setString(10, jumlahEsJeruk.getText()+" Es Jeruk, ");
        		} else {
        			insertOrderStatement.setString(10, "");
        		}
        		
                if (!jumlahGeprek.getText().isEmpty()) {
                	insertOrderStatement.setInt(11, Integer.parseInt(jumlahGeprek.getText()));
        		} else {
        			insertOrderStatement.setInt(11, 0);
        		}
        		
        		if (!jumlahPenyet.getText().isEmpty()) {
        			insertOrderStatement.setInt(12, Integer.parseInt(jumlahPenyet.getText()));
        		} else {
        			insertOrderStatement.setInt(12, 0);
        		}
        		
        		if (!jumlahLalapan.getText().isEmpty()) {
        			insertOrderStatement.setInt(13, Integer.parseInt(jumlahLalapan.getText()));
        		} else {
        			insertOrderStatement.setInt(13, 0);
        		}
        		
        		if (!jumlahCrispy.getText().isEmpty()) {
        			insertOrderStatement.setInt(14, Integer.parseInt(jumlahCrispy.getText()));
        		} else {
        			insertOrderStatement.setInt(14, 0);
        		}
        		
        		if (!jumlahBBQ.getText().isEmpty()) {
        			insertOrderStatement.setInt(15, Integer.parseInt(jumlahBBQ.getText()));
        		} else {
        			insertOrderStatement.setInt(15, 0);
        		}
        		
        		if (!jumlahBakso.getText().isEmpty()) {
        			insertOrderStatement.setInt(16, Integer.parseInt(jumlahBakso.getText()));
        		} else {
        			insertOrderStatement.setInt(16, 0);
        		}
        		
        		if (!jumlahNasGor.getText().isEmpty()) {
        			insertOrderStatement.setInt(17, Integer.parseInt(jumlahNasGor.getText()));
        		} else {
        			insertOrderStatement.setInt(17, 0);
        		}
        		
        		if (!jumlahEsTeh.getText().isEmpty()) {
        			insertOrderStatement.setInt(18, Integer.parseInt(jumlahEsTeh.getText()));
        		} else {
        			insertOrderStatement.setInt(18, 0);
        		}
        		
        		if (!jumlahEsKelapa.getText().isEmpty()) {
        			insertOrderStatement.setInt(19, Integer.parseInt(jumlahEsKelapa.getText()));
        		} else {
        			insertOrderStatement.setInt(19, 0);
        		}
        		
        		if (!jumlahEsJeruk.getText().isEmpty()) {
        			insertOrderStatement.setInt(20, Integer.parseInt(jumlahEsJeruk.getText()));
        		} else {
        			insertOrderStatement.setInt(20, 0);
        		}
                
                int insertRowsAffected = insertOrderStatement.executeUpdate();

    			labelOrder.setText("Berhasil order");
    		} else {
    			labelOrder.setText("Silakan isi");
    		}
    		
    	} catch(Exception event) {
    		event.printStackTrace();
    	}
    }
    
    public void toMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
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
    
}

