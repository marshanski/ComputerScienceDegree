else if(node.left == null || node.right == null)
{
    
    Node parent = node.parent;
    if (node.left == null)
        node = node.right;
    else
        node = node.left;
    node.parent = parent;
}

Node successor = this.successor(node);
this.delete2(successor);
node.key       = successor.getKey();
