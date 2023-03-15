
// C++ Program for counting sort
#include <bits/stdc++.h>
#include <string.h>
using namespace std;
#define RANGE 255
 

void countSort(char arr[])
{

    char output[strlen(arr)];
 

    int count[RANGE + 1], i;
    memset(count, 0, sizeof(count));
 
    // Store count of each character
    for (i = 0; arr[i]; ++i)
        ++count[arr[i]];
 

    for (i = 1; i <= RANGE; ++i)
        count[i] += count[i - 1];
 
    // Build the output character array
    for (i = 0; arr[i]; ++i) {
        output[count[arr[i]] - 1] = arr[i];
        --count[arr[i]];
    }
 
    /*
    For Stable algorithm
    for (i = sizeof(arr)-1; i>=0; --i)
    {
        output[count[arr[i]]-1] = arr[i];
        --count[arr[i]];
    }
 
    For Logic : See implementation
    */
 
    // Copy the output array to arr, so that arr now
    // contains sorted characters
    for (i = 0; arr[i]; ++i)
        arr[i] = output[i];
}
 
// Driver code
int main()
{
    char arr[] = "geeksforgeeks";
 
    // Function call
    countSort(arr);
 
    cout << "Sorted character array is " << arr;
    return 0;
}
 