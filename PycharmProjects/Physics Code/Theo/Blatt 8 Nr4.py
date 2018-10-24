import numpy as np
import matplotlib.pyplot as plt

def fgrosse (fo,p,alpha,xM):
    distance = xtodistance(xM,p)
    fgross = fo * np.exp(-(alpha * distance ** 2))
    return fgross

def frückstell (wo,m,xauslenkung):
    force = -wo**2*m*xauslenkung
    return force

def velocitytostrecke(deltat,v):
    deltas = deltat*v
    return deltas

def xtodistance(x,p):
    distance = np.sqrt(x**2+p**2)
    return distance

m = 1
p = 50
wo = 1
f0 = 100
alpha = 1/1000

Mvelocity = 20
Mx = -200

mx = 0
mvelocity = 0

tges = 200
deltat = 10**-2
tvorAusgabe = 100
tAbstand = 1
iterations = tges/deltat

mxarray = np.zeros(int(tvorAusgabe/deltat))
Mxarray = np.zeros(int(tvorAusgabe/deltat))
frmax = 0
fgmax = 0
xmax = 0

for i in range(int(iterations)):
    Mvelocity = Mvelocity
    Mx += velocitytostrecke(deltat,Mvelocity)

    mforce1 = fgrosse(f0,p,alpha,Mx)
    mforce2 = frückstell(wo,m,mx)
    mforce = mforce1+mforce2
    mvelocity += deltat*mforce/m
    mx += deltat*mvelocity

    if mforce2 > frmax:
        frmax = mforce2

    if mforce1 > fgmax:
        fgmax = mforce1

    if (i > (iterations-tvorAusgabe/deltat)):
        if mx > xmax:
            xmax = mx
        if i % 1 == 0:
            mxarray[i-(int(iterations-tvorAusgabe/deltat))] = mx
            Mxarray[i-(int(iterations-tvorAusgabe/deltat))] = Mx
        if i%1000 == 0:
            print('mx: ' + str(mx))
            print('Mx: ' + str(Mx))
            print('mv: ' + str(mvelocity))
            print('mforce: ' + str(mforce))
            print('rückstellkraft: ' + str(mforce2))
            print('fgross: ' + str(mforce1))
            print('maximale rückstellkraft: ' +  str(frmax))
            print('maximale abstoßungskraft: ' + str(fgmax))



beta = alpha*Mvelocity**2
F0 = f0*np.exp(-alpha*p**2)

amplitude = F0/(m*wo)*(np.pi/beta)**0.5*np.exp(-(wo**2)/(4*beta))

print('Amplitude nach Simulation: ' + str(xmax))
print('Amplitude sollte sein: ' + str(amplitude))


plt.plot(mxarray)
plt.show()

#plt.plot(Mxarray)
#plt.ylim(Mxarray[1],Mxarray[-1])
#plt.show()