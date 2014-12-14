package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;
import fi.omapizzeria.pizzagatto.dao.TayteDAO;

public class AdminService extends HttpServlet {

	/**
	 * Hoitaa kaikki poistot, lis‰ykset ja muutokset adminsivulla
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Poistaa pizzan annetulla id:ll‰
	 * @param poista: Poistettavan pizzan id
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

	/**
	 * Muuttaa annetun id:n omaavan pizzan statusta
	 * @param poista: Muutettavan pizzan id
	 * @param status: Pizzan tuleva status, joka kertoo onko pizza menulistalla
	 */
	public void muutaStatus(int poista, int status) throws ServletException, IOException{
		Pizza p = new Pizza(poista,status);
		
		try {
			PizzaDAO pDao = new PizzaDAO();
			pDao.muutaPizza(p);
		} catch (Exception e) {
			throw new ServletException(e);
		}	
	}
	
	/**
	 * Luo uuden pizzan annetuilla parametreilla
	 * @param pizzanimi: Pizzan nimi
	 * @param pizzahinta: Pizzan hinta
	 * @param status: Pizzan status
	 * @param taytteet: Lista pizzaan tulevista t‰ytteist‰
	 */
	public void lisaaPizztayte(String pizzanimi, double pizzahinta, int status, ArrayList<Integer> taytteet)  throws ServletException, IOException{
		try {
			Pizza pt = new Pizza(pizzanimi, pizzahinta, status);
			PizzaDAO pDao = new PizzaDAO();
			pDao.lisaaPizztayte(pt, taytteet);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Poistaa t‰ytteen annetulla id:ll‰
	 * @param poista: Poistettavan t‰ytteen id
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
	
	/**
	 * Lis‰‰ uuden t‰ytteen
	 * @param taytenimi: T‰ytteen nimi
	 */
	public void lisaaTayte(String taytenimi)throws ServletException, IOException{
		Tayte t = new Tayte(taytenimi);
		try {
			TayteDAO tDao = new TayteDAO();
			tDao.lisaa(t);
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
	}	
	
	/**
	 * Poistaa juoman annetulla id:ll‰
	 * @param poista: Poistettavan juoman id
	 */
	public void poistaJuoma(int poista) throws ServletException, IOException{
		Juoma pois = new Juoma(poista);
		try {
			JuomaDAO jDao = new JuomaDAO();
			jDao.poista(pois);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * Tekee uuden juoman annetuilla parametreill‰
	 * @param nimi: Juoman nimi
	 * @param hinta: Juoman hinta
	 * @param koko: Juoman koko
	 * @param tyyppi: Juoman tyyppi
	 */
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