package code;
import java.util.NoSuchElementException;


public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[]    arr;
    private int      length;
    private static final int NOTFOUND       = -1;
    private static final int NEVERPERFORMED =  0;
    private static final int INSERT         =  1;
    private static final int DELETE         =  2;
    
  

//--------------------CONSTRUCTORS--------------------//
    public BacktrackingArray(Stack stack, int size) 
    {
        this.stack  = stack;
        arr         = new int[size];
        this.length = 0;
        this.stack.push(NEVERPERFORMED);
    }

//--------------------GET--------------------//	
    @Override
    public Integer get(int index)
    /*
    Parameters : index
    return     : The procedure return the index's value in the array
    */
    {
        if(index < this.length)//incase the index is bigger then the numbeer of values in the array
            return arr[index];
        else
            throw new ArrayIndexOutOfBoundsException();

    }
//--------------------SEARCH--------------------//	
    @Override
    public Integer search(int k)
    /*
    Parameters : k value
    return     : The procedure return the index of the value in the array, in case the key isn't in the array we will return -1
    */
    {
        for (int i=0 ; i<this.length; i++)
        {
            if(arr[i] == k)
                return i;
        }
        return NOTFOUND;//incase the number doesn't exist in the array

    }
//--------------------INSERT--------------------//
    @Override
    public void insert(Integer x)
    /*
    Parameters  : x value
    Description : The procedure insert a value into the array.
    */
    {
        arr[this.length] = x;//add the integer in the last available place in the array
        this.length++;//update the length size adter adding a number
        this.stack.push(INSERT);//pushing the insert protocol in order to be ready to backtrack call
    }

    public void insertWithoutStack(Integer x)
    /*
    Parameters  : x value
    Description : The procedure insert a value into the array, without updating the stack.
    */
    {
        arr[this.length] = x;//add the integer in the last available place in the array
        this.length++;//update the length size adter adding a number
    }
    public void insertAfterDelete(int index)
    /*
    Parameters : index
    Description: The procedure will insert the value after he was deleted
    */
    {
        if(this.length == 0 || index+1 == this.length)//incase we need to number in the end of the array
            this.insertWithoutStack((int)this.stack.pop());
        else
        {
            int temp   = arr[index],value = temp;//create a temp variable in oreder to exchange values
            arr[index] = (int)this.stack.pop();// update the target index to his last value
            for(int i= index+1 ;i<this.length; i++)//shifiting all the array right
            {
                value  = arr[i];
                arr[i] = temp;
                temp   = value;
            }
            if(index < this.length)//prevent a Placemention of value bigger then length in the last value of the array.
                arr[this.length] = value;            
        }
        this.length++;//update the length size adter adding a number

    }

//--------------------DELETE--------------------//
    public void deleteWithoutStack(Integer index) 
    /*
    Parameters  : index 
    Description : The procedure delete an index, and them update the array, without updating the stack.
    */
    {
        if(index > 0 && index < this.length && this.length > 0) //check the array compatible with shifting
        {
            for(int i=index; i<this.length; i++)
            {
                arr[i] = arr[i+1];
            }
            this.length--;//update the length size adter deleting a number
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
        if(index >=0 && index < this.length && this.length > 0) //check the array compatible with shifting
        {
            this.stack.push(arr[index]);//pushing the value that was deleted in order to prepare for a backtrack call
            this.stack.push(index);//pushing the index that was deleted in order to prepare for a backtrack call
            this.stack.push(DELETE);//pushing the insert protocol in order to prepare for a backtrack call
            for(int i=index; i<this.length; i++)
            {
                arr[i] = arr[i+1];
            }
            this.length--;//update the length size adter deleting a number
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void deleteLast()
    /*
    Parameters : index
    Description: The procedure will delete the last number from the array 
    */
    {
        arr[this.length-1] = 0;//deletes the last number in the array
        this.length --;//update the length size adter deleting a number

    }
//--------------------MINIMUM--------------------//
    @Override
    public Integer minimum() 
    /*
    Parameters : k value
    return     : The procedure return the index of the minumum in the array
    */
    {
        if(this.length>0) 
        {
            int minIndex =0, min=arr[0];//set the min and minindex
            for (int i=1 ; i<this.length; i++)
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
            throw new ArrayIndexOutOfBoundsException();//in case the array is empty
    }
//--------------------MAXIMUM--------------------//
    @Override
    public Integer maximum() 
    /*
    Parameters : k value
    return     : The procedure return the index of the maximum in the array
    */
    {
        if(this.length>0) 
        {
            int maxIndex =0, max=arr[0];//set the max and maxindex
            for (int i=1 ; i<this.length; i++)
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
            throw new ArrayIndexOutOfBoundsException();//in case the array is empty
    }
//--------------------SUCCESSOR--------------------//
    @Override
    public Integer successor(Integer index) 
    /*
    Parameters : index
    return     : The procedure return the successor of the index
    */
    {
        if(index+1 < this.length && index>=0) //check if the index fit to array's bounds
        {
            int min = Integer.MAX_VALUE, minIndex =NOTFOUND;//set the min and minindex
            for (int i=0 ; i<this.length; i++)
            {
                if(arr[i]>arr[index])
                {
                    if(arr[i]<min)//looking for a minimum value above our index's value
                    {
                        min      = arr[i];
                        minIndex = i;
                    }
                }
            }
            if(minIndex == NOTFOUND)//incase the index doesn't have a successor
                throw new NoSuchElementException("node doesn't have a successor");
            else
                return minIndex;
        }
        else
            throw new ArrayIndexOutOfBoundsException(); 
    }
//--------------------PREDECESSOR--------------------//
    @Override
    public Integer predecessor(Integer index) 
    /*
    Parameters : index
    return     : The procedure return the predecessor of the index
    */
    {
        if(index-1 < this.length && index>=0)//check if the index fit to array's bounds
        {
            int max = Integer.MIN_VALUE, maxIndex =NOTFOUND;//set the max and maxindex
            for (int i=0 ; i<this.length; i++)
            {
                if(arr[i] < arr[index])//looking for a maximum value below our index's value
                {
                    if(arr[i]>max)
                    {
                        max      = arr[i];
                        maxIndex = i;
                    }
                }
            }
            if(maxIndex == NOTFOUND)//incase the index doesn't have a predecessor 
                throw new NoSuchElementException("node doesn't have a predecessor");
            else
                return maxIndex;
        }  
        else
            throw new ArrayIndexOutOfBoundsException(); //in case the array is empty

    }
//--------------------BACKTRACK--------------------//
    @Override
    public void backtrack()
    /*
    Parameters : index
    Description: The procedure will undo the last delete/insert 
    */
    {
        int lastAction, index;
        lastAction = (int)this.stack.pop();//pop the last action
        switch(lastAction)
        {
            case INSERT:
                this.deleteLast();
                break;
            case NEVERPERFORMED:
                this.stack.push(NEVERPERFORMED);//incase we called backtrack without a last action in the stack 
                break;
            case DELETE:
                index = (int)this.stack.pop();//pop the relvent index
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
		/////////////////////////////////////
		// Do not implement anything here! //
		/////////////////////////////////////
    }

    @Override
    public void print()
    /*
    Parameters  : 
    Description : The procedure prints the array
    */
    {
        for(int i=0; i<this.length ;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
}
