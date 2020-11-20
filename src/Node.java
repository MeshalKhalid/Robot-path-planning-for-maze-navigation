
public class Node {

    private int[] state;
    private Node parent;
    private double heuristicValue;
    private double stepValue = 1;

    public Node(int[] state, double heuristicValue) {
        super();
        this.state = state;
        this.heuristicValue = heuristicValue;
        this.parent = null;
    }

    public Node(int[] state, double heuristicValue, Node parent) {
        super();
        this.state = state;
        this.heuristicValue = heuristicValue;
        this.parent = parent;
    }


    public int[] getState() {
        return state;
    }

    public double getStepValue() {
        return stepValue;
    }

    public Node getParent() {
        return parent;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public double getHeuristicValue() {
        return heuristicValue;
    }

    public void setPathCost(int pathCost) {
        this.heuristicValue = pathCost;
    }

}
