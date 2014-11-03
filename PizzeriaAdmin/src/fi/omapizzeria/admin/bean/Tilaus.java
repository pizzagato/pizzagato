package fi.omapizzeria.admin.bean;

public class Tilaus {
	
	String nimi;
	String puhelin;
	String spost;
	String osoite;
	String pstnro;
	String lisatoiveet;
	public Tilaus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tilaus(String nimi, String puhelin, String spost,
			String osoite, String pstnro, String lisatoiveet) {
		super();
		this.nimi = nimi;
		this.puhelin = puhelin;
		this.spost = spost;
		this.osoite = osoite;
		this.pstnro = pstnro;
		this.lisatoiveet = lisatoiveet;
	}
	public Tilaus(String nimi, String spost, String osoite,
			String pstnro, String lisatoiveet) {
		super();
		this.nimi = nimi;
		this.spost = spost;
		this.osoite = osoite;
		this.pstnro = pstnro;
		this.lisatoiveet = lisatoiveet;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public String getPuhelin() {
		return puhelin;
	}
	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}
	public String getSpost() {
		return spost;
	}
	public void setSpost(String spost) {
		this.spost = spost;
	}
	public String getOsoite() {
		return osoite;
	}
	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}
	public String getPstnro() {
		return pstnro;
	}
	public void setPstnro(String pstnro) {
		this.pstnro = pstnro;
	}
	public String getLisatoiveet() {
		return lisatoiveet;
	}
	public void setLisatoiveet(String lisatoiveet) {
		this.lisatoiveet = lisatoiveet;
	}
	@Override
	public String toString() {
		return "Asiakastiedot [nimi=" + nimi + ", puhelin=" + puhelin
				+ ", spost=" + spost + ", osoite=" + osoite + ", pstnro="
				+ pstnro + ", lisatoiveet=" + lisatoiveet + "]";
	}
	
	

}
