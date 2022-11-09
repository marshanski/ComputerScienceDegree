package code;
import java.util.List;
import java.util.*;




public class BacktrackingAVL extends AVLTree 
{
    // For clarity only, this is the default ctor created implicitly.
    private static final int NEVERPERFORMED    =  0;
    private static final int INSERT            =  1;
    private static final int ROOT              =  2;
    private static final int RR                =  20;
    private static final int RL                =  21;
    private static final int LR                =  11;
    private static final int LL                =  10;
    public BacktrackingAVL() 
    {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() 
	{
		if(this.root!=null)
		{
			int order = (int)backtrackStack.pop(),nodeToDelete;
            Node destNode,firstParent,secondParent,t1;
			switch (order)
			{
				case NEVERPERFORMED:
					backtrackStack.push(NEVERPERFORMED);
					break;

				case INSERT:
                    destNode =  (Node)backtrackStack.pop();
                    
                    if(destNode.parent == null)
                        this.root = null;
                    else 
                        this.delete(this.root, destNode.value);

					break;

                case RR:
                    t1                 =  (Node)backtrackStack.pop();
                    nodeToDelete       =  (int)backtrackStack.pop();
                    firstParent        = t1.parent;
                    destNode           = this.rotateRight(t1);

                    if(firstParent!= null)
                    {
                        if(firstParent.right!= null && firstParent.right.value == t1.value)
                            firstParent.right   = destNode;
                        if(firstParent.left!= null && firstParent.left.value == t1.value)
                            firstParent.left   = destNode;
                        
                    }
                    else
                        this.root            = destNode;
                    this.delete(this.root, nodeToDelete);

                     break;
                     
                case LL:

                    t1                 =  (Node)backtrackStack.pop();
                    nodeToDelete       =  (int)backtrackStack.pop();
                    firstParent        = t1.parent;
                    destNode           = this.rotateLeft(t1);
                    if(firstParent!= null)
                    {
                        if(firstParent.right!= null && firstParent.right.value == t1.value)
                            firstParent.right   = destNode;
                        if(firstParent.left!= null && firstParent.left.value == t1.value)
                            firstParent.left   = destNode;

                    }
                    else
                        this.root            = destNode;

                    this.delete(this.root, nodeToDelete);
                    break;

                case RL:

                    destNode           =  (Node)backtrackStack.pop();
                    nodeToDelete       =  (int)backtrackStack.pop();
                    firstParent        = destNode.parent;
                    secondParent       = this.rotateRight(destNode);
                    t1                 = this.rotateLeft(destNode);
                    secondParent.right = t1;
                    if(firstParent!= null)
                    {
                        if(firstParent.right!= null && firstParent.right.value == destNode.value)
                            firstParent.right   = secondParent;
                        if(firstParent.left!= null && firstParent.left.value == destNode.value)
                            firstParent.left   = secondParent;
                    }
                    else
                        this.root = secondParent;

                    this.delete(this.root, nodeToDelete);
                    break;

                case LR:

                    destNode           =  (Node)backtrackStack.pop();
                    nodeToDelete       =  (int)backtrackStack.pop();
                    firstParent        = destNode.parent;
                    secondParent       = this.rotateLeft(destNode);
                    t1                 = this.rotateRight(destNode);
                    secondParent.left  = t1;
                    if(firstParent!= null)
                    {
                        if(firstParent.right!= null && firstParent.right.value == destNode.value)
                            firstParent.right   = secondParent;
                        if(firstParent.left!= null && firstParent.left.value == destNode.value)
                            firstParent.left   = secondParent;
                    }
                    else
                    {
                        this.root             = secondParent;
    
                    }
                    this.delete(this.root, nodeToDelete);
                    break;
            }
        }
        else
		{
			//throw new RuntimeException("Emprty tree");
			
		}
	
    }

    public void updateHeightAfterBacktrack(Node n) 
    {
        if(n == null)
            return;
        else
        {
            n.updateHeight();
            this.updateHeightAfterBacktrack(n.parent);
        }        
    }


 

    public void delete(Node n,int value) 
    {
        
        if(n.right!= null && n.right.value == value)
        {
            n.right =null;
            this.updateHeightAfterBacktrack(n);
            return;
        }
        if(n.left!= null && n.left.value == value)
        {
            n.left = null;
            this.updateHeightAfterBacktrack(n);
            return;
        }

        if(n.value> value)
            delete(n.left, value);
        else
            delete(n.right, value);
    }

    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() 
    {
        List<Integer> list = new LinkedList<Integer>();
        list.add(5);
        list.add(6);
        list.add(7);
        return list;

    }
    
    public int Select(int index)
    {
        if(this.root !=null)
            return this.Select(index,this.root);
        return 0;

    }
    public int Select(int index,AVLTree.Node node)
    {
        int currentRank;
        if(node.left == null)
            currentRank =1;
        else
            currentRank = node.left.size +1;
        if(currentRank == index)
            return node.value;
        else if (index<currentRank)
            return Select(index,node.left);
        else 
            return Select(index-currentRank, node.right);

    }
    
    public int Rank(int value) 
    {
        if(this.root !=null )
            return this.Rank(value,this.root);
        else
            return 0;

    }
    public int Rank(int value,AVLTree.Node node) 
    {
        if(node == null)
            return 0;

        if(node.value == value)
            if(node.left ==null)
                return 0;
            else
                return node.left.size;


        else if(node.value > value)
            return Rank(value,node.left);
        else
        {
            if(node.left == null)
                return Rank(value,node.right) +1;
            else
                return Rank(value,node.right) +node.left.size+1;

        }
            
    }
    public int size(int val)
    {
        return size(val,this.root);
    }
    public int size(int val,AVLTree.Node node)
    {
        if(node.value == val)
            return node.size;
        if(node.value > val)
            return size(val,node.left);
        else 
            return size(val,node.right);
    }
}
