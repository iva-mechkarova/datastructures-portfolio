package projectCode20280;

/**This class implements the stack ADT. It implements it by using an array.
 * It is a bounded stack as it has a max size.*/
public class BoundedStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000; //Default array capacity
	private E[] data; //Generic array used to store data
	private int t = -1; //Index of top element of stack
	private int maxSize; //Used to make stack bounded

	/**
	 * Testing all methods of BoundedStack
	 * */
	public static void main(String[] args) {
	    BoundedStack<Integer> A = new BoundedStack<Integer>(15);
	    
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
	
	/**Constructor to construct stack with default capacity*/
	public BoundedStack()
	{	
		this(CAPACITY);
		maxSize=CAPACITY;
	}
	
	/**Constructor to construct stack with given capacity*/
	@SuppressWarnings({"unchecked"})
	public BoundedStack(int capacity)
	{
		maxSize=capacity;
		data = (E[]) new Object[capacity];
	}

	/**
	 * Returns the number of elements in the stack.
	 * @return number of elements in stack
	 * */
	@Override
	public int size() {
		return t+1;
	}

	/**
	 * Checks whether the stack is empty.
	 * @return true if the stack is empty, false otherwise
	 * */
	@Override
	public boolean isEmpty() {
		return t==-1;
	}

	/**
	 * Method to add element to the stack.
	 * @param element to add to stack
	 * */
	@Override
	public void push(E e) {
		//Throws exception if stack is full
		if(size()==maxSize)
		{
			throw new IllegalArgumentException("Cannot push as stack is full.");
		}
		
		data[++t] = e;
	}

	/**
	 * Method to get top of stack.
	 * @return element at top of stack
	 * */
	@Override
	public E top() {
		if (isEmpty()) 
		{
			System.out.println("Cannot get top as stack is empty.");
			return null;
		}
		return data[t];
	}

	/**
	 * Method to remove element from the stack.
	 * @return element which was removed
	 * */
	@Override
	public E pop() {
		if(isEmpty())
		{
			System.out.println("Cannot pop as stack is empty.");
			return null;
		}
		
		E element = data[t];
		data[t--] = null;
		return element;
	}
	
	/**toString method for BoundedStack
	 * @return string*/
	@Override
	public String toString() 
	{
		String stack;
		stack = "[";
		if (size() > 0)
			stack += data[0];
		
		if (size() > 1)
		{
			for (int i = 1; i <= size() - 1; i++) 
			{
				stack += ", " + data[i];
			}
		}
		
		stack = stack + "]";
		return stack;
	}

}
