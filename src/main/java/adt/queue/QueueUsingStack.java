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
	public void enqueue(T element) throws QueueOverflowException {
		transferIfNeeded(stack2, stack1);
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		transferIfNeeded(stack1, stack2);
		try {
			return stack2.pop();
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {
		transferIfNeeded(stack1, stack2);
		return stack2.top();
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull() || stack2.isFull();
	}

	private void transferIfNeeded(Stack<T> from, Stack<T> to) {
		if (to.isEmpty()) {
			try {
				while (!from.isEmpty()) {
					to.push(from.pop());
				}
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
	}
}
