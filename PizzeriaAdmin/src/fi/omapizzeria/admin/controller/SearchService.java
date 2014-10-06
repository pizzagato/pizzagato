package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import dao.DAOPoikkeus;
import dao.PizzaDAO;
import fi.omapizzeria.admin.bean.Pizza;

public class SearchService {

	
	public ArrayList<Pizza> haePizzat() throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		try {
			PizzaDAO pDao = new PizzaDAO();
			pizzat = pDao.haeKaikki();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		return pizzat;
	}
}
