package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException, StackUnderflowException, StackOverflowException {
		if (stack1.isFull()) {
			throw new QueueOverflowException();
		}

		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}

		stack1.push(element);

		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
	}


	@Override
	public T dequeue() throws QueueUnderflowException, StackUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		return stack1.pop();
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}
		return stack1.top();
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}
}
