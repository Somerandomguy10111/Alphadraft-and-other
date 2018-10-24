#!/usr/bin/python
#
# -------- Covariance.py ---------------------------------------
# Description: repeatedly fill a histogram with 10000 uniformly
#              distributed random numbers and study distributions
#              and correlations of bin contents.
# Author:      G. Quast   Nov. 2013
# dependencies: PYTHON v2.7, numpy, matplotlib.pyplot
# last modified:
# --------------------------------------------------------------
from __future__ import print_function, division, unicode_literals
import numpy as np
import matplotlib.pyplot as plt
import scipy.special as sp
import Histogram
import random


# Verteilungen aus Vorlesungsbeispielen
# ---------------------------------------

# Binomial distribution
def fBinomial(x, n, p):
    k = np.around(x)
    return sp.binom(n, k) * p**k * (1.-p)**(n-k)


# Poisson distribution
def fPoisson(x, mu):
    k = np.around(x)
    return (mu**k)/np.exp(mu)/sp.gamma(k+1.)


# Gauss distribution
def fGauss(x, mu=0., sigma=1.):
    return (np.exp(-(x-mu)**2/2./sigma**2)/np.sqrt(2*np.pi)/sigma)

def genrandomList(n):
    randlist = np.zeros(n)
    for i in range(n):
        randlist[i] = random.random()
    return randlist

# ---------------------------------------------------------------

# Aufgabenteil:
###############


def cov(x1, x2):
    cov = 0
    cor = 0
    # ... fill in the missing evaluation ...
    return cov, cor

# ------------------------------------------------------------
# 1. generate uniform distributions
# ------------------------------------------------------------
Nsample = 100
nexp = 1000

nbin = 5
bins = np.linspace(0, 1, nbin+1)

bin_1 = np.zeros(nexp)
bin_2 = np.zeros(nexp)

# loop over experiments
for i in range(0, nexp):
    # generate data
    randlist = genrandomList(Nsample)
    # histogram data
    n, bine = np.histogram(randlist, nbin)
    plt.clf()

    # store bin contents
    bin_1[i] = n[0]
    bin_2[i] = n[1]

# end loop over experiments

#Plot the first Häufigkeit in Histogramm
x = np.arange(0, Nsample, 1)
plt.plot(fGauss(x,Nsample/5.,np.sqrt(np.var(bin_1))))
n, bine, patches = plt.hist(bin_1, Nsample/5., normed = 1,
                                   facecolor='g', log=False, alpha=0.5)

plt.show()

#Plot the second Häufigkeit in Histogramm
x = np.arange(0, Nsample, 1)
plt.plot(fGauss(x,Nsample/5.,np.sqrt(np.var(bin_2))))
n, bine, patches = plt.hist(bin_2, 20,normed = 1,
                                   facecolor='g', log=False, alpha=0.5)
plt.show()


# statistical analysis of bin_i contents:
# - histogram of distribution of bin contents

# - overlay expected distribution

# - print covariance of bin_2 and bin_4

# - two-dimensional histogram of contents in bin 2 and 4

# - show plot of 2d distribution
