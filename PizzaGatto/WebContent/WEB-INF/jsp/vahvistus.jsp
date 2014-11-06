<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.VahvistusServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
	<title>Pizza Gatto</title>
</head>
<body>
<div id="container">	
	<div id="navi">
		<ul class>
			<li><a href="//localhost:8080/PizzaGatto/Etusivu">ETUSIVU</a> </li>
			<li><a href="//localhost:8080/PizzaGatto/Menu">MENU</a> </li>
			<li class="active"><a href="//localhost:8080/PizzaGatto/Tilaa">TILAA</a></li>	
			<li><a href="//localhost:8080/PizzaGatto/Yhteystiedot">YHTEYSTIEDOT</a></li>
			
		</ul>
	<div id="logo"><img src="img/pizzalogo_mini.png" alt="Logo"></div>
	</div>

</div>
homo t‰s ois n‰‰ pitsut<br>
<br>
t make<br>
<c:out value="${pitsu}"></c:out>
<br>
<c:out value="${juoma}"></c:out>
<br>
<c:out value="${asiakastiedot.nimi }"></c:out>
<c:out value="${asiakastiedot.puhelin }"></c:out>
<c:out value="${asiakastiedot.spost }"></c:out>
<c:out value="${asiakastiedot.osoite }"></c:out>
<c:out value="${asiakastiedot.pstnro }"></c:out>
<c:out value="${asiakastiedot.lisatoiveet }"></c:out>




<footer>
	<div id="vasenfooter">
	<img src="img/tekstilogo1.png" alt="tekstilogo"> 
	</div>

	<div id="oikeafooter">
	
	</div>

</footer>

</body>


</html>