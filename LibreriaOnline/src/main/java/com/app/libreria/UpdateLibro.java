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
 * Servlet implementation class UpdateLibro
 */
@WebServlet(description = "update book", urlPatterns = { "/updateBook.do" })
public class UpdateLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLibro() {
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
		session.beginTransaction();
		
		Book book = new Book();
		book.setId((int)request.getSession().getAttribute("id"));

		book.setTitolo(request.getParameter("titolo"));
		book.setAutore(request.getParameter("autore"));
		book.setAnno_pubblicazione(Integer.valueOf(request.getParameter("annoPubblicazione")));
		book.setQuantità(Integer.valueOf(request.getParameter("quantita")));
		

		session.update(book);
		session.getTransaction().commit();
		

		session.close();
		
		//una volta inviati i dati tramite POST vengono inseriti dentro useradd.jsp
		//RequestDispatcher view = request.getRequestDispatcher("useradd.jsp");
		//poi successivamente vengono elaborati fornendo una domanda e una risposta
		//view.forward(request, response);
		response.sendRedirect("./ListaLibri");

	}

}
