import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.junit.Test;

public class ShopTestTask3 {

	@Test
	public void testGetShortestQueue()
	{
		Shop shop = new Shop(20);

		for (int tillNumber = 1;tillNumber <= 20;tillNumber++)
		{
			// Don't add any customers to queue 6, this will be our shortest queue
			if (tillNumber == 6)
				continue;

			Queue<Customer> queue = shop.getQueue(tillNumber);

			for (int i = 0;i < 5;i++)
				queue.offer(new Customer());
		}

		Queue<Customer> shortestQueue = shop.getShortestQueue();
		assertTrue("Getting the shortest queue failed", shortestQueue.isEmpty());
	}

	@Test
	public void testAddCustomerToShortestQueue()
	{
		Shop shop = new Shop(20);

		Customer customer = new Customer();

		Queue<Customer> shortestQueue = shop.getShortestQueue();
		shop.addCustomer(customer);

		for (int tillNumber = 1;tillNumber <= 20;tillNumber++)
		{
			Queue<Customer> queue = shop.getQueue(tillNumber);
			assertEquals("Queue " + tillNumber + " is the wrong length.", shortestQueue == queue ? 1 : 0, queue.size());
		}
	}
}
