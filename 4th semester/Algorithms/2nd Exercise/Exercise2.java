import java.util.ArrayList;
import java.util.List;
class Exercise2
{

	private int[][] caloriesAndFat; // 2D array. First row has the calories. Second row has the fat.
	private int wantedCalories; // Total calories.
	private int[] solution; // The selected products.
	private int cal;
	public Exercise2( int[][] caloriesAndFat, int wantedCalories )
	{
		this.caloriesAndFat = caloriesAndFat;
		this.wantedCalories = wantedCalories;	
	}

	public void solveExercise2()
	{
		//DP
		int[][] C = new int[caloriesAndFat[0].length+1][wantedCalories+1];
		for(int j=1;j < wantedCalories+1; j++){
			C[0][j]=32767; //infinity = max short value 
		}
		for(int i = 1; i < caloriesAndFat[0].length+1; i++){
			for(int j = 1; j < wantedCalories+1; j++){
				if(caloriesAndFat[0][i-1] <= j){ //an xoraei,pare to max anamessa stis epiloges na to pareis k na mhn
					if(C[i-1][j - caloriesAndFat[0][i-1]] + caloriesAndFat[1][i-1] <= C[i-1][j]){
						C[i][j] = C[i-1][j - caloriesAndFat[0][i-1]] + caloriesAndFat[1][i-1];
					}else{
						C[i][j] = C[i-1][j];
					}
				}else{
					
					C[i][j] = C[i-1][j]; //de xoraei,mh to pareis
				}
			}
		}	
		//Construct
		List<Integer> temp = new ArrayList<>();
		int i=C.length-1;
		boolean inside=false;
		while(wantedCalories>0 && i>0){
			if(C[i][wantedCalories] < C[i-1][wantedCalories]){
				if(inside == false){ //epilogi thermidwn, vrethike to menu me tis max 
					cal= wantedCalories;
					inside=true;
				}
				temp.add(i); //epilogh ths trofis i
				wantedCalories = wantedCalories - caloriesAndFat[0][i-1]; 
			}
			i=i-1;
			if(inside == false && i == 0){//oso de exeis vrei thermides konta stis max epitreptes pou na periexoun diathesimo menu
				wantedCalories = wantedCalories - 1;
				i = C.length-1;
			}
		}
		solution = new int[temp.size()]; //antegrapse th lush
		for(int j = 0; j < temp.size(); j++){
			solution[j] = temp.get(j);
		}
		
	}

	public void printSolution()
	{
		System.out.println("Exercise 2");
		System.out.println("Menu's calories: " + cal);
		System.out.print("Solution: ");
		if(solution!=null)
			for( int i=0; i<solution.length; i++ )
			{
				System.out.print( solution[i]+" " );
			}
		else
			System.out.print( " Solution for Exercise2 is empty." );
				
		System.out.println();
		System.out.println();
		System.out.println();
	}

	
	public void printInputData()
	{
		if( caloriesAndFat !=null )
		{
			int rows = caloriesAndFat.length;
			int columns = caloriesAndFat[0].length;
			
			for( int i=0; i<rows; i++ )
			{
				for( int j=0; j<columns; j++ )
				{
					System.out.print( caloriesAndFat[i][j]+" " );
				}
				System.out.println();
			}
		} 
		else
			System.out.println("Input table is null.");
		System.out.println( "Total calories: "+wantedCalories );
	}
	

}