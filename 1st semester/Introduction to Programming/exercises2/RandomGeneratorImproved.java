import acm.program.*;
import acm.util.*;
import java.lang.Math.*;
public class RandomGeneratorImproved extends RandomGenerator{
	/*alternative method:
		import java.util.ArrayList;
		ArrayList<Integer> Numbers=new ArrayList<Integer>();/*list for all kind of numbers
	public int counter=0;
	public int num(String a){ 
		if(counter<=0){
			System.out.println("Τhere is no "+a+" numbers in this range.");
			return -1;
		}else{
			return Prime(nextInt(0,Number.size));
		}
	}
	public int nextPrime(int n){ 
		return Prime(1,n-1);
	}
	public int nextPrime(int low, int high){
		return Prime(low,high);
	}
	public int Prime(int a,int b,int x){
		for(int i=a;i<=b;i++){
			if(isPrime(i)){
				Numbers.add(i);
			}
		}
		return num("prime");
	}
	public boolean isPrime(int n){ 
		int i;
		double s=Math.sqrt(n);
		if(n==0 ||n==1){
			return false;
		}
		for(i=2;i<=s;i++){
			if (n%i==0){
				return false;
			}
		}
		return true;
	}
	public int nextFibonacci(int n){ 
		return Fibonacci(1,n-1);
	}
	public int nextFibonacci(int low, int high){
		return Fibonacci(low,high);
	}
	public int Fibonacci(int a,int b){
		int fib1, fib2, currentFib;
		if ((a == 1) ||(b==1)) {
			Numbers.add(a);
		}
		if(a==0){
			Numbers.add(1);
			Numbers.add(1);
		}
		fib1=1;
		fib2=0;
		currentFib=1;
		while(a<=b){
				while(currentFib<=a||currentFib==1) {
					if (a == currentFib) {
						Numbers.add(a);
					}
					fib2=fib1;
					fib1=currentFib;
					currentFib=fib1+fib2;
				}
			a++;
		} 
		return num("fibonacci");
	}
	public int nextSquare(int n){ 
		return Square(1,n-1);
	}
	public int nextSquare(int low, int high){
		return Square(low,high);
	}
	public int Square(int a,int b){
		for(int i=a;i<=b;i++){
			double y=Math.sqrt(a);
			if(y==(int)y){
				Numbers.add(a);
			}
		}
		return num("square");
	}
	public int nextPower(int n){
		return Power(1,n-1);
	}
	public int nextPower(int low, int high){
		return Power(low,high);
	}
	public int Power(int a,int b){
		for(int i=a;i<=b;i++){
			if(isPower(i)){
				Numbers.add(i);
			}
		}
		return num("power");
		}
	
	public boolean isPower(int n){
		return n!=0 && ((n&(n-1)) == 0);
	} 
	*/ 
	public int nextPrime(int n){ 
		return(nextPrime(1,n-1));
	}
	public int nextPrime(int low, int high){
		if (low<0||low>high){
			return -1;
		}else{
			return numPrime(checkPrime(low,high),low,high);
		}
	}
	public boolean checkPrime(int a,int b){
		for(int i=a;i<=b;i++){
			int d=0;
			double s=Math.sqrt(i);
			for(int j=2;j<=s;j++){
				if (i%j!=0){
					d=d+1;	
				}
				if(j-1==d){
					return true;
				}
			}
		}
		return false;
	}
	private int numPrime(boolean x,int a,int b){
		int n;
		if (x){
			n=super.nextInt(a,b);
			while(!(checkPrime(n,n)||(n==0)||(n==1))){
				n=super.nextInt(a,b);
			}
			return n;
		}
		System.out.println("there is no prime numbers in this range");
		return -1;
	}
	public int nextFibonacci(int n){
		return numFibonacci(checkFibonacci(1,n-1),1,n-1);
	}
	public int nextFibonacci(int low, int high){
		return numFibonacci(checkFibonacci(low,high),low,high);
	}
	public boolean checkFibonacci(int a,int b){
			int fib1, fib2, currentFib;
			 while(a<=b){
				if ((a==0) || (a == 1)) {
					return true;
				}
				fib1=1;
				fib2=0;
				currentFib=a-1;
				if(a>=2) {
					while(currentFib<a) {
						currentFib = fib1 + fib2;
						if (a == currentFib) {
							return true;
						}
						fib2=fib1;
						fib1=currentFib;
					}
				}
			 a++;
			 }
			return false;
	}
	private int numFibonacci(boolean x,int a,int b){
		int n;
		if (x){
			n=super.nextInt(a,b);
			while(!(checkFibonacci(n,n))){
				n=super.nextInt(a,b);
			}
			return n;
		}
		System.out.println("there is no fibonacci numbers in this range");
		return -1;
	}
	public int nextSquare(int n){
		return numSquare(checkSquare(1,n-1),1,n-1);
	}
	public int nextSquare(int low, int high){
		return numSquare(checkSquare(low,high),low,high);
	}
	public boolean checkSquare(int a,int b){
		for(int i=a;i<=b;i++){
			double y=Math.sqrt(a);
			if(y==(int)y){
				return true;
			}
		}
		return false;
	}
	private int numSquare(boolean x,int a,int b){
		int n;
		if (x){
			n=super.nextInt(a,b);
			boolean y=checkSquare(n,n);
			while(!y || n==0){
				n=super.nextInt(a,b);
			}
			return n;
		}
		System.out.println("there is no square numbers in this range");
		return -1;
	}
	public int nextPower(int n){ 
		return numPower(checkPower(1,n-1),1,n-1);
	}
	public int nextPower(int low, int high){
		return numPower(checkPower(low,high),low,high);
	}
	public boolean checkPower(int a,int b){
		for(int i=a;i<=b;i++){
			if(i!=0 && ((i&(i-1)) == 0)){
				return true;
			}
		}
		return false;
	}
	private int numPower(boolean x,int a,int b){
		int n;
		if (x){
			n=super.nextInt(a,b);
			while(!(checkPower(n,n))){
				n=super.nextInt(a,b);
			}
			return n;
		}
		System.out.println("there is no power numbers in this range");
		return -1;
	}
}