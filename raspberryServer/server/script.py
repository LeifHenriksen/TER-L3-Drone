# Importation Librairies
import RPi.GPIO as GPIO
import time
import sys

deg = int(sys.argv[1]) # Ouverture (degres)
wait = int(float(sys.argv[2])) # Temps d'ouverture (seconde)
rdeg = 0.055*deg+2.5 # Ouverture (degres local)

# Definition du mode de control de GPIO (ici BOARD, cad on manipulera les pins par leur numero)
GPIO.setmode(GPIO.BOARD)

# On choisi le pin 11 comme sortie du signal
# On defini le servo sur le pin 11 pour gerer les infos (PWM)
# On declare un objet PWM (Pulse With Modulation)
GPIO.setup(11,GPIO.OUT)
servo = GPIO.PWM(11,50) # pin 11 / frequence 50Hz

# On demarre le PWM initialise a 2.5 (= 0 degres)
servo.start(2.5)

print (" $ Lachement des graines")

# Tourne a 'deg' degres
servo.ChangeDutyCycle(rdeg)

# On attend 'time' seconde
wait+=1 # On incremente l'attente d'une seconde pour laisser le temps a l'helice de tourner si le temps saisi par l'utilisateur est n$
time.sleep(wait);

# Retourne en position initiale
servo.ChangeDutyCycle(2.5)

time.sleep(1);


# On ferme l'objet et reset GPIO
servo.stop()
GPIO.cleanup()
print (" $ Fin lachement des graines")



