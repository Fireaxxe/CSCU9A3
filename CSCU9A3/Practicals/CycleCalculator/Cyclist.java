
public class Cyclist {
	
	private final String competency;
	private final int numYearsExperience;

	public Cyclist(String competency, int numYearsExperience) {
		this.competency = competency;
		this.numYearsExperience = numYearsExperience;
	}

	public String getCompetency() {
		return competency;
	}
	public int getNumYearsExperience() {
		return numYearsExperience;
	}

	public boolean isBeginner() {
		return competency.equalsIgnoreCase("Beginner");
	}

	public boolean isIntermediate() {
		return competency.equalsIgnoreCase("Intermediate");
	}
	
	public boolean isAdvanced() {
		return competency.equalsIgnoreCase("Advanced");
	}
}
