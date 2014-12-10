package fi.omapizzeria.pizzagatto.bean;

import java.text.DecimalFormat;

public class Tilausrivi {
	DecimalFormat desi = new DecimalFormat("0.00");
	int id;
	Tuote tuote;
	int kpl;
	double kokonaishinta;
	public Tilausrivi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tilausrivi(int id, Tuote tuote, int kpl, double kokonaishinta) {
		super();
		this.id = id;
		this.tuote = tuote;
		this.kpl = kpl;
		this.kokonaishinta = kokonaishinta;
	}
	public Tilausrivi(Tuote tuote, int kpl, double kokonaishinta){
		super();
		this.tuote = tuote;
		this.kpl = kpl;
		this.kokonaishinta = kokonaishinta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tuote getTuote() {
		return tuote;
	}
	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}
	public int getKpl() {
		return kpl;
	}
	public void setKpl(int kpl) {
		this.kpl = kpl;
	}
	public double getKokonaishinta() {
		return kokonaishinta;
	}
	public void setKokonaishinta(double kokonaishinta) {
		this.kokonaishinta = kokonaishinta;
	}
	@Override
	public String toString() {
		return tuote.getNimi() + ", " + kpl + " kpl"
				+ ", kokonaishinta: " + desi.format(kokonaishinta);
	}
}
	

