package project3;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

/**
 * JUnit test for MyStack class that uses MyNode class
 * 
 * @author Candace Johnson
 * @version 3/29/2017
 */
public class MyStackTest {

//	Test if empty stack is empty using peek method (should throw exception)
	@Test
	public void testEmptyStack() {
		try{
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.peek();
			fail("Exception was not thrown from peek() when the stack is empty.");
		}
		catch (EmptyStackException e) {
			// correct behavior
		} catch (Exception e) {
			fail("Wrong exception thrown.");
		}
	}
	
//	Test if empty stack is empty using the empty method (should be empty)
	@Test
	public void testEmptyMethod() {
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			assertTrue("empty() does not indicate that stack is empty.",
				stack.empty() == true);
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}
	
//	Test if empty stack is empty using the pop method (should throw exception)
	@Test
	public void testEmptyPop() {
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.pop();
			fail("Exception was not thrown from pop() when the stack is empty.");
		}
		catch (EmptyStackException e) {
			// correct behavior
		} catch (Exception e) {
			fail("Wrong exception thrown.");
		}
	}
	
//	Test if populated stack is empty using the peek method (should not be empty)
	@Test
	public void testEmptyPeek() {
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(5);
			assertTrue("peek() thinks populated stack is empty", stack.peek() == 5);
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}

//	Test if populated stack is empty using the empty method (after using push to populate)
	@Test
	public void testPopEmpty() {
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(5);
			assertFalse("empty() thinks populated stack is empty", stack.empty());
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}

//	Test if populated stack returns an added node using the peek method (after using push to populate)
	@Test
	public void testPeekReturn(){
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(3);
			stack.push(5);
			assertEquals((Integer) 5, stack.peek());
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}
	
//	Test if populated stack returns last added node using the pop method
	@Test
	public void testPopReturn(){
		try{
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(3);
			stack.push(5);
			assertEquals((Integer) 5, stack.pop());
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}
	
//	Test if populated stack is empty after one added node is popped using the pop method
	@Test
	public void testPopStackEmpty(){
		try{
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(3);
			stack.push(5);
			stack.pop();
			stack.pop();
			stack.peek();
			fail("Exception was not thrown from peek() when the stack became empty.");
		}
		catch(EmptyStackException e){
			// correct behavior
		} catch (Exception e) {
			fail("Wrong exception thrown.");
		}
	}
	
//	Test if top is pointing to last.getNext() after pop() is used on a populated stack
	@Test
	public void testTopPointer(){
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(3);
			stack.push(5);
			stack.pop();
			assertEquals((Integer) 3, stack.peek());
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}
		
//	Test if push will make the new top node point to the old top node as top.getNext()  
	@Test
	public void testPushTop(){
		try {
			MyStack<Integer> stack = new MyStack<Integer>();
			stack.push(3);
			stack.push(5);
			assertEquals((Integer) 5, stack.peek());
		} catch (Exception e) {
			fail("Exception should not be thrown.");
		}
	}
	
//	Cannot test if push will reject an item of a different type - won't compile 
//	Cannot test if push will reject a null parameter - too restrictive

}
