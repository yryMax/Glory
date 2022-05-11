import java.util.*;
// optimised
// 2 times faster
public class J21I_re {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        int len = in.length();
        int[] same = new int[30];
        Vertex[] graph = new Vertex[len];
        for (int i = 0; i < 30; i++) same[i] = -1;

        for (int i = 0; i < len; i++) {
            int cur = in.charAt(i) - 'a';
            Vertex newV = new Vertex(new HashSet<>());
            if (same[cur] != -1) {
                newV.nb.add(same[cur]);
            }
            if (i >= 1) newV.nb.add(i-1);
            if (i < len-1) newV.nb.add(i+1);
            same[cur] = i;
            graph[i] = newV;
        }
        for (int i = 0; i < 30; i++) same[i] = -1;

        for (int i = len -1; i >= 0; i--) {
            int cur = in.charAt(i) - 'a';
            Vertex v = graph[i];
            if (same[cur] != -1) {
                v.nb.add(same[cur]);
            }
            same[cur] = i;
        }

        Queue<Integer> q = new LinkedList();
        int[] vis = new int[len];
        int[] prev = new int[len];
        for (int i = 0; i < len; i++) prev[i] = -1;

        q.add(0);
        vis[0]++;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Integer i : graph[cur].nb) {
                if (vis[i] != 0) continue;
                q.add(i);
                vis[i]++;
                prev[i] = cur;
            }
        }

        int count = 0;
        int now = len-1;
        while (now != 0) {
            count++;
            now = prev[now];
        }
        System.out.println(count);

    }
}
class Vertex {

    Set<Integer> nb;

    public Vertex(Set<Integer> nb) {
        this.nb = nb;
    }
}
