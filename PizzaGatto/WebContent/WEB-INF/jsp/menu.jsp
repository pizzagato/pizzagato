<%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.EtusivuServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel='stylesheet' media='screen and (min-width: 650px) and (max-width: 1024px)' href='css/tabletstyle.css' />
	<link rel='stylesheet' media='screen and (min-width: 300px) and (max-width: 650px)' href='css/mobilestyle.css' />
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
<div id="polaroid"><img src="img/pizza123456.png" alt="pizzakuva"></div>
<div id="menuotsikko"><img src="img/menu.png" alt="Menu"></div>
<div class="divider"></div>
		
		
		
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
				<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${p.hinta}" /> €
			</span>
		</li>
		<li class="taytelista">
		<c:forEach items="${p.taytteet}" var="listNumber" varStatus="listStatus">
		<c:out value="${listNumber}"/>
		
	
		</c:forEach>
		</li>
		</c:forEach>
		
		</ul>
		
		<div class="divider"></div>
		
		
				<ul class="menulista">
		<c:set var="counter" value="0"/>
		<c:forEach items="${juomat}" var="j"> <!--doGet-metodi-->
		<c:set var="counter" value="${counter + 1}"/>
		<li class="nimihinta">
			<span>
				<div class="juomanimi"><c:out value="${counter}."/>
				<c:out value="${j.nimi}"/></div>
			</span>
			<span>
				<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${j.hinta}" /> €
			</span>
			
			<div class="juomakoko"><c:out value="${j.koko}"/></div>
			<br><br>
			<li class="taytelista"></li>

		</c:forEach>
		</ul>
		
		
		
		
		<div class="divider"></div>
		<div id="filleri"></div>
		</div>
		<div id="polaroid2"><img src="img/polaroid2.png" alt="pizzakuva2"></div>		 

<div style="clear: both"></div>
</div>
<footer>
	<div id="vasenfooter">
	<img src="img/tekstilogo1.png" alt="tekstilogo"> 
	</div>

	<div id="oikeafooter">
	
	</div>
</footer>
</body>


</html>