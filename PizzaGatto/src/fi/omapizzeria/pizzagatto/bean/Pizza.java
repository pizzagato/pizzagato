package fi.omapizzeria.pizzagatto.bean;

import java.util.ArrayList;

public class Pizza extends Tuote{	
	ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
	int status;

	public Pizza() {
		super();
	}

	public Pizza(int id, String nimi, Double hinta) {
		super(id, nimi, hinta);
	}
	public Pizza(int id, String nimi, Double hinta, int status) {
		super(id, nimi, hinta);
	}
	
	public Pizza(String nimi, Double hinta){
		super();
		this.nimi = nimi;
		this.hinta = hinta;
	}
	public Pizza(String nimi){
		super();
		this.nimi = nimi;
	}
	public Pizza(int id){
		this.id=id;
	}
	
	public Pizza(int id, int status){
		this.id=id;
		this.status=status;
	}
	

	public Pizza( String nimi, Double hinta, int status) {
		super(status,nimi, hinta);
		this.status = status;
	}
	
	public Pizza( String nimi, Double hinta, int status, ArrayList<Tayte> taytteet) {
		super(status,nimi, hinta);
		this.status = status;
		this.taytteet = taytteet;
	}
	
	
	

	public Pizza(ArrayList<Tayte> taytteet) {
		super();
		this.taytteet = taytteet;
	}
	
	
	public Pizza(String nimi, Double hinta, ArrayList<Tayte> taytteet,
			int status) {
		super();
		this.nimi = nimi;
		this.hinta = hinta;
		this.taytteet = taytteet;
		this.status = status;
	}
	public Pizza(String nimi, int status) {
		super();
		this.nimi = nimi;
		this.status = status;
	}
	
	

	public ArrayList<Tayte> getTaytteet() {
		return taytteet;
	}

	public void setTaytteet(ArrayList<Tayte> taytteet) {
		this.taytteet = taytteet;
	}
	
	public void addTayte(Tayte t) {
		this.taytteet.add(t);
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pizza id=" + id + ", nimi= " + nimi + " taytteet= " + taytteet
				+ ", hinta= " + hinta;
	}

	


}
	