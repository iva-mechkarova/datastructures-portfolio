package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

	private CircularlyLinkedList<E> list = new CircularlyLinkedList<>(); //An empty list which will act as a queue
	
	/**
	 * Testing all methods for LinkedCircularQueue
	 * */
	public static void main(String[] args) {
	    LinkedCircularQueue<Integer> cq = new LinkedCircularQueue<Integer>();
	    System.out.println("LinkedCircularQueue");
	    
    	for(int i=0; i<20; i++)
    	{
    		cq.enqueue(i);
    	}
    	System.out.println(cq + " size: " + cq.size());
    	
    	for(int i=0; i<5; i++)
    	{
    		cq.dequeue();
    	}
    	
    	System.out.println("After dequeuing 5 elements:");
    	System.out.println(cq + " size: " + cq.size());
    	System.out.println("First: " + cq.first());
    	System.out.println("is empty: " + cq.isEmpty());
    	
    	while(!cq.isEmpty())
    	{
    		cq.dequeue();
    	}
    	
    	System.out.println(cq);
    	System.out.println("is empty: " + cq.isEmpty());

	}

	/**Constructor for LinkedCircularQueue. Relies on initially empty list*/
	public LinkedCircularQueue() {};
	
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
		list.advance();
	}
	
	/**
	 * Method to return the first element of the queue
	 * @return front of queue
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
		list.advance();
		return list.remove((list.getCursor()+1)%size()); //Modular arithmetic as it is circular
	}
	
	/**toString method for LinkedCircularQueue
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
