package code;

public class Warmup 
{
    /*
    this method performs a 'backtracking search'. 
    parameters: int[] arr - is an unssorted array,
                int x - is the value we search for in thr array, 
                int forward - is the number of step the method steps forward during the search.
                int back is the number of steps the method steps backwards during the search.
                stack mystack is the stack we use for the backtracking.
    return:     the index of the value x, if not present in the array, returns -1.
    */
    public static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack)
    {
        int index = 0;
        while (index < arr.length)
        {
            for(int i=0; i<=forward-1 && index < arr.length ; i++)
            {
                if(arr[index] == x)
                    return index;
                myStack.push(arr[index]);
                index++;
            }
            for(int i=0 ;i<= back-1 && index < arr.length; i++)
            {
                myStack.pop();
                index--;
            }
        }
        return -1;
    }
    /* this method prefoms a consistent bianry search. meaning a type of binary search which in every itteration the method checks if there were any changes to the original array, and if there were, it would back track accordingly.
    parameters: int []arr - an array of sorted numbers.
                int x - the value we look for in the array.
                stack mystack- a stack of objects used for the back tracking.
    */
    public static int consistentBinSearch(int[] arr, int x, Stack myStack)

    {
    
        int left =0, right = arr.length-1;
        return BinSearch(arr,x,left,right,myStack);
        
    }

    /* this method prefoms a consistent bianry search-
    meaning a type of binary search which in every itteration the method checks if there were any changes to the original array, and if there were, it would back track accordingly.
    parameters: int []arr - an array of sorted numbers.
                int x - the value we look for in the array.
                int left- is the left boundry of the search.
                int right- is the right boundry of the search
                stack mystack- a stack of objects used for the back tracking.
    return:     returns the index of the value x. if x isnt present in the array, returns -1.
    */
    public static int BinSearch(int[] arr, int x,int left, int right ,Stack myStack) 
    {
        if(left>right)
            return -1;

        int middle = (left+right)/2;

        if(arr[middle]== x)
            return middle;
        
        myStack.push(middle);

        if(x<arr[middle])
            return consistentBinSearch(arr,x,left,middle-1,myStack);

        return consistentBinSearch(arr,x,middle+1,right,myStack);
    }

    /* this method checks if there were any changes to the original array, and if there were, it would back track accordingly using the stack.
    parameters: int []arr - an array of sorted numbers.
                int x - the value we look for in the array.
                int left- is the left boundry of the search.
                int right- is the right boundry of the search
                stack mystack- a stack of objects used for the back tracking.
    */
    public static int consistentBinSearch(int[] arr, int x,int left, int right ,Stack myStack) 
    {

    	int inconsistencies = Consistency.isConsistent(arr);
    	int last;
        if (inconsistencies >0)
        {
            
            for (int i =0 ;i <= inconsistencies ;i++)
            {
                last = (int)myStack.pop();
                if(right < last)
                    right = (last *2)+1;
                else
                    left = (last/2)-1;
            }
        }
        return BinSearch(arr,x,left,right,myStack);

    }
    
    
}
