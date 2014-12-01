package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;

public class AddJuomaService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	