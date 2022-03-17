__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: This class is response on generate a random centers
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import numpy as np
import random
'''-----------------------------------------constants------------------------------------------'''
MIN_RANDOM     =  -0.5
PROTOCALRANDOM =  1
PROTOCALMEAN   =  2
MAX_RANDOM     =  0.5
PIXLE          =  28
IMAGE_SIZE     =  28*28
DEFULTINDEX    =  -1
MEANSIZE       =  10
'''--------------------------------------------------------------------------------------------'''
def GenerateMeanCenters(x,k):
    '''The procedure generate means center throw the photos matrix'''
    centerMeans = []
    for i in range(k):
        centerMeans.append(list(x[:, random.randint(0, IMAGE_SIZE - 1)]))
    return np.array(centerMeans).transpose()

def GenerateMeanCentersFromLabels(trainlabels,x,k):
    '''The procedure generate means center throw the cluster lable'''
    centerMeans = []
    clusters     = dict((a, np.zeros(IMAGE_SIZE)) for a in range(k))#dictionary's values is a vector of each cluster group
    clustersSize = dict((a, 0) for a in range(k))                   #dictionary's values contains the sizes of each vector that we already calculate
    index        = 0
    while not checkClusterSize(clustersSize,MEANSIZE):
        if clustersSize[trainlabels[index]] < MEANSIZE:
            clusters[trainlabels[index]]     += x[:,index]/MEANSIZE
            clustersSize[trainlabels[index]] +=1
        index +=1
    for i in range(k):
        centerMeans.append(clusters[i])
    return np.array(centerMeans).transpose()


def checkClusterSize(clusterSize,k):
    '''The procedure is under the GenerateMeanCentersFromLabels. And she
       checks if every dictionary isn't full'''
    for i in range(len(clusterSize)):
        if clusterSize[i]<MEANSIZE:
            return False
    return True


