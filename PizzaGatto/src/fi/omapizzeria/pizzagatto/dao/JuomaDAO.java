package fi.omapizzeria.pizzagatto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;






import fi.omapizzeria.pizzagatto.bean.Juoma;

/**
 * Hoitaa kaikki muutokset tietokannassa, jotka liittyvät juomiin
 */
public class JuomaDAO extends Yhteys {	
	
	public JuomaDAO() throws DAOPoikkeus {
		super();
	}
	
	/**
	 * Hakee Juomat-taulusta halutut juomat, pakkaa tiedot juoma-olioon ja oliot listaan.
	 * @param selectLause SQL-lause juomien hakuun
	 * @return Listan juomista
	 */
	private ArrayList<Juoma> haeJuomat(String selectLause) throws DAOPoikkeus {
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
		Connection yhteys = avaaYhteys();
		try {
			Statement selectHaku = yhteys.createStatement();
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){
				int id=selectTulokset.getInt("juoma_id");
				String nimi = selectTulokset.getString("nimi");
				Double hinta = selectTulokset.getDouble("hinta");
				String koko = selectTulokset.getString("koko");
				String tyyppi = selectTulokset.getString("tyyppi");
				Juoma j = new Juoma(id,nimi,hinta, koko, tyyppi); 
				juomat.add(j);
			
				
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
			}
		return juomat;
	}
	
	/**
	 * Hakee juoman hinnan
	 * @param juoma Juoman nimi, jonka hinta halutaan tietää
	 * @return juomatieto: Palauttaa annetun juoman hinnan
	 * @return 0: Jos juomaa ei löydy tietokannasta, palauttaa hinnan 0
	 */
	public double juomaHinta(String juoma) throws DAOPoikkeus, SQLException {
		Connection yhteys = avaaYhteys();
		String selectLause = "Select hinta FROM Juoma WHERE Juoma.nimi = '" + juoma + "'";
		Statement selectHaku = yhteys.createStatement();
		ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
		if (selectTulokset.next()) {
			double juomatieto = selectTulokset.getDouble("hinta");
			suljeYhteys(yhteys);
			return juomatieto;
		}else {
			suljeYhteys(yhteys);
			return 0;
		}
		
	}
	
	/**
	 * Hakee juoman id:n
	 * @param juoma Juoman nimi, jonka id halutaan tietää
	 * @return juomaId: Palauttaa annetun juoman id:n
	 * @return 0: Jos juomaa ei löydy tietokannasta, palauttaa id:n 0
	 */
	public int juomaid(String juoma) throws DAOPoikkeus, SQLException{
		Connection yhteys = avaaYhteys();
		String selectLause = "Select juoma_id FROM Juoma WHERE Juoma.nimi = '" + juoma + "'";
		Statement selectHaku = yhteys.createStatement();
		ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
		if (selectTulokset.next()) {
			int juomaId = selectTulokset.getInt("juoma_id");
			suljeYhteys(yhteys);
			return juomaId;
		}else {
			suljeYhteys(yhteys);
			return 0;
		}
	
	}
	
	/**
	 * Hakee juomat tilaussivulle
	 * @return juomat lista juomista, joiden tyyppi on virvoitusjuoma
	 */
	public ArrayList<Juoma> haeJuomatTilaus() throws DAOPoikkeus{
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
			String selectLause = "Select juoma_id, nimi, hinta, koko, tyyppi from Juoma where tyyppi='virvoitusjuoma'";
			juomat = haeJuomat(selectLause);
		
		return juomat;
	}
	
	/**
	 * Hakee juomat menuun
	 * @return juomat lista juomista
	 */
	public ArrayList<Juoma> haeJuomatMenu() throws DAOPoikkeus{
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
			String selectLause = "Select juoma_id,nimi, hinta, koko, tyyppi from Juoma";
			juomat = haeJuomat(selectLause);
			
		return juomat;
	}
	
	/**
	 * Lisää annetun juoman Juomat-tauluun
	 * @param j Lisättävä juoma
	 */
	public void lisaa(Juoma j) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into Juoma(nimi, hinta, koko, tyyppi) values(?,?,?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, j.getNimi());
			lause.setDouble(2, j.getHinta());
			lause.setString(3, j.getKoko());
			lause.setString(4, j.getTyyppi());
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("Juoma lisäämisyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}

	/**
	 * Poistaa annetun juoman Juomat-taulusta
	 * @param pois Poistettava juoma
	 */
	public void poista(Juoma pois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from Juoma WHERE juoma_id = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setInt(1, pois.getId());
			lause.executeUpdate();
		} catch (Exception e) {
			throw new DAOPoikkeus("Juoman poistoyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}	
}
