package com.app.libreria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.service.ServiceRegistry;

/**
 * Servlet implementation class ListaLibri
 */
@WebServlet(description = "elenco libri", urlPatterns = { "/ListaLibri" })
public class ListaLibri extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SessionFactory factory;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaLibri() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
        Configuration conf = new Configuration().configure();
        ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        factory = conf.buildSessionFactory(servReg);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getSession().getAttribute("nome") == null ) {
    		response.sendRedirect("loginuser.jsp");
    	}
    	else {
    
   
    		
	    	Session session = factory.openSession();
	
	        try {
	            session.beginTransaction();
	            Criteria criteria = session.createCriteria(Book.class);
	            criteria.addOrder(Order.asc("id"));
	            List<Book> libri = criteria.list(); //lista di tutti i libri contenuti nel db
	            session.getTransaction().commit();
	            
	            
	            /* effettuo la paginazione; predefinito: 5 righe per pagina */
	    		int paginaRichiesta = 1;
	    		
	    		if (request.getParameter("page") != null) {
	    			paginaRichiesta = Integer.parseInt(request.getParameter("page"));
	    		}
	    		
	    		System.out.println("Numero di libri trovati: " + (libri != null ? libri.size() : "0"));
	    		
	    		int righePerPagina = 5;
	    		int righeTotali = libri.size();
	    		
	    		int nPagine = (int) Math.ceil((double)righeTotali / righePerPagina);
	    		
	    		 //se viene richiesto un numero di pagina maggiore delle pagine esistenti, viene restituita l'ultima pagina
	    		if (nPagine < paginaRichiesta) paginaRichiesta = nPagine;
	    		
	    		int limiteMin = (paginaRichiesta - 1) * righePerPagina;
	    		int limiteMax = paginaRichiesta * righePerPagina;
	    		
	    		if (limiteMin < 0) limiteMin = 0;
	    		
	    		if (limiteMax >= righeTotali) {
	    			limiteMax = righeTotali;
	    		}

	    		
	            libri = libri.subList(limiteMin, limiteMax); //genero una sottolista in base ai risultati ottenuti
	            
	            request.setAttribute("currentPage", paginaRichiesta);
	            request.setAttribute("nPagine", nPagine);
	            
	            request.setAttribute("libri", libri);
	            request.getRequestDispatcher("gestioneLibri.jsp").forward(request, response);
    
	        } catch (Exception e) {
	            e.printStackTrace();
	            if (session.getTransaction() != null) {
	                session.getTransaction().rollback();
	            }
	        } finally {
	            session.close();
	        }
    	}
    }
}
