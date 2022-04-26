package code;
import code.BacktrackingArray;
import code.BacktrackingBST.Node;
import code.Stack.*;

public class testArray 
{
    public static void main(String[] args) throws Exception 
    {
        BacktrackingArray a = new BacktrackingArray(new Stack(),15);
        a.insert(1);
        a.insert(2);
        a.insert(3);
        
        //a.insert(4);
        a.delete(0);
        a.delete(1);
        a.backtrack();
        a.backtrack();
        a.print();
        //System.out.println(a.search(4));
        //a.print();
        //a.insert(3);
        //a.print();
        

    }
}
