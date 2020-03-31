package projectCode20280;

import java.util.Iterator;
import java.util.LinkedList;

/**This class implements the Singly Linked List ADT.
 * It implements it by implementing the List interface and creating each method that the ADT would have
 * e.g. add, remove, get. I have also implemented an inner node class.*/
public class SinglyLinkedList<E> implements List<E>, Iterable<E> {

	private Node<E> head = null;
	private int size = 0;

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
	}
	
	/**Inner class which controls how it traverses the internal elements of the singly linked list*/
	private class ListIterator implements Iterator<E>
	{
		Node<E> curr;
		
		/**Constructor to construct ListIterator object which will allow is to iterate through a list*/
		public ListIterator()
		{
			curr = head;
		}
		
		/**Method which checks if the node has a next
		 * @return boolean if curr doesn't equal null*/
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
	
	/**Method to check if list is empty
	 *  @return boolean*/
	@Override
	public boolean isEmpty() {
		//Check if the head is null and if it is then the list is empty
		return head == null;
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
			return head.getElement();
		}
		
		Node<E> cur = head;
		int counter = 0;

		/*Iterate through list until the index i is reached or cur is null.
		 * Each time increment counter and set cur to cur.next.
		 * When counter reaches i then cur will be at the right element.*/
		while(counter != i && cur != null)
		{
			cur = cur.next;
			counter++;
		}

		if (cur == null)
		{
			throw new RuntimeException("Cannot get element");
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
			Node<E> cur = head;
			int counter = 0;
			
			/*Iterate through list until the index i is reached or cur.next is null.
			 * Each time increment counter and set cur to cur.next.
			 * When counter reaches i then cur will be at the right element.*/
			while(counter != i && cur.next!=null)
			{
				cur = cur.next;
				counter++;		
			}
			
			//Set cur as the new node and have it point to cur in order to keep the old cur in the list
			cur = new Node<E>(e, cur); 
			size++;
			
		}
	}

	/**Method to remove a node from the list at a certain position
	 * @param i the index
	 * @return the element that was removed*/
	@Override
	public E remove(int i) {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot delete as list is empty");
		}
		
		if(i<0 || i>=size)
		{
			return null;
		}

		if(i == 0)
		{
			Node<E> temp = head;
			head = head.next;
			size--;
			return temp.getElement();
		}
		else 
		{
			Node<E> cur = head;
			Node<E> prev = null;
			int counter = 0;
			Node<E> temp = head;

			while(counter != i && cur.next != null)
			{
				prev = cur;
				cur = cur.next;
				counter++;
			}

			if (cur == null)
			{
				throw new RuntimeException("Cannot delete");
			}

			temp = cur; //Set temp to be cur so that we can return it
			prev.next = cur.next; //Set prev to point to cur.next in order to remove cur
			size--;
			return temp.getElement();			
		}
	}

	/**Method to iterate through list
	 * @return iterator object*/
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	/**Method to get the size of the Singly Linked List
	 * @return size of list*/
	@Override
	public int size() {
		return size; //Size is incremented/decremented when elements are added/removed so we just need to return it
	}	
	

	/**Method to remove a node from the start of list
	 * @return the element that was removed*/
	@Override
	public E removeFirst() {
		if(isEmpty())
		{
			return null;
		}

		return remove(0);
	}

	/**Method to remove a node from the end of list
	 * @return the element that was removed*/
	@Override
	public E removeLast() {
		if(isEmpty())
		{
			return null;
		}

		return remove((size()-1));
	}

	/**Method to add an element to the start of list
	 * @param e element to add*/
	@Override
	public void addFirst(E e) {
		head = new Node<E>(e, head); //Set the head to the element we wish to add
		size++; //Increment size by 1 as we added an element
	}

	/**Method to add an element to the end of list
	 * @param e element to add*/
	@Override
	public void addLast(E e) {
		Node<E> newest = new Node<E>(e, null); //Stores element to be added
		Node<E> last = head; //Set last to head

		//If the list is empty then we just need to set the head to the element we wish to add
		if(last == null)
		{
			head = newest;
		}
		else
		{
			while(last.getNext() != null) //Keep checking if each node is null to find the end of the list
			{
				last = last.getNext();
			}
			last.setNext(newest); //When last.getNext() is null we will set this element as the one we wish to add
		}
		size++; //Increment the size by 1 as we have added an element
	}

	/**toString method for Singly Linked List
	 * @return string*/
	@Override
	public String toString()
	{
		ListIterator i = new ListIterator();
		String list = "[";
		
		while(i.curr!=null)
		{	
			list = list + i.curr.element;
			
			if(i.curr.getNext() != null)
			{
				list = list + ", ";
			}
			i.curr = i.curr.getNext();
			
		}
		
		list = list + "]";
		
		return list;
	}
	
	//Recursion Practical Q5b
    public void reverseRecursive(Node<E> n) { 
    	if(n.getNext()==null)
    	{
    		System.out.print(n.element + " ");
    	}
    	else
    	{
    		reverseRecursive(n.getNext());
    		System.out.print(n.element + " ");
    	}
    }
    
    /**
     * Method to reverse SinglyLinkedList using ArrayStack
     * */
    public void reverse()
    {
    	ArrayStack<E> as = new ArrayStack<E>();
    	
    	for(int i=0; i<size()-1; i++)
    	{
    		as.push(removeLast());
    	}
    	
    	while(!as.isEmpty())
    	{
    		addFirst(as.pop());
    	}
    	
    }
    
    /**Method which removes the min element of a SinglyLinkedList - needed for Assignment 1 PQSort*/
	public static Integer removeMin(SinglyLinkedList<Integer> ll)
	{
		int min_idx = 0;
		Integer min_value = ll.get(min_idx);
		
		for(int i=1; i<ll.size(); ++i)
		{
			Integer cur = ll.get(i);
			
			if(cur<min_value)
			{
				min_value = cur;
				min_idx = i;
			}
		}
		
		ll.remove(min_idx);
		return min_value;
	}

	
	public static void main(String[] args) {
		/*String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());
		
		sll.add(5, "F");
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(3);
		System.out.println(sll.toString());
		
		for (String s : sll) {
			System.out.print(s + ", ");
		}*/
		
		SinglyLinkedList <Integer> ll =new SinglyLinkedList <Integer >();
		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(2);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);
		System.out.println(ll);
		ll.add(3, 2);
		System.out.println(ll);
		
		ll.removeFirst();
		ll.removeLast();
		System.out.println(ll);
		
		ll.remove(2);
		System.out.println(ll);
		
		ll.removeFirst();
		System.out.println(ll);
		
		ll.removeLast();
		System.out.println(ll);
		
		ll.addFirst(9999);
		ll.addFirst(8888);
		ll.addFirst(7777);
		
		System.out.println(ll);
		
		ll.reverse();
		System.out.println("Reversed: " + ll);
		System.out.println(ll.get(0));
		System.out.println(ll.get(1));
		System.out.println(ll.get(2));
		System.out.println(ll.get(3));
		System.out.println(ll);
		System.out.println(ll.size());
		
		System.out.println("Print in reverse: ");
		ll.reverseRecursive(ll.head);
		
	}


}
