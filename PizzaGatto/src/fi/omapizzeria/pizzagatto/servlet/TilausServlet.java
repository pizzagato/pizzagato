package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import fi.omapizzeria.pizzagatto.bean.Asiakastiedot;
import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tilausrivi;
import fi.omapizzeria.pizzagatto.service.PublicService;

@WebServlet("/Tilaa")
public class TilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		ArrayList<Juoma> juomat;
		PublicService search = new PublicService();
		
		pizzat=search.haeNaytettavatPizzat();
		request.setAttribute("pitsut", pizzat);
		
		juomat=search.haeJuotavatTilaus();
		request.setAttribute("juomat",juomat);
		
		DateTime time = new DateTime();
		if (time.getDayOfWeek()==7 || (time.getDayOfWeek()==6 && (time.getHourOfDay()<=10  || time.getHourOfDay()>=(23-1))) ||
				((time.getDayOfWeek()!=6 || time.getDayOfWeek()!=7) && (time.getHourOfDay()<=9 || time.getHourOfDay()>=(23-1)))) {
			request.getRequestDispatcher("WEB-INF/jsp/tilaa.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/tilaa.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double kokHint=0;
		String kokHi;
		int kpl1=0;
		int kpl2=0;
		HttpSession session = request.getSession();	// T‰ss‰ aloitetaan/alustetaan sessio.
		@SuppressWarnings("unchecked")
		ArrayList<Tilausrivi> tilRivit = (ArrayList<Tilausrivi>) session.getAttribute("tilRivit");	// Toistaiseksi ostoskori meiningill‰, kannattanee tehd‰ tilaus/tilausriveill‰.
		Asiakastiedot asTiedot = (Asiakastiedot) session.getAttribute("asTied");
		if (tilRivit != null) {
		}else {
			tilRivit = new ArrayList<Tilausrivi>();
		}
		String pizzanimi = request.getParameter("pizza");
		String juomanimi = request.getParameter("juoma");
		String kapl = request.getParameter("tuotekpl");
		String jkapl = request.getParameter("tuotekpl2");
		try {
			kpl1 = Integer.parseInt(kapl);
		} catch (Exception e) {
		}
		try {
			kpl2 = Integer.parseInt(jkapl);
		} catch (Exception e) {
		}
		
		String nimi=request.getParameter("nimi");
		String puhnro=request.getParameter("puhnro");
		String sposti=request.getParameter("email");
		String osoite=request.getParameter("osoite");
		String pnro=request.getParameter("pnro");
		String lt=request.getParameter("lisatoiveet");

		Asiakastiedot asiakTiedot = new Asiakastiedot();
		asiakTiedot.setNimi(nimi);
		asiakTiedot.setPuhelin(puhnro);
		asiakTiedot.setSpost(sposti);
		asiakTiedot.setOsoite(osoite);
		asiakTiedot.setPstnro(pnro);
		asiakTiedot.setLisatoiveet(lt);
		if (asTiedot !=null) {
			asTiedot = asiakTiedot;
		}
		
		PublicService ps = new PublicService();
		tilRivit = ps.tilauksenTilausrivi(pizzanimi, juomanimi, kpl1, kpl2, tilRivit);
		
		session.setAttribute("tilRivit", tilRivit); // T‰m‰ oli avain sessioon.
		String action = request.getParameter("action");
		if ("Lis‰‰ Ostoskoriin".equals(action)) {
			for (int i = 0; i < tilRivit.size(); i++) {
				kokHint = kokHint + tilRivit.get(i).getKokonaishinta();
			}
			kokHi = Double.toString(kokHint);
			session.setAttribute("kokHi", kokHi);
			response.sendRedirect("/PizzaGatto/Tilaa");
		}else if ("Tyhjenn‰".equals(action)) {
			request.getSession().invalidate();
			response.sendRedirect("/PizzaGatto/Tilaa");
		}
		else {
			session.setAttribute("asTied", asiakTiedot);
			response.sendRedirect("/PizzaGatto/Vahvistus");
		}
	}
	
}