package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.admin.bean.Tilaus;
import fi.omapizzeria.admin.bean.Pizza;

/**
 * Servlet implementation class VahvistusServlet
 */
@WebServlet("/Vahvistus")
public class VahvistusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VahvistusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pizza=(String) request.getSession().getAttribute("pizza");
		String juoma=(String) request.getSession().getAttribute("juoma");
		Tilaus at = (Tilaus) request.getSession().getAttribute("asiakastiedot");
		
		request.setAttribute("pitsu", pizza);
		request.setAttribute("juoma", juoma);
		request.setAttribute("asiakatiedot",at);
		
		
		
		request.getRequestDispatcher("WEB-INF/jsp/vahvistus.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
