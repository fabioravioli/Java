package com.app.libreria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Servlet implementation class AggiungiLibro
 */
@WebServlet(description = "add book", urlPatterns = { "/addBook" })
public class AggiungiLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Configuration config = new Configuration().configure();
		ServiceRegistry servRegist = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory factory = config.buildSessionFactory(servRegist);
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			Book book = new Book(request.getParameter("titolo"), request.getParameter("autore"), Integer.valueOf(request.getParameter("annoPubblicazione")), Integer.valueOf(request.getParameter("quantita")));
			session.save(book);
			session.getTransaction().commit();
			
			session.close();
			
			request.setAttribute("message", "Libro inserito correttamente!");
			
			request.getRequestDispatcher("ListaLibri").forward(request, response);
			
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
