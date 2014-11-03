package fi.omapizzeria.admin.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.omapizzeria.admin.bean.Tilaus;
import fi.omapizzeria.admin.bean.Juoma;
import fi.omapizzeria.admin.bean.Pizzalistaan;

@WebServlet("/Tilaa") //P‰‰te joka n‰kyy URLissa
public class TilaaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TilaaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Ohjaa tilaussivulle
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pizzalistaan> pizzat;
		SearchService search = new SearchService();
		pizzat=search.haePizzat();
		request.setAttribute("pitsut", pizzat);
		
		ArrayList<Juoma> juomat;
		JuomaService jj= new JuomaService();
		juomat=jj.haeJuotavat();
		request.setAttribute("juomat",juomat);
		
		
			
	
		
		
		request.getRequestDispatcher("WEB-INF/jsp/tilaa.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pizza = request.getParameter("pizza");
		String juoma = request.getParameter("juoma");
		
		String nimi=request.getParameter("nimi");
		String puhnro=request.getParameter("puhnro");
		String sposti=request.getParameter("email");
		String osoite=request.getParameter("osoite");
		String pnro=request.getParameter("pnro");
		String lt=request.getParameter("lisatoiveet");
		
		Tilaus at=new Tilaus(nimi, puhnro, sposti, osoite, pnro, lt);
		
		request.getSession().setAttribute("pizza",pizza);
		request.getSession().setAttribute("juoma",juoma);
		request.getSession().setAttribute("asiakastiedot",at);
		response.sendRedirect("/PizzaGatto/Vahvistus");
		
		

	}

	
	
}
