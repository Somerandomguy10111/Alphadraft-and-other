import PhyPraKit
import numpy as np
import matplotlib.pyplot as plt

def findMaximumxPos(xlist,ylist):
    for i in range(len(ylist)):
        if ylist[i] == max(ylist):
            return xlist[i]

def deltaArray(xlist):
    deltalist = np.zeros(len(xlist)-1)
    for i in range(len(xlist)):
        if (i != len(xlist)-1):
            deltalist[i] = -(xlist[i+1]-xlist[i])
    return deltalist


def maximumfinder(xlist):
    maxlist = np.zeros(len(xlist))
    k = 1
    maxlist[0] = xlist[0]
    for i in range(len(xlist)):
        if (i != 0 and i != len(xlist)-1):
            if (xlist[i-1] < xlist[i] and xlist[i+1] < xlist[i]):
                maxlist[k] = xlist[i]
                k += 1
    truncatedmaxlist = maxlist[maxlist != 0]
    return truncatedmaxlist

def minimumfinder(xlist):
    minlist = np.zeros(len(xlist))
    k = 0
    for i in range(len(xlist)):
        if (i != 0 and i != len(xlist)-1):
            if (xlist[i-1] > xlist[i] and xlist[i+1] > xlist[i]):
                minlist[k] = xlist[i]
                k += 1
    truncatedminlist = minlist[minlist != 0]
    return truncatedminlist


fpath = "C:\\Users\\LiquidSpot\\PycharmProjects\\CGDA\Blatt 5\\signal.csv"

t = PhyPraKit.readCSV(fpath)
#print(t)
#Datenformat: Comma seperated values(CSV)
#Datenformat t = Liste von listen

#Numpy list for time and amplitude
time = np.zeros(10000);
amplitude = np.zeros(10000);

time = t[1][0]
amplitude = t[1][1]

#Smooth amplitude by mean, deltaA as difference between original and smooth Amplitude
smoothamplitude = PhyPraKit.meanFilter(amplitude,5)
deltaA = amplitude - smoothamplitude

#Plot amplitude over time
plt.plot(time,amplitude)
plt.title('amplitude over time')
plt.show()

#Plot smooth amplitude over time
plt.plot(time,smoothamplitude)
plt.title('Smooth amplitude over time')
plt.show()

#Plot deltaAmplitude over time
plt.plot(time,deltaA)
plt.title('delta Amplitude over time')
plt.show()

#Autocorrelate smooth amplitude
ac = PhyPraKit.autocorrelate(smoothamplitude)

#Plot Maximumarray
plt.plot(ac)
plt.title("Autocorrelate")
plt.show()

#Find minimums and maximums of autocorrelate and take differences
maxac = maximumfinder(ac)
minac = minimumfinder(ac)

print(maxac)
print(minac)

plt.plot(ac)
plt.title("Autocorrelate")
plt.show()

#Create Array of differences of Maximums
deltaMax = deltaArray(maxac)
deltaMin = deltaArray(minac)

print(deltaMax)
print(deltaMin)
deltaMin = (-1)*deltaMin
deltaNew = deltaMin + deltaMax

# plot data as histogram
nbin = 10
binc, bine, patches = plt.hist(deltaNew, nbin,facecolor='g', log=False, alpha=1)
plt.title("Histogramm of MaxDifferences")
plt.show()

#Calculate Period and Uncertainity from Histogram data
Perioddata = PhyPraKit.histstat(binc, bine, patches)
print("Period by amplitude alone = " + str(Perioddata[0]))
print("?? = "  + str(Perioddata[1]))
print("Uncertainity = " + str(Perioddata[2]))
print("True Period in s = " + str(Perioddata[0]/0.05555555555))
print("True Uncertantiy = " + str(Perioddata[2]/0.05555555555))

#Stelle Frequenz Spektrum dar
fourierspec = PhyPraKit.FourierSpectrum(time,smoothamplitude)
plt.plot(fourierspec[0],fourierspec[1])
plt.title("Frequenz Spektrum des Signals")
print('Frequency by Fourier Analysis = ' +str(findMaximumxPos(*fourierspec)))
plt.show()

