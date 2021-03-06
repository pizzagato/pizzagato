package fi.omapizzeria.pizzagatto.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fi.omapizzeria.pizzagatto.bean.Asiakastiedot;
import fi.omapizzeria.pizzagatto.bean.Tilaus;
import fi.omapizzeria.pizzagatto.bean.Tilausrivi;
import fi.omapizzeria.pizzagatto.bean.Tuote;
import fi.omapizzeria.pizzagatto.dao.VahvistusDAO;

/**
 * Servlet implementation class VahvistusServlet
 */
@WebServlet("/Vahvistus")
public class VahvistusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VahvistusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();;
		@SuppressWarnings("unchecked")
		ArrayList<Tilausrivi> tilRivit = (ArrayList<Tilausrivi>) request.getSession().getAttribute("tilRivit");
		request.getAttribute("tilRivit");
		request.getAttribute("AsTied");
		ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
		try {
			for (int i = 0; i < tilRivit.size(); i++) {
				Tuote t = new Tuote();
				t.setId(tilRivit.get(i).getTuote().getId());
				t.setNimi(tilRivit.get(i).getTuote().getNimi());
				t.setHinta(tilRivit.get(i).getTuote().getHinta());
				tuotteet.add(t);
			}
		} catch (Exception e) {
			response.sendRedirect("/PizzaGatto/Tilaa");
			return;
		}
		
		session.setAttribute("tuotteet", tuotteet);
		
		
		
		request.getRequestDispatcher("WEB-INF/jsp/vahvistus.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		Asiakastiedot asTied = (Asiakastiedot) session.getAttribute("asTied");
		double kokonaishinta;
		String kokHi = (String) session.getAttribute("kokHi");
		kokonaishinta = Double.parseDouble(kokHi);
		@SuppressWarnings("unchecked")
		ArrayList<Tilausrivi> tilRivit = (ArrayList<Tilausrivi>) session.getAttribute("tilRivit");
		Tilaus tilaus = new Tilaus(asTied, tilRivit, kokonaishinta);
		
		if ("Vahvista".equals(action)) {
			try {
				VahvistusDAO vahvistus = new VahvistusDAO();
				vahvistus.lisaaVahvistus(tilaus, tilRivit);
				response.sendRedirect("/PizzaGatto/Etusivu");
				session.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if ("Takaisin".equalsIgnoreCase(action)) {
			response.sendRedirect("/PizzaGatto/Tilaa");
		}
		
	}

}
