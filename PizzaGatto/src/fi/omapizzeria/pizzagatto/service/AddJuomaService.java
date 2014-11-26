package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;

public class AddJuomaService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Metodi, jolla admin-näkymässä lisätään ja poistetaan juomia tietokannasta. Ei käytössä.
	public void lisaaPoistaJuoma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("nimi")!=null) {
			String nimi = request.getParameter("nimi");
			String sHinta = request.getParameter("hinta");
			Double hinta = Double.parseDouble(sHinta);
			String koko = request.getParameter("koko");
			String tyyppi = request.getParameter("tyyppi");
			Juoma j = new Juoma(nimi, hinta, koko, tyyppi);
			try {
				JuomaDAO jDao = new JuomaDAO();
				jDao.lisaa(j);
			} catch (DAOPoikkeus e) {
				throw new ServletException(e);
			}
			//Juoma poistetaan
		}else if (request.getParameter("poista") != null){
			String poista = request.getParameter("poista");		
			Juoma pois = new Juoma(poista);
			try {
				JuomaDAO jDao = new JuomaDAO();
				jDao.poista(pois);
			} catch (Exception e) {
				throw new ServletException(e);
			}
			
		}

	}
**/
	
	public void poistaJuoma(int poista) throws ServletException, IOException{
		Juoma pois = new Juoma(poista);
		try {
			JuomaDAO jDao = new JuomaDAO();
			jDao.poista(pois);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
	
	
	public void lisaaJuoma(String nimi, double hinta, String koko, String tyyppi)throws ServletException, IOException{
		Juoma j = new Juoma(nimi, hinta, koko, tyyppi);
		try {
			JuomaDAO jDao = new JuomaDAO();
			jDao.lisaa(j);
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
		
		
		
		
		
	}
	
	
}
	
	


	

	
	
	

