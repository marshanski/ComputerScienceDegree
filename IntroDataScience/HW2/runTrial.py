__author__ = ' Tom Or, Raz Marshanski'

import numpy as np

'''--------------------------------------------------------------------------------------------
Description: 
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
from Kmeans          import *
from scipy           import linalg
from pca             import *
from presentData     import *
'''-----------------------------------------constants------------------------------------------'''
TRIALS = 3
'''--------------------------------------------------------------------------------------------'''

def runTrial(reductionX,trainLabels,testLabels,testPhotos,p):
    '''The procedure is manging all the procedure in oreder to create clusters and centers throw Kmeans
       algorithem process, later she translate the clusters into a labels. in the end she check herself precentage of success'''
    print("Dimension " + str(p))
    for i in range(TRIALS):
        centeroids, cluster, clustersIndex = KMeansAlgorithem(K,reductionX)
        clusterLabels                      = clusterToLable(trainLabels, clustersIndex, K)
        print("Check number: " + str(i + 1) + '\n' + "Cluster To Lable: " + str(clusterLabels)+
              '\n'+ "Precentage      : " + str(estimatePercentage(testLabels, centeroids, testPhotos, clusterLabels, K, p)))


def runTrialMeanCluster(reductionX,trainLabels,testLabels,testPhotos,p):
    '''The procedure is manging all the procedure in oreder to create clusters and centers throw Kmeans
       algorithem process, later she translate the clusters into a labels. in the end she check herself precentage of success'''
    print("Mean Cluster Trial:")
    centroids                          = GenerateMeanCentersFromLabels(trainLabels,reductionX,K)
    centeroids, cluster, clustersIndex = KMeansAlgorithemMeanCluster(K,reductionX,centroids)
    clusterLabels                      = clusterToLable(trainLabels, clustersIndex, K)
    print("Check Mean cluster: " +  '\n' + "Cluster To Lable  : " + str(clusterLabels) +
          '\n' + "Precentage        : " + str( estimatePercentage(testLabels, centeroids, testPhotos, clusterLabels, K, p)))

