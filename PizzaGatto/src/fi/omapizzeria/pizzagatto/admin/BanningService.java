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
	
	/*------------------TÄRKEITÄ TIETOJA-------------------*/
	/*|*/												/*|*/
	/*|*/ private final static int banLength = 0;   	/*|*/
	/*|*/ 												/*|*/
	/*|*/ private final static int triesLifetime = 60;	/*|*/
	/*|*/												/*|*/
	/*-----------------------------------------------------*/
	
	private ArrayList<Ip> bannedIps = new ArrayList<Ip>();
	private ArrayList<Ip> attempterIps = new ArrayList<Ip>();
	private AdminDAO ip = null;
	
	/*
	 * Hakee bannatut osoitteet
	 */
	
	private ArrayList<Ip> getBannedIps() throws ServletException {
		try {
			ip = new AdminDAO();
			bannedIps = ip.getAllBanned(); 
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return bannedIps;
	}
	
	/*
	 * Hakee kirjautumisyrittäjien osoitteet
	 */
	
	public ArrayList<Ip> getAttempterIps() throws ServletException {
		try {
			ip = new AdminDAO();
			attempterIps = ip.getAllAttempters(); 
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return attempterIps;
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
	}
	
	/*
	 * Hakee banned-taulusta jokainen bannauksen ajan. Jos ip on ollut listalla halutun ajan, se poistetaan
	 */
	
	public void unBan() throws ServletException {
		bannedIps = getBannedIps();
		for (int i = 0; i < bannedIps.size(); i++) {
			DateTime bannedTime = formatter.parseDateTime(bannedIps.get(i).getAika());
			DateTime currentTime = new DateTime();
			Minutes diff = Minutes.minutesBetween(bannedTime, currentTime);
			int timeDifference = diff.getMinutes();
			if (timeDifference >= banLength) {
				try {
					ip.removeBanned(bannedIps.get(i).getIp());
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
			}
		}
	}
	
	/*
	 * Hakee Attempters-taulusta jokainen yrittäjän ensimmäisen kirjautumisyrityksen ajan. Jos ip on ollut listalla halutun ajan, se poistetaan
	 */
	
	public void clearAttempts() throws ServletException {
		attempterIps = getAttempterIps();
		for (int i = 0; i < attempterIps.size(); i++) {
			DateTime bannedTime = formatter.parseDateTime(attempterIps.get(i).getAika());
			DateTime currentTime = new DateTime();
			Minutes diff = Minutes.minutesBetween(bannedTime, currentTime);
			int timeDifference = diff.getMinutes();
			if (timeDifference >= triesLifetime) {
				try {
					ip.removeAttempter(attempterIps.get(i).getIp());
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
			}
		}
	}
	
	/*
	 * Poistaa kirjautumisyrittäjien taulusta metodille lähtetetyn ip-osoitteen
	 */
	
	public void removeAttempter(String ipAddress) throws ServletException {
		try {
			ip = new AdminDAO();
			ip.removeAttempter(ipAddress);
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	}
	
	/*
	 * Lisää kirjautumisyrittäjien tauluun metodille lähtetetyn ip-osoitteen
	 */
	
	public void addAttempter(String ipAddress) throws ServletException {
		try {
			ip = new AdminDAO();
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		Ip ipObject = new Ip();
		DateTime banTime = new DateTime();
		String sBanTime = banTime.toString(formatter);
		int tries = 0;
		ipObject.setIp(ipAddress);
		ipObject.setAika(sBanTime);
		ipObject.setTries(tries);
		try {
			ip.addAttempter(ipObject);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}	
	
	/*
	 * Tarkastaa, onko nykyinen käyttäjä bannattu
	 */
	
	public boolean userBanStatus(String ipAddress) throws ServletException {
		bannedIps = getBannedIps();
		boolean allowedUser = true;
		for (int i = 0; i < bannedIps.size(); i++) {
			if (ipAddress.equals(bannedIps.get(i).getIp())) {
				@SuppressWarnings("unused")
				int timeLeft = banLength - Minutes.minutesBetween(formatter.parseDateTime(bannedIps.get(i).getAika()), new DateTime()).getMinutes();
				allowedUser = false;
				return allowedUser;
			}
		} return allowedUser;
	}
}
