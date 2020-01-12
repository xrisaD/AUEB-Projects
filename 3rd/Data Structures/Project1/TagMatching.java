import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TagMatching {
	/*
	*reads the file which it's name is given as a string
	*return a string that contains all the lines from the given file
	*/
	public static String readFile(String html){
		StringBuilder tmpContent = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(html)); 
		    String str;
		    while ((str = in.readLine()) != null) {
		       tmpContent.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		return tmpContent.toString();
	}
	/*
	*accept as an entry a string and check if the tags maching
	*prints true if the tags close
	*
	*/
	public static void checkMatching(String content){
		boolean nomatching=false;
		int i=-1;
		StringStackImpl<String> myStack=new StringStackImpl<String>("Tag List");//creates a String stack 
		while(content.indexOf("<",++i)!=-1){ 
			i=content.indexOf("<",i);//find the next tag
			if(content.charAt(i+1)!='/'){ //if we find open tag we push the tagname at list's top
				myStack.push (content.substring(i+1,content.indexOf(">",i)));
			}else{ /*if we find close tag we check if the specific tagname is equal to the list's top string.
					If it is, remove it from the stack.
					The string that is at the top of the list is the next closing tag that we expect to find.
					Else break.
				  */
				if(!myStack.isEmpty()){//Special case:check if the first opening tag doesn't exist.
									   //When we try to close this the stack is empty
					if(myStack.peek().equals(content.substring(i+2,content.indexOf(">",i)))){
						myStack.pop();
					}else{
						break;
					}
				}else{
					nomatching=true;
				}
			}
		}
		/*
		If stack is not empty print or the special case happens print true.
		All strings are removed if all tags are matching.
		*/
		if(!myStack.isEmpty() || nomatching){ 
			System.out.println("Not matching html tags");
		}else{
			System.out.println("Matching html tags");
		}
	}
	
	public static void main(String[] args) {
		String html=readFile(args[0]); //read file name and return html in a string 
		checkMatching(html);		  //check if the tags match
		
	}
}
