
public class Stack 
{
	private int [] stack;
	private int top;
	private int size;
	
	public Stack()
	{
		top = -1;
		size = 50;
		stack = new int [50];
	}
	
	public Stack (int size)
	{
		top = -1;
		this.size = 50;
		stack = new int [this.size];
	}
	
	public boolean push (int item)
	{
		if (!isFull()) 
		{			
			top++;
			stack[top] = item;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int pop()
	{
		return stack[top--];
	}
	
	public boolean isEmpty()
	{
		return (top == -1);
	}
	    	
	public boolean isFull()
	{
		return  (top== stack.length -1);
	}
}
