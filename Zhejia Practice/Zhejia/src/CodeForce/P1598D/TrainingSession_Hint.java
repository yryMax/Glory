package CodeForce.P1598D;

import java.util.*;

public class TrainingSession_Hint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberCase = scanner.nextInt();
        while (numberCase-- > 0) {
            int numberProblem = scanner.nextInt();
            int[][] problems = new int[numberProblem + 1][2];
            for (int i = 1; i <= numberProblem; i++) {
                problems[i][0] = scanner.nextInt();
                problems[i][1] = scanner.nextInt();
            }
            findTotalCombinations(problems, numberProblem);
        }
    }

    private static void findTotalCombinations(int[][] problems, int numberProblem) {
        long totalSum = (long) numberProblem * (numberProblem - 1) * (numberProblem - 2) / 6;
        int[] topic = new int[problems.length];
        int[] difficult = new int[problems.length];

        for (int i = 0; i < problems.length; i++) {
            topic[problems[i][0]]++;
            difficult[problems[i][1]]++;
        }

        for (int i = 0; i < problems.length; i++) {
            if (topic[problems[i][0]] >= 1 && difficult[problems[i][1]] >= 1)
                totalSum -= (long) (topic[problems[i][0]] - 1) * (difficult[problems[i][1]] - 1);
        }
        System.out.println(totalSum);
    }
}
