package AcWing.P102;

import java.util.Arrays;
import java.util.Scanner;

public class BestCowFences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberFields = scanner.nextInt();
        int minimumSizeToSelect = scanner.nextInt();
        double[] cowNumbers = new double[numberFields + 1];
        for (int i = 1; i <= numberFields; i++)
            cowNumbers[i] = scanner.nextInt();

        System.out.println((int) find_MaximumAverageCowNumbers(cowNumbers, minimumSizeToSelect, numberFields));
    }

    private static double find_MaximumAverageCowNumbers(double[] cowNumbers, int minimumSizeToSelect, int numberFields) {
        if (cowNumbers.length == minimumSizeToSelect)
            return Math.floor(Arrays.stream(cowNumbers).sum() / minimumSizeToSelect * 1000);

        double minimumAverage = -1e6;
        double maximumAverage = 1e6;

        double limit = 1e-5;
        while (maximumAverage - minimumAverage > limit) {
            double middleAverage = (maximumAverage + minimumAverage) / 2.0;
            if (check(middleAverage, minimumSizeToSelect, cowNumbers, numberFields)) minimumAverage = middleAverage;
            else maximumAverage = middleAverage;
        }
        return (int) Math.floor(maximumAverage * 1000);
    }

    private static boolean check(double average, int minimumSizeToSelect, double[] cowNumbers, int numberFields) {
        double[] backup = copyArray(cowNumbers);
        for (int index = 1; index <= numberFields; index++)
            backup[index] -= average;

        double[] accumulation = new double[backup.length];
        for (int index = 1; index <= numberFields; index++)
            accumulation[index] = accumulation[index - 1] + backup[index];

        double maxAccumulation = -1e10;
        double minMinus = 1e10;
        for (int right = minimumSizeToSelect; right <= numberFields; right++) {
            minMinus = Math.min(minMinus, accumulation[right - minimumSizeToSelect]);
            maxAccumulation = Math.max(maxAccumulation, accumulation[right] - minMinus);
        }
        return maxAccumulation >= 0;
    }

    public static double[] copyArray(double[] array) {
        double[] newArray = new double[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}
