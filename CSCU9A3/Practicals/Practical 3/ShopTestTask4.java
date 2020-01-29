import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShopTestTask4 {

	@Test
	public void testAddSimpleCoffeeCustomerToShortestQueue()
	{
		Shop shop = new Shop();

		Customer customer = new Customer(false);
		shop.addCustomer(customer);

		assertEquals("Simple coffee customer not added successfully to the fancy coffee queue.", 1, shop.getQueue(1).size());
		assertEquals("Simple coffee customer added to fancy coffee queue too.", 0, shop.getQueue(2).size());
		assertEquals("Simple coffee customer not added successfully to the fancy coffee queue.", customer, shop.getQueue(1).peek());
	}

	@Test
	public void testAddFancyCoffeeCustomerToShortestQueue()
	{
		Shop shop = new Shop();

		Customer customer = new Customer(true);
		shop.addCustomer(customer);

		assertEquals("Fancy coffee customer not added successfully to the fancy coffee queue.", 1, shop.getQueue(2).size());
		assertEquals("Fancy coffee customer added to simple coffee queue too.", 0, shop.getQueue(1).size());
		assertEquals("Fancy coffee customer not added successfully to the fancy coffee queue.", customer, shop.getQueue(2).peek());
	}

	@Test
	public void testGetSimpleCoffeeCustomer()
	{
		Shop shop = new Shop();

		Customer customer1 = new Customer(false);
		Customer customer2 = new Customer(false);

		shop.addCustomer(customer1);
		shop.addCustomer(customer2);

		assertEquals("Getting (and removing) next customer from the queue failed.", customer1, shop.getNextCustomer(1));
		assertEquals("Getting (and removing) next customer from the queue failed.", customer2, shop.getNextCustomer(1));
		assertEquals("Simple coffee queue is not empty after getting all customers.", 0, shop.getQueue(1).size());
		assertEquals("Fancy coffee queue is not empty after getting all customers.", 0, shop.getQueue(2).size());
	}

	@Test
	public void testGetFancyCoffeeCustomer()
	{
		Shop shop = new Shop();

		Customer customer1 = new Customer(true);
		Customer customer2 = new Customer(true);

		shop.addCustomer(customer1);
		shop.addCustomer(customer2);

		assertEquals("Getting (and removing) next customer from the queue failed.", customer1, shop.getNextCustomer(2));
		assertEquals("Getting (and removing) next customer from the queue failed.", customer2, shop.getNextCustomer(2));
		assertEquals("Simple coffee queue is not empty after getting all customers.", 0, shop.getQueue(1).size());
		assertEquals("Fancy coffee queue is not empty after getting all customers.", 0, shop.getQueue(2).size());
	}

}
