#Tehtävä 1: Servletit

##Projekti

*Luo uusi dynaaminen webbiprojekti eclipsellä ja anna sen nimeksi PizzeriaAdmin (ilman ääkkösiä).

##Bean

* Luo pakkaus fi.omapizzeria.admin.bean.
* Luo java-luokka Pizza ja lisää siihen kolme atribuuttia: id (int), nimi (String) ja hinta (double)

##Servlet

* Luo pakkaus fi.omapizzeria.admin.controller.
* Luo uusi Servlet, jonka nimi on ControllerServlet ja se löytyy osoittteesta http://host/PizzeriaAdmin/controller.
* Luo doGet-metodin sisällä ArrayList, ja lisää siihen kolme erilaista pizzaa.
* Tulosta doGet-metodin lopussa responseen collectionin sisältö käyttäen foreach-silmukkaa. Erottele pizzat toisistaan p-tageilla ja pizzan ominaisuudet toisistaan br-tageilla.

