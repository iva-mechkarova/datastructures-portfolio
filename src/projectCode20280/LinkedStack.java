package projectCode20280;

/**This class implements the stack ADT. It implements it by using my custom singly linked list.*/
public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); //An empty list which will act as a stack
	
	/**
	 * Testing all methods of LinkedStack
	 * */
	public static void main(String[] args) {
	    LinkedStack<Integer> A = new LinkedStack<Integer>();
	    System.out.println("LinkedStack");
	    
    	for(int i=0; i<20; i++)
    	{
    		A.push(i);
    	}
    	System.out.println(A + " size: " + A.size());
    	
    	for(int i=0; i<5; i++)
    	{
    		A.pop();
    	}
    	
    	System.out.println("After popping 5 elements:");
    	System.out.println(A + " size: " + A.size());
    	System.out.println("Top: " + A.top());
    	System.out.println("is empty: " + A.isEmpty());
    	
    	while(!A.isEmpty())
    	{
    		A.pop();
    	}
    	
    	System.out.println(A);
    	System.out.println("is empty: " + A.isEmpty());
	}
	
	/**Constructor for LinkedStack. Relies on initially empty list*/
	public LinkedStack() {};

	/**
	 * Returns the number of elements in the stack.
	 * @return number of elements in stack
	 * */
	@Override
	public int size() 
	{
		return list.size();
	}

	
	/**
	 * Checks whether the stack is empty.
	 * @return true if the stack is empty, false otherwise
	 * */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	
	/**
	 * Method to add element to the stack.
	 * @param element to add to stack
	 * */
	@Override
	public void push(E e) {
		list.addFirst(e);	
	}

	
	/**
	 * Method to get top of stack.
	 * @return element at top of stack
	 * */
	@Override
	public E top() {
		return list.first();
	}

	
	/**
	 * Method to remove element from the stack.
	 * @return element which was removed
	 * */
	@Override
	public E pop() {
		return list.removeFirst();
	}
	
	/**toString method for LinkedStack
	 * @return string*/
	@Override
	public String toString() 
	{
		String stack;
		stack = "[";
		if (size() > 0)
			stack += list.get(0);
		
		if (size() > 1)
		{
			for (int i = 1; i <= size() - 1; i++) 
			{
				stack += ", " + list.get(i);
			}
		}
		
		stack = stack + "]";
		return stack;
	}

}
