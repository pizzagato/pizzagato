package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fi.omapizzeria.pizzagatto.bean.Asiakastiedot;
import fi.omapizzeria.pizzagatto.bean.Tilaus;

/**
 * Servlet implementation class VahvistusServlet
 */
@WebServlet("/Vahvistus")
public class VahvistusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Tilaus at = (Tilaus) request.getSession().getAttribute("tilaus");
		request.setAttribute("tilaus",at);
		Asiakastiedot asTied = at.getAsiakastiedot();
		request.setAttribute("asiakastiedot", asTied);
		
		
		
		request.getRequestDispatcher("WEB-INF/jsp/vahvistus.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
