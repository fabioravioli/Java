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
 * Servlet implementation class ModificaLibro
 */
@WebServlet(description = "edit book", urlPatterns = { "/editBook" })
public class ModificaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Configuration conf = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		SessionFactory factory = conf.buildSessionFactory(servReg);
		Session session = factory.openSession();
		session.beginTransaction();
		int bookId = Integer.valueOf(request.getParameter("bookId"));


        String hql = "FROM Book WHERE id = :bookId";
        org.hibernate.Query query = session.createQuery(hql);
        query.setParameter("bookId", bookId);
        
        Book book = (Book) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        if(book != null) {
        	request.getSession().setAttribute("id", book.getId());
            request.getSession().setAttribute("titolo", book.getTitolo());
        	request.getSession().setAttribute("autore", book.getAutore());
        	request.getSession().setAttribute("annoPubblicazione", book.getAnno_pubblicazione());
        	request.getSession().setAttribute("quantita", book.getQuantità());
        	
        	//request.getSession().setAttribute("edit", bookId);
        	
        	response.sendRedirect("editLibro.jsp");
        	
        } else {
    
            response.sendRedirect("loginuser.jsp?error=true");
            
        }
	}

}
