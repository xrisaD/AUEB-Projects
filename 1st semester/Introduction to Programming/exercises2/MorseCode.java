import acm.program.*;
public class MorseCode extends Program{
	private static String[] MORSE={".-" , "-..." , "-.-." , "-.." , "." , "..-." , "--." , "...." , ".." , ".---" , "-.-" , ".-.." , "--" , "-." , "---" , ".--." , "--.-" , ".-." , "..." , "-" , "..-" , "...-" , ".--" , "-..-" , "-.--" , "--.."};
	public void run(){
	String text=readLine("Enter your text");
	text=text.toUpperCase();
	char y;
	for(int i=0;i<text.length();i++){
		y=text.charAt(i);
		if(y==' '){
			println();
		}else if(y-'A'<=25 && y-'A'>=0){
			print(MORSE[y-'A']);
		}
	}
}
}