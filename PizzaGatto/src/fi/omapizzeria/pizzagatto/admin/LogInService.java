package fi.omapizzeria.pizzagatto.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;

public class LogInService {
	private BanningService banService = new BanningService();
	private AdminDAO admindao;
	
	/*------------------T�RKEIT� TIETOJA-------------------*/
	/*|*/												/*|*/
	/*|*/ 											  	/*|*/
	/*|*/ private final static int maxTries = 5;		/*|*/
	/*|*/ 												/*|*/
	/*|*/												/*|*/
	/*-----------------------------------------------------*/
	
	/*
	 * Metodille l�hetet��n k�ytt�j�n sy�tt�m�t tunnukset ja ip-osoite. Metodi palauttaa numeron, jonka perusteella k�ytt�j�lle n�ytet��n haluttu sivu
	 */
	
	public int tryLogin(String ipAddress, ArrayList<String> namepassword) throws ServletException, NoSuchAlgorithmException, UnsupportedEncodingException, DAOPoikkeus {
		admindao = new AdminDAO();
		ArrayList <String> legit = new ArrayList<String>(admindao.getAdmin());
		AdminUser ad = new AdminUser(namepassword.get(0), namepassword.get(1), legit.get(2));
		ad.setPasswordHash(legit.get(1));
		boolean dontAdd = false;

		/*
		 * K�yd��n l�pi kirjautumisyritt�jien ip-osoitteet. Jos k�ytt�j�n osoitete l�ytyy jo listalta sit� ei lis�t�.
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
		 * Verrataan tietokannassa olevaa k�ytt�j�tunnusta sy�tettyyn k�ytt�j�tunnukseen ja
		 * tietokannassa olevaa salattua salasanaa k�ytt�j�n sy�tt�m��n salasanaan, joka on salattu
		 * k�ytt�en samaa suolaa kuin adminin salasanassa
		 */
		
		if (ad.vertaaSalasanaa(namepassword.get(1)) && namepassword.get(0).equals(legit.get(0))) {
			banService.removeAttempter(ipAddress);
			//Arvolla 1 k�ytt�j� p��see sis��n
			return 1;
		} 
		for (int i = 0; i < banService.getAttempterIps().size(); i++) {
			// K�ytt�j� on t�ss� vaiheessa antanut v��r�t tunnukset
			if (banService.getAttempterIps().get(i).getIp().equals(ipAddress)) {
				//Kasvatetaan kirjautumiskertoja
				try {
					admindao = new AdminDAO();
					admindao.riseAttempterTries(ipAddress);
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
				//Jos kirjautumiskertoja tulee liikaa k�ytt�j� b�nn�t��n
				if (banService.getAttempterIps().get(i).getTries() >= maxTries) {
					banService.banUser(ipAddress);
					banService.removeAttempter(ipAddress);
					//Arvolla 0 k�ytt�j� b�nn�t��n
					return 0;
				} 
			}
		}
		//Arvolla 2 k�ytt�j�lle kerrotaan ett� annetut tunnukset ovat v��r�t
		return 2;			
	}
}
