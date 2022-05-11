package CodeForce.P479C;

import java.util.Arrays;
import java.util.Scanner;

public class Exams_StandardSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberExams = scanner.nextInt();
        int[][] examInfo = new int[numberExams][2];
        for (int i = 0; i < numberExams; i++) {
            examInfo[i][0] = scanner.nextInt();
            examInfo[i][1] = scanner.nextInt();
        }
        Arrays.sort(examInfo, (exam1, exam2) -> (exam1[0] == exam2[0]) ? exam1[1] - exam2[1] : exam1[0] - exam2[0]);
        System.out.println(findMinimumTestDate(examInfo));
    }

    private static int findMinimumTestDate(int[][] examsInfo) {
        int minimumDate = 0;
        for (int[] exam : examsInfo) minimumDate = (exam[1] >= minimumDate) ? exam[1] : exam[0];
        return minimumDate;
    }
}
