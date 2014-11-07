package fi.omapizzeria.pizzagatto.admin;

import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;

public class LogInService {
	private BanningService banService = new BanningService();
	private AdminDAO ip;
	private String[] legitIdPassword = new String[2];
	private final static String legitId = "admin"; //Vaatii salauksen
	private final static String legitPassword = "salasana"; //Vaatii salauksen
	
	/*------------------TÄRKEITÄ TIETOJA-------------------*/
	/*|*/												/*|*/
	/*|*/ 											  	/*|*/
	/*|*/ private final static int maxTries = 5;		/*|*/
	/*|*/ 												/*|*/
	/*|*/												/*|*/
	/*-----------------------------------------------------*/
	
	
	
	
	/*
	 * Kirjautuu sisään. Kirjautumisikkuna tulee WindowsService-luokasta ja
	 * mahdollinen bannaus toteutetaan BanningService-luokan metodilla
	 */
	
	public int tryLogin(String ipAddress, ArrayList<String> namepassword) throws ServletException {
		legitIdPassword[0] = legitId;
		legitIdPassword[1] = legitPassword;
		boolean dontAdd = false; //Kello 3:40 purkkaviritelmän huipentuma

		
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {		//Horrible shit coming...
			if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {	
				dontAdd = true;
			}
		}
		
		if (dontAdd == false) {		//Ohjelmointikurssista 0/5
			banService.addAttempter(ipAddress);
		}
		
		/*
		 * Tämä puolestaan on reikiä täynnä. Kai.
		 */
		if (namepassword.get(0).equals(legitId) && namepassword.get(1).equals(legitPassword)) {
			return 1;
		}
		
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {
			if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {
				try {
					ip = new AdminDAO();
					ip.riseAttempterTries(ipAddress);
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
