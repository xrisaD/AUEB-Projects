import java.util.*;
import java.io.*;
public class ST {
	void test(){
		System.out.println(head.item.toString());
		System.out.println(head.l.item.toString());
	}
	/*
	*class TreeNode
	*contain WordFreqs
	*one left child,one right child
	*/
	private class TreeNode {
		 WordFreq item; //
		 TreeNode l; // pointer to left subtree
		 TreeNode r; // pointer to right subtree
		 TreeNode parent; // pointer to node parent
		 int number; //number of nodes in the subtree starting at this node
		 public TreeNode() { //default constructor
			 this.item = new WordFreq();
			 this.l = null;
			 this.r = null;
			 this.parent = null;
			 int number = 0;			 
		 }
		 
		 public TreeNode(WordFreq item) { //constructor
			 this.item = item;
			 this.l = null;
			 this.r = null;
			 int number = 1;
			 this.parent = null;
		 }	
	}
	/*
	*end TreeNode
	*/
	
	
	/*
	*class Node
	*contains Strings
	*/
	class Node{ //usage:stopwords
		String item; 
		Node next;
		Node(String v){
		item = v;
		next = null;
		} 
	} 
	/*
	*end Node
	*/
	
	/*
	*class List
	*contains Nodes with Strings
	*/
	private class List{ //usage:stopwords	
		Node head;
		List(){
			head = new Node("");
			head.next = null;
		}
	}
	/*
	*end List
	*/
	
	private TreeNode head;  //head of ST
	private List stopwords; //list with stopwords
	protected Comp cmp; //comparator

	//constructors
	public ST(){ //default
		this(new StringComparator());
	}
	public ST(Comp cmp) { //set comparator
		    stopwords=new List();//initialize list with stopwords
	        this.cmp = cmp;
	}
	
	/*
	*search a WordFreq with the same String w
	*/
	WordFreq search(String w) {
		if (w == null) throw new IllegalArgumentException();
		if(head==null) { //empty tree
			return null;
		}
		TreeNode result=find(w); //find TreeNode with the given string
		
        if(result==null) {//not found,return null
        	return null;
        }
		//if found
        if(result.item.getCount() > getMeanFrequency()) { //if count > mean frequency
			rootInsert(result); //then insert at head
        }
        return head.item;//return head
        
	}
	
	
	/*
	*insert the given node at the head of the tree
	*temp method at search
	*/
	private void rootInsert(TreeNode given){ 
		while(given.parent!=null){
			if(given.parent.r==given){
				given=rotateLeft(given.parent);
			}
			else if(given.parent.l==given){
				given=rotateRight(given.parent);
			}
		}
	}
	/**
     * Performs a left rotation.
     * @param pivot The node to rotate.
     */
    private TreeNode rotateLeft(TreeNode pivot) {
				//pivot's parent
    			TreeNode parent=pivot.parent;
    			//pivot's child
    			TreeNode child=pivot.r;
				
    			if (parent == null) {
    	            head = child;
    	            head.number = pivot.number;
    	     
    	        } else if (parent.l == pivot) {
						parent.l = child;
						if (child != null){parent.l.number = pivot.number;}
					
    	        } else {
						parent.r = child;
						if (child != null){parent.r.number = pivot.number;}
    	        }
				
				if(pivot.l!=null){//left numbers
					pivot.number= pivot.l .number + 1;//no right leaf anymore at right leaf
				}else{
					pivot.number=1;
				}
				
    	        if (child != null && child.l!=null) {
    	        	pivot.number += child.l.number;
    	        }
    	        //new number for pivot and child
					child.parent = pivot.parent;
					pivot.parent = child;
					pivot.r = child.l;
					if (child.l != null)child.l.parent = pivot;
					child.l = pivot;
    			return child;
    }
    /**
     * Performs a right rotation.
     * @param pivot The node to rotate.
     */
    private TreeNode rotateRight(TreeNode pivot) {
		//pivot's parent
		TreeNode parent=pivot.parent;
		
		//pivot's child
		TreeNode child=pivot.l;
		
		if (parent == null) {
            head = child;
            head.number = pivot.number;
     
        } else if (parent.l == pivot) {
            parent.l = child;
            parent.l.number = pivot.number;
        } else {
            parent.r = child;
            parent.r.number = pivot.number;
        }
		if(pivot.r!=null){
			pivot.number= pivot.r .number + 1;
		}else{
			pivot.number=1;
		}
        if (child.r != null) {
        	pivot.number += child.r.number;
        }
        //new number for pivot and child
        child.parent = pivot.parent;
        pivot.parent = child;
        pivot.l = child.r;
        if (child.r != null) child.r.parent = pivot;
        child.r = pivot;
		return child;
    }
	/*
	*end search method
	*/
	
	
	
