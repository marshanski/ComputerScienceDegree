__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: This class is containing all the procedures in the kmeans algorithem process
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import numpy as np
from CentersFunc import *
from pca         import *
'''-----------------------------------------constants------------------------------------------'''
PIXLE          =  28
IMAGE_SIZE     =  28*28
'''--------------------------------------------------------------------------------------------'''
def KMeansAlgorithem(k,x):
    '''This procedure is calculate the clusters, centeroids and cluster index throw Kmeans algorithem'''
    newCenteroids    = GenerateMeanCenters(x,k)
    centeroids       = np.zeros((IMAGE_SIZE, k))
    converged        = False
    while  not converged :
        clusters,clusterIndex = calculateCluster(x,newCenteroids,k)
        centeroids            = newCenteroids
        newCenteroids         = calculateCenteroids(clusters,newCenteroids,k)
        converged             = np.array_equal(newCenteroids, centeroids)

    return newCenteroids,clusters,clusterIndex


def calculateCluster(x, centroids,k):
    '''The procedure splite the photos matrix into a clusters'''
    clusters      = dict((a, []) for a in range(k))#saving the clusters in a dictionary, the values are categorized by each cluster min distance.
    clustersIndex = dict((a, []) for a in range(k))#saving the clusters in a dictionary, the values are  the indexes categorized by each cluster min distance.

    for photo in range(len(x[0])):
        distanceFromCenter = []
        for i in range(k):

            distanceFromCenter.append(np.sqrt(np.sum((x[:, photo] - centroids[:, i]) ** 2, axis=0)))

        clusters[distanceFromCenter.index(min(distanceFromCenter))].append(x[:, photo])
        clustersIndex[distanceFromCenter.index(min(distanceFromCenter))].append(photo)
    return clusters,clustersIndex

def calculateCenteroids(cluster,centeroids,k):
    '''The procedure is updating the values of the centroids'''
    centerMeans = []
    for i in range(k):
        if cluster[i] == []:
            centerMeans.append(list(centeroids[:, i]))#in a case that a cluster is empty,we don't update his value
        else:
            sigmaCluster = np.zeros(IMAGE_SIZE)#initalize the new centeroids
            lenCluster   = len(cluster[i])
            for photo in cluster[i]:
                sigmaCluster += photo / lenCluster#start to sigma the new centeroids
            centerMeans.append(list(sigmaCluster))

    return np.array(centerMeans).transpose()


def clusterToLable(trainLabels,clusterIndexs,k):
    '''The procedure is translating her cluster indexs into a lables translation '''
    clusterLabels       = dict((a, []) for a in range(k))
    clusterCommonLabels = dict((a,DEFULTINDEX ) for a in range(10))
    for i in clusterIndexs:
        for n in clusterIndexs[i]:
            clusterLabels[i].append(trainLabels[n])
        if clusterLabels[i] != []:
            clusterCommonLabels[i] = max(clusterLabels[i], key=clusterLabels[i].count)
    return clusterCommonLabels

def estimatePercentage(testLabels,centeroids,testPhotos,clusterCommonLabels,k,p):
    '''The procedure calculates her precentage of success '''
    reductionX    = PCA(testPhotos,p)
    mone          = 0
    for photo in range(len(testPhotos[0])):
        distanceFromCenter = []
        for i in range(k):
            distanceFromCenter.append(np.sqrt(np.sum((reductionX[:, photo] - centeroids[:, i]) ** 2, axis=0)))

        if clusterCommonLabels[distanceFromCenter.index(min(distanceFromCenter))] == testLabels[photo]:
            mone+=1

    return (mone/len(testLabels))*100


def KMeansAlgorithemMeanCluster(k,x,centeroids):
    '''This procedure is calculate the clusters, centeroids and cluster index throw Kmeans algorithem
       but now she gets the centeroids'''
    newCenteroids    = centeroids
    centeroids       = np.zeros((IMAGE_SIZE, k))
    converged        = False
    while  not converged:
        clusters,clusterIndex = calculateCluster(x,newCenteroids,k)
        centeroids            = newCenteroids
        newCenteroids         = calculateCenteroids(clusters,newCenteroids,k)
        converged             = np.array_equal(newCenteroids, centeroids)

    return newCenteroids,clusters,clusterIndex


