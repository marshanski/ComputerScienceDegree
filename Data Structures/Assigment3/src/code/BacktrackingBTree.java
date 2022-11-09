package code;
//import java.util.Stack;
import java.util.*;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> 
{
	private static final int NEVERPERFORMED    =  0;
    private static final int ROOT              =  3;
    private static final int INSERT            =  1;
    private static final int SPLIT             =  2;
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() 
	{
		super();

	}
	public BacktrackingBTree(int order) 
	{
		super();

	}
	public void spliteCase(Deque<T> splitedT)
	{
		T first           = splitedT.pop(),second=first;
		int index;
		Node<T> firstNode = this.getNode(first),secondNode=firstNode,left=firstNode,right;
		while(!(splitedT.isEmpty()))
		{
			
			second     = splitedT.pop();
			secondNode = firstNode.parent;
			while(secondNode.indexOf(second)==-1)
				secondNode = secondNode.parent;
			
			index      = secondNode.indexOf(second);
			left       = secondNode.getChild(index);
			right      = secondNode.getChild(index+1);
			firstNode.removeKey(first);
			left.addKey(second);
			//merge
			for (int i=0; i< right.getNumberOfKeys();i++)
			{
				left.addKey(right.keys[i]);
				if(right.getNumberOfChildren()>0)
					left.addChild(right.getChild(i));
			}
			if(right.getNumberOfChildren()>0)
				left.addChild(right.getChild(right.getNumberOfKeys()));
			secondNode.removeKey(second);
			secondNode.removeChild(right);
			first     = second;
			firstNode = secondNode;
		}
		if(secondNode.numOfKeys ==0)
		{
			root = left;
			left.parent = null;
		}
		if(left.parent == null)
		{
			root        = left; 
			left.parent = null;
		}
			

	}



	//You are to implement the function Backtrack.
	public void Backtrack() 
	{
		if(this.root!=null)
		{
			int order = (int)backtrackStack.pop();
			switch (order)
			{
				case NEVERPERFORMED:
					backtrackStack.push(NEVERPERFORMED);
					break;
				case ROOT:
					root = null;
					break;
				case INSERT:
					T temp = (T)backtrackStack.pop();
					this.getNode(temp).removeKey(temp);
					break;
				case SPLIT:
					this.spliteCase((Deque<T>)backtrackStack.pop());
					break;
					
			}
		}
		else
		{
			//throw new RuntimeException("Emprty tree");
			
		}
			
    }
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample()
	{
		//List<Integer> list = Arrays.asList(10,20,30,40);
		List<Integer> list = new LinkedList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);

		return list;
	}
}
