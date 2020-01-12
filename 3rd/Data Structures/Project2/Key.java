
public interface Key<T extends Comparable<T>> {
	int getId();
	public int compareTo(T t);
	int getLikes();
	public String toString();
}
