package fi.omapizzeria.admin.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Etusivu") //P��te joka n�kyy URLissa
public class EtusivuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EtusivuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Ohjaa etusivulle
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/jsp/etusivu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
