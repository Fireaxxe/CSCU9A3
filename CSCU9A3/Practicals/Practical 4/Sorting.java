
public class Sorting {

    public long selectionSort(int[] data)
    {
        // Note the time at the start
        long startTime = System.nanoTime();

        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data.length; j++)
            {
                if (data[i] < data[j])
                {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }      

        // Return how much time has elapsed to do this task
        return System.nanoTime() - startTime;	        
    }
}
