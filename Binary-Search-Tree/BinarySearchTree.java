public class BinarySearchTree implements BSTInterface {

	protected BSTNode root;

	BinarySearchTree() {
		root = null;
	}

	protected class BSTNode {
		protected String item = "";
		protected BSTNode left = null;
		protected BSTNode right = null;

	}

	// TODO: Fill this in and call it from contains()
	protected boolean recursiveSearch(BSTNode node, String s) {
		if (node == null) {
			return false;
		} else if (node.item == s) {
			return true;
		} else if (s.compareTo(node.item) < 0) {
			return recursiveSearch(node.left, s);
		} else if (s.compareTo(node.item) > 0) {
			return recursiveSearch(node.right, s);
		}
		return false;

	}

	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s) {
		if (node == null) {
			BSTNode newNode = new BSTNode();
			newNode.item = s;
			return newNode;
		} else if (node != null) {
			if (s.compareTo(node.item) < 0) {
				node.left = recursiveInsert(node.left, s);

			}
			if (s.compareTo(node.item) > 0) {
				node.right = recursiveInsert(node.right, s);
			}
		}
		return node;

	}

	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {

		if (node != null) {
			if (s.compareTo(node.item) < 0) {
				recursiveRemove(node.left, s);
			}
			if (s.compareTo(node.item) > 0) {
				recursiveInsert(node.right, s);
			}
			if (s == node.item) {
				node = deleteNode(node);
			}
		}
		return node;

	}

	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
		if (node.left == null && node.right == null) {
			node = null;
		} else if (node.left != null) {
			node = node.left;
		} else if (node.right != null) {
			node = node.right;
		} else {
			String smallest = getSmallest(node.right);
			node.item = smallest;
			node.right = recursiveRemove(node.right, smallest);

		}
		return node;

	}

	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
		String smallest = node.item;
		while (node.left != null) {
			smallest = node.left.item;
			node = node.left;
		}
		return smallest;

	}

	// TODO: Fill this in and call it from inOrder()
	protected void inOrderRecursive(BSTNode node, MyQueue queue) {
		if (node != null) {
			inOrderRecursive(node.left, queue);
			queue.enqueue(node.item);
			inOrderRecursive(node.right, queue);
		}
	}

	// TODO: Fill this in and call it from preOrder()
	protected void preOrderRecursive(BSTNode node, MyQueue queue) {
		if (node != null) {
			queue.enqueue(node.item);
			inOrderRecursive(node.left, queue);
			inOrderRecursive(node.right, queue);
		}
	}

	// TODO: Fill this in and call it from postOrder()
	protected void postOrderRecursive(BSTNode node, MyQueue queue) {
		if (node != null) {
			inOrderRecursive(node.left, queue);
			inOrderRecursive(node.right, queue);
			queue.enqueue(node.item);
		}
	}

	// Prints out the tree structure, using indenting to show the different levels
	// of the tree
	public void printTreeStructure() {
		printTree(0, root);
	}

	// Recursive helper for printTreeStructure()
	protected void printTree(int depth, BSTNode node) {
		indent(depth);
		if (node != null) {
			System.out.println(node.item);
			printTree(depth + 1, node.left);
			printTree(depth + 1, node.right);
		} else {
			System.out.println("null");
		}
	}

	// Indents with with spaces
	protected void indent(int depth) {
		for (int i = 0; i < depth; i++)
			System.out.print("  "); // Indents two spaces for each unit of depth
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (root == null) {
			return true;
		}
		return false;
	}

	@Override
	public void makeEmpty() {
		// TODO Auto-generated method stub
		root = null;

	}

	@Override
	public MyQueue inOrder() {
		// TODO Auto-generated method stub
		MyQueue queue = new MyQueue();
		inOrderRecursive(root, queue);
		return queue;
	}

	@Override
	public MyQueue preOrder() {
		// TODO Auto-generated method stub
		MyQueue queue = new MyQueue();
		preOrderRecursive(root, queue);
		return queue;
	}

	@Override
	public MyQueue postOrder() {
		// TODO Auto-generated method stub
		MyQueue queue = new MyQueue();
		postOrderRecursive(root, queue);
		return queue;
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		if (recursiveSearch(root, s) == true) {
			return true;
		}
		return false;
	}

	@Override
	public void put(String s) {
		// TODO Auto-generated method stub
		// BSTNode node = new BSTNode();
		root = recursiveInsert(root, s);

	}

	@Override
	public void delete(String s) {
		// TODO Auto-generated method stub
		root = recursiveRemove(root, s);

	}

	@Override
	public void balanceBST() {
		// TODO Auto-generated method stub
		MyQueue queue = inOrder();
		int count = 0;
		MyQueue tempQueue;
		while (queue != null) {
			tempQueue = (MyQueue) queue.dequeue();
			count++;
		}
		String[] s = new String[count];
		//s = tempQueue;
		queue.dequeueAll();
		root = balanceRecursive(s, 0, s.length-1);

	}

	//
	// // Extra Credit
	//
	// // TODO: If doing the extra credit, fill this in and call it from
	// balanceBST()
	protected BSTNode balanceRecursive(String[] array, int first, int last) {
		
		
		return root;

	}
}