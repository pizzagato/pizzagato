package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import fi.omapizzeria.pizzagatto.service.SearchService;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.JuomaDAO;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;

@WebServlet("/Tilaa") //P‰‰te joka n‰kyy URLissa
public class TilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //Ohjaa tilaussivulle
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pitsut", pizzat);
		
		ArrayList<Juoma> juomat;
		juomat=search.haeJuotavatTilaus();
		request.setAttribute("juomat",juomat);
		DateTime time = new DateTime();
		if (time.getDayOfWeek()==7 || (time.getDayOfWeek()==6 && (time.getHourOfDay()<=10  || time.getHourOfDay()>=(23-1))) ||
				((time.getDayOfWeek()!=6 || time.getDayOfWeek()!=7) && (time.getHourOfDay()<=9 || time.getHourOfDay()>=(23-1)))) {
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/tilaa.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	// T‰ss‰ aloitetaan/alustetaan sessio.
		ArrayList<Tilausrivi> tilRivit = (ArrayList<Tilausrivi>) session.getAttribute("tilRivit");	// Toistaiseksi ostoskori meiningill‰, kannattanee tehd‰ tilaus/tilausriveill‰.
		Asiakastiedot asTiedot = (Asiakastiedot) session.getAttribute("asTied");
		double kokHint=0;
		String kokHi;
		int kpl1=0;
		int kpl2=0;
		//String kokHi = (String) session.getAttribute("kokHi");
		//try {
		//	kokHint = Double.parseDouble(kokHi);
		//} catch (Exception e) {
		//	kokHint = 0;
		//}
		Pizza pizza = new Pizza();
		Juoma juoma = new Juoma();
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
		//	}
		
		//if (asTiedot != null) {
			
		//}else {
			Asiakastiedot asiakTiedot = new Asiakastiedot();
			asiakTiedot.setNimi(nimi); //puhnro, sposti, osoite, pnro, lt;
			asiakTiedot.setPuhelin(puhnro);
			asiakTiedot.setSpost(sposti);
			asiakTiedot.setOsoite(osoite);
			asiakTiedot.setPstnro(pnro);
			asiakTiedot.setLisatoiveet(lt);
			if (asTiedot !=null) {
				asTiedot = asiakTiedot;
			}
			
		//}
		
		pizza.setNimi(pizzanimi);
		juoma.setNimi(juomanimi);		
		// Haetaan pitsojen ja juomien hintatiedot DAO-luokkien avulla
		try {
			PizzaDAO pizzahinta = new PizzaDAO();
			PizzaDAO pizzaId = new PizzaDAO();
			pizza.setHinta(pizzahinta.pizzaHinta(pizzanimi));
			pizza.setId(pizzaId.pizzaid(pizzanimi));
			} catch (DAOPoikkeus e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				JuomaDAO juomahinta = new JuomaDAO();
				JuomaDAO juomaid = new JuomaDAO();
				juoma.setHinta(juomahinta.juomaHinta(juomanimi));
				juoma.setId(juomaid.juomaid(juomanimi));
			} catch (DAOPoikkeus e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		double juomaKokHinta = juoma.getHinta()*kpl2;
		double pizzaKokHinta = pizza.getHinta()*kpl1;
		Tilausrivi juomaRivi = new Tilausrivi(juoma, kpl2, juomaKokHinta);
		Tilausrivi pizzaRivi = new Tilausrivi(pizza, kpl1, pizzaKokHinta);
		if (pizza.getNimi() != null && pizza.getHinta() != null) {
			tilRivit.add(pizzaRivi);
		}
		if (juoma.getNimi() != null && juoma.getHinta() != null) {
			tilRivit.add(juomaRivi);
		}
		//tilausrivit.add(juomaRivi);
		//tilausrivit.add(pizzaRivi);
		session.setAttribute("tilRivit", tilRivit); // T‰m‰ oli avain sessioon.
		// session.setAttribute("asTied", asTiedot);
		String action = request.getParameter("action");
		if ("Lis‰‰".equals(action)) {
			for (int i = 0; i < tilRivit.size(); i++) {
				kokHint = kokHint + tilRivit.get(i).getKokonaishinta();
				
			}
			kokHi = Double.toString(kokHint);
			session.setAttribute("kokHi", kokHi);
			System.out.println(tilRivit);
			response.sendRedirect("/PizzaGatto/Tilaa");
		}else if ("Tyhjenna".equals(action)) {
			request.getSession().invalidate();
			response.sendRedirect("/PizzaGatto/Tilaa");
		}
		else {
			session.setAttribute("asTied", asiakTiedot);
			response.sendRedirect("/PizzaGatto/Vahvistus");
		}
		// response.sendRedirect("/PizzaGatto/Tilaa");	// Toistaiseksi tilaussivun ainoa submit nappula pakotetaan ohjaamaan takaisin tilaussivulle.
	}
	
}