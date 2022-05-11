package AcWing.P137;

import java.util.Arrays;
import java.util.Scanner;

public class Snowflake {
    static int numSnowflake;
    static int[][] snowflakes;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        numSnowflake = scanner.nextInt();
        snowflakes = new int[numSnowflake][6];
        int[] temp = new int[6];
        int[] reverse = new int[6];

        for (int i = 0; i < numSnowflake; i++) {
            for (int s = 0; s < 6; s++) {
                temp[s] = scanner.nextInt();
                reverse[5 - s] = temp[s];
            }
            minRepresentation(temp);
            minRepresentation(reverse);
            if (compareArray(temp, reverse) > 0) System.arraycopy(temp, 0, snowflakes[i], 0, 6);
            else System.arraycopy(reverse, 0, snowflakes[i], 0, 6);
        }
        Arrays.sort(snowflakes, Snowflake::compareArray);
        for (int i = 1; i < numSnowflake; i++) {
            if (compareArray(snowflakes[i], snowflakes[i - 1]) == 0) {
                System.out.println("Twin snowflakes found.");
                return;
            }
        }
        System.out.println("No two snowflakes are alike.");
    }



    private static void minRepresentation(int[] arr) {
        int[] temp = new int[12];
        System.arraycopy(arr, 0, temp, 0, 6);
        System.arraycopy(arr, 0, temp, 6, 6);

        int i = 0, j = 1;
        while (i < 6 && j < 6) {
            int k = 0;
            while (k < 6 && temp[i + k] == temp[j + k]) k++;
            if (k == 6) break;
            if (temp[i + k] > temp[j + k]) {
                i += k + 1;
                if (i == j) i++;
            }
            else {
                j += k + 1;
                if (i == j) j++;
            }
            k = Math.min(i , j);
            System.arraycopy(temp, k, arr, 0, 6);
        }
    }


    private static int compareArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < 6; i++)
            if (arr1[i] < arr2[i]) return 1;
            else if (arr1[i] > arr2[i]) return -1;
        return 0;
    }
}
