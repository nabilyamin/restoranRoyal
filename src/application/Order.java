package application;

public class Order {
	private int tableIdOrder, tableTotalHarga;
	private String tablePesanan;
	
	public Order(int tableIdOrder, String tablePesanan, int tableTotalHarga) {
	this.tableIdOrder = tableIdOrder;
	this.tablePesanan = tablePesanan;
	this.tableTotalHarga = tableTotalHarga;
	}
	
	public int getTableIdOrder() {
		return tableIdOrder;
	}
	public String getTablePesanan() {
		return tablePesanan;
	}
	public int getTableTotalHarga() {
		return tableTotalHarga;
	}
}
