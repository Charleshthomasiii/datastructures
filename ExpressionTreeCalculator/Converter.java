import java.util.*;


public class Converter{
	String infix; //represents the user input of operators and operands
	
	public Converter(String input){
		this.infix = input; //converts the input to the infix
	}
	


	public String toPostFix(){

	 	char[] temp = this.infix.toCharArray(); // converts string to char array
	 	ArrayList<String> arraylist = parse(temp); //uses parser and gives parser to arraylist
	 	String output = "";
	 	Stack<String> stack = new ArrayStack<String>(); //need to check if this works
	 	

	 	//The following while loop executes the algorithm to create the postfix string
	 	
	 	for(int i=0;i<arraylist.size();i++){
	 		
	 		if (isDigit(arraylist.get(i))){//uses isDigit static method
	 			//System.out.println("found digit");
	 			stack.push(arraylist.get(i));
	 			output+=stack.pop();
	 			//System.out.println("pushed then popped digit");
	 			//output+=arraylist.get(i)+" ";
	 		}
	 		
	 		else if (arraylist.get(i).equals("(")) {
	 		//	System.out.println("found (");
	 			stack.push(arraylist.get(i));
	 		}
	 		
	 		else if (arraylist.get(i).equals(")")){
	 			while(stack.top().equals("(")==false)//execute loop until the top of the stack reads this paren
	 				output+=stack.pop();
	 			stack.pop();//removes open paren, will always be there bc there was a closed paren
	 		}
	 		
	 		else{
	 			if(stack.size()==0)
	 				stack.push(arraylist.get(i));
	 			else if (precedence(stack.top())>=precedence(arraylist.get(i))) {
	 				while( (stack.size()!=0) && ( precedence(stack.top()) >= precedence(arraylist.get(i))) ){
	 					output+=stack.pop()+ "";
	 					

	 					//stack.push(arraylist.get(i));
	 				} 
	 				stack.push(arraylist.get(i));
	 			}
	 			else{
	 				stack.push(arraylist.get(i));
	 			}
	 		}
	 		//System.out.println("this is the stack "+stack.toString()+" and this is the string "+output);
	 	}
	 	while(stack.size()!=0)//execute loop until the top of the stack reads this paren
	 				output+=stack.pop()+"";
	 	return output;

	 	//Arraylist now contains elements of either operators or operands
	 	//Need to go through the arraylist elements and convert it to string of postfix using algorithm
	 	//need to create a stack and output string
	 }

	 public static boolean isDigit(String str){
	 	return str.matches("\\d+"); //uses regex to check if str is in digits
	 }

	 public static boolean isOperator(String str){
	 	if(str.equals("*")){
	 		return true;
	 	}
	 	else if(str.equals("/")){
	 		return true;
	 	}
	 	else if(str.equals("-")){
	 		return true;
	 	}
	 	else if(str.equals("+")){
	 		return true;
	 	}
	 	else if(str.equals("^")){
	 		return true;
	 	}
	 	else{
	 		return false;
	 	}

	}

 	public static int precedence(String ch) { 
 	    if (ch.equals("-")){
 	    	return 1;
 	    }
 	    if (ch.equals("+")){
 	    	return 1;
 	    } 
 	    if (ch.equals("*")){
 	    	return 2;
 	    } 
 	    if (ch.equals("/")){
 	    	return 2;
 	    } 
 	    if (ch.equals("^")){
 	    	return 3; 
 	  	}
 	  	return 0;
 	}











	 private static ArrayList<String> parse(char[] input) { //takes a char array and converts it to ArrayList where each element is either a full number or a operand with a space before it
	    ArrayList<String> parsed = new ArrayList<String>();
	    for (int i = 0; i < input.length; ++i) {
	        char c = input[i];
	        if (Character.isDigit(c)) {
	            String number = input[i] + "";
	            for (int j = i + 1; j < input.length; ++j) {
	                if (Character.isDigit(input[j])) {
	                    number += input[j];
	                    i = j;
	                } else {
	                    break;
	                }
	            }
	            parsed.add(number);
	        } else if (c == '*' || c == '/' || 
	                   c == '+' || c == '^' || 
	                   c == '-' || c == '(' || c == ')') {
	            parsed.add(c + "");
	        }
	    }
	    return parsed;
	}
	// public static void main(String args[]) {
	// // Converter converter = new Converter("1+2+3+4");
	// // String postfix = converter.toPostFix();
	// // System.out.println(postfix);
	// // Stack<String> S = new ArrayStack<>();
	// 	// System.out.println(isDigit("1023456789"));
	// 	// System.out.println(isDigit("456"));
	// 	// System.out.println(isDigit("+"));
	// 	// System.out.println(isDigit("-"));
	// Converter con = new Converter("(4+8)*(6-5)/((3-2)*(2+2))");
	// System.out.println(con.toPostFix());




	
	// }
}