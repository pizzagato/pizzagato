package fi.omapizzeria.pizzagatto.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;





import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.bean.Tuote;

public class PizzaDAO extends Yhteys {	
	
	public PizzaDAO() throws DAOPoikkeus {
		super();
	}
	//haeKaikki-metodi hakee tietokannasta kaikki pizzat
	public ArrayList<Pizza> haeKaikkiPizzatTaytteilla() throws DAOPoikkeus{
		ArrayList<Pizza> pitsut = new ArrayList<Pizza>();
		Connection yhteys = avaaYhteys(); //Avaa yhteyden tietokantaan
		try {
			String selectLause = "Select Pizza.nimi, hinta, Tayte.nimi from Pizza LEFT JOIN Pizzatayte ON Pizza.pizza_id=Pizzatayte.pizza_id LEFT JOIN Tayte ON Pizzatayte.tayte_id=Tayte.tayte_id";
			Statement selectHaku = yhteys.createStatement(); //Syöttää SQL:ään komennon, jolla valitaan pizzat
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				String nimi = selectTulokset.getString("pizza.nimi");
				Double hinta = selectTulokset.getDouble("hinta");
				String taytenimi = selectTulokset.getString("tayte.nimi");				
				Pizza p = new Pizza(nimi, hinta); //Yhdistetään pizzoihin niiden ominaisuudet
				Tayte t = new Tayte(taytenimi);
				p.addTayte(t);
				boolean oliSamaaPizzaa = true;
				
				do{//Lisätään täytteet oikeaan pizzaan
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
	//AddServicen käyttämä lisäysmetodi, jolla pizza lisätään tietokantaan. Ei käytössä.
	public void lisaa(Pizza p) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "insert into Pizza(nimi, hinta) values(?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, p.getNimi());
			lause.setDouble(2, p.getHinta());
			lause.executeUpdate();
			System.out.println("LISÄTTIIN PIZZA TIETOKANTAAN: "+p);
		} catch(Exception e) {
			throw new DAOPoikkeus("Pizzan lisäämisyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
	//AddServicen käyttämä poistometodi, jolla pizza poistetaan tietokannasta. Ei käytössä.
	public void poista(Pizza pois) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql = "DELETE from Pizza WHERE nimi = ?";
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
	
	public void muutaPizza(Pizza p)throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			String sql="UPDATE Pizza set status= ? WHERE nimi = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setInt(1, p.getStatus());
			lause.setString(2, p.getNimi());
			lause.executeUpdate();
			System.out.println("Muutettiin Pizzan "+p+" Status");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			suljeYhteys(yhteys);
		}
	}
	
	public void lisaaPizztayte(Pizza pt, int [] taytteet) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		String sql="insert into Pizza(nimi, hinta, status) values(?,?,?)";
		String sql2="insert into Pizzatayte(tayte_id, pizza_id) values(last_insert_id(),?)";
		try {
			
			PreparedStatement lause = yhteys.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			PreparedStatement lause2 = yhteys.prepareStatement(sql2);
			
			lause.setString(1, pt.getNimi());
			lause.setDouble(2, pt.getHinta());
			lause.setInt(3,pt.getStatus());
			
			lause.executeUpdate();
			
			lause2.setInt(1, taytteet[0]);
			
			lause2.executeUpdate();
			
			
			
			
			//for (int i = 0; i < taytteet.length; i++) {
			//	lause2 = yhteys.prepareStatement(sql2);
				//lause2.setInt(1,taytteet[i]);
				//lause2.executeUpdate();
			//}
			

			
			
			
			//lause2.setInt(3,tayte2);
			//lause2.setInt(4,lastid);
			//lause2.setInt(5,tayte3);
			//lause2.setInt(6,lastid);
			//lause2.setInt(7,tayte4);
			//lause2.setInt(8,lastid);
			//lause2.setInt(9, tayte5);
			//lause2.setInt(10,lastid);
			
			
			
			
			
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			suljeYhteys(yhteys);
		}
	}
	
	
}