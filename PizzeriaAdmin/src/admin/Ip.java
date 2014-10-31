package admin;

public class Ip {
	String ip, aika;

	public Ip() {
		super();
		// TODO Auto-generated constructor stub
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
		return "Ip [ip=" + ip + ", aika=" + aika + "]";
	}
	
	
}
