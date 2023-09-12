package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int size;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		this.size = size;
		tail = -1;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null; // Queue is empty
		}
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == size - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
		tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		tail++;
		array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		T removedElement = array[0];
		shiftLeft();
		return removedElement;
	}
}
