package sqlObjects;

public class WorkerAndSalary {
	private String workerName;
	private int salary;

	@Override
	public String toString() {
		return  workerName +": "+salary;
	}

	public WorkerAndSalary(String workerName, int salary) {
		this.workerName = workerName;
		this.salary = salary;
	}
}
