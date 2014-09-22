package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fi.omapizzeria.admin.bean.Pizza;

public class PizzaDAO {

	public PizzaDAO() throws DAOPoikkeus {
		try {
			Class.forName(DBConnectionProperties.getInstance().getProperty("driver")).newInstance();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokannan ajuria ei kyetty lataamaan.", e);
		}
	}
	
	public Connection avaaYhteys() throws DAOPoikkeus {
		try {
			return DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("url"),
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		} catch (Exception e) {
			throw new DAOPoikkeus("Tietokantayhteyden avaaminen ep�onnistui", e);
		}
	}
	
	public void suljeYhteys(Connection yhteys) throws DAOPoikkeus {
		try {
			if (yhteys != null && !yhteys.isClosed())
				yhteys.close();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantayhteys ei jostain syyst� suostu menem��n kiinni.", e);
		}
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
}


