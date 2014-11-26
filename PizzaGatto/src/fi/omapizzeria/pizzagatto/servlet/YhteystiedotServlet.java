package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Tämä servletti toimii kontrollerina yhteystietosivun näyttämiseen.
 */
@WebServlet("/Yhteystiedot") //Pääte joka näkyy URLissa
public class YhteystiedotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @param request Selaimelta tuleva pyyntö
	 * @param reponse Selaimelle lähetettävä vastaus
	 * 
	 * Tämä metodi ohjaa suoraan yhteystietosivulle
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/yhteystiedot.html").forward(request, response);
	}

}
