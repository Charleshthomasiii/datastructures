import java.util.*;

public class ExpressionTreeCalculator {
	public String expression; 
	public Node tree;


	public ExpressionTreeCalculator(String str){
		this.expression= str;
	}

	public void convert(){
		ArrayStack stack = new ArrayStack(1000); //create new empty stack
	   	Converter con = new Converter(expression); //pass the expression into a converter class
	   	String post = con.toPostFix(); //convert it to post fix
	   	for(int i=0; i<post.length(); i++){
	   		if(Character.isDigit(post.charAt(i))){ //if the token is a number
	   			int numb = ((int) post.charAt(i)-48);
	   			Node num = new Node(numb);
	   			stack.push(num);
	   		}
	   		else{
	   			Node exp = new Node(post.charAt(i));
	   			exp.rightChild = (Node) stack.pop();
	   			exp.leftChild = (Node) stack.pop();
	   			stack.push(exp);//push node onto stack
	   		}
	   	}
	   	this.tree = (Node) stack.pop();
	}

    public void prefix(){  //prefix notation print method
		prefix(this.tree);
	}
	public void prefix(Node n){
		System.out.print(n); //print value at node
		if(n.leftChild != null){ //if there is a left child
			prefix(n.leftChild); //recursively call on left child
		}
		if(n.leftChild != null){
			prefix(n.rightChild); //recursively call on the right child
		}
    }


    public void infix(){  //infix notation print method
		infix(this.tree);
	}
	public void infix(Node n){
		if(n.leftChild != null){ //if there is a left child
			System.out.print("("); //print paren
			infix(n.leftChild); //recursively call on left child
		}
		System.out.print(n); //print value at node
		if(n.leftChild != null){
			infix(n.rightChild); //recursively call on the right child
			System.out.print(")");
		}
    }

    public void postfix(){  //postfix notation print method
		postfix(this.tree);
	}
	public void postfix(Node n){
		if(n.leftChild != null){ //if there is a left child
			postfix(n.leftChild); //recursively call on left child
		}
		if(n.leftChild != null){
			postfix(n.rightChild); //recursively call on the right child
		}
		System.out.print(n); //print value at node

    }

   	public static void main(String args[]) {
		while(true){
		 	Scanner scanner = new Scanner(System.in);
		 	System.out.println("Type your expression:");
		 	String exp = scanner.next();
		 	ExpressionTreeCalculator test = new ExpressionTreeCalculator(exp);

		 	
		 	test.convert();
		 	System.out.print("Prefix: ");
		   	test.prefix();
		   	System.out.println();
		   	System.out.print("Infix: ");
		   	test.infix();
		   	System.out.println();
		   	System.out.print("Postfix: ");
		   	test.postfix();
		   	System.out.println();
   	}


   }
}
