<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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


if (request.getSession().getAttribute("nome") == null || request.getSession().getAttribute("ruolo").equals("user")) {
	response.sendRedirect("loginuser.jsp");
}
else {
	if (request.getAttribute("utenti") == null){
		
		String redirect = "userList";
		
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
		
		<p class="welcomeMessage">Benvenuto <%=request.getSession().getAttribute("nome") %> <a href="editUser?userId=<%=request.getSession().getAttribute("id")%>">[Modifica Profilo]</a></p>
		
	</div> <!-- fine topnav -->
	
	
	<div class="row">
		<div class="leftcolumn">
			<div class="tableContainer">
			<h2 class="titleSection">ELENCO UTENTI</h2>
				<table class="tabella">
				    <tr>
				        <th>ID</th>
				        <th>Nome</th>
				        <th>Cognome</th>
				        <th>Username</th>
				        <th>Password</th>
				        <th>E-mail</th>
				        <th>Ruolo</th>
				        <th></th>
				        <th></th>
				    </tr>
				    <c:forEach var="user" items="${utenti}">
				        <tr>
				            <td>${user.id}</td>
				           	<td>${user.nome}</td>
					        <td>${user.cognome}</td>
					        <td>${user.username}</td>
					        <td>${user.password}</td>
					        <td>${user.email}</td>
					        <td>${user.ruolo}</td>
				
				            <td>  <form method="POST" action="editUser"><input type="hidden" id="userId" name="userId" value="${user.id}" /> <button type="submit" class="tableButton">Modifica</button></form></td>
				            <td>  <form method="POST" action="deleteUser.do" onsubmit="return confirm('Vuoi eliminare questo elemento?');"><input type="hidden" id="userId" name="userId" value="${user.id}" /> <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}" /><button class="tableButton" type="submit" >Elimina</button></form></td>
				        </tr>
				    </c:forEach>
				</table>
			</div>

			<div class="paginazione">
				<c:forEach var="i" begin="1" end="${nPagine}">
					<span> | </span>
					<span> <a href="userList?page=${i}">${i}</a></span>
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