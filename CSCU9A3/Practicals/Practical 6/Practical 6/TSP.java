import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * TSP (short for Travelling Salesman Problem) loads a set of locations 
 * from a text file and adds each location that it has read to the 
 * ArrayList 'locations'. It then provides methods to ask how far it is
 * between a set of locations so that we can try out different routes and
 * check what distance they would be.
 * 
 * It also keeps a note of how many times it is asked to calculate a route
 * so that we can get some idea of how much computation is being performed
 * by a particular search algorithm.
 * 
 * @author  David Cairns
 * @date	21/11/13
 *
 */
public class TSP {

	// The list of locations that need to be traversed
	private ArrayList<Place> locations = new ArrayList<Place>();
	
	// A count of the number of route measurement calculations made
	private int evaluations = 0;

	/**
	 * Loads a set of place values from filename into the locations list
	 * @param filename The file to load the location data from
	 */
	public void loadLocations(String filename)
	{
		BufferedReader  in;		// An object that enables us to read data
		String 		    vals[];	// An array of values 
		int				x,y;	// Temp variables to store x and y positions
		
		// Empty the locations list in case it already has places in it
		locations.clear();
		try
		{
			// Try to open the file 'filename' and then read the first line
			in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			// While we have managed to read a line
			while (line != null)
			{
				// If we have a comment, skip this line
				if (!line.startsWith("//"))
				{
					// Split the line into pieces based on tab separator
					vals = line.split("\t"); 
					// Convert the text values into numbers
					x = Integer.parseInt(vals[1]);
					y = Integer.parseInt(vals[2]);
					// Create a new Place based on its position and name
					Place p = new Place(vals[0],x,y);
					
					// Add it to our list of places
					locations.add(p);
				}
				// Try to read the next line of text
				line = in.readLine();
			}
		}
		catch (Exception e)
		{
			// The above may not work so we need to catch any exceptions
			// that might have occurred
			System.err.println("Failed to open file '" + filename + "' :" + e);
		}
	}
	
	
	/**
	 * @return How many locations we currently have
	 */
	public int getNumLocations() { return locations.size(); }

	/**
	 * @return How many route length evaluations have we done 
	 */
	public int getEvaluations() { return evaluations; }
	
	/**
	 * Reset the route length evaluation count to 0
	 */
	public void resetEvaluations() { evaluations = 0; }
	
	/**
	 * Score a particular route, taking into account penalties for not
	 * visiting every location in order.
	 * 
	 * @param route An array of location indexes corresponding to lines in the file
	 * 
	 * @return The total distance of the route plus any penalties
	 */
	public double scoreRoute(int[] route)
	{
		// Get the length of the route as the base for our score
		double score = getRouteLength(route);
		double penalty = 100.0f;
		
		// Now get a sorted version of the route
		ArrayList<Integer> rt = new ArrayList<Integer>();
		for (int r:route) rt.add(r);
		Collections.sort(rt);
		
		// Step down this sorted list and check the location
		// indexes increment by 1 every time
		int loc = 0;
		for (int r:rt)
		{
			// If there is a mismatch, add a penalty to our score
			if (r != loc) score += penalty;
			loc++;
		}
		
		// What is the difference in length between our two routes
		int diff = Math.abs(locations.size()-route.length);
		
		// Add the difference to our score as a penalty multiplier
		// This catches the case where a route may be in order but
		// not visit all locations or includes a location twice.
		score = score + (penalty * diff);
		
		evaluations++;
		return score;
	}

	/**
	 * Calculate the length of this route by stepping
	 * from location to location and adding up the distance
	 * taken at each step.
	 * 
	 * @param route	An array of location indexes corresponding to lines in the file
	 * 
	 * @return The total distance of the route
	 */
	public double getRouteLength(int[] route)
	{
		
		// Use the first place is the route as our starting point
		Place previous = locations.get(route[0]);
		Place current = null;

		double length = 0;
		// We already know the first Place, so start r at 1.
		for (int r=1; r<route.length; r++)
		{
			current = locations.get(route[r]);
			// Add on the distance from our previous place
			// to our current place
			length = length + previous.distanceTo(current);
			
			previous = current;
		}
		
		return length;
	}
	
	// Same as getRouteLength(int[] route) but uses array 
	// of floats which have to be cast to integers
	public double getRouteLength(float[] route)
	{
		
		// Use the first route value as our starting point
		Place previous = locations.get((int)route[0]);
		Place current = null;

		double length = 0;
		// We already know the first Place, so start r at 1.
		for (int r=1; r<route.length; r++)
		{
			current = locations.get((int)route[r]);
			// Add on the distance from our previous place
			// to our current place
			length = length + previous.distanceTo(current);
			
			previous = current;
		}
		
		evaluations++;
		return length;
	}
	
	/**
	 * Print out 'route' to the console
	 * @param route An array of route index positions
	 */
	public void printRoute(int[] route)
	{
		for (int l:route)
		{
			// Get the name of the location at each index position
			System.out.print(locations.get(l).getName() + ",");
		}
		System.out.println();
	}
	
	/**
	 * Builds a String representation of 'route'
	 * @param route	The route to work out the String pattern for
	 * @return A String representation of 'route'
	 */
	public String toString(int[] route)
	{
		// We will build the representation up
		// using a String buffer
		StringBuffer sb = new StringBuffer();
		
		int location; // The current location index on our route
		
		for (int i=0; i<route.length; i++)
		{
			// Get the requested location index
			location = route[i];
			
			// Check that it is valid, then append its name
			if (location >= locations.size())
				sb.append("?");
			else
				sb.append(locations.get(location).getName());
			
			// If this is not the last location in our
			// route, append a comma
			if (i+1 < route.length)
				sb.append(",");
		}
		
		return sb.toString();
	}
	
	/**
	 * Works out whether or not we have solved a particular 
	 * challenge by working out the length of the route
	 * and confirming if it is less than a given threshold
	 * 
	 * @param route	The route to check the distance of
	 * @return 'true' if the route length is less than threshold
	 */
	public boolean solved(ArrayList<Integer> route, float threshold)
	{
		// Check that we have a route, then convert it to an int array
		if (route == null) return false;
		int rt[] = new int[route.size()];
		
		// Get the values to put in the int array 'rt'
		for (int r=0; r<rt.length; r++) rt[r] = route.get(r);
		
		// Now find the route length and check it against our threshold
		return (scoreRoute(rt) < threshold);
	}
}
