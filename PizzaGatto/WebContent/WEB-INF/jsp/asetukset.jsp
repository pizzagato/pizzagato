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
	
   		<input class="active" type="hidden" name="target" value="toMenu">
   		<A HREF="javascript:document.submitForm.submit()">Menun muokkaaminen</A>
   		</form>
	</div>

<div id="wrapper">
	<div id="tekstiboxi">
	<form>
		<fieldset>
		<h2>Kirjautumisasetukset</h2>
		<label class="asetuslabel">Sallittu yritysm‰‰r‰</label>
			<input type="number" name="kirjyritykset" min="1"><br/>
		<label class="asetuslabel">Bannin pituus (min)</label>
			<input type="number" name="banninpituus" min="1">
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Tallenna">	
		</fieldset>

		<fieldset>
		<h2>Poista banni</h2>
		<label class="asetuslabel">Valitse IP</label>
			<select>
				<option selected>3257846457</option>
				<option>35928609849034</option>
				<option>35928609849034</option>
			</select>
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Poista">	
		</fieldset>
	</form>
	<form>
		<fieldset>
		<h2>Yll‰pitotila</h2>
		<label class="asetuslabel">Aseta sivu yll‰pitotilaan</label>
		<input type="checkbox" name="yllapitotila" value="y">
			
		<br style="clear: left;" />
		<input type="submit" class="tilausnappi2" value="Tallenna">	
	</div>
</div>

<div style="clear: both"></div>

</div>

</body>


</html>