	/*
	*insert
	*/
	void insert(WordFreq item) {
		traverse(item);
	}
	/*
	*end insert
	*/
	
	/*
	*Get the balance factor of this ancestor node
	*method for AVL
	*/
	private int getBalance(TreeNode n) { 
        if (n == null) 
            return 0; 
		int left=0;
		int right=0;
		if(n.l!=null){
			left=n.l.number;
		}
		if(n.r!=null){
			right=n.r.number;
		}
        return left - right; 
    }
	/*
	*traverse the tree
	*if found the given wordfreq return the specific TreeNode
	*else create a new TreeNode and insert it as a leaf and return null
	*also increase number of parent nodes
	*also protected AVL's properties 
	*/
	private TreeNode traverse(WordFreq item){ //usage: update and insert
		if (item == null) throw new IllegalArgumentException();
		TreeNode n = head;
		TreeNode p = null;
        int result = 0;
        while (n != null) {
            // Compare element with the element in the current subtree
        	result=cmp.compare(item,n.item);
			
            //if element is equal with current node's element, 
        	//then the element is already present in the tree 
        	if(result == 0){
				//do not insert node at tree
				return n;
			}
            // Keep a reference in the current visited node
			p=n;
            // Go left or right based on comparison result
            if(result<0){
				n=n.l;
			}else if(result>0){
				n=n.r;
			}
        }
        // Create and connect a new node
        TreeNode node=new TreeNode(item);
		node.number++;
        //node's parent is p
        node.parent=p;
        // The new node must be a left or right child of p
        if(result<0){
			p.l=node;
		}else if(result>0){
			p.r=node;
		}
        // if the tree is empty; root must be set
        if(head==null) {
        	head=node;
        }
        TreeNode tmp=node;
        //increase number of parent nodes
        while(tmp.parent != null) {
        	tmp=tmp.parent;
        	tmp.number++;
        }
		
		//AVL
		//check whether this node became unbalanced
		if(node.parent!=null && node.parent.parent!=null){
			TreeNode parentparent=node.parent.parent;
			int balance = getBalance(parentparent);
			int resultleft=0;
			int resultright=0;
			if(node.parent!=null){
				resultleft=cmp.compare(item,node.parent.item);
			}
			if(node.parent!=null){
				resultright=cmp.compare(item,node.parent.item);
			}
			
			// If this node becomes unbalanced, then there 
			// are 4 cases Left Left Case 
			if (balance > 1 && resultleft<0){
				rotateRight(parentparent); 
			}
			// Right Right Case 
			if (balance < -1 && resultright>0) 
				rotateLeft(parentparent); 
	  
			// Left Right Case 
			if (balance > 1 && resultleft>0) { 
				node.parent = rotateLeft(node.parent); 
				rotateRight(parentparent); 
			} 
	  
			// Right Left Case 
			if (balance < -1 && resultright<0) { 
				node.parent = rotateRight(node.parent); 
				rotateLeft(parentparent); 
			} 
		}
		return null;
	}
	
	/*
	*update
	*/
	void update(String w) {
		TreeNode node=traverse(new WordFreq(w));
		if(node!=null){node.item.increaseCount();} //if found,increase the count
	}
	/*
	*end update
	*/
	
	
	/*
	*remove
	*/
	/*
	*remove node with string w
	*/
	   public void remove(String w) {
			TreeNode node=find(w.toUpperCase());//find
			if(node==null){//not found
				return;
			}
			remove(node);//if found remove
			
	    }
	   /*
	   *helper method:remove 
	   *remove given node
	   */
	   private void remove(TreeNode node) {
		   if(node.l!=null && node.r !=null){
			  
		        TreeNode p = node.r;
		        while (p.l != null) {
		        	p = p.l;
		        }  
		        
				TreeNode x = p;
				node.item = x.item;
				node = x;
		   }
		   
			TreeNode parent = node.parent;
			TreeNode child = node.l != null ? node.l : node.r;
			if(parent == null){
				head = child;
			}else if(parent.l == node){
				parent.l = child;
			}else if(parent.r == node){
				parent.r = child;
			}
			if(child != null){
				child.parent = parent;
			}
			node.item = null;
			node.parent = node.l = node.r = null;
			while (parent != null) {//set new numbers
				parent.number--;
				parent = parent.parent;
			}
			
			// check whether this node became unbalanced
			int balance = getBalance(head);
			
			int resultleft=0;
			int resultright=0;
			if(head.l!=null){
				resultleft=getBalance(head.l);
			}
			if(head.l!=null){
				resultright=getBalance(head.r);
			}
			// If this node becomes unbalanced, then there are 4 cases 
  
			// Left Left Case 
			if (balance > 1 && resultleft >= 0) 
				rotateRight(head); 
			// Left Right Case 
			if (balance > 1 && resultleft < 0) 
			{ 
				head.l =  rotateLeft(head.l); 
				rotateRight(head); 
			} 
		  
			// Right Right Case 
			if (balance < -1 && resultright <= 0) 
				rotateLeft(head); 
		  
			// Right Left Case 
			if (balance < -1 && resultright > 0) 
			{ 
				head.r = rotateRight(head.r); 
				rotateLeft(head); 
			} 
			
		}
	
