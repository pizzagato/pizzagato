package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.TayteDAO;

public class AddTayteService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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