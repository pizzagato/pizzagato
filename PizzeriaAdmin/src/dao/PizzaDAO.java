package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import fi.omapizzeria.admin.bean.Pizza;
import fi.omapizzeria.admin.bean.Tayte;

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
				System.out.println(p);				
	
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
}