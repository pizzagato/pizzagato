package fi.omapizzeria.pizzagatto.bean;

import java.util.ArrayList;

public class Tilaus {
	int id;
	Asiakastiedot asiakastiedot;
	ArrayList<Tilausrivi> tilausrivi;
	double kokonaishinta;
	public Tilaus() {
		super();
	}
	public Tilaus(int id, Asiakastiedot asiakastiedot,
			ArrayList<Tilausrivi> tilausrivi, double kokonaishinta) {
		super();
		this.id = id;
		this.asiakastiedot = asiakastiedot;
		this.tilausrivi = tilausrivi;
		this.kokonaishinta = kokonaishinta;
	}
	public Tilaus (Asiakastiedot asiakastiedot, ArrayList<Tilausrivi> tilausrivi, double kokonaishinta){
		this.asiakastiedot = asiakastiedot;
		this.tilausrivi = tilausrivi;
		this.kokonaishinta = kokonaishinta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Asiakastiedot getAsiakastiedot() {
		return asiakastiedot;
	}
	public void setAsiakastiedot(Asiakastiedot asiakastiedot) {
		this.asiakastiedot = asiakastiedot;
	}
	public ArrayList<Tilausrivi> getTilausrivi() {
		return tilausrivi;
	}
	public void setTilausrivi(ArrayList<Tilausrivi> tilausrivi) {
		this.tilausrivi = tilausrivi;
	}
	public double getKokonaishinta() {
		return kokonaishinta;
	}
	public void setKokonaishinta(double kokonaishinta) {
		this.kokonaishinta = kokonaishinta;
	}
	@Override
	public String toString() {
		return "Asiakastiedot: " + asiakastiedot.getNimi() + ", " + asiakastiedot.getOsoite() + ", " + asiakastiedot.getPstnro() + ", " + asiakastiedot.getPuhelin() + ", " + asiakastiedot.getSpost() + ", " + asiakastiedot.getLisatoiveet()
				+ ". Tilauksen tiedot: " + tilausrivi + ", kokonaishinta: " + kokonaishinta;
	}
	
}