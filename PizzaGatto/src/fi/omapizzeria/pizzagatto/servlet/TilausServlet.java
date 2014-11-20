package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Asiakastiedot;
import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tilaus;
import fi.omapizzeria.pizzagatto.bean.Tilausrivi;
import fi.omapizzeria.pizzagatto.service.SearchService;
import fi.omapizzeria.pizzagatto.dao.DAOPoikkeus;
import fi.omapizzeria.pizzagatto.dao.TilausDAO;

@WebServlet("/Tilaa") //P‰‰te joka n‰kyy URLissa
public class TilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TilausServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Ohjaa tilaussivulle
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizza> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pitsut", pizzat);
		
		ArrayList<Juoma> juomat;
		juomat=search.haeJuotavatTilaus();
		request.setAttribute("juomat",juomat);	
		request.getRequestDispatcher("WEB-INF/jsp/tilaa.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tarvitaan sessio, koko roska menee pahimmassa tapauksessa uusiksi.
		
		// T‰ss‰ otetaan String muuttujiin tiedot tilaus.jsp:n lomakkeista. 
		// {
		String pizzanimi = request.getParameter("pizza");
		String juomanimi = request.getParameter("juoma");
		String kapl = request.getParameter("tuotekpl");
		String jkapl = request.getParameter("tuotekpl2");
		int kpl1 = Integer.parseInt(kapl);
		int kpl2 = Integer.parseInt(jkapl);
		String nimi=request.getParameter("nimi");
		String puhnro=request.getParameter("puhnro");
		String sposti=request.getParameter("email");
		String osoite=request.getParameter("osoite");
		String pnro=request.getParameter("pnro");
		String lt=request.getParameter("lisatoiveet");
		//	}
	ArrayList<Tilausrivi> tilaukset = new ArrayList<Tilausrivi>();
		Tilausrivi tilRivi = null;
		Tilausrivi tilRivi2 = null;
		Asiakastiedot asTied = new Asiakastiedot(nimi, puhnro ,sposti ,osoite ,pnro ,lt);
		Pizza pizza = new Pizza(pizzanimi);
		Juoma juoma = new Juoma(juomanimi);
		double pizzanhinta = 0;
		double juomanhinta = 0;
		// Haetaan pitsojen ja juomien hintatiedot DAO-luokkien avulla
		try {
			TilausDAO pizzahinta = new TilausDAO(pizzanimi);
			pizzanhinta = pizzahinta.pizzatieto(pizzanimi);
		} catch (DAOPoikkeus e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			TilausDAO hinta = new TilausDAO(pizzanimi);
			juomanhinta = hinta.juomatieto(juomanimi);
		} catch (DAOPoikkeus e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		// Lasketaan kokonaishinnat ensin tuotteittain. Sen j‰lkeen koko tilauksen kokonaishinta.
		double kokonaishintaPizza = (kpl1*pizzanhinta);
		double kokonaishintaJuoma = (kpl2*juomanhinta);
		double kokonaishintaTilaus = (kokonaishintaPizza+kokonaishintaJuoma);
		// Tehd‰‰n tilausrivit.
		if (pizzanimi != null) {
			tilRivi = new Tilausrivi(pizza, kpl1, kokonaishintaPizza);
			tilaukset.add(tilRivi);
		}if (juomanimi != null) {
			tilRivi2 = new Tilausrivi(juoma, kpl2, kokonaishintaJuoma);
			tilaukset.add(tilRivi2);
		}
		Tilaus at=new Tilaus(asTied, tilaukset, kokonaishintaTilaus);	// Luodaan Tilaus olio, johon syˆtet‰‰n tilausrivit.
		request.getSession().setAttribute("tilaus", at);
		request.getSession().setAttribute("asiakastiedot",asTied);
		response.sendRedirect("/PizzaGatto/Vahvistus");		
	}	
}
