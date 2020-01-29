import java.util.ArrayList;
public class TransportManager
{
    private ArrayList<Vehicle> vehicles;
    public TransportManager()
    {
        vehicles = new ArrayList<Vehicle>();
    }

    public void addVehicle(Vehicle v)
    {
        vehicles.add(v);
    }

    public void describeTransport()
    {
        for (Vehicle v : vehicles)
        {
            System.out.println(v.toString());
        }
    }
}