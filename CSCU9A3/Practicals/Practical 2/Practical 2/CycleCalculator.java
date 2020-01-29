/**
 * CycleCalculator class which uses Colins top secret rules to calculate the 
 * estimated duration of a cycle given certain information about the cyclist, 
 * the route and the weather.
 */
public class CycleCalculator 
{
    // The old method! This method converts our old parameters into the nice objects to pass to
    // our new improved method. We have this method as a way to use our existing unit tests and check that they continue 
    // to pass as you make your changes.
    public double getDuration(int numMiles, String competency, int numYearsExp, boolean cyclingAlone, int temp, int windSpeed, boolean isRaining) 
    {
        CycleRide cycleRide = new CycleRide(numMiles, cyclingAlone);
        Cyclist cyclist = new Cyclist(competency, numYearsExp);
        Weather weather = new Weather(temp, windSpeed, isRaining);

        return getDuration(cycleRide, cyclist, weather);
    }

    // The nice new method that we're refactoring. Refactor more!
    private double getDuration(CycleRide cycleRide, Cyclist cyclist, Weather weather) 
    {
        double speed = 0;

        // Competency
        if (cyclist.isBeginner())
        {
            speed = 10;
        }
        else if (cyclist.isIntermediate())
        {
            speed = 15;
        }
        else if (cyclist.isAdvanced())
        {
            speed = 20;
        }

        // Cycling buddy
        if (!cycleRide.isCycleAlone())
        {
            speed = speed * 1.2;
        }

        // Experience
        speed = speed + (cyclist.getNumYearsExperience() * 0.2);

        // Weather:

        // Temperature
        if (weather.getTemp() <= 10)
        {
            speed = speed - ((10 - weather.getTemp()) * 0.1);
        }
        else if (weather.getTemp() >= 20)
        {
            speed = speed - ((weather.getTemp() - 20) * 0.1);
        }

        // Wind
        if (weather.getWindSpeed() >= 15)
        {
            speed = speed - (weather.getWindSpeed() / 15);
        }

        // Rain
        if (weather.getIsRaining())
        {
            speed = speed - 2;
        }

        return cycleRide.getNumberMiles() / speed;
    }
}