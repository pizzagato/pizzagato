<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Pizza"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Juoma"%>
<%@page import="fi.omapizzeria.pizzagatto.bean.Tayte"%>
<%@page import="fi.omapizzeria.pizzagatto.servlet.MenunMuokkausServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style2.css"> 
	<title>Pizza Gatto - hallintapaneeli</title>
</head>
<body>
<div id="push"></div>
<div id="container">
	<div id="navi"><img src="img/tekstilogo2.png">
		
	<form name="submitForm" method="POST" action="AdminNavigationServlet">
	
   		<input class="active" type="hidden" name="target" value="toSettings">
   		<A HREF="javascript:document.submitForm.submit()">Asetukset</A>
   		</form>

	</div>
<div id="wrapper">
	<div id="tekstiboxi">
	<form action="MenunMuokkaus" method="post">
		<fieldset>
		<h2>Lisää pizza</h2>
		
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="pizzanimi" required>
		</div>
		<div class="tuotehinta">
			<label>Hinta</label><br>
				<input type="text" name="pizzahinta" required>
		</div>
		<br style="clear: left;" />
		<div class="taytevalinta">
			<label>Täytteet</label><br>
			<select name="pizzatayte1" required>
  					<option selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out>
  					</option>
  					</c:forEach>
			</select>
			<select name="pizzatayte2" required>
  					<option selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
			<select name="pizzatayte3">
  					<option selected>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
			<select name="pizzatayte4">
  					<option selected>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
			<select name="pizzatayte5">
  					<option selected>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
		</div>
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi" value="Lisää">		
		</fieldset>
	</form>
	<form action="MenunMuokkaus" method="post">
		<fieldset>
	
		<h2>Lisää juoma</h2>
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="juomanimi" required>
		</div>
		<div class="tuotehinta">
			<label>Hinta</label><br>
				<input type="text" name="juomahinta" required>
		</div>
	
		<div class="tuotekoko">
			<label>Koko</label><br>
			<select name="juomakoko" required>
  			
  					<option selected></option>
  					<option value="0.33 l">0.33 l</option>
  					<option value="16 cl">16 cl</option>
  					<option value="0.75 l">0.75 l</option>
  							
  					
			</select>
		</div>
		<div class="tuotetyyppi">
			<label>Tyyppi</label><br>
				<select name="juomatyyppi" required>
				<option selected></option>
				<option value="virvoitusjuoma">virvoitusjuoma</option>
				<option value="mieto alkoholijuoma">mieto alkoholijuoma</option>
				<option value="valkoviini">valkoviini</option>
				<option value="punaviini">punaviini</option>
				<option value="kuohuviini">kuohuviini</option>
				
				
				</select>
		</div>
			<br style="clear: left;" />
		<input type="submit" class="tilausnappi" value="Lisää">	
		</fieldset>
	</form>
	<form action="MenunMuokkaus" method="post">
		<fieldset>
		<h2>Lisää täyte</h2>
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="tayte" required>
		</div>

		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Lisää">
 		</fieldset>
	</form>
	<form action="MenunMuokkaus" method="post">
		<fieldset>
		<h2>Poista pizza, juoma tai täyte</h2>
		<div class="tuotenimi">
			<label>Pizza</label><br>
			<select name="poistapizza">
  					<option value="1" selected>--</option>
  					<c:forEach items="${pitsut}" var="p">
  					<option><c:out value="${p.nimi}"></c:out></option>
  					</c:forEach>
			</select><br>
			<label>Juoma</label><br>
			<select name="poistajuoma">
  					<option value="1" selected>--</option>
  					<c:forEach items="${juomat}" var="j">
  					<option><c:out value="${j.nimi}"></c:out></option>
  					</c:forEach>
			</select><br>
			<label>Täyte</label><br>
			<select name="poistatayte">
  					<option value="1" selected>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select></div>
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Poista">
		</fieldset>
	</form>	
	<form action="MenunMuokkaus" method="post">
		<fieldset>
		<h2>Muuta pizzan statusta</h2>
		<div class="tuotenimi">
			<label>Pizza</label><br>
			<select name="pizzanstatus">
  					<option value="1" selected>--</option>
  					<c:forEach items="${pitsut}" var="p">
  					<option><c:out value="${p.nimi}"></c:out></option>
  					</c:forEach>
			</select>
		</div>
		<div id="tuotehinta"><label>Status</label></div>
			<input type="radio" name="status" value="1" required>Käytössä<br>
			<input type="radio" name="status" value="0">Ei käytössä
		
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Muuta">
		</fieldset>
	</form>
	
		
<div style="clear: both"></div>
</div>
</div>
</div>

</body>


</html>