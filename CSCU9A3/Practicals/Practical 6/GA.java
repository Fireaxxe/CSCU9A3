import java.util.ArrayList;

/**
 * 
 */

/**
 * @author David
 *
 */
public class GA {

	/**
	 * evolvePattern attempts to work out what a target pattern is by evolving
	 * a population of solutions over a number of generations. It starts with an
	 * initial random set of trial patterns and finds out how good (or bad) they
	 * are. It then loops round a fixed number of generations and for each
	 * generation, it does the following:
	 * 
	 * - Pick two parents via tournament selection
	 * - Produce a new offspring by combining values from the parents
	 * - Mutating some values (with a very low 0.5% chance)
	 * - Add the new offspring to a replacement population
	 * - Replace the old population with the new population
	 * 
	 * @param target The pattern it is trying to guess 
	 * @param s		A reference to solver which will give us a score
	 * @return		The pattern it has evolved
	 */
	public Pattern evolvePattern(Pattern target, Solver s)
	{
		// The size of our population 	
		int popsize = 40;		// 40
		
		// How many new populations should we generate? 
		int generations = 100;	// 100	
		
		// How often should we randomnly flip a bit
		float mutationRate = 0.005f;	// 0.005
		
		// What proportion of the population should compete to breed?
		float tournamentProportion = 0.80f; // 0.80f (80% which is quite competitive)
		
		// The size of the tournament where larger tournaments
		// are more competitive (more chance of best current 
		// solution being selected for the tournament). We 
		// don't want to end up with the whole population or
		// we will just be hill climbing in disguise
		int tournamentSize = (int)(popsize*tournamentProportion);	
		
		
		Chromosome[] pop;		// The parent population
		Chromosome[] child; 	// The child population that will replace the above

		// Initialise our population
		pop = new Chromosome[popsize];
		child = new Chromosome[popsize];
		
		// Create some random solutions
		for (int p=0; p<popsize; p++)
			pop[p] = new Chromosome(target.getLength(),target.getHeight());
		
		// Score our population
		for (int p=0; p<popsize; p++)
			s.scoreSolution(pop[p],target);

		// Find out the best current solution (it's probably not very good
		// but with a GA, it's all relative to the current population
		int best = getIndexBest(pop);

		// Now step through a number of generations of the populations
		for (int g=0; g<generations; g++)
		{	
			// Create a new generation using our current generation
			for (int c=0; c<pop.length; c++)
			{
				// Select two individuals for breeding 
				Chromosome p1 = tournamentSelect(pop,tournamentSize);
				Chromosome p2 = tournamentSelect(pop,tournamentSize);
				
				// Crossover (combine) two parents to produce new offspring
				child[c] = onePointCrossover(p1,p2);
				
				// Mutation 
				child[c].mutate(mutationRate);
				
				// Score new child
				s.scoreSolution(child[c], target);				
			}
			
			
			// Put the best from the last generation into new generation
			// provided it is better than the child solution that would go there
			// Location does not matter since selection is randomised
			if (child[0].getFitness() > pop[best].getFitness())
				pop[0] = pop[best];
			else
				pop[0] = child[0];
				
			// Now copy the rest of the child generation to new parent generation
			for (int p=1; p<pop.length; p++) pop[p] = child[p];
			
			// Find out what the new best result is
			best = getIndexBest(pop);
			
			// Print out the best fitness score every 10 generations
			if (g % 10 == 0)
			{
				best = getIndexBest(pop);
				String debug = String.format("%d\t%.2f",g,pop[best].getFitness());
				System.out.println(debug);
				// System.out.println(pop[best]);
			}
			
		}
		
		
		// Print it out if you want...
		// System.out.println("Best\n" + pop[best] + " Score " + pop[best].getFitness());
		best = getIndexBest(pop);
		return pop[best].getPattern();		
	}

