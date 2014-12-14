package fi.omapizzeria.pizzagatto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;

/**
 * Hoitaa kaikki muutokset tietokannassa, jotka liittyv‰t pizzoihin
 */
public class PizzaDAO extends Yhteys {	
	
	public PizzaDAO() throws DAOPoikkeus {
		super();
	}

	/**
	 * Hakee kaikki pizzat ja niihin liittyv‰t t‰ytteet. Yhdist‰‰ t‰ytteet pizzoihin.
	 * @param selectLause: SQL-lause, jolla pizzat haetaan
	 * @return pitsut: Lista pizza-olioista, joihin on yhdistetty t‰ytteet
	 */
	public ArrayList<Pizza> haeKaikkiPizzatTaytteilla(String selectLause) throws DAOPoikkeus{
		ArrayList<Pizza> pitsut = new ArrayList<Pizza>();
		Connection yhteys = avaaYhteys();
		try {
			Statement selectHaku = yhteys.createStatement();
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){
				int id = selectTulokset.getInt("pizza_id");
				String nimi = selectTulokset.getString("pizza.nimi");
				Double hinta = selectTulokset.getDouble("hinta");
				String taytenimi = selectTulokset.getString("tayte.nimi");				
				Pizza p = new Pizza(id,nimi, hinta); //Yhdistet‰‰n pizzoihin niiden ominaisuudet
				Tayte t = new Tayte(taytenimi);
				p.addTayte(t);
				boolean oliSamaaPizzaa = true;
				
				do{ //Lis‰t‰‰n t‰ytteet oikeaan pizzaan
					if (selectTulokset.next() && selectTulokset.getString("pizza.nimi").equals(nimi)) {
						taytenimi = selectTulokset.getString("tayte.nimi");
						if (taytenimi != null) {
							Tayte tx = new Tayte(taytenimi);
							p.addTayte(tx);
						}
					}else{ 
						selectTulokset.previous();
						oliSamaaPizzaa = false;
					}
					
									
				}while (selectTulokset.getString("pizza.nimi").equals(nimi) && oliSamaaPizzaa); 			
				pitsut.add(p);
			}
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
			}
		return pitsut;
	}
	
	/**
	 * Hakee pizzan hinnan
	 * @param pizza: Pizzan nimi, jonka hinta halutaan tiet‰‰
	 * @return pizzatieto: Palauttaa annetun pizzan hinnan
	 * @return 0: Jos pizzaa ei lˆydy tietokannasta, palauttaa hinnan 0
	 */
	public double pizzaHinta(String pizza) throws DAOPoikkeus, SQLException{
		Connection yhteys = avaaYhteys();
		String selectLause = "Select hinta FROM Pizza WHERE Pizza.nimi = '" + pizza + "'";
		Statement selectHaku = yhteys.createStatement();
		ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
		if (selectTulokset.next()) {
			double pizzatieto = selectTulokset.getDouble("hinta");
			suljeYhteys(yhteys);
			return pizzatieto;
		}else {
			suljeYhteys(yhteys);
			return 0;
		}
	
	}
	
	/**
	 * Hakee pizzan id:n
	 * @param pizza: Pizzan nimi, jonka id halutaan tiet‰‰
	 * @return pizzaId: Palauttaa annetun pizzan id:n
	 * @return 0: Jos pizzaa ei lˆydy tietokannasta, palauttaa id:n 0
	 */
	public int pizzaid(String pizza) throws DAOPoikkeus, SQLException{
		Connection yhteys = avaaYhteys();
		String selectLause = "Select pizza_id FROM Pizza WHERE Pizza.nimi = '" + pizza + "'";
		Statement selectHaku = yhteys.createStatement();
		ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
		if (selectTulokset.next()) {
			int pizzaId = selectTulokset.getInt("pizza_id");
			suljeYhteys(yhteys);
			return pizzaId;
		}else {
			suljeYhteys(yhteys);
			return 0;
		}
	
	}
	
	/**
	 * Lis‰‰ annetun pizzan Pizzat-tauluun
	 * @param p: Lis‰tt‰v‰ pizza
	 */
	public void lisaa(Pizza p) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into Pizza(nimi, hinta) values(?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, p.getNimi());
			lause.setDouble(2, p.getHinta());
			lause.executeUpdate();
		} catch(Exception e) {
			throw new DAOPoikkeus("Pizzan lis‰‰misyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}

	/**
	 * Poistaa annetun pizzan Pizzat-taulusta
	 * @param pois: Poistettava pizza
	 */
	public void poista(Pizza pois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql="DELETE from Pizzatayte WHERE pizza_id = ?";
			String sql2 = "DELETE from Pizza WHERE pizza_id = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			PreparedStatement lause2 = yhteys.prepareStatement(sql2);
			
			lause.setInt(1, pois.getId());
			lause2.setInt(1,pois.getId());
			lause.executeUpdate();
			lause2.executeUpdate();
		} catch (Exception e) {
			throw new DAOPoikkeus("Pizzan poistoyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
	
	/**
	 * Muuttaa annetun pizzan statusta
	 * @param p: Muutettava pizza
	 */
	public void muutaPizza(Pizza p)throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql="UPDATE Pizza set status= ? WHERE pizza_id = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setInt(1, p.getStatus());
			lause.setInt(2, p.getId());
			lause.executeUpdate();
		} catch (Exception e) {
		}finally {
			suljeYhteys(yhteys);
		}
	}
	/**
	 * Yhdist‰‰ listan t‰ytteit‰ annettuun pizzaan
	 * @param pt: Pizza, johon t‰ytteet liitet‰‰n
	 * @param taytteet: Lista t‰ytteist‰, jotka pizzaan liitet‰‰n
	 */
	public void lisaaPizztayte(Pizza pt, ArrayList<Integer> taytteet) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		String sql="insert into Pizza(nimi, hinta, status) values(?,?,?)";
		String sql2="insert into Pizzatayte(tayte_id, pizza_id) values(?,?)";
		String sql3="select MAX(pizza_id) from Pizza";
		int maxid=0;
		try {
			
			PreparedStatement lause = yhteys.prepareStatement(sql);
			PreparedStatement lause2 = yhteys.prepareStatement(sql2);
			PreparedStatement lause3 = yhteys.prepareStatement(sql3);
			lause.setString(1, pt.getNimi());
			lause.setDouble(2, pt.getHinta());
			lause.setInt(3,pt.getStatus());
			
			lause.executeUpdate();
			lause3.executeUpdate();
			
			
			ResultSet setit = lause3.getResultSet();
			if(setit.next()){
				maxid=setit.getInt(1);
			}
			
			for (int i = 0; i < taytteet.size(); i++) {
				lause2.setInt(1,taytteet.get(i));
				lause2.setInt(2,maxid);
				lause2.executeUpdate();
			}
		} catch (Exception e) {
			
		} finally{
			suljeYhteys(yhteys);
		}
	}	
}