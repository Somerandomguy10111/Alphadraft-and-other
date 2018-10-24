import numpy as np
import matplotlib as plt


#Konstanten in SI Einheiten
c = 299792458               #Lichgeschwindigkeit in m/s
e = 1.6021766208 * 10**-19  #Elementarladung in C
mp = 1.6727*10**-27         #Masse Proton in kg
mn = mp                     #Masse Neutron in kg
me = 9.109*10**-31          #Masse Elektron in kg
nA = 6.022140857*10**23     #Avogadro Konstante in mol
mü0 = 4*np.pi*10**-7        #Magnetische Permeablitität im Vak
e0 = 8.854187817 *10**-12   #Elektrische Feldkonstante
pi = np.pi                  #Kreiszahl Pi

#Nützliche Funktionen und Umrechnungen
#Input: Energy in Joule
#Output: Joule to electronvoltage
def JouletoElectronvoltage(Joule):
    eVEnergy = Joule / e
    return eVEnergy

#Input: Energy in electronvoltage
#Output: Energy in Joule
def ElectronvoltagetoJoule(eVEnergy):
    Joule = eVEnergy * e
    return Joule

#Inputs: mass and velocity
#Output: Non relativistic kinetic energy of particle
def kineticenergy(m,v):
    kine = 1/2*m*v**2
    return kine

#Input: DeltaX,DeltaY in kartesian coordinates
#Output: Distance according to DeltaX DeltaY
def deltakoordtodistance(deltax,deltay):
    distance = np.sqrt(deltax**2+deltay**2)
    return distance

#Inputs: 2 variables with inverse sum relation
#Output: Reduced variable which represents the combined effect of both variables in according context
def reducedvar(var1,var2):
    reducedv = var1*var2/(var1+var2)
    return reducedv

#Inputs: variables with inverse sum relation
#Output: Reduced variable which represents the combined effect of all variables in according context
def reducedvariables(varlist):
    suminversevar = 0
    for var in varlist:
        suminversevar += var
    reducedv = 1/suminversevar
    return reducedv

#Input: Triangle capacities from point a to b, a to c, b to c
#Output: Star capacities nearest to point a,b,c
def capacitystartriangletransformation(cab,cac,cbc):
    csum = cab*cac+cac*cbc+cbc*cab

    ca = csum/cbc
    cb = csum/cac
    cc = csum/cab
    return ca,cb,cc

#Input: Triangle capacities from point a to b, a to c, b to c
#Output: Star capacities nearest to point a,b,c
def resitancestartriangletransformation(rab,rac,rbc):
    rsum = rab+rac+rbc
    ra = (rac*rab)/rsum
    rb = (rab*rbc)/rsum
    rc = (rac*rbc)/rsum
    return ra,rb,rc





R = 10
rab = R
rac = R
rbc = 3*R
rad = 3*R
rbe = R

ra,rb,rc = resitancestartriangletransformation(rab,rac,rbc)
Rl = ra+rad
Rr = rb+rbe
Rp = reducedvar(Rl,Rr)
Rer = rc + Rp

print('Ersatzwiederstand Rer = ' + str(Rer))

U = 20
Iges = U/Rer

Ir  = Iges/(Rr/Rl+1)
Il = Ir*(Rr/Rl)

Ibe = Ir
Iad = Il
Iab = abs(Ir-Il)
Iac = (Iges*rc+Il*ra)/(rac)
Ibc = (Iges*rc+Ir*rb)/(rbc)

print(Iges)
print(Ibe)
print(Iad)
print(Iab)
print(Iac)
print(Ibc)

c1 = 6 * 10**-0
c3 = c1
c5 = c1
c2 = 8 * 10**-0
c4 = c2

ca,cb,cc = capacitystartriangletransformation(c3,c2,c5)

cl = reducedvar(ca,c1)
cr = reducedvar(cb,c4)
cp = cl+c3
cer = reducedvar(cc,cp)
