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
	public ArrayList<Ip> getBanned() throws DAOPoikkeus{
		ArrayList<Ip> ips = new ArrayList<Ip>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select banned.ip, aika from banned";
			Statement selectHaku = yhteys.createStatement(); //Syˆtt‰‰ SQL:‰‰n komennon, jolla ip-osoitteet haetaan
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String ip = selectTulokset.getString("banned.ip");
				String aika = selectTulokset.getString("aika");
				Ip i = new Ip(ip, aika); //Yhdistet‰‰n Ip-olioon tiedot
				ips.add(i);
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
		}
		return ips;
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
			throw new DAOPoikkeus("IP:n lis‰‰misyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	//Metodi jolla bannattu IP poistetaan listalta
	public void poista(Ip ipPois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from banned WHERE ip = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, ipPois.getIp());
			lause.executeUpdate();
		} catch (Exception e) {
			throw new DAOPoikkeus("IP:n poistoyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
}