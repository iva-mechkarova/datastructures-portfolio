package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

	private Node<E> head;
	private int size;

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
		//Check if the head is null and if it is then the list is empty
		return head == null;
	}

	@Override
	public E get(int i) {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot get element as list is empty");
		}
		
		if(i==1)
		{
			return head.getElement();
		}
		
		Node<E> cur = head;
		Node<E> prev = null;
		int counter = 1;

		while(counter != i && cur != null)
		{
			prev = cur;
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
		if(i == 1)
		{
			addFirst(e);
		}
		
		if(i == (size()-1))
		{
			addLast(e);
		}
		
		Node<E> cur = head;
		Node<E> prev = null;
		int counter = 1;
		Node<E> temp = head;
		
		while(counter != i && temp != null)
		{
			prev = cur;
			cur = cur.next;
			counter++;		
		}
		
		temp = cur;
		prev.next = new Node<E>(e, prev.next);
		size++;
	}

	@Override
	public E remove(int i) {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot delete as list is empty");
		}

		if(i == 1)
		{
			Node<E> temp = head;
			head = head.next;
			size--;
			return temp.getElement();
		}

		Node<E> cur = head;
		Node<E> prev = null;
		int counter = 1;
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

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
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

		return remove(1);
	}

	@Override
	public E removeLast() {
		if(isEmpty())
		{
			return null;
		}

		return remove((size() - 1));
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
		String list = "";
		Node<E> cur = head;
		
		while(cur!=null)
		{	
			list = list + cur.getElement();
			
			if(cur.getNext() != null)
			{
				list = list + ", ";
			}
			cur = cur.getNext();
			
		}
		
		return list;
	}

	
	public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());
		
		sll.add(5, "F");
		System.out.println(sll.toString());

		/*sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(3);
		System.out.println(sll.toString());*/
		
		/*for (String s : sll) {
			System.out.print(s + ", ");
		}*/
	}


}
