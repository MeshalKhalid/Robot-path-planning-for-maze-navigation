
public class Node {

	private int[] state;
	private int pathCost;

	public Node(int[] state, int pathCost) {
		super();
		this.state = state;
		this.pathCost = pathCost;
	}
	


	public int[] getState() {
		return state;
	}

	public void setState(int[] state) {
		this.state = state;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

}
