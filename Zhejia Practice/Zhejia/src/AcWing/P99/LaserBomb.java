package AcWing.P99;

import java.util.Scanner;

public class LaserBomb {
    static int maxRow;
    static int maxCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTargets = scanner.nextInt();
        int rangeBombs = scanner.nextInt();
        int[][] targetInfo = new int[numberTargets][3];
        for (int bomb = 0; bomb < numberTargets; bomb++) {
            targetInfo[bomb][0] = scanner.nextInt();
            targetInfo[bomb][1] = scanner.nextInt();
            targetInfo[bomb][2] = scanner.nextInt();
            maxRow = Math.max(maxRow, targetInfo[bomb][0]);
            maxCol = Math.max(maxCol, targetInfo[bomb][1]);
        }

        System.out.println(destroyMaximumValue(targetInfo, rangeBombs));
    }

    private static int destroyMaximumValue(int[][] targetInfo, int rangeBombs) {
        int[][] board = new int[maxRow + 1][maxCol + 1];
        for (int[] bomb: targetInfo)
            board[bomb[0]][bomb[1]] += bomb[2];

        int[][] valueAccumulated = new int[maxRow + 1][maxCol + 1];
        valueAccumulated[0][0] = board[0][0];
        for (int col = 1; col <= maxCol; col++) valueAccumulated[0][col] = board[0][col] + valueAccumulated[0][col - 1];
        for (int row = 1; row <= maxRow; row++) valueAccumulated[row][0] = board[row][0] + valueAccumulated[row - 1][0];

        for (int row = 1; row <= maxRow; row++)
            for (int col = 1; col <= maxCol; col++)
                valueAccumulated[row][col] = valueAccumulated[row - 1][col] + valueAccumulated[row][col - 1] - valueAccumulated[row - 1][col - 1] + board[row][col];

        int maxValueToDestroy = 0;

        if (rangeBombs > Math.max(maxRow, maxCol))
            return valueAccumulated[maxRow][maxCol];
        else if (rangeBombs > maxRow && rangeBombs <= maxCol) {
            maxValueToDestroy = Math.max(maxValueToDestroy, valueAccumulated[maxRow][rangeBombs - 1]);
            for (int col = rangeBombs; col <= maxCol; col++)
                maxValueToDestroy = Math.max(maxValueToDestroy, valueAccumulated[maxRow][col] - valueAccumulated[maxRow][col - rangeBombs]);
        }
        else if (rangeBombs <= maxRow && rangeBombs > maxCol) {
            maxValueToDestroy = Math.max(maxValueToDestroy, valueAccumulated[rangeBombs - 1][maxCol]);
            for (int row = rangeBombs; row <= maxRow; row++)
                maxValueToDestroy = Math.max(maxValueToDestroy, valueAccumulated[row][maxCol] - valueAccumulated[row - rangeBombs][maxCol]);
        }
        else {
            maxValueToDestroy = Math.max(maxValueToDestroy, valueAccumulated[rangeBombs - 1][rangeBombs - 1]);
            for (int row = rangeBombs; row <= maxRow; row++)
                for (int col = rangeBombs; col <= maxCol; col++)
                    maxValueToDestroy = Math.max(maxValueToDestroy, valueAccumulated[row][col] - valueAccumulated[row - rangeBombs][col] - valueAccumulated[row][col - rangeBombs] + valueAccumulated[row - rangeBombs][col - rangeBombs]);
        }

        return maxValueToDestroy;
    }
}
