package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;

public class AddService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Metodi, jolla admin-näkymässä lisätään ja poistetaan pizzoja tietokannasta. Ei käytössä.
/*	public void lisaaPoista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("nimi")!=null) {
			String nimi = request.getParameter("nimi");
			String sHinta = request.getParameter("hinta");
			Double hinta = Double.parseDouble(sHinta);
			Pizza p = new Pizza(nimi, hinta);
			try {
				PizzaDAO pDao = new PizzaDAO();
				pDao.lisaa(p);
			} catch (DAOPoikkeus e) {
				throw new ServletException(e);
			}
			//Pizza poistetaan
		}else if (request.getParameter("poista") != null){
			String poista = request.getParameter("poista");		
			Pizza pois = new Pizza(poista);
			try {
				PizzaDAO pDao = new PizzaDAO();
				pDao.poista(pois);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
	*/
	
	
	
	
	
}
	

	
	
	

