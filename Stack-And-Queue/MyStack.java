public class MyStack implements StackInterface {

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(Object o) {
		list.add(0, o);

	}

	@Override
	public Object pop() throws StackException {
		if (!isEmpty()) {
			Object stackFront = list.get(0);
			list.remove(0);
			return stackFront;

		} else {
			throw new StackException("The stack is empty");
		}

	}

	@Override
	public Object peek() throws StackException {
		if (list.isEmpty())
			throw new StackException("The stack is empty");
		else
			return list.get(0);
	}

	@Override
	public void popAll() {
		list.removeAll();

	}

	public String toString() {
		return list.toString();
	}

	/*
	 * TODO 1: Implement "MyStack"
	 */
	private MyLinkedList list;

	public MyStack() {
		list = new MyLinkedList();

	}

	/*
	 * TODO 1: Implement "MyStack"
	 */
}