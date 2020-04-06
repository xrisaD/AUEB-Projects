import acm.program.*;
public class Ticket extends Program{
	private static final double PRICE=1.2;
	public void run(){
		double money=readDouble("Enter money.");
		double s=0;
		while(money!=0){
			if (money==0.1 || money==0.2 || money==0.5 || money==1 || money==2 || money==5){
				s+=money;
			}else{
				println("Try again.");
			}
				money=readDouble("Enter money.");
		}
		if (s>=PRICE){
			println("Here is your ticket.");
			s=s-PRICE;
		}else{
			println("Not enough money to buy the ticket.");
	   }
		while(s>0){
			if (s>=5){
			   s=s-5;
			   println("You have change:5");
			}else if(s>=2){
			   s=s-2;
			   println("You have change:2");
			}else if(s>=1){
			  s=s-1;
			   println("You have change:1");
			}else if(s>=0.5){
			   s=s-0.5;
			   println("You have change:0.5");
		   }else if(s>=0.2){
			   s=s-0.2;
			   println("You have change:0.2");
		   }else{
			   s=s-0.1;
			   println("You have change:0.1");
			}
		}
	}
}