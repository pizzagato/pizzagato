package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fi.omapizzeria.admin.bean.Pizza;

public class PizzaDAO extends Yhteys {

	
	
	public PizzaDAO() throws DAOPoikkeus {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Pizza> haeKaikki() throws DAOPoikkeus{	
		ArrayList<Pizza> pizzat = new ArrayList<Pizza>();
		Connection yhteys = avaaYhteys();
		try {
			//suoritetaan haku
			String sql = "select id, nimi, hinta from pizzat";
			Statement haku = yhteys.createStatement();
			ResultSet tulokset = haku.executeQuery(sql);
			//k�yd��n hakutulokset l�pi
			while(tulokset.next()) {
				int id = tulokset.getInt("id");
				String nimi = tulokset.getString("nimi");
				Double hinta = tulokset.getDouble("hinta");
				//lis�t��n pizza listaan
				Pizza p = new Pizza(id, nimi, hinta);
				pizzat.add(p);
			}
		} catch(Exception e) {
			//JOTAIN VIRHETT� TAPAHTUI
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		} finally {
			//LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
			}
		System.out.println("HAETTIIN TIETOKANNASTA PIZZAT: " +pizzat.toString());
		return pizzat;
	}
	
	public void lisaa(Pizza p) throws DAOPoikkeus{
		//avataan yhteys
		Connection yhteys = avaaYhteys();
		try {
			//suoritetaan haku
			//alustetaan sql-lause
			String sql = "insert into pizzat(nimi, hinta) values(?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			//t�ytet��n puuttuvat tiedot
			lause.setString(1, p.getNimi());
			lause.setDouble(2, p.getHinta());
			//suoritetaan lause
			lause.executeUpdate();
			System.out.println("LIS�TTIIN PIZZA TIETOKANTAAN: "+p);
		} catch(Exception e) {
			//JOTAIN VIRHETT� TAPAHTUI
			throw new DAOPoikkeus("Pizzan lis��misyritys aiheutti virheen", e);
		}finally {
			//LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}
	}
	
	public void poista(Pizza pois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from pizzat WHERE nimi = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, pois.getNimi());
			lause.executeUpdate();
			System.out.println("Poistettiin pizza tietokannasta: "+pois);
		} catch (Exception e) {
			throw new DAOPoikkeus("Pizzan poistoyritys aiheutti virheen", e);
		}finally {
			//LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}
		
	}
}


