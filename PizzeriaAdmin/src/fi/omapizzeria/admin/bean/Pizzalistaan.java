package fi.omapizzeria.admin.bean;

import java.util.ArrayList;

public class Pizzalistaan {
	String nimi;
	Double hinta;
	ArrayList<String> taytenimi = new ArrayList<String>();
	public Pizzalistaan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pizzalistaan(String nimi, Double hinta, String tayte) {
		super();
		this.nimi = nimi;
		this.hinta = hinta;
		taytenimi.add(tayte);
	}
	public Pizzalistaan(String nimi, Double hinta){
		super();
		this.nimi = nimi;
		this.hinta = hinta;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public ArrayList<String> getTaytenimi() {
		return taytenimi;
	}
	public void setTaytenimi(ArrayList<String> taytenimi) {
		this.taytenimi = taytenimi;
	}
	public void setTayte(String tayte){	
		taytenimi.add(tayte);		
	}
	public Double getHinta() {
		return hinta;
	}
	public void setHinta(Double hinta) {
		this.hinta = hinta;
	}
	@Override
	public String toString() {
		return "Pizzalistaan [nimi=" + nimi + ", hinta=" + hinta
				+ ", taytenimi=" + taytenimi + "]";
	}
}
