import java.util.stream.*;
/**
 * CycleCalculator class which uses Colins top secret rules to calculate the 
 * estimated duration of a cycle given certain information about the cyclist, 
 * the route and the weather.
 */
public class CycleCalculator
{  
    double speed = 0;
    public double getDuration(int numMiles, String competency, 
    int numYearsExperience, boolean cyclingAlone, 
    int temp, int windSpeed, boolean isRaining)
    {

        if (competency.equals("Beginner")) 
        { speed = 10;
        }
        else if (competency.equals("Intermediate"))
        { speed = 15;
        }
        else if (competency.equals("Advanced"))
        { speed = 20;
        }   

        if (!cyclingAlone)
        {
            speed = speed * 1.2;  //beginner speed = 10 * 1.2 (+20% from the base speed = 120%)
        }

        speed = speed + (numYearsExperience * 0.2); // beginner speed = 10 + ( 5 years * 0.2)

        if (temp < 10 || temp > 20)
        {
            speed = speed - (0.1 * speed); //if temp is <10 or >20 then  speed(10) - (0.1 * 10(speed))
        }

        if (isRaining)
        {
            speed = speed - 2;
        }

        int mph = 15;
        int avSpeed = 1; // decrease average speed by 1
        while(windSpeed >= mph)
        {             
            if (windSpeed >= mph && windSpeed < mph+15)
            {
                speed = (windSpeed - avSpeed) % speed;
            }
            avSpeed++;
            mph = mph + 15;
        }

        // if (windSpeed >= 15 && windSpeed < 30)
        // { 
            // speed = (windSpeed - 1) % speed;
        // }
        // else if (windSpeed >= 30 && windSpeed < 45)
        // {
            // speed = (windSpeed -2) % speed;
        // }
        // else if (windSpeed >= 45 && windSpeed < 60)
        // {
            // speed = (windSpeed -3) % speed;
        // }
        // else if (windSpeed >= 60 && windSpeed <75)
        // {
            // speed = (windSpeed -4) % speed;
        // }

        double duration = numMiles / speed;
        return duration;
    }

    // advanced work: arrays
    public double getTotalDuration(double[] durations)
    {
        // fill in!
        double totalsum = 0;  
        for (int i = 0; i < durations.length; i++) {  //i is a counter
            durations[i] += totalsum;                 // add each element to totalsum
            totalsum = durations[i];                  
        }                                             //the whole for loop is to add everyelement to totalsum

        double TotalDuration = totalsum;      
        return TotalDuration ;
    }

}
