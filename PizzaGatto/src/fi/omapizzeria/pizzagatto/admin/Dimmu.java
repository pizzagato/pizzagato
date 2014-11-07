package fi.omapizzeria.pizzagatto.admin;

import java.io.IOException;
import java.util.ArrayList;

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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String alreadyBanned = "P��sy estetty. Koita uudelleen my�hemmin.";
    	String getsBanned = "Yritit kirjautua liian monta kertaa sis��n. P��sysi on estetty.";
    	String wrong = "Tunnukset on v��r�t.";
    	String uhoh = "Jokin on pahasti vialla. Ota yhteytt� Jouniin.";
    	String username = request.getParameter("username");
		String password = request.getParameter("password");
	/*	if (username.equals(null) || password.equals(null)) {
			username="tyhj�";
			password="tyhj�";
		} */
		ArrayList<String> namepassword = new ArrayList<String>();
		namepassword.add(username);
		namepassword.add(password);
		
	  	//Poistaa vanhat bannit
		 
		
		BanningService banService = new BanningService();
		banService.unBan();
		
		
		 //Ottaa yl�s k�ytt�j�n ip-osoitteen
		 
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		
		//Tarkistaa onko k�ytt�j�n ip-osoite bannattujen listalla. Jos on, servlet sammuu
		 
		if (banService.userBanStatus(ipAddress) == false) {
			request.setAttribute("reason", alreadyBanned);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}
		
		
		banService.clearAttempts();
		 
		
		LogInService logService = new LogInService();
		if (logService.tryLogin(ipAddress, namepassword) == 1) {
			request.getRequestDispatcher("WEB-INF/jsp/jumalanpuutarha.jsp").forward(request, response);
		} else if (logService.tryLogin(ipAddress, namepassword) == 0) {
			request.setAttribute("reason", getsBanned);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		} else if (logService.tryLogin(ipAddress, namepassword) == 2) {
			request.setAttribute("reason", wrong);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		} else {
			request.setAttribute("reason", uhoh);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/adminetusivu.jsp").forward(request, response);
	}
	
	
} 

