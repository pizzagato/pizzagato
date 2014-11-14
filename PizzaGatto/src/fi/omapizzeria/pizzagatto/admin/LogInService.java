package fi.omapizzeria.pizzagatto.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;

public class LogInService {
	private BanningService banService = new BanningService();
	private AdminDAO admindao;
	
	/*------------------TÄRKEITÄ TIETOJA-------------------*/
	/*|*/												/*|*/
	/*|*/ 											  	/*|*/
	/*|*/ private final static int maxTries = 5;		/*|*/
	/*|*/ 												/*|*/
	/*|*/												/*|*/
	/*-----------------------------------------------------*/
	
	/*
	 * Metodille lähetetään käyttäjän syöttämät tunnukset ja ip-osoite. Metodi palauttaa numeron, jonka perusteella käyttäjälle näytetään haluttu sivu
	 */
	
	public int tryLogin(String ipAddress, ArrayList<String> namepassword) throws ServletException, NoSuchAlgorithmException, UnsupportedEncodingException, DAOPoikkeus {
		admindao = new AdminDAO();
		ArrayList <String> legit = new ArrayList<String>(admindao.getAdmin());
		AdminUser ad = new AdminUser(namepassword.get(0), namepassword.get(1), legit.get(2));
		ad.setPasswordHash(legit.get(1));
		boolean dontAdd = false;

		/*
		 * Käydään läpi kirjautumisyrittäjien ip-osoitteet. Jos käyttäjän osoitete löytyy jo listalta sitä ei lisätä.
		 */
		
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {
			if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {	
				dontAdd = true;
			}
		}
		
		if (dontAdd == false) {
			banService.addAttempter(ipAddress);
		}
		
		/*
		 * Verrataan tietokannassa olevaa käyttäjätunnusta syötettyyn käyttäjätunnukseen ja
		 * tietokannassa olevaa salattua salasanaa käyttäjän syöttämään salasanaan, joka on salattu
		 * käyttäen samaa suolaa kuin adminin salasanassa
		 */
		
		if (ad.vertaaSalasanaa(namepassword.get(1)) && namepassword.get(0).equals(legit.get(0))) {
			banService.removeAttempter(ipAddress);
			//Arvolla 1 käyttäjä pääsee sisään
			return 1;
		} 
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {
			// Käyttäjä on tässä vaiheessa antanut väärät tunnukset
			if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {
				//Kasvatetaan kirjautumiskertoja
				try {
					admindao = new AdminDAO();
					admindao.riseAttempterTries(ipAddress);
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
				//Jos kirjautumiskertoja tulee liikaa käyttäjä bännätään
				if (banService.getAttempterIps().get(i).getTries() >= maxTries) {
					banService.banUser(ipAddress);
					banService.removeAttempter(ipAddress);
					//Arvolla 0 käyttäjä bännätään
					return 0;
				} 
			}
		}
		//Arvolla 2 käyttäjälle kerrotaan että annetut tunnukset ovat väärät
		return 2;			
	}
}
