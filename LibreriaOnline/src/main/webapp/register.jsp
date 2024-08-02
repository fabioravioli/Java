<%@  page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel = "stylesheet" href = "./css/menu.css">
	<link rel = "stylesheet" href = "./css/form.css">
	<title>Home</title>
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
			<%
				if (request.getParameter("userOk") != null && request.getParameter("userOk").equals("true")){
			%>
				<h2>Utente registrato con successo!</h2>
			<%
				}
				else {
			
			%>
			<h2 class="titleSection">Registrazione Utente</h2>
			
			<div class = "container_reg">
				<div class = "container-input">
					<form action = "createUser.do" method = "POST">
					
						<label for = "nome" class = "form-label">Nome*</label>
						<input type = "text" class = "form-control" id = "nome" placeholder = "Inserisci il tuo Nome" name = "nome" value="<%  if (! (null == request.getParameter("nome"))) { out.print(request.getParameter("nome")); } %>" required pattern="[a-zA-Z]+">
						
						<label for = "cognome" class = "form-label">Cognome*</label>
						<input type = "text" class = "form-control" id = "cognome" placeholder = "Inserisci il tuo Cognome" name = "cognome" value="<%  if (! (null == request.getParameter("cognome"))) { out.print(request.getParameter("cognome")); } %>" required pattern="[a-zA-Z]+">
						
						<label for = "username" class = "form-label">Username*</label>
						<input type = "text" class = "form-control" id = "username" placeholder = "Inserisci il tuo Username" name = "username" value="<%  if (! (null == request.getParameter("username"))) { out.print(request.getParameter("username")); } %>" required pattern="[a-zA-Z0-9]+">
						
						<%
							String error = request.getParameter("userExists");
							if ("true".equals(error)) {
						%>	
						<div id="usernameError" class="message error"> Il nome utente esiste già! </div>
						<% 
							}
						%>	
						
						<label for = "email" class = "form-label">Email*</label>
						<input type = "email" class = "form-control" id = "email" placeholder = "Inserisci la tua Email" name = "email" value="<%  if (! (null == request.getParameter("email"))) { out.print(request.getParameter("email")); } %>" required pattern="^([a-zA-Z0-9._%-]+)(@[a-zA-Z0-9.-_]+)(.{1}[a-zA-Z]{2,3})$">
						
						<label for = "confEmail" class = "form-label">Ripeti Email*</label>
						<input type = "email" class = "form-control" id = "confEmail" placeholder = "Conferma la tua Email" name = "confEmail" value="<%  if (! (null == request.getParameter("confEmail"))) { out.print(request.getParameter("confEmail")); } %>" required pattern="^([a-zA-Z0-9._%-]+)(@[a-zA-Z0-9.-_]+)(.{1}[a-zA-Z]{2,3})$">
						
						<div id="messageEmail" class="message"> </div>
						
						<label for = "password" class = "form-label">Password*</label>
						<input type = "password" class = "form-control" id = "password" placeholder = "Inserisci la tua Password" name = "password" value="<%  if (! (null == request.getParameter("password"))) { out.print(request.getParameter("password")); } %>" required maxlength="12" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z] (?=.*[!$%])){8,12}"
												title="Deve contenere 1 lettera maiuscola, 1 minuscola 1 numero 1 carattere speciale tra !$%">
						
						
						<label for = "password" class = "form-label">Ripeti Password*</label>
						<input type = "password" class = "form-control" id = "confpassword" placeholder = "Inserisci nuovamente la tua Password" name = "confpassword" value="<%  if (! (null == request.getParameter("confpassword"))) { out.print(request.getParameter("confpassword")); } %>" required maxlength="12" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z] (?=.*[!$%])){8,12}"
												title="Deve contenere 1 lettera maiuscola, 1 minuscola 1 numero 1 carattere speciale tra !$%">
						
						<div id="messagePassword" class="message"> </div>										
						
						<button type = "submit">Invia</button>
						<input type="reset" class="form-control" id="reset" value="reset">
					</form>
				</div>
			</div>
			<% } %>
		</div>
	</div>
	<footer>
		<h4>Contatti</h4>
		<p>Fabio Ravioli</p>
		<p>fabio.ravioli@gmail.com</p>
	</footer>
	<script>
		
		document.getElementById('confpassword').addEventListener('input', validatePassword);
		
		//creo una funzione per controllare se i due campi di password hanno gli stessi valori
		function validatePassword() {
			const password = document.getElementById('password').value;
			const confpassword = document.getElementById('confpassword').value;
			
			// message serve per riportare il messaggio all'interno dell'elemento span
			const message = document.getElementById('messagePassword');
			
			//se entrambe le password sono diverse, message.textContent aggiungerà il messaggio di verifica delle password
			if(password != confpassword) {
				message.classList.add("error");
				message.textContent = 'Le password non sono uguali';
				
			}
			else {
				message.classList.add("ok");
				message.classList.remove("error");
				message.textContent = "Le password coincidono";
			}
		}
		
		document.getElementById('confEmail').addEventListener('input', validateEmail);
		
		function validateEmail() {
			const email = document.getElementById('email').value;
			const confemail = document.getElementById('confEmail').value;
			
			const messageEmail = document.getElementById('messageEmail');
			
			if(email != confemail) {
				messageEmail.classList.add("error");
				messageEmail.textContent = 'Le email non sono uguali';
			}
			else {
				messageEmail.classList.add("ok");
				messageEmail.classList.remove("error");
				messageEmail.textContent = "Le email coincidono";
			}
		}
	</script>
	
</body>
</html>