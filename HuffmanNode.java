public class HuffmanNode implements Comparable{
	public String letter;
	public Double frequency;
	public HuffmanNode left, right;
	public HuffmanNode(String letter, Double frequency){
		//contructor method
		//creates a new huffman node where the letter is set to this.letter
		//frequency is set to this.frequency
		//and left and right are set to null

		this.letter = letter; 
		this.frequency = frequency;
		left = null;
		right = null;
	}
	public HuffmanNode(HuffmanNode left, HuffmanNode right){
		//alternate constructor method no default exists
		this.left = left;
		this.right = right;
		this.letter = left.letter +right.letter;
		this.frequency = left.frequency +right.frequency;
	}
	public int compareTo(Object O){
		HuffmanNode huff = (HuffmanNode) O;
		int freq = this.frequency.compareTo(huff.frequency);
		return freq;
	}
	public String toString(){
		//overwrites superclass method, possibly prints element, frequencylt
		String str = "<"+letter+","+frequency+">";
		return str;
	}

}