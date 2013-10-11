package br.com.atlantic.teste;


public class Printer implements Runnable {

	protected final int MILLIS_PER_PAGE = 500;

	private Queue<PrintJob> queue;
	private String printerName;
	private boolean isPrinting = false;
	private boolean isOn = true;
	private boolean turnoffPending = false;

	public Printer(String name, Queue<PrintJob> queue) {
		this.queue = queue;
		this.printerName = name;
	}

	public String getPrinterName() {
		return printerName;
	}

	@Override
	public void run() {
		synchronized (queue) {
			try {
				while(isOn){
						if (queue.getNumerberOfJobs() == 0) {
							System.out.println("Fila vazia, aguardando trabalho...");
							queue.wait();
						}
						PrintJob printJob = queue.removeFront();
						System.out.println("[" + getPrinterName() + "] Imprimindo: " + printJob.getJobName());
						isPrinting = true;
						Thread.sleep(printJob.getNumberOfPages() * MILLIS_PER_PAGE);
						System.out.println("[" + getPrinterName() + "] " + printJob.getJobName() + " impresso.");
						isPrinting = false;
						if(turnoffPending){
							halt();
						}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}

	public void halt() {
		if(isPrinting){
			System.out.println("Esperando terminar a impressao para desligar.");
			turnoffPending = true;
			return;
		}
		isOn = false;
		System.out.println("Impressora desligada");
	}
	
}