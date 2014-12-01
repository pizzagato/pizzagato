package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Etusivu") //P‰‰te joka n‰kyy URLissa
public class EtusivuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //Ohjaa etusivulle
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/jsp/etusivu.jsp").forward(request, response);
	}

}
