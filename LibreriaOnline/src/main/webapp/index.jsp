<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel = "stylesheet" href = "./css/menu.css">
	<title>Home</title>
</head>

<body>
	<%
		if (request.getSession().getAttribute("nome") == null ) {
				response.sendRedirect("loginuser.jsp");
			}
		else {

	%>
	<div class="header">
		<h1>LIBRERIA ONLINE</h1>
	</div> <!-- fine header -->

	<div class="topnav">
		<ul class = "menu-nav">
			<li> <a href = "index.jsp">HOME</a> </li>
			<%
			if (request.getSession().getAttribute("nome") == null ) {
				%>
				<li> <a href = "loginuser.jsp">LOGIN</a> </li>
				<%
			}
			else {
				%>
				<li> <a href = "logout">LOGOUT</a></li>
				<%
			}
			%>
			<li> <a href = "register.jsp">REGISTRATI</a> </li>
		</ul>
		
		<%
			if (request.getSession().getAttribute("nome") != null) {
				
			
		%>
		<p class="welcomeMessage">Benvenuto <%=request.getSession().getAttribute("nome") %> <a href="editUser?userId=<%=request.getSession().getAttribute("id")%>">[Modifica Profilo]</a></p>
		<%
			}
		%>
		
	</div> <!-- fine topnav -->
	

	<div class="row">
		<div class="leftcolumn">
			<div class="image">
				<img src="./img/libreria.jpeg">
			</div>
		</div>
		
		<div class="rightcolumn">
			<div class="card">
				<ul class = "menu-sec">
					<li><a href="gestioneLibri.jsp">GESTIONE LIBRI</a></li>
					
					<%
						if (request.getSession().getAttribute("ruolo") != null && (request.getSession().getAttribute("ruolo").equals("amministratore"))) {
					%>
				
					<li><a href="gestioneUtenti.jsp">GESTIONE UTENTI</a></li>
				
				<%
					}
				%>
					
				</ul>
			</div>
		
		<%
		}
		%>
			
		</div> <!--  fine righcolumn -->

	</div> <!-- fine row -->
	
	<footer>
		<h4>Contatti</h4>
		<p>Fabio Ravioli</p>
		<p>fabio.ravioli@gmail.com</p>
	</footer>
	
</body>
</html>