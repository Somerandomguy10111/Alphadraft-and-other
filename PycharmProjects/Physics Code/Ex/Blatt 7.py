import numpy as np
import matplotlib as plt


def reducedcapacity(c1,c2):
    reducedc = c1*c2/(c1+c2)
    return reducedc

c1 = 1
c2 = 2
c3 = 3
qges = 24

q1 = qges/(1+reducedcapacity(c2,c3)/c1)
q2 = qges-q1
q3 = q2

u1 = q1/c1
u2 = q2/c2
u3 = q3/c3

u23 = u2+u3


print("q1 = " + str(q1))
print("q2 = " + str(q2))
print("q3 = " + str(q3))

print("u1 = " + str(u1))
print("u2 = " + str(u2))
print("u3 = " + str(u3))

print("uges = " + str(u1+u23))