public class Yacht extends Boat
{
    private int masts;  
    private int sails;  
    private boolean raised;
    
    public Yacht(String nm, double displaces, int masts, int sails)
    {
        super(nm, displaces);   //constructor from super class
        this.masts = masts;
        this.sails = sails;
        this.raised = false; 
    }
    
    public void raiseSails()
    {
        this.raised = true;
    }
    
    public void lowerSails()
    {
        this.raised = false;
    }
    
    public boolean sailsRaised()
    {
    	return raised;
    }
    
    public String toString()
    {
    	String Sailsraised;
    	
    	if (raised)
    	{    
    	    Sailsraised = "Sails are raised.";
    	}
    	else 
    	{
    		Sailsraised = "Sails are not raised.";
    	}
    	return "Yacht - " + name + ": Displacement " + displacement + ", Masts " + masts + ": Sails " + sails + " " + Sailsraised ;
    }
}
