import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class BinaryTree {

	private static class TreeNode< AnyType> implements Comparable //comparable tree nodes
	{
		double distance; //distance between nodes
		public AnyType element;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode (AnyType o)
		{
			this (o,0, null, null); //default constructor takes 0 distance and an element
		}
	
		public TreeNode (AnyType o, double d, TreeNode l, TreeNode r)
		{
			element = o;
			left = l;
			right = r;
			distance = d;
		}
		
		public String toString()
		{
			return "" + element;
		}

		public int compareTo(Object n){ //compare to method overrides comparable method
			double dist;
			TreeNode g = (TreeNode) n;
			dist = this.distance-g.distance;
			return (int) dist; //why tf does korth want the distance as a double
		}
		public boolean equals(TreeNode n){ //not sure if I ended up using this but oh well
			if(this.element==n.element){
				return true;
			}
			else{
				return false;
			}
		}
	}

	private TreeNode root; //only instance varible inside BinaryTree object

	public BinaryTree( TreeNode root )
	{
		this.root = root; //default and only constructor
	}
	
	public TreeNode breadthFirstSearch( Object o ) //.the meat and potatoes of my program
	{
		TreeNode b;
		BinaryHeap q = new BinaryHeap(); 
		//changed the existing program from using a queueli queue to using a BinaryHeap
		//the necessary binaryheap files are included
		q.insert(root);
		while(!q.isEmpty())
		{
			b = (TreeNode) q.deleteMin();
			
			if (b.element.equals (o))
				return b;
			
			if (b.left != null){
				b.left.distance+=b.distance;
				q.insert (b.left);
			}
			if (b.right != null){
				b.right.distance+=b.distance;
				q.insert (b.right);
			}
		}
		//kept the OG code just in case
		// QueueLi q = new QueueLi();
		// q.enqueue(root);
		
		// while(!q.isEmpty())
		// {
		// 	b = (TreeNode)q.dequeue();
			
		// 	if (b.element.equals (o))
		// 		return b;
			
		// 	if (b.left != null)
		// 		q.enqueue (b.left);
		// 	if (b.right != null)
		// 		q.enqueue (b.right);
		// }
		return null; //needs return statement but won't get here if nothing went wrong
	}

	public static TreeNode stringToTree(File file){//throws FileNotFoundException
		String temp;
		TreeNode left_child;
		TreeNode right_child;
		TreeNode parent;
		LinkedStack<TreeNode> $tack = new LinkedStack(); 
		//I decided to use a stack implemented as a linked list because i like nodes
		//The necessary files are included
		try{	
			Scanner sc = new Scanner(file); 
			//In the instructions for the program, it specified entering a text file
			//as a 'command line argument'. I researched this and found that you could
			//enter command line arguments with the file when you run java 'filename' argument.
			//This program only runs when you do this, so I included a test file "text.txt" 
			//as a default. It should work with other .txt files as long as they are in the 
			//same directory.
			while(sc.hasNext()){
				temp = sc.next(); //reading file
				if(temp.equals("(")){
					//do nothing
				}
				else if( temp.equals(")")){ //using stack to build proper tree
					left_child = $tack.pop();
					right_child = $tack.pop();
					parent = $tack.pop();
					parent.left = left_child;
					parent.right = right_child;
					$tack.push(parent);
				}
				else{
					TreeNode tempTree = new TreeNode(temp, Double.parseDouble(sc.next()),null,null); //the parsedouble converts the scanner token into a double/.
					//TBH I don't know why korth had us use a double for the distance instead of a int, but in the instructions he makes it a double so I'm not gonna question it.
					$tack.push(tempTree);
				}

			}
			sc.close();
		}
		catch (FileNotFoundException ex){ //if the file entered is invalid either because it doesn't exist or was typed wrong.
			System.out.println("Error, please enter a valid .txt file along with this program as a command line argument.");
			System.out.println("The example file downloaded with this prgram is 'text.txt'");
			System.out.println("Try running 'java BinaryTree 'text.txt' from the command line after compiling the program");
		}
		return ($tack.pop());
	}

	public static void main(String[] args) {
		String fileName = args[0]; //taking argument from command line
		File file = new File(fileName);
		TreeNode tr33 =stringToTree(file);
		//this stuff below is what i used to manually build a tree to test my program
		//"a 0 ( b 9 ( * 180 b 6 ) w 9 ( x 3 y 200 ( * 100 z 3 ) ) )"
		// TreeNode car12 = new TreeNode("z",3,null,null);
		// TreeNode car11 = new TreeNode("*",2,null,null);
		// TreeNode car1 = new TreeNode("y",5,car11,car12);
		// TreeNode car2 = new TreeNode("x",3,null,null);
		// TreeNode car3 = new TreeNode("w",9,car2,car1);
		// TreeNode car4 = new TreeNode("b",6,null,null);
		// TreeNode car5 = new TreeNode("*",100,null,null);
		// TreeNode car6 = new TreeNode("b",4,car4,car5);
		// TreeNode car7 = new TreeNode("a",0,car3,car6);
		BinaryTree test = new BinaryTree(tr33);//		
		System.out.println("Please enter the character you would like to search for: "); //searches through the given text file after it has been converted to a string
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		System.out.println("Found "+input+" at distance "+test.breadthFirstSearch(input).distance+".");//finds the 'closest' tree node and returns its distamce field. 
	}
}