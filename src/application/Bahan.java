package application;

public class Bahan {
	private int kolomIdBahan;
	private String kolomBahan;
	private String kolomJumlah;
	
	public Bahan(int kolomIdBahan,
				 String kolomBahan,
				 String kolomJumlah) {
		this.kolomIdBahan = kolomIdBahan;
		this.kolomBahan = kolomBahan;
		this.kolomJumlah = kolomJumlah;
	}
	
	public int getKolomIdBahan() {
		return kolomIdBahan;
	}
	
	public String getKolomBahan() {
		return kolomBahan;
	}
	
	public String getKolomJumlah() {
		return kolomJumlah;
	}
}
