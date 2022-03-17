__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: This class is present the eigen values and the photos before and after the reductiom.
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import os
from   MnistDataLoader import *
from   showImages      import *
from   scipy           import linalg
import numpy as np
'''-----------------------------------------constants------------------------------------------'''
P               = 20
K               = 10
PIXEL_RANGE     = 255
REDUCTION_PIXLE = -0.5
'''--------------------------------------------------------------------------------------------'''
def presentEigenvalues(x,p):
    '''The procedure is presentign the eigen values of the cov matrix of the photos'''
    #using the coveriance matrix in order to get U and UT
    cov_x    = x @ x.transpose()
    cov_x    = (1/x.shape[1])*cov_x
    #Doing a svd in order to get the Eigenvalues
    U, S, UT = linalg.svd(cov_x)
    S        = np.sqrt(S)
    #In the method we used to svd the matrix, the eigenvalues and the eigenvectors comes in descending order
    plt.figure()
    plt.title("Eigenvalues", fontsize=15)
    plt.plot(S)
    plt.show()

def presentPhotos(x,reductionX,trainLables,p):
    '''The procedure is presenting the photos after the PCA reduction'''
    RandomIndexes   = random.sample(range(x.shape[1]), K)    #choosing randomly photos to present
    Indexeslabels   = [trainLables[i] for i in RandomIndexes] #translate the index number os them into a real number
    reductionLabels = list("Reduction Photo Value: "+ str(i) for i in Indexeslabels)
    photoLabels     = list("Photo Number Value: " + str(i) for i in Indexeslabels)
    showImages((x[:, RandomIndexes] + REDUCTION_PIXLE ) * PIXEL_RANGE, photoLabels)
    showImages((reductionX[:, RandomIndexes] + REDUCTION_PIXLE ) * PIXEL_RANGE, reductionLabels)


