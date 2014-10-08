package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import dao.DAOPoikkeus;
import dao.PizzaDAO;
import fi.omapizzeria.admin.bean.Pizzalistaan;

public class SearchService {

	
	public ArrayList<Pizzalistaan> haePizzat() throws ServletException, IOException {
		ArrayList<Pizzalistaan> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO();
			pizzat = pDao.haeKaikki();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	
		return pizzat;
	}
}
