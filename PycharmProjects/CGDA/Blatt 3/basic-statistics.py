#!/usr/bin/env python
# (the first line allows execution directly from the linux shell)
#
# --- simple python exercise
# Author:        G. Quast   Oct. 2013
# dependencies:  PYTHON v2.7, numpy
# last modified:
# ------------------------------------------------------------

# python2 - python3 compatibility
from __future__ import print_function, division, unicode_literals

import numpy as np


# write here your own implementation of the statistical functions
#Blatt Nr.2a
def mean(a):
    return np.sum(a)/float(len(a))

def variance(x):
    var = np.sum((x-mean(x))**2)/len(x)

    return var

def sigma(a):
    return np.sqrt(variance(a))

# --- main program ---
#
# read data from text file
a = np.loadtxt('numbers.dat', dtype=int)
N = len(a)
print(N, " numbers read \n", a)

#     print same statistical quantities using numpy functions
# Blatt Nr.2a) Eigene Funktionen:
print("\n*==* result with numpy functions:")
print("mean is", mean(a))
print("variance is", variance(a))
print("standard deviation sigma=", sigma(a))
print("unbiased sigma =", sigma(a)*np.sqrt(N/(N-1.0)))


# Nr.2 b)
# calculate and print some statistical quantities on raw data
# calculate probabilites from raw data
anzahlen = np.zeros(10)
for x in a:
    anzahlen[int(x)] += 1

for i in range(10):
    print('Die ' + str((i)) + ' ist ' + str(anzahlen[i]) + ' mal vorgekommen')

#Nr.2c)
# calculate mean and sigma from histogram and print
probability = np.zeros(10)
for i in range(len(anzahlen)):
    probability[i] = anzahlen[i]/float(100)

sum = 0

for i in range(len(anzahlen)):
    sum += i*anzahlen[i]
mean = sum/float(100)
print(sum)


sumvar = 0
counter = np.arange(0,10,1)

mean2 = np.sum(probability*counter)
print("Mean by probablities = " + str(mean2))

for i in range(10):
    sumvar += probability[i]*(i**2)
sumvar = sumvar- mean**2

print ("Variance by probablities = " + str(sumvar))