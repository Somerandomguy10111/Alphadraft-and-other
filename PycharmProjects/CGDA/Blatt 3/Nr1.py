# -*- coding: utf-8 -*-
#      diese Zeile legt die Codierung von Umlauten fest
##################################################################

# python2 - python3 compatibility
from __future__ import print_function, division, unicode_literals

path = "C:\Users\danie\PycharmProjects\CGDA\Blatt 3"

import numpy as np
import matplotlib.pyplot as plt

import importlib.util
spec = importlib.util.spec_from_file_location("GaussAB", path)
foo = importlib.util.module_from_spec(spec)
spec.loader.exec_module(foo)





def resonance(x,amplitude,D):

    f1 = (1-x**2)**2
    f2 = (2*x*D)**2
    f3 = np.sqrt(f1+f2)

    R = amplitude/f3              # Berechnung der Funktionswerte
    return R               # Rueckgabe des Ergebnisses


# Ein numpy-array, das die Stuetzstellen enthaelt (np.pi ist pi=3.141..)
emin, emax = 0, 3
npoints = 256
X = np.linspace(emin, emax, npoints, endpoint=True)

# Plot der Funktionen, die werden hier aber noch nicht angezeigt!
amplitude = 1

# Darstellung einer Resonanz Funktion

for dampening in (0.1,0.5,0.6,0.9,1.5,1.9,2.5,3):
    Y = resonance(X, amplitude, dampening)
    plt.plot(X, Y, color="green", label=r"Damp ="+str(dampening))

# Definition der Grenzen der grafischen Ausgabe ...
plt.xlim(0, 3)
plt.xlabel('Winkelgeschwindigkeit')
plt.ylabel('Amplitude')
# und Verschönern der Grafik:
# Setze in X- und Y-Richtung ein paar Ticks (Achsenpunkte)
# LaTeX-Ausdruecke koennen verwendet werden, r steht fuer "raw-string"
plt.xticks([0, 1, 2, 3],
           [r'$1$', r'2', r'2', r'3'])
plt.yticks(np.linspace(0, 4, 5, endpoint=True))

# Wo soll die Legende hin? (Text dazu ist in den "label" der plot-Anweisungen)
plt.legend(loc='upper right')

# Zeige ein Gitternetz
plt.grid(True)

# Zeige nun alles auf dem Bildschirm an
plt.show()