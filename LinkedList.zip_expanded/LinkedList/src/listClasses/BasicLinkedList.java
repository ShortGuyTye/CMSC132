package listClasses;

import java.util.*;

public class BasicLinkedList<T> implements Iterable<T> {

	/* Node definition */
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* We have both head and tail */
	protected Node head, tail;

	/* size */
	protected int listSize;

	public BasicLinkedList() {
		head = null;
		tail = null;
	}

	public int getSize() {
		return listSize;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		if (data == null) {
			return this;
		}
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
			listSize++;
			return this;
		}
		tail.next = newNode;
		tail = newNode;
		listSize++;
		return this;
	}

	public BasicLinkedList<T> addToFront(T data) {
		if (data == null) {
			return this;
		}
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
			listSize++;
			return this;
		}
		newNode.next = head;
		head = newNode;
		listSize++;
		return this;
	}

	public T getFirst() {
		if (head == null) {
			return null;
		}
		return head.data;
	}

	public T getLast() {
		if (head == null) {
			return null;
		}
		return tail.data;
	}

	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		Node temp = head;
		head = head.next;
		listSize--;
		return temp.data;
	}

	public T retrieveLastElement() {
		if (head == null) {
			return null;
		}
		Node temp = tail;
		Node curr = head;
		while (curr.next != tail && curr.next != null) {
			curr = curr.next;
		}
		curr.next = null;
		tail = curr;
		listSize--;
		return temp.data;
	}

	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		if (targetData == null) {
			return this;
		}
		if (head == null) {
			return this;
		}
		Node curr = head;
		if (curr == head) {
			if (comparator.compare(curr.data, targetData) == 0) {
				if (head.next == null) {
					head = null;
					listSize--;
					return this;
				} else {
					head = head.next;
					listSize--;
					return this;
				}
			}
		}
		while (curr.next != null) {
			if (comparator.compare(curr.next.data, targetData) == 0) {
				if (curr.next.next != null) {
					curr.next = curr.next.next;
					listSize--;
				} else {
					listSize--;
					curr.next = null;
					tail = curr;
				}
			} else {
				curr = curr.next;
			}
		}
		return this;
	}

	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			Node curr = null;

			public boolean hasNext() {
				if (head == null) {
					return false;
				} else if (curr == null && head != null) {
					return true;
				} else if (curr != null) {
					if (curr.next != null) {
						return true;
					}
				}
				return false;
			}

			public T next() {
				if (curr == null) {
					curr = head;
					return head.data;
				} else {
					curr = curr.next;
					return curr.data;
				}
			}
		};
		return it;
	}

	public ArrayList<T> getReverseArrayList() {
		ArrayList<T> reverse = new ArrayList<>();
		if (head == null) {
			return reverse;
		}
		return getReverseArrayListAux(reverse);
	}

	private ArrayList<T> getReverseArrayListAux(ArrayList<T> reverse) {
		if (head.next == null) {
			reverse.add(head.data);
			return reverse;
		} else {
			reverse.add(retrieveLastElement());
			return getReverseArrayListAux(reverse);
		}

	}

	public BasicLinkedList<T> getReverseList() {
		if (head == null) {
			BasicLinkedList<T> newList = new BasicLinkedList<T>();
			return newList;
		}
		Node curr = head, newHead = null, newTail = new Node(head.data);
		return getReverseListAux(curr, newHead, newTail);

	}

	private BasicLinkedList<T> getReverseListAux(Node curr, Node newHead,
			Node newTail) {
		BasicLinkedList<T> newList = new BasicLinkedList<T>();
		if (curr == tail) {
			Node temp = newHead;
			newHead = new Node(curr.data);
			newHead.next = temp;
			newList.head = newHead;
			newList.tail = newTail;
			newList.listSize = listSize;
			return newList;
		} else if (curr == head) {
			newHead = newTail;
			return getReverseListAux(curr.next, newHead, newTail);
		} else {
			Node newNode = new Node(curr.data);
			newNode.next = newHead;
			newHead = newNode;
			return getReverseListAux(curr.next, newHead, newTail);
		}
	}
}