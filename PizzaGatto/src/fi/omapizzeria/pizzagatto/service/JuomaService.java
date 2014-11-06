package fi.omapizzeria.pizzagatto.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;

public class JuomaService {

	//Hakee tietokannasta pizzat käyttäen PizzaDAO:n metodia
	public ArrayList<Juoma> haeJuotavat() throws ServletException, IOException {
		ArrayList<Juoma> juomat;
		try {
			JuomaDAO JDao = new JuomaDAO(); //Luodaan PizzaDAO-olio
			juomat = JDao.haeJuomatTilaus();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
	
		return juomat;
	}
}
