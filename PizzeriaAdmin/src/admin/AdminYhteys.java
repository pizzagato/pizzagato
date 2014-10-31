package admin;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.DAOPoikkeus;

/**
 * T‰m‰ luokka hoitaa tietokantayhteyden avaamisen k‰ytt‰en AdminDBConnectionProperties-luokasta saatuja asetuksia
 */

public class AdminYhteys {
	public AdminYhteys() throws DAOPoikkeus {
		try {
			Class.forName(AdminDBConnectionProperties.getInstance().getProperty("driver")).newInstance();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokannan ajuria ei kyetty lataamaan.", e);
		}
	}
	//Metodi yhteyden avaamiseen
	public Connection avaaYhteys() throws DAOPoikkeus {
		try {
			return DriverManager.getConnection(
					AdminDBConnectionProperties.getInstance().getProperty("url"),
					AdminDBConnectionProperties.getInstance().getProperty("username"),
					AdminDBConnectionProperties.getInstance().getProperty("password"));
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
