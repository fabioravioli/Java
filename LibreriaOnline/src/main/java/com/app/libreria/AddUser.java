package com.app.libreria;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class AddUser
 */
@WebServlet(description = "aggiunge nuovo utente", urlPatterns = { "/createUser.do" })
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("loginuser.jsp");
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
		
		String hql = "FROM User WHERE username = :username";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("username", request.getParameter("username"));
		User userTmp = (User) query.uniqueResult();

		if(userTmp != null) { //esiste già un utente con lo stesso username
			session.close();
	
			RequestDispatcher view = request.getRequestDispatcher("register.jsp?userExists=true");
			view.forward(request, response);
			
		}
		else {
			
			
			User user = new User(request.getParameter("username"), request.getParameter("email"), PwdEncrypt.encrypt(request.getParameter("password")), request.getParameter("nome"), request.getParameter("cognome"), "user");

			session.save(user);
			session.getTransaction().commit();	
			session.close();
			
			System.out.println(request.getSession().getId());
			
			RequestDispatcher view = request.getRequestDispatcher("register.jsp?userOk=true");
			view.forward(request, response);
		}
		
	}

}
