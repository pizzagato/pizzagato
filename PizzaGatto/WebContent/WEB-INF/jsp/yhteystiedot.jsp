<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
					<li><a href="//proto114.haaga-helia.fi:8080/PizzaGatto/Tilaa">TILAA</a> </li>	
					<li class="active"><a href="//proto114.haaga-helia.fi:8080/PizzaGatto/Yhteystiedot">YHTEYSTIEDOT</a></li>
				</ul>
			<div id="logo"><img src="img/pizzalogo_mini.png" alt="Logo"></div>
			</div>
		</div>
		
		<div id="wrapper">
			<div id="tekstiboxi">
				<div id="texti">
					<h1>YHTEYSTIEDOT</h1>
					<p> Pizzeria sijaitsee hyvien kulkuyhteyksien 
						varrella Kallion sydämessä.Tervetuloa 
						nauttimaan hyvästä ruuasta Pizza Gattoon!</p>
				
					<p>	Osoite:
					<br>Fleminginkatu 9
					<br>00530 HKI</p>
				
					<p>Pöytävaraukset numerosta:
					 <br>puh. 090 530 0124</p>
					
					
					<h1>AUKIOLOAJAT</h1>
					<table class="aukiolo">
						<tr><td>Ma-Pe</td><td>09-23</td></tr>
						<tr><td>La</td><td>10-23</td></tr>
						<tr><td>Su</td><td>Suljettu</td><tr>
					</table>
				</div>
				<div class="divider2"></div>
				<iframe class="map" src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d1983.7182054413427!2d24.9531194!3d60.1853964!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4692097fed590265%3A0x68a4bdf474e67501!2sFleminginkatu+9%2C+00530+Helsinki!5e0!3m2!1sfi!2sfi!4v1415367297686" frameborder="0" style="border:0"></iframe>
			</div>
		</div>
		
		<div style="clear: both"></div>
		<footer>
			<div id="vasenfooter">
				<img src="img/tekstilogo1.png" alt="tekstilogo"> 
			</div>
			<div id="oikeafooter"></div>
		</footer>
		
	</body>
</html>