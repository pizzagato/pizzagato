package fi.omapizzeria.pizzagatto.bean;

public class Juoma {

	int id;
	String nimi;
	double hinta;
	String koko;
	String tyyppi;
	
	public Juoma() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Juoma(int id, String nimi, double hinta, String koko, String tyyppi) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
		this.koko = koko;
		this.tyyppi = tyyppi;
	}
	
	public Juoma(String nimi, double hinta, String koko, String tyyppi
			) {
		super();
		this.nimi = nimi;
		this.hinta = hinta;
		this.koko = koko;
		this.tyyppi = tyyppi;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public double getHinta() {
		return hinta;
	}
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	public String getKoko() {
		return koko;
	}
	public void setKoko(String koko) {
		this.koko = koko;
	}
	public String getTyyppi() {
		return tyyppi;
	}
	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
	}
	@Override
	public String toString() {
		return "Juoma [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta
				+ ", koko=" + koko + ", tyyppi=" + tyyppi + "]";
	}
	
	
	
}



