<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<form>
		<fieldset>
		<h2>Lis�� pizza</h2>
		
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="pizzanimi" required>
		</div>
		<div class="tuotehinta">
			<label>Hinta</label><br>
				<input type="number" name="currency" min="0" max="9999" step="0.1" required>
		</div>
		<br style="clear: left;" />
		<div class="taytevalinta">
			<label>T�ytteet</label><br>
			<select name="tayte" required>
  					<option value="1" selected disabled>--</option>
  					<option value="2">Joku t�yte</option>
			</select>
			<select name="tayte" required>
  					<option value="1" selected disabled>--</option>
  					<option value="2">Joku t�yte</option>
			</select>
			<select name="tayte">
  					<option value="1" selected>--</option>
  					<option value="2">Joku t�yte</option>
			</select>
			<select name="tayte">
  					<option value="1" selected>--</option>
  					<option value="2">Joku t�yte</option>
			</select>
			<select name="tayte">
  					<option value="1" selected>--</option>
  					<option value="2">Joku t�yte</option>
			</select>
		</div>
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi" value="Lis��">		
		</fieldset>
	</form>
	<form>
		<fieldset>
	
		<h2>Lis�� juoma</h2>
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="juomanimi" required>
		</div>
		<div class="tuotehinta">
			<label>Hinta</label><br>
				<input type="number" name="currency" min="0" max="9999" step="0.1" required>
		</div>
		<div class="tuotekoko">
			<label>Koko</label><br>
			<select name="koko" required>
  					<option value="1" selected>Joku koko</option>
  					<option value="2">Toinen koko</option>
			</select>
		</div>
		<div class="tuotetyyppi">
			<label>Tyyppi</label><br>
				<input type="radio" name="tyyppi" value="1" required>Alkoholiton<br>
				<input type="radio" name="tyyppi" value="2">Alkoholillinen
		</div>
			<br style="clear: left;" />
		<input type="submit" class="tilausnappi" value="Lis��">	
		</fieldset>
	</form>
	<form>
		<fieldset>
		<h2>Lis�� t�yte</h2>
		<div class="tuotenimi">
			<label>Nimi</label><br>
				<input type="text" name="tayte" 	required>
		</div>

		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Lis��">
 		</fieldset>
	</form>
	<form>
		<fieldset>
		<h2>Poista pizza, juoma tai t�yte</h2>
		<div class="tuotenimi">
			<label>Pizza</label><br>
			<select name="poistapizza">
  					<option value="1" selected>--</option>
  					<option value="2">Joku pizza</option>
			</select><br>
			<label>Juoma</label><br>
			<select name="poistajuoma">
  					<option value="1" selected>--</option>
  					<option value="2">Joku juoma</option>
			</select><br>
			<label>T�yte</label><br>
			<select name="poistatayte">
  					<option value="1" selected>--</option>
  					<option value="2">Joku t�yte</option>
			</select></div>
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Poista">
		</fieldset>
	</form>	
	<form>
		<fieldset>
		<h2>Muuta pizzan statusta</h2>
		<div class="tuotenimi">
			<label>Pizza</label><br>
			<select name="poistapizza">
  					<option value="1" selected>--</option>
  					<option value="2">Joku pizza</option>
			</select>
		</div>
		<div id="tuotehinta"><label>Status</label></div>
			<input type="radio" name="status" value="1" required>K�yt�ss�<br>
			<input type="radio" name="status" value="2">Ei k�yt�ss�
		
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Tallenna">
		</fieldset>
	</form>
	
		
<div style="clear: both"></div>

</div>

</body>


</html>