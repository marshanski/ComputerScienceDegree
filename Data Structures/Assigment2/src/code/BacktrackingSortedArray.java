package code;

public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    public int[] arr;
    private int RealLength;
    private static final int NOTFOUND       = -1;
    private static final int NEVERPERFORMED =  0;
    private static final int INSERT         =  1;
    private static final int DELETE         =  2;

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr        = new int[size];
        RealLength = 0;
        this.stack.push(NEVERPERFORMED);
    }
    
    public Integer get(int index)
    //Parameters : index
    //return     : The function returns the indexs' value in the array
    {
        if(RealLength>0 && index < RealLength)
            return arr[index];
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer search(int k)
    //Parameters : k value
    //return     : The procedure return the index of the value in the array, in case the key isn't in the array we will return -1
    {
        if(RealLength>0)
        	return this.binarySearch(0,RealLength-1,k);
        else
            return -1;
    }
    
    private int binarySearch(int left,int right, int key)
    //parameters: left is the left boundry of the search. right is the right boundry of the search.key is the value that we look for in the array.
    // return: the function returns the index of the value we searched for. if the value isn't in the array, the function returns -1.
    {
        if (right < left)
            return NOTFOUND;
        int mid = (left + right) / 2;
        if (key == arr[mid])
            return mid;
        if (key > arr[mid])
            return binarySearch(mid + 1, right, key);
        return binarySearch(left, mid - 1, key);
    }

    @Override
    public void insert(Integer x) 
    {
        //Parameter: int x is a value we want to insert into the sorted array.
        if(RealLength<=arr.length-1)
        {
            int i;
            for(i=RealLength-1;(i>=0 && arr[i] > x); i--)
                arr[i+1] = arr[i];
            
            arr[i+1] = x;
            RealLength++;
            this.stack.push(i+1);//insert value's index into stack.
            this.stack.push(INSERT);//insert the protocol into stack
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }
    
    public void insertWithoutStack(Integer x) 
    {
        //Parameter: int x is a value we want to insert into the sorted array, without using the stack .(this function is for the use of the backtrack function only)
        if(RealLength<=arr.length-1)
        {
            int i;
            for(i=RealLength-1;(i>=0 && arr[i] > x); i--)
                arr[i+1] = arr[i];
            
            arr[i+1] = x;
            RealLength ++;
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void delete(Integer index) 
    {
        //this function deletes the value in the index 'index' from the array.
        if(index >= 0 && index < RealLength && RealLength > 0) //check if the array compatible with shifting
        {
            this.stack.push(arr[index]);//insert the value that wwill be deleted into the stack.
            for (int i = index; i < RealLength; i++)
                arr[i] = arr[i + 1];
            RealLength --;
            this.stack.push(DELETE);//insert the protocol into the stack
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }
    
    public void deleteWithoutStack(Integer index) 
    {
        //this function deletes the value in index 'index' from the array, without using the stack .(this function is for the use of the backtrack function only)
        if(index >= 0 && index < RealLength && RealLength > 0) //check if the array compatible with shifting
        {
            for (int i = index; i < RealLength; i++)
                arr[i] = arr[i + 1];
            RealLength --;
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer minimum() 
    //this function returns the minimum value in the array.
    {
        if(RealLength > 0)
        	return 0;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer maximum() 
    //this function returns the maximum value in the array.
    {
        if(RealLength > 0)
            return RealLength-1;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer successor(Integer index) 
    //this function returns the successor of the index it is given.
    {
        if(RealLength>0 && index < RealLength -1 && index >=0)
            return index+1;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer predecessor(Integer index) 
    //this function returns the predecessor of the index it is given.
    {
        if(RealLength>0 && index>=1 && index < RealLength)
            return index-1;
        else 
            throw new ArrayIndexOutOfBoundsException(); 
    }

    @Override
    public void backtrack() 
    {
        int lastAction, index, val;
        lastAction = (int)this.stack.pop();
        switch(lastAction)
        {
            case INSERT:
                index = (int)this.stack.pop();
                this.deleteWithoutStack(index);
                break;
            case DELETE:
                val=(int)this.stack.pop();
                this.insertWithoutStack(val);
                break;
            case NEVERPERFORMED:
                this.stack.push(NEVERPERFORMED);
                break;
        }
    }

    @Override
    public void retrack() {
		/////////////////////////////////////
		// Do not implement anything here! //
		/////////////////////////////////////
    }

    @Override
    public void print() 
    // this function prints the array.
    {
        for(int i=0; i<RealLength ;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
}
