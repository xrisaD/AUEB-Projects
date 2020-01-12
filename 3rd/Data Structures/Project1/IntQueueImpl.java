import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Implements IntQueue inteface
 */
public class IntQueueImpl<T> implements IntQueue<T>{
	MyNode<T> head;
	MyNode<T> tail;
	String name;
	int N;
	
	private class MyNode<T>{
		T item;
		private MyNode<T> next;
		/**
		*constractor
		*@param item to insert
		*/
		MyNode(T item){
			this.item = item;
			next = null;
		}
		
		
	}
	
	IntQueueImpl(String name){
		this.name = name;
		head = null;
		tail = null;
	}
	
	/**
	 * @return true if the queue is empty
	 */
	public boolean isEmpty(){
		return (N == 0);
	}
	
	/**
	 * insert an integer to the queue
	 */	 
	public void put(T item){
		MyNode t = new MyNode(item);
		if (isEmpty()){
			head = tail = t;
		} else {
			tail.next = t;
			tail = t;
		}
		N++;
		
		
	}
	
	/**
 	 * remove and return the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T get() throws NoSuchElementException {
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		T tmp =   head.item;
		head = head.next;
		N--;
		return tmp;
		
	}	
	
	/**
	 * return without removing the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T peek() throws NoSuchElementException {
		
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		return ((T) head.item);
	}
	
	/**
	 * print the elements of the queue, starting from the oldest 
         * item, to the print stream given as argument. For example, to 
         * print the elements to the
	 * standard output, pass System.out as parameter. E.g.,
	 * printQueue(System.out);
	 */
	public void printQueue(PrintStream stream) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		stream.println(name + ":");
		for (MyNode x = head; x != null; x = x.next) {
			stream.println(x.item.toString());
		}
	}
	
	/**
	 * return the size of the queue, 0 if it is empty
	 * @return number of elements in the queue
	 */
	public int size(){
		return N;
	}
	/**
	*change the item at the top of the queue
	*@param new item
	*/
	public void set(T item){
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		head.item=item;
	}
}
