# Tehtävä 2 - JSP

##Rautalankamalli
* Luo webbiprojektisi WebContent-hakemistoon img-niminen kansio.
* Piirrä rautalankamalli pizzalistan ylläpitotyökalusta ja tallenna se png-tiedostona webbiprojektisi img-kansioon. Vaadittavia ominaisuuksia on pizzojen listaus, lisäys ja poisto. Kaikkien ominaisuuksien tulee toimia samalta sivulta. Käytä hahmotteluun seuraavaa työkalua http://draw.io

##CSS
* Luo projektisi WebContent-hakemistoon css-kansio.
* Luo kansioon tiedosto styles.css.
* Kirjoita tyylitiedostoon omavalintainen sisältö

##JSP
* Luo projektisi WebContent-hakemistoon uusi sivu list.jsp
* Tee listaussivusta rautalankamallisi mukainen
* Lisää sivun loppuun linkki rautalankamalliisi
* Kirjoita jsp-sivun alkuun koodi, jossa erilaisia pizza-olioita lisätään yhteen ArrayListiin.
* Tulosta ArrayListin sisältö html-tagien sekaan.

##Testaus
* Testaa osoitetta http://localhost:8080/PizzeriaAdmin/list.jsp
* Selaimen pitäisi näyttää html-muotoiltu pizzalista sekä toimiva linkki saman sivun rautalankamalliin.
* Lisäyksen ja poiston ei kuulu tässä vaiheessa toimia.

##Esimerkki CSS:n käytöstä

###HTML
```html
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>
<body>
	<p>Hei</p>
	
	<div id="SpesiaaliDivi">A</div>
	<div class="Sininen">B</div>
	
	<table>
	<tr>
	<td class="Sininen">a</td>
	<td>b</td>
	</tr>
	<tr>
	<td>c</td>
	<td>d</td>
	</tr>
	</table>
</body>
</html>
```

###CSS
```css
/* Kaikkialla sama fonttityyli*/

* {
	font-family: Verdana;
}
div {
	background-color: #EEEEEE;
	padding: 10px;
	margin: 20px;
}
table {
	border: 2px;
	border-color: black;
	border-style: dashed;
	height:300px;
	width:300px;
}
td {
	border: 1px;
	border-color: black;
	border-style: solid;
}
.Sininen {
	background-color: #DDDDFF;
}
#SpesiaaliDivi {
	position: fixed;
    top: 5px;
    right: 5px;
    width: 50px;
    height: 50px;
}
```
