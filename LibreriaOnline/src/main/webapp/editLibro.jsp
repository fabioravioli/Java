<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>


<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel = "stylesheet" href = "./css/menu.css">
	<link rel = "stylesheet" href = "./css/form.css">
	<link rel = "stylesheet" href = "./css/table.css">
	<title>Modifica Libro</title>
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
	
	<div class="row ">
		<div class="leftcolumn register">
			<h2 class="titleSection">Modifica Libro</h2>
				<div class = "container_reg">
					<div class = "container-input">
						<form action = "./updateBook.do" method = "POST">
							<label for = "titolo" class = "form-label">Titolo*</label>  
							<input type = "text" class = "form-control" id = "titolo" placeholder = "Titolo" name = "titolo" value="<%=request.getSession().getAttribute("titolo")%>" required>
							
							<label for = "autore" class = "form-label">Autore*</label>
							<input type = "text" class = "form-control" id = "autore" placeholder = "Autore" name = "autore" value=" <%= request.getSession().getAttribute("autore") %>" required pattern="[a-zA-Z ]+">

							<label for = "annoPubblicazione" class = "form-label">Anno di pubblicazione*</label>
							<input type="number" min="1900" max="<% LocalDate date =LocalDate.now(); out.print(date.getYear());%>" step="1" class = "form-control" id = "annoPubblicazione" placeholder = "Anno di pubblicazione" value="<%= request.getSession().getAttribute("annoPubblicazione") %>" name = "annoPubblicazione" required>
							
							
							<label for = "quantita" class = "form-label">Quantità</label>
							<input type = "number" class = "form-control" id = "quantita" placeholder = "Quantità" name = "quantita" value="<%= request.getSession().getAttribute("quantita") %>">
								
							<button type = "submit">Invia</button>
							<input type="reset" class="form-control" id="reset" value="reset">
							
						</form>
					</div>
				</div>
		</div>
	</div>	

<%

}

%>

	<footer>
		<h4>Contatti</h4>
		<p>Fabio Ravioli</p>
		<p>fabio.ravioli@gmail.com</p>
	</footer>
</body>
</html>