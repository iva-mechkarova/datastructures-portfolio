package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

	protected Node<E> head;
	protected int size;

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
	
	@Override
	public boolean isEmpty() {
		//Check if the head is empty
		if(head == null)
		{
			return true; //If the head is empty then the list is empty
		}
		return false;
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
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}	
	

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E e) {
		head = new Node<E>(e, head); //Set the head to the element we wish to add
		size++; //Increment size by 1 as we added an element
	}

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
	
	public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());
		
		for (String s : sll) {
			System.out.print(s + ", ");
		}
	}


}
