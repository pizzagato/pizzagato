package fi.omapizzeria.pizzagatto.bean;

public class Juoma extends Tuote {
	String koko;
	String tyyppi;
	public Juoma() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Juoma(int id, String nimi, Double hinta) {
		super(id, nimi, hinta);
		// TODO Auto-generated constructor stub
	}
	public Juoma(String koko, String tyyppi) {
		super();
		this.koko = koko;
		this.tyyppi = tyyppi;
	}
	public Juoma(String nimi, Double hinta, String koko, String tyyppi){
		super();
		this.nimi = nimi;
		this.hinta = hinta;
		this.koko = koko;
		this.tyyppi = tyyppi;
	}
	public Juoma(int id, String nimi, Double hinta, String koko, String tyyppi){
		super(id, nimi, hinta);
	}
	public Juoma(String nimi){
		super();
		this.nimi = nimi;
	}
	
	public Juoma(int id){
		this.id = id;
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
		return  super.toString()+nimi + " koko=" + koko + ", tyyppi=" + tyyppi + "]";
	}
}