#######################################################################
#
# Beispiel eines Python-Programmes, Kehrwertberechnung
#
# Ausfuehren dieses Programms: python3 PROGRAMMNAME
# Dieses Programm ist in der Sprachversion 3 von python geschrieben.


# Ein Beispiel, wie Python-Module eingebunden werden
# Das time-Modul enthaelt den Befehl time.sleep, der unten aufgerufen wird.
import time
import numpy
import seaborn as snb


#######################################################################
# Beispiel fuer eine Python-Funktion
# Aufgabe: Kehrwert berechnen

def exp(a):
    wert = numpy.exp(a)
    return wert


#######################################################################
# Nun der Hauptprogrammteil.
# Aufgabe: Zahl abfragen, gegebenenfalls Kehrwert berechnen

print("Exponentialwert berechnen")
print("------------------")
print()

# Eingabe einer Zahl
a = float(input("Eine Zahl bitte: "))
# Fasse die Eingabe nun als float auf. Das kann auch schief gehen.
# Moegliche Abhilfe: try/except verwenden...

wert = numpy.complex128(a)


print(" e^%g = %5.2g" % (wert, exp(wert)))
print("text {0:g} und {1:f}".format(wert, exp(wert)))
time.sleep(20)
