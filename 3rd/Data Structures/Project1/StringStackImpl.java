import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Implements StringStack inteface
 */
public class StringStackImpl<T> implements StringStack<T> {
	private int N;
	MyNode<T> head;
	String name;
	private class MyNode<T>{
		private T item;
		MyNode next;
		/**
		*constractor
		*@param item to insert
		*@param next node 
		*/
		MyNode(T item,MyNode next){
			this.item = item;
			this.next = next;
		}
		/**
		*return item 
		*/
		T getItem(){
			return item;
		}
		
		
	}
	StringStackImpl(String listName){
		name = listName;
		head = null;
	}
	
	 /**
	 * @return true if the stack is empty
	 */
	public boolean isEmpty(){
		// TODO Auto-generated method stub
		return (N == 0);
	}
	
	/**
	 * insert a String item to the stack
	 */
	public void push(T item) {
		// TODO Auto-generated method stub
		 head = new MyNode(item, head);
		 N++;
	}

	/**
	 * remove and return the item on the top of the stack
	 * @return the item on the top of the stack
	 * @throws a NoSuchElementException if the stack is empty
	 */
	public T pop() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T tmp = head.getItem();
		MyNode t = head.next;
		head = t; 
		N--;
		return tmp;
	}

	 /**
	 * return without removing the item on the top of the stack
	 * @return the item on the top of the stack
	 * @throws a NoSuchElementException if the stack is empty
	 */
	public T peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return (T) head.getItem();
	}

	/**
	 * print the elements of the stack, starting from the item
         * on the top,
	 * to the stream given as argument. For example, 
	 * to print to the standard output you need to pass System.out as
	 * an argument. E.g., 
	 * printStack(System.out); 
	 */
	public void printStack(PrintStream stream) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		stream.println(name + ":");
		for (MyNode x = head; x != null; x = x.next) {
			stream.println(x.getItem());
		}
	}
	
	/**
         * return the size of the stack, 0 if it is empty
	 * @return the number of items currently in the stack
	 */
	public int size() {
		// TODO Auto-generated method stub
		return N;
	}

}
