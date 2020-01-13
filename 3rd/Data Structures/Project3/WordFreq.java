
public class WordFreq {
	
	private String word;
	private int count;
	
	public WordFreq() {
		this.count = 0;
		this.word = "";
	}
	
	public WordFreq(String word) {
		this.word = word.toUpperCase();//case in-sensitive
		this.count = 1;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int x) {
		this.count = x;
	}
	
	public String key() {
		return word;
	}
	
	public void increaseCount() {
		this.count ++;
	}
	
	
	public String toString() {
		return "Word: " + this.word + " Count: " + this.count; 
	}
	
	
}

