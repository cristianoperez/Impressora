package br.com.atlantic.teste;

import org.junit.Assert;
import org.junit.Test;

public class CircularQueueTest {

	@Test
	public void mustBeEmpty(){
		Queue<PrintJob> queue = new CircularQueue<PrintJob>(10);
		Assert.assertTrue(queue.isEmpty());
	}
	
	@Test
	public void addJob(){
		Queue<PrintJob> queue = new CircularQueue<PrintJob>(10);
		queue.addBack(new PrintJob("Notas.doc", 2));
		Assert.assertTrue(queue.getNumerberOfJobs() == 1);
	}
	
	@Test(expected = QueueException.class)
	public void addJobOvercapacity(){
		Queue<PrintJob> queue = new CircularQueue<PrintJob>(1);
		queue.addBack(new PrintJob("Notas.doc", 2));
		queue.addBack(new PrintJob("Documentos.doc", 2));
	}
	
	@Test
	public void removeFront(){
		Queue<PrintJob> queue = new CircularQueue<PrintJob>(4);
		queue.addBack(new PrintJob("Notas.doc", 2));
		queue.addBack(new PrintJob("Documentos.doc", 2));
		PrintJob removeFront = queue.removeFront();
		
		Assert.assertTrue(removeFront.getNumberOfPages() == 2);
		Assert.assertTrue(queue.getNumerberOfJobs() == 1);
	}
}
