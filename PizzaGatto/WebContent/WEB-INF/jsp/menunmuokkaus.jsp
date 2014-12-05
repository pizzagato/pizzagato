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
		<h2>Lis‰‰ pizza</h2>
		
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="pizzanimi" required>
		</div>
		<div class="tuotehinta">
			<label>Hinta</label><br>
				<input type="text" class="hintasd" name="pizzahinta" required>
		</div>
		<br style="clear: left;" />
		<div class="taytevalinta">
			<label>T‰ytteet</label><br>
			<select name="pizzatayte1" >
  					<option value="1" selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out>
  					</option>
  					</c:forEach>
			</select>
			<select name="pizzatayte2" >
  					<option value="1" selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
			<select name="pizzatayte3">
  					<option value="1" selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
			<select name="pizzatayte4">
  					<option value="1" selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
			<select name="pizzatayte5">
  					<option value="1" selected disabled>--</option>
  					<c:forEach items="${taytteet}" var="t">
  					<option value="${t.id}"><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
			</select>
		</div>
		<br style="clear: left;" />
		<button type="submit" class="tilausnappi" name="Lis‰‰Pizza">Lis‰‰ pizza</button>		
		</fieldset>
	</form>
	<form action="MenunMuokkaus" method="post">
		<fieldset>
	
		<h2>Lis‰‰ juoma</h2>
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="juomanimi" required>
		</div>
		<div class="tuotehinta">
			<label>Hinta</label><br>
				<input type="text" class="hintasd" name="juomahinta" required>
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
		<button type="submit" class="tilausnappi" name="Lis‰‰Juoma">Lis‰‰ juoma</button>	
		</fieldset>
	</form>
	<form action="MenunMuokkaus" method="post">
		<fieldset>
		<h2>Lis‰‰ t‰yte</h2>
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="tayte" required>
		</div>

		<br style="clear: left;" />
		<button type="submit" class="tilausnappi2" name="Lis‰‰T‰yte">Lis‰‰ t‰yte</button>
 		</fieldset>
	</form>
			<form action="MenunMuokkaus" method="post">
		<fieldset>
		<h2>Poista tuote tai t‰yte</h2>
		<div class="tuotenimi">
			<select name="poistaminen" required>
  					<option value="1" selected disabled>Valitse</option>
  				<optgroup label="Pizzat">
  					<c:forEach items="${pitsut}" var="p">
  					<option><c:out value="${p.nimi}"></c:out></option>
  					</c:forEach>
				</optgroup>
				<optgroup label="Juomat">
  					<c:forEach items="${juomat}" var="j">
  					<option><c:out value="${j.nimi}"></c:out></option>
  					</c:forEach>
  				</optgroup>
  				<optgroup label="T‰ytteet">
  					<c:forEach items="${taytteet}" var="t">
  					<option><c:out value="${t.nimi}"></c:out></option>
  					</c:forEach>
  				</optgroup>
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
  					<option value="1" selected disabled>--</option>
  					<c:forEach items="${pitsut}" var="p">
  					<option value="${p.id}"><c:out value="${p.nimi}"></c:out></option>
  					</c:forEach>
			</select>
		</div>
		<div id="tuotehinta"><label>Status</label></div>
			<input type="radio" name="status" value="1" required>K‰ytˆss‰<br>
			<input type="radio" name="status" value="0">Ei k‰ytˆss‰
		
		<br style="clear: left;" />
		<button  type="submit" class="tilausnappi2" name="Muuta">Muuta status</button>
		</fieldset>
	</form>
	
		
<div style="clear: both"></div>
</div>
</div>
</div>

</body>


</html>