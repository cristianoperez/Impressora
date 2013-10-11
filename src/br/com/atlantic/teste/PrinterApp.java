package br.com.atlantic.teste;

public class PrinterApp {
	
	public static void main(String[] args) throws InterruptedException {

		Queue<PrintJob> queue = new CircularQueue<>(20);

		Producer producer1 = new Producer("Producer 1", queue);
		Producer producer2 = new Producer("Producer2", queue);

		Thread producer1Thread = new Thread(producer1);
		Thread producer2Thread = new Thread(producer2);

		producer1Thread.start();
		producer2Thread.start();

		Printer printer = new Printer("Printer 1", queue);
		Thread printerThread = new Thread(printer);
		printerThread.start();

	}
	
}
