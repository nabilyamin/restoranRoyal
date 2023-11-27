package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public Connection databaseLink;
	
	public Connection getConnection() {
		String databaseName = "restoran";
		String databaseUser = "nabilyamin";
		String databasePassword = "yaminnabil321";
		String url = "jdbc:mysql://localhost/" + databaseName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		} catch (Exception event) {
			event.printStackTrace();
		}
		return databaseLink;
	}
}
