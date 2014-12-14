package fi.omapizzeria.pizzagatto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;








import fi.omapizzeria.pizzagatto.bean.Tayte;

/**
 * Hoitaa kaikki muutokset tietokannassa, jotka liittyv‰t t‰ytteisiin
 */
public class TayteDAO extends Yhteys {	
	
	public TayteDAO() throws DAOPoikkeus {
		super();
	}
	
	/**
	 * Hakee kaikki t‰ytteet, pakkaa ne Tayte-olioihin ja pakkaa oliot listaan
	 * @return taytteet: Lista Tayte-olioista, jotka sis‰lt‰v‰t t‰ytteiden id:n ja nimen
	 */
	public ArrayList<Tayte> haeTaytteet() throws DAOPoikkeus {
		ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
		Connection yhteys = avaaYhteys();
		try {
			String selectLause = "Select tayte_id, nimi from Tayte";
			Statement selectHaku = yhteys.createStatement();
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){
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

	/**
	 * Lis‰‰ l‰hetetyn t‰ytteen Tayte-tauluun
	 * @param t: Lis‰tt‰v‰ t‰yte
	 */
	public void lisaa(Tayte t) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into Tayte(nimi)values(?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, t.getNimi());
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("Taytteen lis‰‰misyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	
	/**
	 * Poistaa l‰hetetyn t‰ytteen Tayte-taulusta
	 * @param pois: Poistettava t‰yte
	 */
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
