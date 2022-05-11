package AtCoder.ABC176E;

import java.util.*;

public class Bomber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt(), W = in.nextInt(), numTarget = in.nextInt();
        Set<Point> filled = new HashSet<>();
        int[][] targets = new int[numTarget + 1][2];
        for (int i = 1; i <= numTarget; i++) {
            targets[i][0] = in.nextInt();
            targets[i][1] = in.nextInt();
            filled.add(new Point(targets[i][0], targets[i][1]));
        }
        int[][] heightCount = new int[H + 1][2];
        for (int i = 1; i <= H; i++) heightCount[i][1] = i;
        int[][] widthCount = new int[W + 1][2];
        for (int i = 1; i <= W; i++) widthCount[i][1] = i;
        for (int i = 1; i <= numTarget; i++) {
            heightCount[targets[i][0]][0]++;
            widthCount[targets[i][1]][0]++;
        }
        Arrays.sort(heightCount, (int[] h1, int[] h2) -> h1[0] - h2[0]);
        Arrays.sort(widthCount, (int[] w1, int[] w2) -> w1[0] - w2[0]);
        int bestHeight = heightCount[H][0], bestWeight = widthCount[W][0];
        List<Integer> heights = new ArrayList<>(), widths = new ArrayList<>();
        for (int i = H; i >= 0; i--) {
            if (heightCount[i][0] == bestHeight) heights.add(i);
            else break;
        }

        for (int i = W; i >= 0; i--) {
            if (widthCount[i][0] == bestWeight) widths.add(i);
            else break;
        }

        for (int h = 0; h < heights.size(); h++) {
            for (int w = 0; w < widths.size(); w++) {
                int[] best = new int[] {heightCount[heights.get(h)][1], widthCount[widths.get(w)][1]};
                if (!filled.contains(new Point(best[0], best[1]))) {
                    System.out.println(heightCount[H][0] + widthCount[W][0]);
                    return;
                }
            }
        }
        System.out.println(heightCount[H][0] + widthCount[W][0] - 1);
    }
}

class Point {
    int h; int w;

    public Point(int h, int w) {
        this.h = h;
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return h == point.h && w == point.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, w);
    }
}

