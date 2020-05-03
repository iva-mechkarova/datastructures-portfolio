package projectCode20280;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedDequeTest {

	@Test
	void testSize() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 10; ++i)
			s.addFirst(i);
		assertEquals(10, s.size());
	}

	@Test
	void testIsEmpty() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 10; ++i)
			s.addFirst(i);
		for(int i = 0; i < 10; ++i)
			s.removeFirst();
		assertEquals(true, s.isEmpty());
	}
	
	@Test
	void testAddFirst()	{
		LinkedDeque<Integer> dq = new LinkedDeque<>();
    	for(int i=0; i<5; i++)
    		dq.addFirst(i);   	
		
    	assertEquals("[4, 3, 2, 1, 0]", dq.toString());
	}
	
	@Test
	void testAddLast() {
		LinkedDeque<Integer> dq = new LinkedDeque<>();
    	for(int i=0; i<5; i++)
    		dq.addLast(i);   	
		
    	assertEquals("[0, 1, 2, 3, 4]", dq.toString());
	}

	@Test
	void testFirst() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 10; ++i)
			s.addFirst(i);
		assertEquals(9, s.first());
	}
	
	@Test
	void testLast() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 10; ++i)
			s.addFirst(i);
		assertEquals(0, s.last());
	}
	
	@Test
	void testRemoveFirst() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 5; ++i)
			s.addFirst(i);
		assertEquals(4, s.removeFirst());
		assertEquals("[3, 2, 1, 0]", s.toString());
	}
	
	@Test
	void testRemoveLast() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 5; ++i)
			s.addFirst(i);
		assertEquals(0, s.removeLast());
		assertEquals("[4, 3, 2, 1]", s.toString());
	}
	
	@Test
	void testToString() {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 10; ++i)
			s.addLast(i);
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", s.toString());
	}

}
