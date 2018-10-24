#!/usr/bin/python
# module Histogram.py
''' plot 1d and 2d histograms with matplotlib, based on numpy histograms
.. author:: Guenter Quast <g.quast@kit.edu>
'''

# Author:      G. Quast   Dec. 2013
# dependencies: PYTHON v2.7, numpy, matplotlib.pyplot
# last modified: Jan. 2016
# --------------------------------------------------------------

# pyhton2 - python3 compatibility
from __future__ import print_function, division, unicode_literals

import numpy as np, matplotlib.pyplot as plt


# ### one-dimensional histogram (numpy + matplotlib) ###
def nhist(data, bins=50, xlabel='x', ylabel='frequency'):
    """ Histogram.hist
        show a one-dimensional histogram 
        Args:
          data: array containing float values to be histogrammed
          bins: number of bins
          xlabel: label for x-axis
          ylabel: label for y axix
        Returns:
          float arrays bin content and bin edges
    """
    bc, be = np.histogram(data, bins)  # histogram data
    bincent = (be[:-1] + be[1:]) / 2.
    w = 0.9 * (be[1] - be[0])
    plt.bar(bincent, bc, align='center', width=w, facecolor='b', alpha=0.75)  #
    plt.xlabel(xlabel, size='x-large')  # ... for x ...
    plt.ylabel(ylabel, size='x-large')  # ... and y axes
    #  plt.show()
    return bc, be


def histstat(binc, bine, pr=True):
    """ Histogram.histstat
      calculate mean of a histogram with bincontents binc and bin edges bine
      Args:
       binc: array with bin content
       bine: array with bin edges
      Returns:
       float: mean and sigma    
    """
    bincent = (bine[1:] + bine[:-1]) / 2  # determine bincenters
    mean = sum(binc * bincent) / sum(binc)
    rms = np.sqrt(sum(binc * bincent ** 2) / sum(binc) - mean ** 2)
    if pr:
        print('hist statistics:\n' \
              '   mean=%g, sigma=%g\n' % (mean, rms))
    return mean, rms


# ### two-dimensional histogram (numpy + matplotlib) ###
def nhist2d(x, y, bins=10, xlabel='x axis', ylabel='y axis', clabel='counts'):
    """ Histrogram.hist2d
      create and plot a 2-dimensional histogram  
        Args:
          x: array containing x values to be histogrammed
          y: array containing y values to be histogrammed
          bins: number of bins
          xlabel: label for x-axis
          ylabel: label for y axix
          clabel: label for colour index
        Returns:
          float array: array with counts per bin
          float array: histogram edges in x
          float array: histogram edges in y
    """
    H2d, xed, yed = np.histogram2d(x, y, bins)  # numpy 2d histogram function
    Hpl = np.rot90(H2d)  # rotate, ...
    Hpl = np.flipud(Hpl)  # ... flip, ...
    Hpl = np.ma.masked_where(Hpl == 0, Hpl)  # ... and mask zero values, ...
    im = plt.pcolormesh(xed, yed, Hpl, cmap='Blues')  # ... then make plot
    cbar = plt.colorbar()  # show legend
    cbar.ax.set_ylabel(clabel)  # print lables for legend, ...
    plt.xlabel(xlabel)  # ... for x ...
    plt.ylabel(ylabel)  # ... and y axes
    #  plt.show()
    return H2d, xed, yed


def hist2dstat(H2d, xed, yed, pr=True):
    """
      calculate statistical information from 2d Histogram
      Args:
        H2d: histogram array (as returned by histogram2d)
        xed: bin edges in x
        yed: bin edges in y
      Returns:
        float: mean x
        float: mean y 
        float: variance x
        float: variance y
        float: covariance of x and y
        float: correlation of x and y
    """
    bcx = (xed[:-1] + xed[1:]) / 2
    bcy = (yed[:-1] + yed[1:]) / 2
    sumxy, sumx, sumx2, sumy, sumy2, sum = 0., 0., 0., 0., 0., 0.
    for ix in range(0, len(bcx)):
        for iy in range(0, len(bcy)):
            sumxy += H2d[ix, iy] * bcx[ix] * bcy[iy]
            sumx += H2d[ix, iy] * bcx[ix]
            sumx2 += H2d[ix, iy] * bcx[ix] * bcx[ix]
            sumy += H2d[ix, iy] * bcy[iy]
            sumy2 += H2d[ix, iy] * bcy[iy] * bcy[iy]
            sum += H2d[ix, iy]
    meanx = sumx / sum
    varx = (sumx2 / sum - meanx * meanx)
    meany = sumy / sum
    vary = (sumy2 / sum - meany * meany)
    cov = (sumxy / sum - meanx * meany)
    cor = cov / np.sqrt(varx * vary)
    if pr:
        print('hist2d statistics:\n' \
              '   <x>=%g, <y>=%g\n\
                  var_x=%.2g, var_y=%.2g\n\
                  cov=%.2g, cor=%.2g\n' \
              % (meanx, meany, varx, vary, cov, cor))
    return meanx, meany, varx, vary, cov, cor


# ---------------------------------------------------------------
if __name__ == "__main__":
    # demonstrate usage
    fig = plt.figure(1, figsize=(10., 10.))

    # generate two arrays with pairs of correlated gaussian numbers
    mux, sigx = 10., 2.
    muy, sigy = 20., 3.
    rho = 0.75
    isize = 10000
    u = np.random.randn(isize)
    x = mux + sigx * u  # gauss, with mean mux and sigma sigx
    v = np.random.randn(isize)
    y = muy + sigy * (rho * u + np.sqrt(1. - rho ** 2) * v)

    ax_y = plt.subplot(2, 2, 1)  # y histogram and statistics
    #  bincont,binedge = nhist(y,50,xlabel="y") # histogram data
    bincont, binedge, p = plt.hist(y, 50)  # histogram data
    ax_y.set_xlabel('y')
    ax_y.set_ylabel('frequency')
    histstat(bincont, binedge)

    ax_x = plt.subplot(2, 2, 4)  # x histogram and statistics
    #  bincont,binedge = nhist(x,50) # histogram data
    bincont, binedge, p = plt.hist(x, 50)  # histogram data
    ax_x.set_xlabel('x')
    ax_x.set_ylabel('frequency')
    histstat(bincont, binedge)

    ax_xy = plt.subplot(2, 2, 2)  # 2d historgram and statistics
    #  H, xedges, yedges = nhist2d(x,y,50,'var 1', 'var 2')
    H, xedges, yedges, p = plt.hist2d(x, y, 50, cmap='Blues')
    ax_xy.set_xlabel('x')
    ax_xy.set_ylabel('y')
    hist2dstat(H, xedges, yedges, True)

    plt.show()