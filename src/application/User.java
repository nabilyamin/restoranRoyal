package application;

public class User {
	private int tableId;
	private String tableFirstName;
	private String tableLastName;
	private String tableRoles;
	private String tablePhone;
	private String tableEmail;
	
	public User(int tableId,
				String tableFirstName,
				String tableLastName,
				String tableRoles,
				String tablePhone,
				String tableEmail) {
		this.tableId = tableId;
		this.tableFirstName = tableFirstName;
		this.tableLastName = tableLastName;
		this.tableRoles = tableRoles;
		this.tablePhone = tablePhone;
		this.tableEmail = tableEmail;
	}
	
	public int getTableId() {
		return tableId;
	}
	
	public String getTableFirstName() {
		return tableFirstName;
	}
	
	public String getTableLastName() {
		return tableLastName;
	}
	
	public String getTableRoles() {
		return tableRoles;
	}
	
	public String getTablePhone() {
		return tablePhone;
	}
	
	public String getTableEmail() {
		return tableEmail;
	}
}
