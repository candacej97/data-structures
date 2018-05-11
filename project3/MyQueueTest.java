package project3;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit test for MyQueue class that uses MyNode class
 * 
 * @author Candace Johnson
 * @version 3/30/2017
 */
public class MyQueueTest {

//	Test the creation of an empty queue using peek()
	@Test
	public void testEmptyPeek() {
		try {
			MyQueue<Integer> line = new MyQueue<Integer>();
			assertNull("peek() does not indicate the new queue is empty.", line.peek());
		} catch (Exception e) {
			fail("Exception should not be thrown from peek() on an empty queue.");
		}
		
	}
	
//	Test the creation of an empty queue using poll()
	@Test
	public void testEmptyPoll() {
		try {
			MyQueue<Integer> line = new MyQueue<Integer>();
			assertNull("poll() does not indicate the new queue is empty.", line.poll());
		} catch (Exception e) {
			fail("Exception should not be thrown from poll() on an empty queue.");
		}
	}
	
//	Test populating a queue with a null - testing what front points to
	@Test
	public void testOfferNull() {
		MyQueue<Integer> line = new MyQueue<Integer>();
		try {
			line.offer(null);
			fail("offer(e) should not allow null to be added to the queue.");
		} 
		catch (NullPointerException e) { 
			// correct condition
		}
		catch (Exception e) {
			fail("Wrong exception thrown from offer(e) when adding null to the queue.");
		}
	}

//	Test populating a queue with one element - testing what front points to
	@Test
	public void testOffer1() {
		try{
			MyQueue<Integer> line = new MyQueue<Integer>();
			line.offer(3);
			assertTrue("peek() does not point to correct node.", line.peek() == 3);
		} catch (Exception e) {
			fail("Exception should not be thrown from offer(e) when adding a node.");
			}
	}
	
//	Test populating a queue with one element - testing what offer returns
	@Test
	public void testOfferReturn() {
		try {
			MyQueue<Integer> line = new MyQueue<Integer>();
			assertTrue("offer(e) does not return correct boolean.", line.offer(3));
		} catch (Exception e) {
			fail("Exception should not be thrown when using offer(e).");
			}
	}

//	Test populating a queue with two elements - testing what front points to
	@Test
	public void testOffer2() {
		try {
			MyQueue<Integer> line = new MyQueue<Integer>();
			line.offer(3);
			line.offer(5);
			line.poll();
			assertTrue("peek() does not point to correct node after 'offering' 2 nodes and"
					+ "'poll-ing' 1 away.", line.peek() == 5);
		} catch (Exception e) {
			fail("Exception should not be thrown from offer(e), poll(), or peek().");
		}
	}

//	Test populating a queue with a null after el added - testing what front points to
	@Test
	public void testNullOffer() {
		try{
			MyQueue<Integer> line = new MyQueue<Integer>();
			line.offer(3);
			line.offer(null);
			fail("offer(e) should not allow null to be added to the queue.");
		} catch (NullPointerException e) {
			// correct behavior
		} catch (Exception e) {
			fail("Wrong exception thrown from offer(e) when offer(null) is called.");
		}
	}

//	Test populating a queue with a casted type that is casted - should throw exception
	@Test
	public void testOfferType() {
		try{
			MyQueue<String> line = new MyQueue<String>();
			Object x = new Integer(0);
		    line.offer((String) x);
		    fail("offer(e) should not allow casted node to be added.");
		} catch (ClassCastException e) {
			// correct behavior
		} catch (Exception e) {
			fail("Wrong exception thrown from offer(e) when a casted node is added to"
					+ "the queue.");
		}
		
	}
	
//	Cannot test populating a queue with a different type - will not compile
//	Do not test for an IllegalArgumentException - per Piazza Question 147
	
}
