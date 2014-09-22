package fi.omapizzeria.admin.controller;

import java.io.IOException;
//simport java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOPoikkeus;
import dao.PizzaDAO;
import fi.omapizzeria.admin.bean.Pizza;

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
		try {
			PizzaDAO pDao = new PizzaDAO();
			pizzat = pDao.haeKaikki();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		//requestiin talteen
		request.setAttribute("pizzat", pizzat);
		
		request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nimi = request.getParameter("nimi");
		String sHinta = request.getParameter("hinta");
		Double hinta = Double.parseDouble(sHinta);
		Pizza p = new Pizza(nimi, hinta);
		try {
			PizzaDAO pDao = new PizzaDAO();
			pDao.lisaa(p);
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}
		response.sendRedirect("ControllerServlet"); //redirect doGet

	}

}
