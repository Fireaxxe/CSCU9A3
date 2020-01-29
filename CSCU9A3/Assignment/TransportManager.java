/**
 * Please provide your student ID below.
 * 
 * 	Student ID: ????????
 */

import java.util.ArrayList;

/**
 * TransportManager maintains two data structures containing a set of Vehicle objects.
 * The first is an ArrayList called vehicleList and the second is a binary tree called
 * vehicleTree. Your task is to correctly implement the method bodies for
 * 
 *  protected ArrayList<Vehicle> insertionSort(ArrayList<Vehicle> list)
 *	protected ArrayList<Vehicle> quickSort(ArrayList<Vehicle> list)
 *  
 *  The above methods are called via public methods of the same name which supply 
 *  the local vehicleList object as a parameter. You can observe calls to these public
 *  methods in the go methods of TransportTest.java.  
 */
public class TransportManager
{
    private ArrayList<Vehicle>	vehicleList;
    private BinaryTree			vehicleTree;

    public TransportManager()
    {
        vehicleList = new ArrayList<Vehicle>();
        vehicleTree = new BinaryTree();
    }

    public void clear()
    {
        vehicleList.clear();
        vehicleTree.clear();
    }

    public void addVehicle(Vehicle v)
    {
        vehicleList.add(v);
        vehicleTree.addNode(v);
    }

    public void describeTransportList()
    {
        for (Vehicle v : vehicleList)
        {
            System.out.println(v.toString());
        }
    }

    public void describeTransportTree()
    {	
        vehicleTree.printTree();
    }

    public String preWalk()
    {
        return vehicleTree.preOrderTraversal();
    }

    public String inWalk()
    {
        return vehicleTree.inOrderTraversal();
    }

    public String postWalk()
    {
        return vehicleTree.postOrderTraversal();
    }

    public Vehicle find(String name)
    {
        return vehicleTree.find(name);
    }

    /**
     * This method should use a quick sort approach to rearrange
     * the references in the ArrayList 'vehicleList' such that they are in 
     * non-decreasing alphabetic order. You should not modify this code.
     * 
     * @return	The ArrayList 'vehicles' that has been sorted using quick sort
     */
    public ArrayList<Vehicle> insertionSort()
    {	
        return insertionSort(vehicleList);
    }

    public ArrayList<Vehicle> quickSort()
    {	
        return quickSort(vehicleList, 0, vehicleList.size() - 1);
    }

    protected ArrayList<Vehicle> insertionSort(ArrayList<Vehicle> list)
    {        

        if (list.isEmpty())
        {
            return null;
        }
        else
        {
            int size = list.size();

            for(int a=1; a < size; a++)
            { 
                Vehicle vehicleIntex = list.get(a);
                int b;
                for(b = a - 1; (b >= 0) && (vehicleIntex.compareTo(list.get(b))) < 0; b--)   // Smaller values are moving down
                {
                    list.set(b+1, list.get(b)); // Place the element in index j in position j+1
                }
                list.set(b+1, vehicleIntex);    
            }

            return list;
        }
    }

    /**
     * This method should use a quick sort approach to rearrange
     * the references in the ArrayList 'list' such that they are in 
     * non-decreasing alphabetic order.
     * 
     * @param list An ArrayList of Vehicle objects that need sorting
     * @return	The ArrayList of vehicles that has been sorted using quick sort
     */
      protected ArrayList<Vehicle> quickSort(ArrayList<Vehicle> list, int lowestElement, int highestElement)
    {
          if (list.size() <= 1 ) //if there is only one or non element - it's sorted
        { 
            return list;
        }
        
        // Use the quick sort algorithm to sort 'vehicles' and then 
        // return it. Initially this method just returns an empty
        // list - you need to fix this.
        if (lowestElement < highestElement) 
        {
            Vehicle quickVehicle = list.get(highestElement);
            int a = lowestElement;            
            for (int b = lowestElement; b < highestElement; b++)
            {
                if (list.get(b).getName().compareTo(quickVehicle.getName()) <= 0)
                {
                    Vehicle swap = list.get(a);
                    list.set(a, list.get(b));
                    list.set(b, swap);
                    a += 1;
                }                
            }
            Vehicle swap1 = list.get(a);
            list.set(a, list.get(highestElement));
            list.set(highestElement, swap1);            
            quickSort(list, lowestElement, a-1);   // sort left side
            quickSort(list, a+1, highestElement);  // sort right side        
        }      
        return list;              
    }
}