	/**
	 * Evolve route uses an evolutionary approach to trying to solve the travelling
	 * salesman problem. It creates a population of different orders to travel to each
	 * location in and breeds the solutions which tend to have the shortest journeys
	 * relative to their peers. It uses the same steps as outlined for evolvePattern
	 * with a modified crossover (combination) stage to ensure we do not visit the same
	 * place twice and forget to visit other locations. Apart from that, the code is nearly
	 * identical to evolvePattern.
	 * 
	 * @param problem	A reference to the TSP problem to be solved
	 * @return			An int array containing the order to visit the locations in
	 */
	public int[] evolveRoute(TSP problem)
	{
		// The size of our population 	
		int popsize = 40;		// 40
		
		// How many new populations should we generate? 
		int generations = 100;	// 100
		
		// How often should we randomnly flip a bit
		float mutationRate = 0.2f;	// 0.2 = 20% which is very high
		
		// What proportion of the population should compete to breed?
		float tournamentProportion = 0.80f; // 0.80f (80% which is quite competitive)
		
		// The size of the tournament where larger tournaments
		// are more competitive (more chance of best current 
		// solution being selected for the tournament). We 
		// don't want to end up with the whole population or
		// we will just be hill climbing in disguise
		int tournamentSize = (int)(popsize*tournamentProportion);	

		Chromosome[] pop;		// The parent population
		Chromosome[] child; 	// The child population that will replace the above

		// Initialise our population
		pop = new Chromosome[popsize];
		child = new Chromosome[popsize];
		
		// Initialise our solutions, giving them a shared
		// reference to the problem
		for (int p=0; p<popsize; p++)
			pop[p] = new Chromosome(problem);
		
		
		// Score our population - this uses the reference
		// to the problem to ask for a score for a solution
		for (int p=0; p<popsize; p++)
			pop[p].score();

		// Get the current best solution
		int best = getIndexBest(pop);

		for (int g=0; g<generations; g++)
		{	
			// Create a new generation using our current generation
			for (int c=0; c<pop.length; c++)
			{
				// Select two individuals for breeding 
				Chromosome p1 = tournamentSelect(pop,tournamentSize);
				Chromosome p2 = tournamentSelect(pop,tournamentSize);
				
				// Crossover two parents to produce new offspring
				child[c] = routingCrossover(p1,p2);
				
				// Mutation 
				child[c].mutateRoute(mutationRate);
				
				// Score new child
				child[c].score();				
			}
			
			
			// Put the best from the last generation into new generation
			// provided it is better than the child solution that would go there
			// Location does not matter since selection is randomised
			if (child[0].getFitness() > pop[best].getFitness())
				pop[0] = pop[best];
			else
				pop[0] = child[0];
				
			// Now copy the rest of the child generation to new parent generation
			for (int p=1; p<pop.length; p++) pop[p] = child[p];
			
			// Find out what the new best result is
			best = getIndexBest(pop);
			
			// Print out the best fitness score every 100 generations
//			if (g % 100 == 0)
//			{
//				best = getIndexBest(pop);
//				float bestFitness = pop[best].getFitness();
//				String progress = String.format("%d\t%.2f",g,bestFitness);
//				System.out.println(progress);
//			}
			
		}
		
		// Find the final best solution
		best = getIndexBest(pop);
		
		// Convert float array to an integer array
		float [] genes = pop[best].getPattern().getGenes();
		int [] route = new int[genes.length];
		
		// Copy the float values into route by casting them as ints
		for (int r=0; r<route.length; r++)
			route[r] = (int)genes[r];
		
		// Return our int array
		return route;
				
	}
	
	/**
	 * Go through the population and find the individual with the
	 * best overall score (where best is low in this case).
	 *
	 * @param pop	The population to search
	 * @return		The index of the best solution in 'pop'
	 */
	public int getIndexBest(Chromosome pop[])
	{
		int best = 0;	// Assume initial solution is best
		
		// Start comparing from the next one after it
		for (int p=1; p<pop.length; p++)
		{
			// Is the one at 'p' better than our current 'best'
			if (pop[p].getFitness() < pop[best].getFitness())
			{
				best = p;	// Take a note of the index of the new best result
			}
		}
		return best;
	}

