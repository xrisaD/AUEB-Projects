import java.io.*;
import java.lang.*;

public class Song implements Comparable<Song>,Key<Song>{
	
	private int id;
	private String title;
	private int likes;
	
	Song(){
		this.id = 0;
		this.title = null;
		this.likes = 0;
	}
	
	Song(int id, String title,int likes){
		this.id = id;
		this.title = title;
		this.likes = likes;
	}
	
	public int getId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getLikes(){
		return likes;
	}
	
	public void setId(int x){
		id = x;
	}
	
	public void setTitles(String x){
		title = x;
	}
	
	public void setLikes(int x){
		likes = x;
	}
	
	public void increaseLikes(){
		likes++;
	}
	@Override
	public int compareTo(Song s) {
		if(this.getLikes()<s.getLikes()) {
			return -1; 
		}else if(this.getLikes()>s.getLikes())  {
			return 1;
		}
		return -this.getTitle().compareTo(s.getTitle());//if likes are equal
	}
	public String toString(){
		return "Id: "+ getId() +" Title: "+ getTitle() + " : "+" Likes : "+ getLikes() +"\n";
	}
}