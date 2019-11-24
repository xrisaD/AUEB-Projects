//ETL function
//metanomasia tou pediou regionname se zipcode
//prosthiki ths hmeras '-01' se kathe onoma sthlhs
//afairesh sthlwn me hmeromhnia prin to 2016
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.*;
import java.util.HashMap;


public class ETL {
	static String[] headers;
	static int[] before2016;
	
	static HashMap<String, Integer> recs = new HashMap<String, Integer>(); //zipcode , number
	
	public static void main(String[] args) {
		//extract and transform
		headers=new String[5];
		before2016=new int[5];
		
		EditHeader1(0,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_1Bedroom.csv");
		dataProcessing(0,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_1Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\no_header\\Zip_MedianRentalPrice_1Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_1Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_1BedroomNoHeader.csv");
		recs.clear();
		
		EditHeader1(1,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_2Bedroom.csv");
		dataProcessing(1,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_2Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\no_header\\Zip_MedianRentalPrice_2Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_2Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_2BedroomNoHeader.csv");
		recs.clear();
		
		EditHeader1(2,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_3Bedroom.csv");
		dataProcessing(2,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_3Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\no_header\\Zip_MedianRentalPrice_3Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_3Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_3BedroomNoHeader.csv");
		recs.clear();
		
		EditHeader1(3,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_4Bedroom.csv");
		dataProcessing(3,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_4Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\no_header\\Zip_MedianRentalPrice_4Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_4Bedroom.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_4BedroomNoHeader.csv");
		recs.clear();
		
		EditHeader1(4,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_5BedroomOrMore.csv");
		dataProcessing(4,"C:\\Users\\laptopara\\Desktop\\databasesProject\\Zip_MedianRentalPrice_5BedroomOrMore.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\no_header\\Zip_MedianRentalPrice_5BedroomOrMore.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_5BedroomOrMore.csv",
			"C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_5BedroomOrMoreNoHeader.csv");
		recs.clear();
		
		
		//load
		String param1="C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_1Bedroom.csv";
		try{
			ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\laptopara\\Desktop\\databasesProject\\gen_ddl_python3.py",param1);
			Process p = pb.start();
		}
		catch (IOException e){
			System.err.println("Error.");
		}
		
		String param2="C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_2Bedroom.csv";
		try{
			ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\laptopara\\Desktop\\databasesProject\\gen_ddl_python3.py",param2);
			Process p = pb.start();
		}
		catch (IOException e){
			System.err.println("Error.");
		}
		
		String param3="C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_3Bedroom.csv";
		try{
			ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\laptopara\\Desktop\\databasesProject\\gen_ddl_python3.py",param3);
			Process p = pb.start();
		}
		catch (IOException e){
			System.err.println("Error.");
		}
		
		String param4="C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_4Bedroom.csv";
		try{
			ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\laptopara\\Desktop\\databasesProject\\gen_ddl_python3.py",param4);
			Process p = pb.start();
		}
		catch (IOException e){
			System.err.println("Error.");
		}
		
		String param5="C:\\Users\\laptopara\\Desktop\\databasesProject\\end\\Zip_MedianRentalPrice_5BedroomOrMore.csv";
		try{
			ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\laptopara\\Desktop\\databasesProject\\gen_ddl_python3.py",param5);
			Process p = pb.start();
		}
		catch (IOException e){
			System.err.println("Error.");
		}
		
	}
	public static void EditHeader1(int i,String input){
		BufferedReader reader = null;
		String line=null;
		File f = null;
		int num=0;
		int from=0;
		int index=0;
		int count=0;
		/*
		open file 
		*/
		try{
			f = new File(input);
			
		}
		catch (NullPointerException e){
			System.err.println ("File not found.");
		}
		
		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		System.out.println("Header Edit 1 From File "+input);
		
		try	{
			line=reader.readLine();
			line=line.replace("RegionName","zipcode");
			from=0;
			index = line.indexOf(",",from);
			count=1;
			while(count < 7){
				from = index + 1;
				index = line.indexOf(",",from);
				count++;
			}
			while(index > 0){
				String newdate = line.substring(from,index);
				if(Integer.parseInt(newdate.substring(0,4))<2016){
					num++;
				}
				line=line.replace(newdate,newdate+"-01");
				from = index + 4;
				index = line.indexOf(",",from);
			}
			line=line+"-01";
		}
		catch (IOException e){
			System.err.println("Error reading line .");
		}
		try{ 
			reader.close();  
			
		}catch (IOException e){
			System.err.println("Error close file .");
		}
		headers[i]=line;
		before2016[i]=num;
	}
	public static void EditHeader2(int i,String input){
		BufferedReader reader = null;
		String line=null;
		File f = null;
		int num=0;
		int from=0;
		int index=0;
		int count=0;
		String newHeader="";
		/*
		open file 
		*/
		try{
			f = new File(input);
			
		}
		catch (NullPointerException e){
			System.err.println ("File not found.");
		}
		
		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		System.out.println("Header Edit 2 Fom File "+input);
		try	{
			line=reader.readLine();
			newHeader=newHeader+"zipcode,";
			
			from=0;
			index = line.indexOf(",",from);
			count=1;
			while(count < 6){
				from = index + 1;
				index = line.indexOf(",",from);
				count++;
				newHeader=newHeader+line.substring(from+1,index-1)+",";
			}
			
			while(true){
				from = ++index;
				index = line.indexOf(",",from);
				if(index<0){
						break;
				}
				String newdate = line.substring(from+1,index-1);
				if(Integer.parseInt(newdate.substring(0,4))<2016){
					num++;
				}
				newHeader=newHeader+newdate +"-01"+",";
			}
			String newdate = line.substring(from+1,line.length()-1);
			newHeader=newHeader+newdate +"-01";
		}
		catch (IOException e){
			System.err.println("Error reading line .");
		}
		try{ 
			reader.close();  
			
		}catch (IOException e){
			System.err.println("Error close file .");
		}
		headers[i]=newHeader;
		before2016[i]=num;
	}
	public static void dataProcessing(int i,String inputHead,String inputNohead,String output,String output2){
		BufferedReader reader = null;
		String line=null;
		File f = null;
		File f2 = null;
		BufferedReader reader2 = null;
		int from=0;
		int index=0;
		int count=0;
		/*
		open file 
		*/
		try{
			f = new File(inputHead);
			
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
		
		/*
		open file 
		*/
		try{
			f2 = new File(inputNohead);
			
		}
		catch (NullPointerException e){
			System.err.println ("File with songs not found.");
		}
		
		try{
			reader2 = new BufferedReader(new FileReader(f2));
		}
		catch (FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		
		FileWriter fw=null;
		try{    
           fw=new FileWriter(output);       
        }catch(Exception e){System.err.println(e);}    
        //write header
		try	{
			line=headers[i];
			from=0;
			index = line.indexOf(",",from);
			count=0;
			while(index > 0){
				count++;
				if(count < 7 || count > 6 + before2016[i]){
					fw.write(line.substring(from,index)+",");
				}
				from = ++index;
				index = line.indexOf(",",from);
			}
			fw.write(line.substring(from));
		}
		catch (IOException e){
			System.err.println("Error write line .");
		}
		System.out.println("Create Output File "+output);
		
		//write data to file with header
		try	{
			line=reader.readLine();//no old header
			line=reader.readLine();
			while (line != null) { //read each line
				from = 0;
				index = line.indexOf(",",from);
				count = 0;
				
				boolean notFound=false;
				
				String key=line.substring(0,index);
				if(recs.get(key) == null){ //not found
					recs.put(key,new Integer(0)); //put to hash map
					notFound=true;
				}else{
					notFound=false;
				}
				if(notFound){
					fw.write("\r\n");
					while(index > 0){
						count++;
						if(count<7 || count > 6 + before2016[i]){
							fw.write(line.substring(from,index)+",");
						}
						from = ++index;
						index = line.indexOf(",",from);
					}
					fw.write(line.substring(from));
				}
				//end line
				line=reader.readLine();
			}
		}catch (IOException e){
			System.err.println("Error write line .");
		}
		recs.clear();
		
		FileWriter fw2=null;
		try{    
           fw2=new FileWriter(output2);       
        }catch(Exception e){System.err.println(e);} 
		//write data to file with No header
		try	{
			line=reader2.readLine();
			while (line != null) { //read each line
				
				from = 0;
				index = line.indexOf(",",from);
				count = 0;
				
				boolean notFound=false;
				
				String key=line.substring(0,index);
				if(recs.get(key) == null){ //not found
					recs.put(key,new Integer(0)); //put to hash map
					notFound=true;
				}else{
					notFound=false;
				}
				if(notFound){
					while(index > 0){
						count++;
						if(count<7 || count > 6 + before2016[i]){
							fw2.write(line.substring(from,index)+",");
						}
						from = ++index;
						index = line.indexOf(",",from);
					}
					fw2.write(line.substring(from));
					fw2.write("\r\n");
				}
				//end line
				line=reader2.readLine();
			}
		}catch (IOException e){
			System.err.println("Error write line .");
		}
		
		try{ 
			fw.close();  
		}catch (IOException e){
			System.err.println("Error close .");
		}
		try{ 
			fw2.close();  
			
		}catch (IOException e){
			System.err.println("Error close .");
		}
		try{ 
			reader.close();  
			
		}catch (IOException e){
			System.err.println("Error close file .");
		}
		try{ 
			reader2.close();  
			
		}catch (IOException e){
			System.err.println("Error close file .");
		}
	}	
}