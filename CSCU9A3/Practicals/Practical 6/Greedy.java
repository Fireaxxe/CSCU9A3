

/**
 * Greedy contains a set of methods that attempt to solve a number of different
 * problems using a greedy approach. The greedy approach looks at all possible
 * steps that can be taken from a given position and selects the one that gives
 * the best current improvement. It can also be thought of as a hill climbing
 * approach where the goal is to get to the highest point over the shortest
 * distance. It can be useful to solve a lot of problems but gets stuck in 
 * situations where in order to make a longer term improvement, it must first
 * make some short term losses. With respect to hill climbing, you can think of
 * it refusing to move from a false summit which it considers to be the best
 * solution, even if there is a higher summit further away but which requires
 * you to go back down first.
 * 
 * @author  David Cairns
 * @date	23/11/13
 */
public class Greedy {

	/**
	 * This method attempts to solve the simple pattern matching problem using
	 * the greedy approach.
	 * 
	 * @param 	target	The target pattern that it is trying to reproduce
	 * @param 	s		A reference to the solver class, 
	 * 					used to find out how well it is doing
	 * @return	The proposed solution pattern
	 */
	public Pattern greedyPattern(Pattern target, Solver s)
	{
		// Find out the dimensions of the target pattern
		int length = target.getLength();
		int height = target.getHeight();
		
		// Start with a random pattern
		Pattern solution = new Pattern(length,height,true);
		
		// What is the difference between this and the target
		float best = s.scoreSolution(solution,target);
	
		float 	score;	// Current score of a solution
		int 	g = 0;	// Count of the number of attempts
		
		// Now try changing 1 bit at a time
		for (int x=0; x<length; x++)
			for (int y=0; y<height; y++)
			{
				// If we have a perfect fit, return the solution
				if (best == 0) return solution;
				
				// Get the bit value that we will change
				float current = solution.get(x, y);
				
				// Flip its value and set it in our solution
				if (current == 0)
					solution.set(x, y, 1.0f);
				else
					solution.set(x, y, 0.0f);
				
				// Find out the new score
				score = s.scoreSolution(solution,target);
				
				// If this did not improve matters, undo it and 
				// move to the next location
				if (score > best)
				{
					solution.set(x, y, current);
				}
				else
				{
					best = score;	// Keep this change
				}
				
				g++;	// Keep a count of evaluations
				
				// Every 100 generations, print out the current score
				if (g % 100 == 0)
				{
					String debug = String.format("%d\t%.2f",g,best);
					System.out.println(debug);
				}
			}
		
		// Return the solution we built
		return solution;
	}

	/**
	 * Attempt to solve the TSP using a greedy approach of only making moves
	 * that produce an immediate benefit over all other possible moves.
	 * 
	 * @param problem A reference to the TSP problem that will evaluate success
	 * @return
	 */
	public int[] greedyRoute(TSP problem)
	{
		// Create an int array to hold our route indexes
		int [] route = new int[problem.getNumLocations()];
		
		// Start with an initial route for us to then change
		// In route order is effectively as good as random
		for (int l=0; l<route.length; l++)
			route[l] = l;
		
		// We are now going to try and make changes that yield an
		// improvement and see if we can step towards the best 
		// overall solution.
		
		problem.resetEvaluations();
		
		double 	shortest  = 1000;  	// Dummy value
		double 	swapDistance;		// What is the current shortest swap distance
		int		shortestSwap = 0;	// Index of the position we swapped with
		double	previous;			// What was the previous best result?
		
		do
		{
			// Take a note of the previous best result
			previous = shortest;

			for (int s=0; s<route.length; s++)
			{
				shortestSwap = s;
				shortest = problem.scoreRoute(route);
				
				// Go through all possible steps from s and pick the shortest
				for (int n=s+1; n<route.length; n++)
				{
					// Try swapping the current location 's' with a location 
					// further down the route 'n' and see if we benefit
					swap(s,n,route);
					swapDistance = problem.scoreRoute(route);
					
					// If it improved the solution, take a note of 'n'
					if (swapDistance < shortest)
					{
						shortest = swapDistance;
						shortestSwap = n;
					}
					
					// Now swap back so we can try an alternative
					swap(n,s,route);
				}
				// We should now have the best move from our current position
				// so make that swap permanent
				swap(s,shortestSwap,route);
			}
		}
		while (shortest < previous); // Repeat until we get no further improvement

		// Return our best solution
		return route;
	}
	
	/**
	 * Swaps around the values at locations 'p1' and 'p2' in 'route'.
	 * 
	 */
	public void swap(int p1, int p2, int [] route)
	{
		int temp = route[p1];
		route[p1] = route[p2];
		route[p2] = temp;
	}

}
