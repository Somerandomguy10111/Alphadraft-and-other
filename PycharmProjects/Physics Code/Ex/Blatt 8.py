import numpy as np
import matplotlib as plt

def reducedresistance(r1,r2):
    reducedr = r1*r2/(r1+r2)
    return reducedr

R = 5999000

rv = 20000
ra = 0.02

r1 = R+ra
r2 = rv

rb1 = reducedresistance(R,rv)
rb2 = reducedresistance(r1,r2)*(1+r1/r2)

mr1 = (rb1-R)/R
mr2 = (rb2-R)/R

print("rb1 = " + str(rb1))
print("rb2 = " + str(rb2))

