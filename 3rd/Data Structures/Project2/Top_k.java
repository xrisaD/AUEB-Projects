import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Top_k {
	
	public static void main(String[] args) {
		File f = null;
		BufferedReader reader = null;
		String line=null;
		int id=0;
		String name="";
		int likes=0;
		int first=0;
		int second=0;
		Song[] songs=new Song[9999];
		int k=0;
		//set k
		try{
			k=Integer.parseInt(args[0]); //read likes
		}catch(NumberFormatException e){
			System.err.println("No integer");
		}
		
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
		int j=-1;
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
					songs[++j]=s;
				}
				line=reader.readLine(); 
			}
		}
		catch (IOException e){
			System.err.println("Error reading line .");
		}
		//sort 
		mergesort(songs,0,j);
		
		//print the first k songs
		if(k<=j+1) {
			System.out.println("The top k songs are:");
			for (int i = 0 ; i<k ; i++){
	        	System.out.println(songs[i].getTitle());
	        }
		}else {
			System.out.println("Something went wrong!");
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
	
	//sorting functions
	public static void mergesort(Song[] songs,int p,int r) {
		if(r<=p) { return;}
		int m=(r+p)/2;
		mergesort(songs, p, m);
		mergesort(songs, m+1, r);
		merge(songs, p, m, r);
	}
	static void merge(Song[] songs, int p, int m, int r) {
		Song[] aux=new Song[songs.length];
		int i, j;
		for (i = m+1; i > p; i--) {
			aux[i-1]=songs[i-1];
		}
		for (j = m; j < r; j++) { //reverse aux
			aux[j+1] = songs[r+m-j];
		}
		for (int k = p; k <= r; k++) {
			if (aux[j].compareTo(aux[i])==1) { 
				songs[k]=aux[j--];
			}else { 
				songs[k]=aux[i++];
			}
		}
	}
} 