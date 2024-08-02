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
 * Servlet implementation class UpdateUser
 */
@WebServlet(description = "user update", urlPatterns = { "/updateUser.do" })
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
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
		
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setNome(request.getParameter("nome"));
		user.setCognome(request.getParameter("cognome"));
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRuolo(request.getParameter("ruolo"));


		session.update(user);
		session.getTransaction().commit();
		

		session.close();
		
		//una volta inviati i dati tramite POST vengono inseriti dentro useradd.jsp
		//RequestDispatcher view = request.getRequestDispatcher("useradd.jsp");
		//poi successivamente vengono elaborati fornendo una domanda e una risposta
		//view.forward(request, response);
		response.sendRedirect("userList");
	}

}
