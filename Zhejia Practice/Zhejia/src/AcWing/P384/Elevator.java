package AcWing.P384;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Elevator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int ind0 = 0;
        int[] deltas = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            deltas[i] = in.nextInt();
            if (deltas[i] == 0) ind0 = i;
        }
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        queue.offer(new Pair(0, ind0, 1));
        int[][] states = new int[N + 1][M + 1];
        for (int[] row: states) Arrays.fill(row, Integer.MAX_VALUE - 100);
        states[1][0] = 0;
        while (!queue.isEmpty()) {
            Pair top = queue.poll();
            int currFloor = top.floor;
            int currOption = top.option;
            int currTime = top.time;
            for (int option = 1; option <= M; option++) {
                if (option == ind0) continue;
                int nextFloor = currFloor + deltas[option];
                if (nextFloor > N || nextFloor <= 0) continue;
                int estimatedTime = currTime + Math.abs(option - currOption) + 2 * Math.abs(deltas[option]);
                if (estimatedTime < states[nextFloor][option]) {
                    states[nextFloor][option] = estimatedTime;
                    queue.offer(new Pair(estimatedTime, option, nextFloor));
                }
            }
        }
        int max = Integer.MAX_VALUE - 100;
        for (int i = 1; i <= M; i++) max = Math.min(max, states[N][i]);
        System.out.println((max == Integer.MAX_VALUE - 100)  ? -1 : max);
    }
}

class Pair implements Comparable<Pair> {
    int time;
    int option;
    int floor;

    public Pair(int time, int option, int floor) {
        this.time = time;
        this.option = option;
        this.floor = floor;
    }

    @Override
    public int compareTo(Pair pair) {
        return time - pair.time;
    }
}
