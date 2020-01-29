
public class Weather {

    protected int temp;
    protected int windSpeed;
    protected boolean isRaining;

    public Weather ()
    {
        temp = 13;
        windSpeed = 0;
        isRaining = false;
    }

    public Weather(int temp, int windSpeed, boolean isRaining) {
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.isRaining = isRaining;
    }	

    public int getTemp() 
    {
        return temp;
    }

    public int getWindSpeed() 
    {
        return windSpeed;
    }

    public boolean getIsRaining()
    {
        return isRaining;
    }
}
