package AcWing.P108;

import java.util.Scanner;

public class Puzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int size = scanner.nextInt();
            int[][] input = new int[size][size];
            int[][] output = new int[size][size];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    input[i][j] = scanner.nextInt();
            int[] inArray = transform(input);
            int inReverse = countReverse(inArray, 0, inArray.length - 1);
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    output[i][j] = scanner.nextInt();
            int[] outArray = transform(output);
            int outReverse = countReverse(outArray, 0, outArray.length - 1);
            System.out.println((Math.abs(inReverse - outReverse) % 2 == 0) ? "TAK" : "NIE");

        }
    }

    private static int countReverse(int[] inArray, int start, int end) {
        if (end - start <= 0) return 0;
        if (end - start == 1) {
            int count = (inArray[start] <= inArray[end]) ? 0 : 1;
            int smaller = Math.min(inArray[start], inArray[end]);
            int larger = Math.max(inArray[start], inArray[end]);
            inArray[start] = smaller;
            inArray[end] = larger;
            return count;
        }

        int mid = (start + end) / 2;
        int countLeft = countReverse(inArray, start, mid);
        int countRight = countReverse(inArray, mid + 1, end);

        int count = countLeft + countRight;
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (inArray[i] <= inArray[j]) temp[k++] = inArray[i++];
            else {
                count += (mid - i) + 1;
                temp[k++] = inArray[j++];
            }
        }
        while (i <= mid) temp[k++] = inArray[i++];

        while (j <= end) temp[k++] = inArray[j++];
        System.arraycopy(temp, 0, inArray, start, temp.length);
        return count;
    }

    private static int[] transform(int[][] matrix) {
        int[] out = new int[matrix.length * matrix.length - 1];
        int i = 0;
        for (int j = 0; j < matrix.length; j++)
            for (int k = 0; k < matrix.length; k++)
                if (matrix[j][k] != 0) out[i++] = matrix[j][k];
        return out;
    }
}
