package fi.omapizzeria.pizzagatto.bean;

public class Tilausrivi {
	int id;
	Pizza pizza;
	int kpl;
	double kokonaishinta;
	public Tilausrivi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tilausrivi(int id, Pizza pizza, int kpl, double kokonaishinta) {
		super();
		this.id = id;
		this.pizza = pizza;
		this.kpl = kpl;
		this.kokonaishinta = kokonaishinta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
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
		return "Tilausrivi [id=" + id + ", pizza=" + pizza + ", kpl=" + kpl
				+ ", kokonaishinta=" + kokonaishinta + "]";
	}
	
}


