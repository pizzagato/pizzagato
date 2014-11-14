package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.pizzagatto.bean.Juoma;
import fi.omapizzeria.pizzagatto.bean.Pizza;
import fi.omapizzeria.pizzagatto.bean.Tayte;
import fi.omapizzeria.pizzagatto.dao.PizzaDAO;
import fi.omapizzeria.pizzagatto.service.AddJuomaService;
import fi.omapizzeria.pizzagatto.service.AddPizzaService;
import fi.omapizzeria.pizzagatto.service.AddTayteService;
import fi.omapizzeria.pizzagatto.service.JuomaService;
import fi.omapizzeria.pizzagatto.service.SearchService;
import fi.omapizzeria.pizzagatto.service.TayteService;

/**
 * Servlet implementation class MenunMuokkausServlet
 */
@WebServlet("/MenunMuokkaus")
public class MenunMuokkausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenunMuokkausServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//tuodaan pizzat, juomat ja taytteet sivulle
		
		//pizzat
		ArrayList<Pizza> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pitsut", pizzat);
		
		
		//juomat
		ArrayList<Juoma> juomat;
		JuomaService jj= new JuomaService();
		juomat=jj.haeJuotavatAdmin();
		request.setAttribute("juomat",juomat);
		
		//taytteet
		ArrayList<Tayte> taytteet;
		TayteService tt = new TayteService();
		taytteet=tt.haeTaytteet();
		request.setAttribute("taytteet",taytteet);
		
		
		
		
		
		
		 
		request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// pizzan setit
	//poista pizza	
	
	String poistapizza=request.getParameter("poistapizza");
	AddPizzaService ad = new AddPizzaService();
	if(poistapizza != null){
		ad.poistaPizza(poistapizza);
	}
	//muuta pizzan status
	String muutapizza=request.getParameter("pizzanstatus");
	String status=request.getParameter("status");
	int statusint;
	
	if(muutapizza != null && status != null){
		 statusint=Integer.parseInt(status);
	
	ad.muutaStatus(muutapizza, statusint);
	
	}
	//uuden pizzan lis‰ys t‰ytteineen
	String pizzanimi=request.getParameter("pizzanimi");
	String pizzahinta=request.getParameter("pizzahinta");
	String pizzatayte1=request.getParameter("pizzatayte1");
	String pizzatayte2=request.getParameter("pizzatayte2");
	String pizzatayte3=request.getParameter("pizzatayte3");
	String pizzatayte4=request.getParameter("pizzatayte4");
	String pizzatayte5=request.getParameter("pizzatayte5");
	double pizzhinta;
	int statusnumero=1;
	
	if(pizzanimi != null && pizzahinta != null && pizzatayte1 != null &&
		pizzatayte2 != null && pizzatayte3 != null && pizzatayte4 !=null &&
		pizzatayte5 != null){
		pizzhinta=Double.parseDouble(pizzahinta);
		Tayte tayte1 = new Tayte(pizzatayte1);
		Tayte tayte2 = new Tayte(pizzatayte2);
		Tayte tayte3 = new Tayte(pizzatayte3);
		Tayte tayte4 = new Tayte(pizzatayte4);
		Tayte tayte5 = new Tayte(pizzatayte5);
		ArrayList<Tayte> pizzataytteet = new ArrayList<Tayte>();
		pizzataytteet.add(tayte1);
		pizzataytteet.add(tayte2);
		pizzataytteet.add(tayte3);
		pizzataytteet.add(tayte4);
		pizzataytteet.add(tayte5);
		
		Pizza pizza = new Pizza(pizzanimi, pizzhinta, pizzataytteet, statusnumero);
		ad.lisaaPizza(pizza);
	}
	
	//--------------------------------------------------------------------
	// juoman setit
	// poista juoma
	
	String poistajuoma=request.getParameter("poistajuoma");
	AddJuomaService jd = new AddJuomaService();
	if(poistajuoma != null){
		jd.poistaJuoma(poistajuoma);
	}
	
	
	
	//lis‰‰ juoma
	String juomanimi=request.getParameter("juomanimi");
	String juomahinta=request.getParameter("juomahinta");
	String koko =request.getParameter("juomakoko");
	String tyyppi=request.getParameter("juomatyyppi");
	double desjuomahinta;
	if(juomanimi != null && juomahinta != null && koko != null && tyyppi != null){
		desjuomahinta=Double.parseDouble(juomahinta);
		jd.lisaaJuoma(juomanimi, desjuomahinta, koko, tyyppi);
	}
			
	
	//----------------------------------------------------------------------
	//taytteen setit
	// poista tayte
	String poistatayte=request.getParameter("poistatayte");
	AddTayteService tj = new AddTayteService();
	if(poistatayte != null){
		tj.poistaTayte(poistatayte);
	}
		
	//lis‰‰ t‰yte
	String taytenimi=request.getParameter("tayte");
	if(taytenimi != null){
		tj.lisaaTayte(taytenimi);
	}
	
	
	
	
	
	
	
	
	response.sendRedirect("/PizzaGatto/MenunMuokkaus");
	
	}
	
	
	
}
	

