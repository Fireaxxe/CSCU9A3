import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ShopTestTask2 {

    @Test
    public void testInitialState()
    {
        Shop shop = new Shop(20);

        for (int tillNumber = 1;tillNumber <= 20;tillNumber++)
        {
            assertNotNull("Queue " + tillNumber + " not initialised within the array.", shop.getQueue(tillNumber));
            assertTrue("Queue " + tillNumber + " not empty", shop.getQueue(tillNumber).isEmpty());
        }
    }

    @Test
    public void testTillNumber()
    {
        Shop shop = new Shop(1);

        try
        {
            assertNotNull(shop.getQueue(1));
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            assertTrue("Till numbers start at 1, array indexes start at 0!", false);
        }
    }

    @Test
    public void testAddCustomer()
    {
        Shop shop = new Shop(20);

        Customer customer = new Customer();
        shop.addCustomer(customer, 1);

        assertEquals("Customers not added successfully to the queues.", customer, shop.getQueue(1).peek());

        for (int tillNumber = 2;tillNumber <= 20;tillNumber++)
        {
            assertTrue("Queue " + tillNumber + " should still be empty, we only added to queue 1!", shop.getQueue(tillNumber).isEmpty());
        }
    }

    @Test
    public void testGetNextCustomer()
    {
        Shop shop = new Shop(20);

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();

        shop.addCustomer(customer1, 1);
        shop.addCustomer(customer2, 3);
        shop.addCustomer(customer3, 1);

        assertEquals("Getting next customer from the queue failed.", customer1, shop.getNextCustomer(1));
        assertEquals("Getting next customer from the queue failed.", customer2, shop.getNextCustomer(3));
        assertEquals("Getting next customer from the queue failed.", customer3, shop.getNextCustomer(1));

        for (int tillNumber = 1;tillNumber <= 20;tillNumber++)
        {
            assertTrue("Queue " + tillNumber + " is not empty after getting all customers.", shop.getQueue(tillNumber).isEmpty());
        }
    }
}
