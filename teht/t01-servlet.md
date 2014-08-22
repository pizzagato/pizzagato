#Tehtävä 1: Servletit

## Eclipse

Avaa Eclipse ja vaihda näkymäksi Java EE -perspektiivi

## Tomcat

* Luo Eclipseen uusi Tomcat-palvelininstanssi
* *Eclipse -> File -> New -> Other... -> Server -> Apache -> Tomcat v7.0 Server -> Browse... -> C:\install\apache-tomcat-7.0.39*

## Esimerkkikoodit

* Klonaa demoprojektit GitHubin ohke-Repositorysta
* *Eclipse -> File -> Import -> Projects from Git -> (Clone) URI -> https://github.com/jleikko/ohke.git*

##Projekti

* Luo uusi dynaaminen webbiprojekti eclipsellä ja anna sen nimeksi PizzeriaAdmin (ilman ääkkösiä).

##Bean

* Luo pakkaus fi.omapizzeria.admin.bean.
* Luo java-luokka Pizza ja lisää siihen kolme atribuuttia: id (int), nimi (String) ja hinta (double)

##Servlet

* Luo pakkaus fi.omapizzeria.admin.controller.
* Luo uusi Servlet, jonka nimi on ControllerServlet ja se löytyy osoittteesta http://host/PizzeriaAdmin/controller.
* Luo doGet-metodin sisällä ArrayList, ja lisää siihen kolme erilaista pizzaa.
* Tulosta doGet-metodin lopussa responseen collectionin sisältö käyttäen foreach-silmukkaa. Erottele pizzat toisistaan p-tageilla ja pizzan ominaisuudet toisistaan br-tageilla.

