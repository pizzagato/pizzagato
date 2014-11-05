package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOPoikkeus;
import dao.JuomaDAO;
import fi.omapizzeria.admin.bean.Juoma;
import fi.omapizzeria.admin.bean.Pizza;

@WebServlet("/Menu") //P‰‰te joka n‰kyy URLissa
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Ohjaa menusivulle ja hakee sit‰ varten pizzalistan
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pitsut", pizzat);
		
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
		juomat=search.haeJuomat();
		request.setAttribute("juomat", juomat);
		request.setAttribute("pitsut", pizzat);
		
		request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
