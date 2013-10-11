package br.com.atlantic.teste;

public interface Queue<T> {

	public void addBack(PrintJob job);
	public T removeFront();
	public boolean isEmpty();
	public int getNumerberOfJobs();
	
}
