package projectCode20280;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;

/**Assignment 1. Implementation of PQ Sort with unsorted linked list VS with heap using my custom
 * SinglyLinkedList and HeapPriorityQueue.*/
public class PQSort 
{
	public PQSort()
	{
		
	}
	
	/*Method which ensures that an array is sorted*/
	public static boolean isSorted(Integer[] array, int length) 
	{
	    if (array == null || length < 2) 
	        return true; 
	    if (array[length - 2].compareTo(array[length - 1]) > 0)
	        return false;
	    return isSorted(array, length - 1);
	}
	
	public static void PQSort_unsortedLinkedList()
	{
		System.out.println("PQSort Unsorted Linked List:");
		int n = 100; //Initialize n (size of the unsorted list that we are sorting) to 100
		
		/*Sort lists with random distinct elements by using the PQSort with unsorted linked list
		  Do this for lists with size less than 1000 starting from 100 and increasing at a rate of 1.1*/
		while(n<1000)
		{
			/*Fill linked list with n random distinct elements between 0 and 1000*/
			LinkedList<Integer> arr = (LinkedList<Integer>) new Random().ints(0, 1000).distinct().limit(n).boxed().collect(Collectors.toCollection(LinkedList::new));
			
			long startTime = System.nanoTime(); //Set start time to current system time - this will help us calculate the time it takes for each sort
			SinglyLinkedList<Integer> ul = new SinglyLinkedList<Integer>(); //Use custom singly linked list class as unsorted linked list
			
			/*While there are elements left in the unsorted list remove them and add them to the end of the pq*/
			while(!arr.isEmpty())
			{
				ul.addLast(arr.removeFirst());
			}
			
			/*While there are elements in the pq remove the min and add it to the end of the list that we are sorting.
			 This will sort elements in a descending order.*/
			while(!ul.isEmpty())
			{
				arr.addLast(SinglyLinkedList.removeMin(ul));
			}
			
			long endTime = System.nanoTime(); //Set end time to current system time - this will help us calculate the time it took to sort
			long elapsedTime = endTime - startTime; //end time - start time is the time it took to sort
			
			/*Ensure that the previously unsorted list is now sorted*/
			boolean isSorted = isSorted(arr.toArray(new Integer[arr.size()]), arr.toArray(new Integer[arr.size()]).length);
			
			System.out.println(n + ", " + elapsedTime + ", " + isSorted);
			
			n = (int)(n*1.1); //Increase n by a factor of 1.1
		}
	}
	
	public static void PQSort_heap()
	{
		System.out.println("PQSort Heap:");
		int n = 100; //Initialize n (size of the unsorted list that we are sorting) to 100
		
		/*Sort lists with random distinct elements by using the PQSort with heap
		  Do this for lists with size less than 1000 starting from 100 and increasing at a rate of 1.1*/
		while(n<1000)
		{
			/*Fill linked list with n random distinct elements between 0 and 1000*/
			LinkedList<Integer> arr = (LinkedList<Integer>) new Random().ints(0, 1000).distinct().limit(n).boxed().collect(Collectors.toCollection(LinkedList::new));
			
			long startTime = System.nanoTime(); //Set start time to current system time - this will help us calculate the time it takes for each sort
			HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<Integer, Integer>(); //Use custom heap priority queue class to construct a heap with Integer keys and values
			
			/*While there are elements left in the unsorted list remove them and add them to the heap - they will automatically be sorted*/
			while(!arr.isEmpty())
			{
				Integer val = arr.removeFirst();
				pq.insert(val, val);
			}
			
			/*More efficient way to construct heap than using insert*/
			//Integer[] arr1 = arr.toArray(Integer[]::new); //Change arr into an array of Integer
			
			//<Integer, Integer> pq = new HeapPriorityQueue<Integer, Integer>(arr1, arr1);
			
			/*While there are elements in the pq remove them and add them to the end of the list that we are sorting.
			 This will sort elements in a descending order.*/
			while(!pq.isEmpty())
			{
				arr.addLast(pq.removeMin().getKey()); //Can add key OR value as they are the same
			}
			
			long endTime = System.nanoTime(); //Set end time to current system time - this will help us calculate the time it took to sort
			long elapsedTime = endTime - startTime; //end time - start time is the time it took to sort
			/*Ensure that the previously unsorted list is now sorted*/
			boolean isSorted = isSorted(arr.toArray(new Integer[arr.size()]), arr.toArray(new Integer[arr.size()]).length);
			
			System.out.println(n + ", " + elapsedTime + ", " + isSorted);
			
			n = (int)(n*1.1); //Increase n by a factor of 1.1
		}
	}
	
	public static void main(String[] args)
	{
		PQSort_unsortedLinkedList();
		//PQSort_heap();
	}

}
