__author__ = 'Raz Marshanski'
'''--------------------------------------------------------------------------------------------
Assigments 0 of the course of Mathematical Applications of CS
-----------------------------------------------------------------------------------------------'''
'''-----------------------------------------imports--------------------------------------------'''
import math
import matplotlib.pyplot as plt
import numpy
import time
'''-----------------------------------------constants------------------------------------------'''
'''--------------------------------------------------------------------------------------------'''

def fact(n):
    fct =1
    for i in range(n):
        fct *= i+1
    return fct

def numberOfPermutations(n,k):
    return fact(n)//fact(n-k)

def logFact(n):
    logF = 0 
    for i in range(n):
        logF += math.log(i+1)
    return logF

def numberOfPermutationsWithLog(n,k):
    return round(math.exp(logFact(n) - logFact(n-k)))


%time print ("1 ouf of 1 = %d" %numberOfPermutations(1,1))
%time print ("4 ouf of 2 = %d" %numberOfPermutations(4,2))
%time print ("10000 ouf of 10 = %d" %numberOfPermutations(100000,10))

%time print ("1 ouf of 1 = %d" %numberOfPermutationsWithLog(1,1))
%time print ("4 ouf of 2 = %d" %numberOfPermutationsWithLog(4,2))
%time print ("10000 ouf of 10 = %d" %numberOfPermutationsWithLog(100000,10))


print ("elative error of computing the number of permutations for each combination of arguments is: + "+ str(numberOfPermutations(100000,10)-numberOfPermutationsWithLog(100000,10)))

'''Question 2'''
earthquakes = [0.55,29.85,0.35,0.03,0.03,0.05,26.2,11.71,22.40,0.09,125.72,0.01,58.51,0.78,12.54,5.38,183.21,12.20,5572.37,17.23,13.68,845.38,1024.14,765.27,9.06,0.78,59.06,175.66,1570.27,236.5]
years = list(range(1,31)) 
plt.scatter(years,numpy.log(earthquakes))