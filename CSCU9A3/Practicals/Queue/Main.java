
public class Main {

	public static void main(String[] args) {
//		Intq q = new Intq();
//		q.enqueue(3);
//		q.enqueue(4);
//		q.enqueue(6);
//		
//		q.showAll();
		//System.out.println(q.dequeue());
		//System.out.println(q.dequeue());
		//System.out.println(q.dequeue());
		Personq q = new Personq();
		q.enqueue(new Person("cotsios", "123"));
		q.enqueue(new Person("kotsiou", "321"));
		
		q.showAll();
	}
}
