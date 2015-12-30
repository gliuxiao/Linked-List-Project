package listClasses;

import java.util.Iterator;

/**
 * This class implements a singly-linked list that performs some basic operations, 
 * including add, remove, retrieve and getting other relevant information of the 
 * linked list, such as size
 * @param <T> the type of information that the list contains
 */
public class BasicLinkedList<T> implements Iterable<T>{

	/**
	 * A protected inner class that defines what a node contains
	 */
	protected class Node {
		
		protected T data;
		protected Node next;
		
		/**
		 * A constructor of a node object that contains the data it holds and 
		 * reference to the next node object
		 * @param data the data that the object holds
		 */
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	
	protected int listSize;
	protected Node head;
	protected Node tail;
	
	/**
	 * A standard constructor that creates an empty linked list with both head 
	 * and tail pointing to null
	 */
	public BasicLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Getting the current size of the list
	 * @return the number of elements in the list
	 */
	public int getSize() {
		return listSize;
	}

	/**
	 * Adding an element to the end of the list
	 * @param data the element being added to the list
	 * @return the list to which the element is added
	 */
	public BasicLinkedList<T> addToEnd(T data) {
		Node item = new Node(data);
		if (this.tail == null) {
			// adding a node to an empty list with both head and tail pointing 
			// to the new node
			tail = item;
			head = tail;
		} else {
			// assigning tail pointing to the added node
			tail.next = item;
			tail = item;
		}
		// increment list size by one
		listSize++;
		return this;
	}

	/**
	 * Adding a new node to the beginning of the list
	 * @param data the element being added to the head of the list
	 * @return the list to which the element is added
	 */
	public BasicLinkedList<T> addToFront(T data) {
		Node item = new Node(data);
		if (this.head == null) {
			// adding a node to an empty list with both head and tail pointing 
			// to the new node
			head = item;
			tail = head;
		} else {
			// assigning head pointing to the added node
			item.next = head;
			head = item;
		}
		// increment list size by one
		listSize++;
		return this;
	}

	/**
	 * Getting the first element of the list
	 * @return T the data of the first element in the list
	 */
	public T getFirst() {
		if (this.head == null) {
			return null;
		} else {
			return this.head.data;	
		}
	}

	/**
	 * Getting the last element of the list
	 * @return T the data of the last element in the list
	 */
	public T getLast() {
		if (this.tail == null) {
			return null;
		} else {
			return this.tail.data;
		}
	}

	/**
	 * Removes and returns the first element of the list
	 * @return the data of the first element in the list
	 */
	public T retrieveFirstElement() {
		// retrieving from a list with only one element
		if (this.head == null) {
			return null;
		} else {
			// reassigning head reference
			Node retrItem = head;
			head = head.next;
			listSize--;
			return retrItem.data;
		}
	}
	
	/**
	 * Removes and returns the last element of the list
	 * @return the data of the last element in the list
	 */
	public T retrieveLastElement() {
		// retrieving from a list with only one element
		if (this.tail == null) {
			return null;
		} else {
			Node retrItem = tail;
		    Node curr = head;
			Node prev = null;
			
			while (curr != null) {
				// traverse the list until curr reaches the end of the list
				prev = curr;
				curr = curr.next;
				// reassigning tail reference
				if (curr == tail) {
					prev.next = null;
					curr = null;
					tail = prev;
				}
			}
			listSize--;
			return retrItem.data;
		}
	}

	/**
	 * Removes all elements in the list that matches the given data
	 * @param targetData the data being removed from the list
	 * @param comparator 
	 * 				compare method used to determine whether the current element 
	 * 				in the list matches the target data
	 * @return the list with elements removed
	 */
	public BasicLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		// create a node referencing the current data and the previous element
		Node curr = head;
		Node prev = null;
		
		int counter = 0;
		while (curr != null) {
			
			if (comparator.compare(targetData, curr.data) == 0) {
				if (head == tail) {
				// when removing from the list with only one node
					head = null;
					tail = null;
					counter++;
					break;
				} else if (curr == head && prev == null) {
				// when removing head
					head = curr.next;
					curr = head;
					counter++;
				} else if (curr == tail) {
				// when removing tail
					prev.next = null;
					tail = prev;
					counter++;
					break;
				} else {
				// when removing elements other than head and tail
					curr = curr.next;
					prev.next = curr;
					counter++;
				}

			} else {
				// traverse the list if the current data does not match the 
				// target data
				prev = curr;
				curr = curr.next;
			}
			
		}
		// subtract the total number of elements being removed from the list
		listSize -= counter;
		return this;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	/**
	 * An anonymous inner class that implements Iterable interface
	 */
	class LinkedListIterator implements Iterator<T> {
		private Node cursor;
		
		/**
		 * A constructor that creates a reference to the first element of the list
		 */
		private LinkedListIterator() {
			cursor = head;
		}
		
		/**
		 * This method checks if there is more element in the list other than the 
		 * current node
		 */
		public boolean hasNext() {
			return (cursor != null);
		}
		
		/**
		 * This method returns the current element in the list and makes the cursor 
		 * pointing to the next element to the list
		 */
		public T next() {
			Node curr = cursor;
			cursor = cursor.next;
			return curr.data;
		}
	}
}