	/*
	*end remove
	*/
	
	/*
	*find if the strins exist in tree
	*
	*/
	public TreeNode find(String element) {
			TreeNode p = head;
			while (p != null) {
				// Compare element with the element in the current subtree
				int result = cmp.compare(new WordFreq(element), new WordFreq(p.item.key()));
				if (result == 0) {
					return p;//if found,return p 
				}
				// Go left or right based on comparison result
				p = result < 0 ? p.l : p.r;
			}
			return null;//not found,return null
	}
	
	
	/*
	*load file with the given filename
	*/
	void load(String filename) {
		String text=readFile(filename);
		if(text.equals("")){
			System.out.println("Something went wrong!");
			return;
		}
		int indexstart=0; //start
		int indexend=0; //end
		do{
			indexend=text.indexOf(" ",indexstart);
			
			if(indexend==-1) {//if find last word
				indexend=text.length()-1;
			}
			String word=text.substring(indexstart,indexend).toUpperCase().trim();//next word
			
			 word=wordEdit(word);
			 if(word.equals("")) { //not eligible word
			     indexstart=indexend+1;
				 continue;
			 }
			 
			if(findStopWord(word)==null) {//not stop word
				 update(word);
			}
			indexstart=indexend+1;
		}while((indexend+1)<text.length()); //end of string
	}
	
	/*
	 * findStopWord
	 * return previous node if given word is stopword
	 * else return null
	 *helper method
	 */
	private Node findStopWord(String word) {
		Node t;
		if(stopwords.head!=null){
			for (t=stopwords.head; t.next!=null; t=t.next) {
				if(t.next.item.compareTo(word)==0) {//find stop word
					return t; //return previous node 
				}
			}
		}
		return null;
	}
	
