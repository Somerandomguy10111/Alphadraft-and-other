import numpy as np
import matplotlib.pyplot as plt

#Simulationsschritt (hier nichts verändern)
def simulationstep(deltat,accelerations,velocities,positions):

    if len(velocities)!= len(positions) or len(accelerations) != len(positions):
        print('Unequal dimensions!')
        return

    for i in range(len(velocities)):
        velocities[i] += deltat*accelerations[i]
        positions[i] += deltat*velocities[i]
    return


#Wirkende Kräfte
def fgrosse (xM):

    distance = np.sqrt(xM**2+p**2)
    fgross = f0 * np.exp(-(alpha * distance ** 2))
    return fgross

def frückstell (xauslenkung):
    force = -wo**2*m*xauslenkung
    return force

#Beschleunigungen aus parametern, geschwindigkeiten und positionen
def accelerations(velocities,positions):
    objectcount = len(positions)
    forces = np.zeros(objectcount)
    accelerations = np.zeros(objectcount)

    for i in range(objectcount):
        if i == 0:
            force0 = fgrosse(positions[1])
            force1 = frückstell(positions[0])
            forces[i] = force0 + force1

        if i == 1:
            forces[i] = -fgrosse(positions[1])

        accelerations[i] = forces[i] / masses[i]

    return accelerations




#Simulationsparameter
tges = 200
deltat = 10**-2
tvorAusgabe = 10
iterations = tges/deltat
forcecount = 2

#phyiskalische Parameter
masses = [1,10**8]

#Parameter Kraft 1
f0 = 100
alpha = 1/1000
p = 50

#Parameter Kraft 2
wo = 1
m = masses[0]

#Anfangsbedingungen
velocities = [0,20]
positions = [0,-2000]

#Maximal/Ausgabewerte
objectcount = len(positions)
outputpositions = [[] for i5 in range(objectcount)]
outputvelocities = [[] for i6 in range(objectcount)]

amaxarray = np.zeros(objectcount)
vmaxarray = np.zeros(objectcount)
smaxarray = np.zeros(objectcount)

for i in range(objectcount):
    outputpositions[i] = np.zeros(int(tvorAusgabe/deltat))
    outputvelocities[i] = np.zeros(int(tvorAusgabe/deltat))

#Simulation Anfang
for i in range(int(iterations)):

    accelerationlist = accelerations(velocities,positions)
    
    simulationstep(deltat,accelerationlist,velocities,positions)

    if i > int(iterations-tvorAusgabe/deltat):
        for i3 in range(objectcount):
            if accelerationlist[i3] > amaxarray[i3]:
                amaxarray[i3] = accelerationlist[i3]

        for i3 in range(objectcount):
            if velocities[i3] > vmaxarray[i3]:
                vmaxarray[i3] = velocities[i3]

        for i3 in range(objectcount):
            if positions[i3] > smaxarray[i3]:
                smaxarray[i3] = positions[i3]


    if i >= int(iterations-tvorAusgabe/deltat):
        for i2 in range(objectcount):

            outputpositions[i2][i-(int(iterations-tvorAusgabe/deltat))] = positions[i2]
            outputvelocities[i2][i-(int(iterations-tvorAusgabe/deltat))] = velocities[i2]

        if i%1000 == 0:
            print('mx: ' + str(positions[0]))
            print('Mx: ' + str(positions[1]))
            print('mv: ' + str(velocities[0]))
            print('mforce: ' + str(accelerationlist[0]))
            print()


beta = alpha*velocities[1]**2
F0 = f0*np.exp(-alpha*p**2)

amplitude = F0/(masses[0]*wo)*(np.pi/beta)**0.5*np.exp(-(wo**2)/(4*beta))

print('Amplitude nach Simulation: ' + str(smaxarray[0]))
print('Amplitude sollte sein: ' + str(amplitude))


plt.plot(outputpositions[0])
plt.show()
