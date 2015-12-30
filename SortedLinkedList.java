package listClasses;

import java.util.Comparator;

/**
 * This class implements a sorted linked list that performs certain operations 
 * that require comparison with all the other element in the list, including add
 * and remove particular elements in the list.
 * @param <T> the type of information that the list contains
 */
public class SortedLinkedList<T> extends BasicLinkedList<T>{
	
	private Comparator<T> compare;
	
	/**
	 * A constructor that contains the comparator in order to sort the elements
	 * in the list
	 * @param comparator the compare method used to determine the position of 
	 * elements in the list
	 */
	public SortedLinkedList(Comparator<T> comparator) {
		compare = comparator;
	}

	/**
	 * This method adds the given data to the sorted list. Before the element is 
	 * add, the value of the data is compared to the other values of elements in
	 * the list
	 * @param element the data that the user wishes to add to the list
	 * @return the sorted list with the element added to the list
	 */
	
	public SortedLinkedList<T> add(T element) {
		// create a node referencing the current data and the previous element
		Node curr = head;
		Node prev = null;
		Node item = new Node(element);
		
		// adding element to an empty list
		if (head == null) {
			head = item;
			tail = item;
			listSize++;
		} else {
			while (curr != null) {
				if (compare.compare(item.data, curr.data) <= 0) {
					// adding element to the beginning of the list
					if (curr == head) {
						item.next = head;
						head = item;
						listSize++;
						break;
					// adding element in between nodes
					} else {
						prev.next = item;
						item.next = curr;
						listSize++;
						break;
					}
				// adding element to the end of the list
				} else if (compare.compare(item.data, tail.data) > 0) {
					tail.next = item;
					tail = item;
					listSize++;
					break;
				// traverse the list
				} else {
					prev = curr;
					curr = curr.next;
				}
			}
		}
		return this;
	}

	/**
	 * This method removes any elements in the list that match the target data
	 * @param targetData the data that the user wishes to remove from the list
	 * @return the list with elements being removed
	 */
	public BasicLinkedList<T> remove(T targetData) {
		return super.remove(targetData, this.compare);
	}

	/**
	 * This method throws an exception when the user attempts to add an element 
	 * to the end of the list, since the given data must be compared to other 
	 * data in the list first
	 */
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	/**
	 * This method throws an exception when the user attempts to add an element 
	 * to the beginning of the list, since the given data must be compared to other 
	 * data in the list first
	 */
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
}
