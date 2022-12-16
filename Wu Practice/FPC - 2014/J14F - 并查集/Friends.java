import java.io.*;
import java.util.*;

class Solution {

  public static void run(InputStream in, PrintStream out) {
    Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
    new Solution().solve(sc, out);
    sc.close();
  }

  public void solve(Scanner sc, PrintStream out) {
    int n = sc.nextInt();
    int m = sc.nextInt();
    int s = sc.nextInt();
    UnionFind u = new UnionFind(n);
    while (m-- > 0) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        u.join(a, b);
    }
    for (int i = 0; i < n; i++) {
        u.find(i);
    }
    boolean no = false;
    for (int i = 1; i < n - 1; i++) {
      if (u.find(i) != u.find(i-1)) {
        no = true;
        break;
      }
    }
    if (no) out.println("no");
    else out.println("yes");
  }
}

class UnionFind{

    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int n) {
        if (parent[n] == n) return n;
        else {
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }

    public boolean join(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;
        if (rank[pa] < rank[pb]) parent[pa] = pb;
        else if (rank[pb] < rank[pa]) parent[pb] = pa;
        else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }
}
