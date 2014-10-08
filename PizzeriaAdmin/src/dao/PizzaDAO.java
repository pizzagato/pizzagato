package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import fi.omapizzeria.admin.bean.Pizza;
import fi.omapizzeria.admin.bean.Pizzalistaan;

public class PizzaDAO extends Yhteys {	
	
	public PizzaDAO() throws DAOPoikkeus {
		super();
	}

	public ArrayList<Pizzalistaan> haeKaikki() throws DAOPoikkeus{
		//ArrayList<Pizza> pizzat = new ArrayList<Pizza>();
		ArrayList<Pizzalistaan> pitsut = new ArrayList<Pizzalistaan>();
		Connection yhteys = avaaYhteys();
		try {
			//String sql = "select pizza_id, nimi, hinta from Pizza";
			//Statement haku = yhteys.createStatement();
			//ResultSet tulokset = haku.executeQuery(sql);
			String sql2 = "Select Pizza.nimi, hinta, Tayte.nimi from Pizza LEFT JOIN Pizzatayte ON Pizza.pizza_id=Pizzatayte.pizza_id LEFT JOIN Tayte ON Pizzatayte.tayte_id=Tayte.tayte_id";
			Statement haku2 = yhteys.createStatement();
			ResultSet tulokset2 = haku2.executeQuery(sql2);
			//while(tulokset.next()) {
				//int id = tulokset.getInt("pizza_id");
				//String nimi = tulokset.getString("nimi");
				//Double hinta = tulokset.getDouble("hinta");
				//Pizza p = new Pizza(id, nimi, hinta);
				//pizzat.add(p);
			//}
			while (tulokset2.next()){
				String nimi = tulokset2.getString("pizza.nimi");
				Double hinta = tulokset2.getDouble("hinta");
				String taytenimi = tulokset2.getString("tayte.nimi");
				Pizzalistaan p = new Pizzalistaan(nimi, hinta);
				p.setTayte(taytenimi);
				boolean jatkuu = true;
				
				do{
					if (tulokset2.next() && tulokset2.getString("pizza.nimi").equals(nimi)) {
						taytenimi = tulokset2.getString("tayte.nimi");
						if (taytenimi != null) {
							p.setTayte(taytenimi);
						}
					}else{ 
						tulokset2.previous();
						jatkuu = false;
					}
					
									
				}while (tulokset2.getString("pizza.nimi").equals(nimi) && jatkuu); 
				System.out.println(p);				
	
				pitsut.add(p);
				
			}
			//for (int i = 0; i < pitsut.size(); i++) {
				//System.out.println(pitsut.get(i));
			//}
			
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		}
		finally {
			suljeYhteys(yhteys);
			}
		//System.out.println("HAETTIIN TIETOKANNASTA PIZZAT: " +pizzat.toString());
		return pitsut;
	}
	
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