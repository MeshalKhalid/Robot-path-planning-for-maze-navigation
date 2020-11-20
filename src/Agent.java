import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Agent {
    private SearchUnit searchUnit;
    private int[] initialLocationCoordinates; // The initial state that the agent starts in
    private int[] targetLocationCoordinates; // goal
    private BufferedImage environment;
    private Strategies strategy;

    public enum Actions { // A description of the possible actions available to the agent.
        Up, Down, Right, Left, UpLeft, UpRight, DownLeft, DownRight
    }

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

    private boolean[][] perceptsEnvironment() {
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

    public ArrayList<Node> findPath() {
        int Height = this.environment.getHeight();
        int Width = this.environment.getWidth();
        Maze maze = new Maze(this.initialLocationCoordinates, this.targetLocationCoordinates, perceptsEnvironment(), Height, Width);
        long start = 0;
        long end = 0;
        switch (this.strategy) {
            case BFS:
                start = System.nanoTime();

                ArrayList<Node> path = searchUnit.breadthFirstSearch(maze);

                end = System.nanoTime();
                printTime(start, end);

                return searchUnit.getFinalPath(path);

            case DFS:
                start = System.nanoTime();

                ArrayList<Node> path1 = searchUnit.depthFirstSearch(maze);
                end = System.nanoTime();
                printTime(start, end);
                return searchUnit.getFinalPath(path1);

            case GreedySearchManhattan:
                start = System.nanoTime();
                ArrayList<Node> path2 = searchUnit.greedyBestFirstSearch(maze, true);
                end = System.nanoTime();
                printTime(start, end);
                return searchUnit.getFinalPath(path2);

            case GreedySearchEuclidean:
                start = System.nanoTime();
                ArrayList<Node> path3 = searchUnit.greedyBestFirstSearch(maze, false);
                end = System.nanoTime();
                printTime(start, end);
                return searchUnit.getFinalPath(path3);

            case AStarManhattan:
                start = System.nanoTime();
                ArrayList<Node> path4 = searchUnit.aStarSearch(maze, true);
                end = System.nanoTime();
                printTime(start, end);
                return searchUnit.getFinalPath(path4);

            case AStarEuclidean:
                start = System.nanoTime();
                ArrayList<Node> path5 = searchUnit.aStarSearch(maze, false);
                end = System.nanoTime();
                printTime(start, end);
                return searchUnit.getFinalPath(path5);

            default:
                throw new Error("option not found");
        }


    }

    private void printTime(long start, long end) {
        System.out.println((end - start) + " nanoseconds");
    }


    private void printDetails(int Height, int Width) {
        String MazeDetails = "Maze Height:  " + Height + "	Maze Width:  " + +Width;
        System.out.println("* ".repeat(MazeDetails.length()));
        System.out.println();
        System.out.println(MazeDetails);

        System.out.println();
        System.out.println("* ".repeat(MazeDetails.length()));

    }


}
