package AtCoder.ABC160E;

import java.util.Arrays;
import java.util.Scanner;

public class RedAndGreenApples {
    static int X;
    static int Y;
    static int A;
    static int B;
    static int C;
    static long[] redApples;
    static long[] greenApples;
    static long[] colorlessApples;



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        X = in.nextInt();
        Y = in.nextInt();
        A = in.nextInt();
        B = in.nextInt();
        C = in.nextInt();
        redApples = new long[A];
        greenApples = new long[B];
        colorlessApples = new long[C];
        for (int i = 0; i < A; i++) redApples[i] = in.nextLong();
        for (int i = 0; i < B; i++) greenApples[i] = in.nextLong();
        for (int i = 0; i < C; i++) colorlessApples[i] = in.nextLong();
        Arrays.sort(redApples);
        redApples = reverse(redApples);
        Arrays.sort(greenApples);
        greenApples = reverse(greenApples);
        Arrays.sort(colorlessApples);
        colorlessApples = reverse(colorlessApples);
        long[] accRed = new long[A], accGreen = new long[B], accColorless = new long[C];
        for (int i = 0; i < A; i++) accRed[i] = (i == 0) ? redApples[i] : redApples[i] + accRed[i - 1];
        for (int i = 0; i < B; i++) accGreen[i] = (i == 0) ? greenApples[i] : greenApples[i] + accGreen[i - 1];
        for (int i = 0; i < C; i++) accColorless[i] = (i == 0) ? colorlessApples[i] : colorlessApples[i] + accColorless[i - 1];
        long ans = accRed[X - 1] + accGreen[Y - 1];
        int indRed = X - 1, indGreen = Y - 1, indColorless = 0;
        while (indRed >= 0 && indGreen >= 0 && indColorless < C) {
            if (colorlessApples[indColorless] <= Math.min(redApples[indRed], greenApples[indGreen])) break;
            if (colorlessApples[indColorless] <= redApples[indRed] && colorlessApples[indColorless] > greenApples[indGreen]) {
                ans = ans - greenApples[indGreen] + colorlessApples[indColorless];
                indGreen--;
                indColorless++;
            } else if (colorlessApples[indColorless] <= greenApples[indGreen] && colorlessApples[indColorless] > redApples[indRed]) {
                ans = ans - redApples[indRed] + colorlessApples[indColorless];
                indRed--;
                indColorless++;
            } else {
                if (redApples[indRed] < greenApples[indGreen]) {
                    ans = ans - redApples[indRed] + colorlessApples[indColorless];
                    indRed--;
                    indColorless++;
                } else if (redApples[indRed] > greenApples[indGreen]) {
                    ans = ans - greenApples[indGreen] + colorlessApples[indColorless];
                    indGreen--;
                    indColorless++;
                } else {
                    ans = ans - redApples[indRed] + colorlessApples[indColorless];
                    indRed--;
                    indColorless++;
                    if (indGreen >= 0 && indColorless < C && colorlessApples[indColorless] > greenApples[indGreen]) {
                        ans = ans - greenApples[indGreen] + colorlessApples[indColorless];
                        indGreen--;
                        indColorless++;
                    }
                }
            }

        }

        while (indRed >= 0 && indColorless < C && redApples[indRed] < colorlessApples[indColorless]) {
            ans = ans - redApples[indRed] + colorlessApples[indColorless];
            indRed--;
            indColorless++;
        }

        while (indGreen >= 0 && indColorless < C && greenApples[indGreen] < colorlessApples[indColorless]) {
            ans = ans - greenApples[indGreen] + colorlessApples[indColorless];
            indGreen--;
            indColorless++;
        }

        System.out.println(ans);
    }

    private static long[] reverse(long[] arr) {
        long[] out = new long[arr.length];
        for (int i = 0; i < arr.length; i++) out[i] = arr[arr.length - 1 - i];
        return out;
    }
}
