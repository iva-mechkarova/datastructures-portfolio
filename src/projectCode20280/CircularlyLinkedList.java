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
	
	private Node<E> tail = null; 
	private int size = 0;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int i, E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public E remove(int i) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E e) {
		if(size==0)
		{
			tail = new Node<E>(e, null); //Make the tail the new element
			tail.setNext(tail); //Make the tail point to itself as it needs to circulate
		}
		
		Node<E> newest = new Node<E>(e, tail.getNext());
		
		tail.next = newest; //Add the first element to the start of the list i.e. after the tail
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
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();

		ll.rotate();
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

	}
}
