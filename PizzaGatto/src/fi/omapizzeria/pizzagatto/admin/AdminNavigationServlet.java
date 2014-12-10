package fi.omapizzeria.pizzagatto.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.service.PublicService;

@WebServlet("/AdminNavigationServlet")
public class AdminNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminNavigationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getParameter("target");
		if (target.equals("toMenu")) {
			PublicService search = new PublicService();
			request.setAttribute("pitsut", search.haePizzat()); 
			request.setAttribute("juomat", search.haeJuotavatMenu());
			request.setAttribute("taytteet", search.haeTaytteet());
			request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
		} else if (target.equals("toSettings")) {
			request.getRequestDispatcher("WEB-INF/jsp/asetukset.jsp").forward(request, response);
		}
		
	}

}
