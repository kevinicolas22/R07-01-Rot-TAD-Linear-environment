package adt.queue;

import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;
	public Queue<Integer> queue4;

	@Before
	public void setUp() throws QueueOverflowException, StackUnderflowException, StackOverflowException {

		getImplementations();

		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		queue2.enqueue(1);
		queue2.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueImpl<>(4);
		queue2 = new QueueImpl<>(2);
		queue3 = new QueueImpl<>(3); // vazia
		queue4 = new QueueImpl<>(1); // vazia
	}

	@Test
	public void testHead() {
		assertEquals(Integer.valueOf(1), queue1.head());
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
			queue1.enqueue(5);
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException, StackUnderflowException, StackOverflowException {
		queue2.enqueue(5); // vai depender do tamanho que a fila
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(Integer.valueOf(1), queue1.dequeue());
		} catch (QueueUnderflowException | StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException, StackUnderflowException {
		assertEquals(Integer.valueOf(1), queue3.dequeue());
	}

	@Test
	public void testFilaEnqueue () throws QueueOverflowException, StackUnderflowException, StackOverflowException {
		assertTrue(queue3.isEmpty());
		assertFalse(queue3.isFull());

		queue3.enqueue(1);

		assertFalse(queue3.isEmpty());
		assertFalse(queue3.isFull());
		assertEquals(Integer.valueOf(1), queue3.head());

		queue3.enqueue(2);
		queue3.enqueue(3);

		assertFalse(queue3.isEmpty());
		assertTrue(queue3.isFull());
		assertEquals(Integer.valueOf(1), queue3.head());
	}

	@Test(expected = QueueOverflowException.class)
	public void testFilaCheiaEnqueueError () throws QueueOverflowException, StackUnderflowException, StackOverflowException {
		queue4.enqueue(1);
		queue4.enqueue(2);
	}

	@Test
	public void testFilaDequeue () throws QueueOverflowException, QueueUnderflowException, StackUnderflowException, StackOverflowException {
		assertTrue(queue3.isEmpty());
		assertFalse(queue3.isFull());

		queue3.enqueue(1);
		queue3.enqueue(2);
		queue3.enqueue(3);

		assertFalse(queue3.isEmpty());
		assertTrue(queue3.isFull());

		assertEquals(Integer.valueOf(1), queue3.dequeue());

		assertFalse(queue3.isEmpty());
		assertFalse(queue3.isFull());
		assertEquals(Integer.valueOf(2), queue3.head());

		assertEquals(Integer.valueOf(2), queue3.dequeue());
		assertEquals(Integer.valueOf(3), queue3.dequeue());
		assertNull(queue3.head());

		assertTrue(queue3.isEmpty());
		assertFalse(queue3.isFull());
	}

	@Test(expected = QueueUnderflowException.class)
	public void testFilaVaziaDequeueError () throws QueueUnderflowException, StackUnderflowException {
		queue3.dequeue();
	}

}