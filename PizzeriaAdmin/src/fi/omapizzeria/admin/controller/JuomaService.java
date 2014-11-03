package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import dao.DAOPoikkeus;
import dao.JuomaDAO;
import fi.omapizzeria.admin.bean.Juoma;

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
