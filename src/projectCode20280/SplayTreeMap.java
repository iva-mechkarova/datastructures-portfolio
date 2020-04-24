package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of a sorted map using a Splay Tree.
 */
public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    //protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

    
	  /** Constructs an empty map using the natural ordering of keys. */
	  public SplayTreeMap() { super(); }

	  /**
	   * Constructs an empty map using the given comparator to order keys.
	   * @param comp comparator defining the order of keys in the map
	   */
	  public SplayTreeMap(Comparator<K> comp) { super(comp); }

	  /** Utility used to rebalance after a map operation. */
	  private void splay(Position<Entry<K,V>> p) {
		  while(!isRoot(p))
		  {
			  Position<Entry<K,V>> parent = parent(p);
			  Position<Entry<K,V>> grandPar = parent(parent);
			  
			  if(grandPar == null) //Zig case
				  rotate(p); //Move p upwards
			  else if((parent==left(grandPar)) == (p==left(parent))) //Zig-zag case
			  {
				  rotate(parent); //Move parent upwards
				  rotate(p); //Move p upwards
			  }
			  else
			  {
				  rotate(p); //Move p upwards
				  rotate(p); //Move p upwards again
			  }
		  }
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a node access. */
	  @Override
	  protected void rebalanceAccess(Position<Entry<K,V>> p) {
		  if(isExternal(p))
			  p = parent(p);
		  
		  if(p!=null)
			  splay(p);
			  
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	  @Override
	  protected void rebalanceInsert(Position<Entry<K,V>> p) {
		  splay(p);
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	  @Override
	  protected void rebalanceDelete(Position<Entry<K,V>> p) {
		  if(!isRoot(p))
			  splay(parent(p));
	  }
	  
		public static void main(String [] args) {
			SplayTreeMap<Integer, Integer> treeMap = new SplayTreeMap<>();
	        BinaryTreePrinter<Entry<Integer, Integer>> btp1 = new BinaryTreePrinter<>( treeMap.tree );
	        Integer[] arr = {24, 13, 5, 31, 66, 99, 27, 52, 79, 91, 23, 28, 74, 82, 63};
	        for ( Integer i : arr )
	        {
	            treeMap.put( i, i );
	        }
	        System.out.println( "Size should now be 15. Actual: " + treeMap.size() );
	        System.out.println( "Map entries: " + treeMap );
	        System.out.println( btp1.print() );

	        System.out.println( "The first entry of the map should be <5, 5>. Actual: <" + treeMap.firstEntry().getKey() + ", " + treeMap.firstEntry().getValue() + ">." );
	        System.out.println( "The last entry of the map should be <99, 99>. Actual: <" + treeMap.lastEntry().getKey() + ", " + treeMap.lastEntry().getValue() + ">." );

	        System.out.println();
	        System.out.println( "Attempting to remove element 5 from the tree map." );
	        treeMap.remove( 5 );
	        System.out.println( "Size should now be 14. Actual: " + treeMap.size() );
	        System.out.println( "First entry should now be <13, 13>. Actual: <" + treeMap.firstEntry().getKey() + ", " + treeMap.firstEntry().getValue() + ">." );
	        System.out.println( "Map entries: " + treeMap );
	        System.out.println( btp1.print() );

	        System.out.println();
	        System.out.println( "Attempting to remove <27, 27>, <99, 99>, and <66, 66> from the map." );
	        treeMap.remove( 27 );
	        treeMap.remove( 99 );
	        treeMap.remove( 66 );
	        System.out.println( "Size should now be 11. Actual: " + treeMap.size() );
	        System.out.println( "Last entry should now be <91, 91>. Actual: <" + treeMap.lastEntry().getKey() + ", " + treeMap.lastEntry().getValue() + ">." );
	        System.out.println( "Map entries: " + treeMap );
	        System.out.println( "63 should now be the right child of 24, and 28 should be the left child of 63." );
	        System.out.println( "74 should be the left child of 79." );
	        System.out.println( btp1.print() );

	        System.out.println( "Attempting to put entry <37, 37> in the map." );
	        treeMap.put( 37, 37 );
	        System.out.println( "Size should now be 12. Actual: " + treeMap.size() );
	        System.out.println( "37 should now be the root.");
	        System.out.println( btp1.print() );

	        System.out.println( "Putting <15, 15>, <22, 22>, and <70, 70> in the map." );
	        treeMap.put( 15, 15 );
	        treeMap.put( 22, 22 );
	        treeMap.put( 70, 70 );
	        System.out.println( "Size should now be 15. Actual: " + treeMap.size() );
	        System.out.println( "15 should be the left child of 22." );
	        System.out.println( "22 should be the right child of 23." );
	        System.out.println( "70 should be the root." );
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
