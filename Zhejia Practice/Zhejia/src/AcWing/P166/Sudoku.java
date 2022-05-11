package AcWing.P166;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    static final int N = 9;
    static int[] rows = new int[N];
    static int[] cols = new int[N];
    static int[][] cells = new int[3][3];
    static StringBuilder sudoku;

    static int[] numOnes = new int[1 << N];
    static int[] log2 = new int[1 << N];

    private static int lowBit(int num) {
        return num & -num;
    }

    private static void initial() {
        Arrays.fill(rows, (1 << N) - 1);
        Arrays.fill(cols, (1 << N) - 1);
        for (int[] cell: cells) Arrays.fill(cell, (1 << N) - 1);
    }

    private static int couldFill(int row, int col) {
         return rows[row] & cols[col] & cells[row / 3][col / 3];
    }


    private static boolean DFSearch(int toFill) {
        if (toFill == 0) return true;

        int minChoice = 10;
        int minRow = -1;
        int minCol = -1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (sudoku.charAt(9 * r + c) == '.' && numOnes[couldFill(r, c)] < minChoice) {
                    minChoice = numOnes[couldFill(r, c)];
                    minRow = r;
                    minCol = c;
                }
            }
        }

        for (int choice = couldFill(minRow, minCol); choice > 0; choice -= lowBit(choice)) {
            int index = log2[lowBit(choice)];
            rows[minRow] -= (1 << index);
            cols[minCol] -= (1 << index);
            sudoku.setCharAt(minRow * 9 + minCol,  (char) ('1' + index));
            cells[minRow / 3][minCol / 3] -= (1 << index);
            if (DFSearch(toFill - 1) ) return true;
            rows[minRow] += (1 << index);
            cols[minCol] += (1 << index);
            cells[minRow / 3][minCol / 3] += (1 << index);
            sudoku.setCharAt(minRow * 9 + minCol, '.');
        }


        return false;
    }


    public static void main(String[] args) {
        for (int i = 0; i < N; i++) log2[1 << i] = i;
        for (int i = 0; i < (1 << N); i++) {
            int count = 0;
            for (int j = i; j > 0; j -= lowBit(j)) count++;
            numOnes[i] = count;
        }

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNextLine()) {
            sudoku = new StringBuilder(in.nextLine());
            if (sudoku.equals("end")) break;
            initial();
            int toFill = 0;
            int ind = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    char num = sudoku.charAt(ind++);
                    if (num != '.') {
                        int intNum = num - '1';
                        rows[r] -= 1 << intNum;
                        cols[c] -= 1 << intNum;
                        cells[r / 3][c / 3] -= 1 << intNum;
                    } else toFill++;
                }
            }
            DFSearch(toFill);
            out.println(sudoku);
            out.flush();
        }
    }
}
