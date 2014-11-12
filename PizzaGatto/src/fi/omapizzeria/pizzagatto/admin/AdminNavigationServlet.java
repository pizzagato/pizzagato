package fi.omapizzeria.pizzagatto.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminNavigationServlet
 */
@WebServlet("/AdminNavigationServlet")
public class AdminNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String target = request.getParameter("target");
		 System.out.println(target);
		 if (target.equals("toMenu")) {
			 request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
		} else if (target.equals("toSettings")) {
			request.getRequestDispatcher("WEB-INF/jsp/asetukset.jsp").forward(request, response);
		}
		
	}

}