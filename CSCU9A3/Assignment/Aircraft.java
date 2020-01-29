public class Aircraft extends Vehicle
{
	private int 	numWings=0;
	private double 	wingArea=0;
	
	public Aircraft(String n, int wings, double area)
	{
		name = n;
		setNumWings(wings);
		wingArea = area;
	}
	
	public String toString()
	{
		return "Aircraft - " + name + " : Wing Area " + wingArea + "\nNumber of wings: " + numWings; 
	}

	public int getNumWings() 
	{
		return numWings;
	}

	public void setNumWings(int numWings) 
	{    
		this.numWings = numWings;
	}
}