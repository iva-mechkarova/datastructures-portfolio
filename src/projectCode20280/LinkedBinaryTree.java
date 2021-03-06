package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {


  /** Nested static class for a binary tree node. */
  protected static class Node<E> implements Position<E> {
	  private E element;
	  private Node<E> parent;
	  private Node<E> left;
	  private Node<E> right;
	  
	  public Node(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild)
	  {
		  element = e;
		  this.parent = parent;
		  left = leftChild;
		  right = rightChild;
	  }

	//Accessor methods
	public E getElement() { return element; }
	public Node<E> getParent() { return parent; }
	public Node<E> getLeft() { return left; }
	public Node<E> getRight() { return right; }
	
	public void setElement(E e) { element = e; }
	public void setParent(Node<E> e) { parent = e; }
	public void setLeft(Node<E> e) { left = e; }
	public void setRight(Node<E> e) { right = e; }
	
	  @Override
	  public String toString() {
		  StringBuilder sb = new StringBuilder();
		  sb.append(element);
		  return sb.toString();
	  }  
  } 

  /** Factory function to create a new node storing element e. */
  protected Node<E> createNode(E e, Node<E> parent,
                                  Node<E> left, Node<E> right) {
    return new Node<E>(e, parent, left, right);
  }

  // LinkedBinaryTree instance variables
  /** The root of the binary tree */
  protected Node<E> root = null;     // root of the tree

  /** The number of nodes in the binary tree */
  private int size = 0;              // number of nodes in the tree
  
  /**Comparator for generic elements*/
  private final DefaultComparator<E> comparator = new DefaultComparator<E>(); 
  
  /**Method to compare generic elements
   * @param first element, second element
   * @return 1 if first > second, -1 if first < second, 0 if equal
   * */
  protected int compareTo(E first, E second)
  {
	  return comparator.compare(first, second);
  }

  // constructor
  /** Construts an empty binary tree. */
  public LinkedBinaryTree() { }      // constructs an empty binary tree

  // nonpublic utility
  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this tree)
   * @return    the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)
  /**
   * Returns the number of nodes in the tree.
   * @return number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the root Position of the tree (or null if tree is empty).
   * @return root Position of the tree (or null if tree is empty)
   */
  @Override
  public Position<E> root() {
    return root;
  }

  /**
   * Returns the Position of p's parent (or null if p is root).
   *
   * @param p    A valid Position within the tree
   * @return Position of p's parent (or null if p is root)
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
	  Node<E> x = validate(p);
	  
	  return x.parent; //Will return null if x is root as parent will be set to null
  }

  /**
   * Returns the Position of p's left child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the left child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException {
	  Node<E> x = validate(p);
	  
	  return x.left; //Will return null if left child doesn't exist as it will be set to null
  }

  /**
   * Returns the Position of p's right child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException {
	  Node<E> x = validate(p);
	  
	  return x.right; //Will return null if right child doesn't exist as it will be set to null
  }

  // update methods supported by this class
  /**
   * Places element e at the root of an empty tree and returns its new Position.
   *
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalStateException if the tree is not empty
   */
  public Position<E> addRoot(E e) throws IllegalStateException {
	  if(!isEmpty())
	  {
		  throw new IllegalStateException("Tree is not empty");
	  }
	  
	  root = createNode(e, null, null, null);
	  size = 1;
	  return root;
  }

  public void insert(E e){
      //recursively add from root
      root = addRecursive(root, e);
      ++size;
  }
  
  //recursively add Nodes to binary tree in proper position
  private Node<E> addRecursive(Node<E> p, E e){
	if(isEmpty())
	{
		root = createNode(e, null, null, null);
		return root;
	}
	
	if(compareTo(e, p.element)==-1)
	{
		if(p.left!=null)
		{
			addRecursive(p.left, e);
		}
		else
		{
			p.left = createNode(e, p, null, null);
		}
	}
	else
	{
		if(p.right!=null)
		{
			addRecursive(p.right, e);
		}
		else
		{
			p.right = createNode(e, p, null, null);
		}
	}
	
	return p;
  }

  
  /**
   * Creates a new left child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the left of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p already has a left child
   */
  public Position<E> addLeft(Position<E> p, E e)
                          throws IllegalArgumentException {
	Node<E> parent = validate(p);
	if(parent.getLeft()!=null)
	{
		throw new IllegalArgumentException("This node already has a left child");
	}
	
	Node<E> child = createNode(e, parent, null, null);
	parent.setLeft(child);
	size++;
	return child;
  }

  /**
   * Creates a new right child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the right of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a right child
   */
  public Position<E> addRight(Position<E> p, E e)
                          throws IllegalArgumentException {
	Node<E> parent = validate(p);
	
	if(parent.getRight()!=null)
	{
		throw new IllegalArgumentException("This node already has a right child");
	}
	
	Node<E> child = createNode(e, parent, null, null);
	parent.setRight(child);
	size++;
	return child;
  }

  /**
   * Replaces the element at Position p with element e and returns the replaced element.
   *
   * @param p   the relevant Position
   * @param e   the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException {
	  Node<E> old = validate(p);
	  
	  E replacedElement = old.element;
	  old.setElement(e);
	  return replacedElement;
  }

  /**
   * Attaches trees t1 and t2, respectively, as the left and right subtree of the
   * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
   *
   * @param p   a leaf of the tree
   * @param t1  an independent tree whose structure becomes the left child of p
   * @param t2  an independent tree whose structure becomes the right child of p
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p is not a leaf
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1,
                    LinkedBinaryTree<E> t2) throws IllegalArgumentException {
	Node<E> x = validate(p);
	if(isInternal(x))
	{
		throw new IllegalArgumentException("p must be a leaf of the tree");
	}
	
	size += t1.size + t2.size; 
	
	if(!t1.isEmpty())
	{
		t1.root.setParent(x);
		x.setLeft(t1.root);
		t1.root = null;
		t1.size = 0;
	}
	
	if(!t2.isEmpty())
	{
		t2.root.setParent(x);
		x.setRight(t2.root);
		t2.root = null;
		t2.size = 0;
	}
	
  }

  /**
   * Removes the node at Position p and replaces it with its child, if any.
   *
   * @param p   the relevant Position
   * @return element that was removed
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p has two children.
   */
  public E remove(Position<E> p) throws IllegalArgumentException {
	  Node<E> toRemove = (Node<E>)validate(p);
	  
	  if(numChildren(p) == 2)
	  {
		  throw new IllegalArgumentException("p has two childern");
	  }
	  
	  Node<E> child = (toRemove.getLeft()==null ? toRemove.right : toRemove.left);
	 
	  if(child!=null)
	  {
		  child.parent = toRemove.parent;
	  }
	  
	  if(toRemove == root)
	  {
		  root = child;
	  }
	  else
	  {
		  Node<E> parent = toRemove.parent;
		  if(toRemove == parent.left)
		  {
			  parent.setLeft(child);
		  }
		  else if(toRemove == parent.right)
		  {
			  parent.setRight(child);
		  }
	  }
	  
	  size--;
	  E removed = toRemove.getElement();
	  toRemove.setElement(null);
	  toRemove.setLeft(null);
	  toRemove.setRight(null);
	  toRemove.setParent(toRemove);
	 
	  return removed;
  }
  
  /**
   * Method to construct tree from an array
   * @param the array
   * */
  public void createLevelOrder(E[] arr)
  {
	  root = createLevelOrderHelper(arr, root, 0);
  }
  
  /**
   * Helper method used in createLevelOrder to construct tree from array
   * @param the array, the parent node, position of this node
   * @return if the position is less than the length of the array return the node that is added,
   * otherwise return the parent node
   * */
  private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i)
  {
	  if(i<arr.length)
	  {
		  Node<E> n = createNode(arr[i], p, null, null);
		  n.left = createLevelOrderHelper(arr, n.left, 2*i + 1);
		  n.right = createLevelOrderHelper(arr, n.right, 2*i + 2);
		  size++;
		  return n; 
	  }
	  
	  return p;
  }
  
  /**
   * Method for assignment 2 Q1 - tests if a tree has a symmetric structure.
   * @param root 
   * @return true if symmetric, false otherwise
   * */
  public boolean isSymmetric(Node<E> root)
  {
	  return isSymmetricHelper(root.left, root.right);
  }
  
  /**
   * Method to check if left and right subtrees are symmetric
   * @param node to the left
   * @param node to the right
   * @return true if nodes are symmetric, false otherwise
   */
  private boolean isSymmetricHelper(Node<E> leftSide, Node<E> rightSide)
  {
	  //Base case � if left subtree and right subtree are empty
	  if(leftSide==null && rightSide==null)
		  return true;
	  
	 //Return true if both subtrees are not empty, if left subtree is symmetric to right subtree and right subtree is symmetric to left subtree
	  return (leftSide!=null && rightSide!=null) && isSymmetricHelper(leftSide.left, rightSide.right) && isSymmetricHelper(leftSide.right, rightSide.left);
  }
  
  /**
   * Assignment 2 Q2 - Helper method to get mirror image of subtree
   * @param the root of the tree
   */
  private void mirrorHelper(Node<E> subtreeRoot)
  {
	  //Base case - if the subtree is empty
	  if(subtreeRoot==null)
		  return;
	  
	  //Find mirror images of left subtrees and right subtrees
	  mirrorHelper(subtreeRoot.left);
	  mirrorHelper(subtreeRoot.right);
	  
	  //Swap left and right - i.e. get mirror
	  Node<E> temp = subtreeRoot.left;
	  subtreeRoot.left = subtreeRoot.right;
	  subtreeRoot.right = temp; 
  }
  
  /**
   * Assignment 2 Q2 - Method to get mirror image of tree
   */
  public void mirror()
  {
	  mirrorHelper(root);
  }
  
  /*Global variables for Assignment 2 Q3 - Find distance between 2 nodes*/
  private int d1 = -1; //Depth of n1 initialized to -1
  private int d2 = -1; //Depth of n2 initialized to -1
  private int dist = 0; //Distance initalized to 0
  
  /**
   * Assignment 2 Q3 - Method to find LCA whilst also updating the dist global variable
   * @param root, first node, second node, level of LCA
   * @return the lowest common ancestor of n1 and n2
   */
  public Node<E> findLCA(Node<E> root, E n1, E n2, int depthOfLCA)
  { 
      
      //Base case - when root is null
      if (root == null) 
          return null; 
        
      /*If n1 equals the element at root, then this is the LCA*/
      if (root.element == n1)
      { 
          d1 = depthOfLCA; 
          return root; 
      } 
      
      /*If n1 equals the element at root, then this is the LCA*/
      if (root.element == n2) 
      { 
          d2 = depthOfLCA; 
          return root; 
      } 
        
      /*Look for n1 and n2 in the left and right subtrees 
       * - add 1 to the depth each time as the depth increases*/
      Node<E> leftLCA = findLCA(root.left, n1, n2,  depthOfLCA + 1); 
      Node<E> rightLCA = findLCA(root.right, n1, n2,  depthOfLCA + 1); 
        
      /*If both of the calls on the left and right subtrees return non-null, then
       * one key is in the left subtree and the other is in the right subtree so this node is the LCA*/
      if (leftLCA != null && rightLCA != null) 
      { 
          dist = (d1 + d2) - 2*depthOfLCA; //Dist formula is: depth of n1 + depth of n2 - 2*(depth of LCA)
          return root; //This is the LCA
      } 
        
      /*Otherwise, check if LCA is in left subtree or right subtree*/
      return (leftLCA != null)? leftLCA : rightLCA;     
  } 
  
  /**
   * Helper method for dist
   * @param root, first node, second node
   * @return distance between first node and second node
   */
  public int distHelper(Node<E> root, E n1, E n2)
  { 
      Node<E> lca = findLCA(root, n1, n2, 1); //Find LCA of n1 and n2
       
     //If d1 and d2 was found (i.e. n1 and n2 were in tree), then return dist
     if (d1 != -1 && d2 != -1) 
         return dist; 
       
     //If n1 is ancestor of n2, then n1 is LCA so just pass this in as the root to find the dist 
     if (d1 != -1) 
     { 
         dist = findDepth(lca, n2, 0); 
         return dist; 
     } 
       
     //If n2 is ancestor of n1, then n2 is LCA so just pass this in as the root to find the dist 
     if (d2 != -1) 
     { 
         dist = findDepth(lca, n1, 0); 
         return dist; 
     } 
       
     return -1; //If dist cannot be found due to one or both of the nodes not being present, return -1
 }
  
  /**
   * Method to find depth of an element from any subtree
   * @param root of subtree, element, depth of root of subtree
   * @return depth of element in subtree
   */
  public int findDepth(Node<E> root, E e, int depth) 
  { 
      //Base case - if root is null
      if (root == null) 
          return -1; 
        
      //If element is at the root then return the depth
      if (root.element == e) 
          return depth; 
      
      //Check if the element is in the left or right subtree and return the depth
      int depthLeft = findDepth(root.left, e, depth + 1); 
      return (depthLeft != -1)? depthLeft : findDepth(root.right, e, depth + 1); 
  } 
  
  
  /**
   * Method to find the distance between two elements in a tree
   * @param node one, node two
   * @return the distance
   */
  public int dist(E n1, E n2)
  {
	  return distHelper(root, n1, n2);
  }
  
  @Override
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");
	  for(Position<E> p : positions()) {
		  sb.append(p.getElement());
		  sb.append(", ");	  }
	  sb.replace(sb.lastIndexOf(","), sb.length(), "]");
	  return sb.toString();
  }
  
  public static void main(String [] args) {
	  LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
	  BinaryTreePrinter<Integer> btp = new BinaryTreePrinter<>(bt);
 
	  //Direct Construction of tree
	  Position<Integer> root = bt.addRoot(12);
	  Position<Integer> p1 = bt.addLeft(root, 25);
	  Position<Integer> p2 = bt.addRight(root, 31);
	  
	  Position<Integer> p3 = bt.addLeft(p1, 58);
	  Position<Integer> p6 = bt.addRight(p1, 36);
	  
	  Position<Integer> p5 = bt.addLeft(p2, 42);
	  bt.addRight(p2, 90);
	  
	  Position<Integer> p4 = bt.addLeft(p3, 62);
	  Position<Integer> p7 = bt.addRight(p3, 75);
	  
	  System.out.println(btp.print());
	  System.out.println("bt inorder. Expected: [62, 58, 75, 25, 36, 12, 42, 31, 90]. Actual: " + bt.inorder());
	  System.out.println("Expected size: 9. Actual: " + bt.size());
	  System.out.println("bt preorder. Expected: [12, 25, 58, 62, 75, 36, 31, 42, 90]. Actual: " + bt.preorder());
	  
	  System.out.println("Expected height: 3. Actual: " + bt.height(bt.root()));
	  System.out.println("Expected depth of root: 0. Actual: " + bt.depth(bt.root()));
	  System.out.println("Expected depth of p4: 3. Actual: " + bt.depth(p4));
	  System.out.println("Expected depth of p5: 2. Actual: " + bt.depth(p5));
	  bt.remove(p5);
	  System.out.println("Removing element 42 (p5).");
	  System.out.println("Expected size: 8. Actual: " + bt.size());
	  System.out.println(btp.print());
	  System.out.println("Setting element 25 (p1) to be 26.");
	  bt.set(p1, 26);
	  System.out.println(btp.print());
	  System.out.println("Left child of 26 (p1) should be 58. Actual: " + bt.left(p1));
	  System.out.println("Right child of 26 (p1) should be 36. Actual: " + bt.right(p1));
	  System.out.println("Right child of 75 (p4) should be null. Actual: " + bt.right(p4));
	  System.out.println("Symemtric: " + bt.isSymmetric(bt.root));
	  System.out.println("Removing 62, 75 and 36.");
	  bt.remove(p4);
	  bt.remove(p6);
	  bt.remove(p7);
	  System.out.println(btp.print());
	  System.out.println("Symemtric: " + bt.isSymmetric(bt.root));
	  System.out.println("Get mirror image.");
	  bt.mirror();
	  System.out.println(btp.print());
	  System.out.println(bt.height(bt.root()));
	  System.out.println("Distance: " + bt.dist(12, 58));
	  
	  //Level Order Construction of tree
	  /*Integer[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75, 13, 24, 77};
	  bt.createLevelOrder(arr);
	  
	  System.out.println("Expected inorder: [62, 58, 75, 25, 13, 36, 24, 12, 77, 42, 31, 90]. Actual: " + bt.inorder());  
	  System.out.println(btp.print());
	  System.out.println("Expected preorder: [12, 25, 58, 62, 75, 36, 13, 24, 31, 42, 77, 90]. Actual: " + bt.preorder());
	  
	  System.out.println("Expected height: 3. Actual height: " + bt.height(bt.root()));
	  System.out.println("Expected depth of root: 0. Actual depth: " + bt.depth(bt.root()));
	  
	  System.out.println("bt: " + bt.size() + " " + bt);*/
  }
} 

