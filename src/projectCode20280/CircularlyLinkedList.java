package projectCode20280;

import java.util.Iterator;
/**This class implements the Circularly Linked List ADT.
 * It implements it by implementing the List interface and has a rotate method.
 * It is similar to the Singly Linked List but we need to remember that the head will only be null
 * when the list is empty.*/
public class CircularlyLinkedList<E> implements List<E> {

	/**This inner class defines a node object.
	 * Each node object has an element and another node object (the node it points to).*/
	private static class Node<E> {
		private E element; //Reference to the element stored at this node
		private Node<E> next; //Reference to the next node in the list

		/**Constructor to construct Node object*/
		public Node(E element, Node<E> node)
		{
			this.element = element;
			this.next = node;
		}

		/**Accessor method
		 * @return element*/
		public E getElement()
		{
			return element;
		}

		/**Accessor method
		 * @return next i.e. the node that the current one points to*/
		public Node<E> getNext()
		{
			return next;
		}

		/**Mutator method to set what the Node points to
		 * @param next i.e. the node that the current one points to*/
		public void setNext(Node<E> next)
		{
			this.next = next;
		}
		
		@Override
		public String toString()
		{
			return "" + element;
		}
	}
	
	/**Inner class which controls how it traverses the internal elements of the singly linked list*/
	private class ListIterator implements Iterator<E>
	{
		Node<E> curr;
		 private int nextNode; //This keeps track of how many nodes we have iterated through
		
		 /**Constructor to construct ListIterator object which will allow is to iterate through a list*/
		public ListIterator()
		{
			curr = tail.next; //This is the first element
			nextNode = 0; 
		}
		
		/**Method which checks if the node has a next
		 * @return boolean if nextNode is less than size*/
		public boolean hasNext()
		{
			return nextNode < size;
		}
		
		/**Method which gets node's element and changes curr to the next
		 * @return next node's element*/
		@Override
		public E next() 
		{
			E res = (E)curr.getElement();
			curr = curr.getNext();
			nextNode++;
			return res;
		}
	}
	
	private Node<E> tail = null; //Initialize tail to null
	private int size = 0; //Initialize size of circularly linked list to 0
	
	/**Variable used for LinkedCircularQueue*/
	private int cursor = 0; 

	/**Constructor to construct Circularly Linked List object*/
	public CircularlyLinkedList()
	{
		
	}
	
	/**Method used for LinkedCircularQueue to advance cursor*/
	public void advance()
	{
		if(size()>1)
		{
			cursor++;
		}
	}
	
	/**Method used for LinkedCircularQueue to decrement cursor*/
	public void decrementCursor()
	{
		if(size()>1)
		{
			cursor--;
		}
		
	}
	
	public int getCursor()
	{
		return cursor;
	}
	
	/**Method to get the size of the Circularly Linked List
	 * @return size of list*/
	@Override
	public int size() {
		return size;
	}

	/**Method to check if list is empty
	 *  @return boolean*/
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	/**Method to get the element at a certain index
	 * @param i index of element
	 * @return E the element at that index*/
	@Override
	public E get(int i) {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot get element as list is empty");
		}
		
		if(i<0 || i>=size)
		{
			return null;
		}
		
		if(i==0)
		{
			return tail.next.getElement();
		}
		
		Node<E> cur = tail.next;
		int counter = 0;
		
		/*Iterate through list until the index i is reached.
		 * Each time increment counter and set cur to cur.next.
		 * When counter reaches i then cur will be at the right element.*/
		while(counter != i)
		{
			cur = cur.next;
			counter++;
		}

