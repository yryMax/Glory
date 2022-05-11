package AcWing.P111;

import java.util.*;

public class StallReservations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCow = scanner.nextInt();
        int[][] cows = new int[numCow][3];
        for (int i = 0; i < numCow; i++) {
            cows[i][0] = scanner.nextInt();
            cows[i][1] = scanner.nextInt();
            cows[i][2] = i;
        }
        Arrays.sort(cows, (int[] r1, int[] r2) -> {
            if (r1[0] != r2[0]) return r1[0] - r2[0];
            else return r1[1] - r2[1];
        });
        int[] results = new int[numCow];

        PriorityQueue<Pair> queue = new PriorityQueue<>();

        for (int i = 0; i < numCow; i++) {
            int[] cow = cows[i];
            if (queue.isEmpty() || queue.peek().key >= cow[0]) {
                Pair newStall = new Pair(cow[1]);
                queue.offer(newStall);
                results[cows[i][2]] = newStall.index;
            } else {
                Pair smallest = queue.poll();
                smallest.key  = cow[1];
                queue.offer(smallest);
                results[cows[i][2]] = smallest.index;
            }
        }
        System.out.println(queue.size());
        for (int result: results) System.out.println(result);
    }
}

class Pair implements Comparable<Pair> {
    int key;
    int index;
    static int count = 0;
    public Pair(int key) {
        this.key = key;
        count++;
        index = count;
    }

    @Override
    public int compareTo(Pair otherPair) {
        return key - otherPair.key;
    }
}
