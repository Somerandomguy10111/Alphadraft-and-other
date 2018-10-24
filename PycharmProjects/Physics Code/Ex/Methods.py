import numpy as np
import matplotlib.pyplot as plt


def crossproduct(a,b):
    if len(a) != len(b):
        print("Vektors have different lengths")
        return

    c = np.zeros(len(a))
    for i in range(len(c)):
        for i2 in range(len(a)):
            for i3 in range(len(b)):
                c[i] += levicivita(i+1,i2+1,i3+1)*a[i2]*b[i3]
    return c

def levicivita(i,j,k):
    if (i == j or i == k or j == k):
        return 0

    if (i == 1 and j == 2 and k == 3) or (i == 2 and j == 3 and k == 1) or (i == 3 and j == 1 and k ==2):
        return 1
    else:
        return -1

def vektorbetrag(a):
    b = 0
    for ai in a:
        b += ai**2
    return np.sqrt(b)

def skalarprodukt(a,b):
    if len(a) != len(b):
        print("Vektors have different lengths")
        return

    sk = 0
    for i in range(len(a)):
        sk += a[i]*b[i]
    return sk

def anglebetweenvectors(a,b):
    angle = np.arccos(skalarprodukt(a,b)/(vektorbetrag(a)*vektorbetrag(b)))
    return angle

a = [-1,2,5]
b = [5,10,3]
c = crossproduct(a,b)

absa = vektorbetrag(a)
absb = vektorbetrag(b)

sk = skalarprodukt(a,b)

#print('c = ' + str(c))

angle = anglebetweenvectors(a,b)
#print(angle)

ap = np.sqrt((absa*absb)**2-sk**2)
ap2 = absa*absb*np.sin(angle)

#print(ap)
#print(ap2)
#print(vektorbetrag(c))
#ap ist vektorbetrag , passt!

c1square = c[0]**2
c2square = c[1]**2
c3square = c[2]**2

a1 = a[0]
a2 = a[1]
a3 = a[2]

b1 = b[0]
b2 = b[1]
b3 = b[2]

c1 = c[0]
c2 = c[1]
c3 = c[2]

cstrich = np.zeros(len(c))
for i in range(len(c)):
    cstrich[i]= c[i]


cstrich[0] = -np.sqrt((ap**2-c[1]**2-c[2]**2))

#print(ap**2-c[2]**2-c[1]**2)
#print(c[0]**2)

##Jetzt wird die Verzweiflung erst richtig beginnen
c1square = (absa**2)*absb**2-skalarprodukt(a,b)**2-c2square-c3square
#BIS HIERHIN TEST ERFOLGREICH

print('testing first c1 = ' + str(c1))
print('real c1 = '  + str(c[0]))
print()

c2byc3 = (b1*a3-b3*a1)/(a1*b2-a2*b1)

k1 = -a1/(a3+a2*c2byc3)
c3 = c1*k1

k2 = (-a1*c2byc3)/(a3+a2*(c2byc3))
c2 = c1*k2

c1square = ((absa**2)*absb**2-skalarprodukt(a,b)**2)/(1+k1**2+k2**2)

print('testing important c1square = ' + str(c1square))
print('real c1square = '  + str(c[0]**2))
print()

print('testing c3 = ' + str(c3))
print('real c3 = '  + str(c[2]))
print()


print('testing c2 = ' + str(c2))
print('real c2 = '  + str(c[1]))
print()


print('test 1 c1**2 = ' +str(c1square))
print('real c1**2 = ' + str(c1**2))



crazy = 0



#print('apparently c1**2 = ' + str(crazy))
#print('real c1**2 = ' + str(c[0]**2))
#
#print(skalarprodukt(a,c))
##print(skalarprodukt(a,cstrich))