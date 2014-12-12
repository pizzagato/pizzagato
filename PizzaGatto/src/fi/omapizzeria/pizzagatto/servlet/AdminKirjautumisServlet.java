package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.service.BanningService;
import fi.omapizzeria.pizzagatto.service.LogInService;
import fi.omapizzeria.pizzagatto.service.PublicService;

/**
 * Hoitaa adminkirjautumiseen liittyv�n esitt�misen
 *
 */
@WebServlet("/Admin")
public class AdminKirjautumisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminKirjautumisServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	/*
    	 * Virhetekstit
    	 */
    	
    	String alreadyBanned = "P��sy estetty. Koita uudelleen my�hemmin.";
    	String getsBanned = "Yritit kirjautua liian monta kertaa sis��n. P��sysi on estetty.";
    	String wrong = "Tunnukset on v��r�t.";
    	String uhoh = "Jokin on pahasti vialla. Ota yhteytt� Jouniin.";
    	
    	/*
    	 * K�ytt�j�n sy�tt�m�t tunnukset
    	 */
    	
    	String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.equals(null) || password.equals(null)) {
			username="tyhj�";
			password="tyhj�";
		} 
		ArrayList<String> namepassword = new ArrayList<String>();
		namepassword.add(username);
		namepassword.add(password);
		
	  	/*
	  	 * Poistaa vanhat bannit
	  	 */
		 
		BanningService banService = new BanningService();
		banService.unBan();
		
		 /*
		  * Ottaa yl�s k�ytt�j�n ip-osoitteen
		  */
		 
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		
		/*
		 * Tarkastaa, onko k�ytt�j�n ip-osoite bannattujen listalla. Jos on, n�ytet��n virhesivu
		 */
		 
		if (banService.userBanStatus(ipAddress) == false) {
			request.setAttribute("reason", alreadyBanned);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			return;
		}
		
		/*
		 * Poistetaan vanhentuneen kirjautumisyritykset
		 */
		
		banService.clearAttempts();
		
		/*
		 * K�ytt�j� p��see kirjautumisruutuun ja voi kokeilla kirjautumista
		 */
		
		LogInService logInService = new LogInService();
		try {
			switch (logInService.tryLogin(ipAddress, namepassword)) {
			case 0:
				request.setAttribute("reason", getsBanned);
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
				break;
				
			case 1:
		    	PublicService search = new PublicService();
		    	request.setAttribute("pitsut", search.haePizzat()); 
		    	request.setAttribute("juomat", search.haeJuotavatMenu());
		    	request.setAttribute("taytteet", search.haeTaytteet());
		    	request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
				break;
				
			case 2:
				request.setAttribute("reason", wrong);
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
				break;

			default:
				request.setAttribute("reason", uhoh);
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
				break;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (DAOPoikkeus e) {
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/adminetusivu.jsp").forward(request, response);
	}
	
} 

