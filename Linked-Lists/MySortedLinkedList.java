
public class MySortedLinkedList extends MyLinkedList {

	/*
	 * TODO define the method public void add(Comparable c) that, given a Comparable
	 * (an interface type for all Object subclasses that define a compareTo()
	 * method), adds it to the list in sorted order.
	 */
	public void add(Comparable c) {
		MyWord a = (MyWord) c;
		Node n = new Node(a.getStr());
		if (numItems == 0) {
			head = n;
			numItems++;
		} else {
			boolean inserted = false;
			int size = numItems;
			for (int i = 0; i < size; i++) {
				Node curr = (Node) this.getNode(i);
				if (a.compareTo(curr.item) <= 0) {
					if (i == 0) {
						n.next = curr;
						head = n;

					} else {
						Node prev = (Node) this.getNode(i - 1);
						n.next = prev.next;
						prev.next = n;
					}
					inserted = true;
					numItems++;
					break;
				}
			}
			if (inserted == false) {
				Node last = (Node) this.getNode(size() - 1);
				last.next = n;
				n.next = null;
				numItems++;
			}
		}

	}

	/*
	 * TODO override the method void add(int index, Object o) so that it throws an
	 * UnsupportedOperationException with the message
	 * "Do not call add(int, Object) on MySortedLinkedList". Directly adding objects
	 * at an index would mess up the sorted order.
	 */
	public void add(int index, Object o) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Do not call add(int, Object) on MySortedLinkedList");
	}
}

class MyWord implements Comparable {

	private String str;

	public String getStr() {
		return str;
	}

	public MyWord(String word) {
		this.str = word;
	}

	@Override
	public int compareTo(Object o) {
		String oo = (String) o;
		if (this.str.compareTo(oo) < 0)
			return -1;
		if (this.str.compareTo(oo) == 0)
			return 0;
		return 1;

	}

}
