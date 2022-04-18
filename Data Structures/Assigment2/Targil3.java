public int backtrackingSearch(int[] arr, int x, int foward, int back,Stack mystack)
/*

*/
 {
     int index = 0;
     while (index < arr.lengh)
     {
         for(int i=0; i<=foward-1; i++)
         {
             if(arr[index] == x)
                 return index
             mystack.push(arr[index]);
             index++;
         }
         for(int i=0 ;i<= back; i++)
         {
             mystack.pop()
             index--;
         }
     }
     return -1;
}


public int consistentBinSearch(int []arr, int x, Stack mystack)
/*

*/
{

}


int BinarySearch(int[] a,int x, int left, int right)
{
  if(left>right)
    return -1;

  int middle = (left+right)/2;
  if(a[middle]==x)
    return middle;

  if(x<a[middle])
    return BinarySearch(a,x,left,middle-1);

  return BinarySearch(a,x,middle+1,right);
}