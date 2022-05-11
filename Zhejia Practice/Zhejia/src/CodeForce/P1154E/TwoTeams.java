package CodeForce.P1154E;

import java.io.PrintWriter;
import java.util.*;

public class TwoTeams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out);
        int numStudent = scanner.nextInt();
        int numChoice = scanner.nextInt();
        int[][] students = new int[numStudent + 2][2];
        for (int i = 1; i <= numStudent; i++) {
            students[i][0] = scanner.nextInt();
            students[i][1] = i;
        }
        int[][] ranking = Arrays.copyOfRange(students, 1, numStudent + 1);
        Arrays.sort(ranking, (int[] row1, int[] row2) -> -row1[0] + row2[0]);
        int[] prev = new int[numStudent + 2];
        int[] next = new int[numStudent + 2];
        for (int i = 0; i <= numStudent + 1; i++) {
            next[i] = Math.min(numStudent + 2, i + 1);
            prev[i] = Math.max(0, i - 1);
        }

        int[] result = new int[numStudent + 2];
        int current = 1;
        for (int i = 0; i < numStudent; i++) {
            if (result[ranking[i][1]] == 0) {
                int[] selected = ranking[i];
                result[selected[1]] = current;
                int choose = 1;
                int leftPointer = selected[1];
                int rightPointer = selected[1];
                while (choose++ <= numChoice) {
                    int preIndex = Math.max(0, prev[leftPointer]);
                    int nexIndex = Math.min(numStudent + 1, next[rightPointer]);
                    if (nexIndex != numStudent + 1) result[nexIndex] = current;
                    if (preIndex != 0) result[preIndex] = current;
                    leftPointer = preIndex;
                    rightPointer = nexIndex;
                }
                leftPointer = Math.max(0, prev[leftPointer]);
                rightPointer = Math.min(numStudent + 1, next[rightPointer]);
                next[leftPointer] = rightPointer;
                prev[rightPointer] = leftPointer;
                current = 3 - current;
            }
        }
        for (int i = 1; i <= numStudent; i++) printWriter.print(result[i]);
        printWriter.flush();

    }
}
