/**
 * The Pattern class creates a random pattern of either 1's or 0's which 
 * can be referenced via an x and a y coordinate for a given bit. The
 * actual data is just stored as one long array of floats. Methods are
 * provided to set and get the bits at a given coordinate so you do not
 * have to concern yourself about the internal representation.
 * 
 * @author	David Cairns
 * @date	21/11/13
 *
 */
public class Pattern {

	private int xdim=0;		// The length in bits of the pattern
	private int ydim=0;		// The height in bits of the pattern
	private int size=0;		// The total number of bits in the pattern
	private float shape[];	// The bits in the pattern
	
	
	/**
	 * Create a new pattern. If 'random' is true then it will have random
	 * values for each bit in the pattern. If it is false then all the bits
	 * will be set to 0.
	 * 
	 * @param x	The length of the new pattern
	 * @param y	The height of the new pattern
	 * @param random 'true' to create a random pattern, false to set all to '0'
	 */
	public Pattern(int x, int y, boolean random)
	{
		// Initialise the dimensions of the pattern
		// and allocate enough memory to store it.
		xdim = x;
		ydim = y;
		size = xdim * ydim;
		shape = new float [x*y];
		
		// Loop through the bits and either randomly initialise
		// them or set them to 0
		for (int v=0; v<size; v++)
		{
			shape[v] = 0.0f;
			if (random && Math.random() >= 0.5)
				shape[v] = 1.0f;
		}

	}
	
	/**
	 * Creates a new blank pattern based on the 'template' 
	 * dimensions. All bit values are set to 0.
	 * 
	 * @param template	The template pattern to base the new Pattern on
	 */
	public Pattern(Pattern template)
	{
		this(template.xdim, template.ydim, false);
	}
	
	/**
	 * Returns a duplicate of the current Pattern
	 */
	public Pattern clone()
	{
		Pattern p = new Pattern(xdim,ydim,false);
		p.shape = this.shape.clone();
		return p;
	}
	
	// Accessor methods for the Pattern attributes
	public int getLength() { return xdim; }
	public int getHeight() { return ydim; }
	public int getSize() { return size; }

	/**
	 * Sets a given 'x' and 'y' coordinate to 'val'
	 */
	public void set(int x, int y, float val)
	{
		int loc = (y*xdim) + x;
		shape[loc] = val;
	}

	/**
	 * Returns the value at coordinate 'x,y'
	 */
	public float get(int x, int y)
	{
		int loc = (y*xdim) + x;
		return shape[loc];
	}

	/**
	 * Get the value at position 'p', viewing the 
	 * pattern as an array
	 */
	public float get(int p)
	{
		return shape[p];
	}
	
	/**
	 * Set the value at position 'p' to 'val', viewing the 
	 * pattern as an array
	 */
	public void set(int p, float val)
	{
		shape[p] = val;
	}
	
	/**
	 * Get a reference to the array of float values. In this
	 * case we view them as the genes for our chromosome
	 */
	public float[] getGenes() { return shape; }
	
	/**
	 * Get a String representation of our pattern as a 2D grid
	 */
	public String toString()
	{
		// We are going to use a StringBuffer to build up
		// the string that represents the pattern
		StringBuffer pat = new StringBuffer();
		
		// Loop through the y and x coordinates of the pattern
		for (int y=0; y<ydim; y++)
		{
			for (int x=0; x<xdim; x++)
			{
				// For each bit, add it to our string
				pat.append(String.format("%.0f",get(x,y)));
			}
			// Put a new line at the end of every row
			pat.append('\n');
		}
		
		// Now we have built the pattern, return it as a String
		return pat.toString();
				
	}
	
	/**
	 * Return a count of the number of values that are different
	 * between this pattern and pattern 't'. If the values in the
	 * pattern are either '0' or '1', this is known as the Hamming
	 * distance. We use this to score our sample solutions to see
	 * how close they are to the target.
	 * 
	 * @param t	The pattern to make the comparison with
	 *  
	 * @return A count of the number of values that are different
	 */
	public float difference(Pattern t)
	{
		float diff = 0;
		for (int i=0; i<size; i++)
			if (t.get(i) != this.get(i)) diff = diff + 1.0f;
		return diff/size;
	}
	
	/**
	 * Swaps around the values that are in the array at positions p1 and p2.
	 */
	public void swap(int p1, int p2)
	{
		float tmp = shape[p1];
		shape[p1] = shape[p2];
		shape[p2] = tmp;
	}
}
