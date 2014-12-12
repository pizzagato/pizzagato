package fi.omapizzeria.pizzagatto.service;

import java.util.ArrayList;

import javax.servlet.ServletException;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fi.omapizzeria.pizzagatto.bean.Ip;
import fi.omapizzeria.pizzagatto.dao.AdminDAO;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;

/**
 * BanninService hoitaa k�ytt�jien bannauksen ja unbannauksen, k�ytt�j�n bannausstatuksen tarkistuksen
 * sek� kirjautumisyritt�jien lis�yksen ja poiston.
 *
 */
public class BanningService {
	private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	
	/*------------------T�RKEIT� TIETOJA-------------------*/
	/*|*/												/*|*/
	/*|*/ private final static int banLength = 5;   	/*|*/
	/*|*/ 												/*|*/
	/*|*/ private final static int triesLifetime = 30;	/*|*/
	/*|*/												/*|*/
	/*-----------------------------------------------------*/
	
	private AdminDAO dao = null;
	
	/**
	 * Hakee bannatut osoitteet
	 * @return lista bannatuista ip-osoitteista
	 */
	private ArrayList<Ip> getBannedIps() throws ServletException {
		ArrayList<Ip> bannedIps;
		try {
			dao = new AdminDAO();
			bannedIps = dao.getAllBanned(); 
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return bannedIps;
	}
	
	/**
	 * Hakee kirjautumisyritt�jien osoitteet
	 * @return lista k�ytt�jist�, jotka ovat yritt�neet kirjautua sis��n onnistumatta
	 */
	public ArrayList<Ip> getAttempterIps() throws ServletException {
		ArrayList<Ip> attempterIps;
		try {
			dao = new AdminDAO();
			attempterIps = dao.getAllAttempters(); 
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return attempterIps;
	}
	
	/**
	 * K�ytt�j� bannataan
	 * @param ipAddress bannattava osoite
	 */
	public void banUser(String ipAddress) throws ServletException {
		try {
			dao = new AdminDAO();
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		Ip ipObject = new Ip();
		DateTime banTime = new DateTime();
		String sBanTime = banTime.toString(formatter);
		ipObject.setIp(ipAddress);
		ipObject.setAika(sBanTime);
		try {
			dao.banaaniVasara(ipObject);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * Hakee banned-taulusta bannatut osoitteet ja bannausajan.
	 * Jos ip on ollut listalla halutun ajan, se poistetaan
	 */
	
	public void unBan() throws ServletException {
		ArrayList<Ip> bannedIps;
		
		bannedIps = getBannedIps();
		for (int i = 0; i < bannedIps.size(); i++) {
			DateTime bannedTime = formatter.parseDateTime(bannedIps.get(i).getAika());
			DateTime currentTime = new DateTime();
			Minutes diff = Minutes.minutesBetween(bannedTime, currentTime);
			int timeDifference = diff.getMinutes();
			if (timeDifference >= banLength) {
				try {
					dao.removeBanned(bannedIps.get(i).getIp());
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
			}
		}
	}
	
	/**
	 * Hakee Attempters-taulusta jokainen kirjautumisyritt�j�n ja ensimm�isen kirjautumisyrityksen ajan. 
	 * Jos ip on ollut listalla halutun ajan, se poistetaan
	 */
	
	public void clearAttempts() throws ServletException {
		
		ArrayList<Ip> attempterIps;
		attempterIps = getAttempterIps();
		for (int i = 0; i < attempterIps.size(); i++) {
			DateTime bannedTime = formatter.parseDateTime(attempterIps.get(i).getAika());
			DateTime currentTime = new DateTime();
			Minutes diff = Minutes.minutesBetween(bannedTime, currentTime);
			int timeDifference = diff.getMinutes();
			if (timeDifference >= triesLifetime) {
				try {
					dao.removeAttempter(attempterIps.get(i).getIp());
				} catch (DAOPoikkeus e) {
					throw new ServletException(e);
				}
			}
		}
	}
	
	/**
	 * Poistaa kirjautumisyritt�jien taulusta l�hetetyn ip-osoitteen
	 * @param poistettava ip-osoite
	 */
	
	public void removeAttempter(String ipAddress) throws ServletException {
		try {
			dao = new AdminDAO();
			dao.removeAttempter(ipAddress);
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	}
	
	/**
	 * Lis�� kirjautumisyritt�jien tauluun l�htetetyn ip-osoitteen
	 * @param lis�tt�v� ip-osoite
	 */
	
	public void addAttempter(String ipAddress) throws ServletException {
		try {
			dao = new AdminDAO();
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
			dao.addAttempter(ipObject);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}	
	
	/**
	 * Tarkastaa, onko k�ytt�j�n ip-osoite bannattu
	 * @param tarkastettava ip-osoite
	 * @return true: k�ytt�j� ei ole bannattu
	 * @return false: k�ytt�j� on bannattu
	 */
	
	public boolean userBanStatus(String ipAddress) throws ServletException {
		ArrayList<Ip> bannedIps = new ArrayList<Ip>();
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
