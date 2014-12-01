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
			String sql = "DELETE from Pizzatayte where tayte_id=?";
			String sql2 = "DELETE from Tayte WHERE tayte_id = ?";
			
			PreparedStatement lause = yhteys.prepareStatement(sql);
			PreparedStatement lause2 = yhteys.prepareStatement(sql2);
			
			lause.setInt(1, pois.getId());
			lause2.setInt(1, pois.getId());
			lause.executeUpdate();
			lause2.executeUpdate();
		} catch (Exception e) {
			throw new DAOPoikkeus("Taytteen poistoyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
}
