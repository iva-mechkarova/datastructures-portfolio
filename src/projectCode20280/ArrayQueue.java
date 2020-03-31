package projectCode20280;

/**This class implements the queue ADT. It implements it by using an array.*/
public class ArrayQueue<E> implements Queue<E> {

	//Instance variables
	public static final int CAPACITY = 1000; //Default array capacity
	private E[] data; //Array of type E (generic) used to store data
	private int f = 0; //Index of front element
	private int size = 0; //Size of queue
	
	public static void main(String[] args) {
	    ArrayQueue<Integer> A = new ArrayQueue<Integer>();
	    
    	for(int i=0; i<20; i++)
    	{
    		A.enqueue(i);
    	}
    	System.out.println(A + " size: " + A.size());
    	
    	for(int i=0; i<5; i++)
    	{
    		A.dequeue();
    	}
    	
    	System.out.println("After dequeuing 5 elements:");
    	System.out.println(A + " size: " + A.size());
    	System.out.println("First: " + A.first());
    	System.out.println("is empty: " + A.isEmpty());
    	
    	while(!A.isEmpty())
    	{
    		A.dequeue();
    	}
    	
    	System.out.println(A);
    	System.out.println("is empty: " + A.isEmpty());

	}
	
	/**Constructor to construct queue with default capacity*/
	public ArrayQueue() 
	{
		this(CAPACITY);
	}
	
	/**Constructor to construct queue with given capacity*/
	@SuppressWarnings({"unchecked"})
	public ArrayQueue(int capacity) 
	{
		data = (E[]) new Object[capacity]; //Safe cast
	}

	/**
	 * Returns the number of elements in the queue.
	 * @return number of elements in queue
	 * */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Checks whether the queue is empty.
	 * @return true if the queue is empty, false otherwise
	 * */
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	/**
	 * Insert an element at the end of queue
	 * @param element to insert
	 * */
	@Override
	public void enqueue(E e) throws IllegalStateException
	{
		//If size equals the size of the array then throw exception
		if(size==data.length) throw new IllegalStateException("Queue is full");
		
		int avail = (f+size)%data.length; //Use modular arithmetic to find rear of queue
		data[avail] = e;
		size++;
	}

	/**
	 * Method to return the first element of the queue (null if empty)
	 * @return first element of queue, or null if empty 
	 * */
	@Override
	public E first() {
		if(isEmpty())
		{
			return null;
		}
		
		return data[f];
	}

	/**
	 * Method to remove and return the first element of the queue (null if empty)
	 * @return first element of queue, or null if empty 
	 * */
	@Override
	public E dequeue() {
		if(isEmpty())
		{
			return null;
		}
		
		E removed = data[f];
		data[f] = null;
		f = (f+1)%data.length;
		size--;
		return removed;
	}
	
	/**toString method for ArrayQueue
	 * @return string*/
	@Override
	public String toString() 
	{
		String queue;
		queue = "[";
		if (size() > 0)
			queue += data[0];
		
		if (size() > 1)
		{
			for (int i = 1; i <= size() - 1; i++) 
			{
				queue += ", " + data[i];
			}
		}
		
		queue = queue + "]";
		return queue;
	}

}
