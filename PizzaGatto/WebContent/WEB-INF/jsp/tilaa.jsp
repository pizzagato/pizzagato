<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-15"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.TilausServlet"%>
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
						<div id="tuotenimi">
							<select name="pizza">
			  					<option value="" disabled selected>Valitse tuote</option>
			  					<c:forEach items="${pitsut}" var="p">
			  						<option><c:out value="${p.nimi}"></c:out></option><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${p.hinta}" />
			  					</c:forEach>
							</select>
						</div>
						<div id="tuotekpl"><label>Kpl</label>
							<input type=number min="1" max="99" name="tuotekpl" required></input>
						</div>
						<div id="hinta"></div>
						<br style="clear: left;" />
						<input type="submit" class="lisaysnapu" name="action" value="Lisää Ostoskoriin" onclick="form.action='Tilaa';">
					</fieldset>
				</form>
				
				<form action="Tilaa" method="post">
					<fieldset>
						<h2>Juomat</h2>
						<div id="tuotenimi">
							<select name="juoma">
			  					<option value="" selected>Valitse tuote</option>
			  					<c:forEach items="${juomat}" var="j">
			  						<option><c:out value="${j.nimi}"></c:out></option><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${j.hinta}" />
			  					</c:forEach>
							</select>
						</div>
						<div id="tuotekpl"><label>Kpl</label>
							<input type=number min="0" max="99" name="tuotekpl2"></input>
						</div>
						<br style="clear: left;" />
						<div id="hinta"><input type="submit" class="lisaysnapu" name="action" value="Lisää Ostoskoriin" onclick="form.action='Tilaa';"></div>
					</fieldset>
				</form>
				
				<div class="ostoskori">
					<h2><c:out value="Ostoskori" /></h2>
					<ul>
						<c:forEach items="${tilRivit}" var="tilRivit"> <!--doGet-metodi-->
							<c:set var="counter" value="${counter + 1}"/>
							<li class="kikkihiiri">
								<span>
									<c:out value="${counter}"/>.
									<c:out value="${tilRivit}"/> &#8364;
								</span>
								<span>
									<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${p.hinta}" />	
								</span>
							</li>
						</c:forEach>
					</ul>
					<c:out value="Tilauksen hinta: "/>
					<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${kokHi}" /> euroa.
				</div>
				
				<form action="Tilaa" method="post">
					<fieldset>
						<h2>Tilaajan tiedot</h2>
						<label class="tilaustiedot">Nimi*</label>
						<input type="text" name="nimi" maxlength="40"><br>
						<label class="tilaustiedot">Puhelin</label>
						<input type="text" name="puhnro" maxlength="10"><br>
						<label class="tilaustiedot">Sähköposti*</label>
						<input type="email" name="email"><br>
						<label class="tilaustiedot">Osoite*</label>
						<input type="text" class="osoite" name="osoite" maxlength="30">
						<label class="tilaustiedot">Postinumero*</label>
						<input type="text" pattern="00100|00170|00250|00500|00510|00530|00540|00550|00560|00580" 
							class="pnro" name="pnro" maxlength="5" title="Toimitamme vain cooleihin paikkohin."/>
					 	<br>
					 	<label>Lisätoiveet</label>
					 	<textarea class=tilaustiedot name=lisatoiveet></textarea>
					 	<input type="submit" class="tilausnappi" value="Vahvistus">
					 	<input type="submit" name="action" class="tilausnappi" value="Tyhjennä">
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
		
	</body>
</html>