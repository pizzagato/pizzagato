package fi.omapizzeria.pizzagatto.bean;

public class Tayte {
	int id;
	String nimi;
	
	
	public Tayte() {
		super();		
	}


	public Tayte(int id, String nimi) {
		super();
		this.id = id;
		this.nimi = nimi;
	}
	
	
	public Tayte(int id) {
		super();
		this.id = id;
	}


	public Tayte(String nimi){
		super();
		this.nimi=nimi;
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


	@Override
	public String toString() {
		return nimi;
	}

}
