import java.io.*;

public class Dynamic_Median {
	public static void main(String[] args) {
		File f = null;
		BufferedReader reader = null;
		String line=null;
		int id=0;
		String name="";
		int likes=0;
		int first=0;
		int second=0;
		PQ<Song> bigger = new PQ<Song>(450,1);
		PQ<Song> smaller = new PQ<Song>(450,-1);
		//Theoroume oti to megalutero stoixeio tou smaller PQ tha periexei to median
		int numOfSongs = 0;		
		/*
		open file 
		*/
		try{
			f = new File(args[0]);
		}
		catch (NullPointerException e){
			System.err.println ("File with songs not found.");
		}
		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		try	{
			line=reader.readLine();
			while (line!=null) { //read each line
				first=line.indexOf(" ");
				try{
					id=Integer.parseInt(line.substring(0,first++).trim()); //read id
				}catch(NumberFormatException e){
					System.err.println("No integer");
				}
				for(int i=line.length()-1;i>=0;i--) {//find the last space
					if(line.charAt(i)==' ') {
						second=i;
						break;
					}
				}
				name=line.substring(first,second).trim();//read names
				try{
					likes=Integer.parseInt(line.substring(second).trim()); //read likes
				}catch(NumberFormatException e){
					System.err.println("No integer");
				}
				if(id<=9999 && id>=1){
					//new Song (int id, String title,int likes)
					Song s=new Song(id,name,likes);
					numOfSongs++; 
					if (numOfSongs == 1) {
						smaller.insert(s);
					}else if ((smaller.max()).compareTo(s) < 0) { //if new song is smaller than median
						smaller.insert(s);					//insert it to smaller pq
					} else {			//if new song is bigger than median
						bigger.insert(s);					//insert it to bigger pq
					}
					if (smaller.size() - bigger.size() > 2) {
						bigger.insert(smaller.getMax()); //remove from smaller to bigger
					} else if (bigger.size() - smaller.size() == 0) { //if N is even
						Song tmp = bigger.getMax();	//remove from bigger to smaller
						smaller.insert(tmp);
					}	
					if (numOfSongs % 5 == 0) { //print median 
						System.out.println("Median = " + smaller.max().getLikes() + " likes, achieved by Song: " + smaller.max().getTitle());
					}
				}
				line=reader.readLine(); 
			}
		}catch (IOException e){
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
		
	}
}
