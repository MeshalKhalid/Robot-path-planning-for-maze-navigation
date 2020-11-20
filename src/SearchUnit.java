import java.util.*;


class NodeComparator implements Comparator<Node> { // used to sort nodes based on path cost;

    public int compare(Node n1, Node n2) {
        if (n1.getPathCost() < n2.getPathCost())
            return -1;
        else if (n1.getPathCost() > n2.getPathCost())
            return 1;
        return 0;
    }

}


public class SearchUnit {

    static int pathCost = 0;
    static int nodesExpanded = 0;
    static int frontierMaximumSize = 0;


    public ArrayList<Node> breadthFirstSearch(Maze problem) {


        Node node = new Node(problem.getInitialLocationCoordinates(), 0);

        ArrayList<Node> solution = new ArrayList<>();
        Queue<Node> frontier = new LinkedList<>();
        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);


            return solution;
        }

        frontier.add(node);
        getFrontierMaximumSize(frontier.size());

        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.poll();
            nodesExpanded++;
            for (Agent.Actions action : Agent.Actions.values()) {

                int[] nodeCoordinates = {node.getState()[0], node.getState()[1]};
                int[] state = problem.result(nodeCoordinates, action, problem.matrix);
                solution.add(node);


                if (!explored[state[0]][state[1]]) {
                    explored[state[0]][state[1]] = true;

                    if (problem.goalTest(state)) {

                        return solution;
                    } else {
                        frontier.add(new Node(state, 0, node));
                        getFrontierMaximumSize(frontier.size());
                    }
                }

            }

        }

    }


    public ArrayList<Node> depthFirstSearch(Maze problem) {


        Node node = new Node(problem.getInitialLocationCoordinates(), 0);

        ArrayList<Node> solution = new ArrayList<>();
        Stack<Node> frontier = new Stack<>();

        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);


            return solution;
        }

        frontier.add(node);
        getFrontierMaximumSize(frontier.size());

        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.pop();
            nodesExpanded++;

            for (Agent.Actions action : Agent.Actions.values()) {

                int[] nodeCoordinates = {node.getState()[0], node.getState()[1]};
                int[] state = problem.result(nodeCoordinates, action, problem.matrix);


                if (!explored[state[0]][state[1]]) {
                    solution.add(node);
                    explored[state[0]][state[1]] = true;

                    if (problem.goalTest(state)) {


                        return solution;
                    } else {
                        frontier.push(new Node(state, 0, node));
                        getFrontierMaximumSize(frontier.size());
                    }
                }

            }
        }

    }


    public ArrayList<Node> greedyBestFirstSearch(Maze problem, boolean isManhattan) {
        double pathCost = getHeuristic(
                problem.getInitialLocationCoordinates(),
                problem.getTargetLocationCoordinates(),
                problem.getTargetLocationCoordinates(),
                isManhattan ? Agent.Strategies.GreedySearchManhattan : Agent.Strategies.GreedySearchEuclidean);


        Node node = new Node(problem.getInitialLocationCoordinates(), pathCost);

        ArrayList<Node> solution = new ArrayList<>();
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> frontier = new PriorityQueue<>(1, comparator);
        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);


            return solution;
        }

        frontier.add(node);
        getFrontierMaximumSize(frontier.size());

        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.poll();
            nodesExpanded++;

            for (Agent.Actions action : Agent.Actions.values()) {

                int[] nodeCoordinates = {node.getState()[0], node.getState()[1]};
                int[] state = problem.result(nodeCoordinates, action, problem.matrix);
                solution.add(node);


                if (!explored[state[0]][state[1]]) {
                    explored[state[0]][state[1]] = true;

                    if (problem.goalTest(state)) {
                        return solution;
                    } else {
                        double cost = getHeuristic(
                                problem.getInitialLocationCoordinates(),
                                problem.getTargetLocationCoordinates(),
                                node.getState(),
                                isManhattan ? Agent.Strategies.GreedySearchManhattan : Agent.Strategies.GreedySearchEuclidean);
                        frontier.add(new Node(state, cost, node));
                        getFrontierMaximumSize(frontier.size());
                    }
                }

            }

        }

    }

    public ArrayList<Node> aStarSearch(Maze problem, boolean isManhattan) {
        double pathCost = getHeuristic(
                problem.getInitialLocationCoordinates(),
                problem.getTargetLocationCoordinates(),
                problem.getTargetLocationCoordinates(),
                isManhattan ? Agent.Strategies.AStarManhattan : Agent.Strategies.AStarEuclidean);


        Node node = new Node(problem.getInitialLocationCoordinates(), pathCost);

        ArrayList<Node> solution = new ArrayList<>();
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> frontier = new PriorityQueue<>(comparator);
        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);

            return solution;
        }

        frontier.add(node);
        getFrontierMaximumSize(frontier.size());

        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.poll();
            nodesExpanded++;

            for (Agent.Actions action : Agent.Actions.values()) {

                int[] nodeCoordinates = {node.getState()[0], node.getState()[1]};
                int[] state = problem.result(nodeCoordinates, action, problem.matrix);
                solution.add(node);


                if (!explored[state[0]][state[1]]) {
                    explored[state[0]][state[1]] = true;

                    if (problem.goalTest(state)) {

                        return solution;
                    } else {
                        double cost = getHeuristic(
                                problem.getInitialLocationCoordinates(),
                                problem.getTargetLocationCoordinates(),
                                node.getState(),
                                isManhattan ? Agent.Strategies.AStarManhattan : Agent.Strategies.AStarEuclidean);
                        frontier.add(new Node(state, cost, node));
                        getFrontierMaximumSize(frontier.size());
                    }
                }

            }

        }
    }


    public double getHeuristic(int[] initialPoint, int[] targetPoint, int[] currentPoint, Agent.Strategies strategy) {

        switch (strategy) {
            case GreedySearchManhattan:
                return getManhattanDistance(currentPoint, targetPoint);

            case GreedySearchEuclidean:
                return getEuclideanDistance(currentPoint, targetPoint);

            case AStarManhattan:
                return getManhattanDistance(initialPoint, currentPoint) + getManhattanDistance(currentPoint, targetPoint);

            case AStarEuclidean:
                return getEuclideanDistance(initialPoint, currentPoint) + getEuclideanDistance(currentPoint, targetPoint);

            default:
                throw new Error("Unknown option");
        }

    }


    public double getManhattanDistance(int[] firstPoint, int[] secondPoint) {
        return Math.abs(firstPoint[0] - firstPoint[1]) + Math.abs(secondPoint[0] - secondPoint[1]);
    }

    public double getEuclideanDistance(int[] firstPoint, int[] secondPoint) {
        return Math.sqrt(Math.pow((secondPoint[0] - firstPoint[0]), 2) + Math.pow((secondPoint[1] - firstPoint[1]), 2));
    }

    public ArrayList<Node> getFinalPath(ArrayList<Node> path) {
        ArrayList<Node> finalPath = new ArrayList<>();

        if (path.size() == 1) {
            printDetails(finalPath);
            return path;
        }

        Node parent = path.get(path.size() - 1);
        while (parent.getParent() != null) {
            parent = parent.getParent();
            finalPath.add(parent);
        }
        printDetails(finalPath);

        return finalPath;
    }

    public void getFrontierMaximumSize(int size) {
        if (size > frontierMaximumSize)
            frontierMaximumSize = size;
    }


    public void printDetails(ArrayList<Node> solution) {

        for (Node node : solution) {
            pathCost += node.getPathCost();
        }
        System.out.print(
                "path Cost: " + pathCost + "\n" +
                        "nodes Expanded: " + nodesExpanded + "\n" +
                        "maximum Depth: " + solution.size() + "\n" +
                        "frontier Maximum Size: " + frontierMaximumSize + "\n"
        );
    }


}