package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.admin.bean.Pizza;
import fi.omapizzeria.admin.controller.AddService;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pizzat", pizzat);
		
		request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
		

	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//if-else -rakenne, jolla selvitet‰‰n mist‰ kutsu on tullut
		//kun on haluttu listaus:
		AddService palvelu = new AddService();
		palvelu.lisaaPoista(request, response);
		response.sendRedirect("ControllerServlet"); //redirect doGet

	}
	//
	//
	//
}
