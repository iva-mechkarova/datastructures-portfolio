package projectCode20280;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnsortedTableMapTest {

	@Test
	void testSize() {
		UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
		assertEquals(0, map.size());
		map.put(1, "one");
		map.put(2, "two");
		assertEquals(2, map.size());
	}

	@Test
	void testGet() {
		UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
		Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

		for(Integer i : arr) {
			map.put(i, Integer.toString(i));
		}
		assertEquals("15", map.get(15));
		assertEquals("24", map.get(24));
		assertEquals(null, map.get(-1));

	}

	@Test
	void testPut() {
		UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
		Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

		for(Integer i : arr) {
			map.put(i, Integer.toString(i));
		}
		
		assertEquals("[35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5]", map.keySet().toString());
	}

	@Test
	void testRemoveK() {
		UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
		Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

		for(Integer i : arr) {
			map.put(i, Integer.toString(i));
		}

		assertEquals(12, map.size());
		assertEquals("26", map.remove(26));
		assertEquals(11, map.size());

	}

}