	/**
	 * Go through the population and find the individual with the
	 * best overall score (where best is low in this case).
	 *
	 * @param pop	The population to search
	 * @return		A reference to the best solution in 'pop'
	 */
	public Chromosome getRefBest(Chromosome pop[])
	{
		int best = getIndexBest(pop);
		return pop[best];
	}

	/**
	 * Prints out the whole population so you can see the fitness score
	 * and suggested solution for each individual
	 * 
	 * @param pop	The population to print
	 * @param title	A title to go at the top
	 */
	public void printPopulation(Chromosome pop[], String title)
	{
		System.out.println(title);
		
		for (int c=0; c<pop.length; c++)
		{
			System.out.println(c + " : " + pop[c].getFitness() + "\n" + pop[c]);
		}	
	}
	
	/**
	 * Go through the population and find the individual with the
	 * worst overall score (where worst is high in this case).
	 *
	 * @param pop	The population to search
	 * @return		The index of the best solution in 'pop'
	 */
	public int getIndexWorst(Chromosome pop[])
	{
		int worst = 0;	// Assume the first is worst
		
		// Start at the next position in the population
		// and see if we can find a worse one
		for (int p=1; p<pop.length; p++)
		{
			// Is the one at 'p' worse that our current 'worst'
			if (pop[p].getFitness() > pop[worst].getFitness())
			{
				worst = p;	// Remember the index of our new worst solution
			}
		}
		return worst;
	}

	/**
	 * Selects a random set of individuals from 'pop' until it has reached
	 * 'size' and then returns this sub set of the population. This method is most
	 * often used in tournament selection to select the tournament.
	 * 
	 * @param pop	The population to select from
	 * @param size	The size of the sub set of individuals to pick
	 * @return		An array of references to individuals that is our sub set
	 */
	public Chromosome [] selectFrom(Chromosome[] pop, int size)
	{
		// Create an array of Chromosomes capable of holding our individuals
		Chromosome [] sample = new Chromosome[size];
	
		// Randomly pick individuals from the population until we have the
		// required amount.

		int items = 0;
		int individual;
		while (items < size)
		{	
			individual = (int)(Math.random() * pop.length);
			sample[items] = pop[individual]; // Copy the reference
			items++;
		}
		
		return sample;
	}
	
	/**
	 * Select a random set of individuals from 'pop' and then return the best
	 * individual from that set. The larger the size of the sub set with 
	 * respect to the overall population, the more likely the best overall
	 * solution will be in the sub set (the tournament) and the more likely they
	 * are to be returned. We want to avoid always picking the best otherwise
	 * we end up in a hill climbing situation.
	 * 
	 * @param pop	The population to select from
	 * @param size	The size of tournament to use
	 * @return		The best individual in the tournament
	 */
	public Chromosome tournamentSelect(Chromosome[] pop, int size)
	{
		// Randomly pick individuals from the population until we have enough
		// for our tournament.
		Chromosome [] tournament = selectFrom(pop,size);

		// Now go through tournament and return the best. Note that the bigger the
		// tournament, the more chance there will be that the best individual
		// is in there
		return getRefBest(tournament);
	}
	
	/**
	 * Select a random set of individuals from 'pop' and then return the worst
	 * individual from that set. The larger the size of the sub set with 
	 * respect to the overall population, the more likely the worst overall
	 * solution will be in the sub set (the tournament) and the more likely they
	 * are to be returned. 
	 * 
	 * @param pop	The population to select from
	 * @param size	The size of tournament to use
	 * @return		The worst individual in the tournament
	 */
	public int inverseTournamentSelect(Chromosome[] pop, int size)
	{
		// Randomly pick individuals from the population until we have enough
		// for our tournament.
		Chromosome [] tournament = selectFrom(pop,size);

		// Now go through tournament and return the worst. Note that the bigger the
		// tournament, the more chance there will be that the worst individual
		// is in there
		
		return getIndexWorst(tournament);
	}
	

