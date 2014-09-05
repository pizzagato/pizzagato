# Tehtävä 6 - JDBC

## Tietokantapalvelin (MariaDB)

* Kirjaudu SSH:lla sisään ryhmäsi protopalvelimelle (esim. Putty).
  * RED: proto113.haaga-helia.fi  
  * GREEN: proto184.haaga-helia.fi
  * BLUE: proto114.haaga-helia.fi
* Tarkista henkilökohtaisen tietokantasi salasana komennolla `cat MySQLPassword.txt`.
* Kirjaudu sisään tietokantaan komennolla `mysql -u <kayttajatunnuksesi> -p`

## Suunnittele pizzoja varten taulu tietokantaan

* Suunnittele taulu pizza, ja sisällytä siihen seuraavat kentät
  * id (automaattisesti kasvava kokonaisluku)
  * nimi (merkkijono)
  * hinta (desimaaliluku)
* Piirrä taulusta kuva työkalulla draw.io
* Kirjoita taululle luontiskripti ja aja se protopalvelimella sijaitsevaan henkilökohtaiseen tietokantaasi

## MariaDB JDBC Driver

* Lataa MariaDB Client Library osoitteesta https://downloads.mariadb.org/client-java
* Tallenna ladattu jar-tiedosto Eclipse-projektin kansioon WebContent/WEB-INF/lib

## Testiluokka
* Luo Eclipse-projektiin uusi pakkaus test
* Luo uusi Java-luokka pakkaukseen test ja anna sille nimeksi DBTest
* Kirjoita DBTest-luokkaan main-metodi
* Ohjelmoi main-metodin sisään sellainen koodi, joka tulostaa ajettaessa konsoliin pizza-taulun sisällön.
* Määrittele tietokantayhteys osoitteeseen localhost

## Testaus
* Avaa testausta varten ssh-tunneli protopalvelimelle
* Aja DBTest-luokka perinteisenä Java-sovelluksena "Run as Java Application"
