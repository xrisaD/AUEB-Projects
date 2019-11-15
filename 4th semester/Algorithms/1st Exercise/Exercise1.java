import java.io.File;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Exercise1
{

	private int x;
	private ArrayList<Integer> A;	

	public Exercise1( int x, ArrayList<Integer> A )
	{
		this.x = x;
		this.A = A;
	}

	public int[] getAnswers()
	{
		/*
		* Implement your solution.
		*/
		int[] ans=new int[2];
		ans[0]=findFirstInteger(A,0,A.size()-1,x);
		ans[1]=findLastInteger(A,0,A.size()-1,x);
		return ans;
	}
	/**
	*Return the first given integer in a given List<Integer>
	*@param List<Integer> to search 
	*@param left bound of List
	*@param right bound of List
	*@return the integer we try to find 
	*/
	public static int findFirstInteger(ArrayList<Integer> Integers,int left,int right,int integer){
		int mid = (left + right) / 2; //partition
		if (left >= right && Integers.get(mid)!=integer){//end of possible position
			return -1;
		}
		if(mid==0 && Integers.get(mid)==integer){ //reach head of array and find the given integer
			return mid;
		}
		if(Integers.get(mid)==integer && Integers.get(mid-1)!=integer){ //find the given integer and it is the first in the array 
			return mid; //return index 
		}else if(Integers.get(mid)>=integer){ //given integer smaller than the integer at the mid. Include = because we whant to find the first integer.So we have to reach on the left
			return findFirstInteger(Integers, left, mid, integer);
		}else{
			return findFirstInteger(Integers,mid+1,right,integer);
		}
	}
	/**
	*Return the last given integer in a given List<Integer>
	*@param List<Integer> to search 
	*@param left bound of List
	*@param right bound of List
	*@return the integer we try to find 
	*/
	public static int findLastInteger(ArrayList<Integer> Integers,int left,int right,int integer){
		int mid = (left + right) / 2; //partition
		if (left >= right && Integers.get(mid)!=integer){ //end of possible position
			return -1;
		}
		if(mid==Integers.size()-1){ //tail of List and find the given integer
			return mid;
		}
		if(Integers.get(mid) == integer && Integers.get(mid+1) != integer){ //find the given integer and it is the last in the List 
			return mid; //return index 
		}else if(Integers.get(mid) > integer){//given integer biggers than the integer at the mid
			return findLastInteger(Integers, left, mid, integer);
		}else{
			return findLastInteger(Integers,mid+1,right,integer);
		}
	}
}
