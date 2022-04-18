from ctypes import pointer


def mergeSort(alist):
    '''
    The procedure is sorting the array in the common algorithem" Merge sort", in complexity of O(n * LOG (n))
    '''
    if len(alist)>1:
        mid       = len(alist)//2
        lefthalf  = alist[:mid]
        righthalf = alist[mid:]

        mergeSort(lefthalf)
        mergeSort(righthalf)

        i=0
        j=0
        k=0
        while i < len(lefthalf) and j < len(righthalf):
            if lefthalf[i] < righthalf[j]:
                alist[k]=lefthalf[i]
                i=i+1
            else:
                alist[k]=righthalf[j]
                j=j+1
            k=k+1

        while i < len(lefthalf):
            alist[k]=lefthalf[i]
            i=i+1
            k=k+1

        while j < len(righthalf):
            alist[k]=righthalf[j]
            j=j+1
            k=k+1

        return alist

def isTotalDiffrent(A,B):
    '''
    The procedure gets two arryas and tell is they are totally difrrent
    '''
    A        = mergeSort(A)
    B        = mergeSort(B)
    pointerA = 0
    pointerB = 0
    while( pointerA < len(A) and pointerB < len(B)):
        if (A[pointerA] > B[pointerB]):
            pointerB +=1 
        elif (B[pointerB] > A[pointerA]):
            pointerA +=1 
        else:
            return False
    return True

A = [5,2,14,2,2,7,1]
B = [6,9,9,10,13,0,8]

print(isTotalDiffrent(A,B))
