package AtCoder.ABC176D;


import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class WizardInMaze {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt(), W = in.nextInt();
        int departureH = in.nextInt(), departureW = in.nextInt();
        int destinationH = in.nextInt(), destinationW = in.nextInt();
        int distance = Math.abs(departureH - destinationH) + Math.abs(departureW - destinationW);
        char[][] dnd = new char[H + 1][W + 1];
        long[][] dp = new long[H + 1][W + 1];
        boolean[][] inQueue = new boolean[H + 1][W + 1];
        in.nextLine();
        for (int i = 1; i <= H; i++) {
            String line = in.nextLine();
            for (int j = 1; j <= W; j++) {
                dnd[i][j] = line.charAt(j - 1);
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        dp[departureH][departureW] = 0;
        inQueue[departureH][departureW] = true;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(new Point(departureH, departureW, 0));
        while (!queue.isEmpty()) {
            Point currPoint = queue.poll();
            int[] currCoordinate = new int[] {currPoint.h_coordinate, currPoint.w_coordinate};
            int currDis = currPoint.distance;

            if (!inQueue[currCoordinate[0]][currCoordinate[1]]) continue;
            inQueue[currCoordinate[0]][currCoordinate[1]] = false;
            for (int angle = 0; angle <= 3; angle++) {
                int nextH = currCoordinate[0] + (int) Math.cos(Math.PI / 2 * angle), nextW = currCoordinate[1] + (int) Math.sin(Math.PI / 2 * angle);
                if (nextH < 1 || nextH > H || nextW < 0 || nextW > W) continue;
                if (dnd[nextH][nextW] == '#') continue;
                if (dp[currCoordinate[0]][currCoordinate[1]] < dp[nextH][nextW]) {
                    dp[nextH][nextW] = dp[currCoordinate[0]][currCoordinate[1]];
                    queue.offer(new Point(nextH, nextW, currDis));
                    inQueue[nextH][nextW] = true;
                }
            }

            for (int nextH = Math.max(1, currCoordinate[0] - 2); nextH <= Math.min(H, currCoordinate[0] + 2); nextH++) {
                for (int nextW = Math.max(1 ,currCoordinate[1] - 2); nextW <= Math.min(W, currCoordinate[1] + 2); nextW++) {
                    if (Math.abs(currCoordinate[0] - nextH) + Math.abs(currCoordinate[1] - nextW) <= 1) continue;
                    if (dnd[nextH][nextW] == '#') continue;
                    if (dp[nextH][nextW] > dp[currCoordinate[0]][currCoordinate[1]] + 1) {
                        dp[nextH][nextW] = dp[currCoordinate[0]][currCoordinate[1]] + 1;
                        queue.offer(new Point(nextH, nextW, currDis + 1));
                        inQueue[nextH][nextW] = true;
                    }
                }
            }
        }
        System.out.println(dp[destinationH][destinationW] == Long.MAX_VALUE ? -1 : dp[destinationH][destinationW]);
    }
}

class Point implements Comparable<Point> {
    int h_coordinate;
    int w_coordinate;
    int distance;

    public Point(int h_coordinate, int w_coordinate, int distance) {
        this.h_coordinate = h_coordinate;
        this.w_coordinate = w_coordinate;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return h_coordinate == point.h_coordinate && w_coordinate == point.w_coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h_coordinate, w_coordinate);
    }


    @Override
    public int compareTo(Point o) {
        return distance - o.distance;
    }
}


