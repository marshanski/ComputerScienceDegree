__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description:This class is running a pca on a given matrix according to the wanted rank.
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import numpy as np
from   scipy           import linalg
from   showImages      import *
'''-----------------------------------------constants------------------------------------------'''
P               = 20
K               = 10
'''--------------------------------------------------------------------------------------------'''
def PCA(x,p):
    '''The procedure redction data from the matrix throw pca process'''
    '''creating a coveriance matrix of my photos in order to find my egen values'''
    cov_x       = x @ x.transpose()
    cov_x       = (1/x.shape[1])*cov_x

    '''reducing the x's dimension using SVD'''
    U, S, UT    = linalg.svd(cov_x)
    S           = np.sqrt(S) # In the method we used to svd the matrix, the eigenvalues and the eigenvectors comes in descending order
    UP          = U[:,:p]

    '''reversing my photos matrix after reducion the dimension of the data'''
    W           =  UP.T @ x
    reduictionX =  UP   @ W

    return reduictionX

