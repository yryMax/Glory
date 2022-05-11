package CodeForce.P1249E;

import java.util.Scanner;

public class ByElevatorOrStairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int  numberFloors = scanner.nextInt();
        int overhead = scanner.nextInt();
        int[] stairs = new int[numberFloors];
        int[] elevators = new int[numberFloors];
        for (int i = 1; i < numberFloors; i++) stairs[i] = scanner.nextInt();
        for (int i = 1; i < numberFloors; i++) elevators[i] = scanner.nextInt();
        int[][] result = new int[numberFloors][2];
        result[1][0] = stairs[1];
        result[1][1] = elevators[1] + overhead;
        for (int i = 2; i < numberFloors; i++) {
            result[i][0] = Math.min(result[i - 1][0], result[i - 1][1]) + stairs[i];
            result[i][1] = Math.min(result[i - 1][0] + overhead, result[i - 1][1]) + elevators[i];
        }
        for (int i = 0; i < numberFloors; i++)
            System.out.print(Math.min(result[i][0], result[i][1]) + " ");
    }
}
