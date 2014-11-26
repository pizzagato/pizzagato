package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.bean.Tuote;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;

public class AddPizzaService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Metodi, jolla admin-n�kym�ss� lis�t��n ja poistetaan pizzoja tietokannasta. Ei k�yt�ss�.
	/*public void lisaaPoistaPizza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		}else if (request.getParameter("poistapizza") != null){
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
	
	public void lisaaPizztayte(String pizzanimi, double pizzahinta, int status, int tayte1, int tayte2, int tayte3, int tayte4, int tayte5)  throws ServletException, IOException{
		try {
			
			
			int [] taytteet = {tayte1, tayte2, tayte3, tayte4, tayte5};
			
			
			
			Pizza pt = new Pizza(pizzanimi, pizzahinta, status);
			
			PizzaDAO pDao = new PizzaDAO();
			pDao.lisaaPizztayte(pt, taytteet);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
	

	
	
	
