package CodeForce.P798B;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MikeAndStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberStrings = Integer.parseInt(scanner.nextLine());
        List<String> stringList = new ArrayList<>();
        while (numberStrings-- > 0)
            stringList.add(scanner.nextLine());

        System.out.println(findMinimumMove(stringList));

    }

    private static int findMinimumMove(List<String> stringList) {
        int minimumDistance = Integer.MAX_VALUE;
        for (int i = 0; i < stringList.size(); i++) {
            String matchString = stringList.get(i);
            int sumDistance = 0;
            for (String string: stringList) {
                if (string.equals(matchString)) continue;
                int distance = distance(matchString, string);
                if (distance == -1)
                    return -1;
                else sumDistance += distance;
            }
            if (sumDistance < minimumDistance)
                minimumDistance= sumDistance;
        }
        return minimumDistance;
    }

    private static int distance(String matchString, String string) {
        int minDistance = -1;
        for (int offset = 0; offset < string.length(); offset++) {
            if (string.equals(matchString)) {
                minDistance = offset;
                break;
            }
            char head = string.charAt(0);
            string = string.substring(1) + head;
        }
        return minDistance;
    }
}
