package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import dao.DAOPoikkeus;
import dao.JuomaDAO;
import dao.PizzaDAO;
import fi.omapizzeria.admin.bean.Juoma;
import fi.omapizzeria.admin.bean.Pizzalistaan;

public class SearchService {

	//Hakee tietokannasta pizzat käyttäen PizzaDAO:n metodia
	public ArrayList<Pizzalistaan> haePizzat() throws ServletException, IOException {
		ArrayList<Pizzalistaan> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO(); //Luodaan PizzaDAO-olio
			pizzat = pDao.haeKaikki();
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
