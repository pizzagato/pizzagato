package fi.omapizzeria.pizzagatto.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.Yhteys;

public class AdminDAO extends Yhteys {	
	
	public AdminDAO() throws DAOPoikkeus {
		super();
	}
	
	/*
	 * Hakee tietokannasta kaikki bannatut ip-osoitteet ja bannausajan
	 */
	
	public ArrayList<Ip> getAllBanned() throws DAOPoikkeus{
		ArrayList<Ip> bannedIps = new ArrayList<Ip>();
		Connection yhteys = avaaYhteys();
		try {
			String selectLause = "Select ip, aika from banned";
			Statement selectHaku = yhteys.createStatement();
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){
				String ip = selectTulokset.getString("ip");
				String aika = selectTulokset.getString("aika");
				Ip i = new Ip(ip, aika);
				bannedIps.add(i);
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Bannattujen tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
		}
		return bannedIps;
	}
	
	/*
	 * Hakee kaikkien kirjautujayritt‰jien ip-osoitteet 
	 */
	
	public ArrayList<Ip> getAllAttempters() throws DAOPoikkeus{
		ArrayList<Ip> attempterIps = new ArrayList<Ip>();
		Connection yhteys = avaaYhteys();
		try {
			String selectLause = "Select ip, aika, tries from Attempters";
			Statement selectHaku = yhteys.createStatement();
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){
				String ip = selectTulokset.getString("ip");
				String aika = selectTulokset.getString("aika");
				int tries = selectTulokset.getInt("tries");
				Ip i = new Ip(ip, aika, tries);
				attempterIps.add(i);
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Kirjautujien tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
		}
		return attempterIps;
	}
	
	/*
	 * Bannaa k‰ytt‰j‰n ip-osoitteen
	 */
	
	public void banaaniVasara(Ip i) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into banned(ip, aika) values(?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, i.getIp());
			lause.setString(2, i.getAika());
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("IP:n bannaamisyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	
	/*
	 * Lis‰‰ uuden k‰ytt‰j‰n kirjautujayritt‰jien tauluun
	 */
	
	public void addAttempter(Ip i) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into Attempters(ip, aika, tries) values(?,?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, i.getIp());
			lause.setString(2, i.getAika());
			lause.setInt(3, i.getTries());
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("IP:n lis‰‰minen kirjautujien listaan aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	
	/*
	 * Kasvattaa kirjautumisyritt‰j‰n yrityskertoja yhdell‰
	 */
	
	public void riseAttempterTries(String ipAddress) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "Update Attempters set tries=tries+1 where ip = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, ipAddress);
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("Kirjautujan yrityskertojen lis‰‰minen aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	
	/*
	 * Poistaa ip-osoitteen bannattujen listalta
	 */
	
	public void removeBanned(String ipAddress) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from banned WHERE ip = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, ipAddress);
			lause.executeUpdate();
		} catch (Exception e) {
			throw new DAOPoikkeus("IP:n poistoyritys bannilistalta aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
	
	/*
	 * hakee Admin-taulusta adminin
	 */
	
	public ArrayList<String> getAdmin() throws DAOPoikkeus{
		ArrayList<String> admin = new ArrayList<String>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select nimi, password, salt from Admin";
			Statement selectHaku = yhteys.createStatement(); //Syˆtt‰‰ SQL:‰‰n komennon, jolla ip-osoitteet haetaan
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String nimi = selectTulokset.getString("nimi");
				String password = selectTulokset.getString("password");
				String salt = selectTulokset.getString("salt");
				admin.add(nimi);
				admin.add(password);
				admin.add(salt);
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Adminin tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
		}
		return admin;
	}
	
	/*
	 * Poistaa kirjautumisyritt‰j‰n yritt‰jien taulusta
	 */
	
	public void removeAttempter(String ipAddress) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from Attempters WHERE ip = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, ipAddress);
			lause.executeUpdate();
		} catch (Exception e) {
			throw new DAOPoikkeus("IP:n poistoyritys kirjautujalistalta aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
}