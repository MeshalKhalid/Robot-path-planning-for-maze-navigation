
public class Node {

	private int[] state;
	private Node parent;
	private int pathCost;

	public Node(int[] state, int pathCost) {
		super();
		this.state = state;
		this.pathCost = pathCost;
		this.parent = null;
	}

	public Node(int[] state, int pathCost, Node parent) {
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

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

}
