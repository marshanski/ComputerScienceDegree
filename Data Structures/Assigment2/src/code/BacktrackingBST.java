package code;

import java.util.NoSuchElementException;

import javax.xml.transform.Templates;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node>
 {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;
    private boolean  reCallOn;
    private static final int NEVERPERFORMED   =  0;
    private static final int INSERT           =  1;
    private static final int DELETELEAF       =  2;
    private static final int DeleteTwoSONS    =  3;
    private static final int DeletOneSonRight =  4;
    private static final int DeletOneSonLeft  =  5;
    private static final int DELETE           =  6;

//--------------------CONSTRUCTORS--------------------//
    public BacktrackingBST(Stack stack, Stack redoStack) 
    {
        this.stack     = stack;
        this.redoStack = redoStack;
        this.reCallOn  = false;
        this.stack.push(NEVERPERFORMED);
    }

//--------------------RESET--------------------//	
    public void restartRecall()
    /*
    parametrs:
    Description: the procedure restarts the function of the recall. She turn off the boolean recall
    on and clear the stack.
    */
    {
        this.reCallOn = false;
        this.redoStack.clear();
    }
//--------------------GET--------------------//	
    public Node getRoot()
    /*
    Parameters: 
    return    : the root of the tree
    */
    {
    	if (root == null)//check if the root isn't null
        {
    		throw new NoSuchElementException("empty tree has no root");
    	}
        return root;
    }
//--------------------SEARCH--------------------//	
    public Node search(int k)
    /*
    Parameters: int k
    return    : the node of the number.
    */
    {
        return this.search(root,k);
    }

    public Node search(Node root,int k)
    /*
    Parameters: node root,int k
    return    : the node of the number.
    */
    {
        if(root == null)
            return null;
        else
        {
            if(root.getKey()==k)//incase we found the destination's node
                return root;
            else
            {
                if(k > root.getKey())//check which side of the tree should we serch.
                    return this.search(root.right,k);
                else    
                    return this.search(root.left,k);
            }
        }

    }
    public boolean search(Node root,Node node)
    /*
    Parameters: node root,int k
    return    : if the node exist in the tree
    */
    {
        if(root == null)
            return false;
        else
        {
            if(root == node)//incase we found the destination's node
                return true;
            else
            {
                if(node.getKey() > root.getKey())//check which side of the tree should we serch
                    return this.search(root.right,node);
                else    
                    return this.search(root.left,node);
            }
        }

    }
//--------------------INSERT--------------------//
    public void insert(Node node) 
    /*
    parametrs  :Node
    Description: the procedure insert the node in the right place in the bst.
    */
    {
        
        if(root == null)
            root = node;
        else
            this.insert(root,node);

        this.restartRecall();//the recall function could only work after backtrack action.
        this.stack.push(INSERT);//pushing the last action protocol
        this.stack.push(node);//pushing the related node 
        this.stack.push(INSERT);//pushing another int in order to create uniform protocol among all the options of action
    }
    public void insertWithoutStack(Node node) 
    /*
    parametrs  : Node
    Description: the procedure insert the node in the right place in the bst without pushing 
    variables to the stack.
    */
    {
        
        if(root == null)
            root = node;
        else
            this.insert(root,node);
    }

    public void insert(Node root,Node node) 
    /*
    parametrs  :Nodes: root and node
    Description: the procedure insert the node in the right place in the BST.
    */
    {
        
        if(root.getKey() < node.getKey())//check if going right or left
        {
            if(root.right == null)//in case we got the right place to insert
            {
                root.right  = node;//update the relevent node
                node.parent = root;//update the new node
            }
            else
                insert(root.right ,node);//keep looking the relevent node
        }
        else
        {
            if(root.left == null)//in case we got the right place to insert
            {
                root.left   = node;//update the relevent node
                node.parent = root;//update the new node
            }
            else
                insert(root.left,node);//keep looking the relevent node
        }
    }
//--------------------DELETE--------------------//
    public void delete(Node node) 
    /*
    parametrs  :node to be delter
    Description: the procedure deletes the node from the BST.
    */
    {
        
        if(root == null || node ==null )
            return;
        if(this.search(root,node))//check that the node we got exist in the bst
        {
            int deleteProtocol = this.deleteProtocol(node);//check wich delete protocol we going to do
            this.stack.push(node.getKey());//push the value of the node 
            this.stack.push(node);//push the relevant node
            this.delete2(node);//execute the deletion act
            this.stack.push(deleteProtocol);//push the delete protocol 
            this.restartRecall();//the recall function could only work after backtrack action.
            
        }
    }

    public void deleteWithoutStack(Node node) 
    /*
    parametrs  : node
    Description: the procedure deletes the node from the BST, without updating the stack.
    */
    {
        if(root == null || node ==null )
            return;
        if(this.search(root,node))
        {
            this.delete2(node);           
        }
    }

    public void delete2(Node node)
    /*
    parametrs  :node to be delter
    Description: the procedure deletes the node from the BST.
    */
    {
        if(node == null)
        {
            return;
        }
        //case 1: the node is leaf
        if(node.left == null && node.right == null)
        {
            if(node == node.parent.left)//check if the leaf is right or left son
                node.parent.left = null;//delete the leaf 
            else
                node.parent.right = null;//delete the leaf 
        }
        //Case 2: the node has one son
        else if(node.left == null || node.right == null)
        {
            Node parent = node.parent;
            if(node.getKey() <= parent.getKey())//check if the node has son from left or right
            {
                //one son from left
                if (node.left == null)
                    parent.left = node.right;//delete the right son;
                else
                    //one son from left
                    parent.left = node.left;//delete the left son;
            }
            else
            {
                //one son from right
                if (node.left == null)
                    parent.right = node.right;//delete the right son;
                else
                    parent.right = node.left;//delete the left son;
            }
        }
        else
        {
            // case 3: the node has two sons
            Node successor     = this.successor(node);//get the successor of the node
            int y              = successor.getKey(); //saving the successor key
            this.delete2(successor);//deletes the successor from the bst
            node.key           = y; //update the node key to be the successor key
        } 

    }

    public int deleteProtocol(Node node)
    /*
    parmeter: node
    return  : the appopriate delete protocol of the node's deletion. If he has two sons, or if he is a leaf etc'.
    */
    {
        // case 1: the node is leaf
        if(node.left == null && node.right == null)
            return DELETELEAF;
        //case 2: the node has one son
        else if(node.left == null || node.right == null)
        {
            if (node.left == null)
                return DeletOneSonRight;
            else
                return DeletOneSonLeft;
        }
        //case 3: the node has two sons
        return DeleteTwoSONS;

    }
//--------------------MINIMUM--------------------//
    public Node minimum()
    /*
    parmeter:
    return  : the procedure return node with the minimum key in the BST;
    */
    {
        if(root == null)
            throw new NoSuchElementException("empty tree has no root");
        else
        {
            Node node = root;
            while(node.left != null)
                node = node.left;
            return node;
        }
    }
    public Node minimum(Node root)
    /*
    parmeter: root 
    return  : the procedure return the node with  minimum key value in the BST Starting from the root she got;
    */
    {
        Node node = root;
        while(node.left != null)
            node = node.left;
        return node;
    }
//--------------------MAXIMUM--------------------//
    public Node maximum()
    /*
    parmeter:
    return  : the procedure return node with the maximum key in the BST;
    */
    {
        if(root == null)
            throw new NoSuchElementException("empty tree has no root");
        else
        {
            Node node = root;
            while(node.right != null)
                node = node.right;
            return node;
        }
    }
    public Node maximum(Node root)
    /*
    parmeter: root 
    return  : the procedure return the node with  minimum key value in the BST Starting from the root she got;
    */
    {
        Node node = root;
        while(node.right != null)
            node = node.right;
        return node;
    }
//--------------------SUCCESSOR--------------------//
    public Node successor(Node node) 
    /*
    parmeter: node
    return  : the procedure return node's successor.
    */
    {
        if(node.right != null)
            return(this.minimum(node.right));//incase the node has more nodes from right

        else
        {
            Node parent = node.parent;
            while(parent!=null && node.getKey() > parent.getKey())//searching his successor by climbing in the BST
                parent = parent.parent;

            if(parent == null )//incase we didn't find a successor
                throw new NoSuchElementException("node doesn't have a successor");
            else
                return parent;
        }
    }
//--------------------PREDECESSOR--------------------//
    public Node predecessor(Node node) 
    /*
    parmeter: node
    return  : the procedure return node's predecessor.
    */
    {
        if(node.left != null)
            return(this.maximum(node.left));//incase the node has more nodes from left

        else
        {
            Node parent = node.parent;
            while(parent!=null && node.getKey() < parent.getKey())//searching his predecessor by climbing in the BST
                parent = parent.parent;

            if(parent == null || node.getKey() == parent.getKey())//incase we didn't find a successor
                throw new NoSuchElementException("node doesn't have a predecessor");
            else
                return parent;
        }
    }
//--------------------BACKTRACK--------------------//
    @Override
    public void backtrack() 
    /*
    parmeter: 
    return  : the procedure is backtracking the last action we did in the BST.
    */
    {
        int lastAction     = (int)this.stack.pop();//pop the last action we did in the BST
        if (lastAction == NEVERPERFORMED)
        {
            this.stack.push(NEVERPERFORMED);//incase we called backtrack without a last action in the stack 
            return;
        }
        Node backTrackNode = (Node)this.stack.pop();//pop the relevent node
        int key            = (int)this.stack.pop(); //pop the key
        switch(lastAction)
        {
            case INSERT:
                this.deleteWithoutStack(backTrackNode); //delete the node that we just insert
                this.reCallOn = true;//turn on the recall switch
                this.redoStack.push(backTrackNode);//push relevent node incase of recall 
                this.redoStack.push(INSERT);//push the protocol incase of recll
                break;
            case DeleteTwoSONS:
                Node newNode        = new Node(backTrackNode.getKey(), 1);//create a new node,because we delete one in our last action
                backTrackNode.key   = key;//update the relevnt key with his old key
                newNode.right       = backTrackNode.right;//connect the new node with the ancestry's of the relevnt node
                backTrackNode.right = newNode;//connect the relevnt node to the new node we made
                this.reCallOn = true;//turn on the recall switch
                this.redoStack.push(backTrackNode);//push relevent node incase of recall 
                this.redoStack.push(DELETE);//push the protocol incase of recll
                break;

            case DELETELEAF:
                this.insertWithoutStack(backTrackNode);//insert the node that we just delet
                this.reCallOn = true;//turn on the recall switch
                this.redoStack.push(backTrackNode);//push relevent node incase of recall
                this.redoStack.push(DELETE);//push the protocol incase of recll
                break;

            case DeletOneSonRight:
                this.reCallOn = true;//turn on the recall switch
                backTrackNode.parent.right = backTrackNode;//insert the node that we just delet
                this.redoStack.push(backTrackNode);//push relevent node incase of recall
                this.redoStack.push(DELETE);//push the protocol incase of recll
                break;

            case DeletOneSonLeft:
                this.reCallOn = true;//turn on the recall switch
                backTrackNode.parent.left = backTrackNode;//insert the node that we just delet
                this.redoStack.push(backTrackNode);//push relevent node incase of recall
                this.redoStack.push(DELETE);//push the protocol incase of recll
                break;
        }

    }
//--------------------RETRACK--------------------//
    @Override
    public void retrack() 
     /*
    parmeter: 
    return  : the procedure is retracking the last backtracking action we did in the BST.
    */   
    {

        if(this.reCallOn && !(this.redoStack.isEmpty()))//cehck that we can execute a retrack
        {
            int lastAction     = (int)this.redoStack.pop();//pop the last action
            Node backTrackNode = (Node)this.redoStack.pop();//pop the relevent node
            switch(lastAction)
            {
                case INSERT:
                    this.insertWithoutStack(backTrackNode);//insert after we delete from the backtrack
                    break;  
                case DELETE:
                    this.deleteWithoutStack(backTrackNode);//delete after we insert from the backtrack
                    break;
            }
        }
    }
//--------------------PRINT--------------------//
    public void printPreOrder()
    /*
    parmeter: 
    return  : the procedure is printing the BST in pre order
    */
    {
        if(root == null)
            System.out.print("Empty tree");
        else
        {
            this.printPreOrder(root);
            System.out.println();
        }
    }

    public void printPreOrder(Node root)
    /*
    parmeter: 
    return  : the procedure is printing the BST in pre order
    */
    {
        if(root == null)
            return;
        System.out.print(root.getKey()+" ");
        this.printPreOrder(root.left);
        this.printPreOrder(root.right);
    }

    @Override
    public void print() {
    	printPreOrder();
    }

//-----------------------------------------------------------------------------------------------------------------------------//
    public static class Node {
    	// These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;
        
        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) 
        {
            this.key   = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
        public Node getParent()
        {
            return parent;
        }
        
    }

}
