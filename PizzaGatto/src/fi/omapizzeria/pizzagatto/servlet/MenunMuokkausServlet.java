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
	//muuta pizzan status
	AddPizzaService ad = new AddPizzaService();
	String muutapizza=request.getParameter("pizzanstatus");
	String status=request.getParameter("status");
	int statusint;
	int pizzaid;
	if(request.getParameter("Muuta") != null && muutapizza != null && status != null){
	try {
		 statusint=Integer.parseInt(status);
		 pizzaid=Integer.parseInt(muutapizza);
			
			ad.muutaStatus(pizzaid, statusint);
	} catch (Exception e) {
		// TODO: handle exception
		}

			}
	
	
	//uuden pizzan lis‰ys t‰ytteineen
	String pizzanimi=request.getParameter("pizzanimi");
	String pizzahinta=request.getParameter("pizzahinta");
	double pizzhinta;
	int statusnumero=1;
	
	
	ArrayList<Integer> taytteet = new ArrayList<Integer>();
	

		if(request.getParameter("Lis‰‰Pizza") != null){
			try {
				pizzhinta=Double.parseDouble(pizzahinta);
				for (int i = 0; i < 5; i++) {
					String tayte=request.getParameter("pizzatayte"+(i+1));
					if(tayte != null){
						taytteet.add(Integer.parseInt(tayte));
					}
				}
			
				
				
				ad.lisaaPizztayte(pizzanimi, pizzhinta, statusnumero,taytteet);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	
	//--------------------------------------------------------------------
	// juoman setit
	//lis‰‰ juoma
	AddJuomaService jd = new AddJuomaService();
	String juomanimi=request.getParameter("juomanimi");
	String juomahinta=request.getParameter("juomahinta");
	String koko =request.getParameter("juomakoko");
	String tyyppi=request.getParameter("juomatyyppi");
	double desjuomahinta;
	if(request.getParameter("Lis‰‰Juoma") != null){
		try {
			desjuomahinta=Double.parseDouble(juomahinta);
			jd.lisaaJuoma(juomanimi, desjuomahinta, koko, tyyppi);
		} catch (Exception e) {
	// TODO: handle exception
			}
		
				}
		
	
	//----------------------------------------------------------------------
	//taytteen setit
	//lis‰‰ t‰yte
	AddTayteService tj = new AddTayteService();
	String taytenimi=request.getParameter("tayte");
	if(request.getParameter("Lis‰‰T‰yte") != null){
		
		try {
			tj.lisaaTayte(taytenimi);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//------------------------------------------------------------------------
	//poista tuote
	String optgroup= request.getParameter("optgroup");
	String tuotenimi= request.getParameter("tuotenimi");
	int tuoteid;

	
	

	
	
	
	if(request.getParameter("Poista") != null){
		tuoteid=Integer.parseInt(tuotenimi);
	if(optgroup.equals("Pizzat")){
		ad.poistaPizza(tuoteid);
	}else if(optgroup.equals("Juomat")){
		jd.poistaJuoma(tuoteid);
	}else if(optgroup.equals("T‰ytteet")){
		tj.poistaTayte(tuoteid);
	}
	
	}
	

	SearchService search = new SearchService();
	request.setAttribute("pitsut", search.haePizzat()); 
	request.setAttribute("juomat", search.haeJuotavatMenu());
	request.setAttribute("taytteet", search.haeTaytteet());
	request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
	
	}
	
	
	
}
	

