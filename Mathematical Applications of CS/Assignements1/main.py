__author__ = 'Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Assigments 1 of the course of Mathematical Applications of CS
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import math
import re
import matplotlib.pyplot as plt
import numpy
'''-----------------------------------------constants------------------------------------------'''
'''--------------------------------------------------------------------------------------------'''


def log1(x,n):
    sigma = x-1
    for i in range(2,n):
        sigma += math.pow(-1,i+1) * (math.pow(x-1,i)/i)
        #sigma += ((-1)**(i+1))*((x-1)**i)/i

        
    return sigma



print(log1(4,1000000))