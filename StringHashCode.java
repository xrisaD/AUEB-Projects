
public class StringHashCode extends HashCode{
	public int hashCode(String key,int mul) {
		int hashVal=0;
		for(int i=0;i<key.length();i++){
			hashVal=mul*hashVal+key.charAt(i);
		}
		return hashVal;
	}
}
