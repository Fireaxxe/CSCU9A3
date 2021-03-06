
public class CycleRide {
    protected String name;
    protected int numberMiles;
    protected boolean cycleAlone;

    public CycleRide()
    {
        numberMiles = 0;
        cycleAlone = true;
    }

    public CycleRide(String nm)
    {
        name = nm;
    }

    public CycleRide(int numberMiles, boolean cycleAlone) {
        this.numberMiles = numberMiles;
        this.cycleAlone = cycleAlone;
    }

    public int getNumberMiles() {
        return numberMiles;
    }

    public boolean isCycleAlone() {
        return cycleAlone;
    }

    public float getDifficultyFactor()
	{
		return MountainBikeRide.difficultyFactor();
	}
	
}