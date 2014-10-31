package admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
	/**
	 * Tämä luokka hoitaa tietokantayhteyden asetusten lukemisen properties-tiedostosta
	 */
public class AdminDBConnectionProperties {
	public static final String FILE_NAME = "Admindb_connection.properties";
	private static AdminDBConnectionProperties instance;
	private Properties properties;
	private AdminDBConnectionProperties() throws IOException {
		// ladataan arvot properties-tiedostosta
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		properties = new Properties();
		properties.load(inputStream);
	}
	public String getProperty(String nimi) {
		return this.properties.getProperty(nimi);
	}
	public static AdminDBConnectionProperties getInstance() throws IOException {
		if (instance == null) {
			instance = new AdminDBConnectionProperties();
		}
		return instance;
	}
}