package CodeForce.P1195C;

import java.util.Scanner;

public class BasketBallExercise_MySolution {
    static long[][] records;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberEachTeam = scanner.nextInt();
        long[][] members = new long[2][numberEachTeam];
        records = new long[3][numberEachTeam];
        for (int i = 0; i < numberEachTeam; i++)
            members[0][i] = scanner.nextInt();

        for (int i = 0; i < numberEachTeam; i++)
            members[1][i] = scanner.nextInt();

        System.out.println(chooseMaximumHeights(members, numberEachTeam));
    }

    private static long chooseMaximumHeights(long[][] members, int numberEachTeam) {
        records[0][0] = members[0][0];
        records[1][0] = members[1][0];
        records[2][0] = 0;

        for (int index = 1; index < numberEachTeam; index++) {
            records[0][index] = Math.max(records[1][index - 1], records[2][index - 1]) + members[0][index];
            records[1][index] = Math.max(records[0][index - 1], records[2][index - 1]) + members[1][index];
            records[2][index] = Math.max(records[0][index - 1], records[1][index - 1]);
        }
        return Math.max(records[0][numberEachTeam - 1], Math.max(records[1][numberEachTeam - 1], records[2][numberEachTeam - 1]));

    }
}
