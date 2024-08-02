<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel = "stylesheet" href = "./css/menu.css">
	<link rel = "stylesheet" href = "./css/form.css">
	<link rel = "stylesheet" href = "./css/table.css">
	<title>Modifica Utente</title>
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
	
	<%

		if (( (request.getSession().getAttribute("ruolo").equals("user")) && (request.getParameter("userId") == null || (Integer)request.getSession().getAttribute("id") != Integer.parseInt(request.getParameter("userId")) ) ) )  {

			%>
			<h2>Non hai i permessi per visualizzare questa pagina!</h2>
			<%
		}
		else {
			
			if (request.getParameter("userId") == null) {
				response.sendRedirect("userList");
			}
	
	%>
	<div class="row ">
		<div class="leftcolumn register">
			<h2 class="titleSection">Modifica Utente</h2>
				<div class = "container_reg">
					<div class = "container-input">
						<form action = "./updateUser.do" method = "POST">
							<input type="hidden" id="id" name="id" value="<%= request.getAttribute("id")%>" />
							
							<label for = "nome" class = "form-label">Nome*</label>
							<input type = "text" class = "form-control" id = "nome" placeholder = "Inserisci il tuo Nome" name = "nome" value="<%= request.getAttribute("nome")%>" required pattern="[a-zA-Z]+" >
							
							<label for = "cognome" class = "form-label">Cognome*</label>
							<input type = "text" class = "form-control" id = "cognome" placeholder = "Inserisci il tuo Cognome" name = "cognome" value="<%= request.getAttribute("cognome")%>" required pattern="[a-zA-Z]+">
							
							<label for = "username" class = "form-label">Username*</label>
							<input type = "text" class = "form-control" id = "username" placeholder = "Inserisci il tuo Username" name = "username" value="<%= request.getAttribute("username")%>" required pattern="[a-zA-Z0-9]+">
							
							<label for = "email" class = "form-label">Email*</label>
							<input type = "email" class = "form-control" id = "email" placeholder = "Inserisci la tua Email" name = "email" value="<%= request.getAttribute("email")%>" required pattern="^([a-zA-Z0-9._%-]+)(@[a-zA-Z0-9.-_]+)(.{1}[a-zA-Z]{2,3})$">
							
							<label for = "password" class = "form-label">Password*</label>
							<input type = "password" class = "form-control" id = "password" placeholder = "Inserisci la tua Password" name = "password" value="<%= request.getAttribute("password")%>" required maxlength="12" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z] (?=.*[!$%])){8,12}"
													title="Deve contenere 1 lettera maiuscola, 1 minuscola 1 numero 1 carattere speciale tra !$%">
							
						
							<label for = "ruolo" class = "form-label">Ruolo*</label>
							<select id="ruolo" name="ruolo" class="form-control">
								<option value="user" <% if (request.getAttribute("ruolo") != null && request.getAttribute("ruolo").equals("user")) out.print("selected='selected'");%> > user</option>
								<option value="amministratore" <% if (request.getAttribute("ruolo")!= null && request.getAttribute("ruolo").equals("amministratore")) out.print("selected='selected'");%> >amministratore</option>		
							</select>	
						
							<button type = "submit">Invia</button>
							<input type="reset" class="form-control" id="reset" value="reset">
							
						</form>
					</div>
				</div>
		</div>
	</div>
<%
}
}
%>

	<footer>
		<h4>Contatti</h4>
		<p>Fabio Ravioli</p>
		<p>fabio.ravioli@gmail.com</p>
	</footer>
</body>
</html>