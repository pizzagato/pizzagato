package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;

public class AddPizzaService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void poistaPizza(int poista) throws ServletException, IOException{
		Pizza pois = new Pizza(poista);
		try {
			PizzaDAO pDao = new PizzaDAO();
			pDao.poista(pois);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public void muutaStatus(int poista, int status) throws ServletException, IOException{
		Pizza p = new Pizza(poista,status);
		
		try {
			PizzaDAO pDao = new PizzaDAO();
			pDao.muutaPizza(p);
		} catch (Exception e) {
			throw new ServletException(e);
		}	
	}
	
	public void lisaaPizztayte(String pizzanimi, double pizzahinta, int status, ArrayList<Integer> taytteet)  throws ServletException, IOException{
		try {
			Pizza pt = new Pizza(pizzanimi, pizzahinta, status);
			PizzaDAO pDao = new PizzaDAO();
			pDao.lisaaPizztayte(pt, taytteet);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}