package fi.omapizzeria.pizzagatto.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;

public class TilausDAO extends Yhteys {
	
	public TilausDAO (String pizza) throws DAOPoikkeus, SQLException{
		pizzatieto(pizza);
	}
	
	public void tilausriviin(){
		
	}

	public double pizzatieto(String pizza) throws DAOPoikkeus, SQLException{
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		String selectLause = "Select hinta FROM Pizza WHERE Pizza.nimi = '" + pizza + "'";
		Statement selectHaku = yhteys.createStatement(); //Syöttää SQL:ään komennon, jolla valitaan pizzat
		ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
		if (selectTulokset.next()) {	// Otetaan pitsan hinta ylös, mikäli sitä ei löydy, palauttaa hinnaksi 0.
			double pizzatieto = selectTulokset.getDouble("hinta");
			suljeYhteys(yhteys);
			return pizzatieto;			
		}else {
			suljeYhteys(yhteys);
			return 0;
		}
	
	}
	public double juomatieto(String juoma) throws DAOPoikkeus, SQLException {
		Connection yhteys = avaaYhteys();
		String selectLause = "Select hinta FROM Juoma WHERE Juoma.nimi = '" + juoma + "'";
		Statement selectHaku = yhteys.createStatement();
		ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
		if (selectTulokset.next()) {	// Otetaan juoman hinta ylös, mikäli sitä ei löydy, palauttaa hinnaksi 0.
			double juomatieto = selectTulokset.getDouble("hinta");
			suljeYhteys(yhteys);
			return juomatieto;
		}else {
			suljeYhteys(yhteys);
			return 0;
		}
		
	}
	

}
