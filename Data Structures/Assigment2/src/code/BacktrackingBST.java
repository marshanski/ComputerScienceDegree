package code;

import java.util.NoSuchElementException;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node>
 {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;
    private boolean  reCallOn;
    private static final int NEVERPERFORMED =  0;
    private static final int INSERT         =  1;
    private static final int DELETE         =  2;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack     = stack;
        this.redoStack = redoStack;
        this.reCallOn  = false;
        this.stack.push(NEVERPERFORMED);
        
    }


    public void restartRecall()
    {
        this.reCallOn = false;
        this.redoStack.clear();
    }

    public Node getRoot()
    {
    	if (root == null)
        {
    		throw new NoSuchElementException("empty tree has no root");
    	}
        return root;
    }
	
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
            if(root.getKey()==k)
                return root;
            else
            {
                if(k>root.getKey())
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
            if(root == node)
                return true;
            else
            {
                if(node.getKey()>root.getKey())
                    return this.search(root.right,node);
                else    
                    return this.search(root.left,node);
            }
        }

    }
    public void insert(Node node) 
    /*
    parametrs:Node
    Description: the procedure insert the node in the right place in the bst.
    */
    {
        
        if(root == null)
            root = node;
        else
            this.insert(root,node);
        this.stack.push(root);
    }


    public void insert(Node root,Node node) 
    /*
    parametrs:Node
    Description: the procedure insert the node in the right place in the bst.
    */
    {
        
        if(root.getKey() < node.getKey())
        {
            if(root.right == null)
            {
                root.right  = node;
                node.parent = root; 
            }
            else
                insert(root.right ,node);
        }
        else
        {
            if(root.left == null)
            {
                root.left   = node;
                node.parent = root;
            }
            else
                insert(root.left,node);
        }
    
    }
    

    public void delete(Node node) 
    {
        if(root == null || node ==null )
            return;
        if(this.search(root,node))
        {
            this.delete2(node);
        }
    }
    public void delete2(Node node) 
    {
        if(node == null)
        {
            return;
        }
        
        if(node.left == null && node.right == null)
        {
            if(node == node.parent.left)
                node.parent.left = null;
            else
                node.parent.right = null;
            node = null;
        }
        else if(node.left == null || node.right == null)
        {
            Node parent = node.parent;
            if(node.getKey() <= parent.getKey())
            {
                if (node.left == null)
                    parent.left = node.right;
                else
                    parent.left = node.left;
            }
            else
            {
                if (node.left == null)
                    parent.right = node.right;
                else
                    parent.right = node.left;
            }
            node = null;
        }
        else
        {
            Node successor     = this.successor(node);
            int y              = successor.getKey();
            this.delete2(successor);
            node.key           = y;
            node               = null;
        }    
    }

    public Node minimum()
    /*
    parmeter:
    return  : the procedure return the minimum in the tree;
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
    parmeter:
    return  : the procedure return the minimum in the tree;
    */
    {
        Node node = root;
        while(node.left != null)
            node = node.left;
        return node;
    }

    public Node maximum()
    /*
    parmeter:
    return  : the procedure return the minimum in the tree;
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
    parmeter:
    return  : the procedure return the minimum in the tree;
    */
    {
        Node node = root;
        while(node.right != null)
            node = node.right;
        return node;
    }

    public Node successor(Node node) 
    {
        if(node.right != null)
            return(this.minimum(node.right));

        else
        {
            Node parent = node.parent;
            while(parent!=null && node.getKey() > parent.getKey())
                parent = parent.parent;
            if(parent == null )
                throw new NoSuchElementException("node doesn't have a successor");
            else
                return parent;
        }
    }

    public Node predecessor(Node node) 
    {
        if(node.left != null)
            return(this.maximum(node.left));

        else
        {
            Node parent = node.parent;
            while(parent!=null && node.getKey() < parent.getKey())
                parent = parent.parent;
            if(parent == null || node.getKey() == parent.getKey())
                throw new NoSuchElementException("node doesn't have a predecessor");
            else
                return parent;
        }
    }

    @Override
    public void backtrack() 
    {
        // TODO: implement your code here
    }

    @Override
    public void retrack() 
    {
        // TODO: implement your code here
    }
    public void printPreOrder()
    {
        if(root == null)
            System.out.print("Empty tree");
        else
        {
            System.out.print("Preorder: ");
            this.printPreOrder(root);
            System.out.println();
        }
    }

    public void printPreOrder(Node root)
    {
        if(root == null)
            return;
        System.out.print(root.getKey()+", ");
        this.printPreOrder(root.left);
        this.printPreOrder(root.right);
    }

    @Override
    public void print() {
    	printPreOrder();
    }

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
