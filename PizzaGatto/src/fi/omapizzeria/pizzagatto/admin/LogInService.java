package fi.omapizzeria.pizzagatto.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;

public class LogInService {
	private BanningService banService = new BanningService();
	private AdminDAO admindao;
	private String[] legitIdPassword = new String[2];
	private final static String legitId = "kissa"; //Vaatii salauksen
	private final static String legitPassword = "siika"; //Vaatii salauksen
	
	/*------------------T�RKEIT� TIETOJA-------------------*/
	/*|*/												/*|*/
	/*|*/ 											  	/*|*/
	/*|*/ private final static int maxTries = 5;		/*|*/
	/*|*/ 												/*|*/
	/*|*/												/*|*/
	/*-----------------------------------------------------*/
	
	
	
	
	/*
	 * Kirjautuu sis��n. Kirjautumisikkuna tulee WindowsService-luokasta ja
	 * mahdollinen bannaus toteutetaan BanningService-luokan metodilla
	 */
	
	public int tryLogin(String ipAddress, ArrayList<String> namepassword) throws ServletException, NoSuchAlgorithmException, UnsupportedEncodingException {
		AdminUser ad = new AdminUser(legitId, legitPassword);
		
		boolean dontAdd = false; //Kello 3:40 purkkaviritelm�n huipentuma

		
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {		//Horrible shit coming...
			if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {	
				dontAdd = true;
			}
		}
		
		if (dontAdd == false) {		//Ohjelmointikurssista 0/5
			banService.addAttempter(ipAddress);
		}
		
		/*
		 * T�m� puolestaan on reiki� t�ynn�. Kai.
		 */
		if (ad.vertaaSalasanaa(namepassword.get(1))) {
			banService.removeAttempter(ipAddress);
			return 1;
		}
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {
			 if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {
				
				try {
					admindao = new AdminDAO();
					admindao.riseAttempterTries(ipAddress);
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
				if (banService.getAttempterIps().get(i).getTries() >= maxTries) {
					banService.banUser(ipAddress);
					banService.removeAttempter(ipAddress);
					return 0;
				} 
			}
		}
		return 2;			
	}
}

//namepassword.get(0).equals(legitId) && namepassword.get(1).equals(legitPassword)
