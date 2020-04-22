## Partie serveur-raspberry

Matériel : Raspberry Pi modèle 3B+

Un serveur UDP est hébergé sur le raspberry (sur lequel est branché un servo moteur).
Le serveur reçoit des messages du client (l'application mobile) et agit en conséquence sur le servo moteur afin de relâcher les graines.
Le degré d'ouverture de la trappe de relâchement des graines ainsi que le temps d'ouverture sont modulables à la création du serveur.

### Compilation

Utiliser le makefile afin de compiler

```
make
```

### Utilisation

Le serveur reçoit en paramètre : ouverture et durée.
Pour changer ces valeurs, il faudra donc relancer le serveur.

```
./serveur numero_port ouverture_trappe duree_ouverture
```

     numero_port : choisir un numéro de port (ex : 1234)
     ouverture_trappe : ouverture de la trappe de relâchement de graine en degré (compris entre 0 et 180 inclu)
     duree_overture : durée pendant laquelle la trappe de relâchement des graines reste ouverte (en seconde)

```
./client ip_serveur port_serveur
```

     ip_serveur : ip du serveur (IPV4)
     port_serveur : port du serveur (ex : 1234)

## Commandes client

* Message quelconque : > msg
* Signal 	     : > 1
* Arrêt du serveur   : > stop

## Languages

Client-Serveur : **C** (socket)
Servo Moteur : **Python** (GPIO)

### Servo / GPIO

Le servo se connecte au raspberry grâce à 3 fils : 
* Alimentation (5V) ici rouge
* Terre ici marron
* Modulation (gère l'info) ici orange

On va gérer ce servo grâce à la technique PWM (Pulse Width Modulation) ou MLI en français (Modulation de Largeur d'Impultions).

Comme dans la majorité des cas d'utilisation de servo, nous allons utiliser un signal de 50Hz.
50Hz <=> 50 pluses/sec <=> 1 pluse/20ms
Les pulses sont donc de 20ms chacunes.

*Note :*
  signal on pendant 0,5ms = angle 0°
  signal on pendant 2,5ms = angle 180°


L'angle du servo est contrôlé en fonction du pourcentage du cycle pendant lequel le signal est ON (signal ON -> courant 5V passe),
le reste du temps le signal est OFF (signal OFF -> pas de courant qui passe).

Ici, en python, la durée du cycle est calculée en pourcentage du cycle.
*Exemple :*
  0° = 0,5ms / 20ms * 100 = 2,5%
  180° = 2,5ms / 20ms * 100 = 12,5%

Grâce à nos cours de méthématiques de lycée, nous pouvons grâce à ces deux points déterminer la fonction affine suivante, qui convertie les degrés en proucentage de cycle :
f(x) = 0,55x + 10
