package fi.omapizzeria.pizzagatto.bean;

public class Tuote {
	int id;
	String nimi;
	Double hinta;
	public Tuote() {
		super();
	}
	public Tuote(int id, String nimi, Double hinta) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
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
	public Double getHinta() {
		return hinta;
	}
	public void setHinta(Double hinta) {
		this.hinta = hinta;
	}
	@Override
	public String toString() {
		return "Tuote id=" + id + ", nimi=" + nimi + ", hinta=" + hinta ;
	}

}
