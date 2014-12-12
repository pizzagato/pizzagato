<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.VahvistusServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel='stylesheet' media='screen and (min-width: 650px) and (max-width: 960px)' href='css/tabletstyle.css' />
		<link rel='stylesheet' media='screen and (min-width: 300px) and (max-width: 650px)' href='css/mobilestyle.css' />
		<title>Pizza Gatto</title>
	</head>
	<body>
		<div id="container">  
			<div id="navi">
				<ul>
					<li><a href="//proto114.haaga-helia.fi:8080/PizzaGatto/Etusivu">ETUSIVU</a> </li>
					<li><a href="//proto114.haaga-helia.fi:8080/PizzaGatto/Menu">MENU</a> </li>
					<li class="active"><a href="//proto114.haaga-helia.fi:8080/PizzaGatto/Tilaa">TILAA</a></li>  
					<li><a href="//proto114.haaga-helia.fi:8080/PizzaGatto/Yhteystiedot">YHTEYSTIEDOT</a></li>      
				</ul>
				<div id="logo"><img src="img/pizzalogo_mini.png" alt="Logo"></div>
			</div>
		</div>
	    <div id="wrapper">
		    <div id="tekstiboxi">
			    <h2>VAHVISTUS</h2>
			    <div class=fontti>
				    <h2>Tilauksesi</h2>
				    
				    <table class="vahvistus">
						<c:forEach items="${tilRivit}" var="tilRivit"> <!--doGet-metodi-->             
		                    <c:set var="counter" value="${counter + 1}"/>                   	
                           	<tr>
								<td><c:out value="${counter}"/>.</td>                                
								<td><c:out value="${tilRivit.tuote.nimi}"/></td>
								<td><c:out value="${tilRivit.kpl}"/> kpl</td>                     <%-- Tässä on kappalemäärä tuotteelle. --%>
								<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${tilRivit.kokonaishinta}" /> €<td>     <%-- Tässä on kokonaishinta tuotteelle. --%>
                           	</tr>     
						</c:forEach>
			            <tr class="yht">
				            <td></td>
				            <td></td>
				            <td>Yht.:</td>
				            <td> <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${kokHi}" /> €</td>
			            </tr>
					</table>

				    <h2>Yhteystiedot</h2>
				    <c:out value="${asTied.nimi }"></c:out><br>
				    <c:out value="${asTied.puhelin }"></c:out><br>
				    <c:out value="${asTied.spost }"></c:out><br>
				    <c:out value="${asTied.osoite }"></c:out><br>
				    <c:out value="${asTied.pstnro } Helsinki"></c:out>
				    <br><br> 
				    Erityistoiveet:
				    <div class="etoive">
				    	<c:out value="${asTied.lisatoiveet }"></c:out>
				    </div>
				    <h2>Tilauksen hinta <td> <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${kokHi}" /> €</td></h2>
				    
				    <form action="Vahvistus" method="post" >
				 	 	<input type="submit" class="tilausnappi" name="action" value="Vahvista" onclick="return foo();">
				 	 	<script type="text/javascript">
				    		function foo() {
				        		alert("Tilauksesi on vastaanotettu!");
				        		return true;
				    		}
						</script>
				 		<input type="submit" class="tilausnappi" name="action" value="Takaisin">
					</form>
			    </div>
		    </div>
	    </div>
	    <footer>
            <div id="vasenfooter">
            	<img src="img/tekstilogo1.png" alt="tekstilogo">
            </div>
            <div id="oikeafooter"></div>
	    </footer>
    </body>
</html>