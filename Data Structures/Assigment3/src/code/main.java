package code;
import code.BTree;
import code.BacktrackingBTree;

import java.lang.management.ThreadInfo;
import java.time.chrono.ThaiBuddhistChronology;

import code.AVLTree;
import code.BacktrackingAVL;

public class main 
{
    public static void main(String[] args) 
    {
        //BTree<Integer> tree = new BTree<>(2);
        BacktrackingBTree  tree = new BacktrackingBTree(); 
        //input: {220, 395, 434, -228, 171} 
    // {-380, 435, 155, 471, -326}
        int []a = {-135, 111, -56, 72, 484, 29, -273, -302, -30, 416, 336, -181, 101, 129, 243};
        for (int i=0;i<15;i++)
        {

            if(i==14)
                System.out.println(tree.toString());
            tree.insert(a[i]);

        }
        System.out.println("_________________________");
        //System.out.println(tree.toString());
        tree.Backtrack();
        System.out.println(tree.toString());
    




        



    }
    
}
