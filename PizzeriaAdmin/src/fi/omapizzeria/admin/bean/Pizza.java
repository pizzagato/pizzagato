package fi.omapizzeria.admin.bean;

import java.util.ArrayList;

//Pizza-luokka, jossa olevilla metodeilla luodaan Pizza-objekti ja asetetaan sille ominaisuuksia. Puuttuu vielä id.
//Vaatii uudelleennimeämistä

public class Pizza {
	int id;
	String nimi;
	Double hinta;
	ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pizza(int id, String nimi, Double hinta){
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
		this.taytteet = new ArrayList<Tayte>();
	}
	public Pizza(String nimi, Double hinta){
		super();
		this.nimi = nimi;
		this.hinta = hinta;
		this.taytteet = new ArrayList<Tayte>();
	}
	public int getId() {
		return id;
	}
	public ArrayList<Tayte> getTaytteet() {
		return taytteet;
	}
	public void addTayte(Tayte t) {
		this.taytteet.add(t);
	}
	public void setTaytteet(ArrayList<Tayte> taytteet) {
		this.taytteet = taytteet;
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
		return "Pizzalistaan [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta
				+ ", taytteet=" + taytteet + "]";
	}
	
}
