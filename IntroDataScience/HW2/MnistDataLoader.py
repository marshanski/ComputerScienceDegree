__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: This is a given class. give us the ability to open images.
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import matplotlib.pyplot as plt
import numpy as np
import struct
import random
from array import array
from os.path import join
'''-----------------------------------------constants------------------------------------------'''
'''--------------------------------------------------------------------------------------------'''
# MNIST Data Loader Class
class MnistDataloader(object):
    def __init__(self, training_images_filepath, training_labels_filepath,
                 test_images_filepath, test_labels_filepath):
        self.training_images_filepath = training_images_filepath
        self.training_labels_filepath = training_labels_filepath
        self.test_images_filepath = test_images_filepath
        self.test_labels_filepath = test_labels_filepath

    def read_images_labels(self, images_filepath, labels_filepath):
        labels = []
        with open(labels_filepath, 'rb') as file:
            magic, size = struct.unpack(">II", file.read(8))
            labels = array("B", file.read())
        images = []
        with open(images_filepath, 'rb') as file:
            # read images into columns
            images = np.frombuffer(file.read(), np.uint8, offset=16).reshape(-1, 28*28).transpose()
        return images, labels

    def load_data(self):
        x_train, y_train = self.read_images_labels(self.training_images_filepath, self.training_labels_filepath)
        x_test, y_test = self.read_images_labels(self.test_images_filepath, self.test_labels_filepath)
        return (x_train, y_train), (x_test, y_test)

