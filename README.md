# HueSim

Spring Boot oefening om een REST service te bouwen.
Simuleert een klein deel van de Hue API implementatie.

| Eindpunt | Actie | Omschrijving  |
| ---------|:-----:|:-------------|
| /lamp | GET | geeft alle lampen weer |
| /lamp/id | GET | geeft instellingen van gekozen lamp |
| /lamp/id/ | PUT |  wijzigt omschrijving van de gekozen lamp |
| /lamp/id/status | PUT |  wijzigt instellingen van de gekozen lamp |
---- 
| Eindpunt | Actie | Omschrijving  |
| ---------|:-----:|:-------------|
| /groep | GET | geeft alle groepen weer |
| /groep/id | GET | geeft instellingen van gekozen groep |
| /groep/id/ | PUT |  wijzigt omschrijving van de gekozen groep |
| /groep/id/actie | PUT |  wijzigt instellingen van de lamp(en) in deze groep |

Bij het weergeven van een groep, vindt automatisch een validatie plaats van de lamp(e)n in de groep:
niet bestaande lampen worden uit de groep verwijderd.
Lampen in de groep worden in oplopende volgorde gesorteerd.
