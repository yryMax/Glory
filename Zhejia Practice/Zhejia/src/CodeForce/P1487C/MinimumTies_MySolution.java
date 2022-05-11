package CodeForce.P1487C;

import java.util.Scanner;

public class MinimumTies_MySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTest = scanner.nextInt();

        while (numberTest-- > 0) {
            int numberTeam = scanner.nextInt();
            findMinimumTies(numberTeam);
        }
    }

    private static void findMinimumTies(int numberTeam) {
        StringBuilder result = new StringBuilder();
        if (numberTeam % 2 == 1) {
            int update = numberTeam - 1;
            int previous = -1;
            for (int i = 0; i < numberTeam * (numberTeam - 1) / 2; i++) {
                if (i == update) {
                    result.append("1 ");
                    update += update - 1;
                    previous = 1;
                } else {
                    result.append(-previous).append(" ");
                    previous = -previous;
                }
            }
        } else {
            int half = (numberTeam - 1) / 2;
            for (int group = numberTeam - 1; group >= 1; group--) {
                if (group > half) {
                    result.append("1 ".repeat(Math.max(0, half)));
                    result.append("0 ");
                    result.append("-1 ".repeat(Math.max(0, group - (half + 1))));
                } else result.append("1 ".repeat(group));
            }
        }
        System.out.println(result);
    }
}
