package fi.omapizzeria.pizzagatto.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





import fi.omapizzeria.pizzagatto.bean.Juoma;


public class JuomaDAO extends Yhteys {	
	
	public JuomaDAO() throws DAOPoikkeus {
		super();
	}
	
	private ArrayList<Juoma> haeJuomat(String selectLause) throws DAOPoikkeus {
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			Statement selectHaku = yhteys.createStatement(); //Syöttää SQL:ään komennon, jolla valitaan juomat
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
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
	
	
	
	
	public ArrayList<Juoma> haeJuomatTilaus() throws DAOPoikkeus{
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
			String selectLause = "Select juoma_id, nimi, hinta, koko, tyyppi from Juoma where tyyppi='virvoitusjuoma'";
			juomat = haeJuomat(selectLause);
		
		return juomat;
	}
	
	
	public ArrayList<Juoma> haeJuomatMenu() throws DAOPoikkeus{
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
			String selectLause = "Select juoma_id,nimi, hinta, koko, tyyppi from Juoma";
			juomat = haeJuomat(selectLause);
			
		return juomat;
	}
	
	
	//AddServicen käyttämä lisäysmetodi, jolla pizza lisätään tietokantaan. Ei käytössä.
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
	//AddServicen käyttämä poistometodi, jolla pizza poistetaan tietokannasta. Ei käytössä.
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
	
	public JuomaDAO (String juoma) throws DAOPoikkeus, SQLException{
		juomatieto(juoma);
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
