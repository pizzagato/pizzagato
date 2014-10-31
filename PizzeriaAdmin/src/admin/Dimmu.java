package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Dimmu")
public class Dimmu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Dimmu() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * Poistaa vanhat bannit
		 */
		
		BanningService banService = new BanningService();
		banService.unBan();
		
		/*
		 * Ottaa ylös käyttäjän ip-osoitteen
		 */
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		/*
		 * Tarkistaa onko käyttäjän ip-osoite bannattujen listalla. Jos on, servlet sammuu
		 */
		
		if (banService.userBanStatus(ipAddress) == false) {
			System.exit(0);
		}
		
		/*
		 * Antaa käyttäjälle kirjautumisruudun
		 */
		
		LoggingService logService = new LoggingService();
		if (logService.tryLogin(ipAddress) == true) {
			request.getRequestDispatcher("WEB-INF/jsp/jumalanpuutarha.jsp").forward(request, response);
		} else {
			System.exit(0);
		}
	}
}

