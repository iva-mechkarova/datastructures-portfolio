package projectCode20280;

/**This class implements the queue ADT. It implements it by using my custom singly linked list.*/
public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); //An empty list which will act as a queue
	
	/**
	 * Testing all methods of LinkedQueue
	 * */
	public static void main(String[] args) {
	    LinkedQueue<Integer> A = new LinkedQueue<Integer>();
	    System.out.println("LinkedQueue");
	    
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

	/**Constructor for LinkedQueue. Relies on initially empty list*/
	public LinkedQueue() {};
	
	/**
	 * Returns the number of elements in the queue.
	 * @return number of elements in queue
	 * */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Checks whether the queue is empty.
	 * @return true if the queue is empty, false otherwise
	 * */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	
	/**
	 * Insert an element at the end of queue
	 * @param element to insert
	 * */
	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	
	/**
	 * Method to return the first element of the queue
	 * @return first element of queue
	 * */
	@Override
	public E first() {
		return list.first();
	}

	/**
	 * Method to remove and return the first element of the queue
	 * @return first element of queue
	 * */
	@Override
	public E dequeue() {
		return list.removeFirst();
	}
	
	/**toString method for LinkedQueue
	 * @return string*/
	@Override
	public String toString() 
	{
		String queue;
		queue = "[";
		if (size() > 0)
			queue += list.get(0);
		
		if (size() > 1)
		{
			for (int i = 1; i <= size() - 1; i++) 
			{
				queue += ", " + list.get(i);
			}
		}
		
		queue = queue + "]";
		return queue;
	}

}
