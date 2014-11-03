<%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.admin.bean.Pizza"%>
<%@page import="fi.omapizzeria.admin.controller.EtusivuServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<!DOCTYPE html>
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
		<%--<td class="pitsut"><c:out value="${p.taytenimi}"/></td>  
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
		
	</body>
</html>
 --%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
	<title>Pizza Gatto</title>
</head>
<body>
<div id="container">
	<div id="navi">
		<ul>
			<li><a href="//localhost:8080/PizzaGatto/Etusivu">ETUSIVU</a> </li>
			<li class="active"><a href="//localhost:8080/PizzaGatto/Menu">MENU</a> </li>
			<li><a href="//localhost:8080/PizzaGatto/Tilaa">TILAA</a> </li>	
			<li><a href="//localhost:8080/PizzaGatto/Yhteystiedot">YHTEYSTIEDOT</a></li>
			
		</ul>
	<div id="logo"><img src="img/pizzalogo_mini.png" alt="Logo"></div>
	</div>

</div>
<div id="wrapper">
	<div id="tekstiboxi">
<div id="menuotsikko">Menu</div>
<div class="divider"></div>
		
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
		
		
		<ul class="menulista">
		<c:set var="counter" value="0"/>
		<c:forEach items="${pitsut}" var="p"> <!--doGet-metodi-->
		<c:set var="counter" value="${counter + 1}"/>
		<li class="nimihinta">
			<span>
				<c:out value="${counter}."/>
				<c:out value="${p.nimi}"/>
			</span>
			<span>
				<c:out value="${p.hinta}"/>
			</span>
		</li>
		<li class="taytelista">
		<c:forEach items="${p.taytenimi}" var="listNumber" varStatus="listStatus">
		<c:out value="${listNumber}" />
		
		
		<%--<td class="pitsut"><c:out value="${p.taytenimi}"/></td>  --%>
	
		</c:forEach>
		</c:forEach>
		</li>
		</ul>
		
		
		
		
				<ul class="menulista">
		<c:set var="counter" value="0"/>
		<c:forEach items="${juomat}" var="j"> <!--doGet-metodi-->
		<c:set var="counter" value="${counter + 1}"/>
		<li class="nimihinta">
			<span>
				<c:out value="${counter}."/>
				<c:out value="${j.nimi}"/>
			</span>
			<span>
				<c:out value="${j.hinta}"/>
			</span>
			<span>
				<c:out value="${j.koko}"/>
			</span>
		</li>

		</c:forEach>
		</ul>
		
		
		
		
		<div class="divider"></div>
		
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
		 
</div>
<div style="clear: both"></div>
</div>
<footer>
	<div id="vasenfooter">
	<img src="img/tekstilogo1.png" alt="tekstilogo"> 
	</div>

	<div id="oikeafooter">
	
	</div>
</footer>
</div>
</body>


</html>