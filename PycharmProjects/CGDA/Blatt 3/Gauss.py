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

def Gaussianrand(sigma,mu,anzahl):
    data = np.zeros(1000)
    data = sigma * np.random.randn(anzahl) + mu
    return data

# define a function (Gauss distribution)
def fgauss(x, mu, sigma):
    return (np.exp(-(x-mu)**2/2/sigma**2)/np.sqrt(2*np.pi)/sigma)

# set parameters for Gaussdistribtion
mu = 0
sigma = 1

# generate data
data1 = Gaussianrand(0.5,1.5,1000)
data2 = Gaussianrand(0.15,0.6,1000)

vi = data1 + data2

sigmaprime = np.sqrt(0.5**2+0.15**2)

viprime = Gaussianrand(sigmaprime,2.1,1000)
mu = 2.1
sigma = sigmaprime

omega = data1/data2

n, bins, patches=      plt.hist(vi, 30, normed=1, facecolor='r',
                log=False, alpha=0.5)  # plot data

n, bins, patches=      plt.hist(viprime, 30, normed=1, facecolor='g',
                log=False, alpha=0.5)  # plot data

plt.xlabel('x')
plt.ylabel('probability density')
# title
plt.title('Histogram of Gauss distribution')

# plot Gauss distribution
x = np.arange(-4., 4., 0.1)
plt.plot(x, fgauss(x, mu, sigma), 'r-')             # plot function

# a nicely type-set formula of the function
plt.text(1, 0.4,
         r'$f(x) = \frac{1}{\sqrt{2\pi}\sigma}\,\exp{\left(\frac{-(x-\mu)^2}{2\sigma^2}\right)}$',
         fontsize=14, color='b')

plt.grid(True)  # show a grid for orientation
plt.show()      # now display everything on the screen

n, bins, patches=      plt.hist(omega, 30, normed=1, facecolor='g',
                log=False, alpha=0.5)  # plot data

sigmaprimeprime = 0
for i in range (1000):
    sigmaprimeprime += omega[i]*np.sqrt((0.5/data1[i])**2+(0.15/data2[i])**2)
sigmaprimeprime = sigmaprimeprime/float(1000)
sigma = sigmaprimeprime

# make plot nicer:
# axis labels
plt.xlabel('x')
plt.ylabel('probability density')
# title
plt.title('Histogram of Gauss distribution')

# plot Gauss distribution
x = np.arange(-4., 4., 0.1)
plt.plot(x, fgauss(x, mu, sigma), 'r-')             # plot function

# a nicely type-set formula of the function
plt.text(1, 0.4,
         r'$f(x) = \frac{1}{\sqrt{2\pi}\sigma}\,\exp{\left(\frac{-(x-\mu)^2}{2\sigma^2}\right)}$',
         fontsize=14, color='b')

plt.grid(True)  # show a grid for orientation
plt.show()      # now display everything on the screen
