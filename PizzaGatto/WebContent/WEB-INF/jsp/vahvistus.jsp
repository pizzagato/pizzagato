<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.VahvistusServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
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
<div id="wrapper">
<div id="tekstiboxi">
<h6>VAHVISTUS</h6>
<div class=fontti>


<h2>Tilauksesi</h2>
<br>
<c:forEach items="${tilRivit}" var="tilRivit"> <!--doGet-metodi-->
		<c:set var="counter" value="${counter + 1}"/>
		<li class="kikkihiiri">
			<span>
				<c:out value="${counter}"/>
				<c:out value="${tilRivit}"/>
				
				

			</span>
			<span>
				<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${p.hinta}" />
			</span>
		</li>
	</c:forEach>
	--------------------------------------------------- <br>
	<c:out value="Kokonaishinta: "/> <c:out value="${kokHi}"/>

	
<br><br><br>

<h2>Yhteystiedot</h2>


<c:out value="${asTied.nimi }"></c:out><br>
<c:out value="${asTied.puhelin }"></c:out><br>
<c:out value="${asTied.spost }"></c:out><br>
<c:out value="${asTied.osoite }"></c:out><br>
<c:out value="${asTied.pstnro }"></c:out><br><br>

Erityistoiveet:<br>
<fieldset>
<c:out value="${asTied.lisatoiveet }"></c:out>
 </fieldset>
 
 Nappi ja nappi
 
</div>
</div>
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