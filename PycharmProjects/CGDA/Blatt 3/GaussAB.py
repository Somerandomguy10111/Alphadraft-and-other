#!/usr/bin/python

# -------- Gauss.py ------------------------------------------
# Description: example showing
#              - how to define a function
#              - generate (Gaussian) random numbers
#              - generate a histogram
#              - use matplotlib to plot histogram and function
# Author:      G. Quast   Apr. 2016
# dependencies: PYTHON v2.7 o v3.4 , numpy, matplotlib.pyplot
# last modified:
# ------------------------------------------------------------

# python2 - python3 compatibility
from __future__ import print_function, division, unicode_literals

import numpy as np
import matplotlib.pyplot as plt


# define a function (Gauss distribution)
def fgauss(x, mu, sigma):
    return (np.exp(-(x-mu)**2/2/sigma**2)/np.sqrt(2*np.pi)/sigma)

# set parameters for Gaussdistribtion
mu = 0
sigma = 1

# generate random Gaussian numbers with mean 0 and variance 1
data = sigma*np.random.randn(100) + mu

mlist = np.zeros(1000)
xlist = np.zeros(10000)
for i in range(1000):
        rand = np.random.randn(10)
        mi = 0
        for i2 in range(9):
            mi += rand[i2]
            xlist[i*10+i2] = rand[i2]
        mi = mi/(float(9))
        mlist[i] = mi



n, bins, patches=      plt.hist(mlist, 30, normed=1, facecolor='r',log=False, alpha=0.5)  # plot data

n, bins, patches=      plt.hist(xlist, 30, normed=1, facecolor='g',log=False, alpha=0.5)  # plot data

plt.show()      # now display everything on the screen


