import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
                        System.out.println(state[0] + " " + state[1]);
                        frontier.push(new Node(state, 0, node));
                    }
                }

            }
        }

    }

    public void greedyBestFirstSearch(Maze problem) {

    }

    public void aStarSearchManhattan(Maze problem) {

    }

    public void aStarSearchEuclidean(Maze problem) {

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