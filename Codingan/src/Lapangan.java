
public class Lapangan {
	
	private int id;
	private int price;
	private String jenisLapangan;
	
	public Lapangan(int id, int price, String jenisLapangan) {
		super();
		this.id = id;
		this.price = price;
		this.jenisLapangan = jenisLapangan;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getJenisLapangan() {
		return jenisLapangan;
	}
	public void setJenisLapangan(String jenisLapangan) {
		this.jenisLapangan = jenisLapangan;
	}

}
