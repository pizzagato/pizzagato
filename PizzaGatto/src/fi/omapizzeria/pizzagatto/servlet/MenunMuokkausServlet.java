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
import fi.omapizzeria.pizzagatto.bean.Tuote;
import fi.omapizzeria.pizzagatto.service.AddJuomaService;
import fi.omapizzeria.pizzagatto.service.AddPizzaService;
import fi.omapizzeria.pizzagatto.service.AddTayteService;
import fi.omapizzeria.pizzagatto.service.SearchService;

@WebServlet("/MenunMuokkaus")
public class MenunMuokkausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenunMuokkausServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	
	
		
		
		
	// pizzan setit
	//poista pizza	
	
	String poistapizza=request.getParameter("poistapizza");
	
	
	AddPizzaService ad = new AddPizzaService();
	int poistpizz;
	if(request.getParameter("Poista") != null && poistapizza != null){
		poistpizz=Integer.parseInt(poistapizza);
		ad.poistaPizza(poistpizz);
	}
	//muuta pizzan status
	String muutapizza=request.getParameter("pizzanstatus");
	String status=request.getParameter("status");
	int statusint;
	
	if(request.getParameter("Muuta") != null && muutapizza != null && status != null){
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
	System.out.println(pizzanimi);
	System.out.println(pizzahinta);
	System.out.println(pizzatayte1);
	System.out.println(pizzatayte2);
	System.out.println(pizzatayte3);
	System.out.println(pizzatayte4);
	System.out.println(pizzatayte5);

		int pizzt1;
		int pizzt2;
		int pizzt3;
		int pizzt4;
		int pizzt5;
		double pizzhinta;
		int statusnumero=1;
		
		if(request.getParameter("Lis‰‰Pizza") != null){
			pizzhinta=Double.parseDouble(pizzahinta);
			pizzt1=Integer.parseInt(pizzatayte1);
			pizzt2=Integer.parseInt(pizzatayte2);
			pizzt3=Integer.parseInt(pizzatayte3);
			pizzt4=Integer.parseInt(pizzatayte4);
			pizzt5=Integer.parseInt(pizzatayte5);

			
			ad.lisaaPizztayte(pizzanimi, pizzhinta, statusnumero, pizzt1, pizzt2, pizzt3, pizzt4, pizzt5);
		}
	
	//--------------------------------------------------------------------
	// juoman setit
	// poista juoma
	
	String poistajuoma=request.getParameter("poistajuoma");
	AddJuomaService jd = new AddJuomaService();
	if(request.getParameter("Poista") != null && poistajuoma != null){
		jd.poistaJuoma(poistajuoma);
	}
	
	
	
	//lis‰‰ juoma
	String juomanimi=request.getParameter("juomanimi");
	String juomahinta=request.getParameter("juomahinta");
	String koko =request.getParameter("juomakoko");
	String tyyppi=request.getParameter("juomatyyppi");
	double desjuomahinta;
	if(request.getParameter("Lis‰‰Juoma") != null){
		desjuomahinta=Double.parseDouble(juomahinta);
		jd.lisaaJuoma(juomanimi, desjuomahinta, koko, tyyppi);
	}
			
	
	//----------------------------------------------------------------------
	//taytteen setit
	// poista tayte
	String poistatayte=request.getParameter("poistatayte");
	int poist;
	AddTayteService tj = new AddTayteService();
	if(request.getParameter("Poista") != null && poistatayte != null){
		poist=Integer.parseInt(poistatayte);
		tj.poistaTayte(poist);
	}
		
	//lis‰‰ t‰yte
	String taytenimi=request.getParameter("tayte");
	if(request.getParameter("Lis‰‰T‰yte") != null){
		tj.lisaaTayte(taytenimi);
	}
	
	

	SearchService search = new SearchService();
	request.setAttribute("pitsut", search.haePizzat()); 
	request.setAttribute("juomat", search.haeJuotavatMenu());
	request.setAttribute("taytteet", search.haeTaytteet());
	request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
	
	}
	
	
	
}
	

