#Tehtävä 4 - JSTL

## Kirjasto

* Luo projektiisi hakemisto /WebContent/WEB-INF/lib.
* Kopio demoprojektista jstl-kirjasto omaan lib-hakemistoosi.

## Servlet
* Poista controllerin doGet-metodista kaikki tulostamiseen liittyvä koodi. Jätä jäljellä ainostaan ArrayListin luominen ja sen täyttäminen pizzoilla.
* Lisää doGet-metodin loppuun sellainen koodi, että pizzalista lisätään requestin atribuutiksi.
* Forwardoi requesti lopuksi  jsp-sivulle.

# JSP
* Lisää listaukseen käytetyn jsp-sivun alkuun jstl-referenssi.
* Voit poistaa jsp-sivulta arraylistin luomiskoodin. ArrayList saapuu requestin mukana controllerilta jsp-sivulle.
* Looppaa läpi controllerilta saapunut pizzalista käyttäen silmukkana c:forEach-tagia ja tietojen tulostamisessa c:out-tageja.

# Testaus
* Testaa osoitetta: http://localhost:8080/PizzeriaAdmin/controller
* Sinun pitäisi saada näkyviin html-muotoiltu lista pizzoista. Sen sisältö on koodattu controlleriin ja ulkoasu on määritelty jsp-sivulla.
