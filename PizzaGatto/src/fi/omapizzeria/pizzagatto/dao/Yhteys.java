package fi.omapizzeria.pizzagatto.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * T‰m‰ luokka hoitaa tietokantayhteyden avaamisen k‰ytt‰en DBConnectionProperties-luokasta saatuja asetuksia
 */

public class Yhteys {
	public Yhteys() throws DAOPoikkeus {
		try {
			Class.forName(DBConnectionProperties.getInstance().getProperty("driver")).newInstance();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokannan ajuria ei kyetty lataamaan.", e);
		}
	}
	//Metodi yhteyden avaamiseen
	public Connection avaaYhteys() throws DAOPoikkeus {
		try {
			return DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("url"),
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		} catch (Exception e) {
			throw new DAOPoikkeus("Tietokantayhteyden avaaminen ep‰onnistui", e);
		}
	}
	//Metodi yhteyden sulkemiseen
	public void suljeYhteys(Connection yhteys) throws DAOPoikkeus {
		try {
			if (yhteys != null && !yhteys.isClosed())
				yhteys.close();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantayhteys ei jostain syyst‰ suostu menem‰‰n kiinni.", e);
		}
	}
}
