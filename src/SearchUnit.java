import java.util.*;

/*
 1. Breadth first search (BFS)
2. Depth first search (DFS)
3. Greedy Best-first search (use separately Manhattan distance and Euclidean distance
as heuristics)

4. A* (use separately Manhattan distance and Euclidean distance as heuristics)*/

/*

display :
path cost
number of nodes expanded
maximum tree depth searched
maximum size of the frontier.
 */

class NodeComparator implements Comparator<Node> { // this class use to sort the process in term of size;

    public int compare(Node n1, Node n2) {
        if (n1.getPathCost() < n1.getPathCost())
            return -1;
        else if (n1.getPathCost() > n2.getPathCost())
            return 1;
        return 0;
    }

}


public class SearchUnit {

    public ArrayList<Node> breadthFirstSearch(Maze problem) {


        Node node = new Node(problem.getInitialLocationCoordinates(), 0);

        ArrayList<Node> solution = new ArrayList<Node>();
        Queue<Node> frontier = new LinkedList<Node>();
        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);
            return solution;
        }

        frontier.add(node);


        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.poll();

            for (Maze.Actions action : Maze.Actions.values()) {

                int[] nodeCoordinates = {node.getState()[0], node.getState()[1]};
                int[] state = problem.result(nodeCoordinates, action, problem.matrix);
                solution.add(node);


                if (!explored[state[0]][state[1]]) {
                    explored[state[0]][state[1]] = true;

                    if (problem.goalTest(state)) {
                        return solution;
                    } else {
                        frontier.add(new Node(state, 0, node));
                    }
                }

            }

        }

    }

    public boolean isInFrontier(Queue<Node> frontier, Node child) {
        for (Node node : frontier) {
            if (node.getState()[0] == child.getState()[0] && node.getState()[0] == child.getState()[0])
                return true;
        }
        return false;
    }

    public ArrayList<Node> depthFirstSearch(Maze problem) {


        Node node = new Node(problem.getInitialLocationCoordinates(), 0);

        ArrayList<Node> solution = new ArrayList<Node>();
        Stack<Node> frontier = new Stack<Node>();

        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);
            return solution;
        }

        frontier.add(node);


        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.pop();
//            System.out.println(node.getState()[0]+" "+ node.getState()[1]);

            for (Maze.Actions action : Maze.Actions.values()) {

                int[] nodeCoordinates = {node.getState()[0], node.getState()[1]};
                int[] state = problem.result(nodeCoordinates, action, problem.matrix);


                if (!explored[state[0]][state[1]]) {
                    solution.add(node);
                    explored[state[0]][state[1]] = true;

                    if (problem.goalTest(state)) {

                        return solution;
                    } else {
                        frontier.push(new Node(state, 0, node));
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

        ArrayList<Node> solution = new ArrayList<Node>();
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, comparator);
        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);
            return solution;
        }

        frontier.add(node);


        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.poll();

            for (Maze.Actions action : Maze.Actions.values()) {

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

        ArrayList<Node> solution = new ArrayList<Node>();
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, comparator);
        boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

        explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

        if (problem.goalTest(node.getState())) {
            solution.add(node);
            return solution;
        }

        frontier.add(node);


        while (true) {

            if (frontier.isEmpty()) {
                throw new Error("no solution found");
            }

            node = frontier.poll();

            for (Maze.Actions action : Maze.Actions.values()) {

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
                return getManhattanDistance(initialPoint, targetPoint) + getManhattanDistance(currentPoint, targetPoint);

            case AStarEuclidean:
                return getEuclideanDistance(initialPoint, targetPoint) + getEuclideanDistance(currentPoint, targetPoint);

            default:
                throw new Error("Unknown option");
        }

    }


    public double getManhattanDistance(int[] firstPoint, int[] secondPoint) {
        return Math.abs(firstPoint[0] - firstPoint[1]) + Math.abs(secondPoint[0] - secondPoint[1]);
    }

    public double getEuclideanDistance(int[] firstPoint, int[] secondPoint) {
        return Math.sqrt(Math.pow((firstPoint[0] - secondPoint[0]), 2) + Math.pow((firstPoint[1] - secondPoint[1]), 2));
    }

    public ArrayList<Node> getFinalPath(ArrayList<Node> path) {
        ArrayList<Node> finalPath = new ArrayList<>();
        if (path.size() == 1)
            return path;

        Node parent = path.get(path.size() - 1);
        while (parent.getParent() != null) {
            parent = parent.getParent();
            finalPath.add(parent);
        }

//        switch (strategy) {
//            case BFS:
//                Node parent = path.get(path.size() - 1);
//
//                while (parent.getParent() != null) {
//                    parent = parent.getParent();
//                    finalPath.add(parent);
//                }
//                break;
//
//            //        case DFS:
////            return finalPath;
////        case AStar:
////            return finalPath;
////        case GreedySearch:
////            return finalPath;
//
//
//        }

        return finalPath;
    }
}