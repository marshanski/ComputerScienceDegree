__author__ = ' Tom Or, Raz Marshanski'



''' new one'''
'''--------------------------------------------------------------------------------------------
Description: 
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import os
import numpy as np
from Kmeans          import *
from MnistDataLoader import *
from scipy           import linalg
from showImages      import *
from pca             import *
from dataReader      import *
from presentData     import *
from runTrial        import *
'''-----------------------------------------constants------------------------------------------'''
PIXEL_RANGE     = 255
REDUCTION_PIXLE = -0.5
PIXLE           = 28
P1              = 20
P2              = 12
K               = 10
'''--------------------------------------------------------------------------------------------'''

'''loading my train dataset'''
(x_train, y_train), (x_test, y_test) = dataLoader()

'''convert the dataset into a matrix'''
testPhotos               = x_test
trainLabels              = list(y_train)
testLabels               = list(y_test)

'''reduce the data from the matrixs' photos'''
x                        = (x_train/PIXEL_RANGE) - REDUCTION_PIXLE
testPhotos               = (testPhotos/PIXEL_RANGE) - REDUCTION_PIXLE

'''Dimension 20'''
reductionX = PCA(x,P1)
presentEigenvalues(x,P1)
presentPhotos(x,reductionX,trainLabels,P1)
runTrial(reductionX,trainLabels,testLabels,testPhotos,P1)

'''Dimension 12'''
reductionX = PCA(x,P2)
runTrial(reductionX,trainLabels,testLabels,testPhotos,P2)

'''Running Kmeans with mean center from the train label'''
runTrialMeanCluster(reductionX,trainLabels,testLabels,testPhotos,P1)








