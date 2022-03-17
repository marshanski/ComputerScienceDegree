__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description:This class is showing the photos that she was asked
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import matplotlib.pyplot as plt
'''-----------------------------------------constants------------------------------------------'''
MIN_RANDOM = -0.5
MAX_RANDOM =  0.5
PIXLE      =  28
IMAGE_SIZE = 28*28
'''--------------------------------------------------------------------------------------------'''
def showImages(images, title_texts):
    '''The procedure is showing the photos'''
    cols = 5
    rows = int(images.shape[1] / cols) + 1
    plt.figure(figsize=(30, 20))
    for i in range(images.shape[1]):
        image = images[:, i].reshape(28, 28)
        title_text = title_texts[i]
        plt.subplot(rows, cols, i + 1)
        plt.imshow(image, cmap=plt.cm.gray)
        if (title_text != ''):
            plt.title(title_text, fontsize=15)
    plt.show()



