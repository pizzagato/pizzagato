<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
	<title>Pizza Gatto</title>


<!-- Start WOWSlider.com HEAD section -->
	<link rel="stylesheet" type="text/css" href="engine1//style.css" media="screen" />
	<script type="text/javascript" src="engine1//jquery.js"></script>
<!-- End WOWSlider.com HEAD section -->

</head>
<body>
<div id="container">
	<div id="navi">
		<ul class>
			<li class="active"><a href="etusivu.jsp">ETUSIVU</a> </li>
			<li> <a href="${ControllerServlet.doGet}">MENU</a></li>
			<li><a href="tilaa.html">TILAA</a></li>	
			<li><a href="yht.html">YHTEYSTIEDOT</a></li>
			
		</ul>
	<div id="logo"><img src="img/pizzalogo_mini.png" alt="Logo"></div>
	</div>

</div>
	


<div id="slide"><!-- Start WOWSlider.com BODY section id=wowslider-container1 -->
	<div id="wowslider-container1">
	<div class="ws_images"><ul>
<li><a href="http://wowslider.com/vf"><img src="data1/images/kuva1..png" alt="full screen slider" title="kuva1" id="wows1_0"/></a></li>
<li><img src="data1/images/kuva2..png" alt="kuva2" title="kuva2" id="wows1_1"/></li>
</ul></div>
<div class="ws_bullets"><div>
<a href="#" title="kuva1">1</a>
<a href="#" title="kuva2">2</a>
</div></div><span class="wsl"><a href="http://wowslider.com/vs">jquery slideshow</a> by WOWSlider.com v6.6</span>
	<a href="#" class="ws_frame"></a>
	<div class="ws_shadow"></div>
	</div>
	<script type="text/javascript" src="engine1//wowslider.js"></script>
	<script type="text/javascript" src="engine1//script.js"></script>
<!-- End WOWSlider.com BODY section -->



</div>

<div id="wrapper">
	<div class="vasen">
	<h1>Otsikko</h1>
	<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vitae viverra nisl, vitae vulputate nunc. Nullam accumsan odio non diam semper, et sollicitudin dolor pulvinar. Sed sit amet egestas libero. Nullam tempor augue lacus, quis viverra magna mattis sed. Etiam a accumsan libero, quis molestie mauris. Maecenas orci est, dapibus pellentesque metus vitae, placerat laoreet purus. Mauris vestibulum et lectus eu rhoncus. Quisque ut ligula laoreet, euismod ante vel, facilisis nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>

<p>Cras vitae cursus sem. Sed vulputate nulla et nisl lobortis maximus. Quisque suscipit enim vitae mauris consectetur volutpat. Aenean pretium risus at urna finibus, quis congue nulla commodo. Curabitur vel leo tristique, fermentum nisl a, consequat nunc. Duis id mi vitae orci consequat congue.</p>

	</div>
	<div class="oikea">
	<h1>AUKIOLOAJAT</h1>
	<table>
	<tr><td>MA-PE</td><td>35-54656</td></tr>
	<tr><td>LA</td><td>459-35</td></tr>
	<tr><td>SU</td><td>Suljettu</td><tr>
	</table>
	</div>
</div>

<div style="clear: both"></div>
</div>
<footer>
	<div id="vasenfooter">
	<img src="img/tekstilogo1.png" alt="tekstilogo"> 
	</div>

	<div id="oikeafooter">
	Tilaa uutiskirje <input type="text" name="subscribe"> <button>Tilaa</button>
	</div>

</footer>

</body>


</html>