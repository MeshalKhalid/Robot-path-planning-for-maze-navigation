import java.awt.Color;
import java.awt.image.BufferedImage;



public class Agent {
	private SearchUnit searchUnit;
	private int[] initialLocationCoordinates; // The initial state that the agent starts in
	private int[] targetLocationCoordinates; // goal
	private BufferedImage environment;
	private Strategies stratigy;

	static public enum Strategies {
		BFS, DFS, GreedySearch, AStar
	}

	public Agent(int[] initialLocationCoordinates, int[] targetLocationCoordinates, BufferedImage environment,Strategies stratigy) {
		super();
		this.initialLocationCoordinates = initialLocationCoordinates;
		this.targetLocationCoordinates = targetLocationCoordinates;
		this.environment = environment;
		this.stratigy = stratigy;
		this.searchUnit = new SearchUnit();
		
	}

	public boolean[][] perceptEnvironment() {
		System.out.println("analyzing the maze...\n\n\n");
		int Height = this.environment.getHeight();
		int Width = this.environment.getWidth();
		printDetails(Height,Width);

		boolean[][] matrix = new boolean[Height][Width];
		for (int i = 0; i < Height; i++) {
			for (int j = 0; j < Width; j++) {
				Color color = new Color(this.environment.getRGB(j, i));
				matrix[i][j] = color.getBlue() == 255 && color.getGreen() == 255 && color.getRed() == 255;
			}

		}

		return matrix;

	}
	
	public void findPath(boolean[][] matrix) {
		int Height = this.environment.getHeight();
		int Width = this.environment.getWidth();
		Maze maze = new Maze(this.initialLocationCoordinates, this.targetLocationCoordinates,matrix,Height,Width);
		searchUnit.breadthFirstSearch(maze);
	}
	
	
	public void printDetails(int Height ,int Width) {
		String MazeDetails = "Maze Height:  " + Height + "	Maze Width:  " + +Width;
		System.out.println("* ".repeat(MazeDetails.length()));
		System.out.println();
		System.out.println(MazeDetails);

		System.out.println();
		System.out.println("* ".repeat(MazeDetails.length()));

	}
	

	public int[] getInitialLocationCoordinates() {
		return initialLocationCoordinates;
	}

	public void setInitialLocationCoordinates(int[] initialLocationCoordinates) {
		this.initialLocationCoordinates = initialLocationCoordinates;
	}

	public int[] getTargetLocationCoordinates() {
		return targetLocationCoordinates;
	}

	public void setTargetLocationCoordinates(int[] targetLocationCoordinates) {
		this.targetLocationCoordinates = targetLocationCoordinates;
	}

	public BufferedImage getEnvironment() {
		return environment;
	}

	public void setEnvironment(BufferedImage environment) {
		this.environment = environment;
	}

	public Strategies getStratigy() {
		return stratigy;
	}

	public void setStratigy(Strategies stratigy) {
		this.stratigy = stratigy;
	}

}
