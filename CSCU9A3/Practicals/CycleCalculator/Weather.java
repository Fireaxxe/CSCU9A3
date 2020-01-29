
public class Weather 
{
	protected int temp;
	protected int windSpeed;
	protected boolean isRaining; 


	public Weather (int temp, int windSpeed, boolean isRaining)
	{
		this.temp = temp; 
		this.windSpeed = windSpeed; 
		this.isRaining = isRaining; 
	}
	
	public int getTemp()
	{
		return temp;
	}
	
	public int getwindSpeed()
	{
		return windSpeed;
	}
	
	public boolean getisRaining()
	{
		return isRaining;
	}
}