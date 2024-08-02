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
</div>