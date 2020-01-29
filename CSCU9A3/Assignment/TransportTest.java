import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Please provide your student ID below.
 * 
 * 	Student ID: ????????
 */

/**
 * The TransportTest class provides different methods of checking the behaviour
 * of the TransportManager class and the objects that it uses. It maintains a
 * reference to a TransportManager object called 'manager' and populates it via
 * the loadTransport method. 
 * 
 * If you run this class via a call to main, main will create
 * a TransportTest object and call the go method which produces some useful output that
 * you can analyse. TransportTest also contains the following test methods that 
 * you should implement to confirm that your tree walks are working as intended. At
 * present they only print out the results of a tree walk - you need to convert this
 * to use a valid assertion test.
 * 
 * 	public void preOrderTest()
 * 	public void inOrderTest()
 * 	public void postOrderTest()
 *  
 * @author David
 *
 */
public class TransportTest
{
    TransportManager manager = new TransportManager();
    public static void main(String[] args)
    {
        TransportTest test = new TransportTest();
        test.go();
    }

    public void loadTransport()
    {
        manager.clear();
        manager.addVehicle(new Car("Ferrari","Enzo",5998));
        manager.addVehicle(new Boat("Titanic",52310));
        manager.addVehicle(new Vehicle("Vehicle"));
        manager.addVehicle(new Car("Porsche","Boxter",3200));
        manager.addVehicle(new Aircraft("Concorde",2, 358));
        manager.addVehicle(new Aircraft("Airbus A380",2, 845));
        manager.addVehicle(new Yacht("Maltese Falcon",1240,3,12));
        manager.addVehicle(new Yacht("Discovery",1570,3,10));
    }

    /**
     * Print the contents of the ArrayList 'list' to standard output.
     * @param list The list to print
     */
    public void printArrayList(ArrayList<Vehicle> list)
    {
        for (Vehicle v:list)
        {
            System.out.println(v.getName());
        }
    }

    /**
     * 'go' produces a useful set of output that allows you to see the
     * initial state transport list and tree, the results of doing the
     * three tree walks, an attempt at finding an object in the tree and 
     * the results of doing an insertion sort and a quicksort. Initially,
     * only the state of the transport list will be correct. As you add
     * further functionality, you should see the correct output being
     * printed via this method. You do not need to modify it but you may
     * want to add your own checks to confirm your code is working as intended. 
     */

    public void go()
    {
        loadTransport();
        System.out.println("\n--Transport List--");
        manager.describeTransportList();
        System.out.println();

        System.out.println("\n--Transport Tree--");
        manager.describeTransportTree();	
        System.out.println();

        System.out.println("\n--Pre Order Walk--\n" + manager.preWalk());
        System.out.println("\n--In Order Walk--\n" + manager.inWalk());
        System.out.println("\n--Post Order Walk--\n" + manager.postWalk());

        System.out.println("\n--Find--");
        Vehicle mf = manager.find("Concorde");
        if (mf != null) 
        {
            System.out.println("Found: " + mf.toString());
        }
        else
        {
            System.out.println("Could not find Concorde");
        }

        ArrayList<Vehicle> sorted;

        System.out.println("\n--Insertion Sort--");
        loadTransport();
        sorted = manager.insertionSort();
        printArrayList(sorted);

        System.out.println("\n--Quick Sort--");
        // Reload the transport list, otherwise it will still be sorted...
        loadTransport();
        sorted = manager.quickSort();
        printArrayList(sorted);	

    }

    @Test
    public void printTransportTreeList()
    {
        loadTransport();
        System.out.println("\n--Transport List--");
        manager.describeTransportList();
        System.out.println();

        System.out.println("\n--Transport Tree--");
        manager.describeTransportTree();	
        System.out.println();
    }

    

    @Test
    /**
     * A test for the pre-order walk
     */
    public void preOrderTest()
    {	
        // Ensure the transport list has been loaded into the two data structures
        loadTransport();          
        assertEquals("Enzo, Boxter, Airbus A380, Concorde, Discovery, Titanic, Maltese Falcon, Vehicle, ", manager.preWalk());        
        System.out.println("\n--Pre Order Walk--\n" + manager.preWalk());

    }

    @Test
    /**
     * A test for the in-order walk
     */
    public void inOrderTest()
    {	
        // Ensure the transport list has been loaded into the two data structures
        loadTransport();
        assertEquals("Airbus A380, Boxter, Concorde, Discovery, Enzo, Maltese Falcon, Titanic, Vehicle, ", manager.inWalk());
        System.out.println("\n--In Order Walk--\n" + manager.inWalk());
    }

    @Test
    /**
     * A test for the post-order walk
     */
    public void postOrderTest()
    {	
        // Ensure the transport list has been loaded into the two data structures
        loadTransport();
        assertEquals("Airbus A380, Discovery, Concorde, Boxter, Maltese Falcon, Vehicle, Titanic, Enzo, ", manager.postWalk());
        System.out.println("\n--Post Order Walk--\n" + manager.postWalk());
    }
    
    @Test
    public void findTest()
    {
        loadTransport();        
        Vehicle expectedToFind = new Aircraft ("Concorde", 2, 358);
        System.out.println("\n--Find--");  
        System.out.println(manager.find("Concorde"));       
        assertTrue("Find test failed", expectedToFind != null);
        Vehicle missing = manager.find("Mazda");
        assertTrue("Find test failed", missing == null);
    }

    @Test
    public void InsertionSortingTest()
    {
        ArrayList<Vehicle> insterionSorded;
        System.out.println("\n***Insertion Sorting***");
        loadTransport();
        insterionSorded = manager.insertionSort();
        printArrayList(insterionSorded);
    }

    @Test
    public void QuickSortingTest()
    {
        ArrayList<Vehicle> quickSorted;
        System.out.println("\n***Quick Sorting***");
        loadTransport();
        quickSorted = manager.quickSort();
        printArrayList(quickSorted); 
    }
}