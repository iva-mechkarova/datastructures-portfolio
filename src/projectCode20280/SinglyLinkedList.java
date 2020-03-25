package projectCode20280;

import java.util.Iterator;
import java.util.LinkedList;

public class SinglyLinkedList<E> implements List<E>, Iterable<E> {

	private Node<E> head = null;
	private int size = 0;

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
		
		public ListIterator()
		{
			curr = head;
		}
		
		public boolean hasNext()
		{
			return curr != null;
		}
		
		@Override
		public E next() 
		{
			E res = (E)curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}
	
	@Override
	public boolean isEmpty() {
		//Check if the head is null and if it is then the list is empty
		return head == null;
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
			return head.getElement();
		}
		
		Node<E> cur = head;
		int counter = 0;

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
			Node<E> prev = null;
			int counter = 0;
			
			while(counter != i && cur.next!=null)
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

			while(counter != i && temp != null)
			{
				prev = cur;
				cur = cur.next;
				counter++;
			}

			if (cur == null)
			{
				throw new RuntimeException("Cannot delete");
			}

			temp = cur;
			prev.next = cur.next;
			size--;
			return temp.getElement();			
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public int size() {
		return size; //Size is incremented/decremented when elements are added/removed so we just need to return it
	}	
	

	@Override
	public E removeFirst() {
		if(isEmpty())
		{
			return null;
		}

		return remove(0);
	}

	@Override
	public E removeLast() {
		if(isEmpty())
		{
			return null;
		}

		return remove((size()-1));
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
    public void reverse(Node<E> n) { 
    	if(n.getNext()==null)
    	{
    		System.out.print(n.element + " ");
    	}
    	else
    	{
    		reverse(n.getNext());
    		System.out.print(n.element + " ");
    	}
    	

    }
    
    /*Method which removes the min element of a SinglyLinkedList - needed for Assignment 1 PQSort*/
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
		System.out.println(ll.get(0));
		System.out.println(ll.get(1));
		System.out.println(ll.get(2));
		System.out.println(ll.get(3));
		System.out.println(ll);
		System.out.println(ll.size());
		
		ll.reverse(ll.head);
		
	}


}
