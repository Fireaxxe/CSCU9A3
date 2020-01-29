public class TransportTest
{
    TransportManager manager = new TransportManager();
    public static void main(String[] args)
    {
        TransportTest test = new TransportTest();
        test.go();
    }

    public void go()
    {
        Vehicle v = new Vehicle("The Vehicle");
        Boat b = new Boat("Maltese Falcon",1240,15);
        Aircraft a = new Aircraft("Concorde",2, 5000);
        manager.addVehicle(v);
        manager.addVehicle(b);
        manager.addVehicle(a);
        manager.describeTransport();
    }
}