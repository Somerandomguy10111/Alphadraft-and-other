# Musterloesung zu Blatt 2, Aufg. 2.2
''' Haeufigkeitsverteilung beim Wuerflen
    (kurze Loesung, array mit Zufallszahl als Index)
.. author:: Guenter Quast <g.quast@kit.edu>
'''

# python2 - python3 compatibility
from __future__ import print_function, division, unicode_literals

import numpy as np
import matplotlib.pyplot as plt



def diceroll():
    value = np.ceil(6*np.random.rand())
    return value

def sumroll(n):
    sum = 0
    for i in range(n):
        sum += diceroll()
    return sum

#Gauss Funktion aus letzter aufgabe
def fgauss(x, mu, sigma):
    return (np.exp(-(x-mu)**2/2/sigma**2)/np.sqrt(2*np.pi)/sigma)

# 1) "Wuerfeln" der Zufallszahlen:
würfe = int(input('Anzahl Würfe: '))
n = int(input('Anzahl Spiele: '))

# 2) Bestimmung der Häufigkeit
anzahlen = np.zeros(6*würfe)
for i in range(int(n/6.)):
    anzahlen[int((sumroll(würfe)-1)/6.)] += 1

#calculate mean
data = np.zeros(6*würfe)
for i in range(6*würfe):
    data[i] = i*anzahlen[i]/n
mean = sum(data)

#calculate variance
sumvar = 0
for i in range(len(data)):
    sumvar += data[i]*i
sumvar = sumvar - mean**2
sigma = np.sqrt(sumvar)


print(mean)
print(sumvar)


#Gausskurve als Vergleich zeichnen
x = np.arange(0, 6*würfe, 0.1)
plt.plot(x/6., fgauss(x, mean, sigma), 'r-')             # plot function

# 3) grafische Darstellung als Balkendiagramm mit bar():
plt.bar(np.arange(0,6*würfe,1), anzahlen, width=0.1, align='center', color='g', alpha=0.7)

#   und noch ein wenig verschoenern ...
plt.xlabel(r'$Gew\"urfelte\ Zahl$', size ='x-large')
plt.ylabel(r'$H\"aufigkeit$', size ='x-large')
#   und anzeigen
plt.show()