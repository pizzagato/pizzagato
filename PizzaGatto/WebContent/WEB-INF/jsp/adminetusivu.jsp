<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ylläpitojärjestelmä</title>
	</head>
<body>
	<div id="login">
		<form action="Admin" method="post">
			<h1>Sisäänkirjautuminen</h1>
			<table>
				<tr>
					<td>Käyttäjätunnus</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Salasana</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><button type="submit">Kirjaudu sisään</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>