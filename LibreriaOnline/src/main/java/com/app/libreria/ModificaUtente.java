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
 * Servlet implementation class ModificaUtente
 */
@WebServlet(description = "edi user", urlPatterns = { "/editUser" })
public class ModificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	doPost(request, response);
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
		
		int userId = Integer.valueOf(request.getParameter("userId"));

			
        String hql = "FROM User WHERE id = :userId";
        org.hibernate.Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        
        User user = (User) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        if(user != null) {
 
        	request.setAttribute("id", user.getId());
            request.setAttribute("nome", user.getNome());
        	request.setAttribute("cognome", user.getCognome());
        	request.setAttribute("username", user.getUsername());
        	request.setAttribute("email", user.getEmail());
        	request.setAttribute("password", user.getPassword());
        	request.setAttribute("ruolo", user.getRuolo());
        	
        	request.getRequestDispatcher("editUser.jsp").forward(request, response);
            //response.sendRedirect("editUser.jsp");
        } else {
            response.sendRedirect("loginuser.jsp?error=true");
        }
	}

}
