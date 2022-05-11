package AcWing.P136;

import java.util.Arrays;
import java.util.Scanner;

public class NeighbourValueSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[][] numbers = new int[num][2];
        int[] positions = new int[num];
        int[] prev = new int[num];
        int[] next = new int[num];
        int[][] result = new int[num][2];
        for (int i = 0; i < num; i++) {
            numbers[i][0] = scanner.nextInt();
            numbers[i][1] = i;
        }
        Arrays.sort(numbers, (int[] r1, int[] r2) -> {
            if (r1[0] != r2[0]) return r1[0] - r2[0];
            else return r1[1] - r2[1];
        });

        for (int i = 0; i < num; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
            positions[numbers[i][1]] = i;
        }
        for (int i = num - 1; i >= 1; i--) {
            int index = positions[i];
            int left = prev[index];
            int right = next[index];
            if (left == -1) {
                result[i][0] = Math.abs(numbers[index][0] - numbers[right][0]);
                result[i][1] = numbers[right][1];
                prev[right] = -1;
                continue;
            }
            if (right == num) {
                result[i][0] = Math.abs(numbers[index][0] - numbers[left][0]);
                result[i][1] = numbers[left][1];
                next[left] = num;
                continue;
            }
            int option1 = Math.abs(numbers[index][0] - numbers[left][0]);
            int option2 = Math.abs(numbers[index][0] - numbers[right][0]);
            result[i][0] = Math.min(option1, option2);
            if (option1 < option2) result[i][1] = numbers[left][1];
            else if (option1 > option2) result[i][1] = numbers[right][1];
            else result[i][1] = (Math.min(left, right) == left) ? numbers[left][1] : numbers[right][1];
            next[left] = right;
            prev[right] = left;
        }
        for (int i = 1; i < num; i++)
            System.out.println(result[i][0] + " " + (result[i][1] + 1));

    }
}


