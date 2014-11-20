package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;
import fi.omapizzeria.pizzagatto.dao.TayteDAO;

public class SearchService {

	/*
	 * Hakee tietokannasta kaikki pizzat ja niihin kuuluvat täytteet
	 */
	
	public ArrayList<Pizza> haePizzat() throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO();
			pizzat = pDao.haeKaikkiPizzatTaytteilla();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
		return pizzat;
	}
	
	/*
	 * Hakee tietokannasta kaikki juomat menulle
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
	
	/*
	 * Hakee tietokannasta juotavat tilaukselle
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
	
	/*
	 * Hakee tietokannasta kaikki täytteet
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
