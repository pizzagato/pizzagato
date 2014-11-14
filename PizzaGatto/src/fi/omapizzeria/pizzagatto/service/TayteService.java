package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;
import fi.omapizzeria.pizzagatto.dao.TayteDAO;

public class TayteService {
	public ArrayList<Tayte> haeTaytteet() throws ServletException, IOException {
		ArrayList<Tayte> taytteet;
		try {
			TayteDAO tDao = new TayteDAO(); //Luodaan PizzaDAO-olio
			taytteet = tDao.haeTaytteet();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	
		return taytteet;
	}
}
