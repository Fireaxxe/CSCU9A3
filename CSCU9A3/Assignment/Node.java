/**
 * A node in the BinaryTree. It contains a reference to a Vehicle object
 * associated with the node and references to the left and right Node objects
 * that are below the node. You do not need to modify this code at all.
 */

/**
 * @author David Cairns
 *
 */
public class Node {

    private Vehicle	vehicle = null;	// The Vehicle stored at this node
    private Node	left = null;	// The left node of this node
    private Node	right = null;	// The right node of this node

    /**
     * Default constructor that initialises the node with
     * a Page associated with this node.
     * 
     * @param c	The content of the node
     */
    public Node(Vehicle v)
    {
        vehicle = v;
    }

    /**
     * Set the left reference of this node to 'n'
     *  
     * @param n	A reference to the new left node
     */
    public void setLeft(Node n)
    {
        left = n;
    }

    /**
     * Set the right reference of this node to 'n'
     *  
     * @param n	A reference to the new right node
     */
    public void setRight(Node n)
    {
        right = n;
    }

    // Get properties of the node
    public Vehicle getVehicle() 
    { 
        return vehicle; 
    }	    

    public String getVehicleName() 
    { 
        return vehicle.getName(); 
    }

    public boolean hasLeft() 
    {
        return left != null;
    }

    public boolean hasRight()
    { 
        return right != null;
    }

    public Node left() 
    { 
        return left;
    }

    public Node right() 
    {
        return right;
    }

    public int compareTo(Node pn) 
    { 
        return getVehicleName().compareTo(pn.getVehicleName()); 
    }

    public String toString()
    { 
        return getVehicleName(); 
    }
}