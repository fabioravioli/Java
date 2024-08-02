<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel = "stylesheet" href = "./css/menu.css">
	<link rel = "stylesheet" href = "./css/form.css">
	<title>Logout</title>
</head>

<body>
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
			<h2>Arrivederci</h2>
		</div>
	</div>		


<%
	String error = request.getParameter("error");
	if ("true".equals(error)) {
%>	
	<p style="color:red;"> Username o password errati </p>
	<p style="color:black;"> Se non sei ancora registrato clicca <a href="./register.html">qui</a></p>
	
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