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
import org.hibernate.service.ServiceRegistry;

/**
 * Servlet implementation class EliminaLibro
 */
@WebServlet(description = "delete book", urlPatterns = { "/deleteBook" })
public class EliminaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Configuration config = new Configuration().configure();
		ServiceRegistry servRegist = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory factory = config.buildSessionFactory(servRegist);
		Session session = factory.openSession();
		session.beginTransaction();
		Book book = new Book();
		book.setId(Integer.valueOf(request.getParameter("bookId")));
		session.delete(book);
		session.getTransaction().commit();
		
		session.close();
		
		String redirect = "ListaLibri";
		
		if (request.getParameter("currentPage") != null) {
			redirect += "?page="+request.getParameter("currentPage");
		}

		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