	/*
	 * wordEdit
	 * edit each word
	 * word will contain only characters
	 *helper method
	 */
	private static String wordEdit(String word) {
		int apostrophe = 0;
		int symbol=0;
		int start=0;
		int end=0;
		int alph=0;
		for(int i = 0; i < word.length(); i++){
		   char c = word.charAt(i);
		   if(c>='A' && c<='Z') { //if its letter
				end++;
			   alph++;
			   if(symbol>0) {//if found a symbol before
				   return "";
			   }
			   continue;
		   }else if(c>='0' && c<='9'){
			   return "";
		   }else { //symbol
			   if(c=='\''){
					if (apostrophe>0 && symbol==0) return "";
				    apostrophe++;
					continue;	   
		   		}
			   	if(alph==0) { //first letter is symbol
			   		start++;
			   		continue;
			   	}
			   	if(symbol==0) { //last letter
			   		end=i;
			   	}
			   	symbol++; //given letter may be inside word
		   }
		}
		return word.substring(start,end);
	}
	/*
	 * readFile
	 * read file and convert it to string
	 * return string
	 *helper method
	 */
	private static String readFile(String filename){ 
		StringBuilder tmpContent = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filename)); 
		    String str;
		    while ((str = in.readLine()) != null) {
		       tmpContent.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			return "";
		}
		return tmpContent.toString();
	}
	/*
	*end load
	*/
	
	
	 /*
	 *getTotalWords
	 */
	 //temp variable sum of words
	 private int sum;
	 int getTotalWords() {
		sum=0;
		inorderAndAdd(head);
		return sum;
	 }
	 //inorder method
	 private void inorderAndAdd(TreeNode node) {
		if(node==null){
			//ST is empty -do not traverse
			return;
		}
		
		//visit left tree
		 inorderAndAdd(node.l);
		
		 //visit root
		 adder(node.item.getCount());
		 
		 //visit right tree
		 inorderAndAdd(node.r);
	 }
	 //increase the sum of counts
	 private void adder(int wordcount){
	 	sum+=wordcount;
	 }
	 /*
	 *end getTotalWords
	 */
		
		
	/*
	*getDistinctWords
	*return the sum of WordFreq
	*/
	int getDistinctWords() {
		return head.number;
	}
	/*
	*end getDistinctWords
	*/
	
	
	/*
	*getFrequency
	*count of word w
	*/
	int getFrequency(String w) {
		if (w == null) throw new IllegalArgumentException();
		if(head==null) {
			return 0;
		}
		TreeNode result=find(w);
        if(result==null) {//not found
        	return 0;
        }
        return result.item.getCount();
	}
	/*
	*end getFrequency
	*/
	
	
	/*
	*getMaximumFrequency
	*/
	//temp value max
	private WordFreq max;
	
	WordFreq getMaximumFrequency() {
		if(head!=null){
			max=head.item;
		}
		inorderMax(head);	
		return max;
	}
	//inorderMax
	//helper method at getMaximumFrequency()
	private void inorderMax(TreeNode node) {
		if(node==null){
			//ST is empty -do not traverse
			return;
		}
		//visit left tree
		inorderMax(node.l);
			 
		 //visit root
		if (node.item.getCount() > max.getCount()) {
			max = node.item;
		}
		
		//visit right tree
		 inorderMax(node.r);
	}
	/*
	*end getMaximumFrequency
	*/
	
	
	/*
	*getMeanFrequency
	*/
	double getMeanFrequency() {
		if (head == null) {
			return  0;
		}
		return (double) getTotalWords() / head.number;		
	}
	/*
	*end getMeanFrequency
	*/
	
	
	/*
	*addStopWord
	*/
	void addStopWord(String w) {
		if(stopwords==null) {
			stopwords=new List();
		}
		//new stop word
		Node t=new Node(w.toUpperCase());
		//add new stop word
		t.next = stopwords.head.next;
		stopwords.head.next = t;
	}
	/*
	*end addStopWord
	*/
	
	
	/*
	*removeStopWord
	*/
	void removeStopWord(String w) {
		if(stopwords==null || stopwords.head.next==null) {
			return;
		}
		//find stop word
		Node x=findStopWord(w.toUpperCase());
		if(x!=null){//find stopword
			x.next=x.next.next; //remove
		}
	}
	/*
	*end removeStopWord
	*/
	
	
	
	/*
	*printTreeAlphabetically
	*/
	//print Alphabetically uses inorder method
	void printTreeAlphabetically(PrintStream stream) {//left-root-right
		//inorderAndStream(head,stream);
		inorderAndStream(head,stream);
		System.out.println();
	}
	//inorder method
	 private void inorderAndStream(TreeNode node,PrintStream stream) {
		if(node==null){
			//ST is empty -do not traverse
			return;
		}
		//visit left tree
		inorderAndStream(node.l,stream);
		//visit root
		stream.println(node.item.toString());
		//visit right tree
		inorderAndStream(node.r,stream);
	 }
	 /*
	*end printTreeAlphabetically
	*/
	
	
	/*
	*printTreeByFrequency
	*/
	void printTreeByFrequency(PrintStream stream) {
		ST st = new ST(new IntComparator());
		//depth
		StackImpl<TreeNode> s = new StackImpl<TreeNode>("Stack");
        if (!(head==null)){
			s.push(head);
		}
        while (!s.isEmpty()) {
            TreeNode node = (TreeNode)s.pop();
            st.insert(node.item);
            if (!(node.r==null)) s.push(node.r);
            if (!(node.l==null)) s.push(node.l);
        }
		st.inorderAndStream(st.head,stream);//print
		System.out.println();
	}
	/*
	*end printTreeByFrequency
	*/
	
	 public static void main( String [ ] args ) {
        ST st = new ST();
		
		//stopwords
		st.addStopWord("a");
		st.addStopWord("an");
		st.addStopWord("the");
		st.addStopWord("hey");
		st.removeStopWord("hey");
		
		//load
		st.load("text.txt");
		
		//prints
		System.out.println("alpa:");
		st.printTreeAlphabetically(System.out);
		System.out.println("\nfreq:");
		st.printTreeByFrequency(System.out);
		
		//mean and max
		System.out.println("MEAN: "+st.getMeanFrequency());
		System.out.println("MAX: "+st.getMaximumFrequency());
		System.out.println();
		//frequency
		System.out.println("Frequency of word=LOVE: "+st.getFrequency("LOVE"));
		System.out.println();
		//distinct words
		System.out.println("Distinct Words: "+st.getDistinctWords());
		System.out.println();
		//total words
		System.out.println("Total Words: "+st.getTotalWords());
		System.out.println();
		
		//search and print
		System.out.println("\nSearch 'Hello' and print freq:");
		WordFreq x=st.search("Hello");
		st.printTreeAlphabetically(System.out);
		st.printTreeByFrequency(System.out);
		
		//stopwords
		st.removeStopWord("the");
		
		//remove
		System.out.println("\nRemove 'me' and print freq:");
		st.remove("me");
		st.printTreeByFrequency(System.out);
		
		//update
		System.out.println("\nUpdate 'Hello' and print freq:");
		st.update("Hello");
		st.printTreeByFrequency(System.out);
		
		//insert
		System.out.println("\nInsert and print freq:");
		st.insert(new WordFreq("Words"));
		st.printTreeByFrequency(System.out);
		System.out.println("alpa:");
		st.printTreeAlphabetically(System.out);
		
    }
}
