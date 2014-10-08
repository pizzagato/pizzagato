<%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.admin.bean.Pizza"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/styles.css" rel="stylesheet" type="text/css">
		<title>Pizzalista</title>
	</head>
	<body>
	
		<p>Pizzalista:</p>
		
		<%----<form action="ControllerServlet" method="post">
			<table>
				<tr>
					<td><label>Nimi<br></label>
					<input type="text" name="nimi" size="40"></td>
					
					<td><label>Hinta<br></label>
					<input type="text" name="hinta" size="5"></td>
					
					<td><button type="submit" class="submit">Lis��</button></td>
				</tr>
			</table>
			
		</form>
		 --%>
		<br>
		
		<table>
		<c:set var="counter" value="0"/>
		<c:forEach items="${pitsut}" var="p"> <!--doGet-metodi-->
		<tr>
		<c:set var="counter" value="${counter + 1}"/>
		<td><c:out value="${counter}"/></td>
		<td class="pizzat"><c:out value="${p.nimi}"/></td>
		<td class="pizzat"><c:out value="${p.hinta}"/></td>
		<c:forEach items="${p.taytenimi}" var="listNumber" varStatus="listStatus">
		<td class="pizzat"> <c:out value="${listNumber}" /></td>
		<%--<td class="pitsut"><c:out value="${p.taytenimi}"/></td>  --%>
		</c:forEach>
		</tr>
		</c:forEach>
		</table>
		<%--<form action="ControllerServlet" method="post">
			<table>
				<tr>
					<td><label>Poista nimell�:<br></label>
							<input type="text" name="poista" size="40"></td>
							<td><button type="submit" class="submit">Poista</button></td>
				</tr>
			</table>
		</form>
		<br>
		<a href="img/Rautalankamalli2.png">Rautalankamalli</a>
		 --%>
	</body>
</html>