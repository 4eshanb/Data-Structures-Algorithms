class Node {
	public Object item;
	public Node next;

	Node(Object newItem) {
		item = newItem;
		next = null;
	}
}

public class MyLinkedList implements ListInterface {
	protected Node head;
	protected int numItems;

	public MyLinkedList() {
		numItems = 0;
		head = null;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public void add(int index, Object value) throws ListIndexOutOfBoundsException {

		if (index < 0) {
			throw new ListIndexOutOfBoundsException("List index out of bounds on add");
		}
		Node newNode = new Node(value);
		if (numItems == 0) {
			head = newNode;
			numItems++;
		} else {
			Node curr = head;

			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}
			// Node a = (Node)get(index);
			newNode.next = curr.next;
			curr.next = newNode;
			numItems++;
		}
	}

	@Override
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index < 0) {
			throw new ListIndexOutOfBoundsException("List index out of bounds on remove");
		}
		if (index == 0) {
			head = head.next;
			numItems--;
			return;
		}
		if (index == numItems - 1) {
			Node prev = getNode(index - 1);
			prev.next = null;
		} else {
			Node toRemove = getNode(index);
			Node prev = getNode(index - 1);
			prev.next = toRemove.next;
		}

		numItems--;

	}

	@Override
	public void removeAll() {
		head = null;
		numItems = 0;

	}

	@Override
	public Object get(int index) throws ListIndexOutOfBoundsException {
		if (index < 0 || index > numItems) {
			throw new ListIndexOutOfBoundsException("List index out of bounds");
		}
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.item;
	}

	protected Node getNode(int index) throws ListIndexOutOfBoundsException {
		if (index < 0 || index > numItems) {
			throw new ListIndexOutOfBoundsException("List index out of bounds");
		}
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	@Override
	public int find(Object o) {
		if (head == null) {
			return -1;
		}
		Node curr = head;

		if (curr.item instanceof RhymeGroupWords) {
			return findInRymeGroups(o);
		} else {
			int index = 0;
			while (curr != null) {
				if (((String) curr.item).equals(o)) {
					return index;
				} else {
					curr = curr.next;
				}
				index++;
			}
		}
		return -1;

	}

	private int findInRymeGroups(Object o) {
		Node curr = head;
		int index = 0;
		while (curr != null) {
			if (((RhymeGroupWords) curr.item).getRhymeGroup().equalsIgnoreCase((String) o)) {
				return index;
			} else {
				curr = curr.next;
			}
			index++;
		}
		return -1;

	}

	@Override
	public String toString() {
		String tmp = "";
		Node cur = head;

		for (int i = 0; i < numItems; i++) {
			if (i == 0) {
				tmp = "(";
				tmp += cur.item;
				cur = cur.next;
			} else {
				tmp += ",";
				tmp += cur.item;
				cur = cur.next;
			}
		}
		return tmp + ")";
	}
	/*
	 * TODO: Write a LinkedList implementation for all the methods specified in
	 * ListInterface
	 */

}