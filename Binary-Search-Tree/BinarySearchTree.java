public class BinarySearchTree implements BSTInterface {

	private BSTNode root;

	//constructor that sets root to null
	BinarySearchTree() {
		root = null;
	}

	//inner class
	protected class BSTNode {
		protected String item = "";
		protected BSTNode left = null;
		protected BSTNode right = null;
		
		//constructor for BSTNode
		BSTNode(String item) {
			this.item = item;
			this.left = null;
			this.right = null;
		}

	}

	// searches the subtree rooted at node for the String s
	// returns true if it finds it and false otherwise.
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
	// Return the node, returns the subtree rooted at node into which string is inserted
	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s) {
		if (node == null) {
			BSTNode newNode = new BSTNode(s);
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

	// Finds the string to delete and returns the node
	// this is the modified subtree after removing the string 
	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {

		if (node != null) {
			if (s.compareTo(node.item) < 0) {
				node.left = recursiveRemove(node.left, s);
			}
			if (s.compareTo(node.item) > 0) {
				node.right = recursiveRemove(node.right, s);
			}
			if (s == node.item) {
				node = deleteNode(node);
			}
		}
		return node;

	}

	// deletes the node and returns the deleted node
	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
		if (node.left == null && node.right == null) {
			node = null;
		} else if (node.left != null && node.right == null) {
			node = node.left;
		} else if (node.right != null && node.left == null) {
			node = node.right;
		} else {
			String smallest = getSmallest(node.right);
			node.item = smallest;
			node.right = recursiveRemove(node.right, smallest);

		}
		return node;

	}

    //finds the smallest string in the subtree rooted at node
	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
		String smallest = node.item;
		while (node.left != null) {
			smallest = node.left.item;
			node = node.left;
		}
		return smallest;

	}

	// goes over the bst in inOrder traversal
	// TODO: Fill this in and call it from inOrder()
	protected MyQueue inOrderRecursive(BSTNode node, MyQueue queue) {
		if (node != null) {
			queue = inOrderRecursive(node.left, queue);
			queue.enqueue(node.item);
			queue = inOrderRecursive(node.right, queue);
		}
		return queue;
	}

	// goes over the bst in pre-order traversal
	// TODO: Fill this in and call it from preOrder()
	protected MyQueue preOrderRecursive(BSTNode node, MyQueue queue) {
		if (node != null) {
			queue.enqueue(node.item);
			queue = preOrderRecursive(node.left, queue);
			queue = preOrderRecursive(node.right, queue);
		}
		return queue;
	}

	// goes over the bst in postOrder traversal
	// TODO: Fill this in and call it from postOrder()
	protected MyQueue postOrderRecursive(BSTNode node, MyQueue queue) {
		if (node != null) {
			queue = postOrderRecursive(node.left, queue);
			queue = postOrderRecursive(node.right, queue);
			queue.enqueue(node.item);
		}
		return queue;
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
	
	// Returns true if BST is empty, false otherwise
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (root == null) {
			return true;
		}
		return false;
	}

	// Clears BST so that it is empty
	@Override
	public void makeEmpty() {
		// TODO Auto-generated method stub
		root = null;

	}

	// Returns a queue with the elements in-order
	@Override
	public MyQueue inOrder() {
		// TODO Auto-generated method stub
		MyQueue queue = new MyQueue();
		inOrderRecursive(root, queue);
		return queue;
	}

	// Returns a queue with the elements in pre-order
	@Override
	public MyQueue preOrder() {
		// TODO Auto-generated method stub
		MyQueue queue = new MyQueue();
		preOrderRecursive(root, queue);
		return queue;
	}

	// Returns a queue with the elements in post-order
	@Override
	public MyQueue postOrder() {
		// TODO Auto-generated method stub
		MyQueue queue = new MyQueue();
		postOrderRecursive(root, queue);
		return queue;
	}

	// Returns true if the BST contains the string, otherwise returns false
	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		if (recursiveSearch(root, s) == true) {
			return true;
		}
		return false;
	}

	// Sets root to recuriveInsert. If the string is already in the BST, then put will do nothing
	// If the string is not in the BST, then put will add the string to the BST.
	@Override
	public void put(String s) {
		// TODO Auto-generated method stub
		// BSTNode node = new BSTNode();
		root = recursiveInsert(root, s);

	}

	// Sets root to recursiveRemove. Removes the specified string from the BST, if the string cannot be found, then
	// delete does nothing
	@Override
	public void delete(String s) {
		// TODO Auto-generated method stub
		root = recursiveRemove(root, s);

	}

	// balances an unbalanced BST
	@Override
	public void balanceBST() {
		// TODO Auto-generated method stub
		MyQueue queue = inOrder();
		int count = 0;
		MyQueue tempQueue = new MyQueue();
		while (!queue.isEmpty()) {
			tempQueue.enqueue((String) queue.dequeue());
			count++;
		}
		String[] s = new String[count];
		int i = 0;
		while (!tempQueue.isEmpty()) {
			s[i++] = (String) tempQueue.dequeue();
		}
		// s = tempQueue;
		this.makeEmpty();
		root = balanceRecursive(s, 0, s.length - 1);

	}

	// makes a new node at center of sorted array and sets right and left child recursively to balance the BST
	// // Extra Credit
	//
	// // TODO: If doing the extra credit, fill this in and call it from
	// balanceBST()
	protected BSTNode balanceRecursive(String[] array, int first, int last) {
		if (first > last) {
			return null;
		}
		int mid = (first + last) / 2;
		BSTNode n = new BSTNode(array[mid]);
		n.left = balanceRecursive(array, first, mid - 1);
		n.right = balanceRecursive(array, mid + 1, last);
		return n;

	}
}