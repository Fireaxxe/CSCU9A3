public class Car extends Vehicle
{
	protected String	make="?";
	protected int		engineCC=0;

	public Car(String mk, String mod, int cc)
	{
		name = mod;
		make = mk;
		engineCC = cc;
	}
	
	public String toString()
	{
		return "Car - " + make + " " + name + ": " + engineCC; 
	}
}