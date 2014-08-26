# Tehtävä 5 - Session

## Servlet
* Tarkista servletin doGet-metodissa onko käyttäjän sessiossa "aloitusaika"-niminen atribuutti.
* Mikäli sellaista ei ole vielä olemassa luo uusi Date-olio ja tallenna se "aloitusaika"-nimiseksi sessio atribuutiksi.
* Muuta doPost-metodi sellaiseksi, että lisätyn pizzan tiedot tulostetaankin responsen sijaan konsoliin (System.out), ja tämän jälkeen selain uudelleenohjataan (redirect) hakemaan controller-sivu GET-pyynnöllä. Anna controllerille parametriksi added=true (/PizzeriaAdmin/controller?added=true)

## JSP
* Tulosta jsp-sivun yläreunassa session aloitusaika ruudulle.
* Mikäli requestista löytyy added-niminen http-parametri, näytä käyttäjälle teksti "Uuden pizzan lisääminen onnistui". Voit tarkastella parametrin sisältöä c:if-tagilla. Esim. `<c:if test="${not empty param.parametrin_nimi}">Moikka vaan</c:if>`

## Testaus
* Avaa selain osoitteeseen http://localhost:8080/PizzeriaAdmin/controller
* Sivun yläreunassa pitäisi lukea kellonaika, joka ei muutu ennen kuin käynnistät palvelimen uudestaan ja lataat sivun uudestaan.
* Mikäli syötät uuden pizzan tiedot lomakkeelle, pitäisi 
