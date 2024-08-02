package com.app.libreria;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Servlet implementation class InstallaLibreria
 */
@WebServlet("/install.do")
public class InstallaLibreria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstallaLibreria() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filePath = "/Users/fabioravioli/eclipse-workspace/LibreriaOnline/src/main/webapp/install.jsp";
		File file = new File(filePath);
		
		if (file.exists()) {
			String DB_URL = "jdbc:postgresql://localhost:5433/";
			final String USER = "postgres";
			final String PASS = "fabioravioli";

			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			final String sql1 = "CREATE DATABASE libreria WITH OWNER = postgres ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C' LOCALE_PROVIDER = 'libc' TABLESPACE = pg_default CONNECTION LIMIT = -1 IS_TEMPLATE = False;";
			
			final String sql2 = "CREATE TABLE IF NOT EXISTS public.libri ( id integer NOT NULL DEFAULT nextval('book_id_seq'::regclass), titolo character varying(100) COLLATE pg_catalog.\"default\" NOT NULL, autore character varying(100) COLLATE pg_catalog.\"default\" NOT NULL, anno_pubblicazione integer NOT NULL, quantita integer, CONSTRAINT libri_pkey PRIMARY KEY (id))";

			final String sql3 = "CREATE TABLE IF NOT EXISTS public.users (id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass), email character varying(50) COLLATE pg_catalog.\"default\" NOT NULL, password character varying(50) COLLATE pg_catalog.\"default\" NOT NULL, nome character varying(50) COLLATE pg_catalog.\"default\" NOT NULL, cognome character varying(50) COLLATE pg_catalog.\"default\" NOT NULL, username character varying(50) COLLATE pg_catalog.\"default\" NOT NULL, ruolo character varying(20) COLLATE pg_catalog.\"default\" NOT NULL, CONSTRAINT users_pkey PRIMARY KEY (id))";
					
			final String sql4 = "CREATE SEQUENCE IF NOT EXISTS public.book_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;";
			
			final String sql5 = "CREATE SEQUENCE IF NOT EXISTS public.users_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;";
			
			final String sql6 = "ALTER SEQUENCE public.book_id_seq OWNER TO public.libri.id;";
			
			final String sql7 = "ALTER SEQUENCE public.book_id_seq OWNED BY postgres;";
			
			final String sql8 = "ALTER SEQUENCE public.users_id_seq OWNER TO public.users.id;";
			
			final String sql9 = "ALTER SEQUENCE public.users_id_seq OWNED BY postgres;";
			
			final String sql10 = "ALTER TABLE IF EXISTS public.libri OWNER to postgres;";
			
			final String sql11 = "ALTER TABLE IF EXISTS public.users OWNER to postgres;";

		
			String pdwEnc = PwdEncrypt.encrypt("admin");
			final String sql12 = "INSERT INTO users (email, password, nome, cognome, username, ruolo) VALUES ('admin@localhost.it', '"+pdwEnc+"', 'admin', 'admin', 'admin', 'amministratore')";

			
			
			
			
			try (	
					Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
					Statement stmt = conn.createStatement();

					){
				stmt.executeUpdate(sql1);
				System.out.println("Database 'libreria' creato...");
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			DB_URL += "libreria";
			
			
			try (	
					Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
					Statement stmt = conn.createStatement();

					){

				stmt.executeUpdate(sql4);
				System.out.println("Sequenza incremento ID libri creata...");
			
				stmt.executeUpdate(sql5);
				System.out.println("Sequenza incremento ID utenti creata...");
	
				//stmt.executeUpdate(sql6);
				System.out.println("Query 6 eseguita...");
				
				//stmt.executeUpdate(sql7);
				System.out.println("Query 7 eseguita...");
				
				//stmt.executeUpdate(sql8);
				System.out.println("Query 8 eseguita...");
				
				//stmt.executeUpdate(sql9);
				System.out.println("Query 9 eseguita...");
				
				stmt.executeUpdate(sql2);
				System.out.println("Tabella libri creata...");
				
				stmt.executeUpdate(sql3);
				System.out.println("Tabella utenti creata...");
				
				stmt.executeUpdate(sql10);
				System.out.println("Query 10 eseguita...");
				
				stmt.executeUpdate(sql11);
				System.out.println("Query 11 eseguita...");
				
				stmt.executeUpdate(sql12);
				System.out.println("Utente Amministratore creato...");


			} catch (SQLException e) {
				e.printStackTrace();
			}


			
			RequestDispatcher view = request.getRequestDispatcher("install.jsp?installOk=true");
			view.forward(request, response);
			
		}
		else {
			System.out.println("Il file non esiste!");
			RequestDispatcher view = request.getRequestDispatcher("install.jsp?installNo=true");
			view.forward(request, response);
		}
		
		
		
	}

}
