-----TIETOKANNAN LUONTI---------
-----AJA YKSITELLEN TAI VOI TULLA MAHDOLLISIA ONGELMIA LUONNISSA-------


CREATE TABLE Tayte( # Luodaan t‰ytetaulu johon tallennetaan kaikki pizzerian t‰ytteet
tayte_id INT NOT NULL AUTO_INCREMENT, # id jolla tunnistaa yksitt‰iset t‰ytteet ja joka kasvaa automaattisesti
nimi varchar(35) NOT NULL, # t‰ytteen nimi esim. mozzarella
primary key (tayte_id) # taulun p‰‰avain
); 


CREATE TABLE Pizzatesti( # Luodaan pizzataulu johon tallennetaan kaikki pizzerian pizzat
pizza_id INT  AUTO_INCREMENT, # automaattisesti kasvava id jolla tunnistaa yksitt‰iset pizzat
nimi varchar(20) NOT NULL, # pizzan nimi esim. klassinen
hinta decimal(5,2) NOT NULL, # pizzan hinta kahden desimaalin tarkkuudella
status INT NOT NULL, # pizzan status - onko pizza k‰ytˆss‰ vai ei
primary key (pizza_id) # taulun p‰‰avain
);

CREATE TABLE Juoma( # Luodaan juomataulu johon tallennetaan kaikki pizzerian juomat
juoma_id INT  AUTO_INCREMENT, # automaattisesti kasvava id jolla tunnistaa yksitt‰iset juomat
nimi varchar(50) NOT NULL, # juoman nimi esim. laitilan pˆyt‰vesi pore
hinta decimal(5,2) NOT NULL, # juoman hinta kahden desimaalin tarkkuudella
koko varchar(10) NOT NULL, # juoman koko esim. 0.33 litraa
tyyppi varchar(20) NOT NULL, # juoman tyyppi esim. virvoitusjuoma tai punaviini
primary key (juoma_id) # taulun p‰‰avain
);



CREATE TABLE Tilaus( # Luodaan tilaustaulu johon tallennetaan pizzerian tilaukset
tilaus_id INT AUTO_INCREMENT, # automaattisesti kasvava id jolla tunnistaa yksitt‰iset tilaukset
katuosoite varchar(25) NOT NULL, # tilaajan katuosoite
postinumero varchar(5) NOT NULL, # tilaajan postinumero
puhelinnumero varchar(20), # tilaajan puhelinnumero
kokonaishinta decimal(5,2) NOT NULL, # tilauksen yhteenlaskettu kokonaishinta
primary key (tilaus_id), # taulun p‰‰avain
);


CREATE TABLE Pizzatayte( # luodaan aputaulu joka yhdist‰‰ t‰ytteet oikeisiin pizzoihin
tayte_id INT , # id jolla tunnistaa yksitt‰iset t‰ytteet
pizza_id INT , # id jolla tunnistaa yksitt‰iset pizzat
primary key (tayte_id, pizza_id), # taulun p‰‰avainpari
foreign key(tayte_id) references Tayte (tayte_id), # viiteavain joka yhdist‰‰ taulun Taytetauluun
foreign key(pizza_id) references Pizza (pizza_id) # viiteavain joka yhdist‰‰ taulun Pizzatauluun
);


CREATE TABLE Tilausrivi( luodaan tilausrivitaulu johon tallennetaan tilauksen yksitt‰iset tilausrivit
rivi_id INT NOT NULL AUTO_INCREMENT , # automaattisesti kasvava id jolla tunnistaa yksitt‰iset tilaukset
tilaus_id INT, # id jolla tunnistaa yksitt‰isen tilauksen
pizza_id INT, # id jolla tunnistaa yksitt‰isen pizzan
juoma_id INT, # id jolla tunnistaa yksitt‰isen juoman
rivihinta decimal(5,2) NOT NULL, # yksitt‰isen tilausrivin kokonaishinta kahden desimaalin tarkkuudella
maara INT NOT NULL, # yhden rivin tuotteiden kappalem‰‰r‰
primary key (rivi_id), # taulun p‰‰avain
foreign key (pizza_id) references Pizza(pizza_id), # viiteavain joka yhdist‰‰ taulun Pizzatauluun
foreign key (tilaus_id) references Tilaus(tilaus_id), # viiteavain joka yhdist‰‰ taulun Tilaustauluun
foreign key (juoma_id) references Juoma(juoma_id) # viiteavain joka yhdist‰‰ taulun Juomatauluun
);

CREATE TABLE Admin( # Luodaan Admintaulu johon tallennetaan k‰ytt‰j‰tunnus ja suolattu salasana
nimi varchar(30) NOT NULL, # k‰ytt‰j‰tunnuksen nimi esim. Kissa
password varchar (255) NOT NULL, # k‰ytt‰j‰n salattu salasana
salt varchar (50) NOT NULL, # k‰ytt‰j‰n suola
primary key (nimi) # Taulun p‰‰avain
);

CREATE TABLE Attempters( # Luodaan Kirjautumistaulu johon tallennetaan kirjautumisyritykset
ip varchar(50) NOT NULL, # Kirjautujan ip-osoite
aika varchar(50) NOT NULL, # kirjautumisaika
tries INT NOT NULL, # kirjautumisyritykset
primary key (ip) # Taulun p‰‰avain
);

