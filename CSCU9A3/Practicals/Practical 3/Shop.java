import java.util.LinkedList;
import java.util.Queue;

/**
 * A basic model of a shop, consisting of 1 queue of customers.
 *
 * @author cma
 * @date 11/2011
 *
 */
public class Shop
{
	/**
	 * A queue of all customers currently in the shop.
	 */
	private Queue<Customer> queue;
	private Queue<Customer>[] queues;
	
	public Shop(int numberofTills)
	{
		// Java supports multiple types of queues so we cannot simply ask for a new Queue...
		// One type of queue is a LinkedList, which is what we are using here.
		// For more information check the Javadocs.
		queues = new Queue[numberofTills + 1];
		for (int i = 1; i < numberofTills + 1; i++) {
			queues[i] = new LinkedList<Customer>();
		}
		
	}
	
	/**
	 * Gets the queue of customers currently in the shop.
	 *
	 * @return the queue of customers currently in the shop.
	 */
	public Queue<Customer> getQueue()
	{
		return queue;
	}
	
	public Queue<Customer> getQueue(int tillNumber)
	{
		return queues[tillNumber];
	}
	
	/**
	 * Add a new customer to the back of the queue.
	 *
	 * @param customer the customer to enqueue.
	 */
	public void addCustomer(Customer customer)
	{
		// TODO: add a customer to the queue
		getShortestQueue().add(customer);
		
	}
	
	public void addCustomer(Customer customer, int tillNumber)
	{
		// TODO: add a customer to the queue
		queues[tillNumber].add(customer);
	}
	
	/**
	 * Get the next customer from the front of the queue.
	 *s
	 * @return the next customer in the queue.
	 */
	public Customer getNextCustomer()
	{
		// TODO: return (and remove) the next customer from the queue
		return queue.poll();
	}
	
	public Customer getNextCustomer(int tillNumber)
	{
		// TODO: return (and remove) the next customer from the queue
		return queues[tillNumber].poll();
	}
	
	public Queue<Customer> getShortestQueue(){
		int shortest= 1;
		for (int i = 2; i < queues.length; i++) {
			if((queues[i].size()) < (queues[shortest]).size()){
				shortest = i;
			}
		}
		return queues[shortest];
	}
}
