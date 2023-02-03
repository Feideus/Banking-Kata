# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '11' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

### Reference Documentation

### Ajouts interessants possibles: 

* Ajout d'une classe Client 
* Ajouter la validation a l'entree des controllers (@Valid)
* Extendre la gestion des exceptions pour la rendre plus fine (Eviter la RuntimeException, differencier les erreurs d'input (400) des erreurs d'autorisation (401))
* Externaliser la recuperation du compte dans la persistence pour isoler le traitement metier. (Ajouter un service dedie)
* Factoriser la definition du contexte pour les @Test
* Extraire le mapping des input/output Dto dans une couche de mapping dediee 
  
