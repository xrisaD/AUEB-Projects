import java.io.*;

public class NetBenefit {

	public static void main(String[] args) {
		File f = null;
		BufferedReader reader = null;
		String line=null;
		int q=0;
		int p=0;
		int benefit=0;
		int sumofsares=0;
		
		//2 alongside queue
		IntQueueImpl<Integer> quantity=new IntQueueImpl<Integer>("Quantity");//create a queue with quantity
		IntQueueImpl<Integer> price=new IntQueueImpl<Integer>("Price");//create a queue with price
		
		/*
		open file 
		*/
		try{
			f = new File(args[0]);
			
		}
		catch (NullPointerException e){
			System.err.println ("File with sales not found.");
		}
		
		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		
		//create the net benefit
		try	{
			line=reader.readLine();
			while (line!=null) { //read each line 
				if(line.trim().startsWith("buy")) { 
					try{
						q=Integer.parseInt(line.substring(4,line.indexOf("price")).trim()); //read quantity
					}catch(NumberFormatException e){
						System.err.println("No integer");
					}
					try{
						p=Integer.parseInt(line.substring(line.indexOf("price")+6).trim()); //read price
					}catch(NumberFormatException e){
						System.err.println("No integer");
					}
					quantity.put(q);//put quantity in queue
					price.put(p);//put price in queue
					sumofsares+=q;
				}else if(line.trim().startsWith("sell")) {
					try{
						q=Integer.parseInt(line.substring(5,line.indexOf("price")).trim()); //read quantity
					}catch(NumberFormatException e){
						System.err.println("No integer");
					}
					
					try{
						p=Integer.parseInt(line.substring(line.indexOf("price")+6).trim()); //read price
					}catch(NumberFormatException e){
						System.err.println("No integer");
					}
					if (q <= sumofsares) { //quantity that is asked to be sold has to be smallest or equal than total quantity that has been bought
						while (q > 0) { //repeats until there is no selling quantity left 
								if (quantity.peek() > q) { //if shares that are first in queue are greater than quantity that is sold
									benefit += (p - price.peek()) * q; //calculate benefit
									quantity.set(quantity.peek()-q);//subtracts quantity from the shares that is first in queue
									sumofsares-=q;//subtracts quantity from the total shares
									q = 0;
								} else	{ //shares that are first in queue are less or equal to quantity that we want to sell
									benefit += (p - price.peek()) * quantity.peek();//calculate benefit
									sumofsares-=quantity.peek();//subtracts shares that is first in queue from total shares
									price.get();//remove the price when sell all shares with this price
									q -= quantity.get(); //removes and substracts shares that are first in queue from quantity that we sell
								}
						}
					} else {
						System.err.println("Sell quantity bigger than number of shares in possesion!");
						break;
					}
				}
				line=reader.readLine(); 
			}
		}
		catch (IOException e){
			System.err.println("Error reading line .");
		}
		/*
		closing file
		*/
		try {
			reader.close();//close file
		}
		catch (IOException e){
			System.err.println("Error closing file with sales.");
		}
		System.out.println("Net Benefit: " + benefit); //print benefit
	}
	
	
}
