

/**
 * Place acts as a simple container to hold the name of a place
 * and its position as 'x' and 'y' coordinates.
 * 
 * @author  David Cairns
 * @date	23/11/13
 */
public class Place {
	
	private String	name;	// The name associated with a location
	private int		xc;		// The x coordinate
	private int		yc;		// The y coordinate
	
	/**
	 * Creates a new location based on its name 'n' and the position 'x,y'
	 */
	public Place(String n, int x, int y)
	{
		name = n;
		xc = x;
		yc = y;
	}

	// Attribute accessor methods
	public String getName() { return name; }
	public int getX() { return xc; }
	public int getY() { return yc; }
	
	/**
	 * Calculates the distance between two locations using Pythagoras' theorem.
	 * 
	 * @param p	The place to measure the distance to
	 * @return	The distance between 'p' and position stored by this object
	 */
	public double distanceTo(Place p)
	{
		// Get the difference along the x and y axis
		float xdiff = xc - p.xc;
		float ydiff = yc - p.yc;
		
		// Use Pythagoras theorem to work out the distance 
		// along the diagonal between the two places
		return Math.sqrt((xdiff*xdiff) + (ydiff*ydiff));
	}
}
