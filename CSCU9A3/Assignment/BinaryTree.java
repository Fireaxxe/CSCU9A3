/**
 * Please provide your student ID below.
 * 
 * 	Student ID: ????????
 */

/**
 * BinaryTree - An example of a Binary Tree built using Node objects.
 * Some of the functionality for this class is provided for you (and
 * may provide clues as to how to implement assignment methods). Your 
 * task is to implement the method bodies for:
 * 
 * 	protected void preOrder(Node n, StringBuffer sb)
 * 	protected void inOrder(Node n, StringBuffer sb)
 * 	protected void postOrder(Node n, StringBuffer sb)
 * 	protected Vehicle find(String name, Node n)
 *  
 */
public class BinaryTree 
{

    private Node	root;		// The root node of the tree
    private int 	size = 0;	// A count of the nodes in the tree

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
     * Empties the tree
     */
    public void clear()
    {
        root = null;
        size = 0;
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
     * Adds a Node containing a reference to the Vehicle v to the tree.
     * 
     * @param c	The content of the node that will be added
     */
    public void addNode(Vehicle v)
    {	
        Node node = new Node(v);
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
     * Internal recursive method to add a node to the tree.
     * 
     * @param c	The content to place in the tree
     * @param n	The current node to consider, will not be null
     */
    private void addNode(Node c, Node n)
    {	
        // Is our new node content less than our current node content?
        // If it is, we add it to the left side of the tree so that when
        // we walk it with an in-order traversal, the tree content will
        // come out in alphabetic order. Conversely add it to the right if it 
        // is greater.
        if (c.compareTo(n) < 0)
        { 
            if (n.hasLeft()) 
            {
                addNode(c,n.left());
            }
            else
            {
                // The current left node is null so we can
                // attach our new node to the left of the current node
                n.setLeft(c);
                return;
            }	
        }
        else
        {
            if (n.hasRight())
            {
                addNode(c,n.right());
            }
            else
            {
                // The current right node is null so we can
                // attach our new node to the right of the current node
                n.setRight(c);
                return;				
            }
        }
    }

    /**
     * Public call to start the recursive pre-order traversal 
     * using the root of the tree. Pre-order traversal is also 
     * known as pre-fix traversal.
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
     *  Private method used to make a recursive call from a particular node.
     *  You should provide the correct recursive implementation for this method.
     *   
     * @param n	The node to perform a pre-order traversal from.
     */
    protected void preOrder(Node n, StringBuffer sb)
    {
        if (n == null)
        {
            return;
        }
        else
        {
            sb.append(n.getVehicleName() + ", "); // Visit node "n"
            preOrder(n.left(), sb);
            preOrder(n.right(), sb);
        }
    }

    /**
     * Public call to start a recursive in-order traversal using 
     * the root of the tree. In-order traversal is often called in-fix traversal.
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
     *  You should provide the correct recursive implementation for this method.
     *  
     * @param n	The node to perform the inOrder walk from
     */
    protected void inOrder(Node n, StringBuffer sb)
    {
        if (n == null) 
        {
            return;
        }
        else
        {
            inOrder(n.left(), sb);
            sb.append(n.getVehicleName() + ", "); // Visit node "n"
            inOrder(n.right(), sb);
        }
    }

    /**
     * Public call to start the recursive post-order traversal using
     * the root of the tree. Post order traversal is also called postfix
     * and is used to create a Reverse Polish notation of a tree suitable
     * for calculating equations.
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
     * Protected method used to make a recursive postOrder traversal for node 'n'
     * You should provide the correct recursive implementation for this method.
     * 
     * @param n	The node to perform the postOrder walk from
     */
    protected void postOrder(Node n, StringBuffer sb)
    {
        if (n == null)
        {
            return;
        }
        else
        {
            postOrder(n.left(), sb);
            postOrder(n.right(), sb);
            sb.append(n.getVehicleName() + ", "); // Visit node "n"
        }
    }

    /**
     * Find the reference for a given vehicle by 'name'
     *  
     * @param name	The vehicle name to search for
     * @return	A reference to the vehicle that was found or null if no vehicle found
     */
    public Vehicle find(String name)
    {
        return find(name,root);
    }

    /**
     * Internal protected method used to search the tree from node 'n' looking
     * for a vehicle with the given 'name'. You should provide the correct
     * recursive implementation for this method. Some helpful lines of code
     * have been included...

     * @param name	The name of the vehicle to search for
     * @param n		The current node in the tree to search from
     * @return		A reference to the node that was found or null if not found
     */
    protected Vehicle find(String name, Node n)
    {
        if (n == null) //if not found, return null
        {
            return null;
        }

        int nameOrder = name.compareTo(n.getVehicleName());

        if(nameOrder == 0)
        {
            return n.getVehicle();
        }
        else if (nameOrder > 0 && n.hasRight())
        {
            return find(name, n.right());
        }
        else if (nameOrder < 0 && n.hasLeft())
        {
            return find(name, n.left());
        }
        else
        {
            return null;
        }
    }

    public void printTree()
    {
        printTree(root,0);
    }

    /**
     * Internal method used to make a recursive reverse order walk 
     * from node 'n'. This method is useful to see the current structure of the
     * tree (turned on its side)
     *  
     * @param n		The node to start the reverse order walk from
     * @param depth	The current depth of the node in the tree
     * @param print	true if we should print out node, false otherwise
     */
    private void printTree(Node n, int depth)
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
        System.out.println(n.getVehicleName());

        if (n.hasLeft())
        {
            printTree(n.left(),depth+1);
        }
    }
}