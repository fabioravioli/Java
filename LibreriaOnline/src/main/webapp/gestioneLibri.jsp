<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.LocalDate" %>


<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel = "stylesheet" href = "./css/menu.css">
	<link rel = "stylesheet" href = "./css/form.css">
	<link rel = "stylesheet" href = "./css/table.css">
	<title>Home</title>
</head>

<body>


<%

if (request.getSession().getAttribute("nome") == null ) {
	response.sendRedirect("loginuser.jsp");
}
else {
	if (request.getAttribute("libri") == null){
		
		String redirect = "ListaLibri";
		
		if (request.getAttribute("currentPage") != null) {
			redirect += "?page="+request.getAttribute("currentPage");
		}
		out.print("redirect");
		response.sendRedirect(redirect);
	}
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
			<div>
				<h2 class="titleSection">ELENCO LIBRI</h2>
				<table class="tabella">
				    <tr>
				        <th>ID</th>
				        <th>Titolo</th>
				        <th>Autore</th>
				        <th>Anno di Pubblicazione</th>
				        <th>Quantità</th>
				        <th colspan="2"></th>
				    </tr>
				    <c:forEach var="book" items="${libri}">
				        <tr>
				            <td>${book.id}</td>
				           	<td>${book.titolo}</td>
					        <td>${book.autore}</td>
					        <td>${book.anno_pubblicazione}</td>
					        <td>${book.quantità}</td>
				
				            <td>  <form method="POST" action="editBook"><input type="hidden" id="bookId" name="bookId" value="${book.id}" /> <button class="tableButton" type="submit">Modifica</button></form></td>
				            <td>  <form method="POST" action="deleteBook" onsubmit="return confirm('Vuoi eliminare questo elemento?');"><input type="hidden" id="bookId" name="bookId" value="${book.id}" /> <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}" /><button class="tableButton" type="submit">Elimina</button></form></td>
				        </tr>
				    </c:forEach>
				</table>
			</div>
			
			<div class="paginazione">
				<c:forEach var="i" begin="1" end = "${nPagine}">
					<span> | </span>
					<span> <a href="./ListaLibri?page=${i}">${i}</a></span>
				</c:forEach>
				<span> | </span>
			</div>
		</div> <!-- fine leftcolumn -->
		
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
		
			<div class="card newBook">
				<h3>Inserimento nuovo libro</h3>
				<div class = "container">
					<div class = "container-input">
						<form action = "addBook" method = "POST">
							<label for = "titolo" class = "form-label">Titolo*</label>
							<input type = "text" class = "form-control" id = "titolo" placeholder = "Titolo" name = "titolo" required>
							
							<label for = "autore" class = "form-label">Autore*</label>
							<input type = "text" class = "form-control" id = "autore" placeholder = "Autore" name = "autore" required pattern="[a-zA-Z ]+">
		
							<label for = "annoPubblicazione" class = "form-label">Anno di pubblicazione*</label>
							<input type="number" min="1900" max="<% LocalDate date =LocalDate.now(); out.print(date.getYear());%>" step="1" class = "form-control" id = "annoPubblicazione" placeholder = "Anno di pubblicazione" name = "annoPubblicazione" required>
							
							<label for = "quantita" class = "form-label">Quantità</label>
							<input type = "number" class = "form-control" id = "quantita" placeholder = "Quantità" name = "quantita">
								
							<button type = "submit">Invia</button>
							<input type="reset" class="form-control elimina" id="reset" value="reset">
						</form>
					</div>
				</div>
				<h4 class="messageInsertBook"><% if (request.getAttribute("message") != null) {String s = (String)request.getAttribute("message"); out.print(s);}%> </h4>
			</div> <!-- fine card -->
			
		</div> <!--  fine rightcolumn -->

	</div> <!-- fine row -->
	
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