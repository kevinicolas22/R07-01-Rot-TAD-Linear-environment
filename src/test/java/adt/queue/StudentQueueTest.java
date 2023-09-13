package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException, StackUnderflowException, StackOverflowException {
		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);
	}

	private void getImplementations() {
		// Instancie sua implementação de fila usando pilhas aqui
		queue1 = new QueueUsingStack<>(6); 
		queue2 = new QueueUsingStack<>(2); 
		queue3 = new QueueUsingStack<>(5); 
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(new Integer(1), queue1.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testEnqueueComErro() throws StackOverflowException, StackUnderflowException, QueueOverflowException {
		queue1.enqueue(new Integer(5)); // Isso deve lançar uma StackOverflowException
	}



	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueUnderflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException, StackUnderflowException {
		assertEquals(new Integer(1), queue1.dequeue()); // Isso deve lançar uma QueueUnderflowException
	}
}
