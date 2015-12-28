Viittaa projektin tilaan **28.12.2015** klo **17:00**.
Relevant for project on **28.12.2015** at **17:00**.

### English
#### In general:
The packages and classes are well named and have clear and easy to understand connections. 

The structure of the application is very easy to decipher from the code alone.

Naming of methods and variables is also very good, as is the overall style in general.

#### Interfaces and database:
Nice and clean implementation of interfaces. (Both the java interfaces and the custom DB interface.)

The Iterable<Kortti> of the Pakka class is unused, but I suspect it will be in use shortly

#### Tests:
Test coverage in general was very good. However, the branch coverage could be improved.

Also, some of the test methods seem to do a lot of things (e.g. kortinOttoToimiiJaNullPalautetaanKunIndeksiYliKoon). It may be a good idea to try to split some of the tests into multiple smaller tests.

#### Class diagram:
The class diagram is a bit confusing. There appears to be no visible application logic class or any information on how the application is intended to work on a higher level. The db connection is clear however. Is one card's potential inclusion in multiple packs intended?

#### Nitpick:
Checkstyle reports an issue with a missing whitespace in the ListMemento class. There should be a space before the opening bracket for the class.

### Suomi
#### Yleisesti:
Paketit ja luokat ovat hyvin nimetyt ja luokkien yhteydet on helppo ymmärtää. 

Ohjelman rakenne on helppo ymmärtää koodin pohjalta.

Metodien ja muuttijien nimeäminen on tehty hyvin. Myös koodin luettavuus on hyvää (CleanCode)

#### Rajapinnat ja tietokanta:
Näppärää rajapintojen käyttöä. (Sekä javan omat että Tietokanta.)

Pakka luokan Iterable<Kortti> ei ole käytössä missään, mutta siihen taitaa tulla muutos heti kun ohjelmalogiikka hieman kehittyy.

#### Testit:
Testien rivikattavuus on hyvä, mutta haarautumakattavuus voisi olla parempi.

Jotkin testimetodit (esim. kortinOttoToimiiJaNullPalautetaanKunIndeksiYliKoon) testaavat ehkä turhan montaa asiaa. Voisi olla hyvä idea pilkkoa niitä.

#### Luokkakaavio:
Luokkakaavio on hieman hämmentävä. Ohjelmatason logiikka tai korkean tason rakennetta ei näytä olevan kuvattuna ollenkaan. Tietokantayhteys tosin on hyvin esitetty ja selkeä. Onko yhden kortin tosiaan tarkoitus kuulua * määrään pakkoja?

#### Yks pikkujuttu vielä:
Checkstyle herjaa puuttuvasta välilyönnistä ListMemento luokassa. Luokan aloittavan aaltosulkeen edessä (rivillä 7) pitäsisi olla välilyönti.
