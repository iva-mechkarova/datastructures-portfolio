package projectCode20280;

/***
 * This class implements the double-ended queue (deque) ADT using my custom doubly 
 * linked list.
 */
public class LinkedDeque<E> implements Deque<E> {

	private DoublyLinkedList<E> list = new DoublyLinkedList<>(); //An empty list which will act as a deque
	
	/**
	 * Testing all methods for LinkedDeque
	 * */
	public static void main(String[] args) {
	    LinkedDeque<Integer> dq = new LinkedDeque<Integer>();
	    System.out.println("LinkedCircularQueue");
	    
    	for(int i=0; i<20; i++)
    	{
    		dq.addFirst(i);
    	}
    	System.out.println(dq + " size: " + dq.size());
    	
    	for(int i=0; i<5; i++)
    	{
    		dq.removeFirst();
    	}
    	
    	System.out.println("After dequeuing 5 elements:");
    	System.out.println(dq + " size: " + dq.size());
    	System.out.println("First: " + dq.first());
    	System.out.println("Last: " + dq.last());
    	System.out.println("is empty: " + dq.isEmpty());
    	
    	for(int i=20; i<25; i++)
    	{
    		dq.addLast(i);
    	}
    	System.out.println(dq + " size: " + dq.size());
    	
    	while(!dq.isEmpty())
    	{
    		dq.removeLast();
    	}
    	
    	System.out.println(dq);
    	System.out.println("is empty: " + dq.isEmpty());

	}

	/**Constructor for LinkedDeque. Relies on initially empty list*/
	public LinkedDeque() {};
	
	/**
	 * Returns the number of elements in the deque.
	 * @return number of elements in deque
	 * */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Checks whether the deque is empty.
	 * @return true if the deque is empty, false otherwise
	 * */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Method to return the first element of the deque but not remove (peek)
	 * @return front of deque
	 * */
	@Override
	public E first() {
		return list.first();
	}

	/**
	 * Method to return the last element of the deque
	 * @return rear of deque
	 * */
	@Override
	public E last() {
		return list.last();
	}

	/**
	 * Method to add to front of deque
	 * @param element to be added
	 * */
	@Override
	public void addFirst(E e) {
		list.addFirst(e);
	}

	/**
	 * Method to add to rear of deque
	 * @param element to be added
	 * */
	@Override
	public void addLast(E e) {
		list.addLast(e);
	}

	/**
	 * Method to remove from front of deque
	 * @return element that was removed
	 * */
	@Override
	public E removeFirst() {
		return list.removeFirst();
	}

	/**
	 * Method to remove from rear of deque
	 * @return element that was removed
	 * */
	@Override
	public E removeLast() {
		return list.removeLast();
	}
	
	/**toString method for LinkedDeque
	 * @return string*/
	@Override
	public String toString() 
	{
		String deque;
		deque = "[";
		if (size() > 0)
			deque += list.get(0);
		
		if (size() > 1)
		{
			for (int i = 1; i <= size() - 1; i++) 
			{
				deque += ", " + list.get(i);
			}
		}
		
		deque = deque + "]";
		return deque;
	}

}
