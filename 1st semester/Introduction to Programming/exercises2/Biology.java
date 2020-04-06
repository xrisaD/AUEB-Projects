import acm.program.*;
public class Biology extends Program{
	public void run(){
		println(findFirstMatchingPosition(readDNA("short"),readDNA("long")));
	}
	private int findFirstMatchingPosition(String shortDNA,String longDNA){
		return longDNA.indexOf(conversion(shortDNA));
	}
	private String conversion(String shortDNA){
		String result="";
		for(int i=0;i<shortDNA.length();i++){
			if(shortDNA.charAt(i)=='T'){
				result=result+'A';
			}else if(shortDNA.charAt(i)=='A'){
				result=result+'T';
			}else if(shortDNA.charAt(i)=='G'){
				result=result+'C';
			}else{
				result=result+'G';
			}
		}
		return result;
	}
	private boolean check(String DNA){
		char y;
		for(int i=0;i<DNA.length();i++){
			y=DNA.charAt(i);
			if(y!='A' && y!='T' && y!='C' && y!='G'){
				return false;
			}
		}
		return true;
	}
	private String readDNA(String name){
		String DNA;
		while(true){
			DNA=readLine("Enter the "+name+" DNA");
			if(!check(DNA)){
				println("Something went wrong.Try again..Possible answers is alphanumeric with A,T,G and C");
			}else{
				return DNA;
			}
		}
	}
}