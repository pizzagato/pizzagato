package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.service.SearchService;

@WebServlet("/Menu") //P‰‰te joka n‰kyy URLissa
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //Ohjaa menusivulle ja hakee sit‰ varten pizzalistan
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pitsut", pizzat);
		
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
		juomat=search.haeJuotavatMenu();
		request.setAttribute("juomat", juomat);
		request.setAttribute("pitsut", pizzat);
		
		request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
	}

}
