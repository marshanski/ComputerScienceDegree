__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: this class is calculating LS OF two matrix : A,B  IN diffrent versions
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import numpy as np
from additiveFunc import *
'''-----------------------------------------constants------------------------------------------'''
LAMDA       = 0.5
A           = np.asarray([[2,1,2],[1,-2,1],[1,2,3],[1,1,1]])
B           = np.asarray([[6,1,5,2]]).transpose()
A_T         = A.transpose()
W           = np.diag([1,1000,1,1])
Tikhonov    = np.diag([LAMDA ,LAMDA ,LAMDA ])
'''--------------------------------------------------------------------------------------------'''


'''--------------------------------------------------------------------------------------------'''
'''The code is finding the best approximation of the matrix A,B '''
x           = np.linalg.inv(np.dot (A_T, A))
x           = np.dot (x, A_T)
x           = np.dot (x, B)
print("-----------LS----------")
print(x)
r           = np.dot (A, x)
r           = r-B
print(r)
print(str("The normal is: " + str(calculate_normal(r))))

'''---------------------------------------------------------------------------------------------'''
'''---------------------------------------------------------------------------------------------'''
'''The code is finding the best approximation of the matrix A,B but this time using weightes LS '''
x           = np.dot(A_T,W)
x           = np.linalg.inv(np.dot(x,A))
x           = np.dot(x,A_T)
x           = np.dot(x,W)
x           = np.dot(x,B)
print("-----------W-LS---------")
print(x)
y           = np.dot (A, x)
y           = y-B

print(y)
'''---------------------------------------------------------------------------------------------'''
'''---------------------------------------------------------------------------------------------'''
'''The code is finding the best approximation of the matrix A,B but this time using Tikhonov LS '''
x           = np.dot(A_T,A) + Tikhonov
x           = np.linalg.inv(x)
x           = np.dot(x,A_T)
x           = np.dot(x,B)
print("-----------REG-LS---------")
print(x)
'''---------------------------------------------------------------------------------------------'''







