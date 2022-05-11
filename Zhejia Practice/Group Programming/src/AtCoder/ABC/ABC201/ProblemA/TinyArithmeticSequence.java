package AtCoder.ABC.ABC201.ProblemA;

import java.util.Arrays;
import java.util.Scanner;

public class TinyArithmeticSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) arr[i] = in.nextInt();
        Arrays.sort(arr);
        System.out.println(2 * arr[1] == arr[0] + arr[2] ? "Yes" : "No");
    }
}
