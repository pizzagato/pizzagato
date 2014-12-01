package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	try {
		poistpizz=Integer.parseInt(poistapizza);
		ad.poistaPizza(poistpizz);
		
	} catch (Exception e) {
		// TODO: handle exception
		}
			}
	
	//muuta pizzan status
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
	
	
	//uuden pizzan lis�ys t�ytteineen
	String pizzanimi=request.getParameter("pizzanimi");
	String pizzahinta=request.getParameter("pizzahinta");
	double pizzhinta;
	int statusnumero=1;
	
	
	ArrayList<Integer> taytteet = new ArrayList<Integer>();
	

		if(request.getParameter("Lis��Pizza") != null){
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
	// poista juoma
	
	
		String poistajuoma=request.getParameter("poistajuoma");
		int juomanum;
	AddJuomaService jd = new AddJuomaService();
	if(request.getParameter("Poista") != null){
		try {
		juomanum=Integer.parseInt(poistajuoma);
		jd.poistaJuoma(juomanum);
		
			} catch (Exception e) {
		// TODO: handle exception
		}
			}
	
	
	
	//lis�� juoma
	String juomanimi=request.getParameter("juomanimi");
	String juomahinta=request.getParameter("juomahinta");
	String koko =request.getParameter("juomakoko");
	String tyyppi=request.getParameter("juomatyyppi");
	double desjuomahinta;
	if(request.getParameter("Lis��Juoma") != null){
		try {
			desjuomahinta=Double.parseDouble(juomahinta);
			jd.lisaaJuoma(juomanimi, desjuomahinta, koko, tyyppi);
		} catch (Exception e) {
	// TODO: handle exception
			}
		
				}
		
	
	//----------------------------------------------------------------------
	//taytteen setit
	// poista tayte
	String poistatayte=request.getParameter("poistatayte");
	int poist;
	AddTayteService tj = new AddTayteService();
	if(request.getParameter("Poista") != null && poistatayte != null){
		
		try {
			poist=Integer.parseInt(poistatayte);
			tj.poistaTayte(poist);
		
		} catch (Exception e) {
		// TODO: handle exception
			}
				}
		
	//lis�� t�yte
	String taytenimi=request.getParameter("tayte");
	if(request.getParameter("Lis��T�yte") != null){
		
		try {
			tj.lisaaTayte(taytenimi);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

	SearchService search = new SearchService();
	request.setAttribute("pitsut", search.haePizzat()); 
	request.setAttribute("juomat", search.haeJuotavatMenu());
	request.setAttribute("taytteet", search.haeTaytteet());
	request.getRequestDispatcher("WEB-INF/jsp/menunmuokkaus.jsp").forward(request, response);
	
	}
	
	
	
}
	

