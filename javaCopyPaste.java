package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList; // diane rover easy A
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes for head and tail
    head = new Node();
    tail = new Node();
    //linking an empty list
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    // TODO Auto-generated method stub
    return size;
  }
  
  //offset is logical index, like ht index of all the elements
  
  


  /**
   * Inserts newNode into the list after current without
   * updating size.
   * Precondition: current != null, newNode != null
   */
  private void link(Node current, Node newNode)
  {
    newNode.previous = current;
    newNode.next = current.next;
    current.next.previous = newNode;
    current.next = newNode;
  }
  
  
  /**
   * Removes current from the list without
   * updating size.
   */

  private void unlink(Node current)
  {
    current.previous.next = current.next;
    current.next.previous = current.previous;
  }


  
  private class NodeInfo{
	  
	  private Node node;
	  private int offset;
	  
	  public NodeInfo(Node passedNode, int offset) {
		  node = passedNode;
		  this.offset = offset;
	  }
	  
  }
  
  public NodeInfo find(int pos) {
	  
	  
	  //first attempt
	  
//	  // int currentNode;
//	  // currentNode = head;
//	  
//	  while(true) { //gets position in the node you want
//		  //pos -= node[currentNode].count;
//		  pos -= currentNode.next.count;
//		  currentNode++;
//		  
//		  
//		  
//		  
//		  if(pos < nodeSize) {
//			  // save the current node I'm in?
//			  // or just return a nodeInfo right here - jake
//			  break;
//		  }
//	  }
//	  
//	  for(int i = 0; i < currentNode; i++) { // gets node you want
//		  
//	  }
//	  
//	  //pos is offset you're looking for
//	  
//	  
//	  
//	  // go through each value of the node(s) until you find the right one
//	  // then return nodeInfo 
//	  
	  
	  
	  int fIndex = 0;
	  Node tempNode = head.next;
	  
	  while(fIndex + tempNode.count <= pos) {
		  fIndex += tempNode.count;
		  tempNode = tempNode.next;
		  
		  if(tempNode == tail) { // 
			  break;
		  }
	  }
	  
	  return new NodeInfo(tempNode, pos - fIndex);
	  
	  
  }
  
  @Override
  public boolean add(E item) throws NullPointerException
  {
    // TODO Auto-generated method stub
	
	  //adds a new node in between last node and tail
	  // where should I use the parameter 'item'?
	  /* I feel like this is fucked up somehow?
	   * if there's more than one node named 'temp' or 'addedNode' in the array that's bad no? // nah that's ok
	   */
	  // do I still increment size?
	  // add throw NullPointerException
	 
	  // next and previous are swapped???
	  
	  // only adding a node if the last node is full
	  
	  	//conditioner for adding a new node
		  // if tail.previous.Nodesize == 4
		  
		  // if head.next == tail && tail.previous == head
	  
	  	// what is the variable for amount of items in a node
	  
	  if(item == null) {
		  throw new NullPointerException();
	  }
	 
	  // only add a new node if the last node is full or if the list is empty
	  if(tail.previous.count == 4 || head.next == tail && tail.previous == head)
	  {
		  
		  Node temp; // the node that is formerly the last node of the list (excluding the tail)
		  Node addedNode = new Node(); // the node that's being added
		  
		  temp = tail.previous;
		  addedNode.previous = temp; 
		  tail.previous = addedNode;
		  temp.next = addedNode;
		  addedNode.next = tail;
		  
	  }
	  
	  tail.previous.addItem(item);
	  ++size;
//	  ++tail.previous.count; 
	  //^ commented this out because addItem(item) already increments count
	  // so with that line uncommented it would increment twice
	    
	    return true;
	  
  }

  @Override
  public void add(int pos, E item)
  {
    // TODO Auto-generated method stub
	  
	  if(pos < 0 || pos > size) {
		  throw new IndexOutOfBoundsException();
	  }
	  
	  if(item == null) {
		  throw new NullPointerException();
	  }
	  
	  //check if empty list: or  
	  if(head.next == tail || pos == size) {

	  	// if this happens you need to make a new node. use normal add, that adds to the end of list 
	  	add(item);
	  }
  	
	  NodeInfo nodeInfo = find(pos);
	  Node tempNode = nodeInfo.node;
	  int offset = nodeInfo.offset;
	  
	  
	  
	  if(offset == 0) {

			if(tempNode.previous.count < nodeSize && tempNode.previous != head) { 
				// want to check if the node behind has free space, so you can add to that node behind 
				tempNode.previous.addItem(item); // addItem is a function of node 
			}

			// [1,2,3,4] <-- calling find pos(4) on that will return tail, because tail is still after that 

			else if(tempNode == tail) {
				add(item); // this add should create a new node to place item in // which add is this using?
			}
  	
	  }
	  
	//if the node you want to add to has space, add to it 
	  if(tempNode.count < size) {
	  	tempNode.addItem(offset,item); // this will shift the elements as necessary 
	  }
	  
	  
	  
	// if the node you want to add to has no space 
	// cut and paste the last half into a new node. paste the last half that you cut into the first half of the // // new node 
	//add item to halfPoint *** 
	else{ 
		Node newNode = new Node();
		int halfPoint = nodeSize / 2;
		int count = 0;

		while(count < halfPoint){ 
			newNode.addItem(tempNode.data[halfPoint]); 
			tempNode.removeItem(halfPoint); 
			count++;
		}

		// link the node you orignally wanted to add to, with the node you created 
		link(tempNode,newNode);

		
		
		// now we are adding item 
		if(offset  <= halfPoint) {
			tempNode.addItem(offset, item);
			// addItem is a function of node, will automatically shift the others 
		}
	 

		if(offset > halfPoint) {
			tempNode.addItem(offset - halfPoint, item);
		}
	 
		
	}
	  
	 
	  size++;
	  
  }

  @Override
  public E remove(int pos)
  {
    // TODO Auto-generated method stub
		if(pos < 0 || pos >= size) {
    		throw new IndexOutOfBoundsException();
    		
    	}
    	NodeInfo nodeInfo = find(pos);
    	Node currentNode = nodeInfo.node;
    	int offset = nodeInfo.offset;
    	E removedElement = currentNode.data[offset];
    	
    	if(currentNode.next == tail && currentNode.count == 1) {
    		unlink(currentNode);
    	}
    	
    	
    	else if(currentNode.next == tail || currentNode.count > nodeSize / 2) {
    		currentNode.removeItem(offset);
    	}
    	
    	else {
    		Node nextNode = currentNode.next;
    		currentNode.removeItem(offset);
    		
    		if(nextNode.count > nodeSize / 2) {
    			currentNode.addItem(nextNode.data[0]);
    			nextNode.removeItem(0);
    		}
    		
    		else if(nextNode.count <= nodeSize / 2) {
    			for(int i = 0; i < nextNode.count; i++) {
    				currentNode.addItem(nextNode.data[i]);
    			}
    			
    			unlink(nextNode);
    		}
    	}
	  
    size--;
    return removedElement;
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  
	  
	  // TODO 
	  
	  E[] sortingDataList = (E[]) new Comparable[size];

	  
		int tempIndex = 0;
		
		Node temp = head.next;
		while (temp != tail) {
			
			for (int i = 0; i < temp.count; i++) {
				
				sortingDataList[tempIndex] = temp.data[i];
				tempIndex++;
				
			}
			temp = temp.next;
			
		}

		head.next = tail;
		tail.previous = head;
		insertionSort(sortingDataList, new ElementComparator());
		size = 0;
		
		
		for (int i = 0; i < sortingDataList.length; i++) {
			
			add(sortingDataList[i]);
			
		}
	  
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  // TODO 
	  E[] sortingDataList = (E[]) new Comparable[size];
	  
	  Node tempNode = head.next;
	  int tempIndex = 0;
	  
	  
	  while (tempNode != tail) {
		  
		  for (int i = 0; i < tempNode.count; i++) {
			  
				sortingDataList[tempIndex] = tempNode.data[i];
				tempIndex++;
				
			}
		  
			tempNode = tempNode.next;
	  }
	  
	  
	  
	  
		head.next = tail;
		
		tail.previous = head;

		bubbleSort(sortingDataList);
		size = 0;
		for (int i = 0; i < sortingDataList.length; i++) {
			add(sortingDataList[i]);
		}
	  
  }
  
  @Override
  public Iterator<E> iterator()
  {
    // TODO Auto-generated method stub
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    // TODO Auto-generated method stub
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
    // TODO Auto-generated method stub
    return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }
 
  private class StoutListIterator implements ListIterator<E>
  {
	// constants you possibly use ...   
	  
	// instance variables ... 
//	private int cursorPos;
//	private E currentNode;
	
	//public E[] data_list;
	
	int lastAction = -1; // if next, previous, or nothing was last performed. 1 when next, 0  when previous, -1 when nothing was last performed
	int next_performed = 1; // lastAction set to next_performed when next is performed
	int previous_performed = 0; // lastAction set to previous_performed when previous is performed
	
	
	// k stuff
	private int index;
	private int offset;
	NodeInfo nodeInfo;
	Node currentNode;
	
	
    /**
     * Default constructor
     */
    public StoutListIterator()
    {
    	// TODO 
    	this(0);
		//    	lastAction = -1;
		//    	cursorPos = 0;
    	//toArrayFormat()
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
    	// TODO 
    	
//    	if(pos < 0 || pos > size) { // throw something
//    		throw new IndexOutOfBoundsException();
//    	}
//    		cursorPos = pos;
//    		lastAction = -1;
//    		//toArrayFormat()
    	
    	if(pos < 0 || pos > size) { 
    		throw new IndexOutOfBoundsException();
    	}
    	
    	nodeInfo = find(pos);
    	currentNode = nodeInfo.node;
    	index = pos;
    	offset = nodeInfo.offset;
    	lastAction = -1;
    	
    	
    }
    

    @Override
    public boolean hasNext() {
    
    	// TODO 
    	
    	/*
    	 *  if cursorPos < all the elements in the list
    	 *  elements meaning an elements inside a node
    	 *  size being the number of all elements
    	 */
	    	if(index < size) {
	    		return true;
	    	}
    	
    	return false;
    }

    @Override
    public E next()
    {
    	// TODO 
    	
    	// cursor as a node? then you can use cursor.data this is on lecture 21	
    	
    	
    	if(!(hasNext())){ 
    		throw new NoSuchElementException();
    	}
    	
    	lastAction = next_performed;	
    	
    	//update location before returning 
    	index++;
    	offset++;
    	return currentNode.data[offset];// return what next just went over
    }

    @Override
    public void remove()
    {
    	// TODO 
    	
    	if(lastAction <= -1) { // make sure it's ok to remove. (can't remove twice in a row, must have a next or prev performed before, etc.)
    		throw new IllegalStateException();
    	}
    	
    	
    	if(lastAction == 1) { //next was last performed, thus we remove behind the cursor
    		currentNode.removeItem(offset);
    		StoutList.this.remove(index);
    		//cursorPos ==
    		//cursorPos
    		lastAction = -1; // can't remove twice in a row
    	}
    	
    	else if(lastAction == 0) { // previous was last performed, thus we remove in front of the cursor
    		currentNode.removeItem(offset);
    		StoutList.this.remove(index);
    		lastAction = -1; // can't remove twice in a row
    	}
    	
    	
    	// if there was a merge?
    	
    	
    	
    	
    	
    	
    	
    }

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		
		if(index > 0) {
    		return true;
    	}
	
		return false;
	}

	@Override
	public E previous() {
		// TODO Auto-generated method stub

		// are you return the previous node or the previous element? presumably element
		
		
//		if(!(hasPrevious())){ 
//    		throw new NoSuchElementException();
//    	}
//		
//		
//		
//		index--;
//    	return data_list[cursorPos + 1]; // return what next just went over
		
		
		if(!(hasPrevious())){ 
    		throw new NoSuchElementException();
    	}
		lastAction = previous_performed;
		index--;
		
		//decrement offset too. update location then return current location
		offset--;
		
		// also have a case for if offset == -1, where you'll have to go into the previous node
		
		return currentNode.data[offset];
		
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return index;
		// next returns current element
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return index - 1;
		//previous returns your previous element
	}

	@Override
	public void set(E e) {
		// TODO Auto-generated method stub
		
		if(lastAction > -1) {
			if(lastAction == 0) { // previous
				
				//NodeInfo cNOde = find(index - 1);
				currentNode.previous.data[index] = e; // index instead of offset?
			}
			
			else if(lastAction == 1) { // next
				
				currentNode.data[index] = e;
			}
		}
		else {
			throw new IllegalStateException();
		}
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
		if (e == null) {
			throw new NullPointerException();
		}
		
		
		
		StoutList.this.add(index, e);
		index++;
		lastAction = -1;
		
		
		
	}
    
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.
    // 
    // ...
    // 
  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  
	  // TODO
	  
	  for (int i = 1; i < arr.length; i++) {
		  
			E key = arr[i];
			
			int j = i - 1;

			
			
			while (j >= 0 && comp.compare(arr[j], key) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  
	  // TODO
	  
	  int bIndex = arr.length;
	  
	  
		for (int i = 0; i < bIndex - 1; i++) {
			
			for (int j = 0; j < bIndex - i - 1; j++) {
				
				if (arr[j].compareTo(arr[j + 1]) < 0) {
					E temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
  }
 

  
  
  
//  node info is what is reutrned 
//  
//  find an input, input = 57
//  
//  
//  find: convert from index to node and offset
//  offset an int, node a node pointer
//  traverse through every node, not through every element // dumb way
//  
//  
//  57 -3 - 4
//  keep a running tally of how many elements you have while yuou go through the nodes
//  
//  
//  add count of each node until you are less than or equal to node_size away from your target node
//  
  
}
