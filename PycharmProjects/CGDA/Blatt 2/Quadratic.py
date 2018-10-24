import numpy as np
import matplotlib.pyplot as plt
import seaborn as snb


def squarearray(xlist):
    for i in range(len(xlist)):
        xlist[i] = xlist[i]*xlist[i]
    return xlist

xlist = np.linspace(0,5,100)
ylist = xlist**2
xm = np.array([1,2,3,4])
ym = xm**2+np.random.randn(4)
yerr1 = 0.1*ym

plt.plot(xlist,ylist)
plt.errorbar(xm,ym,yerr = yerr1,color='red',fmt='o')
plt.show()