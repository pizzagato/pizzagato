package fi.omapizzeria.pizzagatto.admin;

public class Ip {
	String ip, aika;
	int tries;

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public Ip(String ip, String aika, int tries) {
		super();
		this.ip = ip;
		this.aika = aika;
		this.tries = tries;
	}

	public Ip() {
		super();
	}

	public Ip(String ip, String aika) {
		super();
		this.ip = ip;
		this.aika = aika;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAika() {
		return aika;
	}

	public void setAika(String aika) {
		this.aika = aika;
	}

	@Override
	public String toString() {
		return "Ip [ip=" + ip + ", aika=" + aika + ", tries=" + tries + "]";
	}
	
	
}
