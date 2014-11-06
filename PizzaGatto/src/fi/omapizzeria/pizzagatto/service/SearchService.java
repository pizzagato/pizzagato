package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;

public class SearchService {

	//Hakee tietokannasta pizzat käyttäen PizzaDAO:n metodia
	public ArrayList<Pizza> haePizzat() throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO(); //Luodaan PizzaDAO-olio
			pizzat = pDao.haeKaikkiPizzatTaytteilla();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	
		return pizzat;
	}
	
	public ArrayList<Juoma> haeJuomat () throws ServletException, IOException {
		ArrayList<Juoma> juomat;
		try {
			JuomaDAO jDao = new JuomaDAO(); //Luodaan PizzaDAO-olio
			juomat = jDao.haeJuomatMenu();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	
		return juomat;
	}
}