CREATE TABLE banned( # Luodaan Bannitaulu johon tallennetaan tiedot kirjautujan bannist‰
ip varchar(50) NOT NULL, # Kirjautujan ip-osoite
aika varchar(50) NOT NULL, # Bannin aika
primary key (ip) # Taulun p‰‰avain
);


--------------------------------------------------------------------------


------TIETOKANNAN DATAN LUONTILAUSEITA----------------------
------PIZZAT-----------

insert into Pizza(nimi, hinta, status) values('Klassinen', 12.00,1) # Lis‰t‰‰n Pizzatauluun arvot
,('Perinteinen', 14.00,1)
,('Tuhti', 14.00,1)
,('Fiini', 15.00,1)
,('Suussasulava',14.00,1)
,('Raikas',15.00,1)
,('Oivallinen',14.00,1)
,('Kauaskatseinen',13.00,1);

----------------------------
----TAYTTEET----------------
insert into Tayte(nimi) values('kirsikkatomaatti') # Lis‰t‰‰n T‰ytetauluun arvot 
,('mozzarella')
,('rucola')
,('paahdettu valkosipuli')
,('salami')
,('ananas')
,('jalopeno')
,('possu')
,('sipuli')
,('savujuusto')
,('gorgonzola')
,('saksanp‰hkin‰')
,('p‰‰ryn‰')
,('vuohenjuusto')
,('munakoiso')
,('pinjansiemen')
,('kylmasavulohi')
,('sitruunan kuori')
,('ilmakuivattu kinkku')
,('aurinkokuivattu tomaatti')
,('basilika')
,('artisokka')
,('balsamico')
,('chili');

------------------------------
----JUOMAT-------------------- 
insert into Juoma(nimi, hinta, koko, tyyppi) values # Lis‰t‰‰n Juomatauluun arvot
('Laitilan Rio Cola kolalimonaadi',2.50,'0.33 l','virvoitusjuoma')
,('Laitilan Sitruunasooda sitruunalimonaadi',2.50,'0.33 l','virvoitusjuoma')
,('Laitilan La Rita appelsiinilimonaadi',2.50,'0.33 l','virvoitusjuoma')
,('Laitilan LePom hedelm‰limonaadi',2.50,'0.33 l','virvoitusjuoma')
,('Laitilan Pore pˆyt‰vesi',1.50,'0.33 l','virvoitusjuoma')

,('Kukko Pils',6.00,'0.33 l','mieto alkoholijuoma')
,('Kukko Ale',6.00,'0.50 l','mieto alkoholijuoma')
,('Kievari Schwarz',7.00,'0.50 l','mieto alkoholijuoma')
,('Oiva kuiva luomu omenasiideri',6.00,'0.33 l','mieto alkoholijuoma')
,('Oiva kuiva p‰‰ryn‰siideri',6.00,'0.33 l','mieto alkoholijuoma')

,('Hardys Bin 141 Colombard Chardonnay 2013',10.00,'16 cl','valkoviini')
,('Hardys Bin 141 Colombard Chardonnay 2013',25.00,'0.75 l','valkoviini')

,('Ruffino Libaio Chardonnay 2013',12.00,'16 cl','valkoviini')
,('Ruffino Libaio Chardonnay 2013',27.00,'0.75 l','valkoviini')

,('Ferngrove Diamond Chardonnay 2013',12.90,'16 cl','valkoviini')
,('Ferngrove Diamond Chardonnay 2013',28.00,'0.75 l','valkoviini')

,('Fontella Chianti 2012',10.50,'16 cl','punaviini')
,('Fontella Chianti 2012',26.50,'0.75 l','punaviini')

,('Painters Cove Shiraz Cabernet 2013',13.20,'16 cl','punaviini')
,('Painters Cove Shiraz Cabernet 2013',30.50,'0.75 l','punaviini')

,('Cluver & Schaal Pinot Noir 2012',16.00,'16 cl','punaviini')
,('Cluver & Schaal Pinot Noir 2012',34.50,'0.75 l','punaviini')

,('Perlugo Extra Brut',14.70,'16 cl','kuohuviini')
,('Perlugo Extra Brut',29.50,'0.75 l','kuohuviini');


-----------------------------
--------YHDISTETƒƒN TƒYTTEET PIZZOIHIN------------
insert into Pizzatayte(pizza_id,tayte_id)values # Yhdistet‰‰n oikeat t‰ytteet oikeisiin pizzoihin
(1,1),(1,2),(1,3),(1,4)
,(2,5),(2,6),(2,7),(2,2)
,(3,8),(3,9),(3,10)
,(4,11),(4,12),(4,13)
,(5,14),(5,15),(5,16),(5,3)
,(6,17),(6,18),(6,3)
,(7,19),(7,20),(7,21)
,(8,1),(8,22),(8,3),(8,23),(8,24);


----------------------------------------------
----LUODAAN ADMINTUNNUKSET-------------------- 
INSERT INTO Admin # Syˆtet‰‰n Admintauluun k‰ytt‰j‰tunnus ja suolattu salasana ja suola
VALUES ('kissa', 'uhpt5vor5ixk+gs6MyBdO/dw8bsOWti8FPVGNHeMQVmFClacgtE4Nz2FzitrYdTe/g9ok902GbA1iyX3nlYzaw==', 'KxjaWmIjd3g=');






