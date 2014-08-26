#Tehtävä 3 - Lomakkeet

##JSP (lomake)

* Viimeistele listaussivulle html-lomake, jolla voi lisätä listaan uuden pizzan (nimi ja hinta).
* Aseta lomake lähetettäväksi osoitteeseen "controller" käyttäen http-metodia POST.

##Servlet

* Ota vastaan servletin doPost-metodissa lähetetyt http-parametrit ja paketoi ne yhteen Pizza-olioon.
* Tulosta doPost-metodin lopussa Pizza-olion sisältö ruudulle html-muotoiltuna (muuten tyhjä valkoinen sivu).

##Testaus

* Avaa osoite http://localhost:8080/PizzeriaAdmin/list.jsp
* Täytä uuden pizzan tiedot lomakkeeseen ja lähetä lomake
* Selaimen pitäisi siirtyä osoitteeseen http://localhost:8080/PizzeriaAdmin/controller
* Selaimessa tulisi näkyä juuri lisätyn pizzan tiedot valkoiselle sivulle tulostettuna.
