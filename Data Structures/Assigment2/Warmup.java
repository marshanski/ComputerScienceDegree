

public class Warmup {
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
            for(int i=0 ;i<= back && index < arr.length; i++)
            {
                myStack.pop();
                index--;
            }
        }
        return -1;
    }

    public static int consistentBinSearch(int[] arr, int x, Stack myStack) 
    {
        // TODO: implement your code here
    	
    	// Your implementation should contain a this line:
        int left=0, right = arr.length-1;

        BinSearch(arr,x,left,right,myStack);
        
    }


    public static int consistentBinSearch(int[] arr, int x,int left, int right ,Stack myStack) 
    {

    	int inconsistencies = Consistency.isConsistent(arr);
    	int last;
        if (inconsistencies >0)
        {
            last = myStack.pop();
            for (int i =0 ;i<= inconsistencies ;i++)
            {
                if(right < last)
                    right = (last *2)+1;
                else
                    left = (last/2)-1;
                last = myStack.pop();
            }
        }
        BinSearch(arr,x,left,right,myStack);

    }
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


    
    
}
