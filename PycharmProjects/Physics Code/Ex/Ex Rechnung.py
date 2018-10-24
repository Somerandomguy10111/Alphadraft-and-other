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
cWasser = 4190              #Spezifische Wärmekapazität von Wasser in J/kg/K
cEisen = 460                #Spezifische Wärmekapazität von Wasser in J/kg/K

#Nützliche Funktionen und Umrechnungen
#Input: Energy in Joule
#Output: Joule to electronvoltage
def JouleToeV(Joule):
    eVEnergy = Joule / e
    return eVEnergy

#Input: Energy in electronvoltage
#Output: Energy in Joule
def eVtoJoule(eVEnergy):
    Joule = eVEnergy * e
    return Joule

#Inputs: mass and velocity
#Output: Non relativistic kinetic energy of particle
def Ekin(m,v):
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
def CSTtransform(cab,cac,cbc):
    csum = cab*cac+cac*cbc+cbc*cab

    ca = csum/cbc
    cb = csum/cac
    cc = csum/cab
    return ca,cb,cc

#Input: Triangle capacities from point a to b, a to c, b to c
#Output: Star capacities nearest to point a,b,c
def RSTtransform(rab,rac,rbc):
    rsum = rab+rac+rbc
    ra = (rac*rab)/rsum
    rb = (rab*rbc)/rsum
    rc = (rac*rbc)/rsum
    return ra,rb,rc

Tw = 300
Te = 473.15

me = 1
mw = 10

Ew = Tw*mw*cWasser
Ee = Te*me*cEisen

kw = mw*cWasser
ke = me*cEisen

Tf = (Ew+Ee)/(kw+ke)

print('finaltemp = ' + str(Tf))
print('deltaQw = ' + str(kw*Tf-Ew))
print('deltaQe = ' + str(ke*Tf-Ee))