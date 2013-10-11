package br.com.atlantic.teste;

public class CircularQueue<T> implements Queue<T> {

	private int size = 0;
	private Object[] jobs;
	
	public CircularQueue(int capacity) {
		
		if(capacity < 0){
			throw new QueueException("Capacidade da fila de ser maior que 0.");
		}

		jobs = new Object[capacity];
		
	}

	@Override
	public void addBack(PrintJob job) {
		checkCapacity();
		jobs[size] = job;
		size = size + 1;
	} 

	@SuppressWarnings("unchecked")
	@Override
	public T removeFront() {
		T obj = null;
		if(jobs.length > 0){
			obj = (T) jobs[0];
			jobs[0] = null;
		}
		
		for (int i = 0; i < size; i++) {
			if(jobs[i] ==  null){
				if(i + 1 <= size - 1){
					jobs[i] = jobs[i + 1];
					jobs[i + 1] = null;
				}
			}
		}
		
		if(size > 0){
			size = size-1;
		}
		
		return obj;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int getNumerberOfJobs() {
		return size;
	}

	private boolean checkCapacity(){
		int newSize = size + 1;
		if(newSize > jobs.length){
			throw new QueueException("Capacidade da fila atingida.");
		}
		return true;
	}
}
