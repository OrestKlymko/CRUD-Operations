package sqlObjects;

public class LongestProject {
	private int id;

	private int mountCount;

	public LongestProject(int id, int mountCount) {
		this.id = id;
		this.mountCount = mountCount;
	}


	@Override
	public String toString() {
		return  id+ ": " +mountCount;
	}
}
