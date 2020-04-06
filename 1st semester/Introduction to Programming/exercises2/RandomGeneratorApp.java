import acm.program.*;
import acm.util.*;
public class RandomGeneratorApp extends Program{
public void run(){
	RandomGeneratorImproved d=new RandomGeneratorImproved();
	int a,b,ans,ans2,stop;
	int n=0;
	stop=readInt("Hello!This a program that create Random Numbers until you press 0.If you want to continue press 1");
	while(stop!=0){
		println("What type of random number do you to want create?");
		ans=readInt("If you want a Prime number press 1,if you want a Power of 2 press 2,if you want a Fibonacci press 3,if you want a Square press 4");
		while(ans!=1 && ans!=2 && ans!=3 && ans!=4){
			println("Something went wrong.Try again..Possible answers is 1,2,3 or 4");
			 ans=readInt("If you want a Prime number press 1,if you want a Power of 2 press 2,if you want a Fibonacci press 3,if you want a Square press 4");
		}
		ans2=readInt("If you want to choose only upper bound press 1.If you want to choose both lower and upper bound press 2");
		while(ans2!=1 && ans2!=2){
			println("Something went wrong.Try again..Possible answers is 1 or 2");
			ans2=readInt("If you want to choose only upper bound press 1.If you want to choose both lower and upper bound press 2");
		}
		if (ans2==1){
			a=0;
			b=readInt("press upper bound");
		}else{
			a=readInt("press under bound");
			b=readInt("press upper bound");
		}
		if(ans==1){
			n=d.nextPrime(a,b);
		}else if (ans==2){
			n=d.nextPower(a,b);
		}else if(ans==3){
			n=d.nextFibonacci(a,b);
		}else if (ans==4){
			n=d.nextSquare(a,b);
		}
		println(n);
		stop=readInt("Press 1 if you want to read one more number.Press 0 if you want to stop");
}
}
}