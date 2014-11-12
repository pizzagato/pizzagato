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
	
	//getBanned-metodi hakee tietokannasta kaikki bannatut ip-osoitteet ja bannausajan
	public ArrayList<Ip> getAllBanned() throws DAOPoikkeus{
		ArrayList<Ip> bannedIps = new ArrayList<Ip>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select ip, aika from banned";
			Statement selectHaku = yhteys.createStatement(); //Syˆtt‰‰ SQL:‰‰n komennon, jolla ip-osoitteet haetaan
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String ip = selectTulokset.getString("ip");
				String aika = selectTulokset.getString("aika");
				Ip i = new Ip(ip, aika); //Yhdistet‰‰n Ip-olioon tiedot
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
	//Hakee kaikki, jotka ovat koittaneet kirjautua
	public ArrayList<Ip> getAllAttempters() throws DAOPoikkeus{
		ArrayList<Ip> attempterIps = new ArrayList<Ip>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select ip, aika, tries from Attempters";
			Statement selectHaku = yhteys.createStatement(); //Syˆtt‰‰ SQL:‰‰n komennon, jolla ip-osoitteet haetaan
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String ip = selectTulokset.getString("ip");
				String aika = selectTulokset.getString("aika");
				int tries = selectTulokset.getInt("tries");
				Ip i = new Ip(ip, aika, tries); //Yhdistet‰‰n Ip-olioon tiedot
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
	//Metodi jolla bannataan ip
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
	
	
	public void riseAttempterTries(String ipAddress) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "Update Attempters set tries=tries+1 where ip = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql); //Syˆtt‰‰ SQL:‰‰n komennon, jolla ip-osoitteet haetaan
			lause.setString(1, ipAddress);
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("Kirjautujan yrityskertojen lis‰‰minen aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	
	//Metodi jolla bannattu IP poistetaan listalta
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
	
	public ArrayList<String> getAdmin() throws DAOPoikkeus{
		ArrayList<String> admin = new ArrayList<String>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select nimi, password from Admin";
			Statement selectHaku = yhteys.createStatement(); //Syˆtt‰‰ SQL:‰‰n komennon, jolla ip-osoitteet haetaan
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String nimi = selectTulokset.getString("nimi");
				String password = selectTulokset.getString("password");
				admin.add(nimi);
				admin.add(password);
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Adminin tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
		}
		return admin;
	}
	
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