		return cur.getElement();
		
	}

	/**Method to add an element to the list at a certain position
	 * @param i the index, e the element*/
	@Override
	public void add(int i, E e) {
		if(i<0)
		{
			System.out.println("Cannot add element at index less than 0");
		}
		
		if(i == 0)
		{
			addFirst(e);
		}		
		else if(i == (size()-1))
		{
			addLast(e);
		}
		else
		{		
			Node<E> cur = tail.next;
			Node<E> prev = tail;
			int counter = 0;
			
			/*Iterate through list until the index i is reached.
			 * Each time increment counter and set cur to cur.next.
			 * When counter reaches i then cur will be at the right element.*/
			while(counter != i)
			{
				prev = cur;
				cur = cur.next;
				counter++;		
			}
			
			//Set cur as the new node and have it point to cur in order to keep the old cur in the list
			prev.next = new Node<E>(e, prev.next); 
			size++; 
			
		}

	}

	/**Method to remove a node from the list at a certain position
	 * @param i the index
	 * @return the element that was removed*/
	@Override
	public E remove(int i) {
		if(isEmpty())
		{
			throw new RuntimeException("Cannot delete as list is empty");
		}
		
		if(i<0 || i>=size)
		{
			return null;
		}

		if(i == 0)
		{
			return removeFirst();
		}		
		else if(i==size-1)
		{
			return removeLast();
		}	
		else 
		{
			Node<E> cur = tail.next; //tail.next is the first element
			Node<E> prev = tail; //prev is tail as tail and first element are connected
			int counter = 0;
			Node<E> temp; 

			while(counter != i)
			{
				prev = cur;
				cur = cur.next;
				counter++;
			}

			temp = cur; //Set temp to be cur so that we can return it
			prev.next = cur.next; //Set prev to point to cur.next in order to remove cur
			size--;
			return temp.getElement();
		}
	}

	/**Method to remove a node from the start of list
	 * @return the element that was removed*/
	@Override
	public E removeFirst() {
		if(isEmpty())
		{
			return null;
		}
		
		Node<E> head = tail.next;
		
		if(head==tail) //Only one element in the list
		{
			tail = null;
		}
		else
		{
			tail.next = head.next; //Make the tail point to the element after head i.e. removing head 
		}
		
		size--;
		return head.getElement();
	}

	/**Method to remove a node from the end of list
	 * @return the element that was removed*/
	@Override
	public E removeLast() {
		if(isEmpty())
		{
			return null;
		}
		
		Node<E> head = tail.next;
		Node<E> cur = tail.next;
		
		if(cur==tail) //Only one element in the list
		{
			tail = null;
		}
		else
		{
			while(cur.getNext()!=tail)
			{	
				
				cur = cur.next;
			}
			tail = cur;
			cur.next = head;
			
		}
		
		size--;
		return tail.getElement();
	}
	
	/**Method to iterate through list
	 * @return iterator object*/
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	/**Method to add an element to the start of list
	 * @param e element to add*/
	@Override
	public void addFirst(E e) {
		if(size==0)
		{
			tail = new Node<E>(e, null); //Make the tail the new element
			tail.setNext(tail); //Make the tail point to itself as it needs to circulate
		}
		else
		{
			Node<E> newest = new Node<E>(e, tail.getNext());		
			tail.next = newest; //Add the first element to the start of the list i.e. after the tail	
		}

		size++; //Increment the size
	}

	/**Method to add an element to the end of list
	 * @param e element to add*/
	@Override
	public void addLast(E e) {
		addFirst(e); //Adds element e to the start of the list
		tail = tail.getNext(); //New element becomes the tail
	}

	/**Method to rotate tail of list i.e set tail to node after it*/
	public void rotate() {
		if(tail!=null)
		{
			tail = tail.getNext(); //Make the first element the tail of the list
		}		
	}
	
	/**Method to return the first element of the circularly linked list
	 * @return E first element*/
	public E first()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return tail.getNext().getElement(); //The first element is the one after the tail
		}
	}
	
	/**Method to return the last element of the circularly linked list
	 * @return E last element*/
	public E last()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return tail.getElement(); //The last element is the tail
		}
	}
	
	/**toString method for Circularly Linked List
	 * @return string*/
	@Override
	public String toString()
	{
		ListIterator i = new ListIterator();
		String list = "[";
		
		while(i.hasNext())
		{	
			list = list + i.curr.element + ", ";	
			i.next();
		}
		
		list = list + "]";
		
		return list;
	}
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		Integer[] arr = { 24, 13, 5, 31, 66, 99, 27, 52, 79, 91 };

		for (Integer i : arr) {
			ll.addFirst(i);
		}

		System.out.println("Testing addFirst method. Expected: [91, 79, 52, 27, 99, 66, 31, 5, 13, 24]. Actual: " + ll);
		
		System.out.println("Expected size: 8. Actual: " + ll.size());
		
		ll.removeFirst();
		System.out.println("Removing first element.");
		System.out.println("Expected: [79, 52, 27, 99, 66, 31, 5, 13, 24]. Actual: " + ll);
		
		ll.removeLast();
		System.out.println("Removing last element.");
		System.out.println("Expected: [79, 52, 27, 99, 66, 31, 5, 13]. Actual: " + ll);
		
		System.out.println("Expected size: 8. Actual: " + ll.size());
		
		ll.remove(4);
		System.out.println("Removing fourth element.");
		System.out.println("Expected: [79, 52, 27, 66, 31, 5, 13]. Actual: " + ll);
		
		System.out.println("Expected size: 7. Actual: " + ll.size());
		
		ll.remove(2);
		System.out.println("Removing second element.");
		System.out.println("Expected: [79, 52, 66, 31, 5, 13]. Actual: " + ll);
		
		System.out.println("Expected size: 6. Actual: " + ll.size());
		
		System.out.println("Adding 9999, 8888, 7777 to start of list.");
		ll.addFirst(9999);
		ll.addFirst(8888);
		ll.addFirst(7777);

		System.out.println("Expected: [7777, 8888, 9999, 79, 52, 66, 31, 5, 13]. Actual: " + ll);
		
		System.out.println("Expected size: 9. Actual: " + ll.size());
		
		System.out.println("Adding element 2 at position 3.");
		ll.add(3, 2);
		System.out.println("2 should now be between 9999 and 79. Actual: " + ll);


		System.out.println("Get element at index 0. Expected: 7777. Actual: " + ll.get(0));
		System.out.println("Get element at index 1. Expected: 8888. Actual: " + ll.get(1));
		System.out.println("Get element at index 2. Expected: 9999. Actual: " + ll.get(2));
		System.out.println("Get element at index 3. Expected: 2. Actual: " + ll.get(3));
		System.out.println("Expected size: 10. Actual: " + ll.size());

		System.out.println("Iterate through list, printing each value:");
		for (Integer e : ll) {
			System.out.println("value: " + e);
		}
	}
		
}
