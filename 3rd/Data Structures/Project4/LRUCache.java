

public class LRUCache<K,V> implements Cache<K,V> {
	
	int size; //cache size
	int capacity; //number of elements in cache
	DoublyLinkedList<K,V> list;
	HashTable<K,V> hashmap;
	long hits;
	long misses;
	HashCode h;
	
	LRUCache(int size,HashCode h){
		this.size=size;
		this.capacity=0;
		list= new DoublyLinkedList<K,V>();
		hashmap =new HashTable<K,V>(size*2);
		this.hits = 0;
		this.misses = 0;
		this.h=h;
	}
	
	private class Node<K,V>{
		K key; //key of element
		V value; //data of element
		Node<K,V> next;
		Node<K,V> prev;
		
		Node(K key,V value){
			this.key=key;
			this.value=value;
			this.next=null;
			this.prev=null;
		}
	}
	
	private class HashTable<K,V>{ //LinearProbingHashTable with double hashing
		private int currentSize, maxSize;
		public Node<K, V>[] nodes;
		private int primeSize;
		private Node<K,V> pseudoNode; // for deleted items
		
		HashTable(int capacity) 
	    {
	        currentSize = 0;
	        maxSize = capacity;
			maxSize = getPrime();  //table size will be the first smaller prime than capacity
	        nodes = new Node[maxSize];
	        
	        pseudoNode=new Node<K,V>(null,null); // for deleted items
	    }
		
		/* Function to get prime number less than table size  */
	    public int getPrime()
	    {
	        for (int i = maxSize - 1; i >= 1; i--)
	        {
	            int fact = 0;
	            for (int j = 2; j <= (int) Math.sqrt(i); j++)
	                if (i % j == 0)
	                    fact++;
	            if (fact == 0)
	                return i;
	        }
	        /* Return a prime number */
	        return 3;
	    }
		
		/* turning a key to an integer value */
		
		
		/* first hash function of double hashing that calculates starting position of key */
	    private int hashFunc1(K k)
	    {
	        int hashVal=h.hashCode((String)k,37);
	        hashVal %= maxSize;
	        if (hashVal < 0)
	           hashVal += maxSize;
	        return hashVal;
	    }
		
	    /* second function for double hashingthat calculates step size of key */
	    private int hashFunc2(K k)
	    {
			int hashVal=h.hashCode((String)k,37);
	        hashVal %= maxSize;
	        if (hashVal < 0)
	            hashVal += maxSize;
	        return 5 - hashVal % 5;
	    }
	    
	
	
	    public void insert(K key, Node<K,V> item) {
	        int hashVal = hashFunc1(key); // hash the key
	        int stepSize = hashFunc2(key); // get step size
	        
			// until empty cell or -1
	        while (nodes[hashVal] != null && nodes[hashVal].key != null) {
	          hashVal += stepSize; // add the step
	          hashVal %= maxSize; // for wraparound
	        }
			
	        nodes[hashVal] = item; // insert item
	    }
	    
	    public void delete(K key) {
	    	int hashVal = hashFunc1(key); 
	        int stepSize = hashFunc2(key);
			
	        int sumOfStep = 0;
			
	        while (nodes[hashVal] != null && sumOfStep <= maxSize) {
	            if (nodes[hashVal].key!=null && nodes[hashVal].key.equals(key)) {
				    nodes[hashVal] = pseudoNode; // delete item
	                return; 
	            }
	            hashVal += stepSize; // add the step
	            hashVal %= maxSize; // for wraparound
				sumOfStep += stepSize;
				
	        }
	        return; // can't find item
	    }
	    
	    public Node<K,V> find(K key) {
	        int hashVal = hashFunc1(key); // hash the key
	        int stepSize = hashFunc2(key); // get step size
			
			int sumOfStep = 0;
	        while (nodes[hashVal] != null && sumOfStep <= maxSize) {
	          if (nodes[hashVal].key!=null && nodes[hashVal].key.equals(key))
	            return nodes[hashVal]; // yes, return item
	          hashVal += stepSize; // add the step
	          hashVal %= maxSize;
			  sumOfStep += stepSize;
			  // for wraparound
			  
	        }
	        return null; // can't find item
	      }
	}
	
	/* keeps the LRU order*/
	private class DoublyLinkedList<K,V>{
		private Node<K,V> head;
		private Node<K,V> tail;
		
		public DoublyLinkedList(){
			head = null;
			tail = null;
		}
		
		/* adds element to head  */
		public Node<K,V> addFist(K key,V value){ 
			Node<K,V> newNode = new Node<K,V>(key,value);
			
			if(head == null){
				head = newNode;
				tail = newNode;
				return head;
			}
			newNode.next=head;
			head.prev=newNode;
			head = newNode;		
			
			return head;
		}
		
		/*removes tail */
		public K removeLast(){
			
			K key = (K) tail.key; 
			
			tail = tail.prev;
			tail.next=null;
			
			return key;
		}
		
		/*moves item to head*/
		public void moveToHead(Node<K,V> page){
			
			if(page == null || head == null || page == head)
				return;
			
			if(page == tail){
				tail = tail.prev;
				tail.next=null;			
			}
					
			Node<K,V> next = page.next;
			Node<K,V> prev = page.prev;		
			
			prev.next=next;
			if(next != null)
				next.prev=prev;
			
			page.prev=null;
			page.next=head;
			head.prev=page;
			
			head = page;		
		}
	}
	
	/*searches the cache for block with the given key */
	/* returns null if it is not found*/
	public V lookUp(K key) {
		Node<K,V> node = hashmap.find(key);
		if (node != null){
			++hits;
			list.moveToHead(node);
			return node.value;
		}
		++misses;
		return null;
	}

	/*inserts new element in cache*/
	public void store(K key, V value) {
		if (capacity < size){
			++capacity;
			
		} else {
			hashmap.delete(list.removeLast());
		}
		hashmap.insert(key, list.addFist(key, value));	
		
	}

	/*ratio of cache hits*/
	public double getHitRatio() {
		
		return (double)hits/(hits + misses);
	}

	/*number of cache hits*/
	public long getHits() {
		
		return hits;
	}

	/*number of cache misses*/
	public long getMisses() {
		
		return misses;
	}

	/*total number of lookups*/
	public long getNumberOfLookUps() {
		
		return hits + misses;
	}

}

