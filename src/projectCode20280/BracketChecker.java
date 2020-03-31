package projectCode20280;

public class BracketChecker 
{
	/**
	 * Method to check if parentheses match using array stack
	 * @param string that we wish to check parentheses of 
	 * @return true if balanced, false if not
	 * */
	public static boolean checkParentheses(String in) 
	{
		//If the string is empty then it must be true
		if (in.isEmpty())
			return true;

		ArrayStack<Character> stack = new ArrayStack<Character>(); //Declare custom ArrayStack object
		
		//Loop through each character of the string
		for (int i = 0; i < in.length(); i++) 
		{
			char current = in.charAt(i);
			//If current is a left delimiter push it to the stack
			if (current == '{' || current == '(' || current == '[') 
			{
				stack.push(current);
			}
			
			//If current is a right delimiter then check if it matches
			if (current == '}' || current == ')' || current == ']') 
			{
				//If the stack is empty then the left delimiter is missing
				if (stack.isEmpty())
					return false;
				
				char last = stack.top();
				//If the top of the stack is matching the right delimiter then pop it from the stack
				if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
					stack.pop();
				else
					return false; //If it doesn't match then it must be false
			}
		}
		
		//If the stack is empty at the end then all delimiters were matched so it is true, otherwise false
		return stack.isEmpty() ? true : false; 
	}
	
	/**
	 * Testing method with different strings 
	 * */
	public static void main(String[] main)
	{
		String[] inputs = {
				"[]]()()", //Not correct
				"c[d]", //Correct
				"a{b[c]d}e", //Correct
				"a{b(c]d}e", //Not correct
				"a[b{c}d]e}", //Not Correct
				"a{b(c)", //Not correct
				"][]][][[]][]][][[[", //Not correct
				"(((abc)))" //Correct			
		};
		
		for(String input: inputs)
		{
			boolean isBalanced = BracketChecker.checkParentheses(input);
			System.out.println("isBalanced " + (isBalanced ? "yes " : "no ") + input);
		}
	}

}
