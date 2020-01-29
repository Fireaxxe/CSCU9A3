
public class Main 
{
	public static void main(String[] args)
{
//		Stack intStack = new Stack ();
//		if (!intStack.isFull())
//		{
//			intStack.push(2);
//			intStack.push(4);
//			intStack.push(6);
//			intStack.push(9);
//		}
//	
//	System.out.println(intStack.pop());	
//	System.out.println(intStack.pop());	
//	System.out.println(intStack.pop());	
//	System.out.println(intStack.pop());	
		
	Person person1 = new Person ("cotsios", "123");
	Person person2 = new Person ("kotsiou", "321");
	
	PersonStack stack = new PersonStack();
	
	stack.push(person1);
	stack.push(person2);
	
	System.out.println(stack.pop().toString());
	System.out.println(stack.pop().toString());
	}
}
