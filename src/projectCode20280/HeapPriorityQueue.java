package projectCode20280;

/*
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
	
	//Heap represented as an array list
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

	/**
	 * Creates an empty priority queue based on the natural ordering of its keys.
	 */
	public HeapPriorityQueue() {
		super();
	}

	/**
	 * Creates an empty priority queue using the given comparator to order keys.
	 * 
	 * @param comp comparator defining the order of keys in the priority queue
	 */
	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * Creates a priority queue initialized with the respective key-value pairs. The
	 * two arrays given will be paired element-by-element. They are presumed to have
	 * the same length. (If not, entries will be created only up to the length of
	 * the shorter of the arrays)
	 * 
	 * @param keys   an array of the initial keys for the priority queue
	 * @param values an array of the initial values for the priority queue
	 */
	public HeapPriorityQueue(K[] keys, V[] values) 
	{
		super();	
		for(int j=0; j<Math.min(keys.length, values.length); j++)
		{
			heap.add(new PQEntry<K,V>(keys[j], values[j]));
		}
			
		heapify(); //Construct bottom-up heap
	}

	// protected utilities
	protected int parent(int j) 
	{
		return (j - 1) / 2;
	}

	protected int left(int j) 
	{
		return 2 * j + 1;
	}

	protected int right(int j) 
	{
		return 2 * j + 2;
	}

	protected boolean hasLeft(int j) 
	{
		return left(j) < heap.size();
	}

	protected boolean hasRight(int j) 
	{
		return right(j) < heap.size();
	}

	/** Exchanges the entries at indices i and j of the array list. */
	protected void swap(int i, int j)
	{
		Entry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	/**
	 * Moves the entry at index j higher, if necessary, to restore the heap
	 * property.
	 */
	protected void upheap(int j)
	{
		//Loop until children of root is reached - no need to check root as it has no parent
		while(j>0)
		{
			int par = parent(j); //Find parent of the element we are upheaping
			if(compare(heap.get(j), heap.get(par))>=0)
				break; //Heap property verified
			swap(j,par);
			j=par; //Continue from parent's location
		}
	}

	/**
	 * Moves the entry at index j lower, if necessary, to restore the heap property.
	 */
	protected void downheap(int j)
	{
		//Loop to bottom of heap or break statement
		while(hasLeft(j))
		{
			int leftIndex = left(j);
			int smallChildIndex = leftIndex; //Initialize to left - we will check if right is smaller
			
			if(hasRight(j))
			{
				int rightIndex = right(j);
				if(compare(heap.get(leftIndex), heap.get(rightIndex))>0)
					smallChildIndex = rightIndex; //Small index is right child as it is smaller
			}
			
			if(compare(heap.get(smallChildIndex), heap.get(j)) >=0)
				break; //Heap property restored
			swap(j, smallChildIndex);
			j=smallChildIndex; //Continue from child's position
		}
	}

	/** Performs a bottom-up construction of the heap in linear time. */
	protected void heapify()
	{
		int start = parent(size()-1); //Start at parent of last entry
		
		//Loop until we reach the root
		for(int i = start; i>=0; i--)
		{
			downheap(i);
		}	
	}

	// public methods

	/**
	 * Returns the number of items in the priority queue.
	 * 
	 * @return number of items
	 */
	@Override
	public int size() {
		return heap.size();
	}

	/**
	 * Returns (but does not remove) an entry with minimal key.
	 * 
	 * @return entry having a minimal key (or null if empty)
	 */
	@Override
	public Entry<K, V> min()
	{
		//TODO
		return null;
	}

	/**
	 * Inserts a key-value pair and return the entry created.
	 * 
	 * @param key   the key of the new entry
	 * @param value the associated value of the new entry
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException if the key is unacceptable for this queue
	 */
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException
	{
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		heap.add(newest);
		upheap(heap.size()-1);
		return newest;
	}

	/**
	 * Removes and returns an entry with minimal key.
	 * 
	 * @return the removed entry (or null if empty)
	 */
	@Override
	public Entry<K, V> removeMin()
	{
		if(heap.isEmpty())
			return null;		
		Entry<K,V> answer = heap.get(0);
		swap(0, heap.size()-1);
		heap.remove(heap.size()-1);
		downheap(0);
		return answer;
	}

	/** Used for debugging purposes only */
	private void sanityCheck() {
		for (int j = 0; j < heap.size(); j++) {
			int left = left(j);
			int right = right(j);
			if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
				System.out.println("Invalid left child relationship");
			if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
				System.out.println("Invalid right child relationship");
		}
	}
	
	public static void main(String[] args)
	{
		LinkedList<Integer> arr = (LinkedList<Integer>) new Random().ints(0, 1000).distinct().limit(10).boxed().collect(Collectors.toCollection(LinkedList::new));
		Integer[] arr1 = arr.toArray(Integer[]::new); 
		HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<Integer, Integer>(arr1, arr1);
		pq.sanityCheck();
	}
}

