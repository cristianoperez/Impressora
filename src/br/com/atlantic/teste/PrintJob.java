package br.com.atlantic.teste;

public class PrintJob {

	private String jobName;
	private int numberOfPages;
	
	public PrintJob(String jobName, int numberOfPages) {
		this.jobName = jobName;
		this.numberOfPages = numberOfPages;
	}

	public String getJobName() {
		return jobName;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	@Override
	public String toString() {
		return "PrintJob [jobName=" + jobName + ", numberOfPages="
				+ numberOfPages + "]";
	}
	
}
