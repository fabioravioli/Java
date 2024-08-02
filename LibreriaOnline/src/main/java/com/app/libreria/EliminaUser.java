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
 * Servlet implementation class EliminaUser
 */
@WebServlet(description = "delete user", urlPatterns = { "/deleteUser.do" })
public class EliminaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaUser() {
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
		User user = new User();
		
		user.setId(Integer.valueOf(request.getParameter("userId")));
		session.delete(user);
		session.getTransaction().commit();
		
		session.close();
		
		String redirect = "userList";
		
		if (request.getParameter("currentPage") != null) {
			redirect += "?page="+request.getParameter("currentPage");
		}

		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
