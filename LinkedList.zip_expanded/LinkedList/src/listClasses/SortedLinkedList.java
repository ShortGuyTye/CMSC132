package listClasses;

import java.util.*;

/**
 * Implements a generic sorted list using a provided Comparator. It extends
 * BasicLinkedList class.
 * 
 * @author Dept of Computer Science, UMCP
 * 
 */

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	private Comparator<T> comparator;

	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public SortedLinkedList<T> add(T element) {
		if (element == null) {
			return this;
		}
		Node newNode = new Node(element);
		if (head == null) {
			head = new Node(element);
			tail = new Node(element);
			listSize++;
			return this;
		} else {
			Node curr = head;
			if (curr == head) {
				if (comparator.compare(curr.data, element) > 0) {
					newNode.next = head;
					head = newNode;
					listSize++;
					return this;
				}
			}
			while (curr.next != null) {
				if (comparator.compare(curr.next.data, element) > 0) {
					newNode.next = curr.next;
					curr.next = newNode;
					listSize++;
					return this;
				}
				curr = curr.next;
			}
			if (curr.next == null) {
				curr.next = newNode;
				tail = newNode;
				listSize++;
			}
			return this;
		}
	}

	public SortedLinkedList<T> remove(T target) {
		return (SortedLinkedList<T>) super.remove(target, comparator);

	}

	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
}