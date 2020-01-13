 class StringComparator extends Comp {
	public int compare(WordFreq a, WordFreq b) {
		return a.key().compareTo(b.key());
	}
}