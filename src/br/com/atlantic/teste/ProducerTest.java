package br.com.atlantic.teste;


public class ProducerTest {
	
	public static void main(String[] args) {
		System.out.println("Iniciando");
		Queue<PrintJob> fila = new CircularQueue<>(100);
		
		Producer producer = new Producer("Producer1", fila);
		Thread thread = new Thread(producer);
		thread.start();
		System.out.println("Finalizado");
	}

}
