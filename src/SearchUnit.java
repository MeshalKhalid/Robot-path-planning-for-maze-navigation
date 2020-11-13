import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

	public ArrayList<int[]> breadthFirstSearch(Maze problem) {
		System.out.println(
				problem.getInitialLocationCoordinates()[0] + " >< " + problem.getInitialLocationCoordinates()[1]);

		Node node = new Node(problem.getInitialLocationCoordinates(), 0);

		ArrayList<int[]> solution = new ArrayList<int[]>();
		Queue<Node> frontier = new LinkedList<Node>();
		boolean[][] explored = new boolean[problem.getHeight()][problem.getWidth()];

		explored[problem.getInitialLocationCoordinates()[0]][problem.getInitialLocationCoordinates()[1]] = true;

		if (problem.goalTest(node.getState())) {
			System.out.println("first node is the solution");
			solution.add(node.getState());
			return solution;
		}

		frontier.add(node);

		

		while (true) {

			if (frontier.isEmpty()) {
				throw new Error("no solution found");
			}

			node = frontier.poll();

			int counter = 1;
			for (Maze.Actions action : Maze.Actions.values()) {
//				System.out.println(node.getState()[0] + " <--> current state <--> "+ node.getState()[1]);
//				System.out.println("\n------------" + "{counter " + counter + " } "
//						+ "----------------------------------" + (counter == 1 ? "=======<>\n" : "\n"));
				int[] nodeCoordinates = {node.getState()[0],node.getState()[1]};
				int[] state = problem.result(nodeCoordinates, action, problem.matrix);
				solution.add(state);

				counter++;

		

				if (!explored[state[0]][state[1]]) {
					explored[state[0]][state[1]] = true;
					//System.out.println("< added to explored > " + state[0] + " " + state[1]);

					if (problem.goalTest(state)) {

						//System.out.println("found: "+state[0]+" <> "+state[1]);
						return solution;
					} else {
//						System.out.println("< added to frontier > " + state[0] + " " + state[1]);

						frontier.add(new Node(state, 0));
					}
				}  //else
					//System.out.println("skip " + state[0] + " " + state[1]);

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

	public void depthFirstSearch(Maze problem) {

	}

	public void greedyBestFirstSearch(Maze problem) {

	}

	public void aStarSearchManhattan(Maze problem) {

	}

	public void aStarSearchEuclidean(Maze problem) {

	}

}
