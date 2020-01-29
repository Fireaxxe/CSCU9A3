/**
 * Please provide your student ID below.
 * 
 * 	Student ID: ????????
 */

/**
 * Vehicle is the base class for all Vehicle types.
 * 
 */

public class Vehicle implements Comparable<Vehicle>
{
    protected String name;		// The name of the vehicle
    protected double speed;		// The current speed of the vehicle 
    protected double maxSpeed;	// The maximum speed of the vehicle
    protected double heading;	// The current heading of the vehicle
    protected double maxTurn;	// The maximum turn of the vehicle

    /**
     * Default Constructor 
     */
    public Vehicle()
    {
        name = "?";
        maxSpeed = 0;
        speed = 0;
        heading = 0;
        maxTurn=0;
    }

    /**
     * Constructs a vehicle based on its name
     * 
     * @param nm The name to give to the vehicle
     */
    public Vehicle(String nm)
    {
        name = nm;       
    }

    /**
     * Constructs a vehicle based on its name and maximum speed
     * 
     * @param nm The name to give to the vehicle
     * @param maxV The maximum speed for the vehicle
     */
    public Vehicle(String nm, double maxV) // *
    {
        name = nm;
        maxSpeed = maxV;
    }

    /**
     * Constructs a vehicle based on its name, maximum
     * maximum speed and maximum turn.
     * 
     * @param nm The name to give to the vehicle
     * @param maxV The maximum speed for the vehicle
     * @param maxT The maximum turn of the vehicle
     */
    public Vehicle(String nm, double maxV, double maxT)
    {
        name = nm;
        maxSpeed = maxV;
        maxTurn = maxT;
    }

    /**
     * Gets the name of the vehicle.
     * 
     * 	 * @return The name of vehicle
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the maximum speed of the vehicle.
     * 
     *   * @return The maximum speed of vehicle
     */
    public double getMaxSpeed()
    {
        return maxSpeed;
    }

    /**
     * Gets the maximum turn of the vehicle.
     * 
     *   * @return The maximum turn of vehicle
     */
    public double getMaxTurn()
    {
        return maxTurn;
    }

    /**
     * Adjust the heading of the vehicle
     * 
     * @param degrees The number of degrees to change the heading by
     */
    public void turn(double degrees)
    {
        if ((heading + degrees) % 360 <= maxTurn) 
        {
            heading = (heading + degrees) % 360;
        } 
        else 
        {
            heading = maxTurn; // If heading surpasses maxTurn
        }
    }

    /**
     * Adjust the current speed of the vehicle
     * 
     * @param v	The velocity to adjust the current speed by
     */
    public void accelerate(double v)
    {
        if (speed + v <= maxSpeed) 
        {
            speed = speed + v;
        } 
        else 
        {
            speed = maxSpeed; // If speed surpasses maxSpeed
        }
    }

    /**
     * Get the current speed of the vehicle
     * 
     * @return The current speed of the vehicle
     */
    public double getCurrentSpeed() 
    {
        return speed; 
    }

    /**
     * Get a description of the Vehicle as a String
     */
    public String toString()
    {
        return "Vehicle - " + name + ": Speed " + speed + ", Max " + maxSpeed;
    }

    /**
     * Compare the current Vehicle with the Vehicle 'v' and return a negative
     * value if the current Vehicle is less than 'v', 0 if it is the same and
     * a positive value if it is greater.
     */
    @Override
    public int compareTo(Vehicle v) 
    {
        return name.compareTo(v.name);
    }

}
