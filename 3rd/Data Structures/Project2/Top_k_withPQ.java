import java.io.*;

public class Top_k_withPQ {
	
	
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		PQ<Song> songs = new PQ<Song>(2*k,-1);
		File f = null;
		BufferedReader reader = null;
		String line=null;
		int id=0;
		String name="";
		int likes=0;
		int first=0;
		int second=0;
		
		/*
		open file 
		*/
		try{
			f = new File(args[1]);
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
					if(songs.size()==k) { 
						Song min=songs.max();
						if(min.compareTo(s)==-1) { //if new song has more likes than smallest in pq removes k-th element
													//and insert new 
							songs.getMax();
							songs.insert(s);
						} 
						
					}else { //insert if there are not k elements in pq
						songs.insert(s);
					}
				}
				line=reader.readLine(); 
			}
		
		}catch (IOException e){
			System.err.println("Error reading line .");
		}
		for(int i = 0; i <  k; i++){ //print k elements
			System.out.println(songs.getMax());
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
