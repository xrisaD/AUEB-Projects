import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.*; 


class Exercise1
{

	private Map <String, List<String>> nodes;
	private List<String> solution;
	private String name1;
	private String name2;
	
	//extras
	private Map <String, Boolean> inQ;
	private Map <String, Integer> order;
	private Map <String, String> parent;
	private int clock;
	private Map<Integer, HashSet<String>> uf;  //arraylist with hashsets.One hash set for each neighbor of name1.Use hashset like union find.
	private Map<String, Integer> depth;
	int iuf;
	String min1 = "";
	String min2 = "";
	
	public Exercise1( Map <String, List<String>> nodes, String name1, String name2 )
	{
		this.nodes = nodes;
		this.name1 = name1;
		this.name2 = name2;
		this.solution = new ArrayList<>();
	}

	public void solveExercise1()
	{
		if(nodes.get(name1)==null || nodes.get(name2)==null){
			System.out.println("Wrong names");
			return;
		}
		
		iuf=-1;
		min1 = "";
		min2 = "";
		clock=1;
		inQ=new HashMap<String, Boolean>();
		parent=new HashMap<String, String>();
		order=new HashMap<String,Integer>();
		uf = new  HashMap<Integer,HashSet<String>>();
		depth = new HashMap<String,Integer>();
		//initialize all nodes as not visited and with null parent
		for ( String key : nodes.keySet() ) {
			inQ.put(key,false);
			parent.put(key,"");
			depth.put(key,-1);
		}
		
		
		//explore for name1;
		exploreB(name1);
		
		if(!inQ.get(name2)){ //not visit name2 in bfs for name1
			System.out.println(name1+ " and "+name2+" aren't in the same connected component."); 
			return;
		}else if(min1 =="" && min2==""){
			System.out.println("No cycle");
		}else{ //make solution
			List<String> solution1 = new ArrayList<>();
			List<String> solution2 = new ArrayList<>();
			
			String p = parent.get(min1);
			int k1 = 0;
			solution1.add(k1++,min1);
			while(p!=null){
				solution1.add(k1++,p);
				p = parent.get(p);
			}
			
			p = parent.get(min2);
			int k2 = 0;
			solution2.add(k2++,min2);
			while(p!=null){
				solution2.add(k2++,p);
				p = parent.get(p);
			}
			int k3 = 0;
			for(int j=k1-2;j>=0;j--){
				solution.add(k3++,solution1.get(j));
			}
			for(int j=k2-3;j>=0;j--){
				solution.add(k3++,solution2.get(j));
			}
			solution.add(k3++,name1);
			
		}
	}
	
	public void exploreB(String name1){
		
		LinkedList<String> Q = new LinkedList<String>(); 
		depth.put(name1,0);
		inQ.put(name1,true);				 //set first node in queue
		Q.add(name1);						//add philosophy with name1 in queue
		int maxdepth = Integer.MAX_VALUE;   //infinity
		
		boolean findName2=false;
		
		while (Q.size() != 0) { 		//non empty queue
			String u = Q.poll(); 		//get the first philosophy from queue
			visit(u);					//set phiosophy as visited
			if(u.equals(name2)){ //you find name2
				findName2=true;
			}
			List<String> neighbors = nodes.get(u);		//get philosophy u's neighbors
			for (String n : neighbors){ 				//for each neighbor
				if(name1.equals(u)){ //if neighbors with name1
					uf.put(++iuf,new HashSet<String>());
					uf.get(iuf).add(n);
				}
				if(!inQ.get(n)){						//not inQ
					Q.add(n);							//add it to queue
					inQ.put(n,true);					//set it inQ
					Union(u,n);//add it to the same union find(hashset as it's father)
					depth.put(n,depth.get(u)+1);
					parent.put(n,u);
				}else if(inQ.get(n) && !parent.get(u).equals(n) && findName2 && FindUF(u) == FindUF(name2) && FindUF(n) != FindUF(name2)){/*exei hdh episkeftei kai o n den einai o pateras tou u kai exei vrethei o N2 k o u anhkei sto idio monopati me ton N2 alla o n anhkei se allo monopati */
					if(depth.get(u)+depth.get(n) < maxdepth){ //LATHOS
						min1 = u;
						min2 = n;
						maxdepth=depth.get(u)+depth.get(n);
					}
				}
			}
		}
	}
	public int FindUF(String node){
		for(int i=0;i<=iuf;i++){
			if(uf.get(i).contains(node)){
				return i;
			}
		}
		return -1;
	}
	public void Union(String p, String child){ //union with the correct union-findstruct
		for(int i=0;i<=iuf;i++){
			if(uf.get(i).contains(p)){
				uf.get(i).add(child);
				break;
			}
		}
	}
	public void visit(String name){
		order.put(name,clock);
		clock = clock + 1;
	}
		
	public void printSolution()
	{
		if(solution.size()!=0)
		{
			for( int i=0; i<solution.size(); i++ )
			{
				System.out.print( solution.get(i)+" " );
			}	
			System.out.println();
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

		System.out.println( "Find a circle with "+this.name1+" and "+this.name2+" inside." );
	}
	*/

}