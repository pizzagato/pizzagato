package fi.omapizzeria.pizzagatto.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;








import fi.omapizzeria.pizzagatto.bean.Tayte;


public class TayteDAO extends Yhteys {	
	
	public TayteDAO() throws DAOPoikkeus {
		super();
	}
	
	public ArrayList<Tayte> haeTaytteet() throws DAOPoikkeus {
		ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select tayte_id, nimi from Tayte";
			Statement selectHaku = yhteys.createStatement(); //Syöttää SQL:ään komennon, jolla valitaan juomat
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String nimi = selectTulokset.getString("nimi");
				int id = selectTulokset.getInt("tayte_id");
				
				Tayte t= new Tayte(id, nimi); 
				taytteet.add(t);
			
				
			}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
			}
		return taytteet;
	}
	
	
	
	
	
	
	//AddServicen käyttämä lisäysmetodi, jolla pizza lisätään tietokantaan. Ei käytössä.
	public void lisaa(Tayte t) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into Tayte(nimi)values(?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, t.getNimi());
			lause.executeUpdate();
			System.out.println("LISÄTTIIN Tayte TIETOKANTAAN: "+t);
		} catch(Exception e) {
			throw new DAOPoikkeus("Taytteen lisäämisyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	//AddServicen käyttämä poistometodi, jolla pizza poistetaan tietokannasta. Ei käytössä.
	public void poista(Tayte pois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from Tayte WHERE nimi = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, pois.getNimi());
			lause.executeUpdate();
			System.out.println("Poistettiin tayte tietokannasta: "+pois);
		} catch (Exception e) {
			throw new DAOPoikkeus("Taytteen poistoyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
}
