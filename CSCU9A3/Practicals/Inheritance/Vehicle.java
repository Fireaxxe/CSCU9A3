public class Vehicle
{
    protected String name;
    private double speed;
    private double maxSpeed;
    private double heading;
    public Vehicle()
    {
        name = "?";
        maxSpeed = 0;
        speed = 0;
    }

    public Vehicle(String nm)
    {
        name = nm;
    }

    public void turn(double degrees)
    {
        heading = (heading + degrees) % 360;
    }

    public void accelerate(double v)
    {
        speed = speed + v;
    }

    public double getCurrentSpeed() 
    { 
        return speed; 
    }

    public String toString()
    {
        return "Vehicle - " + name + ": " + speed;
    }
    // Further methods that apply to all forms of Vehicle...
}