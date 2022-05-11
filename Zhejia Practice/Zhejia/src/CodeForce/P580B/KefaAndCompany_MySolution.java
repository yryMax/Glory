package CodeForce.P580B;

import java.util.Arrays;
import java.util.Scanner;

public class KefaAndCompany_MySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberFriends = scanner.nextInt();
        int minWealthDifference = scanner.nextInt();
        Integer[][] friends = new Integer[numberFriends][2];
        for (int i = 0; i < numberFriends; i++) {
            friends[i][0] = scanner.nextInt();
            friends[i][1] = scanner.nextInt();
        }
        Arrays.sort(friends, (f1, f2) -> f1[0] - f2[0]);
        System.out.println(findMaximumFriendShip(friends, minWealthDifference));

    }

    private static long findMaximumFriendShip(Integer[][] friends, int minWealthDifference) {
        if (friends.length == 1) return friends[0][1];
        long max = Integer.MIN_VALUE;
        int left = 0;
        long currentSum = 0;
        for (int right = 0; right < friends.length; right++) {
            if (friends[right][0] - friends[left][0] >= minWealthDifference) {
                max = Math.max(max, currentSum);
                for (int i = left; i < binarySearchLargerThan(friends, left, right, friends[right][0] - (minWealthDifference - 1)); i++)
                    currentSum -= friends[i][1];
                left = binarySearchLargerThan(friends, left, right, friends[right][0] - (minWealthDifference - 1));
            }
            currentSum += friends[right][1];
        }
        return Math.max(currentSum, max);
    }

    private static int binarySearchLargerThan(Integer[][] friends, int left, int right, long target) {
        if (left == right) return friends[left][0] >= target ? left : -1;
        else {
            int middle = (left + right) / 2;
            return (friends[middle][0] >= target) ? binarySearchLargerThan(friends, left, middle, target) : binarySearchLargerThan(friends, middle + 1, right, target);
        }
    }
}
