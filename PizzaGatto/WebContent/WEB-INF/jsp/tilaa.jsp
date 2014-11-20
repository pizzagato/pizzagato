<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.TilausServlet"%>
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

<div id="wrapper">
	<div id="tekstiboxi">
	<h1>Tilauslomake</h1>
	<form action="Tilaa" method="post">
	<fieldset>
		<h2>Pizzat</h2>
		
		<div id="tuotenimi"><label class="numerointi">1.</label>
		
				<select name="pizza">
  					<option value="" disabled selected>Valitse tuote</option>
  					<c:forEach items="${pitsut}" var="p">
  					<option><c:out value="${p.nimi}"></c:out></option>
  					</c:forEach>
  					
				</select>
				
				</div>
			<div id="tuotekpl"><label>Kpl</label>
					<input type=number min="1" max="99" name="tuotekpl" required></input></div>
			<div id="hinta">Lis�ysnappi</div>
			
			<br style="clear: left;" />
			
	</fieldset>
	<fieldset>
	
		<h2>Juomat</h2>
		<div id="tuotenimi"><label class="numerointi">1.</label>
				<select name="juoma">
  					<option value="" selected>Valitse tuote</option>
  					<c:forEach items="${juomat}" var="j">
  					<option><c:out value="${j.nimi}"></c:out></option>
  					</c:forEach>
				</select></div>
			<div id="tuotekpl2"><label>Kpl</label>
					<input type=number min="0" max="99" name="tuotekpl2"></input></div>
			<div id="hinta">Lis�ysnappi</div>
			<br style="clear: left;" />
	</fieldset>
	<fieldset>
	<h2>Tilaajan tiedot</h2>
		<label class="tilaustiedot">Nimi</label>
			<input type="text" name="nimi" maxlength="40" required><br>
		<label class="tilaustiedot">Puhelin</label>
			<input type="text" name="puhnro" maxlength="10"><br>
		<label class="tilaustiedot">S�hk�posti</label>
			<input type="email" name="email" required><br>
		<label class="tilaustiedot">Osoite</label>
			<input type="text" class="osoite" name="osoite" maxlength="30" required>
		<label class="tilaustiedot">Postinumero</label>
			<input type="text" pattern="[0-9]*" class="pnro" name="pnro" maxlength="5" required>
 	</fieldset>
 	<fieldset>
 	<label>Lis�toiveet</label>
 	<textarea class=tilaustiedot name=lisatoiveet></textarea>
 	
 	 <input type="submit" class="tilausnappi" value="Tilaa">
 	</fieldset>
	</form>
	</div>
</div>

<div style="clear: both"></div>

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