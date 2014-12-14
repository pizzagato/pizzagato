package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.bean.Tilausrivi;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;
import fi.omapizzeria.pizzagatto.dao.TayteDAO;

/**
 * Hoitaa kaikki haut tietokannasta ja kaikki asiakasn‰kym‰n tietokantamuutokset
 */
public class PublicService {


	/**
	 * Hakee kaikki pizzat
	 * @return lista, jossa on kaikki pizzat
	 */
	public ArrayList<Pizza> haePizzat() throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO();
			pizzat = pDao.haeKaikkiPizzatTaytteilla("Select Pizza.nimi,Pizza.pizza_id, hinta, Tayte.nimi from Pizza "
					+ "LEFT JOIN Pizzatayte ON Pizza.pizza_id=Pizzatayte.pizza_id LEFT JOIN Tayte ON Pizzatayte.tayte_id=Tayte.tayte_id");
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
		return pizzat;
	}
	
	/**
	 * Hakee kaikki n‰ytett‰v‰t pizzat
	 * @return lista, jossa on kaikki n‰ytett‰v‰t pizzat
	 */
	public ArrayList<Pizza> haeNaytettavatPizzat() throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO();
			pizzat = pDao.haeKaikkiPizzatTaytteilla("Select Pizza.nimi,Pizza.pizza_id, hinta, Tayte.nimi from Pizza "
					+ "LEFT JOIN Pizzatayte ON Pizza.pizza_id=Pizzatayte.pizza_id LEFT JOIN Tayte ON Pizzatayte.tayte_id=Tayte.tayte_id WHERE status=1");
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return pizzat;
	}
	
	/**
	 * Lis‰‰ tilausriviin tietoja ja palauttaa sen takaisin
	 * @param pizzanimi: Lis‰tt‰v‰n pizzan nimi
	 * @param juomanimi: Lis‰tt‰v‰n juoman nimi
	 * @param kpl1: Lis‰tt‰v‰n pizzan kappalem‰‰r‰
	 * @param kpl2: Lis‰tt‰v‰n juoman kappalem‰‰r‰
	 * @param tilRivit: Tilausrivi, johon tuotteita lis‰t‰‰n
	 * @return Lista tilausriveist‰
	 */
	public ArrayList<Tilausrivi> tilauksenTilausrivi(String pizzanimi, String juomanimi, int kpl1, int kpl2, ArrayList<Tilausrivi> tilRivit) {
		Pizza pizza = new Pizza();
		Juoma juoma = new Juoma();
		pizza.setNimi(pizzanimi);
		juoma.setNimi(juomanimi);		
		// Haetaan pitsojen ja juomien hintatiedot DAO-luokkien avulla
		try {
			PizzaDAO pizzahinta = new PizzaDAO();
			PizzaDAO pizzaId = new PizzaDAO();
			pizza.setHinta(pizzahinta.pizzaHinta(pizzanimi));
			pizza.setId(pizzaId.pizzaid(pizzanimi));
			} catch (DAOPoikkeus e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				JuomaDAO juomahinta = new JuomaDAO();
				JuomaDAO juomaid = new JuomaDAO();
				juoma.setHinta(juomahinta.juomaHinta(juomanimi));
				juoma.setId(juomaid.juomaid(juomanimi));
			} catch (DAOPoikkeus e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		double juomaKokHinta = juoma.getHinta()*kpl2;
		double pizzaKokHinta = pizza.getHinta()*kpl1;
		Tilausrivi juomaRivi = new Tilausrivi(juoma, kpl2, juomaKokHinta);
		Tilausrivi pizzaRivi = new Tilausrivi(pizza, kpl1, pizzaKokHinta);
		
		if (pizza.getNimi() != null && pizza.getHinta() != null) {
			tilRivit.add(pizzaRivi);
		}
		if (juoma.getNimi() != null && juoma.getHinta() != null) {
			tilRivit.add(juomaRivi);
		}
		return tilRivit;
	}
	
	/**
	 * Hakee tietokannasta kaikki juomat menulle
	 * @return lista, jossa on kaikki juomat
	 */
	public ArrayList<Juoma> haeJuotavatMenu () throws ServletException, IOException {
		ArrayList<Juoma> juomat;
		try {
			JuomaDAO jDao = new JuomaDAO();
			juomat = jDao.haeJuomatMenu();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return juomat;
	}
	
	/**
	 * Hakee tietokannasta juotavat tilaussivulle
	 * @return lista, jossa on kaikki alkoholittomat juomat
	 */
	public ArrayList<Juoma> haeJuotavatTilaus() throws ServletException, IOException {
		ArrayList<Juoma> juomat;
		try {
			JuomaDAO JDao = new JuomaDAO();
			juomat = JDao.haeJuomatTilaus();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return juomat;
	}
	
	/**
	 * Hakee tietokannasta kaikki t‰ytteet
	 * @return lista, jossa on kaikki t‰ytteet
	 */
	public ArrayList<Tayte> haeTaytteet() throws ServletException, IOException {
		ArrayList<Tayte> taytteet;
		try {
			TayteDAO tDao = new TayteDAO();
			taytteet = tDao.haeTaytteet();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return taytteet;
	}
}
