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

		// Transfira todos os elementos de stack1 para stack2
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}

		// Empilhe o novo elemento em stack1
		stack1.push(element);

		// Transfira todos os elementos de stack2 de volta para stack1
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
		// A fila com implementação de pilha não tem limite de tamanho.
		return false;
	}
}
