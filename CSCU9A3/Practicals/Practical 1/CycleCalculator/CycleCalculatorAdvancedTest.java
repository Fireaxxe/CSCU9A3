import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class CycleCalculatorAdvancedTest
{
	private static final double ACCURACY = 0.001;

	private CycleCalculator cycleCalculator;
	
	@Before
	public void setup()
	{
		cycleCalculator = new CycleCalculator();		
	}
	
	@Test
	public void testGetTotalDuration()
	{
		assertEquals("Test adding up total durations failed:", 23.4, cycleCalculator.getTotalDuration(new double[] {0.5, 1.2, 3.4, 5.6, 2.32, 1.4, 8.98}), ACCURACY);
		assertEquals("Test adding up total durations failed:", 28.13, cycleCalculator.getTotalDuration(new double[] {2, 5.65, 4.8, 7.5, 6.98, 1.2}), ACCURACY);	
	}
}
