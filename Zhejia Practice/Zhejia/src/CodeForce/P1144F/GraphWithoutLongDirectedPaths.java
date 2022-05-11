package CodeForce.P1144F;

import java.util.*;

public class GraphWithoutLongDirectedPaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numVertex = scanner.nextInt();
        int numEdge = scanner.nextInt();
        int[][] edges = new int[numEdge][2];
        int[] vertexes = new int[numVertex + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numEdge; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
            if (!graph.containsKey(edges[i][0]))
                graph.put(edges[i][0], new ArrayList<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            if (!graph.containsKey(edges[i][1]))
                graph.put(edges[i][1], new ArrayList<>());
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        Stack<Integer> trace = new Stack<>();
        trace.push(edges[0][0]);
        vertexes[edges[0][0]] = 1;
        while (!trace.isEmpty()) {
            Integer front = trace.pop();
            int colour = vertexes[front];
            List<Integer> neighbours = graph.get(front);
            for (int neighbour: neighbours) {
                graph.get(neighbour).remove(front);
                if (vertexes[neighbour] != 0 && vertexes[neighbour] == colour) {
                    System.out.println("NO");
                    return;
                } else if (vertexes[neighbour] == 0) {
                    vertexes[neighbour] = - colour;
                    trace.push(neighbour);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        System.out.println("YES");
        for (int i = 0; i < numEdge; i++)
            if (vertexes[edges[i][0]] == -1) result.append(0);
            else result.append(1);
        System.out.println(result);
    }
}


