public class Boat extends Vehicle
{
	protected double 	displacement=0;
	
	public Boat(String n, double displaces)
	{
		name = n;
		displacement = displaces;
	}
	
	public String toString()
	{
		return "Boat - " + name + " : Displaces " + displacement; 
	}
}
