package application;

public class Pendapatan {
	private int pendapatan;
	private String idPendapatan;
	
	Pendapatan(int pendapatan, String idPendapatan){
		this.pendapatan = pendapatan;
		this.idPendapatan = idPendapatan;
	}
	
	public int getPendapatan() {
		return pendapatan;
	}
	public String getIdPendapatan() {
		return idPendapatan;
	}
}