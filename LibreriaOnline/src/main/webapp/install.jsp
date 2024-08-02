<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/install.css">
<title>Installazione Libreria Online</title>
</head>
<body>
	<div class="container">
	

	<%
	if (request.getParameter("installOk") != null && request.getParameter("installOk").equals("true")) {
	%>
	<p>
		INSTALLAZIONE TERMINATA CON SUCCESSO. <br />Ricorda di ELIMINARE il
		file <b>istall.jsp</b>
	</p>
	<%
	} else if (request.getParameter("installNo") != null && request.getParameter("installOk").equals("true")) {
	%>
	<p>Non è possibile installare l'applicazione</p>

	<%
	} else {
	%>
	<p class="titleOp">Esegui le seguenti operazioni:</p>
	<ul>
		<li>Apri il file "java/InstallaLibreria.java" e modifica i campi
			<b>DB_URL</b>, <b>USER</b> e <b>PASS</b>
		</li>
		<li><a href="./install.do">Clicca qui</a> per INSTALLARE</li>
	</ul>
	<%
}
%>
	</div>
</body>
</html>