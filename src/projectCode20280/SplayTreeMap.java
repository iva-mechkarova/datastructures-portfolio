package projectCode20280;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

    
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
			SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
			Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

			for(Integer i : arr) {
				map.put(i, Integer.toString(i));
			}
			
			System.out.println(map.toString());
			
			//spl.remove(arr[0]);

			//System.out.println("avl: " + spl);
		}
	}
