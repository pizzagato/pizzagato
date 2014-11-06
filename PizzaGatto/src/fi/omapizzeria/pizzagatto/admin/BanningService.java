package fi.omapizzeria.pizzagatto.admin;

import java.util.ArrayList;

import javax.servlet.ServletException;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;

public class BanningService {
	private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	private WindowsService windowService = new WindowsService();
	
	/*----------BANNIN PITUUS MINUUTTEINA-------------*/
	/*|*/											/*|*/
	/*|*/ private final static int banLength = 30;  /*|*/
	/*|*/											/*|*/
	/*------------------------------------------------*/
	
	private ArrayList<Ip> ips = new ArrayList<Ip>(); //Lista bannatyista osoitteista
	private AdminDAO ip = null;
	
	/*
	 * Hakee bannatut osoitteet
	 */
	
	private ArrayList<Ip> getIps() throws ServletException {
		try {
			ip = new AdminDAO();
			ips = ip.getBanned(); 
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return ips;
	}
	
	/*
	 * Käyttäjä bannataan
	 */
	
	public void banUser(String ipAddress) throws ServletException {
		try {
			ip = new AdminDAO();
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		Ip ipObject = new Ip();
		DateTime banTime = new DateTime();
		String sBanTime = banTime.toString(formatter);
		ipObject.setIp(ipAddress);
		ipObject.setAika(sBanTime);
		try {
			ip.banaaniVasara(ipObject);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		windowService.bannedWindow();
	}
	
	/*
	 * Hakee ip-listasta jokainen bannauksen ajan. Jos ip on ollut listalla halutun ajan, se poistetaan
	 */
	
	public void unBan() throws ServletException {
		ips = getIps();
		for (int i = 0; i < ips.size(); i++) {
			DateTime bannedTime = formatter.parseDateTime(ips.get(i).getAika()); //Haetaan listasta jokaisen ip:n bannausaika
			DateTime currentTime = new DateTime(); //Verrokiksi nykyinen aika
			Minutes diff = Minutes.minutesBetween(bannedTime, currentTime); //Verrataan aikoja keskenään, erotus minuutteina
			int timeDifference = diff.getMinutes(); //muutetaan int-muotoon
			if (timeDifference >= banLength) { //Jos aikaero (eli bannattuna oltu aika) on isompi kuin haluttu bannin pituus otetaan bannattu ip listalta pois
				try {
					ip.poista(ips.get(i));
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
			}
		}
	}
	
	/*
	 * Tarkastaa, onko nykyinen käyttäjä bannattu
	 */
	
	public boolean userBanStatus(String ipAddress) throws ServletException {
		ips = getIps();
		boolean allowedUser = true;
		for (int i = 0; i < ips.size(); i++) {
			if (ipAddress.equals(ips.get(i).getIp())) {
				int timeLeft = banLength - Minutes.minutesBetween(formatter.parseDateTime(ips.get(i).getAika()), new DateTime()).getMinutes();
				windowService.isBannedWindow(timeLeft);
				allowedUser = false;
				return allowedUser;
			}
		} return allowedUser;
	}
}
