
public class Maze {

//	private ArrayList<int[]> path;
	private int[] initialLocationCoordinates; // The initial state that the agent starts in
	private int[] targetLocationCoordinates; // goal
	boolean[][] matrix;
	private int Height;
	private int Width;

	public enum Actions { // A description of the possible actions available to the agent.
		Up, Down, Right, Left
	}

	public Maze(int[] initialLocationCoordinates, int[] targetLocationCoordinates, boolean[][] matrix, int Height,
			int Width) {
		super();
		if (!matrix[initialLocationCoordinates[0]][initialLocationCoordinates[1]]) {
			throw new Error("Can't start from a black block");
		}

		if (!matrix[targetLocationCoordinates[0]][targetLocationCoordinates[1]]) {
			throw new Error("Goal can't be a black block");
		}

		this.initialLocationCoordinates = initialLocationCoordinates;
		this.targetLocationCoordinates = targetLocationCoordinates;
		this.matrix = matrix;
		this.Height = Height;
		this.Width = Width;
	}

	public boolean goalTest(int[] state) {
		return (state[0] == this.targetLocationCoordinates[0]) && (state[1] == this.targetLocationCoordinates[1]);
	}

	public int getHeight() {
		return this.Height;
	}

	public int getWidth() {
		return this.Width;
	}

//	public int pathCost() {
//		return this.path.size();
//	}

	public int[] result(int[] oldState, Actions action, boolean[][] matrix) {

		int y = oldState[0];
		int x = oldState[1];
		int[] state = new int[2];
		state = oldState;

		switch (action) {
		case Up:
			if (matrix[y + 1][x]) {
				state[0] += 1;

			}
			System.out.println("Up");

			return state;

		case Down:
			if ((y - 1 >= 0) && matrix[y - 1][x]) {
				state[0] -= 1;

			}
			System.out.println("Down");

			return state;

		case Right:
			if (matrix[y][x + 1]) {
				state[1] += 1;
			}
			System.out.println("Right");

			return state;

		case Left:
			if ((x - 1 >= 0) && matrix[y][x - 1]) {
				state[1] -= 1;
			}
			System.out.println("Left");

			return state;

		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	public boolean[][] getMatrix() {
		return this.matrix;
	}

	public int[] getInitialLocationCoordinates() {
		return initialLocationCoordinates;
	}

	public int[] getTargetLocationCoordinates() {
		return targetLocationCoordinates;
	}

}
