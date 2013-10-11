package br.com.atlantic.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
		
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
				List<PrintJob> jobs = getJobs();
				Random random = new Random();
				for (PrintJob job : jobs) {
					Thread.sleep(random.nextInt(5) * 1000);
					queue.addBack(job);
					System.out.println("[" + getProducerName() + "] Produzindo arquivo: " + job.getJobName() + ", numero de paginas: " + job.getNumberOfPages());
					queue.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getProducerName() {
		return producerName;
	}
	
	public List<PrintJob> getJobs(){
	
		List<PrintJob> jobs = new ArrayList<>();
		
		jobs.add(new PrintJob("proposta.txt", 3));
		jobs.add(new PrintJob("notas.doc", 1));
		jobs.add(new PrintJob("estrutura.xml", 2));
		jobs.add(new PrintJob("especificacao.doc", 4));
		jobs.add(new PrintJob("relatorio.doc", 5));
		jobs.add(new PrintJob("anotacoes.txt", 7));
		jobs.add(new PrintJob("users.json", 4));
		jobs.add(new PrintJob("producao.txt", 9));
		jobs.add(new PrintJob("tabelas.doc", 1));
		jobs.add(new PrintJob("lembretes.txt", 3));
		
		return jobs;
	}
	
}
