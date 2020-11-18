import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Agent {
    private SearchUnit searchUnit;
    private int[] initialLocationCoordinates; // The initial state that the agent starts in
    private int[] targetLocationCoordinates; // goal
    private BufferedImage environment;
    private Strategies strategy;

     public enum Strategies {
         BFS, DFS, GreedySearchManhattan, GreedySearchEuclidean, AStarManhattan, AStarEuclidean
     }

    public Agent(int[] initialLocationCoordinates, int[] targetLocationCoordinates, BufferedImage environment, Strategies strategy) {
        super();
        this.initialLocationCoordinates = initialLocationCoordinates;
        this.targetLocationCoordinates = targetLocationCoordinates;
        this.environment = environment;
        this.strategy = strategy;
        this.searchUnit = new SearchUnit();

    }

    public boolean[][] perceptsEnvironment() {
        System.out.println("analyzing the maze...\n\n\n");
        int Height = this.environment.getHeight();
        int Width = this.environment.getWidth();
        printDetails(Height, Width);

        boolean[][] matrix = new boolean[Height][Width];
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < Width; j++) {
                Color color = new Color(this.environment.getRGB(j, i));
                matrix[i][j] = color.getBlue() == 255 && color.getGreen() == 255 && color.getRed() == 255;
            }

        }

        return matrix;

    }

    public ArrayList<Node> findPath(boolean[][] matrix) {
        int Height = this.environment.getHeight();
        int Width = this.environment.getWidth();
        Maze maze = new Maze(this.initialLocationCoordinates, this.targetLocationCoordinates, matrix, Height, Width);

        switch (this.strategy) {
            case BFS:
                return searchUnit.getFinalPath(searchUnit.breadthFirstSearch(maze));

            case DFS:
                return searchUnit.getFinalPath(searchUnit.depthFirstSearch(maze));

            case GreedySearchManhattan:
                return searchUnit.getFinalPath(searchUnit.greedyBestFirstSearch(maze, true));

            case GreedySearchEuclidean:
                return searchUnit.getFinalPath(searchUnit.greedyBestFirstSearch(maze, false));

            case AStarManhattan:
                return searchUnit.getFinalPath(searchUnit.aStarSearch(maze, true));

            case AStarEuclidean:
                return searchUnit.getFinalPath(searchUnit.aStarSearch(maze, false));

            default:
                throw new Error("option not found");
        }


    }


    public void printDetails(int Height, int Width) {
        String MazeDetails = "Maze Height:  " + Height + "	Maze Width:  " + +Width;
        System.out.println("* ".repeat(MazeDetails.length()));
        System.out.println();
        System.out.println(MazeDetails);

        System.out.println();
        System.out.println("* ".repeat(MazeDetails.length()));

    }

    public enum Actions { // A description of the possible actions available to the agent.
        Up, Down, Right, Left, UpLeft, UpRight, DownLeft, DownRight
    }
}
