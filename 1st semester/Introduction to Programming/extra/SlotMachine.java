import acm.program.*;
import acm.util.*;
public class SlotMachine extends ConsoleProgram{
 public void run(){
 int poso=50;
 println("Do you like instructions?no");
 /*CHERRY=1
   LEMON=2
   ORANGE=3
   PLUM=4
   BELL=5
   BAR=6
   */
  while(true){
  if (poso>0){
	 String an=readLine("You have"+poso+"."+"would you like to play?");
     if (an.equals("yes")){
	    poso=poso-1;
		Choice s1=new Choice(rgen.nextInt(1,6));
		Choice s2=new Choice(rgen.nextInt(1,6));
		Choice s3=new Choice(rgen.nextInt(1,6));
		print(s1.getChoice()+"  "+s2.getChoice()+"  "+s3.getChoice()+".");
		int xrhm=check(s1.getChoice(),s2.getChoice(),s3.getChoice());
        if (xrhm>0){
		   print("--You win $"+xrhm);
		   poso=poso+xrhm;
		}else
			print("--You lose");
  }else{
	  break;
	 
	 }
  }else{
    break;
  }
  println(" ");
 }
 }
 private RandomGenerator rgen = RandomGenerator.getInstance();
 
 private int check(String a,String b,String g){
	if(a.equals("CHERRY")){
	  int d=2;
	  if(b.equals("CHERRY")){
	   d=d+3;
	   if(g.equals("CHERRY")){
	    d=d+2;
		}
	  }
	  return d;
	}else if (a.equals(b) && b.equals(g) && a.equals("BAR")){
	   return 250;
	}else if(a.equals(b) &&(g.equals(a) || g.equals("BAR"))){
	    if(a.equals("BELL")){
		   return 20;
		}else if(a.equals("PLUM")){
		  return 14;
		}else if(a.equals("ORANGE")){
		  return 10;
		}
	
	}
    return 0;
 }
 }
 