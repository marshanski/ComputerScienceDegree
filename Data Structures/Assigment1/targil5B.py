import math
from operator import le


def findIndex(A,x):
    index    = 0 
    powIndex = 0
    while (index < len(A) and x >= A[index]):
        if(A[index] == x):
            return index
        else:
            powIndex +=1 
            index    += int(math.pow(2,powIndex))


    lowBound = index - int(math.pow(2,powIndex))
    if (index > len(A)):
        upBound = len(A) - 1 
    else:
        upBound = index
    return binarySearch(A,lowBound,upBound,x)


def binarySearch(arr, low, high, x):
 
    # Check base case
    if high >= low:
 
        mid = (high + low) // 2
        if arr[mid] == x:
            return mid
        elif arr[mid] > x:
            return binarySearch(arr, low, mid - 1, x)
        else:
            return binarySearch(arr, mid + 1, high, x)
    else:
        return -1





A = [1,3,4,5,6,7,8,7,12,15]

print (findIndex(A,1))
