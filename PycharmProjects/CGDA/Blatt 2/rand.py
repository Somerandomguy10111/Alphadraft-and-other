import numpy as np
import matplotlib.pyplot as plt

seiten = 6

def diceroll():
    value = np.ceil(seiten*np.random.rand())
    print(value)
    return value

anzahlen = np.zeros(120)


for i in range (120):
    result = diceroll()
    anzahlen[int(result-1)] += 1

    print("A2" + str(anzahlen[0]))

plt.bar([1,2,3,4,5,6,7,8,9],anzahlen)
plt.show()

for i in range(seiten):
    print("Die "+ str(i+1) + " ist " + str(anzahlen[i]) + " mal vorgekommen")
