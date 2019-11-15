class Exercise1
{

	private int[] leaves;
	private int[] solution;
	private int mintrip;
	
	public Exercise1( int[] leaves )
	{
		this.leaves = leaves;
	}

	public void solveExercise1()
	{
		int[] result=new int[leaves.length];
		result[0]=0;
		
		for(int i = 1; i < leaves.length; i++){ //vres to plithos twn min fullwn
			int min=32767; //infinity = max short value 
			for(int j = 0;j < i ;j++){
				if(i-j <= leaves[j] && result[j] < min ){ //an mporei na ftasei apo to jos ekei k uparxei veltiosi
					min=result[j];
				}
			}
			result[i]=min+1; 
		}
		solution=new int[result[leaves.length-1]+1];
		solution[solution.length-1]=result.length;//sigoura tha mpei to teleftaio fullo
		
		int i = leaves.length-2; //traverse 
		int index=result[leaves.length-1]-1; 
		
		
		while(index >= 0){
			if(result[i] == index && leaves[i] >= i - solution[index]){
				solution[index--]=i+1;
			}
			i--;
		}
		mintrip = result[result.length-1];
	}

	public void printSolution()
	{
		System.out.println("Exercise 1");
		System.out.println("Minimun trip: " + mintrip);
		System.out.print("Solution: ");
		if(solution!=null)
			for( int i=0; i<solution.length; i++ )
			{
				System.out.print( solution[i]+" " );
			}
		else
			System.out.print( " Solution for Exercise1 is empty." );
				
		System.out.println();
		System.out.println();
		System.out.println();
	}

	/*
	public void printInputData()
	{
		if( leaves !=null )
		{
			for( int i=0; i<leaves.length; i++ )
			{
				System.out.print( leaves[i]+" " );
			}
			System.out.println();
		} 
		else
			System.out.println("Input table is null.");
	}
	*/

}