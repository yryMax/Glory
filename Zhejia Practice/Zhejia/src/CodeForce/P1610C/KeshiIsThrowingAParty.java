package CodeForce.P1610C;

import java.util.Scanner;

public class KeshiIsThrowingAParty {
    static int[][] friends;
    private static boolean valid(int num) {
        int put = 0;
        for (int i = 0; i < friends.length; i++) {
            if (put + 1 + friends[i][0] < num) continue;
            if (friends[i][1] < put) continue;
            put++;
            if (put == num) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = scanner.nextInt();
        while (numTest-- > 0) {
            int numFriend = scanner.nextInt();
            friends = new int[numFriend][2];
            for (int i = 0; i < numFriend; i++) {
                friends[i][0] = scanner.nextInt();
                friends[i][1] = scanner.nextInt();
            }

            int left = 0, right = numFriend;
            while (left < right) {
                int mid = (left + right + 1) / 2;
                if (valid(mid)) left = mid; else right = mid - 1;
            }
            System.out.println(left);
        }
    }
}
