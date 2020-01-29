import java.io.*;

/**
 * IntegerTree - An example of a Binary Tree built from IntTreeNode nodes.
 * 
 */
public class IntegerTree 
{

    private 		IntTreeNode	root;		// The root node of the tree
    private int 	size = 0;				// A count of the nodes in the tree

    /**
     * Returns the number of nodes in the tree.
     * 
     * @return Number of nodes in the tree.
     */
    public int size()
    {
        return size;
    }

    /**
     * Determines if the tree is empty or not.
     * 
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (root == null);
    }

    /**
     * Adds an IntTreeNode to the tree that will provide a Binary Tree.
     * 
     * @param val	The integer value to be added
     */
    public void addNode(int val)
    {	
        IntTreeNode node = new IntTreeNode(val);

        // If tree is empty, make our new node the root and leave
        if (root == null)
        {
            root = node;
            size = 1;
        }
        else
        {
            // Start the recursive calls to add the descendants of node 'p'		
            addNode(node,root);
            size = size + 1;
        }
    }

    /**
     * Internal recursive method to add a node
     * 
     * @param c	The node to place in the tree
     * @param n	The current node to consider, will not be null
     */
    private void addNode(IntTreeNode c, IntTreeNode node)
    {	
        // Is our new node value less than our current node value?
        // If it is, we add it to the left side of the tree so that when
        // we walk it with an in-order traversal, the tree content will
        // come out in alphabetic order. Conversely add it to the right if it 
        // is greater.
        if (c.getValue() < node.getValue())
        { 
            if (node.hasLeft()) 
            {
                addNode(c,node.left());
            }
            else
            {
                // Attach it to the left of the current node
                node.setLeft(c);
                return;
            }	
        }
        else
        {
            if (node.hasRight())
            {
                addNode(c,node.right());
            }
            else
            {
                // Attach it to the right of the current node
                node.setRight(c);
                return;				
            }
        }
    }

    /**
     * Public call to start the recursive pre-order traversal 
     * using the root of the tree. 
     * 
     * @return A reference to a comma separated String containing
     * the trees contents as determined by a preOrder Traversal.
     */
    public String preOrderTraversal()
    {
        StringBuffer buff = new StringBuffer();

        if (root == null) 
        {
            return "Empty Tree!";
        }
        else
        {
            preOrder(root,buff);
        }
        return buff.toString();
    }

    /**
     *  Private method used to make a recursive call from a particular node
     *   
     * @param n	The node to perform a pre-order traversal from.
     * @param sb The StringBuffer to store the results of walking the tree
     */
    private void preOrder(IntTreeNode n, StringBuffer sb)
    {
        sb.append(n.getValue() + ",");

        if(n.hasLeft()) //visit left
        {
            preOrder(n.left(), sb);
        }

        if(n.hasRight())//visit right
        {
            inOrder(n.right(),sb);
        }
    }

    /**
     * Public call to start a recursive in-order traversal using 
     * the root of the tree. 
     *
     * @return A reference to a comma separated String containing
     * the trees contents as determined by an in-order traversal.
     */
    public String inOrderTraversal()
    {
        StringBuffer buff = new StringBuffer();

        if (root == null) 
        {
            return "Empty Tree!";
        }
        else
        {
            inOrder(root, buff);
        }
        return buff.toString();
    }

    /**
     * Private method used to make a recursive in-order traversal from node 'n'
     *  
     * @param n	The node to perform the inOrder walk from
     * @param sb The StringBuffer to store the results of walking the tree
     */
    private void inOrder(IntTreeNode n, StringBuffer sb)
    {
        if(n.hasLeft()) //visit left
        {
            inOrder(n.left(), sb);
        }
        sb.append(n.getValue() + ", ");

        if(n.hasRight())//visit right
        {
            inOrder(n.right(),sb);
        }
    }

    /**
     * Public call to start the recursive post-order traversal using
     * the root of the tree. 
     * 
     * @return A reference to a comma separated String containing
     * the trees contents as determined by a post-order traversal.
     */
    public String postOrderTraversal()
    {
        StringBuffer buff = new StringBuffer();

        if (root == null) 
        {
            return "Empty Tree!";
        }
        else
        {
            postOrder(root,buff);
        }
        return buff.toString();
    }

    /**
     * Private method used to make a recursive postOrder traversal for node 'n'
     * 
     * @param n	The node to perform the postOrder walk from
     * @param sb The StringBuffer to store the results of walking the tree
     */
    private void postOrder(IntTreeNode n, StringBuffer sb)
    {
        if(n.hasLeft()) //visit left
        {
            postOrder(n.left(), sb);
        } 

        if(n.hasRight())///visit right
        {
            postOrder(n.right(),sb);           
        }
        sb.append(n.getValue() + ", ");        
    }

    public void printTree()
    {
        printTree(root,0);
    }

    /**
     * Internal method used to make a recursive reverse order walk from node 'n'
     *  
     * @param n		The node to start the reverse order walk from
     * @param depth	The current depth of the node in the tree
     */
    private void printTree(IntTreeNode n, int depth)
    {
        if (n.hasRight()) 
        {
            printTree(n.right(),depth+1);
        }

        // Show depth of current code by indenting to the right
        for (int d=0; d<depth; d++)
        {
            System.out.print("  ");
        }
        System.out.println(n);
        if (n.hasLeft())
        {printTree(n.left(),depth+1);
        }
    }
}