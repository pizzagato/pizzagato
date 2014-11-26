package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;
import fi.omapizzeria.pizzagatto.dao.TayteDAO;

public class AddTayteService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Metodi, jolla admin-näkymässä lisätään ja poistetaan pizzoja tietokannasta. Ei käytössä.
	/**public void lisaaPoistaTayte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("nimi")!=null) {
			String nimi = request.getParameter("nimi");
			
			Tayte t = new Tayte(nimi);
			try {
				TayteDAO tDao = new TayteDAO();
				tDao.lisaa(t);
			} catch (DAOPoikkeus e) {
				throw new ServletException(e);
			}
			//Pizza poistetaan
		}else if (request.getParameter("poista") != null){
			String poista = request.getParameter("poista");		
			Tayte pois = new Tayte(poista);
			try {
				TayteDAO tDao = new TayteDAO();
				tDao.poista(pois);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
	
	
	*/
	
	public void poistaTayte(int poista) throws ServletException, IOException{
		Tayte pois = new Tayte(poista);
		try {
			TayteDAO tDao = new TayteDAO();
			tDao.poista(pois);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
	public void lisaaTayte(String taytenimi)throws ServletException, IOException{
		Tayte t = new Tayte(taytenimi);
		try {
			TayteDAO tDao = new TayteDAO();
			tDao.lisaa(t);
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	}
	
	
	
	
}
	

	
	
	

