import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class ShopTestTask1 {

	private Shop shop;

	@Before
	public void setUp()
	{
		shop = new Shop();
	}

	@Test
	public void testInitialState()
	{
		Queue<Customer> queue = shop.getQueue();
		assertTrue("Shop queue not empty", queue.isEmpty());
	}

	@Test
	public void testAddCustomer()
	{
		Customer customer = new Customer();
		shop.addCustomer(customer);

		Queue<Customer> queue = shop.getQueue();
		assertEquals("Customer not added successfully to the queue.", 1, queue.size());
		assertEquals("Customer not added succcesfully to the queue.", customer, queue.peek());
	}

	@Test
	public void testGetNextCustomer()
	{
		Customer customer = new Customer();
		shop.addCustomer(customer);

		assertEquals("Customer not added successfully to the queue.", 1, shop.getQueue().size());
		assertEquals("Customer not added succcesfully to the queue.", customer, shop.getNextCustomer());
	}
}
