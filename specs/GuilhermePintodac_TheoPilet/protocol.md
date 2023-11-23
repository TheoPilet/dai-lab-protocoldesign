# DAI Labo 3

## Résumé
Calcp est un protocole client-serveur. Un client se connecte au serveur puis il envoie un calcul au serveur puis le serveur renvoie la réponse. Les opérations qu’il est capable d’effectuer sont les opérations de base sans la division (addition, soustraction et multiplication).

## Protocole de transport
Calcp va utiliser TCP pour établir la connexion client-serveur.  Le client doit donc connaître l’adresse IP du serveur. Le port d’écoute du serveur sera le port 5555.
Le client aura le choix de fermer la connexion après chaque calcul effectuer par le serveur.

## Message
OPERAND <nombre> :  Le client envoie les opérandes au serveur, un par un.
OPERATOR<opérateur> : Le client envoie l’opération à réaliser par le serveur.
RESULT<nombre> : Le serveur envoie le résultat du calcul.
ERROR_OPERAND : Le serveur signale à l’utilisateur que l’opérande envoyé ne correspond pas à une valeur numérique.
ERROR_OPERATOR : Le serveur signale à l’utilisateur que l’opérateur envoyé ne correspond pas à une opération qu’il peut effectuer.
Chaque message d’erreur est envoyé au maximum 3 fois. Si le troisième message d’une même erreur est envoyé alors il faut recommencer toute l’opération. Tous les messages sont en UTF8, avec un "\n" à la fin. 