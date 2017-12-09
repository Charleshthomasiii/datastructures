public class HuffmanTree{
	HuffmanNode root; // points to first node

	public HuffmanTree(HuffmanNode huff){
		this.root = huff; //constructor sets the root to

	}
	public void printLegend(){
		printLegend(root, "");
	}
	private void printLegend(HuffmanNode t, String s){
		if(t.letter.length()>1){
			printLegend(t.left, s+ "0");
			printLegend(t.right, s+ "1");

		}
		else{
			System.out.println(t.letter+"="+s);
		}
	}

	public static HuffmanNode[] stringToArray(String legend){
		String[] splited = legend.split(" "); //split the legend into an array of strings
		// System.out.println(legend.length());
		HuffmanNode [] noods = new HuffmanNode[(splited.length/2)];//half the 
		// System.out.print(noods.length);
		int j =0;

		for(int i =0; i<noods.length; i++){
			j=i*2;
			String letter=splited[j];

			noods[i]=new HuffmanNode(letter, new Double( Integer.parseInt(splited[j+1]) ));
		}
		return noods;

	}
	public static BinaryHeap legendToHeap(String legend){		
		//HuffmanNode [] noods = new HuffmanNode[]{ new HuffmanNode("A",new Double(20)), new HuffmanNode("E",new Double(24)), new HuffmanNode("G",new Double(3)), new HuffmanNode("H",new Double(4)), new HuffmanNode("I",new Double (17)), new HuffmanNode("L",new Double(6)), new HuffmanNode("N",new Double(5)), new HuffmanNode("O",new Double(10)), new HuffmanNode("S",new Double(8)), new HuffmanNode("V",new Double(1)), new HuffmanNode("W",new Double(2)) }; 
		HuffmanNode [] noods = stringToArray(legend); //passes array through string to array method, converts a properly formatted string into huffman node array
		BinaryHeap yeet = new BinaryHeap(noods);//pass an array of nodes in
		return yeet;
	}
	public static HuffmanTree createFromHeap(BinaryHeap b){
		HuffmanNode last = new HuffmanNode("f", new Double(10)); //arbitrary node that is written over couldnt use null because there are two constructors and it was ambiguous, better solution probably exists but this works
		while(b.getSize()>1){
			HuffmanNode nod = new HuffmanNode( (HuffmanNode) b.deleteMin(), (HuffmanNode) b.deleteMin() );
			b.insert(nod);
			last=nod;
		}
		HuffmanTree tree = new HuffmanTree(last);//not sure if the last node should take children idk
		return tree; 
	}
	public static void main(String[] args) {
		String strlgd ="A 20 E 24 G 3 F 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
		BinaryHeap bheap = legendToHeap(strlgd);
		System.out.print("bineary heap: ");
		bheap.printHeap();
		HuffmanTree htree= createFromHeap(bheap);
		System.out.println("legend: ");
		htree.printLegend();
	}
}