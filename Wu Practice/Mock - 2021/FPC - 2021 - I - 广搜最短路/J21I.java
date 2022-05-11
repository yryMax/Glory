import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class J21I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        long len = in.length();
        int[] dist = new int[30]; // 26 letters
        node[] nodes = new node[(int)len];
        Queue<Integer> queue = new LinkedList<>();
        int[] vis = new int[(int) len];
        for (int i = 0; i < 30; i++) {
            dist[i] = -1;
        }

        for (int i = 0; i < len; i++) {
            int cur = in.charAt(i) - 'a';
            node newNode = new node(new ArrayList<>(), i);
            if (dist[cur] != -1) { // not first occurence
                newNode.sameLeft = dist[cur];
            }
            dist[cur] = i; // update the left closest.

            if (i > 0) newNode.nb.add(i - 1);
            if (i < len - 1) newNode.nb.add(i + 1);
            nodes[i] = newNode;
        }

        dist = new int[30]; // reset for 2nd-loop
        for (int i = 0; i < 30; i++) {
            dist[i] = -1;
        }

        for (int i = (int) len-1; i >= 0; i--) {
            node curNode = nodes[i];
            int cur = in.charAt(i) - 'a';
            if (dist[cur] != -1) {
                curNode.sameRight = dist[cur];
            }
            dist[cur] = i; // update the left closest.
        }

        int[] prev = new int[(int)len];

        queue.add(0);
        vis[0]++;
        prev[0] = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer i : nodes[cur].nb) {
                if (vis[i] != 0) continue;
                queue.add(i);
                vis[i]++;
                prev[i] = cur;
            }
            if (nodes[cur].sameLeft != -1 && vis[nodes[cur].sameLeft] == 0) {
                queue.add(nodes[cur].sameLeft);
                vis[nodes[cur].sameLeft]++;
                prev[nodes[cur].sameLeft] = cur;
            }
            if (nodes[cur].sameRight != -1 && vis[nodes[cur].sameRight] == 0) {
                queue.add(nodes[cur].sameRight);
                vis[nodes[cur].sameRight]++;
                prev[nodes[cur].sameRight] = cur;
            }
        }
        int count = 0;
        int last = prev[(int)len-1];
        while (last != -1) {
            count++;
            last = prev[last];
        }
        System.out.println(count);

    }
}
class node {
    ArrayList<Integer> nb;
    int sameLeft;
    int sameRight; // only useful is left and right same distance
    int pos;

    public node(ArrayList<Integer> nb, int pos) {
        this.nb = nb;
        this.pos = pos;
        this.sameLeft = -1;
        this.sameRight = -1;
    }
}