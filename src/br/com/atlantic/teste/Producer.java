package br.com.atlantic.teste;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
		
	private final String PRODUCER_PATH = "C:/producer.txt";
	private Queue<PrintJob> queue;
	private String producerName;
	
	public Producer(String name, Queue<PrintJob> queue) {
		this.queue = queue;
		this.producerName = name;
	}

	@Override
	public void run() {
		synchronized (queue) {
			try {
				Path path = Paths.get(PRODUCER_PATH);
				List<String> readAllLines = Files.readAllLines(path, StandardCharsets.UTF_8);
				Random random = new Random();
				for (String string : readAllLines) {
					if(string != null && !string.isEmpty()){
						String[] split = string.split("/");
						PrintJob printJob = new PrintJob(split[0], Integer.parseInt(split[1]));
						Thread.sleep(random.nextInt(5) * 1000);
						queue.addBack(printJob);
						System.out.println("[" + getProducerName() + "] Produzindo arquivo: " + printJob.getJobName() + ", numero de paginas: " + printJob.getNumberOfPages());
						queue.notify();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getProducerName() {
		return producerName;
	}
	
	
}
