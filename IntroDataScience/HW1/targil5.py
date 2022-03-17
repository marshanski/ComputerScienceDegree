__author__ = ' Tom Or, Raz Marshanski'

'''--------------------------------------------------------------------------------------------
Description: this class is calculating insurance charging formula 
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
from   additiveFunc  import *
from   matplotlib    import pyplot as plt
import pandas as pd
'''-----------------------------------------constants------------------------------------------'''
FILE        = 'insurData.csv'
SEX         = 'sex'
SMOKER      = 'smoker'
REGION      = 'region'
CHARGES     = 'charges'
BMI         = 'bmi'
TRAIN_A     = 'TRAIN_A'
TRAIN_B     = 'TRAIN_B'
TEST_A      = 'TEST_A'
TEST_B      = 'Test_B'
X_LABEL     = "Participant's Num"
Y_LABEL     = "Error"
TITLE       = 'Error distribution'
EXPERIMENTS = 5
'''--------------------------------------------------------------------------------------------'''

df          = pd.read_csv(FILE)
df[SEX]     = df[SEX].apply(translate_gender)
df[SMOKER]  = df[SMOKER].apply(translate_smoker)
df[REGION]  = df[REGION].apply(translate_region)
df[CHARGES] = df[REGION].apply(divided_charges)
reduce_meaning(df,CHARGES)
reduce_meaning(df,BMI)

ls_errors   =[]
for i in range(EXPERIMENTS):
    experients      = splite_matrix(df)
    x               = calculate_x(experients[TRAIN_A],experients[TRAIN_B])
    r_test          = calculate_argmin(experients[TEST_A],experients[TEST_B],x)
    r_train         = calculate_argmin(experients[TRAIN_A],experients[TRAIN_B],x)
    ls_errors.append(list(r_test))
    print ("The MSE of the train %s is: " %(i+1) + str((np.dot(r_train,r_train))/4))
    print ("The MSE of the test %s is : " %(i+1) + str(np.dot(r_test, r_test)) )


plt.title(TITLE)
plt.xlabel(X_LABEL)
plt.ylabel(Y_LABEL)
plt.plot(ls_errors[0])
plt.show()














