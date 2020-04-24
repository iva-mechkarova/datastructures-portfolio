package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of a sorted map using an AVL tree.
 */

public class AVLTreeMap<K, V> extends TreeMap<K, V> {
    
	/** Constructs an empty map using the natural ordering of keys. */
	public AVLTreeMap() {
		super();
	}

	/**
	 * Constructs an empty map using the given comparator to order keys.
	 * 
	 * @param comp comparator defining the order of keys in the map
	 */
	public AVLTreeMap(Comparator<K> comp) {
		super(comp);
	}

	/** Returns the height of the given tree position. */
	protected int height(Position<Entry<K, V>> p) {
		return tree.getAux(p);
	}

	/**
	 * Recomputes the height of the given position based on its children's heights.
	 */
	protected void recomputeHeight(Position<Entry<K, V>> p) {
		tree.setAux(p, 1+Math.max(height(left(p)), height(right(p))));
	}

	/** Returns whether a position has balance factor between -1 and 1 inclusive. */
	protected boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(height(left(p)) - height(right(p))) <=1;
	}

	/** Returns a child of p with height no smaller than that of the other child. */
	protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if(height(left(p)) > height(right(p)))
			return left(p);
		
		if(height(left(p)) < height(right(p)))
			return right(p);
		
		//If children are equal and p is root then can choose either
		if(isRoot(p))
			return left(p);
		
		//If children are equal height, return aligned child
		if(p==left(parent(p)))
			return left(p);
		else
			return right(p);
	}

	/**
	 * Utility used to rebalance after an insert or removal operation. This
	 * traverses the path upward from p, performing a trinode restructuring when
	 * imbalance is found, continuing until balance is restored.
	 */
	protected void rebalance(Position<Entry<K, V>> p) {
		int oldHeight, newHeight; //Initialise oldHeight and newHeight
		do
		{
			oldHeight = height(p); //Not yet calculated if internal
			/*If imbalanced, perform restructuring, setting p to resulting root, recompute height*/
			if(!isBalanced(p))
			{
				p = tree.restructure(tallerChild(tallerChild(p)));
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = height(p);
			p = parent(p);
		} while (oldHeight != newHeight && p!=null);
	}

	/** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	@Override
	protected void rebalanceInsert(Position<Entry<K, V>> p) {
		rebalance(p);
	}

	/** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	@Override
	protected void rebalanceDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p))
			rebalance(parent(p));
	}

	/** Ensure that current tree structure is valid AVL (for debug use only). */
	private boolean sanityCheck() {
		for (Position<Entry<K, V>> p : tree.positions()) {
			if (isInternal(p)) {
				if (p.getElement() == null)
					System.out.println("VIOLATION: Internal node has null entry");
				else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
					System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
					dump();
					return false;
				}
			}
		}
		return true;
	}

	
	public String toBinaryTreeString() {
		BinaryTreePrinter< Entry<K, V> > btp = new BinaryTreePrinter<>( (LinkedBinaryTree<Entry<K, V>>) this.tree);		
		return btp.print();	
	}
	
	
	public static void main(String [] args) {
		AVLTreeMap<Integer, Integer> treeMap = new AVLTreeMap<>();
        BinaryTreePrinter<Entry<Integer, Integer>> btp1 = new BinaryTreePrinter<>( treeMap.tree );
        Integer[] arr = {24, 13, 5, 31, 66, 99, 27, 52, 79, 91, 23, 28, 74, 82, 63};
        for ( Integer i : arr )
        {
            treeMap.put( i, i );
        }
        System.out.println( "Size should now be 15. Actual: " + treeMap.size() );
        System.out.println( "Map entries: " + treeMap );
        System.out.println( btp1.print() );

        treeMap.sanityCheck();

        System.out.println();
        System.out.println( "Attempting to remove element 5 from the tree map." );
        treeMap.remove( 5 );
        System.out.println( "Size should now be 14. Actual: " + treeMap.size() );
        treeMap.sanityCheck();
        System.out.println( "Map entries: " + treeMap );
        System.out.println( btp1.print() );

        System.out.println();
        System.out.println( "Attempting to remove <27, 27>, <99, 99>, and <66, 66> from the map." );
        treeMap.remove( 27 );
        treeMap.remove( 99 );
        treeMap.remove( 66 );
        System.out.println( "Size should now be 11. Actual: " + treeMap.size() );
        treeMap.sanityCheck();
        System.out.println( "Map entries: " + treeMap );
        System.out.println( btp1.print() );

        System.out.println( "Attempting to put entry <37, 37> in the map." );
        treeMap.put( 37, 37 );
        System.out.println( "Size should now be 12. Actual: " + treeMap.size() );
        treeMap.sanityCheck();
        System.out.println( btp1.print() );

        System.out.println( "Putting <15, 15>, <22, 22>, and <70, 70> in the map." );
        treeMap.put( 15, 15 );
        treeMap.put( 22, 22 );
        treeMap.put( 70, 70 );
        System.out.println( "Size should now be 15. Actual: " + treeMap.size() );
        treeMap.sanityCheck();
        System.out.println( btp1.print() );

        System.out.println( "Attempting to change the value of entry <37, 37> to 38." );
        treeMap.put( 37, 38 );
        System.out.println( "Value of key 37 should now be 38. Actual: " + treeMap.get( 37 ) );
        System.out.println( "Size should remain 15. Actual: " + treeMap.size() );
        System.out.println();
        System.out.println("Attempting to change value of <31, 31> to 0.");
        treeMap.put( 31, 0 );
        System.out.println( "Value of key 31 should now be 0. Actual: " + treeMap.get( 31 ) );
        System.out.println( "Size should remain 15. Actual: " + treeMap.size() );
        treeMap.sanityCheck();
        System.out.println("Map entries: " + treeMap);
        System.out.println(btp1.print());

        System.out.println("The ceiling entry of 18 should be 22. Actual: " + treeMap.ceilingEntry( 18 ));
        System.out.println("The ceiling entry of 93 should be null. Actual: " + treeMap.ceilingEntry( 93 ));
        System.out.println("The ceiling entry of 44 should be 52. Actual: " + treeMap.ceilingEntry( 44 ));
        System.out.println("The floor entry of 18 should be 15. Actual: " + treeMap.floorEntry( 18 ));
        System.out.println("The floor entry of 65 should be 63. Actual: " + treeMap.floorEntry( 65 ));
        System.out.println("The floor entry of 30 should be 28. Actual: " + treeMap.floorEntry( 30 ));
        System.out.println();

        System.out.println("The higher entry of 81 should be 82. Actual: " + treeMap.higherEntry( 81 ));
        System.out.println("The higher entry of 95 should be null. Actual: " + treeMap.higherEntry( 95 ));
        System.out.println("The higher entry of 12 should be 13. Actual: " + treeMap.higherEntry( 12 ));

        System.out.println("The lower entry of 12 should be null. Actual: " + treeMap.lowerEntry( 12 ));
        System.out.println("The lower entry of 22 should be 15. Actual: " + treeMap.lowerEntry( 22 ));
        System.out.println("The lower entry of 94 should be 91. Actual: " + treeMap.lowerEntry( 94 ));
		
	}
}

