

public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private Stack reCallStack;
    private int[]    arr;
    private int      length;
    private boolean  reCallOn;
    private static final int NOTFOUND       = -1;
    private static final int NEVERPERFORMED =  0;
    private static final int INSERT         =  1;
    private static final int DELETE         =  2;
    
    // TODO: implement your code here

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) 
    {
        this.stack       = stack;
        this.reCallStack = new Stack();
        this.reCallOn    = false;
        arr              = new int[size];
        length           = 0;
        this.stack.push(NEVERPERFORMED);
    }

    public void restartRecall()
    {
        this.reCallOn = false;
        this.reCallStack.clear();
    }

    @Override
    public Integer get(int index)
    /*
    Parameters : index
    return     : The procedure return the index's value in the array
    */
    {
        if (index < length)
            return arr[index];
        else
            throw new ArrayIndexOutOfBoundsException();

    }

    @Override
    public Integer search(int k)
    /*
    Parameters : k value
    return     : The procedure return the index of the value in the array, in case the key isn't in the array we will return -1
    */
    {
        for (int i=0 ; i<length; i++)
        {
            if(arr[i] == k)
                return i;
        }
        return NOTFOUND;

    }

    @Override
    public void insert(Integer x)
    /*
    Parameters  : x value
    Description : The procedure insert a value into the array.
    */
    {
        this.restartRecall();
        arr[length] = x;
        length++;
        this.stack.push(INSERT);
        
    }

    public void insertWithoutStack(Integer x)
    /*
    Parameters  : x value
    Description : The procedure insert a value into the array.
    */
    {
        arr[length] = x;
        length++;
    }

    public void deleteWithoutStack(Integer index) 
    /*
    Parameters  : index 
    Description : The procedure delete an index, and them update the array.
    */
    {
        if(index > 0 && index < length && length > 0) //check the array compatible with shifting
        {
            for(int i=index; i<length; i++)
            {
                arr[index] = arr[index+1];
            }
            length--;//update the value of the length
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void delete(Integer index) 
    /*
    Parameters  : index 
    Description : The procedure delete an index, and them update the array.
    */
    {
        if(index > 0 && index < length && length > 0) //check the array compatible with shifting
        {
            this.restartRecall();
            this.stack.push(arr[index]);
            this.stack.push(index);
            this.stack.push(DELETE);
            for(int i=index; i<length; i++)
            {
                arr[index] = arr[index+1];
            }
            length--;//update the value of the length
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer minimum() 
    /*
    Parameters : k value
    return     : The procedure return the index of the minumum in the array
    */
    {
        if(length>0) 
        {
            int minIndex =0, min=arr[0];//set the min and minindex
            for (int i=1 ; i<length; i++)
            {
                if(arr[i] < min)//compare every value to the min
                {
                    min      = arr[i];
                    minIndex = i;
                }
            }
            return minIndex;    
        }
        else 
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer maximum() 
    /*
    Parameters : k value
    return     : The procedure return the index of the maximum in the array
    */
    {
        if(length>0) 
        {
            int maxIndex =0, max=arr[0];//set the max and maxindex
            for (int i=1 ; i<length; i++)
            {
                if(arr[i] > max)//comapre every value to the max
                {
                    max      = arr[i];
                    maxIndex = i;
                }
            }
            return maxIndex; 
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer successor(Integer index) 
    /*
    Parameters : index
    return     : The procedure return the successor of the index
    */
    {
        if(index+1 < length && index>=0) 
            return index+1;
        else
            throw new ArrayIndexOutOfBoundsException(); 
    }

    @Override
    public Integer predecessor(Integer index) 
    /*
    Parameters : index
    return     : The procedure return the predecessor of the index
    */
    {

        if(index-1 < length && index>=0)  
            return index-1;
        else
            throw new ArrayIndexOutOfBoundsException(); 
    }

    public void insertAfterDelete(int index)
    /*
    Parameters : index
    Description: The procedure will undo the last delete/insert 
    */
    {
        if(length == 0)
            this.insertWithoutStack((int)this.stack.pop());
        else
        {
            int temp  = arr[index],value = temp;
            arr[index] = (int)this.stack.pop();
            for(int i= index+1 ;i<length; i++)
            {
                value  = arr[i];
                arr[i] = temp;
                temp   = value;
            }
            arr[length] = value;            
        }
        length++;

    }

    public void deleteLast()
    /*
    Parameters : index
    Description: The procedure will undo the last delete/insert 
    */
    {
        arr[length-1] = 0;
        length --;

    }

    @Override
    public void backtrack()
    /*
    Parameters : index
    Description: The procedure will undo the last delete/insert 
    */
    {
        int lastAction, index;
        lastAction = (int)this.stack.pop();
        switch(lastAction)
        {
            case INSERT:
                this.reCallStack.push(arr[length-1]);
                this.reCallStack.push(INSERT);
                this.reCallOn = true;
                this.deleteLast();
                break;
            case NEVERPERFORMED:
                this.stack.push(NEVERPERFORMED);
                break;
            case DELETE:
                index = (int)this.stack.pop();
                this.reCallStack.push(index);
                this.reCallStack.push(DELETE);
                this.reCallOn = true;
                this.insertAfterDelete(index);
                break;
        }
    }

    @Override
    public void retrack()
    /*
    Parameters : index
    Description: The procedure will undo the last delete/insert 
    */
    {
        if(this.reCallOn && !(this.reCallStack.isEmpty()))
        {
            lastAction = (int)this.reCallStack.pop();
            switch(lastAction)
            {
                case INSERT:
                    this.insertWithoutStack((int)this.reCallStack.pop());
                    break;
                case DELETE:
                    this.deleteWithoutStack((int)this.reCallStack.pop());
                    break;
            }
        }
    }

    @Override
    public void print()
    /*
    Parameters  : 
    Description : The procedure prints the array
    */
    {
        for(int i=0; i<length ;i++)
        {
            System.out.print(arr[i]+' ');
        }
    }
    
}
