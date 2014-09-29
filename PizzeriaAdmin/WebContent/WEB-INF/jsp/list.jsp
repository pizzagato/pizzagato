<%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.admin.bean.Pizza"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/styles.css" rel="stylesheet" type="text/css">
		<title>Pizzojen hallintasivu</title>
	</head>
	<body>
		<p> <c:out value="${aAika}"/> 
			<c:if test="${not empty param.added}">Uuden pizzan lisääminen onnistui</c:if> 
		</p>
		
		<p>Pizzojen hallintasivu</p>
		
		<form action="ControllerServlet" method="post">
			<table>
				<tr>
					<td><label>Nimi<br></label>
					<input type="text" name="nimi" size="40"></td>
					
					<td><label>Hinta<br></label>
					<input type="text" name="hinta" size="5"></td>
					
					<td><button type="submit" class="submit">Lisää</button></td>
				</tr>
			</table>
			
		</form>
		<br>
		
		<table>
		<c:forEach items="${pizzat}" var="p">
		<tr>
		<td class="pizzat"><c:out value="${p.nimi}"/></td>
		<td class="pizzat"><c:out value="${p.hinta}"/></td>
		</tr>
		</c:forEach>
		</table>
		<form action="ControllerServlet" method="post">
			<table>
				<tr>
					<td><label>Poista nimellä:<br></label>
							<input type="text" name="poista" size="40"></td>
							<td><button type="submit" class="submit">Poista</button></td>
				</tr>
			</table>
		</form>
		<br>
		<a href="img/Rautalankamalli2.png">Rautalankamalli</a>
		

		
	</body>
</html>