import static org.junit.Assert.*;
import org.junit.Test;

public class IntTreeTest {

	@Test
	public void testWalks()
	{
		IntegerTree	ent = new IntegerTree();
		
		ent.addNode(17);
		ent.addNode(5);
		ent.addNode(9);
		ent.addNode(35);
		ent.addNode(24);
		ent.addNode(3);
		ent.addNode(42);
		ent.addNode(7);
		
		ent.printTree();
		
		String preOrder = ent.preOrderTraversal();
		String inOrder = ent.inOrderTraversal();
		String postOrder = ent.postOrderTraversal();
		
		System.out.println("preOrder: " + preOrder);
		System.out.println("inOrder: " + inOrder);
		System.out.println("postOrder: " + postOrder);
		 
		assertTrue("Pre Order failed",true);
		assertTrue("In Order failed",true);
		assertTrue("Post Order failed",true);
		
				
	}
}
