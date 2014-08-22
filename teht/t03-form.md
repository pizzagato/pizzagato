#Tehtävä 3 - Lomakkeet

##JSP (lomake)

* Tee sovellukseesi html-lomake, jolla voi lisätä listaan uuden pizzan. Lomake voi olla yhdistettynä listaussivuun tai kokonaan oma jsp-sivunsa.
* Aseta lomake lähetettäväksi osoitteeseen "addnew" käyttäen http-metodia POST.

##Servlet

* Ota vastaan servletin doPost-metodissa lähetetyt http-parametrit ja paketoi ne yhteen Pizza-olioon.
* Forwardoi request listaussivulle, mutta lisää requestin atribuutiksi mukaan Pizza-olio.

##JSP (tietojen näyttäminen)
* Tarkastele listaussivun alussa, onko requestissa mukana Pizza-oliota. Mikäli olio löytyy requestista, näytä käyttäjälle teksti "Pizza PIZZAN_NIMI on lisätty listaan." Uuden pizzan ei tarvitse kuitenkaan näkyä listassa vielä tässä vaiheessa.
