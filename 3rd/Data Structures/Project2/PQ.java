import java.util.Comparator;
public class PQ<T extends Key>{
	 private int[] ids; 
	 public T[] heap;
	 private int size;
	 int typeOfHeap;
	 public PQ(int capacity,int typeOfHeap) {
	        if (capacity < 1) throw new IllegalArgumentException();
	        //(T[])new Object[];
	        this.heap = (T[])new Key[capacity+1];
	        this.size = 0;
	        this.ids=new int[9999];
			this.typeOfHeap=typeOfHeap;
	    }
	 public void insert(T t) {
	        // Ensure object is not null
			if(t==null){ throw new IllegalStateException();}
	        // Check available space
			if(this.size==this.heap.length-1){ throw new IllegalStateException();}
			if((double)this.size/this.heap.length-1>=0.75) { resize();}
	        // Place object at the next available position
			ids[t.getId()]=++size;
			heap[size]=t;
	        // Let the newly added object swim
			swim(this.size);
			
	    }
	 void resize(){
		 T[] h = (T[]) new Key[this.heap.length*2];
		 for(int i=1;i<=size;i++) {
			 h[i]=this.heap[i];
		 }
		 this.heap=h;
	 }
	 public T getMax() {
	        // Ensure not empty
			if(this.size==0){ throw new IllegalStateException();}
			
	        // Keep a reference to the root object
			T t=this.heap[1];
	        // Replace root object with the one at rightmost leaf
			if(this.size>1){
				this.heap[1]=this.heap[size];
				ids[this.heap[size].getId()]=1;
			}
	        // Dispose the rightmost leaf
			heap[size--]=null;
			ids[t.getId()]=0;
	        // Sink the new root element
			this.sink(1,size());
	        // Return the object removed
			return t; 
	 }
	 public T remove(int id) {
		 // Ensure not empty
		 if(this.size==0){ throw new IllegalStateException();}
		 //find index of array
		 int index=ids[id];
		 if(index!=0) {
			// Keep a reference to the object we wish to remove
			 T t=this.heap[index];
			// Replace the t object with the one at rightmost leaf
			 if(this.size>1){
					this.heap[index]=this.heap[size];
					ids[this.heap[size].getId()]=index;
			 }
			  // Dispose the rightmost leaf
				heap[size--]=null;
				ids[t.getId()]=0;
		        // Sink the new root element
				this.sink(index,size());
		        // Return the object removed
				return t; 
		 }
		 System.out.println("There is no such object with the id: " + id);
		 return null;
	 }
	 public T max() {
		// Ensure not empty
		if(this.size==0){ throw new IllegalStateException();}
		return this.heap[1];
	 }
	 private void swim(int i) {
	        // if i root (i==1) return
			while(i>1){
				// find parent
				int p=i/2;
				T node=this.heap[i];
				T parent=this.heap[p];
				// compare parent with child i
				// if child <= parent return
				if(typeOfHeap*(node.compareTo((Comparable)parent))<=0){ 
					return;
				}
				// else swap and i=p
				else{
					ids[heap[i].getId()]=p;
					ids[heap[p].getId()]=i;
					swap(i,p);
					i=p; 
				}
			}
	    }
	 private void sink(int i,int N){
	        // determine left, right child
			int left=2*i;
			int right=2*i+1;
			int max=left;
			while(left<=N){
				if(right<=N){
					int res=typeOfHeap*(this.heap[left].compareTo((Comparable)this.heap[right])); 
					if(res>0){
						max=left;
					}
					else{
						max=right;
					}
				}
				//compare i and max
				if(typeOfHeap*(this.heap[i].compareTo((Comparable)this.heap[max]))>=0){ 
					return;
				}				
				ids[heap[i].getId()]=max;
				ids[heap[max].getId()]=i;
				swap(i,max);
				i=max;
				left=2*i;
				right=2*i+1;
				max=left;
			}
	        // if 2*i > size, node i is a leaf   return
			
	        // while haven't reached the leafs
	            // Determine the largest child of node i
	            // If the heap condition holds, stop. Else swap and go on.
	    }
	 /**
	     * Interchanges two array elements.
	     */
	    private void swap(int i, int j) {
	        T tmp = heap[i];
	        heap[i] = heap[j];
	        heap[j] = tmp;
	    }
	    /**
	     * Prints the objects of the heap.
	     */
	    public void print() {
	        for (int i=size(); i>=1; i--){
	            System.out.print(heap[i].toString());
	        }
	        System.out.println();
	    }
	    /**
	     * Chekcs if heap is empty.
	     */
	    boolean isEmpty(){
	        return size == 0;
	    }
	    int size() {
	    	return size;
	    }
}