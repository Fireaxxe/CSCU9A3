import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Solver sets up our different problems and tries to solve them in different ways.
 */

public class Solver {

	// The following is used to keep a track of how many times we call the
	// objective function. It is normally proportional to the amount of work
	// involved in finding a solution to a problem
	private int evaluations = 0;  
	private Pattern target = null;
		
	public Solver()
	{
		target = new Pattern(40,40,true); // Create a random 40x40 target pattern
	}
	
	@Test
	public void solvePattern()
	{
		// View the pattern we are trying to guess
		System.out.println("Target\n" + target);
		
		// Use the greedy algorithm to find the pattern
		evaluations = 0;
		Greedy grd = new Greedy();
		Pattern greedyPattern = grd.greedyPattern(target,this);
		System.out.println("Greedy Evaluations " + evaluations + "\n");

		// Now try to get the GA to guess the pattern
//		evaluations = 0;
//		GA ga = new GA();
//		Pattern gaPattern = ga.evolvePattern(target, this);
//		System.out.println("GA Evaluations " + evaluations + "\n");

	}
	
	@Test
	public void solveRoute()
	{
		TSP tsp = new TSP();
		tsp.loadLocations("locations.txt");
		String	output;
		
		// Use the following array to calculate the score for a
		// particular route. The values in the route array are
		// indexes to the lines in the file with 0 being the first line.
		int route[] = {9,1,6,8,5,3,0,4,7,2};	
		// This route would be:
		// Lauder -> Galashiels -> Penicuik -> Livingston -> Stirling ->
		// Callander -> Kilmarnock -> Cumbernauld -> Kinross -> St Andrews
		System.out.println("Distance " + (int)tsp.getRouteLength(route));
		System.out.println("Route: " + tsp.toString(route));
		System.out.println();

		// Try to solve routing problem with a greedy approach
//		tsp.resetEvaluations();
//		Greedy grd = new Greedy();
//		int greedyPath[] = grd.greedyRoute(tsp);
//		double greedyDistance = tsp.scoreRoute(greedyPath);
//		output = String.format("Greedy Evaluations: %d\tRoute Distance:%.1f", 
//	               tsp.getEvaluations(),greedyDistance);
//		System.out.println(output);
//		tsp.printRoute(greedyPath);
//		System.out.println();

		// Now try a GA approach
//		tsp.resetEvaluations();
//		GA ga = new GA();
//		int gaPath[] = ga.evolveRoute(tsp);
//		double gaDistance = tsp.scoreRoute(gaPath);
//		output = String.format("GA Evaluations: %d\tRoute Distance:%.1f", 
//				               tsp.getEvaluations(),gaDistance);
//		System.out.println(output);
//		tsp.printRoute(gaPath);
		
	}
	
	// We score our solutions via the following methods so that we can keep
	// a count of how often they are called using the 'evaluations' counter.
	// This gives a reasonable approximation of the amount of work involved.
	
	/**
	 * 
	 * @param proposed
	 * @param target
	 * @return
	 */
	public float scoreSolution(Pattern proposed, Pattern target)
	{
		evaluations++;
		return proposed.difference(target);
	}
	
	/**
	 * 
	 * @param c
	 * @param target
	 * @return
	 */
	public float scoreSolution(Chromosome c, Pattern target)
	{
		evaluations++;
		return c.score(target);
	}
}
