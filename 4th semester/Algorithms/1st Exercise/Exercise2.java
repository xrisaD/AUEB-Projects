import java.util.ArrayList;
import java.util.Random;

public class Exercise2
{
	private static int[] unorderedTable;
	private static int[] r; //helper array
	
	public Exercise2( ArrayList<Integer> A )
	{
		unorderedTable = new int[A.size()];
		for( int i = 0; i<unorderedTable.length; i++ )
			unorderedTable[i] = A.get(i);
	}

	public int[] getAnswers()
	{
		/*
		* Implement your solution.
		*/
		QuickSort(0, unorderedTable.length-1); //sort array
		return unorderedTable;
	}
	static void QuickSort(int left, int right){
		if (left >= right) return;
		 
		int[] p = partition(left, right);
		
		QuickSort(left, (p[0]-1));  //sort before pivot
        QuickSort(p[0]+p[1], right); //sort after pivot
	}
	
	static int[] partition ( int left, int right){
		//find a random possition in the array.The number at this possition is pivot!
		Random rand = new Random();
		int n = (right-1-left)*rand.nextInt(1)+left;
		int pivot = unorderedTable[n];
		
		r=new int[2];
		r[0]=left;
		r[1]=0;
		for (int i = left; i <= right ; i++){
			if (unorderedTable[i] < pivot){
				Swaps(i);
				r[0]++;//increase number of smaller ints
			}else if(unorderedTable[i] == pivot){
				Swaps(i);
				r[1]++;//increase number of equal ints 
			}
		}
		return r;
	}
	static void Swaps(int i){
		if(r[1]<1){ //if found at least one number equal pivot
			swap(i,r[0]);//give space to the small number at the top of the array with bigger values
		}else{
			swap(r[0]+r[1],r[0]);//give space to the pivot at the top of the array with bigger values
			if(i!=r[0]+r[1]){//not same swaps
				swap(i,r[0]);} //replace the big number with the number we test
		}
	}
	static void swap(int a, int b){ //swap numbers
     int temp = unorderedTable[a];
     unorderedTable[a] = unorderedTable[b];
     unorderedTable[b] = temp;
	}
}
