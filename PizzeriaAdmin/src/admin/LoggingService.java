package admin;

import java.util.ArrayList;

import javax.servlet.ServletException;

public class LoggingService {
	private WindowsService windowService = new WindowsService();
	private BanningService banService = new BanningService();
	private String[] legitIdPassword = new String[2];
	private final static String legitId = "admin"; //Vaatii salauksen
	private final static String legitPassword = "salasana"; //Vaatii salauksen
	private final static int maxTries = 5; //Kuinka monennesta kirjautumisyrityksest‰ napsahtaa banaania
	
	/*
	 * Kirjautuu sis‰‰n. Kirjautumisikkuna tulee WindowsService-luokasta ja
	 * mahdollinen bannaus toteutetaan BanningService-luokan metodilla
	 */
	
	public boolean tryLogin(String ipAddress) throws ServletException {
		legitIdPassword[0] = legitId;
		legitIdPassword[1] = legitPassword;
		
		for (int i = 0; i < maxTries; i++) {
			ArrayList<String> idPassword = windowService.loginWindow();
			if (idPassword.size() == 0) {
				return false;
			} else if (idPassword.equals(legitIdPassword)) {
				return true;
			}
		}
		banService.banUser(ipAddress);
		return false;
	}
}
