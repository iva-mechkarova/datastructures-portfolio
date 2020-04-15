package projectCode20280;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * An implementation of a sorted map using a binary search tree.
 */

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

	// We reuse the LinkedBinaryTree class. A limitation here is that we only use the key.
	protected LinkedBinaryTree<Entry<K, V>> tree = new LinkedBinaryTree<Entry<K,V>>();

	/** Constructs an empty map using the natural ordering of keys. */
	public TreeMap() {
		super(); // the AbstractSortedMap constructor
		tree.addRoot(null); // create a sentinel leaf as root
	}

	/**
	 * Returns the number of entries in the map.
	 * 
	 * @return number of entries in the map
	 */
	@Override
	public int size() {
		return (tree.size() - 1) / 2; // only internal nodes have entries
	}

	/** Utility used when inserting a new entry at a leaf of the tree */
	private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
		tree.set(p, entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}

	// Some notational shorthands for brevity (yet not efficiency)
	protected Position<Entry<K, V>> root() {
		return tree.root();
	}

	protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
		return tree.parent(p);
	}

	protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
		return tree.left(p);
	}

	protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
		return tree.right(p);
	}

	protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
		return tree.sibling(p);
	}

	protected boolean isRoot(Position<Entry<K, V>> p) {
		return tree.isRoot(p);
	}

	protected boolean isExternal(Position<Entry<K, V>> p) {
		return tree.isExternal(p);
	}

	protected boolean isInternal(Position<Entry<K, V>> p) {
		return tree.isInternal(p);
	}

	protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
		tree.set(p, e);
	}

	protected Entry<K, V> remove(Position<Entry<K, V>> p) {
		return tree.remove(p);
	}

	/**
	 * Returns the position in p's subtree having the given key (or else the
	 * terminal leaf).
	 * 
	 * @param key a target key
	 * @param p   a position of the tree serving as root of a subtree
	 * @return Position holding key, or last node reached during search
	 */
	private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key) {
		if(isExternal(p))
		{
			return p; //Key is not found
		}
		
		int c = compare(key, p.getElement());
		
		if(c==0)
		{
			return p; //Key is found
		}
		else if(c<0)
		{
			return treeSearch(left(p),key); //Recurse on left subtree
		}
		else
		{
			return treeSearch(right(p),key); //Recurse on right subtree
		}
	}

	/**
	 * Returns position with the minimal key in the subtree rooted at Position p.
	 * 
	 * @param p a Position of the tree serving as root of a subtree
	 * @return Position with minimal key in subtree
	 */
	protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
		Position<Entry<K,V>> curr = p;
		
		while(isInternal(curr))
		{
			curr = left(curr);
		}
		
		return parent(curr);
	}

	/**
	 * Returns the position with the maximum key in the subtree rooted at p.
	 * 
	 * @param p a Position of the tree serving as root of a subtree
	 * @return Position with maximum key in subtree
	 */
	protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
		Position<Entry<K,V>> curr = p;
		
		while(isInternal(curr))
		{
			curr = right(curr);
		}
		
		return parent(curr);
	}

	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if(isEmpty())
			return null;
		
		Position<Entry<K,V>> pos = treeSearch(root(), key);
		return pos.getElement().getValue();
	}

	/**
	 * Associates the given value with the given key. If an entry with the key was
	 * already in the map, this replaced the previous value with the new one and
	 * returns the old value. Otherwise, a new entry is added and null is returned.
	 * 
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with the key (or null, if no such
	 *         entry)
	 */
	@Override
	public V put(K key, V value) throws IllegalArgumentException {
		Entry<K,V> entry = new MapEntry<>(key, value);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		
		if(isExternal(p))
		{
			expandExternal(p, entry);
			return null;
		}
		else
		{
			V old = p.getElement().getValue();
			set(p, entry);
			return old;
		}
	}

	/**
	 * Removes the entry with the specified key, if present, and returns its
	 * associated value. Otherwise does nothing and returns null.
	 * 
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) throws IllegalArgumentException {
		Position<Entry<K,V>> p = treeSearch(root(), key);
		
		if(isExternal(p))
		{
			return null;
		}
		else
		{
			V old = p.getElement().getValue();
			if(isInternal(left(p)) && isInternal(right(p)))
			{
				Position<Entry<K,V>> r = treeMax(left(p));
				set(p, r.getElement());
				p = r;
			}
			
			Position<Entry<K,V>> leaf = isExternal(left(p)) ? left(p) : right(p);
			remove(leaf);
			remove(p);
			return old;
		}
	}

	// additional behaviors of the SortedMap interface
	/**
	 * Returns the entry having the least key (or null if map is empty).
	 * 
	 * @return entry with least key (or null if map is empty)
	 */
	@Override
	public Entry<K, V> firstEntry() {
		if (isEmpty())
			return null;
		return treeMin(root()).getElement();
	}

	/**
	 * Returns the entry having the greatest key (or null if map is empty).
	 * 
	 * @return entry with greatest key (or null if map is empty)
	 */
	@Override
	public Entry<K, V> lastEntry() {
		if (isEmpty())
			return null;
		return treeMax(root()).getElement();
	}

	/**
	 * Returns the entry with least key greater than or equal to given key (or null
	 * if no such key exists).
	 * 
	 * @return entry with least key greater than or equal to given (or null if no
	 *         such entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		// TODO
		return null;
	}

	/**
	 * Returns the entry with greatest key less than or equal to given key (or null
	 * if no such key exists).
	 * 
	 * @return entry with greatest key less than or equal to given (or null if no
	 *         such entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		// TODO
		return null;
	}

	/**
	 * Returns the entry with greatest key strictly less than given key (or null if
	 * no such key exists).
	 * 
	 * @return entry with greatest key strictly less than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
		// TODO
		return null;
	}

	/**
	 * Returns the entry with least key strictly greater than given key (or null if
	 * no such key exists).
	 * 
	 * @return entry with least key strictly greater than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		// TODO
		return null;
	}

	// Support for iteration
	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
		
		for(Position<Entry<K,V>> p : tree.inorder())
		{
			if(isInternal(p))
			{
				buffer.add(p.getElement());
			}		
		}
		
		return buffer;
	}

	/**
	 * Returns an iterable containing all entries with keys in the range from
	 * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
	 * 
	 * @return iterable with keys in desired range
	 * @throws IllegalArgumentException if <code>fromKey</code> or
	 *                                  <code>toKey</code> is not compatible with
	 *                                  the map
	 */
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
		// TODO
		return null;
	}

	// remainder of class is for debug purposes only
	/** Prints textual representation of tree structure (for debug purpose only). */
	protected void dump() {
		dumpRecurse(root(), 0);
	}

	/** This exists for debugging only */
	private void dumpRecurse(Position<Entry<K, V>> p, int depth) {
		String indent = (depth == 0 ? "" : String.format("%" + (2 * depth) + "s", ""));
		if (isExternal(p))
			System.out.println(indent + "leaf");
		else {
			System.out.println(indent + p.getElement());
			dumpRecurse(left(p), depth + 1);
			dumpRecurse(right(p), depth + 1);
		}
	}
	
	@Override
	public String toString()
	{
		return tree.toString();
	}

	public static void main(String[] args) {
		/*TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		
		treeMap.put(0, 0);
		treeMap.put(-1, 0);
		treeMap.put(1, 0);
		System.out.println(treeMap);
		
		BinaryTreePrinter<Entry<Integer, Integer>> btp = new BinaryTreePrinter<>(treeMap.tree);
		System.out.println(btp.print());
		
		Random rnd = new Random();
		int n = 5;
		java.util.List<Integer> rands = rnd.ints(1, 1000).limit(n).distinct().boxed().collect(Collectors.toList());

		for(Integer i : rands) {
			treeMap.put(i, i);
		}
		
		System.out.println("tree entries: " + treeMap.entrySet());
		
		treeMap.remove(rands.get(1));

		System.out.println("tree entries after removal: " + treeMap.entrySet());*/
		
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        BinaryTreePrinter<Entry<Integer, Integer>> btp1 = new BinaryTreePrinter<>( treeMap.tree );
        Integer[] arr = {44, 17, 88, 8, 32, 65, 97, 28, 54, 82, 93, 21, 29, 76, 80};
        for ( Integer i : arr )
        {
            treeMap.put( i, i );
        }
        System.out.println( "Size should now be 15. Actual: " + treeMap.size() );
        System.out.println( "Map entries: " + treeMap );
        System.out.println( btp1.print() );

        System.out.println( "The first entry of the map should be <8, 8>. Actual: <" + treeMap.firstEntry().getKey() + ", " + treeMap.firstEntry().getValue() + ">." );
        System.out.println( "The last entry of the map should be <97, 97>. Actual: <" + treeMap.lastEntry().getKey() + ", " + treeMap.lastEntry().getValue() + ">." );

        System.out.println();
        System.out.println( "Attempting to remove element 8 from the tree map." );
        treeMap.remove( 8 );
        System.out.println( "Size should now be 14. Actual: " + treeMap.size() );
        System.out.println( "First entry should now be <17, 17>. Actual: <" + treeMap.firstEntry().getKey() + ", " + treeMap.firstEntry().getValue() + ">." );
        System.out.println( "Map entries: " + treeMap );
        System.out.println( btp1.print() );

        System.out.println();
        System.out.println( "Attempting to remove <21, 21>, <88, 88>, and <97, 97> from the map." );
        treeMap.remove( 21 );
        treeMap.remove( 88 );
        treeMap.remove( 97 );
        System.out.println( "Size should now be 11. Actual: " + treeMap.size() );
        System.out.println( "Last entry should now be <93, 93>. Actual: <" + treeMap.lastEntry().getKey() + ", " + treeMap.lastEntry().getValue() + ">." );
        System.out.println( "Map entries: " + treeMap );
        System.out.println( "82 should now be the right child of 44, and 76 should be the right child of 65." );
        System.out.println( "93 should be the right child of 82." );
        System.out.println( btp1.print() );

        System.out.println( "Attempting to put entry <37, 37> in the map." );
        treeMap.put( 37, 37 );
        System.out.println( "Size should now be 12. Actual: " + treeMap.size() );
        System.out.println( "37 should now be the right child of 32." );
        System.out.println( btp1.print() );

        System.out.println( "Putting <15, 15>, <22, 22>, and <70, 70> in the map." );
        treeMap.put( 15, 15 );
        treeMap.put( 22, 22 );
        treeMap.put( 70, 70 );
        System.out.println( "Size should now be 15. Actual: " + treeMap.size() );
        System.out.println( "15 should be the left child of 17." );
        System.out.println( "22 should be the left child of 28." );
        System.out.println( "70 should be the left child of 76" );
        System.out.println( btp1.print() );

        System.out.println( "Attempting to change the value of entry <32, 32> to 33." );
        treeMap.put( 32, 33 );
        System.out.println( "Value of key 32 should now be 33. Actual: " + treeMap.get( 32 ) );
        System.out.println( "Size should remain 15. Actual: " + treeMap.size() );
        System.out.println();
        System.out.println("Attempting to change value of <44, 44> to 0.");
        treeMap.put( 44, 0 );
        System.out.println( "Value of key 44 should now be 0. Actual: " + treeMap.get( 44 ) );
        System.out.println( "Size should remain 15. Actual: " + treeMap.size() );
        System.out.println("Map entries: " + treeMap);
        System.out.println(btp1.print());

        /*System.out.println("The ceiling entry of 18 should be 22. Actual: " + treeMap.ceilingEntry( 18 ));
        System.out.println("The ceiling entry of 93 should be 93. Actual: " + treeMap.ceilingEntry( 93 ));
        System.out.println("The ceiling entry of 43 should be 44. Actual: " + treeMap.ceilingEntry( 43 ));
        System.out.println("The floor entry of 18 should be 17. Actual: " + treeMap.floorEntry( 18 ));
        System.out.println("The floor entry of 65 should be 65. Actual: " + treeMap.floorEntry( 65 ));
        System.out.println("The floor entry of 45 should be 44. Actual: " + treeMap.floorEntry( 45 ));
        System.out.println();

        System.out.println("The higher entry of 81 should be 82. Actual: " + treeMap.higherEntry( 81 ));
        System.out.println("The higher entry of 93 should be null. Actual: " + treeMap.higherEntry( 93 ));
        System.out.println("The higher entry of 32 should be 44. Actual: " + treeMap.higherEntry( 32 ));

        System.out.println("The lower entry of 15 should be null. Actual: " + treeMap.lowerEntry( 15 ));
        System.out.println("The lower entry of 22 should be 17. Actual: " + treeMap.lowerEntry( 22 ));
        System.out.println("The lower entry of 55 should be 54. Actual: " + treeMap.lowerEntry( 55 ));*/
		
	}
}
