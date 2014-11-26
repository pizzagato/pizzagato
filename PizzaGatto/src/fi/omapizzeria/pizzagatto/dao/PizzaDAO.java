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
			String selectLause = "Select Pizza.nimi,Pizza.pizza_id, hinta, Tayte.nimi from Pizza LEFT JOIN Pizzatayte ON Pizza.pizza_id=Pizzatayte.pizza_id LEFT JOIN Tayte ON Pizzatayte.tayte_id=Tayte.tayte_id";
			Statement selectHaku = yhteys.createStatement(); //Syöttää SQL:ään komennon, jolla valitaan pizzat
			ResultSet selectTulokset = selectHaku.executeQuery(selectLause);
			while (selectTulokset.next()){ //Laitetaan tulokset omiin muuttujiinsa
				int id = selectTulokset.getInt("pizza_id");
				String nimi = selectTulokset.getString("pizza.nimi");
				Double hinta = selectTulokset.getDouble("hinta");
				String taytenimi = selectTulokset.getString("tayte.nimi");				
				Pizza p = new Pizza(id,nimi, hinta); //Yhdistetään pizzoihin niiden ominaisuudet
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
			String sql="DELETE from Pizzatayte WHERE pizza_id = ?";
			String sql2 = "DELETE from Pizza WHERE pizza_id = ?";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			PreparedStatement lause2 = yhteys.prepareStatement(sql2);
			
			lause.setInt(1, pois.getId());
			lause2.setInt(1,pois.getId());
			lause.executeUpdate();
			lause2.executeUpdate();
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
			
			for (int i = 0; i < taytteet.length; i++) {
				lause2.setInt(1,taytteet[i]);
				lause2.setInt(2,maxid);
				lause2.executeUpdate();
			}
			
			
			
			

			
			
			
			
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			suljeYhteys(yhteys);
		}
	}
	
	
}