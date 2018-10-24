import numpy as np
import matplotlib.pyplot as plt

def Fgrav(g,m,angle):
    fgrav = -m*g*angle
    return fgrav

def simulationstep(deltat, accelerations, velocities, positions):
    if len(velocities) != len(positions) or len(accelerations) != len(positions):
        print('Unequal dimensions!')
        return

    for i in range(len(velocities)):
        velocities[i] += deltat * accelerations
        positions[i] += deltat * velocities[i]
    return velocities, positions


def accelerations(paramlists, masses, velocities, positions, objectcount):
    forces = np.zeros(objectcount)
    accelerations = np.zeros(len(forces))

    for i in range(objectcount):
        if i == 0:
            force1 = Fgrav(paramlists[0], masses[0], positions[0])
            forces[i] = force1

        if i == 1:
            forces[i] = 0

        accelerations[i] = forces[i] / masses[i]

    return accelerations


# Simulationsparameter
tges = 200
deltat = 10 ** -2
tvorAusgabe = 100
iterations = tges / deltat
objectcount = 2

# Objekt 0 ist leichte Masse
# Objekt 1 ist schwere Masse
# phyiskalische Parameter
masses = {1}

R = 10
g = 10

paramlists = object
paramlists[0] = {g, masses[0]}

# Anfangsbedingungen
velocities = {20}
positions = {0}

# Maximal/Ausgabewerte
outputpositions = object
outputvelocities = object

amaxarray = np.zeros(objectcount)
vmaxarray = np.zeros(objectcount)
smaxarray = np.zeros(objectcount)

for i in range(objectcount):
    outputpositions[i] = np.zeros(int(tvorAusgabe / deltat))
    outputvelocities[i] = np.zeros(int(tvorAusgabe / deltat))

# Simulation Anfang
for i in range(int(iterations)):

    accelerations = accelerations(paramlists, masses, velocities, positions, objectcount)

    simulationstep(deltat, accelerations, velocities, positions)

    if i > (iterations - tvorAusgabe / deltat):
        for i in range(objectcount):
            if accelerations[i] > amaxarray[i]:
                amaxarray[i] = accelerations[i]

        for i in range(objectcount):
            if velocities[i] > vmaxarray[i]:
                vmaxarray[i] = velocities[i]

        for i in range(objectcount):
            if positions[i] > smaxarray[i]:
                smaxarray[i] = positions[i]

    if (i > (iterations - tvorAusgabe / deltat)):
        for i2 in range(objectcount):
            outputpositions[i2][i - (int(iterations - tvorAusgabe / deltat))] = positions[i2]
            outputvelocities[i2][i - (int(iterations - tvorAusgabe / deltat))] = velocities[i2]

        if i % 1000 == 0:
            print('mx: ' + str(positions[0]))
            print('Mx: ' + str(positions[1]))
            print('mv: ' + str(velocities[0]))
            print('mforce: ' + str(accelerations[0]))
            print()

print('Amplitude nach Simulation: ' + str(smaxarray[0]))
print('Amplitude sollte sein: ' + str())

plt.plot(outputpositions)
plt.show()

# plt.plot(Mxarray)
# plt.ylim(Mxarray[1],Mxarray[-1])
# plt.show()