package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private static class Node<E> {
		private E element; //Reference to the element stored at this node
		private Node<E> next; //Reference to the next node in the list

		//Constructor
		public Node(E element, Node<E> node)
		{
			this.element = element;
			this.next = node;
		}

		//Accessors
		public E getElement()
		{
			return element;
		}

		public Node<E> getNext()
		{
			return next;
		}

		//Mutator Method
		public void setNext(Node<E> next)
		{
			this.next = next;
		}
	}
	
	//Inner class which controls how it traverses the internal elements of the singly linked list
	private class ListIterator implements Iterator<E>
	{
		Node<E> curr;
		 private int nextNode; //This keeps track of how many nodes we have iterated through
		
		public ListIterator()
		{
			curr = tail.next; //This is the first element
			nextNode = 0; 
		}
		
		public boolean hasNext()
		{
			return nextNode < size;
		}
		
		@Override
		public E next() 
		{
			E res = (E)curr.getElement();
			curr = curr.getNext();
			nextNode++;
			return res;
		}
	}
	
	private Node<E> tail = null; 
	private int size = 0;

	//Constructor
	public CircularlyLinkedList()
	{
		
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

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
		
		while(counter != i)
		{
			cur = cur.next;
			counter++;
		}

		return cur.getElement();
		
	}

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
			
			while(counter != i)
			{
				prev = cur;
				cur = cur.next;
				counter++;		
			}
			
			prev.next = new Node<E>(e, prev.next);
			size++;
			
		}

	}

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
			Node<E> cur = tail.next;
			Node<E> prev = tail;
			int counter = 0;
			Node<E> temp = tail.next;

			while(counter != i)
			{
				prev = cur;
				cur = cur.next;
				counter++;
			}

			temp = cur;
			prev.next = cur.next;
			size--;
			return temp.getElement();
		}
	}

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

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

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

	@Override
	public void addLast(E e) {
		addFirst(e); //Adds element e to the start of the list
		tail = tail.getNext(); //New element becomes the tail
	}

	public void rotate() {
		if(tail!=null)
		{
			tail = tail.getNext(); //Make the first element the tail of the list
		}		
	}
	
	//Method to return the first element of the circularly linked list
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
	
	//Method to return the last element of the circularly linked list
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
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}
		System.out.println(ll);
		
		ll.addFirst(10);
		System.out.println(ll);

		ll.removeFirst();
		System.out.println("Remove first " + ll);

		ll.removeLast();
		System.out.println("Remove last " + ll);
		System.out.println("tail: " + ll.tail.getElement());
		
		ll.removeLast();
		System.out.println(ll);
		System.out.println(ll.removeLast());
		
		System.out.println("Remove element @ index 2: " + ll.remove(2));
		System.out.println(ll);
		
		ll.add(4, 3);
		System.out.println(ll);

		ll.removeFirst();
		ll.rotate();
		System.out.println(ll);

		ll.removeLast();
		ll.rotate();
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}
		
		System.out.println("size: " + ll.size());
		System.out.println(ll.get(0));
		System.out.println(ll.get(1));
		System.out.println(ll.get(3));
		System.out.println(ll.get(ll.size-1));

	}
}
