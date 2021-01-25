public class MyQueue implements QueueInterface {

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(Object item) {
		list.add(list.size(), item);
	}

	@Override
	public Object dequeue() throws QueueException {
		if (!isEmpty()) {
			Object queueFront = list.get(0);
			list.remove(0);
			return queueFront;

		} else {
			throw new QueueException("Queue is empty");
		}

	}

	@Override
	public void dequeueAll() {
		list.removeAll();
	}

	@Override
	public Object peek() throws QueueException {
		if (list.isEmpty()) {
			throw new QueueException("Queue is empty");
		}
		return list.get(0);

	}

	public String toString() {
		return list.toString();
	}

	/*
	 * TODO 2: Implement "MyQueue"
	 */
	private MyLinkedList list;

	public MyQueue() {
		list = new MyLinkedList();
	}

	/*
	 * TODO 2: Implement "MyQueue"
	 */

}