package CodeForce.P1530D;

import java.util.Arrays;
import java.util.Scanner;

public class SecretSanta_StandardSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTest = scanner.nextInt();
        while (numberTest-- > 0) {
            int numberGuest = scanner.nextInt();
            int[][] guest = new int[2][numberGuest + 1];
            for (int i = 1; i <= numberGuest; i++) {
                guest[0][i] = scanner.nextInt();
                guest[1][i] = i;
            }
            findBestMatches(guest);

        }
    }

    private static void findBestMatches(int[][] guest) {
        System.out.println(Arrays.stream(guest[0]).distinct().count() - 1);
        boolean[] found = new boolean[guest[0].length];
        int[] result = new int[guest[0].length];
        int[] reversed = new int[guest[0].length];
        for (int i = 1; i < result.length; i++) {
            if (result[i] > 0) continue;
            int current = i;

            while (!found[guest[0][current]]) {
                result[current] = guest[0][current];
                reversed[guest[0][current]] = current;
                found[guest[0][current]] = true;
                current = guest[0][current];
            }
            if (current != i) {
                int desired = guest[0][current];
                int occupied = reversed[desired];
                if (desired != guest[0][occupied]) {
                    result[current] = desired;
                    reversed[desired] = current;
                    result[occupied] = i;
                    reversed[i] = occupied;
                } else {
                    result[current] = i;
                    reversed[i] = current;
                }
                found[current] = true;
                found[i] = true;
            }

            if (result[i] == 0) {
                int desired = guest[0][current];
                int occupied = reversed[desired];
                result[current] = desired;
                reversed[desired] = current;
                result[occupied] = i;
                reversed[i] = occupied;
                found[current] = true;
                found[i] = true;
            }

        }
        printArray(result);
    }

    private static void printArray(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < array.length; i++)
            stringBuilder.append(array[i]).append(" ");
        System.out.println(stringBuilder);
    }
}
