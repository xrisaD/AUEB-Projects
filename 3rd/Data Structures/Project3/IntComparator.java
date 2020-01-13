class IntComparator extends Comp {
	public int compare(WordFreq a, WordFreq b) {
		if(a.getCount()>b.getCount()){
			return -1;
		} else {
			return 1;
		}
	}
}