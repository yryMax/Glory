package CodeForce.P545C;

import java.util.Scanner;

public class Woodcutters_MySolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTrees = scanner.nextInt();
        int[][] trees = new int[numberTrees][2];
        for (int i = 0; i < numberTrees; i++) {
            trees[i][0] = scanner.nextInt();
            trees[i][1] = scanner.nextInt();
        }

        System.out.println(findMaximumTrees(trees));
    }

    private static int findMaximumTrees(int[][] trees) {
        if (trees.length == 1) return 1;
        int[] positions = new int[trees.length];
        positions[0] = (trees[1][0] - trees[0][0] > trees[0][1]) ? trees[0][0] + trees[0][1] : trees[0][0];

        int[][] optimal = new int[trees.length][3];
        optimal[0][0] = 0;
        optimal[0][1] = 1;
        optimal[0][2] = (trees[1][0] - trees[0][0] > trees[0][1]) ? 1 : Integer.MIN_VALUE;

        for (int t = 1; t < trees.length; t++) {
            optimal[t][0] = max(optimal[t - 1][0], optimal[t - 1][1], optimal[t - 1][2]);

            optimal[t][1] = max(optimal[t - 1][0] + ((trees[t][0] - trees[t - 1][0] > trees[t][1]) ? 1 : 0), optimal[t - 1][1] + ((trees[t][0] - trees[t - 1][0] > trees[t][1]) ? 1 : 0), optimal[t - 1][2] + ((trees[t][0] - positions[t - 1] > trees[t][1]) ? 1 : 0));

            optimal[t][2] = (t == trees.length - 1) ? max(optimal[t - 1][0], optimal[t - 1][1], optimal[t - 1][2]) + 1 : max(optimal[t - 1][0], optimal[t - 1][1], optimal[t - 1][2]) + ((trees[t + 1][0] - trees[t][0] > trees[t][1]) ? 1 : 0);
            positions[t] = (t == trees.length - 1) ? Integer.MAX_VALUE : trees[t][0] + ((trees[t + 1][0] - trees[t][0] > trees[t][1]) ? trees[t][1] : 0);
        }
        return max(optimal[trees.length - 1][0], optimal[trees.length - 1][1], optimal[trees.length - 1][2]);
    }

    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
