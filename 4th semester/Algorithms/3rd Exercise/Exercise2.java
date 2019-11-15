import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Exercise2
{

	private Map <String, List<String>> nodes;
	private List<String> solution1;
	private long t1;
	private List<String> solution2;
	private long t2;
	private Map <Integer, String> names;
	
	public Exercise2( Map <String, List<String>> nodes )
	{
		this.nodes = nodes;
		this.solution1 = new ArrayList<>();
		this.t1 = 0L; // time in milliseconds
		this.solution2 = new ArrayList<>();
		this.t2 = 0L;  // time in milliseconds
	}

	public void solveExercise2()
	{
		/*
		* Implement your solution.
		* Use the following code to get the time (in milliseconds) for each solution.
		* - long start = System.currentTimeMillis();
		* - // your solution
		* - long end = System.currentTimeMillis(); 
		* - t1 = (end-start); // in the same way t2 = (end-start);
		*/
		//BruteForce();
		Greedy();
	}
	boolean found=false;
	public void BruteForce (){
		long start = System.currentTimeMillis();
		
		//give a number to each node
		names = new HashMap<Integer,String>(); 
		int j=0;
		int x=0;
		for ( String key : nodes.keySet() ) {
			names.put(x++,key);
		}
		
		//try all subset with i size
		for(int i=1;i<nodes.size()-1;i++){
			System.out.println(i);
			CheckForK(i);
			if(found){
				j=i;//j is minimun size of subset that confirm the condition
				break;
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(nodes.size());
		long end = System.currentTimeMillis();
		t1=end-start;
	}
	public void CheckForK(int i){
		String subset[] = new String[i]; //array with subset each time
		createSubset(0,subset,0,i);
	}
	public void createSubset(int index,String[] subset,int now,int k){
		if(found){
			return;
		}
		if (now == k ) { //you complete the subset
			found=CheckVC(subset);
			if(found){
				System.out.println("HEY");
				for(int i=0;i<subset.length;i++){
					solution2.add(subset[i]);
				}
			}
			return;
		}
		if(index>=nodes.size()){
			return;
		}
		if(names.get(index)!=null){
			subset[now] = names.get(index); 
			createSubset(index+1,subset,now+1,k);
		}
		createSubset(index+1,subset,now,k);
	}
	public boolean CheckVC(String[] subset){ //check for vertex cover
		Map <String, List<String>> nodestmp=new HashMap<String,List<String>>();; //copy graph
		for ( String key : nodes.keySet() ) {
			nodestmp.put(key,nodes.get(key));
		}
		for(int i=0;i<subset.length;i++){ //for each node in subset
			if(nodestmp.get(subset[i])!=null){ //if this node exist in graph
				for(String x : nodestmp.get(subset[i])){ //for each neighbor
					nodestmp.get(x).remove(subset[i]);
				}
				nodestmp.remove(subset[i]);
			}
		}//delete node from graph
		for ( String key : nodestmp.keySet() ) {
			if(nodestmp.get(key).size()>0){
				return false;
			}
		}
		System.out.println("PRINTTT");
		return true;
	}
	public void Greedy (){
		long start = System.currentTimeMillis();
		while(!existEdges()){
			String max=findMax();//find node with max neighberhood
			deleteMaxNei(max);	//delete this node
			solution2.add(max); //add it to solution
		}
		long end = System.currentTimeMillis();
		t2 = end-start;
	}
	public boolean existEdges(){ //edge=scedule
		for ( String key : nodes.keySet() ) {
			if(nodes.get(key).size()>0){
				return false;
			}
		}
		return true;
	}
	public String findMax(){ 
		int max=-1;
		String maxval="";
		for ( String key : nodes.keySet() ) {
			if(max<nodes.get(key).size()){
				max=nodes.get(key).size();
				maxval=key;
			}
		}
		return maxval;
	}
	public void deleteMaxNei(String max){
		for(String val : nodes.get(max)){ 
			nodes.get(val).remove(max); 
		}
		nodes.remove(max);
	}
	public void printSolution()
	{
		System.out.println("solution 1:");
		if(solution1.size()!=0)
		{
			for( int i=0; i<solution1.size(); i++ )
			{
				System.out.print( solution1.get(i)+" " );
			}
			System.out.println( " "+t1 );
		}
		System.out.println("solution 2:");
		if(solution2.size()!=0)
		{
			for( int i=0; i<solution2.size(); i++ )
			{
				System.out.print( solution2.get(i)+" " );
			}
			System.out.println( " "+t2 );
		}
	}

	
	/*
	public void printInputData()
	{
		if( nodes !=null )
		{
			System.out.println( "Size of Adjacency-List: "+nodes.size() );
			for (String key   : nodes.keySet()) 
			{
			    List<String> values = nodes.get(key);
			    System.out.println( "-Node "+key+" has neighbors: "+Arrays.toString(values.toArray()) ); 
			}
			System.out.println();
		} 
		else
			System.out.println("Input table is null.");
	}
	*/
	
}