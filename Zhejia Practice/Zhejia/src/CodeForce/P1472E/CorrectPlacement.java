package CodeForce.P1472E;

import java.util.Arrays;
import java.util.Scanner;

public class CorrectPlacement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = scanner.nextInt();
        while (numTest-- > 0) {
            int numFriend = scanner.nextInt();
            int[][] friends = new int[numFriend + 1][3];
            for (int i = 1; i <= numFriend; i++) {
                int height = scanner.nextInt();
                int weight = scanner.nextInt();
                friends[i][0] = Math.min(height, weight);
                friends[i][1] = Math.max(height, weight);
                friends[i][2] = i;
            }
            friends[0][0] = 0;
            friends[0][1] = Integer.MAX_VALUE;
            int[] result = new int[numFriend + 1];
            Arrays.sort(friends, (int[] r1, int[] r2) -> {
                if (r1[0] != r2[0]) return r1[0] - r2[0];
                else return r1[1] - r2[1];
            });
            int minKey = friends[0][1];
            int minIndex = 0;
            int head = 0;
            int headMin = friends[0][1];
            boolean found = false;
            for (int i = 1; i <= numFriend; i++) {
                if (friends[i][0] != friends[head][0]) {
                    if (headMin < minKey) {
                        minKey = headMin;
                        minIndex = head;
                    }
                    head = i;
                    headMin = friends[i][1];
                    found = false;
                    if (friends[i][1] <= minKey) {
                        result[friends[i][2]] = -1;
                        found = false;
                    } else {
                        result[friends[i][2]] = friends[minIndex][2];
                        found = true;
                    }
                } else {
                    if (found) result[friends[i][2]] = result[friends[i - 1][2]];
                    else if (friends[i][1] <= minKey) {
                        result[friends[i][2]] = -1;
                    } else {
                        result[friends[i][2]] = friends[minIndex][2];
                        found = true;
                    }
                }
            }
            for (int i = 1; i <= numFriend; i++)
                System.out.print(result[i] + " ");
            System.out.print("\n");

        }
    }
}
