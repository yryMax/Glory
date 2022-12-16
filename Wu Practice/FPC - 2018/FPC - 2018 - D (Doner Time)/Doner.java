import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Doner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() + 1;
        int s = sc.nextInt();
        Edge[] edges = new Edge[s];
        for (int i = 0; i < s; i++) {
            edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
;       }
        int m = sc.nextInt();
        int[] shopIds = new int[m];
        for (int i = 0; i < m; i++) {
            shopIds[i] = sc.nextInt();
        }

        long[] dist = new long[n];
        boolean[] visit = new boolean[n];
        ArrayList<ArrayList<Edge>> neighbours = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            neighbours.add(new ArrayList<>());
        }

        for (Edge e : edges) {
            int from = e.a;
            int to = e.b;
            int weight = e.weight;
            neighbours.get(from).add(new Edge(from, to, weight));
            neighbours.get(to).add(new Edge(to, from, weight));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        dist[1] = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            visit[cur.id] = true;
            for (Edge e : neighbours.get(cur.id)) {
                if (visit[e.b]) continue;
                long newDist = cur.cost + e.weight;
                if (newDist < dist[e.b]) {
                    dist[e.b] = newDist;
                    q.add(new Node(e.b, newDist));
                }
            }
        }
        long minDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            minDist = Math.min(minDist, dist[shopIds[i]]);
        }

        PriorityQueue<Integer> ansQ = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            if (dist[shopIds[i]] == minDist) ansQ.add(shopIds[i]);
        }
        int minId = ansQ.poll();
        System.out.println(minId + " " + minDist);

    }
}

class Node implements Comparable<Node> {
    int id;
    long cost;
    public Node(int id, long cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        if (this.cost < o.cost) return -1;
        else if (this.cost == o.cost) return 0;
        return 1;
    }
}
class Edge {
    int a;
    int b;
    int weight;
    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
}