import java.util.ArrayList;

/**
 * This class is used to store the 'genetic material' that encodes the solution
 * to a problem. The meaning of the genes is relative to the problem. In some
 * cases they may encode bit values in a pattern, in other cases they are the 
 * indexes of locations to visit on a map. The GA does not generally need to 
 * know what they mean in order to operate correctly although there can be 
 * different constraints on what the valid ranges of the genes can be.
 * 
 * @author 	David Cairns
 * @date	21/11/13
 *
 */
public class Chromosome {

	private Pattern genes;			// The set of values comprising this solution
	private TSP		problem=null;	// A reference to a TSP problem to try them on
	private float 	fitness=0.0f;	// The fitness score associates with the 'genes'
	
	/**
	 * Creates a random initial solution based on a grid of values 'xdim' by 'ydim'
	 * @param xdim	Length of grid
	 * @param ydim	Height of grid
	 */
	public Chromosome(int xdim, int ydim)
	{
		genes = new Pattern(xdim,ydim, true);
	}
	
	/**
	 * Creates a new Chromsome object using 'template' to 
	 * set the required value (dimensions or problem reference).
	 * 
	 * @param template	The template to use, gene values are not copied
	 */
	public Chromosome(Chromosome template)
	{
		genes = new Pattern(template.genes);
		problem = template.problem;
		fitness = 0.0f;
	}
	
	/**
	 * Creates a new Chromosome object using the problem 'tsp' as its
	 * reference problem that should be solved with shuffled location
	 * indexes.
	 * 
	 * @param tsp	A reference to the problem to be solved
	 */
	public Chromosome(TSP tsp)
	{
		problem = tsp;	// Make a copy of the reference to the problem
		int	size = problem.getNumLocations();	// Note the number of genes needed
		
		genes = new Pattern(size,1,false);	// Create a blank one dimensional pattern
		
		// Put locations in order
		for (int g=0; g<size; g++) genes.set(g, g);
		
		// Now randomly swap the values around
		for (int i=0; i<50; i++) 
			genes.swap((int)(Math.random()*size),(int)(Math.random()*size));
	}
	
	/**
	 * Return a reference to the problem being solved by this chromosome.
	 */
	public TSP getProblem() { return problem; }
	
	/**
	 * Return a duplicate of this Chromosome that can be changed without 
	 * affecting this one.
	 */
	public Chromosome clone()
	{
		Chromosome c = new Chromosome(this.problem);
		
		c.fitness = this.fitness;
		c.problem = this.problem;
		c.genes = this.genes.clone();
		
		return c;
	}
	
	/**
	 * Get a score for the genes associated with this Chromosome and
	 * record it for later use.
	 * 
	 * @param t	The target pattern to score against
	 * @return	The fitness score
	 */
	public float score(Pattern t)
	{
		fitness = genes.difference(t);
		return fitness;
	}
	
	/**
	 * Get a score for the genes associated with this Chromosome and
	 * record it for later use. This method uses the problem reference
	 * to find out what the score is.
	 * 
	 * @return	The fitness score
	 */
	public float score()
	{
		fitness = (float)problem.getRouteLength(genes.getGenes());
		return fitness;
	}
	
	/**
	 * Randomly flip bits in the genes with a chance of flipping
	 * a bit equal to 'probability'.
	 * 
	 * @param probability	The chance of flipping a bit
	 */
	public void mutate(float probability)
	{
		for (int g=0; g<genes.getSize(); g++)
		{
			if (Math.random() < probability)
			{
				// Flip the value
				if (genes.get(g) == 0) 
					genes.set(g,1.0f);
				else
					genes.set(g,0.0f);				
			}
		}		
	}
	
	/**
	 * Swap locations encoded in the genes with the given 'probability' of
	 * doing a swap.
	 * 
	 * @param probability	The chance of swapping a particular gene with another
	 */
	public void mutateRoute(float probability)
	{
		int size = genes.getSize();
		
		for (int g=0; g<size; g++)
		{
			if (Math.random() < probability/2)
			{
				genes.swap(g,(int)(Math.random()*size));
			}
		}
	}
	
	/**
	 * Returns a route as an ArrayList (internally it is stored as a float array)
	 * 
	 * @return	The route as an ArrayList of Integer
	 */
	public ArrayList<Integer> getRoutes()
	{
		ArrayList<Integer> rt = new ArrayList<Integer>();
		
		for (float f:genes.getGenes())
			rt.add((int)f);
		
		return rt;
	}
	
	// The following provide accessor methods for the attributes of Chromosome.
	
	public float getFitness() { return fitness; }
	public Pattern getPattern() { return genes; }
	public float getGene(int g) { return genes.get(g); }
	public void setGene(int g, float value) { genes.set(g, value); }
	
	public int numGenes() { return genes.getSize(); }	

	public String toString() { return genes.toString(); }

}

