package com.app.libreria;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
 * Servlet implementation class LoginUser
 */
@WebServlet(description = "login utente", urlPatterns = { "/login.co" })
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
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
		Configuration conf = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		SessionFactory factory = conf.buildSessionFactory(servReg);
		Session session = factory.openSession();
		session.beginTransaction();
		
        String username = request.getParameter("username");
      
              
        String hql = "FROM User WHERE username = :username AND password = :password";
        org.hibernate.Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", PwdEncrypt.encrypt(request.getParameter("password")));
        User user = (User) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        if(user != null) {
        	request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("nome", user.getNome());
        	request.getSession().setAttribute("cognome", user.getCognome());
        	request.getSession().setAttribute("ruolo", user.getRuolo());
        	
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("loginuser.jsp?error=true");
        }
	}

}
