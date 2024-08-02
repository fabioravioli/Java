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
 * Servlet implementation class ListaUtenti
 */
@WebServlet(description = "user list", urlPatterns = { "/userList" })
public class ListaUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SessionFactory factory;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUtenti() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
        Configuration conf = new Configuration().configure();
        ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        factory = conf.buildSessionFactory(servReg);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.addOrder(Order.asc("id"));
            List<User>  users = criteria.list();
            session.getTransaction().commit();
            
            /* effettuo la paginazione; predefinito: 5 righe per pagina */
    		int paginaRichiesta = 1;
    		
    		if (request.getParameter("page") != null) {
    			paginaRichiesta = Integer.parseInt(request.getParameter("page"));
    		}
    		
    		 System.out.println("Numero di utenti trovati: " + (users != null ? users.size() : "0"));
    		
    		int righePerPagina = 5;
    		int righeTotali = users.size();
    		
    		int nPagine = (int) Math.ceil((double)righeTotali / righePerPagina);
    		
    		//se viene richiesto un numero di pagina maggiore delle pagine esistenti, viene restituita l'ultima pagina
    		if (nPagine < paginaRichiesta) paginaRichiesta = nPagine;
    		
    		int limiteMin = (paginaRichiesta - 1) * righePerPagina;
    		int limiteMax = paginaRichiesta * righePerPagina;
    		
    		if (limiteMin < 0) limiteMin = 0;
    		
    		if (limiteMax >= righeTotali) {
    			limiteMax = righeTotali;
    		}

    		
            users = users.subList(limiteMin, limiteMax); //genero una sottolista in base ai risultati ottenuti
            
            
            request.setAttribute("currentPage", paginaRichiesta);
            request.setAttribute("nPagine", nPagine);
   
            request.setAttribute("utenti", users);
            
            request.getRequestDispatcher("gestioneUtenti.jsp").forward(request, response);
            
           
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
