package projectCode20280;

import java.util.Iterator;
/**This class implements the Doubly Linked List ADT.
 * It implements it by implementing the List interface and has a header and trailer.
 * The header and trailer are actual nodes with null elements.*/
public class DoublyLinkedList<E> implements List<E> {
	
	private Node<E> header = null;
	private Node<E> trailer = null;
	private int size = 0;

	/**This inner class defines a node object.
	 * Each node object has an element and another 2 node objects 
	 * (the node it points to and the node before it).*/
	private class Node<E> {
		private E element; //Reference to the element stored at this node
		private Node<E> prev; //Reference to the previous node in the list
		private Node<E> next; //Reference to the next node in the list
		
		/**Constructor to construct Node object*/
		public Node(E e, Node<E> p, Node<E> n)
		{
			element = e;
			prev = p;
			next = n;
		}
		
		/**Mutator method to set what the Node points to
		 * @param n i.e. the node that the current one points to*/
		public void setNext(Node<E> n)
		{
			next = n;
		}
		
		/**Accessor method
		 * @return next i.e. the node that the current one points to*/
		public Node<E> getNext()
		{
			return next;
		}
		
		/**Accessor method
		 * @return element*/
		public E getElement()
		{
			return element;
		}
		
		/**Accessor method
		 * @return prev i.e. the previous node*/
		public Node<E> getPrev()
		{
			return prev;
		}
		
		/**Mutator method to set the previous node
		 * @param p i.e. the node before the current node*/
		public void setPrev(Node<E> p)
		{
			prev = p;
		}
		
	}
	
	/**Inner class which controls how it traverses the internal elements of the singly linked list*/
	private class ListIterator implements Iterator<E>
	{
		Node<E> curr;
		
		/**Constructor to construct ListIterator object which will allow is to iterate through a list*/
		public ListIterator()
		{
			curr = header;
		}
		
		/**Method which checks if the node has a next
		 * @return boolean if cur doesn't equal null*/
		public boolean hasNext()
		{
			return curr != null;
		}
		
		/**Method which gets node's element and changes curr to the next
		 * @return next node's element*/
		@Override
		public E next() 
		{
			E res = (E)curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}
	
	/**Constructor to construct Doubly Linked List object. 
	 * It will initially have header and trailer as elements.*/
	public DoublyLinkedList()
	{
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, null, null);
		header.setNext(trailer);
	}
	
	/**Method to add an element between two nodes
	 * @param e element to add, predecessor, successor*/
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		//Create and link a new node
		Node<E> newest = new Node<E>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	/**Method to get the size of the Doubly Linked List
	 * @return size of list*/
	@Override
	public int size() {
		return size;
	}

	/**Method to check if list is empty
	 *  @return boolean*/
	@Override
	public boolean isEmpty() {
		return header == trailer;
	}

	/**Method to get the element at a certain index
	 * @param i index of element
	 * @return E the element at that index*/
	@Override
	public E get(int i) {
		if (isEmpty())
		{
			return null;
		}
		
		if(i<0 || i>=size)
		{
			return null;
		}
		
		if(i==0)
		{
			return header.getNext().getElement();
		}
		
		i = i+1;
		Node<E> cur = header;
		int counter = 0;

		/*Iterate through list until the index i is reached (or cur is null i.e. end of list reached).
		 * Each time increment counter and set cur to cur.next.
		 * When counter reaches i then cur will be at the right element.*/
		while(counter != i && cur != null)
		{
			cur = cur.next;
			counter++;
		}

		if (cur == null)
		{
			return null;
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
			i += 1;
			Node<E> cur = header;
			Node<E> prev = null;
			int counter = 0;
			
			/*Iterate through list until the index i is reached (or cur.next is null i.e. end of list reached).
			 * We are using cur.next as when cur is null it could be at the trailer.
			 * Each time increment counter, set previous to current and set cur to cur.next.
			 * When counter reaches i then cur will be at the right element.*/
			while(counter!=i && cur.next !=null)
			{
				prev = cur;
				cur = cur.next;
				counter++;
				
			}
			
			addBetween(e, prev, cur);
			
		}
	}

	/**Method to remove a node from the list at a certain position
	 * @param i the index
	 * @return the element that was removed*/
	@Override
	public E remove(int i) {
		if(i<0 || i>=size)
		{
			return null;
		}
		
		if (isEmpty())
		{
			return null;
		}
		i += 1; //Add 1 as 0 is technically header but we don't want to remove this
		int counter = 0;
		Node<E> cur = header;
		Node<E> prev = null;
		Node<E> temp;
		
		/*Iterate through list until the index i is reached (or cur.next is null i.e. end of list reached).
		 * We are using cur.next as when cur is null it could be at the trailer.
		 * Each time increment counter, set previous to current and set cur to cur.next.
		 * When counter reaches i then cur will be at the right element.*/
		while(counter!= i && cur.next!=null)
		{
			prev = cur;
			cur = cur.next;
			counter++;
		}
		
		if (cur == null)
		{
			return null;
		}
		
		temp = cur; //Set temp to be cur so that we can return it
		prev.next = cur.next;
		size--;
		return temp.getElement();	
		
	}

	/**Method to iterate through list
	 * @return iterator object*/
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	/**Method to remove a node from the start of list
	 * @return the element that was removed*/
	@Override
	public E removeFirst() {
		if (isEmpty())
		{
			return null;
		}
		
		return remove(0);
	}

	/**Method to remove a node from the end of list
	 * @return the element that was removed*/
	@Override
	public E removeLast() {
		if (isEmpty())
		{
			return null;
		}
		
		return remove(size-1);
	}
	
	/**Method to add an element to the start of list
	 * @param e element to add*/
	@Override
	public void addFirst(E e) {
		addBetween(e, header, header.getNext()); 	
	}

	/**Method to add an element to the end of list
	 * @param e element to add*/
	@Override
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer); 	
		
	}
	
	/**toString method for Doubly Linked List
	 * @return string*/
	@Override
	public String toString()
	{
		ListIterator i = new ListIterator();
		String list = "[";
		
		while(i.curr!=null)
		{	
			if (i.curr.element != null)
				list = list + i.curr.element;
			
			if((i.curr.getNext() != null) && (i.curr.element != null))
			{
				list = list + ", ";
			}
			i.curr = i.curr.getNext();		
		}
		list = list + "]";
		
		return list;
	}
	
	public static void main(String[] args) {
		   DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
           ll.addFirst(0);
           ll.addFirst(1);
           ll.addFirst(2);
           ll.addLast(-1);
           System.out.println(ll);
           ll.add(2, 7);
           System.out.println(ll);
           System.out.println(ll.get(4));
           
           ll.removeFirst();
           System.out.println(ll);

           ll.removeLast();
           System.out.println(ll);
           
           ll.remove(2);
           System.out.println(ll);
           
           for(Integer e: ll) {
                   System.out.println("value: " + e);
           }
	}

	
}
