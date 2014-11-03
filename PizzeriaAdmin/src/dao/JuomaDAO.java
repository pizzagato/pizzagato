package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import fi.omapizzeria.admin.bean.Juoma;


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
				String nimi = selectTulokset.getString("nimi");
				Double hinta = selectTulokset.getDouble("hinta");
				String koko = selectTulokset.getString("koko");
				String tyyppi = selectTulokset.getString("tyyppi");
				Juoma j = new Juoma(nimi,hinta, koko, tyyppi); 
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
			String selectLause = "Select nimi, hinta, koko, tyyppi from Juoma where tyyppi='virvoitusjuoma'";
			juomat = haeJuomat(selectLause);
		
		return juomat;
	}
	
	
	public ArrayList<Juoma> haeJuomatMenu() throws DAOPoikkeus{
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
			String selectLause = "Select nimi, hinta, koko, tyyppi from Juoma";
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
			System.out.println("LISÄTTIIN PIZZA TIETOKANTAAN: "+j);
		} catch(Exception e) {
			throw new DAOPoikkeus("Pizzan lisäämisyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	//AddServicen käyttämä poistometodi, jolla pizza poistetaan tietokannasta. Ei käytössä.
	public void poista(Juoma pois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from Juoma WHERE nimi = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, pois.getNimi());
			lause.executeUpdate();
			System.out.println("Poistettiin pizza tietokannasta: "+pois);
		} catch (Exception e) {
			throw new DAOPoikkeus("Pizzan poistoyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
}
