__author__ = ' Tom Or, Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Description: this class include all the aaditive functions.
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import random
import numpy as np
'''-----------------------------------------constants------------------------------------------'''
FILE        = 'insurData.csv'
SEX_NUM     = {"male" : 0 ,"female" :1 }
SMOKER_NUM  = {'yes' : 1, 'no' : 0}
REGION_NUM  = {'southwest': 1 , 'northwest' : 2 ,'northeast' : 3,'southeast' :4}
SEX         = 'sex'
SMOKER      = 'smoker'
REGION      = 'region'
CHARGES     = 'charges'
AGE         = 'age'
BMI         = 'bmi'
CHILDREN    = 'children'
TRAIN_A     = 'TRAIN_A'
TRAIN_B     = 'TRAIN_B'
TEST_A      = 'TEST_A'
TEST_B      = 'Test_B'
'''--------------------------------------------------------------------------------------------'''

def translate_gender(sex):
    ''' the procedure translate the gender  into numnerical.
    :param sex:
    :return: numerical gender
    '''
    return SEX_NUM[sex]

def translate_smoker(smoker):
    ''' the procedure translate the smoker  into numnerical.
    :param sex:
    :return: numerical smoker
    '''
    return SMOKER_NUM[smoker]

def translate_region(region):
    ''' the procedure translate the region  into numnerical.
    :param sex:
    :return: numerical region
    '''
    return REGION_NUM[region]

def divided_charges(charges):
    ''' the procedure
    :param charges:
    :return: numrical charges
    '''
    return float(charges/1000)


def reduce_meaning(df,category):
    ''' the procedure gets the dataframe calculate the average of the charges and then reduce the meaning
    :param df:
    :return:
    '''
    mean = float(df[category].mean())
    for i in range(df[category].count()):
        df.loc[i, [category]] = df[category][i]/mean

def splite_matrix(df):
    ''' the procedure splite randomally the data frame into two matrix within a relation of 1:5
    :param df:
    :return: a dictionary with 4 matrix: test(A,B) ,trains(a,b)
    '''
    train_index = list(range(df[CHARGES].count()))
    test_matrix_a   = []
    test_matrix_b   = []
    train_matrix_a  = []
    train_matrix_b  = []

    for i in range(0,len(train_index),5):# moving over the datafame with jumping of 5 in order to divided the given df into 4 to 1 seperete dfs.
        index_random = random.choice(train_index)
        test_matrix_a.append(list([df.loc[index_random][AGE],df.loc[index_random][SEX],df.loc[index_random][BMI],df.loc[index_random][CHILDREN],df.loc[index_random][SMOKER],df.loc[index_random][REGION]]))
        test_matrix_b.append(df.loc[index_random][CHARGES])
        train_index.remove(index_random)

    for i in train_index:
        train_matrix_a.append(list([df.loc[i][AGE],df.loc[i][SEX],df.loc[i][BMI],df.loc[i][CHILDREN],df.loc[i][SMOKER],df.loc[i][REGION]]))
        train_matrix_b.append(df.loc[i][CHARGES])

    return {TRAIN_A: np.asarray(train_matrix_a), TRAIN_B: np.asarray(train_matrix_b).transpose(), TEST_A: np.asarray(test_matrix_a), TEST_B: np.asarray(test_matrix_b).transpose()}

def calculate_x(A,B):
    '''the procdure calculate the LS of the two MAtrix
    :param A: A mtarix
    :param B: A vector
    :return:
    '''
    x = np.linalg.inv(np.dot(A.transpose(), A))
    x = np.dot(x, A.transpose())
    x = np.dot(x, B)
    return x

def calculate_argmin(A,B,x):
    ''' The procedure calculate the argmin of the x
    :param A:
    :param B:
    :param x:
    :return: the ar
    '''
    return np.dot(A, x) - B

def calculate_normal(vector):
    ''' the procedure calculates the normal of a given vector
    :param vector:
    :return: the normal
    '''
    normal = 0
    for x in vector:
        normal += x*x
    return normal










