package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Asiakastiedot;
import fi.omapizzeria.pizzagatto.bean.Tilausrivi;

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
		
		Asiakastiedot asTied = (Asiakastiedot) request.getSession().getAttribute("asTied");
		ArrayList<Tilausrivi> tilRivit = (ArrayList<Tilausrivi>) request.getSession().getAttribute("tilRivit");
		request.getAttribute("tilRivit");
		request.getAttribute("AsTied");
		
		System.out.println(asTied);
		System.out.println(tilRivit);
		//Tilaus at = (Tilaus) request.getSession().getAttribute("tilaus");
		//request.setAttribute("tilaus",at);
		//Asiakastiedot asTied = at.getAsiakastiedot();
		//request.setAttribute("asiakastiedot", asTied);
		
		
		
		request.getRequestDispatcher("WEB-INF/jsp/vahvistus.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
