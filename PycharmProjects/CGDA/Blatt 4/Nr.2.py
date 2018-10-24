#!/usr/bin/python

# -------- PlotUniform.py ------------------------------------------
# Description: example showing
#              - how to define and fill a histogram with
#                 uniform random numbers
#              - use matplotlib to plot histogram and function
#              - calculate statistical information from histogram array
#
# Author:      G. Quast   Dec. 2013
# dependencies: PYTHON v2.7, numpy, matplotlib.pyplot
# last modified:
# --------------------------------------------------------------
from __future__ import print_function, division, unicode_literals
from random import *
import numpy as np
import matplotlib.pyplot as plt


# define a function (straight line)
def fpol1(x, a=1., b=0.):
    return (a*x + b)

#Gauss Funktion aus letzter aufgabe
def fgauss(x, mu, sigma):
    return (np.exp(-(x-mu)**2/2/sigma**2)/np.sqrt(2*np.pi)/sigma)

def sumrandom(n):
    sum = 0
    for i in range(n):
        sum += random()
    return sum


def histstat(binc, bine):
    # calculate mean of a histogram with bincontents binc and bin edges bine
    bincent = (bine[1:]+bine[:-1])/2    # determine bincenters
    mean = sum(binc*bincent)/sum(binc)
    rms = np.sqrt(sum(binc*bincent**2)/sum(binc) - mean**2)
    return mean, rms

# generate some data
zdata = np.zeros(100000)
n2 = 2

for i in range(len(zdata)):
    z = (sumrandom(n2)-(n2/2.))/np.sqrt(n2/12.)
    #print('sum of randn' +str(sum(np.random.randn(n2))))
    zdata[i] = z

# plot data as histogram
nbin = 50
binc, bine, patches = plt.hist(zdata, nbin, normed=1,
                               facecolor='g', log=False, alpha=0.5)
# make plot nicer:
plt.xlabel('random number')  # axis labels
plt.ylabel('frequency')
plt.title('Histogram of uniform random numbers')  # title

#Gausskurve als Vergleich zeichnen
x = np.arange(-2,2, 0.1)
plt.plot(x, fgauss(x, 0, 1), 'r-')             # plot function


plt.show()

# calculate histogram mean and rms from histogram array
hmean, hsigma = histstat(binc, bine)    # get mean and RMS
print("mean, sigma = ", hmean, hsigma)