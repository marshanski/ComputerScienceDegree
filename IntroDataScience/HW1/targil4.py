__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: the class calculates the D matrix of the equetion DA-B
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import numpy as np
from scipy.optimize import fmin
'''-----------------------------------------constants------------------------------------------'''
A           = np.asarray([[5,6,7,8],[1,3,5,4],[1,0.5,4,2],[3,4,3,1]])
B           = np.asarray([[0.57,0.56,0.8,2],[1.5,4,6.7,4.9],[0.2,0.1,1,0.6],[11,30,26,10]])
D           = []
'''--------------------------------------------------------------------------------------------'''


def f1(x):
    result =0
    for j in range(4):
        result +=(A[0][j]*x -B[0][j])**2
    return result

def f2(x):
    result =0
    for j in range(4):
        result +=(A[1][j]*x -B[1][j])**2
    return result

def f3(x):
    result =0
    for j in range(4):
        result +=(A[2][j]*x -B[2][j])**2
    return result

def f4(x):
    result =0
    for j in range(4):
        result +=(A[3][j]*x -B[3][j])**2
    return result


D.append(fmin(f1,np.asarray([0]),disp=0)[0])
D.append(fmin(f2,np.asarray([0]),disp =0)[0])
D.append(fmin(f3,np.asarray([0]),disp=0)[0])
D.append(fmin(f4,np.asarray([0]),disp=0)[0])
print(D)
