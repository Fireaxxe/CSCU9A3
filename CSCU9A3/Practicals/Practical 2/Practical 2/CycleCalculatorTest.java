import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CycleCalculatorTest
{
	private static final double ACCURACY = 0.001;

	private CycleCalculator cycleCalculator;

	@Before
	public void setup()
	{
		cycleCalculator = new CycleCalculator();		
	}
	
	@Test
	public void testCompetency()
	{
		assertEquals("Beginner competency level failed:", 1.0, cycleCalculator.getDuration(10, "Beginner", 0, true, 15, 0, false), ACCURACY);
		assertEquals("Intermediate competency level failed:", 1.0, cycleCalculator.getDuration(15, "Intermediate", 0, true, 15, 0, false), ACCURACY);
		assertEquals("Advanced competency level failed:", 1.0, cycleCalculator.getDuration(20, "Advanced", 0, true, 15, 0, false), ACCURACY);
	}
	
	@Test
	public void testCycleBuddy()
	{
		assertEquals("Test cycling with a buddy failed:", 1.0, cycleCalculator.getDuration(12, "Beginner", 0, false, 15, 0, false), ACCURACY);
		assertEquals("Test cycling alone failed:", 1.0, cycleCalculator.getDuration(10, "Beginner", 0, true, 15, 0, false), ACCURACY);
	}
	
	@Test
	public void testYearsExperience()
	{
		assertEquals("Test years experience failed:", 1.0, cycleCalculator.getDuration(11, "Beginner", 5, true, 15, 0, false), ACCURACY);
	}
	
	@Test
	public void testLessThan10()
	{
		assertEquals("Test temperature less than 10 failed:", 1.0, cycleCalculator.getDuration(9, "Beginner", 0, true, 0, 0, false), ACCURACY);
		assertEquals("Test temperature more than 10 failed:", 1.0, cycleCalculator.getDuration(10, "Beginner", 0, true, 15, 0, false), ACCURACY);
	}
	
	@Test
	public void testMoreThan20()
	{
		assertEquals("Test temperature more than 20 failed:", 1.0, cycleCalculator.getDuration(9, "Beginner", 0, true, 30, 0, false), ACCURACY);
		assertEquals("Test temperature less than 20 failed:", 1.0, cycleCalculator.getDuration(10, "Beginner", 0, true, 15, 0, false), ACCURACY);
	}
	
	@Test
	public void testWindSpeed()
	{
		assertEquals("Test windspeed at 10mph failed:", 1.0, cycleCalculator.getDuration(10, "Beginner", 0, true, 15, 10, false), ACCURACY);
		assertEquals("Test windspeed at 20mph failed:", 1.0, cycleCalculator.getDuration(9, "Beginner", 0, true, 15, 20, false), ACCURACY);
		assertEquals("Test windspeed at 70mph failed:", 1.0, cycleCalculator.getDuration(6, "Beginner", 0, true, 15, 70, false), ACCURACY);
	}
	
	@Test
	public void testRaining()
	{
		assertEquals("Test raining failed:", 1.0, cycleCalculator.getDuration(8, "Beginner", 0, true, 15, 0, true), ACCURACY);
		assertEquals("Test not raining failed:", 1.0, cycleCalculator.getDuration(10, "Beginner", 0, true, 15, 0, false), ACCURACY);
	}

}
