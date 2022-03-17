__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description:This class is loader the data from the files into a matrix
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import os
from MnistDataLoader import *
'''-----------------------------------------constants------------------------------------------'''
'''--------------------------------------------------------------------------------------------'''
def dataLoader():
    '''The procedure is charging on reading the data from the file'''
    trainingImagesFilepath = join(os.getcwd(), 'train-images.idx3-ubyte')
    trainingLabelsFilepath = join(os.getcwd(), 'train-labels.idx1-ubyte')
    testImagesFilepath     = join(os.getcwd(), 't10k-images.idx3-ubyte')
    testLabelsFilepath     = join(os.getcwd(), 't10k-labels.idx1-ubyte')
    mnistDataLoader        = MnistDataloader(trainingImagesFilepath, trainingLabelsFilepath, testImagesFilepath,testLabelsFilepath)
    return mnistDataLoader.load_data()




