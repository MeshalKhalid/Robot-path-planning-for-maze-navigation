
public class Node {

	private int[] state;
	private Node parent;
	private double pathCost;

	public Node(int[] state, double pathCost) {
		super();
		this.state = state;
		this.pathCost = pathCost;
		this.parent = null;
	}

	public Node(int[] state, double pathCost, Node parent) {
		super();
		this.state = state;
		this.pathCost = pathCost;
		this.parent = parent;
	}


	public int[] getState() {
		return state;
	}

	public Node getParent() {
		return parent;
	}

	public void setState(int[] state) {
		this.state = state;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

}
