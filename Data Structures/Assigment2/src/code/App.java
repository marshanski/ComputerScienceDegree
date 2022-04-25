package code;
import code.BacktrackingBST;
import code.BacktrackingBST.Node;
import code.Stack.*;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        BacktrackingBST a = new BacktrackingBST(new Stack(),new Stack());
        Node f1  = new Node(1,1);
        Node f12 = new Node(1,1);
        Node f2  = new Node(2,2);
        Node f3  = new Node(3,3);
        Node f4  = new Node(4,4);
        Node f5  = new Node(5,5);
        Node f6  = new Node(6,6);
        Node f7  = new Node(7,7);
        Node f72 = new Node(7,7);
        Node f8  = new Node(8,8);
        Node f9  = new Node(9,9);
        Node f10 = new Node(10,10);
        Node f42 = new Node(4,4);

        a.insert(f5);
        a.insert(f7);
        a.insert(f9);
        a.insert(f3);
        a.insert(f4);
        a.insert(f1);
        //a.insert(f2);
        a.printPreOrder();
        a.delete(f3);
        a.printPreOrder();
        
        

        

    }
}
