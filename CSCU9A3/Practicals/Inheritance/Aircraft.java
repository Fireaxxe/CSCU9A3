public class Aircraft extends Vehicle
{
    private int numWings;
    private double wingLift;
    public Aircraft(String n, int wings, double lift)
    {
        name = n;
        numWings = wings;
        wingLift = lift;
    }

    public String toString()
    {
        return "Aircraft - " + name + " : Lift " + wingLift;
    }
}