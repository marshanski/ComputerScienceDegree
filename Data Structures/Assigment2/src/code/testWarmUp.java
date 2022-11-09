package code;
import code.Stack.*;
import code.Warmup.*;

public class testWarmUp
{
    public static void main(String[] args) throws Exception 
    {
        int [] a =  {1,1,2,7,15,16,23,99,100,100,100,130,193,196,197};

        Stack s  = new Stack();
        System.out.println(Warmup.consistentBinSearch(a,15,s));

    }
    
}