	// The following methods provide different ways of combining the
	// 'genetic material' of our individuals to produce new offspring.
	
	/**
	 * This method produces a new offspring by taking all the genes from one
	 * parent up to a random point and then takes the remaining genes from the
	 * second parent from this point onward. It is called one point crossover 
	 * since there is only one point where you cross from taking genes from one
	 * parent to taking them from the other parent.
	 * 
	 * @param p1	Parent one
	 * @param p2	Parent two
	 * @return		A new offspring that combines the genes from 'p1' and 'p2'
	 */
	public Chromosome onePointCrossover(Chromosome p1, Chromosome p2)
	{	
		int numGenes = p1.numGenes();
		int crossoverPoint = (int)(Math.random()*numGenes);
		Chromosome child = new Chromosome(p1); // Use p1 as a template
		
		int g=0;
		while (g<crossoverPoint)
			child.setGene(g,p1.getGene(g++));
		while (g<numGenes)
			child.setGene(g,p2.getGene(g++));
		
		return child;
	}

	/**
	 * This method produces a new offspring by taking genes at random from either
	 * parent one or parent two. For each gene (parameter/bit value/location index), 
	 * it decides at random which parent it should draw the new value from.
	 * 
	 * @param p1	Parent one
	 * @param p2	Parent two
	 * @return		A new offspring that uniformly combines the genes from 'p1' and 'p2'
	 */
	public Chromosome uniformCrossover(Chromosome p1, Chromosome p2)
	{
		Chromosome child = new Chromosome(p1); // Use p1 as a template
		
		for (int g=0; g<child.numGenes(); g++)
		{
			if (Math.random() < 0.5f)
				child.setGene(g,p1.getGene(g));
			else
				child.setGene(g,p2.getGene(g));
		}
		return child;
	}
	
	/**
	 * This method produces a new offspring by combining genes from parent one
	 * and parent two in such a way that we do not get duplicate index values
	 * in the offspring and all values are represented just once. It is used
	 * for routing problems like TSP where this constraint is important. You could
	 * just randomnly combine values but the evolutionary process would take a lot
	 * longer than it needs to.
	 * 
	 * @param p1	Parent one
	 * @param p2	Parent two
	 * @return		A new offspring that combines the genes from 'p1' and 'p2'
	 */

    public Chromosome routingCrossover(Chromosome p1, Chromosome p2) 
    { 
		// Convert genome arrays into their set of routes
		ArrayList<Integer> p1Routes = p1.getRoutes();
		ArrayList<Integer> p2Routes = p2.getRoutes();
		
		int n1,location;
		
		Chromosome child = p1.clone();

		// Make a number of swaps equal to the
		// number of locations in the route
		int locations = p1Routes.size();
		for (int r=0; r<locations; r++)
		{
			// Half the time we should stick with parent one's solution
			if (Math.random() <= 0.5) continue;
			
			// Pick a random index			
			n1 = (int)(Math.random()*p1Routes.size());
			
			// Get the location that corresponds to it
			location = p1Routes.get(n1);
			
			// Now find its position in parent 2's routes
			int v=0;
			boolean found = false;
			
			for (v=0; v<p2Routes.size() && !found; v++)
				if (p2Routes.get(v) == location)
				{
					found = true;
					break;
				}
					
			if (!found)
			{
				System.err.println("Didn't find location in second parent!");
				System.exit(0);
			}
								
			// Remove old position
			p1Routes.remove(n1);
			// Insert new position or add to end if last place
			if (v < p1Routes.size())
				p1Routes.add(v, location);
			else
				p1Routes.add(location);
		}
		
		for (int g=0; g<p1Routes.size(); g++)
			child.setGene(g, p1Routes.get(g));
		
		return child;
			
    }

}
