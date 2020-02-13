package projectCode20280;

import java.util.Iterator;


public class DoublyLinkedList<E> implements List<E> {
	
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	private class Node<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n)
		{
			element = e;
			prev = p;
			next = n;
		}
		
		public void setNext(Node<E> n)
		{
			next = n;
		}
		
		public Node<E> getNext()
		{
			return next;
		}
		
		public E getElement()
		{
			return element;
		}
		
		public Node<E> getPrev()
		{
			return prev;
		}
		
		public void setPrev(Node<E> p)
		{
			prev = p;
		}
		
	}
	
	//Inner class which controls how it traverses the internal elements of the singly linked list
	private class ListIterator implements Iterator<E>
	{
		Node<E> curr;
		
		public ListIterator()
		{
			curr = header;
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
	
	public DoublyLinkedList()
	{
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, null, null);
		header.setNext(trailer);
	}
	
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		//Create and link a new node
		Node<E> newest = new Node<E>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return header == trailer;
	}

	@Override
	public E get(int i) {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot get element as list is empty");
		}
		
		if(i==0)
		{
			return header.getNext().getElement();
		}
		
		i = i+1;
		Node<E> cur = header;
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
			
			while(counter!=i && cur.next !=null)
			{
				prev = cur;
				cur = cur.next;
				counter++;
				
			}
			
			addBetween(e, prev, cur);
			
		}
	}

	@Override
	public E remove(int i) {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot delete as list is empty");
		}
		i += 1;
		int counter = 0;
		Node<E> cur = header;
		Node<E> prev = null;
		Node<E> temp = header;
		
		while(counter!= i && temp!=null)
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
		return new ListIterator();
	}



	@Override
	public E removeFirst() {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot delete as list is empty");
		}
		
		return remove(0);
	}

	@Override
	public E removeLast() {
		if (isEmpty())
		{
			throw new RuntimeException("Cannot delete as list is empty");
		}
		
		return remove(size-1);
	}
	

	@Override
	public void addFirst(E e) {
		addBetween(e, header, header.getNext()); 	
	}

	@Override
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer); 	
		
	}
	
	@Override
	public String toString()
	{
		ListIterator i = new ListIterator();
		String list = "";
		
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
		
		return list;
	}
	
	public static void main(String[] args) {
		   DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
           ll.addFirst(0);
           ll.addFirst(1);
           ll.addFirst(2);
           ll.addLast(-1);
           System.out.println(ll);
           ll.add(0, 7);
           System.out.println(ll);
           System.out.println(ll.get(0));
           
           ll.removeFirst();
           System.out.println(ll);

           ll.removeLast();
           System.out.println(ll);
           
           ll.remove(0);
           System.out.println(ll);
           
           for(Integer e: ll) {
                   System.out.println("value: " + e);
           }
	}

	
}
