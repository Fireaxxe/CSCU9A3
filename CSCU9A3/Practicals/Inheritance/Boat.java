public class Boat extends Vehicle
{
    private double displacement;
    private int numSails;
    public Boat(String n, double displace, int sails)
    {
        name = n;
        displacement = displace;
        numSails = sails;
    }

    public String toString()
    {
        return "Boat - " + name + " : Displaces " + displacement;
    }
}