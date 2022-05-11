package CodeForce.P479C;

import java.util.Arrays;
import java.util.Scanner;

public class Exams_MySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberExams = scanner.nextInt();
        long[][] examsInfo = new long[numberExams][2];
        for (int i = 0; i < numberExams; i++) {
            examsInfo[i][1] = scanner.nextInt();
            examsInfo[i][0] = scanner.nextInt();
        }
        Arrays.sort(examsInfo, (date1, date2) -> (int) ((date1[0] == date2[0]) ? date1[1] - date2[1] : date1[0] - date2[0]));
        System.out.println(findMinimumTestDate(examsInfo));
    }

    private static long findMinimumTestDate(long[][] examsInfo) {
        long minimumDate = examsInfo[0][0];
        long currentAdvancedDate = examsInfo[0][0];
        boolean advancedDate = true;
        for (int day = 1; day < examsInfo.length; day++) {
            if (examsInfo[day][0] > currentAdvancedDate) {
                currentAdvancedDate = examsInfo[day][0];
                if (examsInfo[day][0] >= minimumDate) {
                    minimumDate = examsInfo[day][0];
                    advancedDate = true;
                }
                if (examsInfo[day][1] < examsInfo[day - 1][1]) {
                    minimumDate = Math.max(examsInfo[day - 1][1], minimumDate);
                    advancedDate = false;
                }
            }
            if (!advancedDate && examsInfo[day][1] > minimumDate) minimumDate = examsInfo[day][1];

        }
        return minimumDate;
    }
}
